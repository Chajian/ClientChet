package com.yezi.chet.control;

import com.yezi.chet.data.ApplicationData;

/**
 * socket通讯发送者接口
 *
 * @author yezi
 * @version 1.0
 */
public interface Sender {
    /**
     * 将传递的数据进行socket传输
     * @param info 要传递的数据
     */
    void put(ApplicationData info);
}
