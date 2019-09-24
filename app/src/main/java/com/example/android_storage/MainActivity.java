package com.example.android_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private final static String SharedPreferencesFileName = "config";
    private final static String FILENAME = "custom_file";

    private final static String Name = "Name";
    private final static String LoginDate = "LoginDate";
    private final static String STUDENT_ID = "STUDENT_ID";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Button readBtn;
    private Button writeBtn;
    private Button fileReadBtn;
    private Button fileWriteBtn;

    private void init() {
        readBtn = (Button) findViewById(R.id.read);
        writeBtn = (Button) findViewById(R.id.write);
        fileReadBtn = (Button) findViewById(R.id.read_file);
        fileWriteBtn = (Button) findViewById(R.id.write_file);
    }

    private void useSharedPreferences() {
        sharedPreferences = getSharedPreferences(SharedPreferencesFileName, MODE_PRIVATE);
        editor = sharedPreferences.edit();
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

    private void useFileStorage() {
        fileReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fileWriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try {
                    FileOutputStream outputStream = openFileOutput(FILENAME, MODE_PRIVATE);
                    out = new BufferedOutputStream(outputStream);
                    String content = "Hello World!";
                    try {
                        out.write(content.getBytes(Charset.defaultCharset()));
                        out.flush();
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        fileReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try {
                    FileInputStream fileInputStream = openFileInput(FILENAME);
                    in = new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder builder = new StringBuilder("");
                    try {
                        while ((c = in.read()) != -1) {
                            builder.append((char) c);
                        }
                        Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
                    } finally {
                        if (in != null) {
                            in.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        useSharedPreferences();
        useFileStorage();
    }
}
