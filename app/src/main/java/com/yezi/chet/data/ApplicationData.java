package com.yezi.chet.data;

import com.yezi.chet.data.user.User;

import java.io.Serializable;
import java.util.Arrays;

/**
 * socket请求数据包
 * @author yezi
 *
 *------------------用户信息【info列表】解释----------------------
 *  current    description        ClassTypes
 *   0             发送的消息        String
 *   1              接受者的账号      String
 */
public class ApplicationData implements Serializable {
    private static final long serialVersionUID = 1L;//版本号
    public static ApplicationData data;
    public String token;//验证用户信息
    public int type;
    public int type2;
    User user;//发送者账号信息
    String[] info;

    private ApplicationData(int type) {
        this.type = type;
//        user = new User(null,Tool.getTrandeName(),null);
        user = new User();
    }

    public ApplicationData(){
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }

    public static ApplicationData getData(int type) {
        if(data == null)
            data = new ApplicationData(type);
        data.setType(type);
        return data;
    }

    public static ApplicationData getData() {
        if(data==null)
            data = new ApplicationData();
        return data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ApplicationData{" +
                "token='" + token + '\'' +
                ", type=" + type +
                ", user=" + user +
                ", info=" + Arrays.toString(info) +
                '}';
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }
}
