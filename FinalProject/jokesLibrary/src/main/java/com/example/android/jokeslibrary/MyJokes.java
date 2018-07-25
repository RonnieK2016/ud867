package com.example.android.jokeslibrary;

import java.util.Random;

public class MyJokes {

    private String[] jokesArr = new String[] {
            "If doctors were like software engineers, they would say things like \"Have you tried killing yourself and being reborn?\"",
            "There are only 10 types of people in the world: those that understand binary and those that don’t.",
            "CAPS LOCK – Preventing Login Since 1980.",
            "Artificial intelligence usually beats real stupidity.",
            "Computers make very fast, very accurate mistakes."
    };


    public String tellJoke() {
        return jokesArr[new Random().nextInt(jokesArr.length)];
    }
}
