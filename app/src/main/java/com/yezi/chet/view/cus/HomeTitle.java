package com.yezi.chet.view.cus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.yezi.chet.R;

public class HomeTitle  extends LinearLayout {

    ImageView back,opeartion;

    public HomeTitle(Context context) {
        super(context);
        init(context);
    }

    public HomeTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public HomeTitle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.home_title,this);
        back = findViewById(R.id.home_title_1);
        opeartion = findViewById(R.id.home_title_2);
    }

    public void setBackOnclick(OnClickListener onclick){
        back.setOnClickListener(onclick);
    }

    public void setOpeartionOnclick(OnClickListener onclick){
        opeartion.setOnClickListener(onclick);
    }

    public ImageView getBack() {
        return back;
    }

    public void setBack(ImageView back) {
        this.back = back;
    }

    public ImageView getOpeartion() {
        return opeartion;
    }

    public void setOpeartion(ImageView opeartion) {
        this.opeartion = opeartion;
    }
}
