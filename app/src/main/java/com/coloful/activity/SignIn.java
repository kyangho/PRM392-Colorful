package com.coloful.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.coloful.R;
import com.coloful.dao.DBHelper;
import com.coloful.model.Account;

import java.util.UUID;

public class SignIn extends AppCompatActivity {
    ImageButton back;
    Button signin;
    EditText ed_username, ed_password, ed_email;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        back = (ImageButton) findViewById(R.id.bt_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent landing = new Intent(SignIn.this, Landing.class);
                startActivity(landing);
            }
        });

        ed_username = (EditText) findViewById(R.id.username);
        ed_password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.bt_signin);
        db = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ed_username.getText().toString();
                String password = ed_password.getText().toString();

                Account account = new Account(username, password);

                if(username.equals("") || password.equals("")){
                    Toast.makeText(SignIn.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean isLogin = db.checkAccount(account);
                    if(isLogin){
                        Toast.makeText(SignIn.this, "Welcome", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignIn.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
