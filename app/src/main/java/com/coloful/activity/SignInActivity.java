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
import com.coloful.dao.AccountDao;
import com.coloful.dao.DBHelper;
import com.coloful.datalocal.DataLocalManager;
import com.coloful.model.Account;

public class SignInActivity extends AppCompatActivity {
    ImageButton back;
    Button signin;
    EditText ed_username, ed_password, ed_email;
    DBHelper db;
    AccountDao accountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        accountDao = new AccountDao();
        back = (ImageButton) findViewById(R.id.bt_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent landing = new Intent(SignInActivity.this, LandingActivity.class);
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

                if (username.equals("") || password.equals("")) {
                    Toast.makeText(SignInActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    Account isLogin = accountDao.checkAccount(SignInActivity.this, account);
                    if (isLogin != null) {
                        DataLocalManager.setAccount(account);
                        Intent main = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(main);
                    } else {
                        Toast.makeText(SignInActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
