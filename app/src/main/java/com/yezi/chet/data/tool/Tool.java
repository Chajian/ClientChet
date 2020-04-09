package com.yezi.chet.data.tool;

import java.util.Random;

public class Tool {


    /**
     * 随机获得一个游客名
     * @return 返回一个游客名
     */
    public static String getTrandeName(){
        Random random = new Random();
        long code = System.currentTimeMillis()/random.nextInt(5892);
        return "游客"+code;
    }

    //拆分byte【】
    public byte[] subBytes(int start,int end,byte[] bytes){
        byte[] value = new byte[end-start];
        int j = 0;
        for(int i = start; i <=end ; i++,j++){
            value[j] = bytes[i];
        }
        return value;
    }
}
