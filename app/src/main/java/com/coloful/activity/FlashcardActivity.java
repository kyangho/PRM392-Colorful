package com.coloful.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.coloful.R;
import com.coloful.dao.QuizDao;
import com.coloful.model.Question;
import com.coloful.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class FlashcardActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgExist, imgPrevious, imgNext;
    TextView tvFlashcardNumber, tvContent;
    List<Question> questionList = new ArrayList<>();
    Question currentQues = new Question();
    int quizId;
    int posPre, posNext;
    String screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        getSupportActionBar().hide();

        quizId = getIntent().getIntExtra("quizId", 0);
        screen = getIntent().getStringExtra("screen");

        QuizDao dao = new QuizDao();
        Quiz q = dao.getQuizById(this, quizId);
        questionList = q.getQuestionList();
        currentQues = questionList.get(0);
        posPre = 0;
        posNext = 1;

        imgExist = findViewById(R.id.img_exist);
        imgPrevious = findViewById(R.id.img_flash_pre);
        imgNext = findViewById(R.id.img_flash_next);
        tvFlashcardNumber = findViewById(R.id.tv_flash_card_number);
        tvContent = findViewById(R.id.tv_content);

        tvContent.setText(currentQues.getContent());
        tvFlashcardNumber.setText(posNext + "/" + questionList.size());
        imgPrevious.setVisibility(View.INVISIBLE);

        imgExist.setOnClickListener(this::onClick);
        imgPrevious.setOnClickListener(this::onClick);
        imgNext.setOnClickListener(this::onClick);
        tvContent.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_exist:
                backScreen(screen);
                break;
            case R.id.img_flash_pre:
                imgNext.setVisibility(View.VISIBLE);
                currentQues = questionList.get(posPre);
                tvContent.setText(currentQues.getContent());
                tvFlashcardNumber.setText(posPre + "/" + questionList.size());
                posNext = posPre;
                posPre--;
                if (posPre == 0) {
                    imgPrevious.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.img_flash_next:
                imgPrevious.setVisibility(View.VISIBLE);
                currentQues = questionList.get(posNext);
                tvContent.setText(currentQues.getContent());
                posNext++;
                tvFlashcardNumber.setText(posNext + "/" + questionList.size());
                posPre = posNext - 1;
                if (posNext == questionList.size()) {
                    imgNext.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.tv_content:
                String content = tvContent.getText().toString();
                if (content.equalsIgnoreCase(currentQues.getContent())) {
                    tvContent.setText(currentQues.getAnswer());
                } else {
                    tvContent.setText(currentQues.getContent());
                }
                break;
        }
    }

    private void backScreen(String screen) {
        Intent intent;
        switch (screen) {
            case "study details":
                intent = new Intent(this, StudySetDetailsActivity.class);
                intent.putExtra("screen", "home");
                intent.putExtra("quizId", quizId);
                startActivity(intent);
                break;
            case "option practice":
                intent = new Intent(this, OptionPracticeActivity.class);
                intent.putExtra("quizId", quizId);
                startActivity(intent);
                break;

        }
    }
}