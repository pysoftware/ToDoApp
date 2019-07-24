package com.magere.to_do.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ToDoEntity::class], version = 2)
abstract class Database : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {
        private const val DB_NAME = "persons_db"
        private var instance: com.magere.to_do.data.db.Database? = null

        fun getInstance(context: Context): com.magere.to_do.data.db.Database? {
            if (instance == null) {
                synchronized(com.magere.to_do.data.db.Database::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.magere.to_do.data.db.Database::class.java, DB_NAME
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}