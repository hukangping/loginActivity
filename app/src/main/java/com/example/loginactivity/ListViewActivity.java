package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListViewActivity extends AppCompatActivity {


    private TextView tv_user;
    private ListView listView;
    private String[] data = new String[]{"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry",
            "Cherry", "Mango", "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        tv_user = findViewById(R.id.tv_welcome);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        if (tv_user != null) {//指针非空设置文本
            tv_user.setText("欢迎用户" + data);//设置文本 欢迎用户谁谁谁
        }
        initView();
    }

    public void initView() {
        listView = findViewById(R.id.lv_simplelistview);
        //listview需要和Adatper搭配一起使用
        //ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        //ListView和适配器要组合在一起才能发挥作用
        listView.setAdapter(adapter);
    }

}