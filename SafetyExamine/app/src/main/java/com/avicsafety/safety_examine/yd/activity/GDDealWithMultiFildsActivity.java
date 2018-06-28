package com.avicsafety.safety_examine.yd.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.datedialog.DateTimePickerDialog;
import com.avicsafety.safety_examine.yd.activity.ydUtil.CTCSRestClientUsage;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

/**
 * Created by 刘畅 on 2017/7/21.
 */
@ContentView(R.layout.n_activity_gd_cl_yd)
public class GDDealWithMultiFildsActivity extends BaseActivity{

    private String id;
    private int opFlag;
    private String faultrectime, teleAlarmreaanalysis, teleSubAlarmreaanalysis,
            alarmrealist, resprocess, other, specialist,faultautorectime,faulttitle;

    @ViewInject(R.id.et_faultrectime)
    private EditText et_faultrectime;
    @ViewInject(R.id.et_teleAlarmreaanalysis)
    private EditText et_teleAlarmreaanalysis;
    @ViewInject(R.id.et_teleSubAlarmreaanalysis)
    private EditText et_teleSubAlarmreaanalysis;
    @ViewInject(R.id.et_alarmreaanalysis)
    private EditText et_alarmreaanalysis;
    @ViewInject(R.id.et_resprocess)
    private EditText et_resprocess;
    @ViewInject(R.id.et_other)
    private EditText et_other;
    @ViewInject(R.id.ll_other)
    private LinearLayout ll_other;
    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.yd_cl_photo)
    private Button yd_cl_photo;

    private int teleAlarmreaanalysisSelectedIndex = -1;

    public MyProgressDialog progressDialog;
    private String fbType = "无线";
    private MenuItem mActionSetting;


    public static String getStringDate(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);

        return dateString;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit, menu);
        mActionSetting = menu.findItem(R.id.action_submit);
        if(faultautorectime == null || faultautorectime.equals("")){
            mActionSetting.setVisible(false);
        }
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
        teleAlarmreaanalysis = intent.getStringExtra("teleAlarmreaanalysis");
        teleSubAlarmreaanalysis = intent
                .getStringExtra("teleSubAlarmreaanalysis");
        alarmrealist = intent.getStringExtra("alarmrealist");
        resprocess = intent.getStringExtra("resprocess");
        specialist = intent.getStringExtra("specialist");
        faultautorectime = intent.getStringExtra("faultautorectime");
        faulttitle = intent.getStringExtra("faulttitle");


        if(faultautorectime == null || faultautorectime.equals("")){
            yd_cl_photo.setVisibility(View.VISIBLE);
        }


//        et_teleAlarmreaanalysis.setText(teleAlarmreaanalysis);
//        et_teleSubAlarmreaanalysis.setText(teleSubAlarmreaanalysis);
//        et_alarmreaanalysis.setText(alarmrealist);
//        et_resprocess.setText(resprocess);

    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();

        et_faultrectime.setInputType(InputType.TYPE_NULL);
        et_faultrectime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(et_faultrectime);
            }
        });



        et_teleAlarmreaanalysis.setInputType(InputType.TYPE_NULL); // 故障原因分类
        et_teleAlarmreaanalysis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根

                et_teleSubAlarmreaanalysis.setText("");
                et_alarmreaanalysis.setText("");
                et_resprocess.setText("");

                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
//                builder.setIcon(null);
                builder.setTitle("请选择【原因分类】");
                // 指定下拉列表的显示数据
                final String[] re = new String[]{
                        "外部供电","动力环境设备",
                        "传输设备","无线（不含BSC及RNC）",
                        "BSC&RNC","人为因素",
                        "自然灾害"
                };
                final String[] re1 = new String[]{
                        "外部供电","动力环境设备",
                        "传输设备","无线（不含BSC及RNC）",
                        "BSC&RNC","人为因素",
                        "自然灾害","其他"
                };
