package com.yezi.chet.data.user;

import com.yezi.chet.data.ApplicationData;

import java.io.Serializable;

/**
 * 聊天记录对象
 */
public class Message implements Serializable {

    public String sender_account;
    public String reciver_account;
    public String message;
    public boolean isready;
    public long time;


    public String getSender_account() {
        return sender_account;
    }

    public void setSender_account(String sender_account) {
        this.sender_account = sender_account;
    }

    public String getReciver_account() {
        return reciver_account;
    }

    public void setReciver_account(String reciver_account) {
        this.reciver_account = reciver_account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsready() {
        return isready;
    }

    public void setIsready(boolean isready) {
        this.isready = isready;
    }

    public int rightORleft(){
        if(ApplicationData.getData().getUser().getAccount()==sender_account)
            return 0;
        else
            return 1;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
