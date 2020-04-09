package com.yezi.chet.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteObjConverter {
    /**
     * 使用IO的inputstream流将byte[]转换为object
     *
     * @param bytes
     * @return
     */
    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * 使用IO的outputstream流将object转换为byte[]
     *
     * @param obj
     * @return
     */
    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public static byte[] byteMerger(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length+bt2.length];
        int i=0;
        for(byte bt: bt1){
            bt3[i]=bt;
            i++;
        }

        for(byte bt: bt2){
            bt3[i]=bt;
            i++;
        }
        return bt3;
    }

    //将账号信息转换为byte数组
    public static byte[] AccountToBytes(String account){
        byte[] value = account.getBytes();
        byte[] nummber = new byte[2];
        nummber[0] = (byte) value.length;
        nummber[1] = -1;
        System.out.println(new String(value));
        value = byteMerger(value,nummber);
        System.out.println(Arrays.toString(value));
        return value;
    }

    public static byte[] subBytes(int start,int end,byte[] bytes){
        byte[] value = new byte[end-start];
        int j = 0;
        for(int i = start; i < end ; i++,j++){
            value[j] = bytes[i];
        }
        return value;
    }
}
