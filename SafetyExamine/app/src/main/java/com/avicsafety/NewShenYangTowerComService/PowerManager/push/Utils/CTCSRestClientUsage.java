package com.avicsafety.NewShenYangTowerComService.PowerManager.push.Utils;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
//import com.avicsafety.ShenYangTowerComService.activity.PlanActivity;
//import com.avicsafety.ShenYangTowerComService.activity.PlanDetailsActivity;
//import com.avicsafety.ShenYangTowerComService.activity.PlanListActivity;
import com.avicsafety.NewShenYangTowerComService.model.PowerWork;
import com.avicsafety.NewShenYangTowerComService.model.PowerWorkBean;
import com.avicsafety.NewShenYangTowerComService.model.PowerWorkBeanMyImpl;
import com.avicsafety.lib.tools.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 刘畅 on 2017/8/21.
 */

public class CTCSRestClientUsage {
    public void GetJWD(final Context context,double longitude,double latitude,String userAccount,
                       String userName,double blongitude,double blatitude){
        RequestParams params = new RequestParams(Constants.ADD_PERSONNEL_POSITION);
        params.addParameter("longitude",longitude);
        params.addParameter("latitude",latitude);
        params.addParameter("staffnum",userAccount);
        params.addParameter("staffName",userName);
        params.addParameter("blongitude",blongitude);
        params.addParameter("blatitude",blatitude);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context,"失败QvQ~",Toast.LENGTH_SHORT).show();
                L.v("locationManager!",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }



