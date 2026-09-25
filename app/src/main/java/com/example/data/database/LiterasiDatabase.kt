package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        StudentProfileEntity::class,
        ReadingProgressEntity::class,
        GameProgressEntity::class,
        QuizHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LiterasiDatabase : RoomDatabase() {
    abstract fun literasiDao(): LiterasiDao

    companion object {
        @Volatile
        private var INSTANCE: LiterasiDatabase? = null

        fun getInstance(context: Context): LiterasiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LiterasiDatabase::class.java,
                    "literasi_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
