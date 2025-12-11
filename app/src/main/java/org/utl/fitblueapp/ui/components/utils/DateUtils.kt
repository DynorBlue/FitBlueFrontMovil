package org.utl.fitblueapp.ui.components.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    
    fun formatDate(date: Date, pattern: String = "MMM dd, yyyy"): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(date)
    }
    
    fun formatShortDate(date: Date): String {
        return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date)
    }
    
    fun formatLongDate(date: Date): String {
        return SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(date)
    }
    
    fun isDateValid(date: Date): Boolean {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        
        val oneYearFromNow = Calendar.getInstance().apply {
            timeInMillis = today.timeInMillis
            add(Calendar.YEAR, 1)
        }
        
        val calendar = Calendar.getInstance().apply {
            time = date
        }
        
        return calendar.timeInMillis >= today.timeInMillis && 
               calendar.timeInMillis <= oneYearFromNow.timeInMillis
    }
    
    fun isFutureDate(date: Date): Boolean {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        
        val calendar = Calendar.getInstance().apply {
            time = date
        }
        
        return calendar.timeInMillis >= today.timeInMillis
    }
    
    fun isPastDate(date: Date): Boolean {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }
        
        val calendar = Calendar.getInstance().apply {
            time = date
        }
        
        return calendar.timeInMillis <= today.timeInMillis
    }
    
    fun getDaysBetween(startDate: Date, endDate: Date): Long {
        val diffInMillis = endDate.time - startDate.time
        return diffInMillis / (1000 * 60 * 60 * 24)
    }
    
    fun addDays(date: Date, days: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_MONTH, days)
        return calendar.time
    }
    
    fun getStartOfDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }
    
    fun getEndOfDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }
}