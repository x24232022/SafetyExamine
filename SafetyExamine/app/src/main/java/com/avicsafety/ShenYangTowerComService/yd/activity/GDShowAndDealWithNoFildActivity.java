package com.avicsafety.ShenYangTowerComService.yd.activity;

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

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.ShenYangTowerComService.activity.FileScActivity;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.model.MgdxqN;
import com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.CTCSRestClientUsage;
import com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Constants;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Field;

/**
 * Created by 刘畅 on 2017/7/20.
 */
@ContentView(R.layout.n_activity_gdxq_yd)
public class GDShowAndDealWithNoFildActivity extends BaseActivity implements View.OnClickListener{
    public MyProgressDialog progressDialog;

    public MgdxqN getM() {
        return m;
    }

    public void setM(MgdxqN m) {
        this.m = m;
    }

    private MgdxqN m;
    private String id;
    private MUser mu;
    private int taskType = 0;



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

        if (m.getStatus().equals("处理中")&&m.getSignProcessDate().equals("")) {
            // 处理，退回，验证，到站
            btn_clrqs.setVisibility(View.VISIBLE);
        }

        if(taskType == 1 && !m.getSignProcessDate().equals("")){
            btn_clrqs.setVisibility(View.GONE);
            btn_clrfk.setVisibility(View.VISIBLE);
            btn_th.setVisibility(View.VISIBLE);
        }
        if(taskType == 1 &&!m.getProcessBakDate().equals("")){
            btn_clrfk.setVisibility(View.GONE);
            btn_cl.setVisibility(View.VISIBLE);
            btn_th.setVisibility(View.VISIBLE);
            btn_scfj.setVisibility(View.VISIBLE);
        }
        if (taskType ==1 && (TextUtils.isEmpty(m.getIsReject()) || m.getIsReject()
                .equals("false"))) {
            btn_yz.setVisibility(View.GONE);
            btn_dd.setVisibility(View.VISIBLE);
        }


        if(taskType==1){
            btn_dbfk.setVisibility(View.GONE);
            btn_gddb.setVisibility(View.GONE);
            btn_cbfk.setVisibility(View.GONE);
            btn_gdcb.setVisibility(View.GONE);
        }



        if (taskType == 2 && m.getStatus().equals("已解决")) {
            // 重处理，关闭
            btn_ccl.setVisibility(View.VISIBLE);
            btn_gb.setVisibility(View.VISIBLE);
            btn_cl.setVisibility(View.GONE);
            btn_th.setVisibility(View.GONE);
            btn_scfj.setVisibility(View.GONE);
        }

        if (m.getStatus().equals("协同验证")) {
            // 处理
            // btn_cl.setVisibility(View.VISIBLE);
        }

        if (m.getStatus().equals("共同到站")) {

            // 仅供查看，无操作
        }