    public void GetJWDX(final Context context,String id,String userid,String name,
                       double lon,double lat){
        RequestParams params = new RequestParams(Constants.JWD);
        params.addParameter("id",id);
        params.addParameter("userid",userid);
        params.addParameter("name",name);
        params.addParameter("lon",lon);
        params.addParameter("lat",lat);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context,"失败QvQ~",Toast.LENGTH_SHORT).show();
                L.v("locationManager!",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


//    public void GetWWCGD(final Context context,int pageNo,String fapgperson,
//                         int orderstates)throws JSONException{
//
//        RequestParams params = new RequestParams(Constants.AUDITED_POWER_WORK);
//        params.addParameter("pageNo", pageNo);
//        params.addParameter("pageSize", "10");
//        params.addParameter("fapgperson",fapgperson);
//        params.addParameter("orderStates",orderstates);
//
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                PowerWorkBean m = JSON.parseObject(result,PowerWorkBean.class);
//
//                ((PlanActivity)context).initList(m);
//                ((PlanActivity) context).progressDialog
//                        .dismiss();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                L.v("ResultDataFalse~~!!!!:",ex.getMessage());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }

//    public void GetFDRWLB(final Context context,int pageNo,String fapgperson,
//                         int orderstates)throws JSONException{
//
//        RequestParams params = new RequestParams(Constants.AUDITED_POWER_WORK);
//        params.addParameter("pageNo", pageNo);
//        params.addParameter("pageSize", "10");
//        params.addParameter("fapgperson",fapgperson);
//        params.addParameter("orderStates",orderstates);
//
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//                PowerWorkBeanMyImpl m = JSON.parseObject(result,PowerWorkBeanMyImpl.class);
//                List<PowerWork> data = m.getRows();
//
//                ((PlanListActivity)context).initList2(data,m.getTotalRows());
//                ((PlanListActivity) context).progressDialog
//                        .dismiss();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                L.v("ResultDataFalse~~!!!!:",ex.getMessage());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }








//    public void GetGDXQ(final Context context,int pageNo,String fapgperson,
//                          int orderstates)throws JSONException{
//
//        RequestParams params = new RequestParams(Constants.AUDITED_POWER_WORK);
//        params.addParameter("pageNo", pageNo);
//        params.addParameter("pageSize", "10");
//        params.addParameter("fapgperson",fapgperson);
//        params.addParameter("orderStates",orderstates);
//
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                JSONObject data = null;
//                JSONArray m = null;
//                try {
//                    data = new JSONObject(result);
//                    m = data.getJSONArray("rows");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                for(int i = 0;i < m.length();i++){
//
//                    PowerWork data1 = JSON.parseObject(m[i],PowerWork.class);
//                }
//                ((PlanDetailsActivity)context).initList(data1);
//                ((PlanDetailsActivity) context).progressDialog
//                        .dismiss();
//                L.v("ResultDataTrue!!~!!!!:",result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                L.v("ResultDataFalse~~!!!!:",ex.getMessage());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
    public void GDZP(final Context context, String pgperson){

        RequestParams params = new RequestParams(Constants.POWER_TRANSFER_PERSON);
        params.addParameter("pgperson",pgperson);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(context,"人员加载成功",Toast.LENGTH_SHORT).show();
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

//    public void RWSLWJ(final Context context, int pageNo, int pageSize, String fapgperson){
//
//        RequestParams params = new RequestParams(Constants.AUDITED_POWER_WORK);
//        params.addParameter("pageNo",pageNo);
//        params.addParameter("pageSize",pageSize);
//        params.addParameter("fapgperson",fapgperson);
//        params.addParameter("orderStates",2);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                JSONObject object ;
//                try {
//                    object = new JSONObject(result);
//                    String number = object.getString("totalRows");
////                    int numbercode = Integer.parseInt(number);
//                    ((PlanActivity)context).tv_work_wj_number.setText(number);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//    }

//    public void RWSLYJ(final Context context, int pageNo, int pageSize, String fapgperson){
//
//        RequestParams params = new RequestParams(Constants.AUDITED_POWER_WORK);
//        params.addParameter("pageNo",pageNo);
//        params.addParameter("pageSize",pageSize);
//        params.addParameter("fapgperson",fapgperson);
//        params.addParameter("orderStates",3);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                JSONObject object ;
//                try {
//                    object = new JSONObject(result);
//                    String number = object.getString("totalRows");
//                    ((PlanActivity)context).tv_work_yj_number.setText(number);
////                    int numbercode = Integer.parseInt(number);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//    }
//    public void RWSLZF(final Context context, int pageNo, int pageSize, String fapgperson){
//
//        RequestParams params = new RequestParams(Constants.AUDITED_POWER_WORK);
//        params.addParameter("pageNo",pageNo);
//        params.addParameter("pageSize",pageSize);
//        params.addParameter("fapgperson",fapgperson);
//        params.addParameter("orderStates",4);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                JSONObject object ;
//                try {
//                    object = new JSONObject(result);
//                    String number = object.getString("totalRows");
////                    int numbercode = Integer.parseInt(number);
//                    ((PlanActivity)context).tv_work_zf_number.setText(number);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//    }
//    public void RWSLYW(final Context context, int pageNo, int pageSize, String fapgperson){
//
//        RequestParams params = new RequestParams(Constants.AUDITED_POWER_WORK);
//        params.addParameter("pageNo",pageNo);
//        params.addParameter("pageSize",pageSize);
//        params.addParameter("fapgperson",fapgperson);
//        params.addParameter("orderStates",5);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                JSONObject object ;
//                try {
//                    object = new JSONObject(result);
//                    String number = object.getString("totalRows");
////                    int numbercode = Integer.parseInt(number);
//                    ((PlanActivity)context).tv_work_yw_number.setText(number);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//    }

//    public void RWSLGZWJ(final Context context, int pageNo, int pageSize, String fapgperson){
//
//        RequestParams params = new RequestParams(Constants.LOAD_FAULT_WORK);
//        params.addParameter("pageNo",pageNo);
//        params.addParameter("pageSize",pageSize);
//        params.addParameter("fapgperson",fapgperson);
//        params.addParameter("status",2);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                JSONObject object ;
//                try {
//                    object = new JSONObject(result);
//                    String number = object.getString("totalRows");
////                    int numbercode = Integer.parseInt(number);
//                    ((PlanActivity)context).tv_fault_wj_number.setText(number);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//    }
    private void FDGDZP(Context context,String url,int id,String orderid,int account,
                        int fapgperson){
        RequestParams params = new RequestParams();
        params.addParameter("url",url);
        params.addParameter("id",id);
        params.addParameter("orderid",orderid);
        params.addParameter("account",account);
        params.addParameter("fapgperson",fapgperson);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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
