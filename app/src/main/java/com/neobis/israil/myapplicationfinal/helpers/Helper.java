package com.neobis.israil.myapplicationfinal.helpers;

import android.util.Log;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Helper {
    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    public static final String WEATHER_URL = "https://samples.openweathermap.org/";

    public static <T> List<T> selectTenElements(List<T> list) {
        Collections.shuffle(list);
        return list.subList(0, list.size() > 10 ? 10 : list.size());
    }
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            Log.d("Error","List size must be greater than 0");
        }

        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
}