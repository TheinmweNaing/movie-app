package com.example.movieapp.model;

import androidx.room.TypeConverter;

import com.example.movieapp.model.entity.MovieCast;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class Converters {

    @TypeConverter
    public static String fromOccupation(MovieCast.Occupation occupation) {
        return occupation.name();
    }

    @TypeConverter
    public static MovieCast.Occupation toOccupation(String value) {
        return MovieCast.Occupation.valueOf(value);
    }

    @TypeConverter
    public static String fromLocalDate(LocalDate value) {
        if (value != null) {
            return value.toString("yyyy/MM/dd");
        }
        return null;
    }

    @TypeConverter
    public static LocalDate toLocalDate(String value) {
        if (value != null) {
            return LocalDate.parse(value, DateTimeFormat.forPattern("yyyy/MM/dd"));
        }
        return null;
    }
}
