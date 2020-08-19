package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtils {

    public static final String IST = "Asia/Kolkata";
    public static final String format = "yyyy-MM-dd HH:mm";

    public static LocalDateTime getLocalDateTimefromString(String time,String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, formatter);
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(ZoneId.of(IST));
    }
}
