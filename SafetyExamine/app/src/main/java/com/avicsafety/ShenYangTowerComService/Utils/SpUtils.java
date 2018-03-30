package com.avicsafety.ShenYangTowerComService.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/3/20.
 */

public class SpUtils {
    private Context mContext;

    private SpUtils() {
    }

    static SpUtils mSpUtils = new SpUtils();

    public static SpUtils getSputils() {

        return mSpUtils;
    }

    SharedPreferences sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);

    //写入boolean类型的数据
    public void putBooleanData(Context context, String key, boolean msg) {
        this.mContext = context;
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, msg);
        edit.commit();
    }

    //写入String类型的数据
    public void putStringData(Context context, String key, String str) {
        this.mContext = context;
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, str);
        edit.commit();
    }

    //获取boolean类型的数据
    public boolean getBooleanData(Context context, String key, boolean defValue) {
        this.mContext = context;
        return sp.getBoolean(key, defValue);
    }

    public String getStringData(Context context, String key, String defValue) {
        this.mContext = context;

        return sp.getString(key, defValue);
    }

}

