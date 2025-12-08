package org.utl.fitblueapp.db

import androidx.room.TypeConverter
import java.util.Date

class ConvertidorFecha{

@TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millis: Long?): Date? {
        return millis?.let { Date(it) }
    }
}