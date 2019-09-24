package com.example.android_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private final static String SharedPreferencesFileName = "config";
    private final static String Name = "Name";
    private final static String LoginDate = "LoginDate";
    private final static String STUDENT_ID = "STUDENT_ID";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(SharedPreferencesFileName, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Button readBtn = (Button) findViewById(R.id.read);
        Button writeBtn = (Button) findViewById(R.id.write);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameStr = sharedPreferences.getString(Name, null);
                String loginDateStr = sharedPreferences.getString(LoginDate, null);
                String studentIdStr = sharedPreferences.getString(STUDENT_ID, null);
                if (NameStr != null && loginDateStr != null && studentIdStr != null) {
                    Toast.makeText(MainActivity.this, "姓名为：" + NameStr + "\n" + "学号为：" + studentIdStr + "\n" + "登录时间为：" + loginDateStr, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String LoginDateStr = simpleDateFormat.format(new Date());

                editor.putString(Name, "张三");
                editor.putString(STUDENT_ID, "2017010695");
                editor.putString(LoginDate, LoginDateStr);
                editor.apply();
                Toast.makeText(MainActivity.this, "数据写入成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
