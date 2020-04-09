package com.yezi.chet.data.constant;

public class Permission {

    //内部人员
    public static final int ADMIN = 0;//管理员
    //贵族用户
    public static final int SVIP = 10;
    public static final int VIP = 11;


    //20-50普通的用户
    public static final int ORDINARY = 20;//普通用户


    //请求包状态1000-1200

    public static final int NULL = -1;

    public static final int COMMUNITY_LOGIN = 1000;//登陆请求

    public static final int GET_USER_INFO = 1001;//获取用户数据

    public static final int SEND_MESSAGE_FRIENDS = 1005;//给好友发送信息

    public static final int SEND_MESSAGE_PUBLIC = 1006;//给群聊发送信息

    public static final int COMMUNITY_REGISTER = 1020;//注册请求

    public static final int SEARCH_ADD_FRIENDS = 1021;//查询好友信息

    public static final int GET_FRIENDS_INFO = 1022;//获取好友信息

    public static final int GET_ALL_THING = 1023;//获取所有信息

    public static final int SUCCEFF = 1024;//请求成功

    public static final int PRIVATE_CHET = 1050;//私聊请求

    public static final int ADD_FRIEND = 1060;//添加好友请求

    public static final int ADD_FRIEND_AGREE = 1061;//同意好友的添加

    public static final int ADD_FRIEND_DISAGREE = 1062;//不同意好友的添加

    public static final int FAIL = 1080;//请求失败

    public static final int SEND_CIRCLE = 1091;//发送朋友圈

    public static final int PUBLIC_CHET = 1092;//群聊请求

    public static final int ADD_PUBLIC_CHET = 1090;//加入群聊

    public static final int COMMUNITY_TRY = 1100;//尝试建立连接

}
