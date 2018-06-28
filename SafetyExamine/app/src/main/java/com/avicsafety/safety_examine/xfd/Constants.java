package com.avicsafety.safety_examine.xfd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.avicsafety.safety_examine.url.BaseUrl;

import java.io.ByteArrayOutputStream;

/**
 * Created by 刘畅 on 2017/12/26.
 */

public class Constants {

//    public static final String BASE_URL = "http://42.56.72.9:10010/phoneServices/fd/geographicalPositionReceiveServlet";
    public static final String BASE_URL = BaseUrl.getBaseUrl()+"phoneServices/fd/geographicalPositionReceiveServlet";
//    public static final String BASE_URL = "http://192.168.1.127:8080/phoneServices/fd/geographicalPositionReceiveServlet";
//    public static final String BASE_URL = "http://192.168.56.1:8080/phoneServices/fd/geographicalPositionReceiveServlet";
//   public static final String TEST_URL="http://42.56.72.9:10012/phoneServices/fd/geographicalPositionReceiveServlet";
    public static final String TEST1_URL="http://192.168.1.183:8080/phoneServices/fd/geographicalPositionReceiveServlet";
    public static final String Pei_URL="http://192.168.1.114:8080/phoneServices/fd/geographicalPositionReceiveServlet";
    public static final String TEST3_URL="http://192.168.1.135:8080/phoneServices/fd/geographicalPositionReceiveServlet";

    public static String Bitmap2StrByBase64(String path){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bit = BitmapFactory.decodeFile(path, opts);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 80, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT).replace("\n","");
    }

}
