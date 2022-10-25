package com.coloful.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.coloful.R;

public class OptionPracticeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvLearnOption;
    TextView tvFlashcardOption;
    TextView tvCancelOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_practice);
        getSupportActionBar().hide();

        tvCancelOption = findViewById(R.id.tv_cancel_option);
        tvLearnOption = findViewById(R.id.tv_learn_option);
        tvFlashcardOption = findViewById(R.id.tv_flashcard_option);

        tvCancelOption.setOnClickListener(this::onClick);
        tvLearnOption.setOnClickListener(this::onClick);
        tvFlashcardOption.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_flashcard_option:
                intent = new Intent(this, FlashcardActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_learn_option:
                intent = new Intent(this, LearningActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_cancel_option:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }
}