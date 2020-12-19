package com.example.loginactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int Fail = 10;
    private Button btn_login;
    private TextView tvUserWarning, tvPswWarning;
    private EditText etUser, etPsw;
    private SharedPreferences sp;
    private Button btn_register;
    private Object key, value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    public void initView() {
        btn_login = findViewById(R.id.btn_login);
        tvPswWarning = findViewById(R.id.tv_psw_hint);
        tvUserWarning = findViewById(R.id.tv_user_hint);
        etUser = findViewById(R.id.et_user);
        etPsw = findViewById(R.id.et_psw);
        btn_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
                Map<String, ?> userLogin = sp.getAll();
                /*Iterator it = userLogin.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry =(Map.Entry) it.next();
                    key = entry.getKey();
                    value = entry.getValue();
                    Log.d(TAG, "key="+key+"  value"+value);
                }*/
                if (userLogin.containsKey(etUser.getText().toString().trim()) && !(etUser.getText().toString().trim().equals(""))) {
                    Object value = userLogin.get(etUser.getText().toString().trim());
                    if (value.toString().equals(etPsw.getText().toString())) {
                        Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                        intent.putExtra("data", etUser.getText().toString().trim());
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setMessage("Incorrect username or password!");
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
                    }
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setMessage("Incorrect username or password!");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).show();
                }

                /*if(key.toString().equals(etUser.getText().toString().trim()) && value.toString().equals(etPsw.getText().toString())){
                    Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                } else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setMessage("Incorrect username or password!");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).show();
                }*/
                if ("".equals(etUser.getText().toString().trim())) {
//                    tvUserWarning.setText("Account cannot be empty");
                    tvUserWarning.setVisibility(View.VISIBLE);
                } else {
                    tvUserWarning.setVisibility(View.GONE);
                }
                if (etPsw.getText().toString().isEmpty()) {
//                    tvPswWarning.setText("Password cannot be empty");
                    tvPswWarning.setVisibility(View.VISIBLE);
                } else {
                    tvPswWarning.setVisibility(View.GONE);
                }//非空验证

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}