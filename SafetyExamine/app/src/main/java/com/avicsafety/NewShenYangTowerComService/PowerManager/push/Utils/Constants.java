package com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.avicsafety.NewShenYangTowerComService.model.MUser;

/**
 * Created by 刘畅 on 2017/8/21.
 */

public class Constants {
    public static final String URL_SERVER = "http://42.56.70.110:10008/DDTT";
//    public static final String URL_SERVER = "http://192.168.1.114:8080/DDTT";

//    public static final String URL_SERVER="http://113.107.235.66:9928/DDTT";//外网地址

    public static final String ZRWTS = URL_SERVER + "/androidMgt/androidFacadeAction!loadAllNum";

    public static final String URL_SERVER1 = "192.168.1.117:8080";

    public static final String JWD = URL_SERVER1 + "/GeographicalPositionReceiveServlet";
    public static final String ADD_PERSONNEL_POSITION=URL_SERVER + "/androidMgt/androidFacadeAction!StaffAddressBack";

    public static final String AUDITED_POWER_WORK = URL_SERVER + "/androidMgt/androidFacadeAction!loadCheckedData";

    public static final String POWER_TRANSFER_PERSON = URL_SERVER + "/androidMgt/androidFacadeAction!getFapgperson";
    //计划发电转派工单
    public static final String POWER_TRANSFER_WORK= URL_SERVER + "/androidMgt/androidFacadeAction!redeploy";
    //事故工单转派工单
    public static final String FAULT_TRANSFER_WORK= URL_SERVER + "/androidMgt/androidFacadeAction!sendLeader";
    //计划工单接单
    public static final String POWER_ORDERS_WORK = URL_SERVER + "/androidMgt/androidFacadeAction!reportReceiving";
    //故障工单接单
    public static final String POWER_FAULT_WORK = URL_SERVER + "/androidMgt/androidFacadeAction!orderReceivingInfo";

    public static final String LOAD_FAULT_WORK = URL_SERVER + "/androidMgt/androidFacadeAction!loadOrderList";

    public static final String POWER_START_GENERATION = URL_SERVER + "/androidMgt/androidFacadeAction!reportGenerating";

    public static final String POWER_FAULT_START_WORK = URL_SERVER + "/androidMgt/androidFacadeAction!reportGenerating";

    public static final String POWER_TURN_DOWN = URL_SERVER + "/androidMgt/androidFacadeAction!turnDown";

    public static final String FAULT_TURN_DOWN = URL_SERVER + "/androidMgt/androidFacadeAction!rejectedOrder";

    public static final String POWER_END_GENERATION = URL_SERVER + "/androidMgt/androidFacadeAction!reportGenerateFinish";

    public static final String POWER_FAULT_END_WORK = URL_SERVER + "/androidMgt/androidFacadeAction!powerGenerationStop";



















    public static String getSubmitUser(Context context) {

        SharedPreferences userInfo = context.getSharedPreferences("uInfo",
                Context.MODE_PRIVATE);

        return userInfo.getString("userName", "");
    }

    public static MUser getUserInfo(Service oThis) {
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
    public static MUser getUserInfo1(Activity oThis) {
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
