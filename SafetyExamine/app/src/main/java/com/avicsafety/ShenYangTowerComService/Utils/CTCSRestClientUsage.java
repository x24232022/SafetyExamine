package com.avicsafety.ShenYangTowerComService.Utils;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.activity.ChangeOneActivity;
import com.avicsafety.ShenYangTowerComService.activity.GDDealWithMultiFildsActivity;
import com.avicsafety.ShenYangTowerComService.activity.GDDealWithOneFileActivity;
import com.avicsafety.ShenYangTowerComService.activity.GDShowAndDealWithNoFildActivity;
import com.avicsafety.ShenYangTowerComService.activity.MyApplication;
import com.avicsafety.ShenYangTowerComService.activity.PhotoActivity;
import com.avicsafety.ShenYangTowerComService.activity.RWList0Activity;
import com.avicsafety.ShenYangTowerComService.activity.RWList1Activity;
import com.avicsafety.ShenYangTowerComService.activity.StartCodeActivity;
import com.avicsafety.ShenYangTowerComService.activity.TowerMainActivity;
import com.avicsafety.ShenYangTowerComService.activity.UserListActivity;
import com.avicsafety.ShenYangTowerComService.conf.Constants;
import com.avicsafety.ShenYangTowerComService.model.MUsers;
import com.avicsafety.ShenYangTowerComService.model.Mdept;
import com.avicsafety.ShenYangTowerComService.model.MgdxqTT;
import com.avicsafety.ShenYangTowerComService.model.Mgroup;
import com.avicsafety.ShenYangTowerComService.model.MksyzmTT;
import com.avicsafety.ShenYangTowerComService.model.MrwlbTT;
import com.avicsafety.ShenYangTowerComService.model.MrwslTT;
import com.avicsafety.lib.tools.L;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


/**
 * Created by 刘畅 on 2017/6/2.
 */

public class CTCSRestClientUsage {

