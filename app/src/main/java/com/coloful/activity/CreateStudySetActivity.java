package com.coloful.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.coloful.R;
import com.coloful.adapters.ListViewCreateSetAdapter;
import com.coloful.dao.QuizDao;
import com.coloful.datalocal.DataLocalManager;
import com.coloful.model.Account;
import com.coloful.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateStudySetActivity extends AppCompatActivity {
    Button btnSave;
    List<Question> questionList = new ArrayList<>();
    EditText edt_create_quiz_title;
    Account account;
    AutoCompleteTextView textIn, textIn1;
    ImageButton buttonAdd;
    LinearLayout container;
    ArrayAdapter<String> adapter1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_set);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4257b0")));

        edt_create_quiz_title = findViewById(R.id.edt_create_quiz_title);
        btnSave = findViewById(R.id.btn_save_set);
        questionList.add(new Question());
        account = DataLocalManager.getAccount();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizDao db = new QuizDao();
                db.addQuiz(CreateStudySetActivity.this, account.getUsername(), edt_create_quiz_title.getText().toString().trim(), questionList);
            }
        });
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line);
        buttonAdd = (ImageButton) findViewById(R.id.add);
        container = (LinearLayout) findViewById(R.id.container);
        addItem();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    public void addItem() {
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.list_view_create_set_item, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 15, 0, 15);
        addView.setLayoutParams(params);
        AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.edt_term_1);
        AutoCompleteTextView textOut1 = (AutoCompleteTextView) addView.findViewById(R.id.edt_definition_1);
        textOut.setAdapter(adapter1);
        textOut1.setAdapter(adapter1);
        container.addView(addView);
        Button buttonRemove = (Button) addView.findViewById(R.id.remove);
        final View.OnClickListener thisListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout) addView.getParent()).removeView(addView);
            }
        };
        buttonRemove.setOnClickListener(thisListener);
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
}