package uk.ac.tees.mad.D3905133.model.remote

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)