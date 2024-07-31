package uk.ac.tees.mad.D3905133

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.D3905133.model.local.NewsArticle
import uk.ac.tees.mad.D3905133.model.remote.Article
import uk.ac.tees.mad.D3905133.model.remote.UserProfile
import uk.ac.tees.mad.D3905133.model.remote.userModel
import uk.ac.tees.mad.D3905133.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val appRepository: AppRepository,
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _favoriteNews = MutableStateFlow<List<NewsArticle>>(emptyList())
    val favoriteNews: StateFlow<List<NewsArticle>> = _favoriteNews

    val isSignedIn = mutableStateOf(false)
    val isLoading = mutableStateOf(false)
    val newsList = mutableStateOf(emptyList<Article>())
    val userProfile = mutableStateOf<UserProfile?>(null)

    init {
        auth.currentUser?.let {
            isSignedIn.value = true
        }
        fetchNews()
        getFavoriteNews()
        fetchUserProfile()
    }

    fun signUp(context: Context, email: String, password: String) {
        isLoading.value = true
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading.value = false
                if (task.isSuccessful) {
                    auth.currentUser?.let {
                        val user = userModel(email, password)
                        saveUserDataToFirestore(context, user)
                    }
                } else {
                    Log.d(TAG, "signUp: failure", task.exception)
                    Toast.makeText(context, "Sign Up Failed, ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveUserDataToFirestore(context: Context, user: userModel) {
        val userUid = auth.currentUser?.uid
        userUid?.let { uid ->
            firestore.collection("users").document(uid).set(user)
                .addOnSuccessListener {
                    Log.d("Firestore", "Added user data")
                    isSignedIn.value = true
                    Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.d("Firestore", "Failed to add user data: ${e.message}")
                    Toast.makeText(context, "Failed to save user data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }


    fun fetchUserProfile() {
        val userUid = auth.currentUser?.uid
        Log.d("userUid", userUid.toString())
        userUid?.let { uid ->
            firestore.collection("users").document(uid).get().addOnSuccessListener { document ->
                userProfile.value = document.toObject(UserProfile::class.java)
                Log.d("Firestore", "Fetched user data ${userProfile.value}")
            }.addOnFailureListener { e ->
                Log.d("Firestore", e.message.toString())
            }
        }
    }

    fun updateUserProfile(context : Context, name: String, location: String) {
        val uid = auth.currentUser?.uid
        if (uid!=null) {
            val user = UserProfile(
                uid = uid,
                email = auth.currentUser?.email ?: "",
                name = name,
                location = location
            )
            firestore.collection("users").document(uid).set(user).addOnSuccessListener {
                Log.d("Firestore", "Updated user data")
                fetchUserProfile()
                Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                Log.d("Firestore", e.message.toString())
                Toast.makeText(context, "Failed to update profile: ${e.message}", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun logIn(context: Context, email: String, password: String) {
        isLoading.value = true
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            isLoading.value = false
            Log.d(TAG, "logIn: success")
            Toast.makeText(context, "Log In Successful", Toast.LENGTH_SHORT).show()
            isSignedIn.value = true
        }.addOnFailureListener {
            isLoading.value = false
            Log.d(TAG, "logIn: failure", it)
            Toast.makeText(context, "Log In Failed, ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun getFavoriteNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = appRepository.getFavNews()
            _favoriteNews.value = response
            Log.d("FavNews", _favoriteNews.value.toString())
        }
    }

    fun deleteNews(news: NewsArticle) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteNote(news)
            getFavoriteNews()
        }
    }

    fun insertNews(title: String?, description: String?, publishedAt: String?, author: String?) {
        val news = NewsArticle(
            title = title ?: "No Title",
            description = description ?: "No Description",
            published = publishedAt ?: "No Date",
            author = author ?: "Unknown"
        )
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.insertNote(news)
            getFavoriteNews()
        }
    }

    fun checkSignedIn(): Boolean {
        return auth.currentUser != null
    }

    fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            newsList.value = appRepository.fetchNewsApi()
            Log.d("News", "${newsList.value}")
        }
    }
}