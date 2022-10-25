package com.coloful.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.coloful.R;
import com.coloful.adapters.ListViewStudySetAdapter;
import com.coloful.model.Question;

import java.util.ArrayList;
import java.util.List;

public class StudySetDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    List<Question> questionList = new ArrayList<>();
    ListView listView;
    Button btnStudySet;
    ImageButton imnLearn;
    ImageButton imgFlashcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_set_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4257b0")));
        questionList.add(
                new Question("Which architecture style supports scalability and reliability better?\n" +
                        "A. Buffered message-based architecture (2)\n" +
                        "B. Both (1) and (2) are the same\n" +
                        "C. Non-buffered Event-based architecture (1)", "A"));
        questionList.add(
                new Question("Which architecture style supports scalability and reliability better?\n" +
                        "A. Buffered message-based architecture (2)\n" +
                        "B. Both (1) and (2) are the same\n" +
                        "C. Non-buffered Event-based architecture (1)", "A"));
        listView = findViewById(R.id.lv_study_set_details);
        ListViewStudySetAdapter adapter = new ListViewStudySetAdapter(this, questionList);
        listView.setAdapter(adapter);

        btnStudySet = findViewById(R.id.btn_study_set);
        imgFlashcard = findViewById(R.id.img_flashcard);
        imnLearn = findViewById(R.id.img_learn);

        btnStudySet.setOnClickListener(this::onClick);
        imgFlashcard.setOnClickListener(this::onClick);
        imnLearn.setOnClickListener(this::onClick);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MainActivity mainActivity = new MainActivity();
                Intent intent = new Intent(this, mainActivity.getClass());
                startActivity(intent);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_study_set:
                 intent = new Intent(this, OptionPracticeActivity.class);
                startActivity(intent);
                break;
            case R.id.img_flashcard:
                intent = new Intent(this, FlashcardActivity.class);
                startActivity(intent);
                break;
            case R.id.img_learn:
                intent = new Intent(this, LearningActivity.class);
                startActivity(intent);
                break;
        }
    }
}