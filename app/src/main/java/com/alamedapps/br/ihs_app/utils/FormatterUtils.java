package com.alamedapps.br.ihs_app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FormatterUtils {


    public static String firstLetterUppercase(String word) {

        String wordFormatted = word.substring(0, 1) + word.substring(1).toLowerCase();

        return wordFormatted;
    }


    public static String getNowDate() {
        Calendar calendar = new GregorianCalendar();

        Locale local = new Locale("pt", "BR");
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy HH:mm", local);

        calendar.setTime(new Date());
        String dateString = f1.format(calendar.getTime());

        return dateString;
    }

    public static boolean isPastMouth(String dateText) throws ParseException {
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date now = new Date();
        Date date = f1.parse(dateText);
        int monthInterval = date.getMonth() - now.getMonth();
        if (date.getDay() == now.getDay() && Math.abs(monthInterval) >= 2)
            return true;
        return false;
    }

    public static String dateFormatted(String dateText) throws ParseException {
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat f2 = new SimpleDateFormat(" 'às' HH:mm");
        SimpleDateFormat f1 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy, HH:mm", local);

        Date date = f1.parse(dateText);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        String nome = "";

        if (calendar.getTime().getDay() == date.getDay() &&
                calendar.getTime().getMonth() == date.getMonth()) {
            nome = "Hoje";
        } else if (date.getDay() - calendar.getTime().getDay() == 1) {
            nome = "Ontem";
        }

        date = new Date();
        return null;
    }


}
