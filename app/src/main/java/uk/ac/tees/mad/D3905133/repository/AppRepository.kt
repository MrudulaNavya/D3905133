package uk.ac.tees.mad.D3905133.repository

import android.util.Log
import retrofit2.Retrofit
import uk.ac.tees.mad.D3905133.data.local.NewsDao
import uk.ac.tees.mad.D3905133.data.remote.NewsApi
import uk.ac.tees.mad.D3905133.model.local.NewsArticle
import uk.ac.tees.mad.D3905133.model.remote.Article
import javax.inject.Inject

class AppRepository @Inject constructor(
    val newsApi: NewsApi,
    val newsDao: NewsDao
) {

    suspend fun fetchNewsApi() : List<Article>{
        val response = newsApi.getNews("gb","47dc0f46bc3a43618823019cbcf63df4")
        Log.d("NewsResponse", "$response")
        val articles = response.body()?.articles
        Log.d("NewsArticles", "$articles")
        if (articles!=null) {
            return articles
        }else{
            return emptyList()
        }
    }

    suspend fun insertNote(note:NewsArticle){
        newsDao.insertNews(note)
    }

    suspend fun getFavNews(): List<NewsArticle>{
        val response = newsDao.getNews()
        return response
    }

    suspend fun deleteNote(newsArticle:NewsArticle){
        newsDao.deleteNews(newsArticle)
    }
}