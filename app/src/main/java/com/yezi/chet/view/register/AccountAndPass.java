package com.yezi.chet.view.register;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yezi.chet.R;
import com.yezi.chet.tools.StringTool;

/**
 * 设置账号和密码步骤
 */
public class AccountAndPass extends Register {

    EditText edit_account;
    EditText edit_pass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_and_pass);
        init();
    }

    public void init(){
        getSupportActionBar().hide();
        edit_account = findViewById(R.id.register_edit_account);
        edit_pass = findViewById(R.id.register_edit_pass);
        btn = findViewById(R.id.register_btn_sure);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string_account = edit_account.getText().toString().trim();
                String string_pass = edit_pass.getText().toString().trim();
                if(StringTool.checkAccount(string_account) && StringTool.checkPassworld(string_pass)){//检测账号和密码
                    account = string_account;
                    passworld = string_pass;
                    Intent intent = new Intent(AccountAndPass.this,HeadPicture.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(AccountAndPass.this,"账号或密码出错!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
