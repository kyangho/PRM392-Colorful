package com.coloful.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.coloful.R;

public class FlashcardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        getSupportActionBar().hide();
    }
}