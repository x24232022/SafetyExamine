package com.avicsafety.safety_examine.dx.activity.dxUtil;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.safety_examine.url.BaseUrl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 刘畅 on 2017/7/19.
 */

public class Constants {



//    public static final String BASE_URL = "http://42.56.72.9:10005/";
    public static final String BASE_URL = BaseUrl.getBaseUrl();

//    public static final String BASE_URL = "http://192.168.1.112:8080/";
    public static final String BASE_GDCL = BASE_URL + "phoneServices/troubTicketMainForTeleServlet";
    // 工单签收、工单审核、重新处理
    public static final String BASE_QS_SH_CXCL = BASE_URL + "phoneServices/troubTicketMainForTeleServlet";
    public static final String BASE_PF_ZP_TH_BH = BASE_URL + "phoneServices/troubTicketMainForTeleServlet";
    public static final String BASE_WCLGDGD = BASE_URL + "phoneServices/troubTicketMainForTeleServlet";
    public static final String BASE_GDXQ = BASE_URL + "phoneServices/troubTicketMainForTeleServlet";
    public static final String BASE_RWLB = BASE_URL + "phoneServices/troubTicketMainForTeleServlet";
    public static final String BASE_RWSL = BASE_URL + "phoneServices/troubTicketMainForTeleServlet";
    public static final String BASE_XBM_XFZ_XRY = BASE_URL + "phoneServices/";

    // 1:任务数量;2:任务列表;3工单详情;4:签收工单;5:派发工单;6:工单处理;7:关闭工单;8:重新处理;9:驳回工单;10：转派工单；11退回工单
    public static String RWSL = "1";
    public static int RWLB = 2;
    public static int GDXQ = 3;
    public static int GDQS = 4;
    public static int GDPF = 5;
    public static int GDCL = 6;
    public static int GDGB = 7;
    public static int GDCCL = 8;
    public static int GDBH = 9;
    public static int GDZP = 10;
    public static int GDTH = 11;
    public static int GDCLED = 12;

    public static String[] cities = { "省分公司", "沈阳", "大连", "鞍山", "抚顺", "本溪",
            "丹东", "锦州", "营口", "阜新", "辽阳", "铁岭", "朝阳", "盘锦", "葫芦岛" };

    public static int[] cityValues = { 2, 4, 15, 1, 13, 17, 14, 10, 6, 12, 9,
            7, 16, 8, 3 };

    public static String[] teleAlarmreaanalysis = { "电源", "其他" };
    public static String[] teleAlarmreaanalysisValues = { "2", "0" };

    public static String[][] teleSubAlarmreaanalysis = {
            { "电源-高温断站", "电源-基站电源设备故障", "电源-基站外市电故障", "电源-停电(无蓄电池)",
                    "电源-停电(蓄电池性能下降)", "电源-停电(蓄电池性能正常)" }, { "其他" } };

    public static String[][] teleSubAlarmreaanalysisValues = {
            { "1000163", "1000164", "1000165", "1000166", "1000167", "1000168" },
            { "1000174" } };

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

    public static String getVersionName(Context context){
        String PackageName = context.getPackageName();
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(PackageName, 0);
            return info.versionName;
        }catch (Exception e){
            return "";
        }
    }
}
