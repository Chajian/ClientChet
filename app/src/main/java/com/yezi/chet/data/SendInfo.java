package com.yezi.chet.data;

import java.io.Serializable;
/**
 *  用于存储发送信息的对象
 *  实现功能：离线发送信息等待
 *
 *
 *
 */
public class SendInfo implements Serializable {
    private static final long serialVersionUID = 1L;//版本号
    private String sender_account = null;
    private String reciver_account = null;
    private ApplicationData data;//发送的数据；
    private Object data2;

    public SendInfo(String reciver_account, ApplicationData data) {
        this.reciver_account = reciver_account;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSender_account() {
        return sender_account;
    }

    public void setSender_account(String sender_account) {
        this.sender_account = sender_account;
    }

    public String getRevier_account() {
        return reciver_account;
    }

    public void setRevier_account(String reciver_account) {
        this.reciver_account = reciver_account;
    }

    public ApplicationData getData() {
        return data;
    }

    public void setData(ApplicationData data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "SendInfo{" +
                "sender_account='" + sender_account + '\'' +
                ", reciver_account='" + reciver_account + '\'' +
                ", data=" + data +
                '}';
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }
}
