package com.yezi.chet.control;

import android.os.Handler;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.SendInfo;


/**
 * socket的行为接口。在进行socket建立之后，为了实现各种行为
 * @author yezi
 * @version 1.0
 */
public interface OperationSocket {

    /**
     * @deprecated 行为方法
     * @param data 通过socket传送来的数据
     */
    void opeartion(SendInfo data);

    void setHandle(Handler handle);

}
