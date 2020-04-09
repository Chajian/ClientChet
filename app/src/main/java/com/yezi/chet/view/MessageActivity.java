package com.yezi.chet.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.yezi.chet.R;
import com.yezi.chet.community.netty.CommunityBoot;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Message;
import com.yezi.chet.sql.dao.MessagesDao;
import com.yezi.chet.view.cus.FriendsMessage;
import com.yezi.chet.view.cus.FriendsMessageAdapter;
import com.yezi.chet.view.cus.FriendsMessageBottom;

import java.util.Date;
import java.util.List;

import io.netty.channel.MessageSizeEstimator;

public class MessageActivity extends AppCompatActivity {

    RecyclerView recyclerViewl;
    FriendsMessageBottom bottom;
    String target_account;
    FriendsMessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();
    }

    public void init(){
        //update
        SendInfo sendInfo = new SendInfo(ApplicationData.getData().getUser().getAccount(),ApplicationData.getData(Permission.GET_ALL_THING));
        CommunityBoot.getBoot().senderData(sendInfo);

        //getMessage
        target_account = getIntent().getStringExtra("target_account");
        final List<Message> list = MessagesDao.getMessagesDao().selectMessage(target_account, ApplicationData.getData().getUser().getAccount(), CommunityBoot.getBoot().getMySQLliteOpen().getReadableDatabase());
        messageAdapter = new FriendsMessageAdapter(list);
        recyclerViewl = findViewById(R.id.message_recyc);
        recyclerViewl.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewl.setAdapter(messageAdapter);

        bottom = findViewById(R.id.message_bottom);


        //sendMessage
        bottom.setSendOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendInfo sendInfo = new SendInfo(target_account,ApplicationData.getData(Permission.SEND_MESSAGE_FRIENDS));
                Message message = new Message();
                message.setMessage(bottom.getSendInfo());
                message.setSender_account(ApplicationData.getData(Permission.SEND_MESSAGE_FRIENDS).getUser().getAccount());
                message.setReciver_account(target_account);
                message.setTime(new Date().getTime());
                sendInfo.setData2(message);

                list.add(message);
                recyclerViewl.invalidate();
                MessagesDao messagesDao = new MessagesDao();
                if(!messagesDao.isRequestBeing(message,CommunityBoot.getBoot().getMySQLliteOpen().getReadableDatabase()))
                    messagesDao.insertMessage(message,CommunityBoot.getBoot().getMySQLliteOpen().getWritableDatabase());

                //发送信息
                CommunityBoot.getBoot().senderData(sendInfo);
            }
        });
    }



}
