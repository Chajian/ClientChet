package com.yezi.chet.control.operation;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.data.user.Friend;

import java.util.List;

public class GetFriendsInfoOpeartion extends BaseOperation{

    @Override
    public void opeartion(SendInfo data) {
        List<Friend> list = data.getData().getUser().getFriends();
        if(list != null && list.size() >0) {
            ApplicationData.getData().getUser().setFriends(list);
            handler.sendEmptyMessage(Permission.GET_FRIENDS_INFO);
        }
        super.opeartion(data);
    }
}
