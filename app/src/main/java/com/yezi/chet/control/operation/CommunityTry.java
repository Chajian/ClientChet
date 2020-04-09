package com.yezi.chet.control.operation;

import android.util.Log;

import com.yezi.chet.MainActivity;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.tools.PermissionUtil;
import com.yezi.chet.data.ApplicationData;

/**
 * socket初次连接的行为
 * @author yezi
 * @version 1.0
 */
public class CommunityTry extends BaseOperation {
    @Override
    public void opeartion(SendInfo data) {
        handler.sendEmptyMessage(0);
        super.opeartion(data);
    }

}
