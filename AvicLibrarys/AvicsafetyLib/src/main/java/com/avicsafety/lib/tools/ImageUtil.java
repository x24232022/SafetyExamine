package com.avicsafety.lib.tools;

import android.content.ContentValues;
import android.provider.MediaStore;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ImageUtil {

    public static void DownLoadImagesByUrl(String filesDir, final String fileName, String url) {
        //存到本地的绝对路径
        final String filePath = filesDir + "/" + fileName;
        File file = new File(filesDir + "/");
        //如果不存在
        if (!file.exists()) {
            //创建
            file.mkdirs();
        }

        RequestParams entity = new RequestParams(url);
        entity.setSaveFilePath(filePath);
        x.http().get(entity, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
//                filesPath.add(result.getAbsolutePath());
                LogUtil.e("onSuccess：" + result.getAbsolutePath());
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                values.put(MediaStore.Images.Media.TITLE, fileName);
                x.app().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                L.e("onError ");
                Toast.makeText(x.app(), "网络错误，图片下载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                L.e("onCancelled ");
            }

            @Override
            public void onFinished() {
                L.e("onFinished ");
            }
        });
    }
}
