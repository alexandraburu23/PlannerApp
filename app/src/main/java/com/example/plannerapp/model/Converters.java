package com.example.plannerapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;

public class Converters {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate toDate(String dateString) {
        if(dateString == null){
            return null;
        } else {
            return LocalDate.parse(dateString);
        }
    }
    @TypeConverter
    public static String toDateString(LocalDate date){
        if(date == null){
            return null;
        } else {
            return date.toString();
        }
    }
}
