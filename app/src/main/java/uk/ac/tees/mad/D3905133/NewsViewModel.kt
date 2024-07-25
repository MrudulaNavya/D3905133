package uk.ac.tees.mad.D3905133

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val auth: FirebaseAuth,
) : ViewModel() {

    val isSignedIn = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    init {
        auth.currentUser?.let {
            isSignedIn.value = true
        }
    }

    fun signUp(context : Context, email: String, password: String) {
        isLoading.value = true
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            isLoading.value = false
            Log.d(TAG, "signUp: success")
            Toast.makeText(context,"Sign Up Successful", Toast.LENGTH_SHORT).show()
            isSignedIn.value = true
        }.addOnFailureListener { e->
            isLoading.value = false
            Log.d(TAG, "signUp: failure", e)
            Toast.makeText(context,"Sign Up Failed, ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun logIn(context : Context, email: String, password: String) {
        isLoading.value = true
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            isLoading.value = false
            Log.d(TAG, "logIn: success")
            Toast.makeText(context,"Log In Successful", Toast.LENGTH_SHORT).show()
            isSignedIn.value = true
        }.addOnFailureListener {
            isLoading.value = false
            Log.d(TAG, "logIn: failure", it)
            Toast.makeText(context,"Log In Failed, ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkSignedIn():Boolean{
        if (auth.currentUser!=null){
            return true
        }else{
            return false
        }
    }
}