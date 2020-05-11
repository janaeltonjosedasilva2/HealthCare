package br.saojudas.mobile.healthcare.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConverterTime {
    @TypeConverter
    public Long paraLong(Calendar valor) {
        return valor == null ? null : valor.getTimeInMillis();
    }

    @TypeConverter
    public Calendar paraCalendar(Long valor) {
        Calendar horaDose = Calendar.getInstance();
        if (valor != null) {
            horaDose.setTimeInMillis(valor);
        }
        return horaDose;
    }
}