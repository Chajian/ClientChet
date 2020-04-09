package com.yezi.chet.community;

/**
 * 进行建立socket连接的接口
 * @author yezi
 * @version 1.0
 */
public interface Community {
    /**
     * 通过传递的俩个变量，创建socket对象
     * @param ip 服务器ip地址
     * @param port 服务器端口
     */
    void createCommunity(String ip,int port);

}
