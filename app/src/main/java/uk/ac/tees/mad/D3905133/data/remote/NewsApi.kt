package uk.ac.tees.mad.D3905133.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.mad.D3905133.model.remote.NewsModel

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
    ): Response<NewsModel>
}