    public void dealGDCL(final Context context, String id, int type, String powerFailureDate,
                         String powerRecoveryDate, String electricityStartDate,
                         String electricityEndDate, String electricityReason,
                         String enduranceReasonRemark, String electricityType,
                         String address, String interruptMinute, String remark) throws UnsupportedEncodingException {

        RequestParams params = new RequestParams(Constants.BASE_GDCL);

        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("id",id);
        params.addParameter("type",type);

        params.addParameter("powerFailureDate",powerFailureDate);
        params.addParameter("powerRecoveryDate",powerRecoveryDate);
        params.addParameter("electricityStartDate",electricityStartDate);
        params.addParameter("electricityEndDate",electricityEndDate);
        params.addParameter("electricityReason", URLEncoder.encode(electricityReason,"UTF-8"));
        params.addParameter("enduranceReasonRemark", URLEncoder.encode(enduranceReasonRemark,"UTF-8"));
        params.addParameter("electricityType", URLEncoder.encode(electricityType,"UTF-8"));
        params.addParameter("address", URLEncoder.encode(address,"UTF-8"));
        params.addParameter("interruptMinute", URLEncoder.encode(interruptMinute,"UTF-8"));
        params.addParameter("remark", URLEncoder.encode(remark,"UTF-8"));

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

    public void dealSCTP(final Context context, String id, String data,int type){

        RequestParams params = new RequestParams(Constants.BASE_SCTP);
        params.addParameter("id",id);
        params.addParameter("submitUser",Constants.getSubmitUser(context));
        params.addParameter("files",data);
        params.addParameter("uploadType","ElectricityGeneration");
        params.addParameter("type",type);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(context,"成功,页面自动关闭..", Toast.LENGTH_LONG).show();
                ((PhotoActivity) context).progressDialog.dismiss();
                ((PhotoActivity)context).finish();
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



    public void GetRWSL(final Context context, final int source) throws JSONException {

        RequestParams params = new RequestParams(Constants.BASE_RWSL);
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

                        MrwslTT m = JSON.parseObject(datas, MrwslTT.class);

                        switch (source) {
                            case 0:
                                ((TowerMainActivity) context).initGvMain(m);

                                ((TowerMainActivity) context).progressDialog
                                        .dismiss();

                                break;

                            case 1:
                                ((RWList0Activity)context).initList(m);

                                ((RWList0Activity) context).progressDialog
                                        .dismiss();
                                break;

                            case 2:
                                //((ChangeOneActivity) context).progressDialog.dismiss();

                                ((ChangeOneActivity) context).setMfd(m);
                                //((ChangeOneActivity) context).progressDialog.dismiss();
//                                ((ChangeOneActivity) context).initDatafd();
                                ((ChangeOneActivity) context).initDatayd();
                                break;

                        }

                    } else {


                        switch (source) {
                            case 0:

                                ((TowerMainActivity) context).progressDialog
                                        .dismiss();

                                break;

                            case 1:

                                ((RWList0Activity) context).progressDialog
                                        .dismiss();
                                break;

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
                Toast.makeText(context,"任务获取失败",Toast.LENGTH_SHORT).show();
                L.e("FDRenWuLog !!!!!!!!!!!!!:",ex.getMessage().toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public void GetDDRWSL(final Context context,String fapgperson){
        RequestParams params = new RequestParams(com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Constants.ZRWTS);
        params.addParameter("fapgperson",fapgperson);
        params.addParameter("status",2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("totalRows");
                    ((TowerMainActivity)context).setCode(code);
                } catch (JSONException e) {
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

    public void GetXDDRWSL(final Context context,String fapgperson){
        RequestParams params = new RequestParams(com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Constants.ZRWTS);
        params.setConnectTimeout(60000);
        params.addParameter("fapgperson",fapgperson);
        params.addParameter("status",2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String xcode = res.getString("totalRows");
                    ((ChangeOneActivity)context).setXcode(xcode);
                } catch (JSONException e) {
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


    //我的任务

    public void GetRWLB(final Context context, int taskType, int start, int limit)
            throws JSONException {

        RequestParams params = new RequestParams(Constants.BASE_RWLB);
        params.setCharset("UTF-8");
        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("type", Constants.WDRW);
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

                        List<MrwlbTT> mList = JSON.parseArray(datas,
                                MrwlbTT.class);

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
                L.v("AAAAAAAAAAAA:",ex.getLocalizedMessage());
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

        RequestParams params = new RequestParams(Constants.BASE_GDQX);
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

                        MgdxqTT m = JSON.parseObject(datas, MgdxqTT.class);

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
        //开始验证码
    public void GetKSYZM(final Context context, String id) throws JSONException {

        RequestParams params = new RequestParams(Constants.BASE_KSYZM);
        params.setCharset("UTF-8");
        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("type", Constants.HQKSYZM);
        params.addParameter("id", id);

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

                        MksyzmTT m = JSON.parseObject(datas, MksyzmTT.class);

//                                ((StartCodeActivity)context).initEVENT();

                        ((StartCodeActivity) context).showCode(m);


                    } else {
                        ((StartCodeActivity) context).progressDialog
                                .dismiss();
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



//
//
//
//
//
//
//
    // 工单其他操作，派发转派退回驳回协同验证
    public void dealWithOneFild(final Context context, int type, String id,
                                String value, String turnOpinion) throws JSONException, UnsupportedEncodingException {

        RequestParams params = new RequestParams(Constants.BASE_PFZPTHBHXTYZ);
        params.addParameter("submitUser", Constants.getSubmitUser(context));
        params.addParameter("type", type);
        params.addParameter("id", id);
        params.addParameter("turnOpinion", URLEncoder.encode(turnOpinion,"UTF-8"));

        if (Constants.ZP == type) {
            params.addParameter("transferUserId", value);
        } else if (Constants.TH == type) {
            params.addParameter("backReason", URLEncoder.encode(value, "UTF-8"));
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
//
//
//
//
//
//
//

//
//
//
//
//
//
//








    /*public void dealQMJZ(final Context context, String attributename, String classname){

        RequestParams params = new RequestParams();
        params.put("attributename",attributename);
        params.put("submitUser",CTCSHelper.getSubmitUser(context));
        params.put("type",CTCSHelper.MJZZXHQ);
        params.put("classname",classname);

        CTCSRestClient.get("phoneServices/electricityGenerationServlet",params
                ,new JsonHttpResponseHandler(){

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          String responseString, Throwable throwable) {
                        // TODO 自动生成的方法存根
                        super.onFailure(statusCode, headers, responseString,
                                throwable);
                        ((GDDealWithMultiFildsActivity)context).progressDialog
                                .dismiss();
                        Toast.makeText(context, "获取失败了...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        System.out.println("@@" + response.toString());

                        String jMsg = null;
                        try {
                            if (response.get("Code").equals(200)) {
                                jMsg = response.get("Response").toString();
                                // 登录成功保存用户信息

                                MmjzhqTT m = JSON.parseObject(jMsg, MmjzhqTT.class);

                                ((GDShowAndDealWithNoFildActivity) context)
                                        .ShowGDInfo(m);

                                ((GDShowAndDealWithNoFildActivity) context).progressDialog
                                        .dismiss();

                            } else {
                                Toast.makeText(context,
                                        (CharSequence) response.get("Msg"),
                                        Toast.LENGTH_SHORT).show();

                                ((GDShowAndDealWithNoFildActivity) context).progressDialog
                                        .dismiss();
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });

    }*/

}
