package com.avicsafety.ShenYangTowerComService.xfd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.activity.ChangeOneActivity;
import com.avicsafety.lib.tools.L;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by 刘畅 on 2017/12/26.
 * 网络请求类
 */

public class XinFDMethod {

    //发送位置信息type0
    public void GetXinFsJwd(final Context context, String userid, int type, double lon, double lat) {
        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.addParameter("lon",lon);
        params.addParameter("lat",lat);
        params.setConnectTimeout(60000);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Rwsl rwsl = JSON.parseObject(result, Rwsl.class);
                ((ChangeOneActivity) context).setRwsl(rwsl);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    //发送数量type2
    public void GetXinFdRWSL(final Context context, String userid, int type) {
        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.addParameter("userid", userid);
        params.setConnectTimeout(60000);
        params.addParameter("type", type);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Rwsl rwsl = JSON.parseObject(result, Rwsl.class);
                ((ChangeOneActivity) context).setRwsl(rwsl);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //新发电工单数量type2
    public void GetXinFdPlanRWSL(final Context context, String userid, int type) {
        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.setConnectTimeout(60000);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Rwsl rwsl = JSON.parseObject(result, Rwsl.class);
                ((PlanActivityXin) context).tv_xin_work_wj_number.setText(rwsl.getWjgd());
                ((PlanActivityXin) context).tv_xin_work_yjs_number.setText(rwsl.getYjsgd());
                ((PlanActivityXin) context).tv_xin_work_ycf_number.setText(rwsl.getYcfgd());
                ((PlanActivityXin) context).tv_xin_work_ydz_number.setText(rwsl.getYdzgd());
                ((PlanActivityXin) context).progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //type5发照片
    public void dealdzSCTP(final Context context, String id, String data,int type,String userid,int types){

        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.addParameter("ticketid",id);
        params.addParameter("files",data);
        params.addParameter("type",type);
        params.addParameter("userid",userid);
        params.addParameter("types",types);
        params.setConnectTimeout(60000);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(context,"成功,页面自动关闭..", Toast.LENGTH_LONG).show();
                ((PhotoActivityDzXin) context).progressDialog.dismiss();
                ((PhotoActivityDzXin)context).finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }
    //异常，失败，成功请求的方法type7
    public void dealSCTP(final Context context, String id, String data,int type,String status,String userid,int types){

        RequestParams params = new RequestParams(Constants.TEST_URL);
        params.addParameter("ticketid",id);
        params.addParameter("files",data);
        params.addParameter("type",type);
        params.addParameter("status",status);
        params.addParameter("userid",userid);
        params.addParameter("types",types);
        params.setConnectTimeout(60000);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(context,"成功,页面自动关闭..", Toast.LENGTH_LONG).show();
                ((PhotoActivityXin) context).progressDialog.dismiss();
                ((PhotoActivityXin)context).finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }





}
