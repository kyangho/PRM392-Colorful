package com.coloful.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.coloful.R;
import com.coloful.adapters.FragmentDialogHelper;
import com.coloful.dao.DBHelper;
import com.coloful.datalocal.DataLocalManager;
import com.coloful.model.Account;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ImgBtnBack;
    private TextView tvFgUsername;
    private TextView tvFgPassword;
    private Button btnLogin;
    private EditText edtUsername;
    private EditText edtPassword;
    private TextView tvMsg;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();
        ImgBtnBack = findViewById(R.id.ImgBtn_back);
        tvFgUsername = findViewById(R.id.tv_fg_username);
        tvFgPassword = findViewById(R.id.tv_fg_password);
        btnLogin = findViewById(R.id.btn_login);
        edtUsername = findViewById(R.id.edt_username_login);
        edtPassword = findViewById(R.id.edt_password_login);
        tvMsg = findViewById(R.id.tvMsg);

        tvFgPassword.setOnClickListener(this::onClick);
        tvFgUsername.setOnClickListener(this::onClick);
        ImgBtnBack.setOnClickListener(this::onClick);
        btnLogin.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        FragmentManager fm;
        FragmentDialogHelper popup;
        switch (view.getId()) {
            case R.id.btn_login:
                checkLogin();
                break;
            case R.id.ImgBtn_back:
                intent = new Intent(this, LandingActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_fg_username:
                fm = getSupportFragmentManager();
                popup = FragmentDialogHelper.newInstance("username");
                popup.show(fm, null);
                break;
            case R.id.tv_fg_password:
                fm = getSupportFragmentManager();
                popup = FragmentDialogHelper.newInstance("password");
                popup.show(fm, null);
                break;
        }
    }

    private void checkLogin() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        Account account = new Account(username, password);

        if (username.equals("") || password.equals("")) {
//            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            tvMsg.setText("Please enter username and password!");
        } else {
            Boolean isLogin = db.checkAccount(account);
            if (isLogin == null || !isLogin) {
                tvMsg.setText("Username or password is invalid!");
            } else {
                DataLocalManager.setAccount(account);
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
//                Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}