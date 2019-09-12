package com.jm.jutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Utils {

    public static String timeStamp2Date(long utcTime) {


        SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//当地时间格式
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(utcTime);
        return localTime;
    }


    public static String ReadFromFile(File file) {
        if ((file != null) && file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                String config = reader.readLine();
                fin.close();
                return config;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
