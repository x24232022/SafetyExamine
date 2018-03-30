package com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.activity.ChangeOneActivity;
import com.avicsafety.ShenYangTowerComService.activity.FileScActivity;
import com.avicsafety.ShenYangTowerComService.model.MUsers;
import com.avicsafety.ShenYangTowerComService.model.Mdept;
import com.avicsafety.ShenYangTowerComService.model.MgdxqN;
import com.avicsafety.ShenYangTowerComService.model.Mgroup;
import com.avicsafety.ShenYangTowerComService.model.Mrwlb;
import com.avicsafety.ShenYangTowerComService.model.Mrwsl;
import com.avicsafety.ShenYangTowerComService.yd.activity.GDDealWithMultiFildsActivity;
import com.avicsafety.ShenYangTowerComService.yd.activity.GDDealWithOneFileActivity;
import com.avicsafety.ShenYangTowerComService.yd.activity.GDGTDZActivity;
import com.avicsafety.ShenYangTowerComService.yd.activity.GDShowAndDealWithNoFildActivity;
import com.avicsafety.ShenYangTowerComService.yd.activity.MainActiitys;
import com.avicsafety.ShenYangTowerComService.yd.activity.PhotoYdActivity;
import com.avicsafety.ShenYangTowerComService.yd.activity.PhotoYdClActivity;
import com.avicsafety.ShenYangTowerComService.yd.activity.ProcessedydActivity;
import com.avicsafety.ShenYangTowerComService.yd.activity.RWList0Activity;
import com.avicsafety.ShenYangTowerComService.yd.activity.RWList1Activity;
import com.avicsafety.ShenYangTowerComService.yd.activity.UserListActivity;
import com.avicsafety.lib.tools.L;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 刘畅 on 2017/7/19.
 */

public class CTCSRestClientUsage {