//                final String[] cities = new D_FeedBak().getTypeValue(oThis,
//                        "fbType", fbType, "rType");
                // 设置一个下拉的列表选择项
                if(faulttitle.indexOf("共同到站后需铁塔处理") != -1){
                    builder.setItems(re1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            et_teleAlarmreaanalysis.setText(re1[which]);

                        }
                    });
                }else {
                    builder.setItems(re, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            et_teleAlarmreaanalysis.setText(re[which]);

                        }
                    });
                }

                builder.show();
            }

        });

        et_teleSubAlarmreaanalysis.setInputType(InputType.TYPE_NULL); // 故障原因细分
        et_teleSubAlarmreaanalysis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                et_alarmreaanalysis.setText("");
                et_resprocess.setText("");

                System.out.println("@@@@@@@" + teleAlarmreaanalysis);
                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                // 指定下拉列表的显示数据
                final String[] cities = new D_FeedBak().getTypeValue(oThis,
                        "rType", et_teleAlarmreaanalysis.getText().toString()
                                .trim(), "rDType");
                builder.setTitle("请选择【原因细分】");
                // 设置一个下拉的列表选择项
                builder.setItems(cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_teleSubAlarmreaanalysis.setText(cities[which]);
                    }
                });
                builder.show();

            }
        });

        et_alarmreaanalysis.setInputType(InputType.TYPE_NULL);
        et_alarmreaanalysis.setOnClickListener(new View.OnClickListener() { // 故障原因

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                et_resprocess.setText("");

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        oThis);
                // 指定下拉列表的显示数据
                builder.setTitle("请选择【故障原因】");
                // 设置一个下拉的列表选择项
                final String[] cities = new D_FeedBak().getTypeValue(
                        oThis, "rDType", et_teleSubAlarmreaanalysis
                                .getText().toString().trim(), "rER");

                builder.setItems(cities,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                et_alarmreaanalysis
                                        .setText(cities[which]);

                            }
                        });
                builder.show();

            }
        });

        et_resprocess.setInputType(InputType.TYPE_NULL);
        et_resprocess.setOnClickListener(new View.OnClickListener() { // 处理措施

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        oThis);
                // 指定下拉列表的显示数据
                final String[] cities = new D_FeedBak().getTypeValue(
                        oThis, "rER", et_alarmreaanalysis.getText()
                                .toString().trim(), "rD");
                builder.setTitle("请选择【处理措施】");
                // 设置一个下拉的列表选择项
                builder.setItems(cities,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                et_resprocess.setText(cities[which]);

                                if (et_resprocess.getText().toString()
                                        .trim().equals("需回单人员手动填写")
                                        || et_resprocess.getText()
                                        .toString().trim()
                                        .equals("手动填写")) {

                                    ll_other.setVisibility(View.VISIBLE);

                                    System.out.println("woca"
                                            + et_resprocess);
                                    return;
                                }
                            }
                        });
                builder.show();

            }
        });


        yd_cl_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionSetting.setVisible(true);
                Intent intent = new Intent(oThis,PhotoYdClActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


    }
    public void showDialog(final EditText et_faultrectime) {
        DateTimePickerDialog dialog = new DateTimePickerDialog(this,
                System.currentTimeMillis());
        dialog.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
            @Override
            public void OnDateTimeSet(AlertDialog dialog, long date) {

                et_faultrectime.setText(getStringDate(date));

            }
        });
        dialog.show();
    }

    private void submitInfo() {
        // TODO 自动生成的方法存根

        faultrectime = et_faultrectime.getText().toString().trim();
        teleAlarmreaanalysis = et_teleAlarmreaanalysis.getText().toString()
                .trim();
        teleSubAlarmreaanalysis = et_teleSubAlarmreaanalysis.getText()
                .toString().trim();
        alarmrealist = et_alarmreaanalysis.getText().toString().trim();
        resprocess = et_resprocess.getText().toString().trim();
        other = et_other.getText().toString().trim();

        if (TextUtils.isEmpty(teleAlarmreaanalysis)) {
            Toast.makeText(oThis, "原因分类不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(teleSubAlarmreaanalysis)) {
            Toast.makeText(oThis, "原因细分不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(alarmrealist)) {
            Toast.makeText(oThis, "故障原因不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(resprocess)) {
            Toast.makeText(oThis, "处理措施不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ll_other.isShown() && TextUtils.isEmpty(other)) {
            Toast.makeText(oThis, "手工填写内容不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }



        progressDialog = new MyProgressDialog(oThis, "操作中..");

        try {
            new CTCSRestClientUsage().dealGDCL(oThis, opFlag, id,
                    teleAlarmreaanalysis, teleSubAlarmreaanalysis,
                    alarmrealist, resprocess, other, faultrectime);
        } catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

}
