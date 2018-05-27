package com.avicsafety.NewShenYangTowerComService.lt.activity.ltUtil;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.avicsafety.NewShenYangTowerComService.model.MUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 刘畅 on 2017/7/26.
 */

public class Constants {
    public static final String BASE_URL = "http://42.56.72.9:10005/";

    public static final String BASE_GDCL = BASE_URL + "phoneServices/troubTicketMainForUnicomServlet";
    public static final String BASE_GDDZ = BASE_URL + "phoneServices/troubTicketMainForUnicomServlet";
    // 工单签收、工单审核、重新处理
    public static final String BASE_QS_SH_CXCL = BASE_URL + "phoneServices/troubTicketMainForUnicomServlet";
    // 工单其他操作，派发转派退回驳回协同验证
    public static final String BASE_PF_ZP_TH_BH_XTYZ = BASE_URL + "phoneServices/troubTicketMainForUnicomServlet";
    public static final String BASE_WCLGDGD = BASE_URL + "phoneServices/troubTicketMainForUnicomServlet";
    public static final String BASE_GDXQ = BASE_URL + "phoneServices/troubTicketMainForUnicomServlet";
    public static final String BASE_RWLB = BASE_URL + "phoneServices/troubTicketMainForUnicomServlet";
    public static final String BASE_RWSL = BASE_URL + "phoneServices/troubTicketMainForUnicomServlet";
    public static final String BASE_XBM_XFZ_XRY = BASE_URL + "phoneServices/";

    // 1:任务数量;2:任务列表;3工单详情;4:签收工单;5:派发工单;6:工单处理;7:关闭工单;8:重新处理;9:驳回工单;
    // 10协同验证；11共同到站；12：退回；13：转派
    public static int RWSL = 1;
    public static int RWLB = 2;
    public static int GDXQ = 3;
    public static int GDQS = 4;
    public static int GDPF = 5;
    public static int GDCL = 6;
    public static int GDGB = 7;
    public static int GDCCL = 8;
    public static int GDBH = 9;
    public static int GDYZ = 10;
    public static int GDDZ = 11;
    public static int GDTH = 12;
    public static int GDZP = 13;
    public static int GDCLEDYD = 15;

    public static String[] cities = { "省分公司", "沈阳", "大连", "鞍山", "抚顺", "本溪",
            "丹东", "锦州", "营口", "阜新", "辽阳", "铁岭", "朝阳", "盘锦", "葫芦岛" };

    public static int[] cityValues = { 2, 4, 15, 1, 13, 17, 14, 10, 6, 12, 9,
            7, 16, 8, 3 };

    public static String dateToStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        return formatter.format(date);
    }

    public static String getSubmitUser(Context context) {

        SharedPreferences userInfo = context.getSharedPreferences("uInfo",
                Context.MODE_PRIVATE);

        return userInfo.getString("userName", "");
    }

    public static MUser getUserInfo(Activity oThis) {
        // TODO 自动生成的方法存根
        SharedPreferences sp = oThis.getSharedPreferences("userInfo",
                Context.MODE_PRIVATE);

        String jsonStr = sp.getString("userInfo", "");

        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        } else {
            return JSON.parseObject(jsonStr, MUser.class);
        }
    }

    public static void setSubmitUser(String submitUser, Context context) {

        SharedPreferences userInfo = context.getSharedPreferences("uInfo",
                Context.MODE_PRIVATE);
        userInfo.edit().putString("userName", submitUser).commit();

    }

    public static void setUserInfo(Context oThis, String userInfoStr) {
        // TODO 自动生成的方法存根

        // 在本地保存当前用户信息
        SharedPreferences sp = oThis.getSharedPreferences("userInfo",
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        if (!TextUtils.isEmpty(userInfoStr)) {
            editor.putString("userInfo", userInfoStr);
        } else {
            editor.clear();
        }
        editor.commit();
    }
}
