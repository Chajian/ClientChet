package com.yezi.chet.control.operation;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;
import com.yezi.chet.data.user.Message;
import com.yezi.chet.sql.MySQLliteOpen;
import com.yezi.chet.sql.dao.MessagesDao;
import com.yezi.chet.sql.dao.NewFriendsRequest;

import java.util.List;

/**
 * 添加好友
 */
public class GetAllThingOpeartion extends BaseOperation {

    MySQLliteOpen mySQLliteOpen;
    private NewFriendsRequest newFriendsRequest;
    private MessagesDao messagesDao;


    public GetAllThingOpeartion(MySQLliteOpen mySQLliteOpen) {
        this.mySQLliteOpen = mySQLliteOpen;
        newFriendsRequest = NewFriendsRequest.getFriendsRequest();
        messagesDao = MessagesDao.getMessagesDao();
    }

    @Override
    public void opeartion(SendInfo data) {
        //将请求数据写入到数据库中
//        newFriendsRequest.addRequest(new Friend(data.getSender_account(),"",data.getData().getUser().getPhoto()),mySQLliteOpen.getWritableDatabase());
        switch (data.getData().getType2()) {
            case Permission.GET_FRIENDS_INFO:
                List<Friend> list = (List<Friend>) data.getData2();
                for (Friend friend : list)
                    if (newFriendsRequest.isRequestBeing(ApplicationData.getData().getUser().getAccount(), mySQLliteOpen.getReadableDatabase()))
                        newFriendsRequest.addRequest(friend, mySQLliteOpen.getWritableDatabase());

            break;

            case Permission.SEND_MESSAGE_FRIENDS:
                List<Message> list1 = (List<Message>) data.getData2();
                for(Message message: list1)
                    if(!messagesDao.isRequestBeing(message,mySQLliteOpen.getReadableDatabase()))
                        messagesDao.insertMessage(message,mySQLliteOpen.getWritableDatabase());
            break;
        }
        if (handler != null)
            handler.sendEmptyMessage(0);
        super.opeartion(data);
    }
}
