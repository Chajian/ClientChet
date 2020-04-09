package com.yezi.chet.view.cus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.yezi.chet.R;

public class FriendsMessageBottom extends LinearLayout {


    EditText editText;
    Button imageView;

    public FriendsMessageBottom(Context context) {
        super(context);
        init(context);
    }

    public FriendsMessageBottom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FriendsMessageBottom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public FriendsMessageBottom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.recyc_bottom_message,this);
        editText = findViewById(R.id.recyc_bottom_message_edit);
        imageView = findViewById(R.id.recyc_bottom_message_btn);
    }

    public void setSendOnclick(OnClickListener onclick){
        imageView.setOnClickListener(onclick);
    }

    public String getSendInfo(){
        String message = editText.getText().toString().trim();
        editText.setText("");
        return message;
    }
}