        if(taskType == 7&&m.getIsNeedSupervise().equals("是")&&m.getSuperviseDate().equals("")){
            btn_gddb.setVisibility(View.VISIBLE);
            btn_cbfk.setVisibility(View.GONE);
            btn_gdcb.setVisibility(View.GONE);
            btn_clrfk.setVisibility(View.GONE);
            btn_clrqs.setVisibility(View.GONE);
            btn_dbfk.setVisibility(View.GONE);
            btn_scfj.setVisibility(View.GONE);
            btn_qs.setVisibility(View.GONE);
            btn_pf.setVisibility(View.GONE);
            btn_cl.setVisibility(View.GONE);
            btn_ccl.setVisibility(View.GONE);
            btn_bh.setVisibility(View.GONE);
            btn_zp.setVisibility(View.GONE);
            btn_th.setVisibility(View.GONE);
            btn_gb.setVisibility(View.GONE);
            btn_ks.setVisibility(View.GONE);
        }
        if(taskType == 8&&m.getIsNeedSupervise().equals("是")&&m.getIsSupervise().equals("是")&&m.getSuperviseBakDate().equals("")
                ){
            btn_dbfk.setVisibility(View.VISIBLE);
            btn_gddb.setVisibility(View.GONE);
            btn_cbfk.setVisibility(View.GONE);
            btn_gdcb.setVisibility(View.GONE);
            btn_clrfk.setVisibility(View.GONE);
            btn_clrqs.setVisibility(View.GONE);
            btn_scfj.setVisibility(View.GONE);
            btn_qs.setVisibility(View.GONE);
            btn_pf.setVisibility(View.GONE);
            btn_cl.setVisibility(View.GONE);
            btn_ccl.setVisibility(View.GONE);
            btn_bh.setVisibility(View.GONE);
            btn_zp.setVisibility(View.GONE);
            btn_th.setVisibility(View.GONE);
            btn_gb.setVisibility(View.GONE);
            btn_ks.setVisibility(View.GONE);
        }
        if(taskType==5&&m.getIsNeedReminders().equals("是")&&m.getRemindersDate().equals("")){
            btn_gdcb.setVisibility(View.VISIBLE);
            btn_dbfk.setVisibility(View.GONE);
            btn_gddb.setVisibility(View.GONE);
            btn_cbfk.setVisibility(View.GONE);
            btn_clrfk.setVisibility(View.GONE);
            btn_clrqs.setVisibility(View.GONE);
            btn_scfj.setVisibility(View.GONE);
            btn_qs.setVisibility(View.GONE);
            btn_pf.setVisibility(View.GONE);
            btn_cl.setVisibility(View.GONE);
            btn_ccl.setVisibility(View.GONE);
            btn_bh.setVisibility(View.GONE);
            btn_zp.setVisibility(View.GONE);
            btn_th.setVisibility(View.GONE);
            btn_gb.setVisibility(View.GONE);
            btn_ks.setVisibility(View.GONE);
        }
        if(taskType==6&&m.getIsNeedReminders().equals("是")&&m.getIsReminders().equals("是")&&m.getRemindersBakDate().equals("")){
            btn_cbfk.setVisibility(View.VISIBLE);
            btn_dbfk.setVisibility(View.GONE);
            btn_gddb.setVisibility(View.GONE);
            btn_gdcb.setVisibility(View.GONE);
            btn_clrfk.setVisibility(View.GONE);
            btn_clrqs.setVisibility(View.GONE);
            btn_scfj.setVisibility(View.GONE);
            btn_qs.setVisibility(View.GONE);
            btn_pf.setVisibility(View.GONE);
            btn_cl.setVisibility(View.GONE);
            btn_ccl.setVisibility(View.GONE);
            btn_bh.setVisibility(View.GONE);
            btn_zp.setVisibility(View.GONE);
            btn_th.setVisibility(View.GONE);
            btn_gb.setVisibility(View.GONE);
            btn_ks.setVisibility(View.GONE);
        }


