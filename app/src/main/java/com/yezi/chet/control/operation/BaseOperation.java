package com.yezi.chet.control.operation;

import android.os.Handler;
import android.util.Log;

import com.yezi.chet.control.OperationSocket;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;


public class BaseOperation implements OperationSocket {

    public static Handler handler;

    @Override
    public void opeartion(SendInfo data) {
        handler = null;
    }

    @Override
    public void setHandle(Handler handle) {
        this.handler = handle;
    }
}
