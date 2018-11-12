package com.alamedapps.br.ihs_app.utils;

public class FormatterUtils {


    public static String firstLetterUppercase(String word){

        String wordFormatted = word.substring(0, 1) + word.substring(1).toLowerCase();

        return wordFormatted;
    }



}
