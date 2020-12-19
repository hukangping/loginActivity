package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private EditText etUsername;
    private EditText etPsw;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    public void initView() {
        btnRegister = findViewById(R.id.btn_Register);
        etUsername = findViewById(R.id.et_username);
        etPsw = findViewById(R.id.et_psw);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etUsername.getText().toString().trim())) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                if (etPsw.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }//非空验证
                if ("".equals(etUsername.getText().toString().trim()) || etPsw.getText().toString().isEmpty()) {
                    Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
                if (!("".equals(etUsername.getText().toString().trim())) && !("".equals(etPsw.getText().toString().trim()))) {
                    //如果不为空获取SharedPreference对象生成xml文件
                    sp = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
                    Map<String, ?> userLogin = sp.getAll();
                    if (!(userLogin.containsKey(etUsername.getText().toString().trim()))) {
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putString(etUsername.getText().toString().trim(), etPsw.getText().toString());
                        edit.apply();//提交数据到xml文件中
                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "用户名已存在！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });

    }
}