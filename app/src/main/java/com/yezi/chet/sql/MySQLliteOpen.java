package com.yezi.chet.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class  MySQLliteOpen extends SQLiteOpenHelper {

    private String NewFriends = "CREATE TABLE NewFriends(" +
            "id integer primary key autoincrement," +
            "account text," +
            "head blob(5120)," +
            "hostaccount text" +
            ")";

    private String Messages = "CREATE TABLE Messages(" +
            "id integer primary key autoincrement," +
            "sender_id text," +
            "message text," +
            "reciver_id text," +
            "is_ready integer," +
            "time integer"+
            ")";

    public MySQLliteOpen(@Nullable Context context){
        super(context,"chet.db",null,1);
    }


    public MySQLliteOpen(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySQLliteOpen(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public MySQLliteOpen(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewFriends);
        db.execSQL(Messages);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
