package com.example.uas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Univ::class], version = 1)
abstract class UnivRoomDatabase: RoomDatabase() {
    abstract fun UnivDao() : UnivDao

    companion object {
        @Volatile
        private var INSTANCE:UnivRoomDatabase?= null

        @JvmStatic
        fun getDatabase(context: Context):UnivRoomDatabase {
            if (INSTANCE == null) {
                synchronized(UnivRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UnivRoomDatabase::class.java, "chara_database")
                        .build()
                }
            }
            return INSTANCE as UnivRoomDatabase
        }
    }
}