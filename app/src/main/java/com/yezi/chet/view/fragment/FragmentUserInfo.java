package com.yezi.chet.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yezi.chet.R;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.tools.PictureTool;

public class FragmentUserInfo extends Fragment implements View.OnClickListener {

    ImageView head_picture;
    TextView register_time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo,container,false);
        init(view);
        return view;
    }

    public void init(View view){
        ApplicationData data = ApplicationData.getData();
        head_picture = view.findViewById(R.id.fragment_userinfo_headpicture);
        register_time = view.findViewById(R.id.fragment_userinfo_registertime);
        if(data.getUser().getRegistertime()!=null)
            register_time.setText(data.getUser().getRegistertime());
        if(data.getUser().getPhoto()!=null) {
            Log.e("获取了图片","sdf"+data.getUser().getPhoto().length);
            head_picture.setImageBitmap(PictureTool.ByteBecameBitmap(data.getUser().getPhoto()));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_userinfo_headpicture:

                break;
        }
    }
}
