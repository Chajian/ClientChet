package com.yezi.chet.control.operation;

import com.yezi.chet.data.SendInfo;

public class FailOpeartion extends BaseOperation {

    @Override
    public void opeartion(SendInfo data) {
        handler.sendEmptyMessage(1);
        super.opeartion(data);
    }
}
