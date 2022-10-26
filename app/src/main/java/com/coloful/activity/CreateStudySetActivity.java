package com.coloful.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.coloful.R;
import com.coloful.adapters.ListViewCreateSetAdapter;
import com.coloful.dao.DBHelper;
import com.coloful.dao.QuizDao;
import com.coloful.datalocal.DataLocalManager;
import com.coloful.model.Account;
import com.coloful.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateStudySetActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSave;
    ImageButton imgAdd;
    List<Question> questionList = new ArrayList<>();
    EditText edt_create_quiz_title;
    Account account;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_set);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4257b0")));

        imgAdd = findViewById(R.id.img_add_question);

        questionList.add(new Question());
        listView = findViewById(R.id.lv_create_set);
        ListViewCreateSetAdapter adapter = new ListViewCreateSetAdapter(this, questionList);
        listView.setAdapter(adapter);

//        btnSave.setOnClickListener(this::onClick);
        imgAdd.setOnClickListener(this::onClick);
        account = DataLocalManager.getAccount();

        edt_create_quiz_title = findViewById(R.id.edt_create_quiz_title);
        btnSave = findViewById(R.id.btn_save_set);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizDao db = new QuizDao();
                db.addQuiz(CreateStudySetActivity.this,account.getUsername(),edt_create_quiz_title.getText().toString().trim());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MainActivity mainActivity = new MainActivity();
                Intent intent = new Intent(this, mainActivity.getClass());
                intent.putExtra("backScreen", "Home");
                startActivity(intent);
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_set:
                break;
            case R.id.img_add_question:
                break;
        }
    }

}