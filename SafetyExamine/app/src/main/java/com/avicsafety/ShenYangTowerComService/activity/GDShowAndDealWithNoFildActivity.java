package com.avicsafety.ShenYangTowerComService.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.CTCSRestClientUsage;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.conf.Constants;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.model.MgdxqTT;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Field;

/**
 * Created by 刘畅 on 2017/6/22.
 */
@ContentView(R.layout.n_activity_gdxq_yd)
public class GDShowAndDealWithNoFildActivity extends BaseActivity implements View.OnClickListener{
    public MyProgressDialog progressDialog;
    private String id;
    private int taskType = 0;
    private MUser mu;
    private MgdxqTT mTT;

    public MgdxqTT getM() {
        return mTT;
    }
    public void setM(MgdxqTT mTT) {
        this.mTT = mTT;
    }

    public void ShowGDInfo(MgdxqTT mTT) {
        // TODO 自动生成的方法存根

        this.setM(mTT);

        Field[] field = mTT.getClass().getDeclaredFields();

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
                    tv_value.setText(mTT.getNo());
                    ll_content.addView(view);
                    break;
                case 4:
                    tv_name.setText("状态");
                    tv_value.setText(mTT.getStatus());
                    ll_content.addView(view);
                    break;
                case 5:
                    tv_name.setText("处理人姓名");
                    tv_value.setText(mTT.getHandler());
                    ll_content.addView(view);
                    break;
                case 6:
                    tv_name.setText("运营商故障工单号");
                    tv_value.setText(mTT.getFaultNo());
                    ll_content.addView(view);
                    break;
                case 7:
                    tv_name.setText("主题");
                    tv_value.setText(mTT.getTheme());
                    ll_content.addView(view);
                    break;
                case 8:
                    tv_name.setText("站址编号");
                    tv_value.setText(mTT.getSiteId());
                    ll_content.addView(view);
                    break;
                case 9:
                    tv_name.setText("站点名称");
                    tv_value.setText(mTT.getSiteName());
                    ll_content.addView(view);
                    break;
                case 10:
                    tv_name.setText("区域");
                    tv_value.setText(mTT.getArea());
                    ll_content.addView(view);
                    break;
                case 11:
                    tv_name.setText("地市");
                    tv_value.setText(mTT.getLocationStr());
                    ll_content.addView(view);
                    break;
                case 12:
                    tv_name.setText("基站类型");
                    tv_value.setText(mTT.getBtsType());
                    ll_content.addView(view);
                    break;
                case 13:
                    tv_name.setText("交流输入故障告警流水号(开关电源)");
                    tv_value.setText(mTT.getTroubleNo());
                    ll_content.addView(view);
                    break;
                case 14:
                    tv_name.setText("一级低压脱离告警工单号");
                    tv_value.setText(mTT.getOutAlarmNo());
                    ll_content.addView(view);
                    break;
                case 15:
                    tv_name.setText("蓄电池续航能力(分钟)");
                    tv_value.setText(mTT.getEnduranceAbility());
                    ll_content.addView(view);
                    break;
                case 16:
                    tv_name.setText("移动铁塔站/铁塔新建站");
                    tv_value.setText(mTT.getSiteType());
                    ll_content.addView(view);
                    break;
                case 17:
                    tv_name.setText("发电共享基站(联通/电信/三家)");
                    tv_value.setText(mTT.getShareType());
                    ll_content.addView(view);
                    break;
                case 18:
                    tv_name.setText("市电停电时间");
                    tv_value.setText(mTT.getPowerFailureDate());
                    ll_content.addView(view);
                    break;
                case 19:
                    tv_name.setText("市电恢复时间");
                    tv_value.setText(mTT.getPowerRecoveryDate());
                    ll_content.addView(view);
                    break;
                case 20:
                    tv_name.setText("具体发电点");
                    tv_value.setText(mTT.getAddress());
                    ll_content.addView(view);
                    break;
                case 21:
                    tv_name.setText("发电开始时间");
                    tv_value.setText(mTT.getElectricityStartDate());
                    ll_content.addView(view);
                    break;
                case 22:
                    tv_name.setText("发电结束时间");
                    tv_value.setText(mTT.getElectricityEndDate());
                    ll_content.addView(view);
                    break;
                case 23:
                    tv_name.setText("屋内屋外发电");
                    tv_value.setText(mTT.getElectricityType());
                    ll_content.addView(view);
                    break;
                case 24:
                    tv_name.setText("发电原因(不在选项内的发电原因请直接填写)");
                    tv_value.setText(mTT.getElectricityReason());
                    ll_content.addView(view);
                    break;
                case 25:
                    tv_name.setText("发电中断时间间隔(分钟)");
                    tv_value.setText(mTT.getInterruptMinute());
                    ll_content.addView(view);
                    break;
                case 26:
                    tv_name.setText("手动填写发电原因");
                    tv_value.setText(mTT.getEnduranceReasonRemark());
                    ll_content.addView(view);
                    break;
                case 27:
                    tv_name.setText("到站验证码");
                    tv_value.setText(mTT.getStartCode());
                    ll_content.addView(view);
                    break;
                case 28:
                    tv_name.setText("取消原因");
                    tv_value.setText(mTT.getCancelReason());
                    ll_content.addView(view);
                    break;
                case 29:
                    tv_name.setText("备注");
                    tv_value.setText(mTT.getRemark());
                    ll_content.addView(view);
                    break;
                case 30:
                    tv_name.setText("退回原因");
                    tv_value.setText(mTT.getBackReason());
                    ll_content.addView(view);
                    break;
                case 31:
                    tv_name.setText("审核是否通过");
                    tv_value.setText(mTT.getIsPass());
                    ll_content.addView(view);
                    break;
                case 32:
                    tv_name.setText("审核意见");
                    tv_value.setText(mTT.getAuditContent());
                    ll_content.addView(view);
                    break;
                case 33:
                    tv_name.setText("作废原因");
                    tv_value.setText(mTT.getAbolishReason());
                    ll_content.addView(view);
                    break;
                case 34:
                    tv_name.setText("是否驳回");
                    tv_value.setText(mTT.getIsDoubt());
                    ll_content.addView(view);
                    break;
                case 35:
                    tv_name.setText("驳回描述");
                    tv_value.setText(mTT.getDoubtDesc());
                    ll_content.addView(view);
                    break;
                case 36:
                    tv_name.setText("转派意见列表");
                    tv_value.setText(mTT.getTurnOpinion());
                    ll_content.addView(view);
                    break;
                case 37:
                    tv_name.setText("取消原因");
                    tv_value.setText(mTT.getCancelReason());
                    ll_content.addView(view);
                    break;
                default:
                    break;

            }
            initBtns();
        }
    }



    // 显示按钮的控制
    private void initBtns() {
        // TODO 自动生成的方法存根

        reSetAllBtns();// 重置按钮状态
        if (mTT.getStatus().equals("处理中") && TextUtils.isEmpty(mTT.getSignDate())) {
            // 签收
            btn_qs.setVisibility(View.VISIBLE);
        }
        if (mTT.getStatus().equals("处理中") && mTT.getSignDate() != null
                ) {
            // 转派
            btn_zp.setVisibility(View.VISIBLE);
        }

        if (mTT.getStatus().equals("处理中")) {
            // 处理，退回，验证，到站
            btn_cl.setVisibility(View.GONE);
            btn_th.setVisibility(View.VISIBLE);
            btn_zp.setVisibility(View.VISIBLE);
            btn_ks.setVisibility(View.VISIBLE);
        }
        if(mTT.getStartCode() != null){
            btn_cl.setVisibility(View.VISIBLE);
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
                btn_ks.setVisibility(View.GONE);
                break;
        }

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
        btn_ks.setVisibility(View.GONE);
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
        btn_ks.setOnClickListener(this);

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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qs:

                progressDialog = new MyProgressDialog(oThis, "签收..");

                try {
                    new CTCSRestClientUsage().dealWithNoFild(oThis, id,
                            Constants.QS);

                } catch (JSONException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }

                break;
            case R.id.btn_cl:
                startActivity(new Intent(oThis, GDDealWithMultiFildsActivity.class)
                                .putExtra("id", id)
                                .putExtra("type", Constants.GDCL)
                        );
                this.finish();
                break;
            case R.id.btn_zp:
                startActivity(new Intent(oThis, GDDealWithOneFileActivity.class)
                        .putExtra("id", id).putExtra("type", Constants.ZP));
                this.finish();
                break;
            case R.id.btn_th:
                startActivity(new Intent(oThis, GDDealWithOneFileActivity.class)
                        .putExtra("id", id).putExtra("type", Constants.TH));
                this.finish();
                break;
            case R.id.btn_ks:
                Intent intent = new Intent(oThis,StartCodeActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
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
    @ViewInject(R.id.btn_yz)
    private Button btn_yz;
    @ViewInject(R.id.btn_dd)
    private Button btn_dd;
    @ViewInject(R.id.btn_ks)
    private Button btn_ks;
}
