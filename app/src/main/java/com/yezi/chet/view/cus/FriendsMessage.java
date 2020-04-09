package com.yezi.chet.view.cus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yezi.chet.R;

public class FriendsMessage extends LinearLayout {

    LinearLayout left,right;
    ImageView img_left,img_right;
    TextView message_left,message_right;
    int status = 0;//0显示右边，1显示左边




    public FriendsMessage(Context context) {
        super(context);
        init(context);
    }

    public FriendsMessage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FriendsMessage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public FriendsMessage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.recyc_friend_message,this);

        left = findViewById(R.id.friends_message_left_layout);
        right = findViewById(R.id.friends_message_right_layout);
        img_left = findViewById(R.id.friends_message_left_img);
        img_right = findViewById(R.id.friends_message_right_img);
        message_left = findViewById(R.id.friends_message_left_text);
        message_right = findViewById(R.id.friends_message_right_text);
    }


}