    public void dealGDCL(final Context context, int opFlag, String id, String teleAlarmreaanalysis,
                         String teleSubAlarmreaanalysis, String alarmrealist,
                         String resprocess, String other,
                         String faultrectime) throws UnsupportedEncodingException {

        RequestParams params = new RequestParams(Constants.BASE_GDCL);

        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("id",id);
        params.addParameter("type",opFlag);

        params.addParameter("teleAlarmreaanalysis",URLEncoder.encode(teleAlarmreaanalysis,"UTF-8"));
        params.addParameter("teleSubAlarmreaanalysis",URLEncoder.encode(teleSubAlarmreaanalysis,"UTF-8"));
        params.addParameter("alarmrealist",URLEncoder.encode(alarmrealist,"UTF-8"));
        params.addParameter("resprocess",URLEncoder.encode(resprocess,"UTF-8"));
        params.addParameter("other", URLEncoder.encode(other,"UTF-8"));
        params.addParameter("faultrectime", faultrectime);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    if (res.getInt("Code") == 200) {
                        JSONObject datas = res.getJSONObject("Response");
                        if (datas.getString("isSuccess").equals("success")) {
                            Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                            ((GDDealWithMultiFildsActivity) context).progressDialog.dismiss();
                            ((GDDealWithMultiFildsActivity) context).finish();
                        }
                    }
                } catch (JSONException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context,"失败",Toast.LENGTH_SHORT).show();
                L.v("工单处理错误阿~:",ex.getLocalizedMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    public void dealSCTPDZ(final Context context, String id, String data,int type){

        RequestParams params = new RequestParams(com.avicsafety.ShenYangTowerComService.conf.Constants.BASE_SCTP);
        params.addParameter("id",id);
        params.addParameter("submitUser", com.avicsafety.ShenYangTowerComService.conf.Constants.getSubmitUser(context));
        params.addParameter("files",data);
        params.addParameter("uploadType","TroubTicketMain");
        params.addParameter("type",type);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(context,"成功,页面自动关闭..", Toast.LENGTH_LONG).show();
                ((PhotoYdActivity) context).progressDialog.dismiss();
                ((PhotoYdActivity)context).finish();
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


    public void dealSCTP(final Context context, String id, String data,int type){

        RequestParams params = new RequestParams(com.avicsafety.ShenYangTowerComService.conf.Constants.BASE_SCTP);
        params.addParameter("id",id);
        params.addParameter("submitUser", com.avicsafety.ShenYangTowerComService.conf.Constants.getSubmitUser(context));
        params.addParameter("files",data);
        params.addParameter("uploadType","TroubTicketMain");
        params.addParameter("type",type);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(context,"成功,页面自动关闭..", Toast.LENGTH_LONG).show();
                ((PhotoYdClActivity) context).progressDialog.dismiss();
                ((PhotoYdClActivity)context).finish();
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

    public void dealSCWJ(final Context context, String id, String path,int type){

        RequestParams params = new RequestParams(com.avicsafety.ShenYangTowerComService.conf.Constants.BASE_SCWJ);
        params.setMultipart(true);
        params.addParameter("id",id);
        params.addParameter("submitUser", com.avicsafety.ShenYangTowerComService.conf.Constants.getSubmitUser(context));
        params.addParameter("uploadType","TroubTicketMain");
        params.addParameter("type",type);
        File file = new File(path);
        params.addBodyParameter("files",file);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(context,"成功,页面自动关闭..", Toast.LENGTH_LONG).show();
                ((FileScActivity) context).progressDialog.dismiss();
                ((FileScActivity)context).finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context,"上传失败!",Toast.LENGTH_SHORT).show();
                ((FileScActivity)context).progressDialog.dismiss();
                L.v("ssssssssssss : ",ex.getMessage().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }




    public void GetRWSL(final Context context, final int source) throws JSONException {

        RequestParams params = new RequestParams(Constants.BASE_RWSL);
        params.setConnectTimeout(60000);
        params.setCharset("UTF-8");
        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("type", Constants.RWSL);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");
                    if (code.equals("200")) {
                        String datas = res.getString("Response").toString();

                        Mrwsl m = JSON.parseObject(datas, Mrwsl.class);

                        switch (source) {
                            case 0:
                                ((MainActiitys) context).initGvMain(m);

                                ((MainActiitys) context).progressDialog
                                        .dismiss();

                                break;

                            case 1:
                                ((RWList0Activity)context).initList(m);

                                ((RWList0Activity) context).progressDialog
                                        .dismiss();
                                break;

                            case 2:
                                //((ChangeOneActivity) context).progressDialog.dismiss();

                                ((ChangeOneActivity) context).setMyd(m);
                               // ((ChangeOneActivity) context).progressDialog.dismiss();
                                ((ChangeOneActivity) context).initDatalt();
                                ((ChangeOneActivity) context).initDataXfd();
                                break;

                        }

                    } else {


                        switch (source) {
                            case 0:

                                ((MainActiitys) context).progressDialog
                                        .dismiss();

                                break;

//                            case 1:
//
//                                ((RWList0Activity) context).progressDialog
//                                        .dismiss();
//                                break;

                            case 2:
                                ((ChangeOneActivity) context).progressDialog
                                        .dismiss();

                                break;
                        }

                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ((ChangeOneActivity)context).progressDialog.dismiss();
                Toast.makeText(context,"任务获取失败!!!",Toast.LENGTH_SHORT).show();
                L.v("AAAAAAAAA:",ex.getMessage().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



    //我的任务

    public void GetRWLB(final Context context, int taskType, int start, int limit)
            throws JSONException {

        RequestParams params = new RequestParams(Constants.BASE_RWLB);
        params.setCharset("UTF-8");
        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("type", Constants.RWLB);
        params.addParameter("taskType", taskType);
        params.addParameter("start", start);
        params.addParameter("limit", limit);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");
                    if (code.equals("200")) {
                        String datas = res.getString("Response").toString();
                        // 登录成功保存用户信息
                        int cod = 1;

                        List<Mrwlb> mList = JSON.parseArray(datas,
                                Mrwlb.class);
                        if(mList.size() == 0){
                            ((RWList1Activity) context).finish();

                        }

                        ((RWList1Activity) context).initList2(mList, res.getInt("total"));

                        ((RWList1Activity) context).progressDialog
                                .dismiss();


                    } else {
                        Toast.makeText(context,
                                (CharSequence) res.get("Msg"),
                                Toast.LENGTH_SHORT).show();

                        ((RWList1Activity) context).progressDialog
                                .dismiss();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context,"获取信息失败.",Toast.LENGTH_SHORT).show();
                L.v("BBBBBBBBB:",ex.getLocalizedMessage());
                ((RWList1Activity)context).progressDialog.dismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    // 工单详情
    public void GetGDXQ(final Context context, String id) throws JSONException {

        RequestParams params = new RequestParams(Constants.BASE_GDXQ);
        params.setCharset("UTF-8");
        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("type", Constants.GDXQ);
        params.addParameter("id", id);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("@@" + result.toString());

                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");
                    if (code.equals("200")) {
                        String datas = res.getString("Response").toString();
                        // 登录成功保存用户信息

                        MgdxqN m = JSON.parseObject(datas, MgdxqN.class);

                        ((GDShowAndDealWithNoFildActivity) context)
                                .ShowGDInfo(m);

                        ((GDShowAndDealWithNoFildActivity) context).progressDialog
                                .dismiss();

                    } else {

                        ((GDShowAndDealWithNoFildActivity) context).progressDialog
                                .dismiss();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context,"工单详情获取失败~",Toast.LENGTH_SHORT).show();
                L.v("yd工单详情!        :",ex.getMessage().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



    // 工单签收、工单审核、重新处理
    public void dealWithNoFild(final Context context, String id, int opFlag)
            throws JSONException {

        RequestParams params = new RequestParams(Constants.BASE_QSSHCXCL);

        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("type", opFlag);
        params.addParameter("id", id);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                String jMsg = null;
                try {
                    res = new JSONObject(result);
                    if (res.get("Code").equals(200)) {
                        JSONObject res1 = res.getJSONObject("Response");
                        // GDShowAndDealWithNoFildActivity
                        if(res1.get("isSuccess").toString().equals("success")){
                            Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                            ((GDShowAndDealWithNoFildActivity) context).progressDialog.dismiss();
                            ((GDShowAndDealWithNoFildActivity) context).initData();
//                            ((GDShowAndDealWithNoFildActivity) context).btn_clrqs.setVisibility(View.GONE);
//                            ((GDShowAndDealWithNoFildActivity) context).btn_clrfk.setVisibility(View.VISIBLE);
//                            ((GDShowAndDealWithNoFildActivity) context).btn_th.setVisibility(View.VISIBLE);
                            //MyApplication.getInstance().exit();
                        }else{
                            jMsg= res1.get("message").toString();
                            Toast.makeText(context, "操作失败:"+jMsg, Toast.LENGTH_SHORT).show();
                            ((GDShowAndDealWithNoFildActivity) context).progressDialog.dismiss();
                        }
                    } else {
                        Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
                        ((GDShowAndDealWithNoFildActivity) context).progressDialog
                                .dismiss();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context,"签收失败..",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    // 工单其他操作，派发转派退回驳回协同验证
    public void dealWithOneFild(final Context context, int opFlag, String id,
                                String value,String value1) throws JSONException, UnsupportedEncodingException {

        RequestParams params = new RequestParams(Constants.BASE_ZP_PF_TH_BH_XTYZ);
        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("type", opFlag);
        params.addParameter("id", id);

        if (Constants.GDPF == opFlag) {
            params.addParameter("processer", value);
        } else if (Constants.GDZP == opFlag) {
            params.addParameter("transferUserId", value);
        } else if (Constants.GDTH == opFlag) {
            params.addParameter("rejectreason2", URLEncoder.encode(value, "UTF-8"));
            params.addParameter("returnBak", URLEncoder.encode(value1,"UTF-8"));
        } else if (Constants.GDBH == opFlag) {
            params.addParameter("rejectContent", URLEncoder.encode(value, "UTF-8"));
        } else if (Constants.GDYZ == opFlag) {
            params.addParameter("validateContent", value);
        } else if(Constants.CBFK == opFlag){
            params.addParameter("remindersBak",URLEncoder.encode(value, "UTF-8"));
        } else if(Constants.GDDB == opFlag){
            params.addParameter("supervise", URLEncoder.encode(value, "UTF-8"));
        } else if(Constants.DBFK == opFlag){
            params.addParameter("superviseBak",URLEncoder.encode(value, "UTF-8"));
        } else if(Constants.CLRFK == opFlag){
            params.addParameter("processBak",URLEncoder.encode(value, "UTF-8"));
        } else if(Constants.GDCB == opFlag){
            params.addParameter("reminders",URLEncoder.encode(value, "UTF-8"));
        }

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                String jMsg = null;
                try {
                    res = new JSONObject(result);
                    if (res.get("Code").equals(200)) {
                        JSONObject datas = res.getJSONObject("Response");
                        // 登录成功保存用户信息
                        if(datas.get("isSuccess").toString().equals("success")){
                            Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                            ((GDDealWithOneFileActivity) context).progressDialog.dismiss();
                            //((GDShowAndDealWithNoFildActivity) context).goBack(null);
//                            ((GDShowAndDealWithNoFildActivity) context).btn_cl.setVisibility(View.VISIBLE);
//                            ((GDShowAndDealWithNoFildActivity) context).btn_th.setVisibility(View.VISIBLE);
//                            ((GDShowAndDealWithNoFildActivity) context).btn_scfj.setVisibility(View.VISIBLE);
                            ((GDDealWithOneFileActivity)context).finish();
                        }else{
                            jMsg= datas.get("message").toString();
                            Toast.makeText(context, "操作失败:"+jMsg, Toast.LENGTH_SHORT).show();
                            ((GDDealWithOneFileActivity) context).progressDialog.dismiss();
                        }
                    } else {
                        Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show();
                        ((GDShowAndDealWithNoFildActivity) context).progressDialog
                                .dismiss();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                L.v("aaaaaaaaaaaaaaaaaaa",ex.getLocalizedMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }




    // 选部门，选分组，选人员
    public void getUser(final Context context, final int opFlag, String id)
            throws JSONException {

        RequestParams params = null;

        String url = Constants.BASE_XBM_XFZ_XRY;
        switch (opFlag) {
            case 0:

                url += "getDepartmentServlet";
                params = new RequestParams(url);
                params.setCharset("UTF-8");
                params.addParameter("submitUser", Constants.getSubmitUser(context));
                params.addParameter("location", id);
                break;
            case 1:
                url += "getGroupServlet";
                params = new RequestParams(url);
                params.setCharset("UTF-8");
                params.addParameter("submitUser", Constants.getSubmitUser(context));
                params.addParameter("departmentId", id);
                break;
            case 2:
                url += "getUserServlet";
                params = new RequestParams(url);
                params.setCharset("UTF-8");
                params.addParameter("submitUser", Constants.getSubmitUser(context));
                params.addParameter("groupId", id);
                break;
        }

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                String jMsg = null;
                try {
                    res = new JSONObject(result);
                    if (res.get("Code").equals(200)) {
                        jMsg = res.getString("Response").toString();
                        // 登录成功保存用户信息

                        switch (opFlag) {
                            case 0:
                                List<Mdept> listDept = JSON.parseArray(jMsg,
                                        Mdept.class);
                                ((UserListActivity) context).setDept(listDept);
                                break;
                            case 1:
                                List<Mgroup> listGroup = JSON.parseArray(jMsg,
                                        Mgroup.class);
                                ((UserListActivity) context).setGroup(listGroup);
                                break;
                            case 2:
                                List<MUsers> listUsers = JSON.parseArray(jMsg,
                                        MUsers.class);
                                ((UserListActivity) context).setPreson(listUsers);
                                break;
                        }

                    }

                    ((UserListActivity) context).progressDialog.dismiss();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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

    //我处理过的工单
    public void GetProcessedyd(final Context context, int start, int limit) throws JSONException {

        RequestParams params = new RequestParams(Constants.BASE_WCLGDGD);
        params.setCharset("UTF-8");
        params.addParameter("submitUser",Constants.getSubmitUser(context));
        params.addParameter("type", Constants.GDCLEDYD);
        params.addParameter("start", start);
        params.addParameter("limit", limit);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                String jMsg = null;
                try {
                    res = new JSONObject(result);
                    if (res.get("Code").equals(200)) {

                        jMsg = res.getString("Response").toString();
                        // 登录成功保存用户信息

                        List<Response> mList2 = JSON.parseArray(jMsg,
                                Response.class);


                        ((ProcessedydActivity) context).initList2(mList2, res.getInt("total"));

                        ((ProcessedydActivity) context).progressDialog.dismiss();

                    } else {
                        Toast.makeText(context, (CharSequence) res.get("Msg"),
                                Toast.LENGTH_SHORT).show();

                        ((ProcessedydActivity) context).progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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

    public void dealGDdz(final Context context, int opFlag, String id,
                         String togetherContent, String towerUser, String towerUserTel,
                         String togetherDate) throws JSONException,
            UnsupportedEncodingException {

        RequestParams params = new RequestParams(Constants.BASE_GDDZ);

        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("id", id);
        params.addParameter("type", opFlag);

        params.addParameter("togetherContent",
                URLEncoder.encode(togetherContent, "UTF-8"));
        params.addParameter("towerUser", URLEncoder.encode(towerUser, "UTF-8"));
        params.addParameter("towerUserTel", towerUserTel);
        params.addParameter("togetherDate", togetherDate);

        System.out.println("@@@@@" + togetherContent + towerUser + towerUserTel
                + togetherDate);// 测试

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String jMsg = null;
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    if (res.get("Code").equals(200)) {
                        jMsg = res.get("Response").toString();
                        // 登录成功保存用户信息

                        Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();

                        ((GDGTDZActivity) context).progressDialog
                                .dismiss();
                        ((GDGTDZActivity) context).finish();


                    } else {
                        Toast.makeText(context,
                                (CharSequence) res.get("Msg"),
                                Toast.LENGTH_SHORT).show();

                        ((GDGTDZActivity) context).progressDialog
                                .dismiss();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ((GDGTDZActivity) context).progressDialog.dismiss();
                Toast.makeText(context, "操作失败了...", Toast.LENGTH_SHORT).show();
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