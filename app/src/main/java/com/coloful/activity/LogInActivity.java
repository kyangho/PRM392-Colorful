package com.coloful.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.coloful.R;
import com.coloful.adapters.FragmentDialogHelper;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private TextView tvBack;
    private TextView tvFgUsername;
    private TextView tvFgPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();
        tvBack = findViewById(R.id.tv_back);
        tvFgUsername = findViewById(R.id.tv_fg_username);
        tvFgPassword = findViewById(R.id.tv_fg_password);
        btnLogin = findViewById(R.id.btn_login);

        tvFgPassword.setOnClickListener(this::onClick);
        tvFgUsername.setOnClickListener(this::onClick);
        tvBack.setOnClickListener(this::onClick);
        btnLogin.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        FragmentManager fm;
        FragmentDialogHelper popup;
        switch (view.getId()) {
            case R.id.btn_login:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_back:
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
}