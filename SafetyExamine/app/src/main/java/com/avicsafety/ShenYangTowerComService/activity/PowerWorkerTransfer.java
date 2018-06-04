package com.avicsafety.ShenYangTowerComService.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Constants;
import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.ToastUtils;
import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Utils;
import com.avicsafety.ShenYangTowerComService.PowerManager.push.presenter.SubAddressPresenter;
import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.model.PowerWork;
import com.ydtx.powermanger.Module.BaseModule;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘畅 on 2017/8/28.
 */
@ContentView(R.layout.work_transfer)
public class PowerWorkerTransfer extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.sp_person)
    Spinner sp_person;
    @ViewInject(R.id.et_remark)
    EditText et_remark;
    @ViewInject(R.id.btn_transfer)
    Button btn_btn_transfer;
    private BaseModule.SubAddress subs;//公共服务器访问类
    private String userAccount;//账号
    private PowerWork pw=new PowerWork();
    private int flag=1;
    private String path;
    private List<String> names=new ArrayList<String>();
    private List<String> accounts=new ArrayList<String>();
    public MyProgressDialog progressDialog;


    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("工单转派");
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        btn_btn_transfer.setOnClickListener(this);
        subs= SubAddressPresenter.getInstance();
//        userAccount= Utils.readOAuth(this).getAccount();
        if(getIntent()!=null){
            Bundle bund=getIntent().getBundleExtra("bund");
            pw=(PowerWork) bund.getSerializable("pw");
        }
        RequestParams params = new RequestParams(Constants.POWER_TRANSFER_PERSON);
        params.addParameter("pgperson",pw.getPgperson());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(flag==1){
                    //加载人员信息
                    parseUser(result);
                }else{
                    ToastUtils.showToast(oThis, "操作成功!");
                    setResult(200);
                    finish();
                }
                Utils.hideProgress(oThis);
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
    void parseUser(String response){
        try {
            JSONObject json = new JSONObject(response);
            JSONArray jar = json.getJSONArray("rows");
            if(jar.length()>0){
                names.clear();
                accounts.clear();
                names.add("请选择");
                accounts.add("请选择");
                for (int i = 0; i <jar.length(); i++) {
                    JSONObject job=jar.getJSONObject(i);
                    names.add(job.getString("staffName"));
                    accounts.add(job.getString("staffNum"));
                }
            }else{
                names.clear();
                accounts.clear();
                names.add("请选择");
                accounts.add("无可选人员");
            }
        } catch (Exception e) {
            names.clear();
            accounts.clear();
            names.add("请选择");
            accounts.add("无可选人员");
        }
        Utils.loadSpinner(oThis, sp_person, names);
    }
    @Event(R.id.btn_transfer) void onClick(){

    }

    @Override
    public void onClick(View v) {
        flag=2;
        String name=Utils.readOAuth(oThis).getName();
        String fapgperson=sp_person.getSelectedItem().toString();
        if(TextUtils.isEmpty(fapgperson)){
            ToastUtils.showToast(oThis, "人员加载失败,请重新尝试!");
            return;
        }
        if(fapgperson.equals("请选择")){
            ToastUtils.showToast(oThis, "请选择转派人员!");
            return;
        }
        int index=sp_person.getSelectedItemPosition();
        if(!name.equals(fapgperson)){
            path=Constants.POWER_TRANSFER_WORK;
            if(!TextUtils.isEmpty(pw.getFaulttime())){
                path=Constants.FAULT_TRANSFER_WORK;
            }
            RequestParams params = new RequestParams(path);

            params.addParameter("id",pw.getId()+"");
            params.addParameter("orderid",pw.getOrderid());
            //params.put("account",userAccount);
            //params.put("name",name);
            params.addParameter("account",accounts.get(index));
            params.addParameter("fapgperson",accounts.get(index));
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Toast.makeText(oThis,"派发成功!",Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Toast.makeText(oThis,"派发失败请仔细检查!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }else{
            ToastUtils.showToast(oThis, "您已经是该工单负责人");
        }
    }
}
