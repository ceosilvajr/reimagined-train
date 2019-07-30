package com.ceosilvajr.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ceosilvajr.roomdb.converter.DateConverter
import com.ceosilvajr.roomdb.dao.PirmaDao
import com.ceosilvajr.roomdb.entities.PirmaEntity

/**
 * @author ceosilvajr@gmail.com
 */
@Database(entities = arrayOf(PirmaEntity::class), version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pirmaDao(): PirmaDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "signme.db").build()
    }
}