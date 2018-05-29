package com.avicsafety.NewShenYangTowerComService.xfd;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.NewShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.NewShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.NewShenYangTowerComService.activity.ChangeOneActivity;
import com.avicsafety.NewShenYangTowerComService.model.MUser;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 刘畅 on 2017/12/26.
 * 任务条数页
 */

@ContentView(R.layout.activity_xin_power_work)
public class PlanActivityXin extends BaseActivity implements View.OnClickListener {
    private MUser userAccoutn;
    public MyProgressDialog progressDialog;

    private String userid = "boot";


    public static void startPlanActivityXin(Activity activity,String tickid){
        Intent intent = new Intent(activity, PlanActivityXin.class);
        intent.putExtra("ticketid", tickid);
        activity.startActivity(intent);
    }
    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        userAccoutn = com.avicsafety.NewShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis);

        laodata();
        setTitle("任务数量");
        tv_xin_work_wj.setOnClickListener(this);
        tv_xin_work_ydz.setOnClickListener(this);
        tv_xin_work_yjs.setOnClickListener(this);
        tv_xin_work_ycf.setOnClickListener(this);


    }

    @Override
    protected void InitializeData() {
        super.InitializeData();
    }

    public void laodata() {
        new XinFDMethod().GetXinFdPlanRWSL(oThis,userAccoutn.getUserName(),2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressDialog = new MyProgressDialog(oThis, "获取中..");
        laodata();
    }

    public void onClick(View v) {
        Intent intent = new Intent(oThis,PlanListActivityXin.class);
        Intent intent1 = new Intent(oThis,InquireActivity.class);
        switch (v.getId()) {
            case R.id.tv_xin_work_wj:
                intent.putExtra("listtype",0);
                startActivity(intent);
                oThis.finish();
                break;
            case R.id.tv_xin_work_ycf:
                intent.putExtra("listtype",1);
                startActivity(intent);
                oThis.finish();
                break;
            case R.id.tv_xin_work_ydz:
                intent.putExtra("listtype",2);
                startActivity(intent);
                oThis.finish();
                break;
            case R.id.tv_xin_work_yjs:
                intent.putExtra("listtype",3);
                startActivity(intent);
                oThis.finish();
                break;

            default:
                break;
        }

    }







    @ViewInject(R.id.tv_xin_work_wj)
    private TextView tv_xin_work_wj;
    @ViewInject(R.id.tv_xin_work_ycf)
    private TextView tv_xin_work_ycf;
    @ViewInject(R.id.tv_xin_work_ydz)
    private TextView tv_xin_work_ydz;
    @ViewInject(R.id.tv_xin_work_yjs)
    private TextView tv_xin_work_yjs;

    @ViewInject(R.id.tv_xin_work_wj_number)
    public TextView tv_xin_work_wj_number;
    @ViewInject(R.id.tv_xin_work_ycf_number)
    public TextView tv_xin_work_ycf_number;
    @ViewInject(R.id.tv_xin_work_ydz_number)
    public TextView tv_xin_work_ydz_number;
    @ViewInject(R.id.tv_xin_work_yjs_number)
    public TextView tv_xin_work_yjs_number;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ChangeOneActivity.class));
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlanActivityXin.this.finish();
    }
}
