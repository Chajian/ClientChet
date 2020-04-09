package com.yezi.chet.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理
 */
public class PermissionUtil {
    public static final String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};//存放想要申请的权限
    public static final List<String> apply_permissions = new ArrayList<>();//已经拥有的权限
    public static final int REQUEST_PERMISSION_CALL = 100;
    public PermissionUtil() {
    }

    //检测权限组的权限
    public static void checkPermissions(Context context){
        apply_permissions.clear();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for(int i = 0 ; i < permissions.length ; i++){
                //如果权限没有
                if(ContextCompat.checkSelfPermission(context,permissions[i]) != PackageManager.PERMISSION_GRANTED)
                    apply_permissions.add(permissions[i]);
            }
        }
    }

    public static boolean checkPermission(Context context, String permission){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED)
                return true;
            else
                return false;
        }
        return true;
    }

    //将需要申请的权限进行申请
    public static void startRequestPermission(Activity activity){
        if(apply_permissions != null && apply_permissions.size() >0)
            ActivityCompat.requestPermissions(activity, apply_permissions.toArray(new String[apply_permissions.size()]),REQUEST_PERMISSION_CALL);
    }

    public static void startRequestPermission(Activity activity, String permission){
        ActivityCompat.requestPermissions(activity,new String[]{permission},REQUEST_PERMISSION_CALL);
    }

}
