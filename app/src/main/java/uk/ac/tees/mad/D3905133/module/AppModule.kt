package uk.ac.tees.mad.D3905133.module

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.mad.D3905133.data.local.NewsDao
import uk.ac.tees.mad.D3905133.data.local.NewsDatabase
import uk.ac.tees.mad.D3905133.data.remote.NewsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return NewsDatabase.getDatabase(context)
    }

    @Provides
    fun provideQuoteDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return   Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    fun providesAuthentication() : FirebaseAuth = Firebase.auth

    @Provides
    fun providesFirestore() : FirebaseFirestore = Firebase.firestore
}