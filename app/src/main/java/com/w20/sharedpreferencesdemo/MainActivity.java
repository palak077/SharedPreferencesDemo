package com.w20.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //there is class SharedPreferences
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.w20.sharedpreferencesdemo", Context.MODE_PRIVATE);
        // write into shared preferences
        sharedPreferences.edit().putString("name", "Ali").apply();
        // read from shared preferences
        String name = sharedPreferences.getString("name", "");
        Log.i(TAG, "onCreate: " + name);

        ArrayList<String> names = new ArrayList<>(Arrays.asList("Ali1", "Nahid1", "Sarmad1"));
//        sharedPreferences.edit().putStringSet("names", new HashSet<String>(names)).apply();
//
//        Set<String> newNames = sharedPreferences.getStringSet("names", new HashSet<String>());
//        Log.i(TAG, "onCreate: " + newNames.toString());

        try {
            sharedPreferences.edit().putString("names", ObjectSerializer.serialize(names)).apply();
            Log.d(TAG, "onCreate: " + ObjectSerializer.serialize(names));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> newNames = new ArrayList<>();

        try {
            newNames = (ArrayList) ObjectSerializer.deserialize(sharedPreferences.getString("names", ObjectSerializer.serialize(new ArrayList<>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i(TAG, "onCreate: " + newNames.toString());

    }
}
