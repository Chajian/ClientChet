package com.yezi.chet.control.operation;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;

public class RegisterOperation extends BaseOperation {

    @Override
    public void opeartion(SendInfo data) {
        handler.sendEmptyMessage(0);
        super.opeartion(data);
    }
}
