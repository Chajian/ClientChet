package com.yezi.chet.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yezi.chet.R;
import com.yezi.chet.community.netty.CommunityBoot;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.view.cus.AddFriendsAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.SearchView;

import java.util.List;

public class AddFriendsActivity extends AppCompatActivity {

    AddFriendsAdapter addFriendsAdapter;
    SearchView searchView;
    RecyclerView recyclerView;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    List<Friend> list = (List<Friend>) msg.obj;
                    addFriendsAdapter = new AddFriendsAdapter(list);
                    recyclerView.setAdapter(addFriendsAdapter);
                    recyclerView.invalidate();
                    break;

                case 1:

                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        init();
    }

    public void init(){
        searchView = findViewById(R.id.add_friends_serach);
        recyclerView = findViewById(R.id.add_friends_showinfo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CommunityBoot.getBoot().senderData(new SendInfo(query, ApplicationData.getData(Permission.SEARCH_ADD_FRIENDS)),handler);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
