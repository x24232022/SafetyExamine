package com.avicsafety.safety_examine.lt.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.lt.activity.ltUtil.CTCSRestClientUsage;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;

/**
 * Created by 刘畅 on 2017/7/27.
 */
@ContentView(R.layout.n_activity_gd_cl_lt)
public class GDDealWithMultiFildsActivity extends BaseActivity{
    private String id;
    private int opFlag;

    private String teleresults, teleoperation_bak3, telehanprofessional,
            alarmreaanalysis, alarmrealist, resprocess;
    private String zhuanye = "铁塔";
    public MyProgressDialog progressDialog;

    @ViewInject(R.id.et_teleresults)
    private EditText et_teleresults;
    @ViewInject(R.id.et_teleoperation_bak3)
    private EditText et_teleoperation_bak3;
    @ViewInject(R.id.et_telehanprofessional)
    private EditText et_telehanprofessional;
    @ViewInject(R.id.et_alarmreaanalysis)
    private EditText et_alarmreaanalysis;
    @ViewInject(R.id.et_alarmrealist)
    private EditText et_alarmrealist;
    @ViewInject(R.id.et_resprocess)
    private EditText et_resprocess;


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

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("工单处理");
        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        opFlag = intent.getIntExtra("opFlag", 0);
        alarmrealist = intent.getStringExtra("alarmrealist");
        resprocess = intent.getStringExtra("resprocess");
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        et_teleoperation_bak3.setInputType(InputType.TYPE_NULL); // 故障原因分类
        et_teleoperation_bak3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                et_teleoperation_bak3.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                builder.setIcon(null);
                builder.setTitle("请[选择]是否节点站");
                // 指定下拉列表的显示数据
                final String[] re = new String[]{"是","否"};
                // 设置一个下拉的列表选择项
                builder.setItems(re, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_teleoperation_bak3.setText(re[which]);
                    }
                });
                builder.show();
            }

        });

        et_telehanprofessional.setInputType(InputType.TYPE_NULL); // 专业
        et_telehanprofessional.setText(zhuanye);
//        et_telehanprofessional.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                et_telehanprofessional.setText("");
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
//                // 指定下拉列表的显示数据
//
//                String re = new String(zhuanye);
//                builder.setTitle("请选择【处理专业】");
//                // 设置一个下拉的列表选择项
//                builder.setItems(re, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        et_telehanprofessional.setText(zhuanye);
//                    }
//                });
//                builder.show();
//
//            }
//        });

        et_alarmreaanalysis.setInputType(InputType.TYPE_NULL);
        et_alarmreaanalysis.setOnClickListener(new View.OnClickListener() { // 故障原因

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                et_alarmreaanalysis.setText("");

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        oThis);
                // 指定下拉列表的显示数据
                builder.setTitle("请选择【故障原因】");
                // 设置一个下拉的列表选择项
                final String[] re = new String[]{"铁塔市政停电，无后备电池",
                        "铁塔电源设备故障","铁塔市政停电，蓄电池性能下降","铁塔机房空调故障，高温断站",
                        "铁塔业主原因（室分基站）","铁塔业主原因，不让进站","铁塔业主纠纷，拉闸限电","铁塔业主装修改造","铁塔市政停电（室分基站）",
                        "铁塔机房高温，无空调"};

                builder.setItems(re,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                et_alarmreaanalysis
                                        .setText(re[which]);

                            }
                        });
                builder.show();

            }
        });
    }

    private void submitInfo() {
        // TODO 自动生成的方法存根




        teleresults = et_teleresults.getText().toString().trim();
        teleoperation_bak3 = et_teleoperation_bak3.getText().toString().equals("是")?"true":"false";
        telehanprofessional = et_telehanprofessional.getText().toString().trim();
        alarmreaanalysis = et_alarmreaanalysis.getText().toString().trim();
        alarmrealist = et_alarmrealist.getText().toString().trim();
        resprocess = et_resprocess.getText().toString().trim();


        if (TextUtils.isEmpty(teleresults)) {
            Toast.makeText(oThis, "结果不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(teleoperation_bak3)) {
            Toast.makeText(oThis, "是否节点站必须选择!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(telehanprofessional)) {
            Toast.makeText(oThis, "处理专业不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(alarmreaanalysis)) {
            Toast.makeText(oThis, "故障原因不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(alarmrealist)) {
            Toast.makeText(oThis, "故障原因说明不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(resprocess)) {
            Toast.makeText(oThis, "解决过程不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = new MyProgressDialog(oThis, "操作中..");

        try {
            new CTCSRestClientUsage().dealGDCL(oThis, opFlag, id,
                    teleresults, teleoperation_bak3,telehanprofessional,
                    alarmreaanalysis, alarmrealist, resprocess);
        }catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
