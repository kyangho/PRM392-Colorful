package com.coloful.activity;

import androidx.appcompat.app.AppCompatActivity;

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
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        getSupportActionBar().hide();

        quizId = getIntent().getIntExtra("quizId", 0);

        Quiz q = QuizDao.init().get(0);
        questionList = q.getQuestionList();
        currentQues = questionList.get(0);
        position = 0;

        imgExist = findViewById(R.id.img_exist);
        imgPrevious = findViewById(R.id.img_flash_pre);
        imgNext = findViewById(R.id.img_flash_next);
        tvFlashcardNumber = findViewById(R.id.tv_flash_card_number);
        tvContent = findViewById(R.id.tv_content);

        tvContent.setText(currentQues.getContent());
        tvFlashcardNumber.setText((position + 1) + "/" + questionList.size());
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
                break;
            case R.id.img_flash_pre:
                position--;
                imgNext.setVisibility(View.VISIBLE);
                if (position == 0) {
                    imgPrevious.setVisibility(View.INVISIBLE);
                    break;
                }else {
                    imgPrevious.setVisibility(View.VISIBLE);
                    currentQues = questionList.get(position);
                    tvContent.setText(currentQues.getContent());
                    tvFlashcardNumber.setText((position) + "/" + questionList.size());

                    break;
                }
            case R.id.img_flash_next:
                position++;
                imgPrevious.setVisibility(View.VISIBLE);
                if (position == questionList.size()) {
                    imgNext.setVisibility(View.INVISIBLE);
                    break;
                }else {
                    imgNext.setVisibility(View.VISIBLE);
                    currentQues = questionList.get(position);
                    tvContent.setText(currentQues.getContent());

                    tvFlashcardNumber.setText(position + "/" + questionList.size());
                    break;
                }
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
}