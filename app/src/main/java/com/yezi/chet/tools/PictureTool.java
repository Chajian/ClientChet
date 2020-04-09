package com.yezi.chet.tools;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 图片处理工具
 */
public class PictureTool {

    public static final int REQUEST_CODE_TAKE_PICTURE = 200;
    public static Uri uri = null;

    //调用相机拍照
    public static void OpenCamera(Activity activity)throws IOException {
        File outImage = new File(activity.getExternalCacheDir(),"output_image.jpg");
        if(outImage.exists()){
            outImage.delete();
        }
        outImage.createNewFile();
        if(Build.VERSION.SDK_INT >= 24){
            uri = FileProvider.getUriForFile(activity,"com.yezi.chet.tools.camera.fileprovider",outImage);
        }
        else{
            uri = Uri.fromFile(outImage);
        }
        Intent openCameraIntent =new Intent("android.media.action.IMAGE_CAPTURE");
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        activity.startActivityForResult(openCameraIntent,REQUEST_CODE_TAKE_PICTURE);
    }

    //将Bitmap转换成byte[]
    public static byte[] BitmapBecameByte(Bitmap bitmap){
        // 实例化字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);// 压缩位图
        return baos.toByteArray();// 创建分配字节数组
    }

    //将byte[]转换成Bitmap
    public static Bitmap ByteBecameBitmap(byte[] datas){
        return BitmapFactory.decodeByteArray(datas, 0, datas.length);// 从字节数组解码位图
    }

    public static Bitmap drawableToBitamp(Drawable drawable)
    {
//
//        int w = drawable.getIntrinsicWidth();
//        int h = drawable.getIntrinsicHeight();
//        Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
//        return bitmap;
        // 取 drawable 的长宽PixelFormat
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }



}
