package com.example.fabcompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fabcompose.database.dao.DailyTaskDao
import com.example.fabcompose.database.entities.DailyTaskEntity


@Database(
    entities = [DailyTaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DailyTaskRoomDb : RoomDatabase() {
    abstract fun dailyTaskDao(): DailyTaskDao

    companion object {

        @Volatile
        private var INSTANCE: DailyTaskRoomDb? = null

        fun getDatabase(context: Context): DailyTaskRoomDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    DailyTaskRoomDb::class.java, "DailyTask_Db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
}