        switch (taskType) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 9:
                btn_scfj.setVisibility(View.GONE);
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
                btn_gdcb.setVisibility(View.GONE);
                btn_cbfk.setVisibility(View.GONE);
                btn_gddb.setVisibility(View.GONE);
                btn_clrqs.setVisibility(View.GONE);
                btn_clrfk.setVisibility(View.GONE);
                btn_dbfk.setVisibility(View.GONE);
                break;
        }

    }

    private void reSetAllBtns() {
        // TODO 自动生成的方法存根
        btn_scfj.setVisibility(View.GONE);
        btn_qs.setVisibility(View.GONE);
        btn_pf.setVisibility(View.GONE);
        btn_cl.setVisibility(View.GONE);
        btn_ccl.setVisibility(View.GONE);
        btn_bh.setVisibility(View.GONE);
        btn_zp.setVisibility(View.GONE);
        btn_th.setVisibility(View.GONE);
        btn_gb.setVisibility(View.GONE);
        btn_ks.setVisibility(View.GONE);
        btn_gdcb.setVisibility(View.GONE);
        btn_cbfk.setVisibility(View.GONE);
        btn_gddb.setVisibility(View.GONE);
        btn_dbfk.setVisibility(View.GONE);
        btn_clrqs.setVisibility(View.GONE);
        btn_clrfk.setVisibility(View.GONE);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();

        btn_scfj.setOnClickListener(this);
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
        btn_ks.setOnClickListener(this);
        btn_gdcb.setOnClickListener(this);
        btn_cbfk.setOnClickListener(this);
        btn_gddb.setOnClickListener(this);
        btn_dbfk.setOnClickListener(this);
        btn_clrfk.setOnClickListener(this);
        btn_clrqs.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("工单详情");
        id = getIntent().getStringExtra("id");
        taskType = getIntent().getIntExtra("taskType", 0);
        mu = Constants.getUserInfo(oThis);

        taskType = getIntent().getIntExtra("taskType",0);
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
            case R.id.btn_clrqs:

                progressDialog = new MyProgressDialog(oThis, "签收..");

                try {
                    new CTCSRestClientUsage().dealWithNoFild(oThis, id,
                            Constants.CLRQS);

                } catch (JSONException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                break;
            case R.id.btn_clrfk:
                startActivity(new Intent(oThis,GDDealWithOneFileActivity.class)
                        .putExtra("id",id).putExtra("opFlag",Constants.CLRFK));
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
                        .putExtra("location", m.getLocation())
                        .putExtra("teleAlarmreaanalysis",
                                m.getTeleAlarmreaanalysis())
                        .putExtra("teleSubAlarmreaanalysis",
                                m.getTeleSubAlarmreaanalysis())
                        .putExtra("alarmrealist", m.getAlarmrealist())
                        .putExtra("resprocess", m.getResprocess())
                        .putExtra("specialist", m.getSpecialist())
                        .putExtra("faultautorectime",m.getFaultautorectime())
                        .putExtra("faulttitle",m.getFaulttitle()));

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
                this.finish();
                break;

            case R.id.btn_dd:
                startActivity(new Intent(oThis, GDGTDZActivity.class)
                        .putExtra("id", id)
                        .putExtra("opFlag", Constants.GDDZ));
                break;

            case R.id.btn_scfj:
                startActivity(new Intent(oThis, FileScActivity.class)
                        .putExtra("id", id));
                break;
            case R.id.btn_gdcb:
                startActivity(new Intent(oThis,GDDealWithOneFileActivity.class)
                        .putExtra("id",id).putExtra("opFlag",Constants.GDCB));
                finish();
                break;
            case R.id.btn_cbfk:
                startActivity(new Intent(oThis,GDDealWithOneFileActivity.class)
                        .putExtra("id",id).putExtra("opFlag",Constants.CBFK));
                finish();
                break;
            case R.id.btn_gddb:
                startActivity(new Intent(oThis,GDDealWithOneFileActivity.class)
                        .putExtra("id",id).putExtra("opFlag",Constants.GDDB));
                finish();
                break;
            case R.id.btn_dbfk:
                startActivity(new Intent(oThis,GDDealWithOneFileActivity.class)
                        .putExtra("id",id).putExtra("opFlag",Constants.DBFK));
                finish();
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



    public void ShowGDInfo(MgdxqN m) {
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
                    tv_name.setText("告警标题");
                    tv_value.setText(m.getFaulttitle());
                    ll_content.addView(view);
                    break;
                case 2:
                    tv_name.setText("工单流水号");
                    tv_value.setText(m.getNo());
                    ll_content.addView(view);
                    break;
                case 3:
                    tv_name.setText("状态");
                    tv_value.setText(m.getStatus());
                    ll_content.addView(view);
                    break;
                case 4:
                    tv_name.setText("EOMS系统工单ID");
                    tv_value.setText(m.getSerialNo());
                    ll_content.addView(view);
                    break;
                case 5:
                    tv_name.setText("网管告警流水号");
                    tv_value.setText(m.getAlarmId());
                    ll_content.addView(view);
                    break;
                case 6:
                    tv_name.setText("网管告警ID");
                    tv_value.setText(m.getAlarmStaId());
                    ll_content.addView(view);
                    break;
                case 7:
                    tv_name.setText("原始告警号");
                    tv_value.setText(m.getOriAlarmId());
                    ll_content.addView(view);
                    break;
                case 8:
                    tv_name.setText("告警产生时间");
                    tv_value.setText(m.getAlarmtime());
                    ll_content.addView(view);
                    break;
                case 9:
                    tv_name.setText("告警设备类型");
                    tv_value.setText(m.getAlarmdevtype());
                    ll_content.addView(view);
                    break;
                case 10:
                    tv_name.setText("设备厂家");
                    tv_value.setText(m.getVendor());
                    ll_content.addView(view);
                    break;
                case 11:
                    tv_name.setText("设备型号");
                    tv_value.setText(m.getEquipType());
                    ll_content.addView(view);
                    break;
                case 12:
                    tv_name.setText("网元ID");
                    tv_value.setText(m.getTyneid());
                    ll_content.addView(view);
                    break;
                case 13:
                    tv_name.setText("网元名称");
                    tv_value.setText(m.getBak1());
                    ll_content.addView(view);
                    break;
                case 14:
                    tv_name.setText("告警级别");
                    tv_value.setText(m.getAlarmlevel());
                    ll_content.addView(view);
                    break;
                case 15:
                    tv_name.setText("告警逻辑分类");
                    tv_value.setText(m.getAlarmtype());
                    ll_content.addView(view);
                    break;
                case 16:
                    tv_name.setText("告警逻辑子类");
                    tv_value.setText(m.getAlarmSubType());
                    ll_content.addView(view);
                    break;
                case 17:
                    tv_name.setText("告警省份");
                    tv_value.setText(m.getAlarmProvince());
                    ll_content.addView(view);
                    break;
                case 18:
                    tv_name.setText("告警发生地市");
                    tv_value.setText(m.getAlarmlocation());
                    ll_content.addView(view);
                    break;
                case 19:
                    tv_name.setText("告警定位");
                    tv_value.setText(m.getOrientation());
                    ll_content.addView(view);
                    break;
                case 20:
                    tv_name.setText("所属运行商");
                    tv_value.setText(m.getBelongs());
                    ll_content.addView(view);
                    break;
                case 21:
                    tv_name.setText("告警处理响应级别");
                    tv_value.setText(m.getAlarmHandleLevel());
                    ll_content.addView(view);
                    break;
                case 22:
                    tv_name.setText("派单方式");
                    tv_value.setText(m.getCreateType());
                    ll_content.addView(view);
                    break;
                case 23:
                    tv_name.setText("所属区县");
                    tv_value.setText(m.getBelongsuburb());
                    ll_content.addView(view);
                    break;
                case 24:
                    tv_name.setText("基站属性");
                    tv_value.setText(m.getBtslevel());
                    ll_content.addView(view);
                    break;
                case 25:
                    tv_name.setText("告警对象别名");
                    tv_value.setText(m.getNodeCn());
                    ll_content.addView(view);
                    break;
                case 26:
                    tv_name.setText("告警发现时间");
                    tv_value.setText(m.getFindTime());
                    ll_content.addView(view);
                    break;
                case 27:
                    tv_name.setText("网元状态");
                    tv_value.setText(m.getAcknowledged());
                    ll_content.addView(view);
                    break;
                case 28:
                    tv_name.setText("告警描述");
                    tv_value.setText(m.getFailuredescription());
                    ll_content.addView(view);
                    break;
                case 29:
                    tv_name.setText("业务影响说明");
                    tv_value.setText(m.getAlarmServEffect());
                    ll_content.addView(view);
                    break;
                case 30:
                    tv_name.setText("告警智能处理");
                    tv_value.setText(m.getAlarmAutoHandle());
                    ll_content.addView(view);
                    break;
                case 31:
                    tv_name.setText("移动到站联系人");
                    tv_value.setText(m.getChinaMobileUser());
                    ll_content.addView(view);
                    break;
                case 32:
                    tv_name.setText("移动到站联系人电话");
                    tv_value.setText(m.getChinaMobileUserTel());
                    ll_content.addView(view);
                    break;
                case 33:
                    tv_name.setText("移动要求到站时间");
                    tv_value.setText(m.getChinaMobileTogetherDate());
                    ll_content.addView(view);
                    break;
                case 34:
                    tv_name.setText("转派人员");
                    tv_value.setText(m.getTransferUserName());
                    ll_content.addView(view);
                    break;
                case 35:
                    tv_name.setText("转派人员ID");
                    tv_value.setText(m.getTransferUserId());
                    ll_content.addView(view);
                    break;
                case 36:
                    tv_name.setText("转派人电话");
                    tv_value.setText(m.getTransferUserTel());
                    ll_content.addView(view);
                    break;
                case 37:
                    // tv_name.setText("转派时间（后台字段不显示）");
                    // tv_value.setText(m.gettransferDate);
                    // ll_content.addView(view);
                    break;
                case 38:
                    tv_name.setText("处理人 ");
                    tv_value.setText(m.getLineMaintenanceName());
                    ll_content.addView(view);
                    break;
                case 39:
                    tv_name.setText("处理人ID");
                    tv_value.setText(m.getLineMaintenanceNameid());
                    ll_content.addView(view);
                    break;
                case 40:
                    tv_name.setText("处理人电话");
                    tv_value.setText(m.getLineMaintenanceNametp());
                    ll_content.addView(view);
                    break;
                case 41:
                    tv_name.setText("手工记录故障恢复时间");
                    tv_value.setText(m.getFaultrectime());
                    ll_content.addView(view);
                    break;
                case 42:
                    tv_name.setText("系统自动恢复时间");
                    tv_value.setText(m.getFaultautorectime());
                    ll_content.addView(view);
                    break;
                case 43:
                    tv_name.setText("原因分类");
                    tv_value.setText(m.getTeleAlarmreaanalysis());
                    ll_content.addView(view);
                    break;
                case 44:
                    tv_name.setText("原因细分");
                    tv_value.setText(m.getTeleSubAlarmreaanalysis());
                    ll_content.addView(view);
                    break;
                case 45:
                    tv_name.setText("故障原因");
                    tv_value.setText(m.getAlarmrealist());
                    ll_content.addView(view);
                    break;
                case 46:
                    tv_name.setText("处理措施");
                    tv_value.setText(m.getResprocess());
                    ll_content.addView(view);
                    break;
                case 47:
                    tv_name.setText("手工填写内容");
                    tv_value.setText(m.getOther());
                    ll_content.addView(view);
                    break;
                case 48:
                    tv_name.setText("历次工单退回记录");
                    tv_value.setText(m.getRejectreason());
                    ll_content.addView(view);
                    break;
                case 49:
                    tv_name.setText("本次退回原因");
                    tv_value.setText(m.getRejectreason2());
                    ll_content.addView(view);
                    break;
                case 50:
                    tv_name.setText("协同验证内容");
                    tv_value.setText(m.getValidateContent());
                    ll_content.addView(view);
                    break;
                case 51:
                    tv_name.setText("共同到站原因");
                    tv_value.setText(m.getTogetherContent());
                    ll_content.addView(view);
                    break;
                case 52:
                    tv_name.setText("铁塔到站联系人");
                    tv_value.setText(m.getTowerUser());
                    ll_content.addView(view);
                    break;
                case 53:
                    tv_name.setText("铁塔到站联系人电话");
                    tv_value.setText(m.getTowerUserTel());
                    ll_content.addView(view);
                    break;
                case 54:
                    tv_name.setText("要求到站时间");
                    tv_value.setText(m.getMustTogetherDate());
                    ll_content.addView(view);
                    break;
                case 55:
                    tv_name.setText("创建时间");
                    tv_value.setText(m.getSla_createtime());
                    ll_content.addView(view);
                    break;
                case 56:
                    tv_name.setText("分配时间");
                    tv_value.setText(m.getSla_assignetime());
                    ll_content.addView(view);
                    break;
                case 57:
                    tv_name.setText("解决时间");
                    tv_value.setText(m.getSla_resolutiontime());
                    ll_content.addView(view);
                    break;
                case 58:
                    tv_name.setText("关闭时间");
                    tv_value.setText(m.getSla_closetime());
                    ll_content.addView(view);
                    break;
                case 59:
                    tv_name.setText("转派时间");
                    tv_value.setText(m.getTransferDate());
                    ll_content.addView(view);
                    break;
                case 60:
                    tv_name.setText("运营商");
                    tv_value.setText(m.getOperatorSiteLevel());
                    ll_content.addView(view);
                    break;
                case 61:
                    tv_name.setText("站址编码");
                    tv_value.setText(m.getSiteNo());
                    ll_content.addView(view);
                    break;
                case 62:
                    tv_name.setText("资源归属");
                    tv_value.setText(m.getBak3());
                    ll_content.addView(view);
                    break;
                case 63:
                    tv_name.setText("是否需要督办");
                    tv_value.setText(m.getIsNeedSupervise());
                    ll_content.addView(view);
                    break;
                case 64:
                    tv_name.setText("是否督办");
                    tv_value.setText(m.getIsSupervise());
                    ll_content.addView(view);
                    break;
                case 65:
                    tv_name.setText("督办内容");
                    tv_value.setText(m.getSupervise());
                    ll_content.addView(view);
                    break;
                case 66:
                    tv_name.setText("督办反馈内容");
                    tv_value.setText(m.getSuperviseBak());
                    ll_content.addView(view);
                    break;
                case 67:
                    tv_name.setText("督办时间");
                    tv_value.setText(m.getSuperviseDate());
                    break;
                case 68:
                    tv_name.setText("督办反馈时间");
                    tv_value.setText(m.getSuperviseBakDate());
                    ll_content.addView(view);
                    break;
                case 69:
                    tv_name.setText("是否需要催办");
                    tv_value.setText(m.getIsNeedReminders());
                    ll_content.addView(view);
                    break;
                case 70:
                    tv_name.setText("是否催办");
                    tv_value.setText(m.getIsReminders());
                    ll_content.addView(view);
                    break;
                case 71:
                    tv_name.setText("催办内容");
                    tv_value.setText(m.getReminders());
                    ll_content.addView(view);
                    break;
                case 72:
                    tv_name.setText("催办反馈内容");
                    tv_value.setText(m.getRemindersBak());
                    ll_content.addView(view);
                    break;
                case 73:
                    tv_name.setText("催办时间");
                    tv_value.setText(m.getRemindersDate());
                    ll_content.addView(view);
                    break;
                case 74:
                    tv_name.setText("催办反馈时间");
                    tv_value.setText(m.getRemindersBakDate());
                    ll_content.addView(view);
                    break;
                case 75:
                    tv_name.setText("处理人签收时间");
                    tv_value.setText(m.getSignProcessDate());
                    ll_content.addView(view);
                    break;
                case 76:
                    tv_name.setText("处理人反馈");
                    tv_value.setText(m.getProcessBak());
                    ll_content.addView(view);
                    break;
                case 77:
                    tv_name.setText("处理人反馈时间");
                    tv_value.setText(m.getProcessBakDate());
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
    public Button btn_cl;
    @ViewInject(R.id.btn_ccl)
    private Button btn_ccl;
    @ViewInject(R.id.btn_bh)
    private Button btn_bh;
    @ViewInject(R.id.btn_zp)
    private Button btn_zp;
    @ViewInject(R.id.btn_th)
    public Button btn_th;
    @ViewInject(R.id.btn_gb)
    private Button btn_gb;
    @ViewInject(R.id.btn_yz)
    private Button btn_yz;
    @ViewInject(R.id.btn_dd)
    private Button btn_dd;
    @ViewInject(R.id.btn_ks)
    private Button btn_ks;
    @ViewInject(R.id.btn_scfj)
    public Button btn_scfj;
    @ViewInject(R.id.btn_gdcb)
    private Button btn_gdcb;
    @ViewInject(R.id.btn_cbfk)
    private Button btn_cbfk;
    @ViewInject(R.id.btn_gddb)
    private Button btn_gddb;
    @ViewInject(R.id.btn_dbfk)
    private Button btn_dbfk;
    @ViewInject(R.id.btn_clrqs)
    public Button btn_clrqs;
    @ViewInject(R.id.btn_clrfk)
    public Button btn_clrfk;

}
