package com.avicsafety.NewShenYangTowerComService.dx.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.NewShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.NewShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.NewShenYangTowerComService.dx.activity.dxUtil.CTCSRestClientUsage;
import com.avicsafety.NewShenYangTowerComService.dx.activity.dxUtil.Constants;
import com.avicsafety.NewShenYangTowerComService.model.MUser;
import com.avicsafety.NewShenYangTowerComService.model.Mgdxq;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Field;

/**
 * Created by 刘畅 on 2017/7/25.
 */
@ContentView(R.layout.n_activity_gdxq)
public class GDShowAndDealWithNoFildActivity extends BaseActivity implements View.OnClickListener{
    public MyProgressDialog progressDialog;
    private String id;
    private MUser mu;
    private int taskType = 0;

    public Mgdxq getM() {
        return m;
    }

    public void setM(Mgdxq m) {
        this.m = m;
    }

    private Mgdxq m;






    // 显示按钮的控制
    private void initBtns() {
        // TODO 自动生成的方法存根

        // 1签收 、驳回 :状态为新建时 签收 时间==null或==“”
        // 2派发：状态为新建时 签收 时间！=null且!=“”
        // 3转派：状态为新建时 （签收 时间==null 或 ==“”） 且location 不等于 4 (沈阳) 且(transferDate
        // == null或
        // transferDate==“”)
        // 4工单处理、退回工单：状态为处理中
        // 5重新处理、关闭工单：状态为已解决

        reSetAllBtns();// 重置按钮状态
        if (m.getStatus().equals("新建") && TextUtils.isEmpty(m.getSignInDate())) {
            // 签收
            // 驳回
            btn_qs.setVisibility(View.VISIBLE);
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
            // 处理，退回
            btn_cl.setVisibility(View.VISIBLE);
            btn_th.setVisibility(View.VISIBLE);

        }

        if (m.getStatus().equals("已解决")) {
            // 重处理，关闭
            btn_ccl.setVisibility(View.VISIBLE);
            btn_gb.setVisibility(View.VISIBLE);
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
                ll_btns.setVisibility(View.GONE);
                break;

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

    public void initData() {
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
    }


    @Override
    protected void onResume() {
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


    @Override
    public void onClick(View v) {
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
                        .putExtra("id", id).putExtra("opFlag", Constants.GDCL)
                        .putExtra("location", m.getLocation()));
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
            case R.id.btn_gb:

                progressDialog = new MyProgressDialog(oThis, "工单关闭..", true);

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




    public void ShowGDInfo(Mgdxq m) {
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
                    tv_name.setText("故障主题");
                    tv_value.setText(m.getFaulttitle());
                    ll_content.addView(view);
                    break;
                case 2:
                    tv_name.setText("工单流水号");
                    tv_value.setText(m.getNo());
                    ll_content.addView(view);
                    break;
                case 3:
                    tv_name.setText("所属运营商");
                    tv_value.setText(m.getBelongs());
                    ll_content.addView(view);
                    break;
                case 4:
                    tv_name.setText("工单状态");
                    tv_value.setText(m.getStatus());
                    ll_content.addView(view);
                    break;
                case 5:
                    tv_name.setText("告警设备编号");
                    tv_value.setText(m.getAlarmdevserno());
                    ll_content.addView(view);
                    break;
                case 6:
                    tv_name.setText("告警设备类型");
                    tv_value.setText(m.getAlarmdevtype());
                    ll_content.addView(view);
                    break;
                case 7:
                    tv_name.setText("告警发生时间");
                    tv_value.setText(m.getAlarmtime());
                    ll_content.addView(view);
                    break;
                case 8:
                    tv_name.setText("故障现象描述");
                    tv_value.setText(m.getFailuredescription());
                    ll_content.addView(view);
                    break;
                case 9:
                    tv_name.setText("网元名称");
                    tv_value.setText(m.getBak1());
                    ll_content.addView(view);
                    break;
                case 10:
                    tv_name.setText("告警源");
                    tv_value.setText(m.getAlarmsource());
                    ll_content.addView(view);
                    break;
                case 11:
                    tv_name.setText("电信故障流水号");
                    tv_value.setText(m.getSerialNo());
                    ll_content.addView(view);
                    break;
                case 12:
                    tv_name.setText("是否主动发现故障");
                    tv_value.setText(m.getIsfind());
                    ll_content.addView(view);
                    break;
                case 13:
                    tv_name.setText("网络设备级别");
                    tv_value.setText(m.getEquipLevel());
                    ll_content.addView(view);
                    break;
                case 14:
                    tv_name.setText("申告时间");
                    tv_value.setText(m.getOriginTime());
                    ll_content.addView(view);
                    break;
                case 15:
                    tv_name.setText("设备维护单位");
                    tv_value.setText(m.getMaintainUnit());
                    ll_content.addView(view);
                    break;
                case 16:
                    tv_name.setText("故障处理时限(小时)");
                    tv_value.setText(m.getProcessLimit());
                    ll_content.addView(view);
                    break;
                case 17:
                    tv_name.setText("工作内容");
                    tv_value.setText(m.getWorkContent());
                    ll_content.addView(view);
                    break;
                case 18:
                    tv_name.setText("对端新建时间");
                    tv_value.setText(m.getOppositeCreateDate());
                    ll_content.addView(view);
                    break;
                case 19:
                    tv_name.setText("申告来源");
                    tv_value.setText(m.getOrigin());
                    ll_content.addView(view);
                    break;
                case 20:
                    tv_name.setText("工单发起人归属部门");
                    tv_value.setText(m.getInitiatorDepartment());
                    ll_content.addView(view);
                    break;
                case 21:
                    tv_name.setText("工单发起人电话");
                    tv_value.setText(m.getInitiatorTel());
                    ll_content.addView(view);
                    break;
                case 22:
                    tv_name.setText("工单发起人");
                    tv_value.setText(m.getInitiator());
                    ll_content.addView(view);
                    break;
                case 23:
                    tv_name.setText("追加内容");
                    tv_value.setText(m.getAppContent());
                    ll_content.addView(view);
                    break;
                case 24:
                    tv_name.setText("追加人");
                    tv_value.setText(m.getAppPerson());
                    ll_content.addView(view);
                    break;
                case 25:
                    tv_name.setText("追加人电话");
                    tv_value.setText(m.getAppPersonTel());
                    ll_content.addView(view);
                    break;
                case 26:
                    tv_name.setText("追加时间");
                    tv_value.setText(m.getAppDate());
                    ll_content.addView(view);
                    break;
                case 27:
                    tv_name.setText("催单时间");
                    tv_value.setText(m.getUrgeDate());
                    ll_content.addView(view);
                    break;
                case 28:
                    tv_name.setText("催单人电话");
                    tv_value.setText(m.getUrgePersonTel());
                    ll_content.addView(view);
                    break;
                case 29:
                    tv_name.setText("催单人");
                    tv_value.setText(m.getUrgePerson());
                    ll_content.addView(view);
                    break;
                case 30:
                    tv_name.setText("催单内容");
                    tv_value.setText(m.getUrgeContent());
                    ll_content.addView(view);
                    break;
                case 31:
                    tv_name.setText("责任地市");
                    tv_value.setText(m.getDutyLocation());
                    ll_content.addView(view);
                    break;
                case 32:
                    tv_name.setText("原因分类");
                    tv_value.setText(m.getTeleAlarmreaanalysis());
                    ll_content.addView(view);
                    break;
                case 33:
                    tv_name.setText("原因细分");
                    tv_value.setText(m.getTeleSubAlarmreaanalysis());
                    ll_content.addView(view);
                    break;
                case 34:
                    tv_name.setText("责任方");
                    tv_value.setText(m.getDutyBelong());
                    ll_content.addView(view);
                    break;
                case 35:
                    tv_name.setText("本次退回原因");
                    tv_value.setText(m.getRejectreason2());
                    ll_content.addView(view);
                    break;
                case 36:
                    tv_name.setText("历次工单退回记录");
                    tv_value.setText(m.getRejectreason());
                    ll_content.addView(view);
                    break;
                case 37:
                    // tv_name.setText("转派时间（后台字段不显示）");
                    // tv_value.setText(m.gettransferDate);
                    // ll_content.addView(view);
                    break;
                case 38:
                    tv_name.setText("是否有遗留问题");
                    tv_value.setText(m.getIsAnyQuestion());
                    ll_content.addView(view);
                    break;
                case 39:
                    tv_name.setText("资源是否调整");
                    tv_value.setText(m.getIsAdjustment());
                    ll_content.addView(view);
                    break;
                case 40:
                    tv_name.setText("故障恢复情况");
                    tv_value.setText(m.getTroubRecoverCondition());
                    ll_content.addView(view);
                    break;
                case 41:
                    tv_name.setText("故障地点");
                    tv_value.setText(m.getTroubAddress());
                    ll_content.addView(view);
                    break;
                case 42:
                    tv_name.setText("影响业务");
                    tv_value.setText(m.getScopeextent());
                    ll_content.addView(view);
                    break;
                case 43:
                    tv_name.setText("故障处理过程描述");
                    tv_value.setText(m.getResprocess());
                    ll_content.addView(view);
                    break;
                case 44:
                    tv_name.setText("创建时间");
                    tv_value.setText(m.getSla_createtime());
                    ll_content.addView(view);
                    break;
                case 45:
                    tv_name.setText("分配时间");
                    tv_value.setText(m.getSla_assignetime());
                    ll_content.addView(view);
                    break;
                case 46:
                    tv_name.setText("解决时间");
                    tv_value.setText(m.getSla_resolutiontime());
                    ll_content.addView(view);
                    break;
                case 47:
                    tv_name.setText("关闭时间");
                    tv_value.setText(m.getSla_closetime());
                    ll_content.addView(view);
                    break;

                default:
                    break;

            }

            initBtns();

        }

    }









    @ViewInject(R.id.ll_content)
    private LinearLayout ll_content;
    @ViewInject(R.id.ll_btns)
    private LinearLayout ll_btns;
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
}
