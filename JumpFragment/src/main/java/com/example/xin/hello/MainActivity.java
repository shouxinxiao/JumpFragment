package com.example.xin.hello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().
                beginTransaction().
                addToBackStack(null).
                replace(R.id.content, new OneFragment()).
                commit();
    }
}
