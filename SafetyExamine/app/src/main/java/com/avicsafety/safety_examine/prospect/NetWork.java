package com.avicsafety.safety_examine.prospect;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.safety_examine.model.ProspectBean;
import com.avicsafety.safety_examine.model.ProspectTotal;
import com.avicsafety.safety_examine.url.BaseUrl;
import com.avicsafety.safety_examine.xfd.Constants;

import com.avicsafety.lib.tools.L;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2018/6/14 0014.
 * 勘察模块网络请求类
 */

public class NetWork {
    private static final String TEST_URL="http://192.168.1.183:8080/";
    public static final String BASE_URL =BaseUrl.getBaseUrl()/*TEST_URL*/+"phoneServices/survey/surveyServlet";

    private static NetWork newWork = new NetWork();

    private NetWork() {}
    public static NetWork getNetWork() {
        return newWork;
    }
    /**
     * ChangeOneActivity  type0 获取工单数量
     *
     * ProspectListActivity  type1 获取全部的工单信息
     *
     * ProspectActivity  type2  获取单条工单的详细信息
     *
     * ProspectActivity  type3  修改工单的状态信息
     *
     * ProspectActivity  type4  打点上传经纬度
     */



}
