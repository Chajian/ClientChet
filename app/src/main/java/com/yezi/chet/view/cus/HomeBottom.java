package com.yezi.chet.view.cus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.yezi.chet.R;

public class HomeBottom extends LinearLayout {

    View view;
    ImageView imageView1,imageView2,imageView3,imageView4;

    public HomeBottom(Context context) {
        super(context);
        init(context);
    }

    public HomeBottom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeBottom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public HomeBottom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        view =  LayoutInflater.from(context).inflate(R.layout.home_bottom,this);
        imageView1 = findViewById(R.id.main_bottom_1);
        imageView2 = findViewById(R.id.main_bottom_2);
        imageView3 = findViewById(R.id.main_bottom_3);
        imageView4 = findViewById(R.id.main_bottom_4);


    }

    public void setOnClick(OnClickListener onClick){
        imageView1.setOnClickListener(onClick);
        imageView2.setOnClickListener(onClick);
        imageView3.setOnClickListener(onClick);
        imageView4.setOnClickListener(onClick);
    }

    public void hideAll(){
        imageView1.setImageDrawable(getResources().getDrawable(R.drawable.message_off,getContext().getTheme()));
        imageView2.setImageDrawable(getResources().getDrawable(R.drawable.lof_of_people_off,getContext().getTheme()));
        imageView3.setImageDrawable(getResources().getDrawable(R.drawable.myfriends_info_off,getContext().getTheme()));
        imageView4.setImageDrawable(getResources().getDrawable(R.drawable.user_info_off,getContext().getTheme()));
    }


    public void ClickIndex(int i){
        hideAll();
        switch (i){
            case 1:
                imageView1.setImageDrawable(getResources().getDrawable(R.drawable.message,getContext().getTheme()));
                break;

            case 2:
                imageView2.setImageDrawable(getResources().getDrawable(R.drawable.lof_of_people,getContext().getTheme()));
                break;

            case 3:
                imageView3.setImageDrawable(getResources().getDrawable(R.drawable.myfriends_info,getContext().getTheme()));
                break;

            case 4:
                imageView4.setImageDrawable(getResources().getDrawable(R.drawable.user_info,getContext().getTheme()));
                break;
        }
    }
}
