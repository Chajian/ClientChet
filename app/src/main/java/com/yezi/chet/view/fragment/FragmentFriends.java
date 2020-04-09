package com.yezi.chet.view.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.yezi.chet.R;
import com.yezi.chet.community.Community;
import com.yezi.chet.community.netty.CommunityBoot;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.view.cus.FriendAdapter;

import java.util.Arrays;
import java.util.List;

public class FragmentFriends extends Fragment {

    RecyclerView viewPager;
    List<Friend> list;
    FriendAdapter friendAdapter;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Permission.GET_FRIENDS_INFO:
                    list = ApplicationData.getData().getUser().getFriends();
                    friendAdapter = new FriendAdapter(list);
                    viewPager.setAdapter(friendAdapter);
                    viewPager.invalidate();
                    Log.e("获取了信息哦","" );
                break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myfriends,container,false);
        init(view);
        return  view;
    }

    public void init(View view){
        SendInfo sendInfo = new SendInfo(ApplicationData.getData().getUser().getAccount(),ApplicationData.getData(Permission.GET_FRIENDS_INFO));
        CommunityBoot.getBoot().getInitializerHandle().getMonitorHandle().getOperationSocket().setHandle(handler);
        CommunityBoot.getBoot().senderData(sendInfo);//发送请求包
        viewPager = view.findViewById(R.id.fragment_friends_recyc);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getContext());
        viewPager.setLayoutManager(linearLayoutManager);

    }
}
