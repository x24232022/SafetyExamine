package com.avicsafety.ShenYangTowerComService.dx.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.ShenYangTowerComService.dx.activity.dxUtil.CTCSRestClientUsage;
import com.avicsafety.ShenYangTowerComService.dx.activity.dxUtil.Constants;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;

/**
 * Created by 刘畅 on 2017/7/25.
 */
@ContentView(R.layout.n_activity_gd_cl)
public class GDDealWithMultiFildsActivity extends BaseActivity{
    private int type;
    private String id;
    public MyProgressDialog progressDialog;
    private String isAnyQuestion = "false", isAdjustment = "false",
            troubRecoverCondition, troubAddress, scopeextent,
            teleAlarmreaanalysis, teleSubAlarmreaanalysis, resprocess,
            dutyBelong;
    private int dutyLocation;
    private int opFlag;
    private int teleAlarmreaanalysisSelectedIndex = -1,
            teleSubAlarmreaanalysisSelectedIndex = -1;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit:
                submitInfo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void submitInfo() {
        // TODO 自动生成的方法存根

        troubRecoverCondition = et_troubRecoverCondition.getText().toString();
        troubAddress = et_troubAddress.getText().toString();
        scopeextent = et_scopeextent.getText().toString();
        resprocess = et_resprocess.getText().toString();
        dutyBelong = et_dutyBelong.getText().toString();

        if (TextUtils.isEmpty(troubRecoverCondition)) {
            Toast.makeText(oThis, "故障恢复情况不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(troubAddress)) {
            Toast.makeText(oThis, "故障地点不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(scopeextent)) {
            Toast.makeText(oThis, "影响业务不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(teleAlarmreaanalysis)) {
            Toast.makeText(oThis, "原因分类不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(teleSubAlarmreaanalysis)) {
            Toast.makeText(oThis, "原因细分不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(resprocess)) {
            Toast.makeText(oThis, "故障处理过程描述不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(dutyBelong)) {
            Toast.makeText(oThis, "责任方不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = new MyProgressDialog(oThis, "操作中..", true);

        try {
            new CTCSRestClientUsage().dealGDCL(oThis, opFlag, id,
                    isAnyQuestion, isAdjustment, troubRecoverCondition,
                    troubAddress, scopeextent, dutyLocation,
                    teleAlarmreaanalysis, teleSubAlarmreaanalysis, resprocess,
                    dutyBelong);
        } catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }


    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("工单处理");

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        opFlag = intent.getIntExtra("opFlag", 0);
        dutyLocation = Integer.parseInt(intent.getStringExtra("location"));


        et_dutyLocation.setText(Constants.cities[dutyLocation]);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();

        rg_isAnyQuestion
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO 自动生成的方法存根

                        switch (checkedId) {
                            case R.id.rb_1_1:
                                isAnyQuestion = "true";
                                break;
                            case R.id.rb_1_2:
                            default:
                                isAnyQuestion = "false";
                                break;
                        }

                    }
                });

        rg_isAdjustment
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO 自动生成的方法存根

                        switch (checkedId) {
                            case R.id.rb_2_1:
                                isAdjustment = "true";
                                break;
                            case R.id.rb_2_2:
                            default:
                                isAdjustment = "false";
                                break;
                        }

                    }
                });

        et_dutyLocation.setInputType(InputType.TYPE_NULL);
        et_dutyLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根

                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                builder.setIcon(null);
                builder.setTitle("请选择【责任地市】");
                // 指定下拉列表的显示数据

                // 设置一个下拉的列表选择项
                builder.setItems(Constants.cities,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                et_dutyLocation
                                        .setText(Constants.cities[which]);
                                dutyLocation = which + 1;

                            }
                        });
                builder.show();

            }
        });

        et_teleAlarmreaanalysis.setInputType(InputType.TYPE_NULL);
        et_teleAlarmreaanalysis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                builder.setIcon(null);
                builder.setTitle("请选择【原因分类】");
                // 指定下拉列表的显示数据

                // 设置一个下拉的列表选择项
                builder.setItems(Constants.teleAlarmreaanalysis,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                teleAlarmreaanalysisSelectedIndex = which;

                                et_teleAlarmreaanalysis
                                        .setText(Constants.teleAlarmreaanalysis[which]);
                                teleAlarmreaanalysis = Constants.teleAlarmreaanalysisValues[which];

                            }
                        });
                builder.show();
            }
        });

        et_teleSubAlarmreaanalysis.setInputType(InputType.TYPE_NULL);
        et_teleSubAlarmreaanalysis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根

                if (teleAlarmreaanalysisSelectedIndex < -0) {
                    Toast.makeText(oThis, "请先选择原因分类!", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                builder.setIcon(null);
                builder.setTitle("请选择【原因细分】");
                // 指定下拉列表的显示数据

                // 设置一个下拉的列表选择项
                builder.setItems(
                        Constants.teleSubAlarmreaanalysis[teleAlarmreaanalysisSelectedIndex],
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                teleSubAlarmreaanalysisSelectedIndex = which;

                                et_teleSubAlarmreaanalysis
                                        .setText(Constants.teleSubAlarmreaanalysis[teleAlarmreaanalysisSelectedIndex][which]);
                                teleSubAlarmreaanalysis = Constants.teleSubAlarmreaanalysisValues[teleAlarmreaanalysisSelectedIndex][which];

                            }
                        });
                builder.show();
            }
        });
    }

    @ViewInject(R.id.et_troubRecoverCondition)
    private EditText et_troubRecoverCondition;
    @ViewInject(R.id.et_troubAddress)
    private EditText et_troubAddress;
    @ViewInject(R.id.et_scopeextent)
    private EditText et_scopeextent;
    @ViewInject(R.id.et_dutyLocation)
    private EditText et_dutyLocation;
    @ViewInject(R.id.et_teleAlarmreaanalysis)
    private EditText et_teleAlarmreaanalysis;
    @ViewInject(R.id.et_teleSubAlarmreaanalysis)
    private EditText et_teleSubAlarmreaanalysis;
    @ViewInject(R.id.et_resprocess)
    private EditText et_resprocess;
    @ViewInject(R.id.et_dutyBelong)
    private EditText et_dutyBelong;

    @ViewInject(R.id.rg_isAnyQuestion)
    private RadioGroup rg_isAnyQuestion;
    @ViewInject(R.id.rg_isAdjustment)
    private RadioGroup rg_isAdjustment;
}
