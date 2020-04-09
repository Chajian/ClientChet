package com.yezi.chet.view.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yezi.chet.R;

/**
 * 注册活动的基类
 */
public class Register extends AppCompatActivity {

    protected static byte[] head_picture = null; //头像
    protected static String[] info = null;//用户基本信息
    protected static String account = null;
    protected static String passworld = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
