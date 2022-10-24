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
import com.coloful.adapters.ListViewAdapter;
import com.coloful.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateStudySetActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSave;
    ImageButton imgAdd;
    List<Question> questionList = new ArrayList<>();

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_set);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4257b0")));

        imgAdd = findViewById(R.id.img_add_question);
        btnSave = findViewById(R.id.btn_save_set);

        questionList.add(new Question());
        listView = findViewById(R.id.lv_create_set);
        ListViewAdapter adapter = new ListViewAdapter(this, questionList);
        listView.setAdapter(adapter);

        btnSave.setOnClickListener(this::onClick);
        imgAdd.setOnClickListener(this::onClick);
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