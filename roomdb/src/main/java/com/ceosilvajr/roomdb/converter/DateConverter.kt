package com.ceosilvajr.roomdb.converter

import androidx.room.TypeConverter
import java.util.*


/**
 * @author ceosilvajr@gmail.com
 */
class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long): Date {
        return Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return (date.time)
    }
}