package uk.ac.tees.mad.D3905133.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uk.ac.tees.mad.D3905133.model.local.NewsArticle

@Dao
interface NewsDao {

    @Query("SELECT * FROM newsarticles")
    suspend fun getNews(): List<NewsArticle>

    @Insert
    suspend fun insertNews(news : NewsArticle)

    @Delete
    suspend fun deleteNews(news : NewsArticle)
}