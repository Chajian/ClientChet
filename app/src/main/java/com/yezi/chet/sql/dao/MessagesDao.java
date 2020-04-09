package com.yezi.chet.sql.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.inspector.database.SqliteDatabaseDriver;
import com.yezi.chet.data.user.Message;
import com.yezi.chet.sql.MySQLliteOpen;

import java.util.ArrayList;
import java.util.List;

public class MessagesDao {

    public static final String TABLE = "Messages";
    public static MessagesDao messagesDao;


    public void insertMessage(Message message, SQLiteDatabase sqlite){
        sqlite.execSQL("INSERT INTO "+TABLE+" (sender_id,message,reciver_id) VALUES (?,?,?)",
                new String[]{message.getSender_account(),message.getMessage(),message.getReciver_account()});
        sqlite.close();
    }

    public List<Message> selectMessage(String sender_account,String revicer_account,SQLiteDatabase sqlite){
        List<Message> list = new ArrayList<>();
        Cursor cursor = sqlite.rawQuery("SELECT * FROM "+TABLE+" WHERE sender_id = ? and reciver_id = ?",
                new String[]{sender_account,revicer_account});
        if(cursor.moveToFirst())
            do{
                Message message = new Message();
                message.setSender_account(sender_account);
                message.setReciver_account(revicer_account);
                message.setMessage(cursor.getString(cursor.getColumnIndex("message")));
                list.add(message);
            }
            while(cursor.moveToNext());

        cursor.close();
        sqlite.close();
        return list;
    }

    //判断某个聊天记录是否已经存在
    public boolean isRequestBeing(Message message,SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE+" WHERE sender_id = ? and reciver_id = ? and time = ?"
                ,new String[]{message.getSender_account(),message.getReciver_account(),String.valueOf(message.getTime())});
        return cursor.moveToFirst();
    }


    public static MessagesDao getMessagesDao() {
        if(messagesDao == null)
            messagesDao = new MessagesDao();
        return messagesDao;
    }
}
