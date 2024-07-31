package uk.ac.tees.mad.D3905133.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uk.ac.tees.mad.D3905133.model.local.NewsArticle


@Database(entities = [NewsArticle::class], version = 1)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun newsDao(): NewsDao

    companion object{
        @Volatile
        private var Instance : NewsDatabase? = null

        fun getDatabase(context: Context):NewsDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context,NewsDatabase::class.java,"quote_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}