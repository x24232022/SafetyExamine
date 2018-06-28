package com.avicsafety.safety_examine.lt.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.lt.activity.ltUtil.CTCSRestClientUsage;
import com.avicsafety.safety_examine.lt.activity.ltUtil.Constants;
import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.safety_examine.model.MgdxqLT;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Field;

/**
 * Created by 刘畅 on 2017/7/27.
 */
@ContentView(R.layout.n_activity_gdxq_yd)
public class GDShowAndDealWithNoFildActivity extends BaseActivity implements View.OnClickListener{

    private String id;
    private int taskType = 0;
    private MUser mu;
    public MyProgressDialog progressDialog;

    public MgdxqLT getM() {
        return m;
    }

    public void setM(MgdxqLT m) {
        this.m = m;
    }

    private MgdxqLT m;



    // 显示按钮的控制
    private void initBtns() {
        // TODO 自动生成的方法存根

        // 新建=============

        // 签收:工单状态status为新建时显示按钮为（signInDate 为空时显示）
        // 派发:（signInDate 为不空时显示）
        // 驳回:（signInDate 为空且isReject为true时显示）
        // 转派:（预留，正式应用暂不显示）

        // 待处理============

        // 处理、协同、共同、退回:状态为处理中时显示按钮为

        // 协同验证==========

        // 处理:状态为协同验证时显示按钮为

        // 共同到站==========

        // 共同到站:状态为共同到站时但仅可以查看

        // 待审核============

        // 关闭、重新处理:状态为已解决时显示为

        reSetAllBtns();// 重置按钮状态
        if (m.getStatus().equals("新建") && TextUtils.isEmpty(m.getSignInDate())) {
            // 签收
            btn_qs.setVisibility(View.VISIBLE);
        }

        Log.e("Msg", m.getIsReject() + "");
        Log.e("Msg", m.getStatus() + "");
        Log.e("Msg", m + "");

        if (m != null // 驳回
                && m.getStatus() != null
                && m.getIsReject() != null
                && m.getStatus().equals("新建")
                && Constants.getSubmitUser(oThis).equals("sfjzjk")
                && (TextUtils.isEmpty(m.getIsReject()) || m.getIsReject().equals("true"))
                ) {
            btn_bh.setVisibility(View.VISIBLE);
        }

        if (m.getStatus().equals("新建") && !TextUtils.isEmpty(m.getSignInDate())) {
            // 派发
            btn_pf.setVisibility(View.VISIBLE);
        }

        if (m.getStatus().equals("新建") && TextUtils.isEmpty(m.getSignInDate())
                && !m.getLocation().equals("4")
                && TextUtils.isEmpty(m.getTransferDate())) {
            // 转派
            btn_zp.setVisibility(View.VISIBLE);
        }

        if (m.getStatus().equals("处理中")) {
            // 处理，退回，验证，到站
            btn_cl.setVisibility(View.VISIBLE);
            btn_th.setVisibility(View.VISIBLE);


            if (m != null && m.getStatus() != null
                    && m.getStatus().equals("处理中")) {
                btn_yz.setVisibility(View.GONE);
            }


            if (m != null
                    && m.getStatus() != null
                    && (TextUtils.isEmpty(m.getIsReject()) || m.getIsReject()
                    .equals("false"))) {
                btn_yz.setVisibility(View.GONE);
                btn_dd.setVisibility(View.VISIBLE);
            }


        }

        if (m.getStatus().equals("已解决")) {
            // 重处理，关闭
            btn_ccl.setVisibility(View.VISIBLE);
            btn_gb.setVisibility(View.VISIBLE);
        }

        if (m.getStatus().equals("协同验证")) {
            // 处理
            // btn_cl.setVisibility(View.VISIBLE);
        }

        if (m.getStatus().equals("共同到站")) {

            // 仅供查看，无操作
        }

        switch (taskType) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 9:
                btn_qs.setVisibility(View.GONE);
                btn_pf.setVisibility(View.GONE);
                btn_cl.setVisibility(View.GONE);
                btn_ccl.setVisibility(View.GONE);
                btn_bh.setVisibility(View.GONE);
                btn_zp.setVisibility(View.GONE);
                btn_th.setVisibility(View.GONE);
                btn_gb.setVisibility(View.GONE);
                btn_yz.setVisibility(View.GONE);
                btn_dd.setVisibility(View.GONE);
                ll_btns.setVisibility(View.GONE);
                break;
        }

    }


    public void initData() {
        // TODO 自动生成的方法存根

        ll_content.removeAllViewsInLayout();

        progressDialog = new MyProgressDialog(oThis, "获取中..");

        try {
            new CTCSRestClientUsage().GetGDXQ(oThis, id);

        } catch (JSONException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.towermain, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                initData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("工单详情");
        id = getIntent().getStringExtra("id");
        taskType = getIntent().getIntExtra("taskType", 0);
        mu = Constants.getUserInfo(oThis);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();

        btn_qs.setOnClickListener(this);
        btn_pf.setOnClickListener(this);
        btn_cl.setOnClickListener(this);
        btn_ccl.setOnClickListener(this);
        btn_bh.setOnClickListener(this);
        btn_zp.setOnClickListener(this);
        btn_th.setOnClickListener(this);
        btn_gb.setOnClickListener(this);
        btn_yz.setOnClickListener(this);
        btn_dd.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        // TODO 自动生成的方法存根

        switch (v.getId()) {
            case R.id.btn_qs:

                progressDialog = new MyProgressDialog(oThis, "签收..");

                try {
                    new CTCSRestClientUsage().dealWithNoFild(oThis, id,
                            Constants.GDQS);

                } catch (JSONException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }

                break;
            case R.id.btn_pf:
                startActivity(new Intent(oThis, GDDealWithOneFileActivity.class)
                        .putExtra("id", id).putExtra("opFlag", Constants.GDPF));
                this.finish();
                break;
            case R.id.btn_cl:
                startActivity(new Intent(oThis, GDDealWithMultiFildsActivity.class)
                        .putExtra("id", id)
                        .putExtra("opFlag", Constants.GDCL)
                        .putExtra("Location", m.getLocation())
                        //.putExtra("teleAlarmreaanalysis",m.getTeleAlarmreaanalysis())
                        //.putExtra("teleSubAlarmreaanalysis", m.getTeleSubAlarmreaanalysis())
                        .putExtra("alarmrealist", m.getAlarmrealist())
                        .putExtra("resprocess", m.getResprocess())
                        .putExtra("specialist", m.getSpecialist()));
                this.finish();
                break;

            case R.id.btn_ccl:
                progressDialog = new MyProgressDialog(oThis, "重新处理..");

                try {
                    new CTCSRestClientUsage().dealWithNoFild(oThis, id,
                            Constants.GDCCL);
                } catch (JSONException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                this.finish();
                break;
            case R.id.btn_bh:
                startActivity(new Intent(oThis, GDDealWithOneFileActivity.class)
                        .putExtra("id", id).putExtra("opFlag", Constants.GDBH));
                this.finish();
                break;
            case R.id.btn_zp:
                startActivity(new Intent(oThis, GDDealWithOneFileActivity.class)
                        .putExtra("id", id).putExtra("opFlag", Constants.GDZP));
                this.finish();
                break;
            case R.id.btn_th:
                startActivity(new Intent(oThis, GDDealWithOneFileActivity.class)
                        .putExtra("id", id).putExtra("opFlag", Constants.GDTH));
                this.finish();
                break;
            case R.id.btn_yz:
                startActivity(new Intent(oThis, GDDealWithOneFileActivity.class)
                        .putExtra("id", id).putExtra("opFlag", Constants.GDYZ));
                break;

            case R.id.btn_dd:
                startActivity(new Intent(oThis, GDGTDZActivity.class).putExtra(
                        "id", id).putExtra("opFlag", Constants.GDDZ));
                this.finish();
                break;

            case R.id.btn_gb:

                progressDialog = new MyProgressDialog(oThis, "工单关闭..");

                try {
                    new CTCSRestClientUsage().dealWithNoFild(oThis, id,
                            Constants.GDGB);
                } catch (JSONException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }

                break;

        }

    }


    @Override
    protected void onResume() {
        // TODO 自动生成的方法存根

        super.onResume();
        initData();
    }

    private void reSetAllBtns() {
        // TODO 自动生成的方法存根

        btn_qs.setVisibility(View.GONE);
        btn_pf.setVisibility(View.GONE);
        btn_cl.setVisibility(View.GONE);
        btn_ccl.setVisibility(View.GONE);
        btn_bh.setVisibility(View.GONE);
        btn_zp.setVisibility(View.GONE);
        btn_th.setVisibility(View.GONE);
        btn_gb.setVisibility(View.GONE);
    }

    public void ShowGDInfo(MgdxqLT m) {
        // TODO 自动生成的方法存根

        this.setM(m);

        Field[] field = m.getClass().getDeclaredFields();

        for (int i = 0; i < field.length; i++) {

            View view = LayoutInflater.from(oThis).inflate(
                    R.layout.n_gdxq_item, null);

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_value = (TextView) view.findViewById(R.id.tv_value);

            switch (i) {
                case 0:
                    // tv_name.setText("工单主键（仅用于后台操作、不显示）");
                    // tv_value.setText("id");
                    // ll_content.addView(view);
                    break;
                case 1:
//				tv_name.setText("地市");
//				tv_value.setText(m.getLocation());
//				ll_content.addView(view);
                    break;
                case 2:
//				tv_name.setText("是否可驳回");
//				tv_value.setText(m.getIsReject());
//				ll_content.addView(view);
                    break;
                case 3:
                    tv_name.setText("工单流水号");
                    tv_value.setText(m.getNo());
                    ll_content.addView(view);
                    break;
                case 4:
                    tv_name.setText("状态");
                    tv_value.setText(m.getStatus());
                    ll_content.addView(view);
                    break;
                case 5:
                    tv_name.setText("所属运行商");
                    tv_value.setText(m.getBelongs());
                    ll_content.addView(view);
                    break;
                case 6:
                    tv_name.setText("专业");
                    tv_value.setText(m.getSpecialist());
                    ll_content.addView(view);
                    break;
                case 7:
                    tv_name.setText("告警发生地市");
                    tv_value.setText(m.getAlarmlocation());
                    ll_content.addView(view);
                    break;
                case 8:
                    tv_name.setText("故障主题");
                    tv_value.setText(m.getFaulttitle());
                    ll_content.addView(view);
                    break;
                case 9:
                    tv_name.setText("故障类型");
                    tv_value.setText(m.getAlarmtype());
                    ll_content.addView(view);
                    break;
                case 10:
                    tv_name.setText("告警设备编号");
                    tv_value.setText(m.getAlarmdevserno());
                    ll_content.addView(view);
                    break;
                case 11:
                    tv_name.setText("网元名称");
                    tv_value.setText(m.getBak1());
                    ll_content.addView(view);
                    break;
                case 12:
                    tv_name.setText("告警发生时间");
                    tv_value.setText(m.getAlarmtime());
                    ll_content.addView(view);
                    break;
                case 13:
                    tv_name.setText("网络级别");
                    tv_value.setText(m.getAlarmlevel());
                    ll_content.addView(view);
                    break;
                case 14:
                    tv_name.setText("故障现象描述");
                    tv_value.setText(m.getFailuredescription());
                    ll_content.addView(view);
                    break;
                case 15:
                    tv_name.setText("手工记录故障恢复时间");
                    tv_value.setText(m.getFaultrectime());
                    ll_content.addView(view);
                    break;
                case 16:
                    tv_name.setText("系统记录故障恢复时间");
                    tv_value.setText(m.getFaultautorectime());
                    ll_content.addView(view);
                    break;
                case 17:
                    tv_name.setText("结果");
                    tv_value.setText(m.getResults());
                    ll_content.addView(view);
                    break;
                case 18:
                    tv_name.setText("是否节点站");
                    tv_value.setText(m.getOperation_bak3());
                    ll_content.addView(view);
                    break;
                case 19:
                    tv_name.setText("处理专业");
                    tv_value.setText(m.getHanprofessional());
                    ll_content.addView(view);
                    break;
                case 20:
                    tv_name.setText("故障原因 ");
                    tv_value.setText(m.getAlarmreaanalysis());
                    ll_content.addView(view);
                    break;
                case 21:
                    tv_name.setText("故障原因说明");
                    tv_value.setText(m.getAlarmrealist());
                    ll_content.addView(view);
                    break;
                case 22:
                    tv_name.setText("解决过程");
                    tv_value.setText(m.getResprocess());
                    ll_content.addView(view);
                    break;
                case 23:
                    tv_name.setText("历次工单退回记录");
                    tv_value.setText(m.getRejectreason());
                    ll_content.addView(view);
                    break;
                case 24:
                    tv_name.setText("本次退回原因");
                    tv_value.setText(m.getRejectreason2());
                    ll_content.addView(view);
                    break;
                case 25:
                    tv_name.setText("创建时间");
                    tv_value.setText(m.getSla_createtime());
                    ll_content.addView(view);
                    break;
                case 26:
                    tv_name.setText("分配时间");
                    tv_value.setText(m.getSla_assignetime());
                    ll_content.addView(view);
                    break;
                case 27:
                    tv_name.setText("解决时间");
                    tv_value.setText(m.getSla_resolutiontime());
                    ll_content.addView(view);
                    break;
                case 28:
                    tv_name.setText("关闭时间");
                    tv_value.setText(m.getSla_closetime());
                    ll_content.addView(view);
                    break;
                case 29:
                    tv_name.setText("指定处理人");
                    tv_value.setText(m.getLineMaintenanceName());
                    ll_content.addView(view);
                    break;
                case 30:
                    tv_name.setText("驳回原因");
                    tv_value.setText(m.getRejectContent());
                    ll_content.addView(view);
                    break;
                default:
                    break;

            }
            initBtns();
        }
    }

    @ViewInject(R.id.btn_qs)
    private Button btn_qs;
    @ViewInject(R.id.btn_pf)
    private Button btn_pf;
    @ViewInject(R.id.btn_cl)
    private Button btn_cl;
    @ViewInject(R.id.btn_ccl)
    private Button btn_ccl;
    @ViewInject(R.id.btn_bh)
    private Button btn_bh;
    @ViewInject(R.id.btn_zp)
    private Button btn_zp;
    @ViewInject(R.id.btn_th)
    private Button btn_th;
    @ViewInject(R.id.btn_gb)
    private Button btn_gb;
    @ViewInject(R.id.btn_yz)
    private Button btn_yz;
    @ViewInject(R.id.btn_dd)
    private Button btn_dd;
    @ViewInject(R.id.ll_content)
    private LinearLayout ll_content;
    @ViewInject(R.id.ll_btns)
    private LinearLayout ll_btns;
}
