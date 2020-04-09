package com.yezi.chet.sql.dao;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.user.Friend;

import java.util.ArrayList;
import java.util.List;

//处理好友请求
public class NewFriendsRequest {

    public static final String TABLE = "NewFriends";
    public static NewFriendsRequest friendsRequest;

    private NewFriendsRequest() {
    }

    public synchronized void addRequest(Friend friend, SQLiteDatabase db){
        db.execSQL("INSERT INTO "+TABLE+" (account,head,hostaccount) values (?,?,?)",new Object[]{friend.getName(),friend.getHead_picture(), ApplicationData.getData().getUser().getAccount()});
        db.close();
    }

    public synchronized List<Friend> getRequests(String account, SQLiteDatabase db){
        List<Friend> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE+" WHERE hostaccount = ?",new String[]{account});
        if(cursor.moveToFirst()){
            do{
                Friend friend = new Friend(cursor.getString(cursor.getColumnIndex("account")),"",cursor.getBlob(cursor.getColumnIndex("head")));
                list.add(friend);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public synchronized boolean isRequestBeing(String account,SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE+" WHERE hostaccount = ?",new String[]{account});
        cursor.close();
        db.close();
        return cursor.moveToFirst();
    }

    public synchronized void deleteRequest(String account,SQLiteDatabase db){
        db.execSQL("DELETE FROM "+TABLE+" WHERE account = "+account);
        db.close();
    }

    public static NewFriendsRequest getFriendsRequest() {
        if(friendsRequest == null)
            friendsRequest = new NewFriendsRequest();
        return friendsRequest;
    }
}
