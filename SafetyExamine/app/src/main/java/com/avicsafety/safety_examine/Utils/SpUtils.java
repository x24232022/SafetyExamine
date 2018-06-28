package com.avicsafety.safety_examine.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;
import java.util.TreeSet;

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



    //写入String类型的数据
    public void putStringData(Context context, String key, String str) {
        this.mContext = context;
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, str);
        edit.commit();
    }
    //写入Set集合
    public void putSatData(Context context, String key, Set<String> valueSet ){
        this.mContext =context;

        SharedPreferences.Editor edit = sp.edit();
        edit.putStringSet(key,valueSet);
        edit.commit();

    }
    //获取Set集合
    public Set<String> getStringSetData(Context context,String key){
        this.mContext=context;
        Set<String> setData=new TreeSet<>();
         return sp.getStringSet(key,setData);

    }

    //获取String类型的数据
    public String getStringData(Context context, String key) {
        this.mContext = context;
        String defValue=sp.getString(key,"");
        return defValue;
    }


    //删除数据
    public void deleteData(Context context,String key){
        this.mContext=context;
        SharedPreferences.Editor edit=sp.edit();
        edit.remove(key);
    }

}

