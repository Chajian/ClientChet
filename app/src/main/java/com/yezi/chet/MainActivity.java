package com.yezi.chet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yezi.chet.community.Community;
import com.yezi.chet.community.netty.CommunityBoot;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.sql.MySQLliteOpen;
import com.yezi.chet.tools.PermissionUtil;
import com.yezi.chet.view.HomeActivity;
import com.yezi.chet.view.register.AccountAndPass;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.thread.ChetThreadPool;
import com.yezi.chet.tools.StringTool;

/**
 * 登陆界面
 * @author yezi
 * @version 1.0
 *
 */
public class MainActivity extends AppCompatActivity {

    EditText account,passworld;
    Button btn_login;
    TextView text_register;
    AlertDialog alertDialog;
    boolean is_community = false;
    CommunityBoot communityBoot;
    MySQLliteOpen mySQLliteOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    alertDialog.dismiss();
                    is_community = true;
                    PermissionUtil.checkPermissions(MainActivity.this);//检测权限
                    PermissionUtil.startRequestPermission(MainActivity.this);
                    break;

                case 1:
                    alertDialog.show();
                    break;

                case Permission.COMMUNITY_LOGIN:
                    Toast.makeText(getApplicationContext(),"登陆成功!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                    break;
            }
        }
    };

    public void init(){
            getSupportActionBar().hide();
            communityBoot = CommunityBoot.getBoot();
            communityBoot.setContext(this);
            communityBoot.createCommunity("192.168.1.153",8080);
            mySQLliteOpen = new MySQLliteOpen(this);


            text_register = findViewById(R.id.main_text_register);
            account = findViewById(R.id.main_edit_user);
            passworld = findViewById(R.id.main_edit_pass);
            btn_login = findViewById(R.id.main_btn_login);
            alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("正在连接服务器中!")
                    .setMessage("asdfsdf")
                    .setPositiveButton("", null)
                    .create();

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String string_account = account.getText().toString().trim();
                    String string_pass = passworld.getText().toString().trim();
                    if (StringTool.checkAccount(string_account) && StringTool.checkPassworld(string_pass)) {
                        ApplicationData.getData(Permission.COMMUNITY_LOGIN).getUser().setPassword(string_pass);
                        ApplicationData.getData(Permission.COMMUNITY_LOGIN).getUser().setAccount(string_account);
                        communityBoot.getInitializerHandle().getMonitorHandle().getOperationSocket().setHandle(handler);
                        communityBoot.senderData(new SendInfo(string_account,ApplicationData.getData(Permission.COMMUNITY_LOGIN)));
                    } else {
                        Toast.makeText(MainActivity.this, "用户名或密码出错!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            text_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent register = new Intent(MainActivity.this, AccountAndPass.class);
                    startActivity(register);
                }
            });
            //建立初次连接
//            SocketMonitor.getSocketMonitor().operationSocket.setHandle(handler);
            CommunityBoot.getBoot().getInitializerHandle().getMonitorHandle().getOperationSocket().setHandle(handler);
            ApplicationData data = ApplicationData.getData(Permission.COMMUNITY_TRY);
            SendInfo sendInfo = new SendInfo("",data);
            communityBoot.senderData(sendInfo);
            handler.sendEmptyMessage(1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PermissionUtil.REQUEST_PERMISSION_CALL){
            for(int i = 0 ; i < grantResults.length ; i++){
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED)
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[i]));
                        showToast("权限未申请成功!");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showToast(String string){
        Toast.makeText(this,string,Toast.LENGTH_LONG).show();
    }
}
