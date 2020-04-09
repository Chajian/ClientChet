package com.yezi.chet.control.operation;

import android.util.Log;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;

public class LoginOpeartion extends BaseOperation {
    @Override
    public void opeartion(SendInfo data) {
        if(data.getData().getUser()!=null) {
            ApplicationData.getData(Permission.COMMUNITY_LOGIN).setUser(data.getData().getUser());
            ApplicationData.getData(Permission.COMMUNITY_LOGIN).setToken(data.getData().getToken());
        }
        handler.sendEmptyMessage(Permission.COMMUNITY_LOGIN);
        super.opeartion(data);
    }
}
