package com.yezi.chet.view.register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yezi.chet.R;
import com.yezi.chet.community.netty.CommunityBoot;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.tools.ByteObjConverter;
import com.yezi.chet.tools.PictureTool;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * 设置头像步骤
 */
public class HeadPicture extends Register {

    EditText edit_name;
    EditText edit_age;
    ImageView image_head;
    Button btn;
    CommunityBoot communityBoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_picture);
        init();
    }

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(HeadPicture.this,"注册成功!",Toast.LENGTH_LONG).show();
                    finish();
                    break;

                case 1:
                    Toast.makeText(HeadPicture.this,"注册失败",Toast.LENGTH_SHORT).show();
                    break;
            }
            finish();
        }
    };

    public void init(){
        edit_name = findViewById(R.id.register_edit_name);
        edit_age = findViewById(R.id.register_edit_age);
        image_head = findViewById(R.id.register_image_head);
        btn = findViewById(R.id.register_head_sure);

        communityBoot = CommunityBoot.getBoot();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(head_picture == null)
                    head_picture = PictureTool.BitmapBecameByte(BitmapFactory.decodeResource(getResources(),R.drawable.head));
                communityBoot.getInitializerHandle().getMonitorHandle().getOperationSocket().setHandle(handler);
                User user = new User(edit_age.getText().toString().trim(),account,passworld,"0",head_picture,"");
                ApplicationData data = ApplicationData.getData(Permission.COMMUNITY_REGISTER);
                data.setUser(user);
                data.setType(Permission.COMMUNITY_REGISTER);

                SendInfo sendInfo = new SendInfo(null,data);
                sendInfo.setSender_account(account);
                communityBoot.senderData(sendInfo);
            }
        });
        image_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PictureTool.OpenCamera(HeadPicture.this);
                }
                catch (IOException e){
                    Log.e("打开相机出问题",e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case PictureTool
                    .REQUEST_CODE_TAKE_PICTURE:
                if(resultCode == RESULT_OK){
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(PictureTool.uri));
                        image_head.setImageBitmap(bm);
                        head_picture = PictureTool.BitmapBecameByte(bm);
                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}
