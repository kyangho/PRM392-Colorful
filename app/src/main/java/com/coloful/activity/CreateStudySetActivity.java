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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

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
    EditText edt_create_quiz_title,txt_question,txt_answer;
    Account account;
    ImageButton buttonAdd;
    LinearLayout list;
    ArrayAdapter<String> adapter1;
    ArrayList<String> text = new ArrayList<>();
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_set);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4257b0")));
        context = this;
        edt_create_quiz_title = findViewById(R.id.edt_create_quiz_title);
        txt_question = findViewById(R.id.txt_question);
        txt_answer = findViewById(R.id.txt_answer);
        list = findViewById(R.id.list);
        btnSave = findViewById(R.id.btn_save_set);
        questionList.add(new Question());
        buttonAdd = (ImageButton) findViewById(R.id.add);
        account = DataLocalManager.getAccount();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizDao db = new QuizDao();
                Long qId = db.addQuiz(CreateStudySetActivity.this, account.getUsername(), edt_create_quiz_title.getText().toString().trim());
                for (int i = 0; i < list.getChildCount(); i++) {
                    if (list.getChildAt(i) instanceof LinearLayoutCompat) {
                        LinearLayoutCompat ll = (LinearLayoutCompat) list.getChildAt(i);
                        for (int j = 0; j < ll.getChildCount(); j++) {
                            if (ll.getChildAt(j) instanceof EditText){
                                EditText et = (EditText) ll.getChildAt(j);
                                if(et.getId() == R.id.txt_question){
                                    Toast.makeText(context, "" + et.getText().toString(), Toast.LENGTH_SHORT).show();
                                    db.addQuestion(CreateStudySetActivity.this, et.getText().toString().trim(), qId, txt_answer.getText().toString().trim());
                                }
                            }
                        }
                    }
                }
            }
        });
        //tét
        adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line);
//        addItem();
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
        TextView textOut = (TextView) addView.findViewById(R.id.txt_question);
        TextView textOut1 = (TextView) addView.findViewById(R.id.txt_answer);
//        textOut.setAdapter(adapter1);
//        textOut1.setAdapter(adapter1);
        list.addView(addView);
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