package com.yezi.chet.data.user;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

/**
 *  用户对象
 * @author yezi
 *
 * ------------------用户信息【info列表】解释----------------------
 * current    description        ClassTypes
 * 0            User's name        String
 * 1            Usre's age          int
 * 2            User's summary      String
 * 3            User's brithday     String
 * 4            User's permission   int
 * 5            User's online       int    补充说明[0代表不在线，1代表在线]
 *
 *    提醒  添加一些好友列表等数据，等，并且制作一个数据包，可以发信息，，加好友等请求
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;//版本号
    public int id;
    public String info;//存储用户信息
    public String account;//账号
    public String password;//密码
    public String online;
    public byte[] photo;
    public String registertime;
    private List<Friend> friends;//好友列表

    public User(String info, String account, String password, String online, byte[] photo, String registertime) {
        this.info = info;
        this.account = account;
        this.password = password;
        this.online = online;
        this.photo = photo;
        this.registertime = registertime;
//        Log.i("sdf",this.photo);
    }

    public User() {
    }

    //添加用户数据到列表里


    public String getAccount() {
        return account;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public byte[] getPhoto() {
        return photo;
    }

//    public void setPhoto(byte[] photo) {
//        this.photo = photo;
//    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }


    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public String getPassword() {
        return password;
    }

    //获取玩家是否在线状态
    public boolean CheckOnline(){
        return Integer.parseInt(online) == 0 ? false:true;
    }

    @Override
    public String toString() {
        if(photo!=null)
        return "User{" +
                "id='" + id + '\'' +
                ", info=" + info +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", online='" + online + '\'' +
                ", photo='" + photo.length + '\'' +
                ", registertime='" + registertime + '\'' +
                '}';
        return "";
    }
}
