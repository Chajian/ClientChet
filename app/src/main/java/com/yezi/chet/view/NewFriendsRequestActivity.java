package com.yezi.chet.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yezi.chet.R;
import com.yezi.chet.community.netty.CommunityBoot;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.sql.dao.NewFriendsRequest;
import com.yezi.chet.view.cus.NewFriendsRequestAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewFriendsRequestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewFriendsRequestAdapter requestAdapter;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends_request);
        init();
    }

    public void init(){
        CommunityBoot.getBoot().senderData(new SendInfo(ApplicationData.getData().getUser().getAccount(),ApplicationData.getData(Permission.GET_ALL_THING)));
        recyclerView = findViewById(R.id.new_friends_requests);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewFriendsRequest newFriendsRequest = NewFriendsRequest.getFriendsRequest();
        List <Friend> list = newFriendsRequest.getRequests(ApplicationData.getData().getUser().getAccount(), CommunityBoot.getBoot().getMySQLliteOpen().getReadableDatabase());

        requestAdapter = new NewFriendsRequestAdapter(list);
        recyclerView.setAdapter(requestAdapter);
        recyclerView.invalidate();
    }
}
