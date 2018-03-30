package com.avicsafety.lib.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by shili on 2017/4/13.
 * 图片处理 tools
 */

public class BitMapUtil {
    /**
     * 修改图片大小
     * @param bm
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap ModifyPhotoSize(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        int newWidth1;
        int newHeight1;
        if (width > height) {
            newWidth1 = newWidth;
            newHeight1 = newHeight;
        } else {
            newWidth1 = newHeight;
            newHeight1 = newWidth;
        }
        float scaleWidth = ((float) newWidth1) / width;
        float scaleHeight = ((float) newHeight1) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    /**
     * 通过字节数组 获取一个图片
     * @param bytes
     * @param opts
     * @return
     */
    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }


    /**
     * 将图片转化为一个BASE64的编码
     * @param path
     * @return
     */
    public static String Bitmap2StrByBase64(String path){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bit = BitmapFactory.decodeFile(path, opts);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 80, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
