package com.yezi.chet.data.user;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Arrays;

//存放好友列表的头像，姓名，信息
public class Friend implements Serializable {
    private static final long serialVersionUID = 2L;//版本号
    public String name;
    public String info;
    public byte[] head_picture;

    public Friend(String name, String info, byte[] head_picture) {
        this.name = name;
        this.info = info;
        this.head_picture = head_picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(byte[] head_picture) {

        this.head_picture = head_picture;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", head_picture=" + Arrays.toString(head_picture) +
                '}';
    }
}
