package com.yezi.chet.tools;

import java.util.*;

/**
 *  转换用户对象的字符串工具
 */
public class ConversionStringUser {


    /**
     *  将info字符串转换成User对象的info属性
     * @param info 数据库中的info字符串
     * @return 返回一个Hashtable对象
     */
    public static Hashtable<String,Object> ChangeUserInfo(String info){
        Hashtable<String, Object> infos = new Hashtable<>();
        String[] values = info.split(",");
        infos.put("name", values[0]);
        infos.put("age",values[1]);
        infos.put("summary",values[2]);
        infos.put("brithday",values[3]);
        infos.put("permission",values[4]);
        return infos;
    }


    public static String ChangeUserInfo(Hashtable<String,Object> info){
        String values = "";
        Iterator marks = info.keySet().iterator();
        while(marks.hasNext()){
            values += marks.next()+",";
        }
        return values;
    }


    /**
     *  将friend字符串转换成User对象的friends属性
     * @param friend 数据库中的friends字符串
     * @return 返回一个List对象
     */
    public static List<String> ChangeUserFriends(String friend){
        String[] values = friend.split(",");
        return Arrays.asList(values);
    }

    public static String ChangeUserFriends(List<String> friend){
        String values = "";
        for(String s : friend)
            values += s+",";
        return values;
    }

}
