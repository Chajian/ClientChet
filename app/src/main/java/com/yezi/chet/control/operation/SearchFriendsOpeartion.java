package com.yezi.chet.control.operation;

import android.os.Message;

import com.yezi.chet.data.SendInfo;

public class SearchFriendsOpeartion extends BaseOperation {

    @Override
    public void opeartion(SendInfo data) {
        Message message = new Message();
        message.obj=data.getData2();
        message.what = 0;
        if(handler!=null)
        handler.sendMessage(message);
        super.opeartion(data);
    }
}
