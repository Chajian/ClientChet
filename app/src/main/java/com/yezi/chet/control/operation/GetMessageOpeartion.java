package com.yezi.chet.control.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.user.Message;
import com.yezi.chet.sql.MySQLliteOpen;
import com.yezi.chet.sql.dao.MessagesDao;

public class GetMessageOpeartion extends BaseOperation {
    MySQLliteOpen mySQLliteOpen;

    public GetMessageOpeartion(MySQLliteOpen mySQLliteOpen) {

        this.mySQLliteOpen = mySQLliteOpen;
    }

    @Override
    public void opeartion(SendInfo data) {
        Message message = new Message();
        message.setSender_account(data.getSender_account());
        message.setReciver_account(data.getRevier_account());
        message.setMessage((String) data.getData2());
        MessagesDao messagesDao = new MessagesDao();
        messagesDao.insertMessage(message,mySQLliteOpen.getWritableDatabase());
        super.opeartion(data);
    }
}
