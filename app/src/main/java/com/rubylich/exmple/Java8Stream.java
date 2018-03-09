package com.rubylich.exmple;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rl98880 on 02/03/2018.
 */

public class Java8Stream {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void streamMap() {
        List<String> myList =
                Arrays.asList("a", "b", "c");

        myList
                .stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

    }
}
