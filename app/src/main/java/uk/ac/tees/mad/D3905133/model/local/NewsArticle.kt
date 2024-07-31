package uk.ac.tees.mad.D3905133.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newsarticles")
data class NewsArticle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title : String?,
    val description : String?,
    val published : String?,
    val author : String?
)
