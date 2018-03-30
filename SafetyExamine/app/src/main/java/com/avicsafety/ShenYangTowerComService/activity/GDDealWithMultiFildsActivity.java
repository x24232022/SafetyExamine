package com.avicsafety.ShenYangTowerComService.activity;

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

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.CTCSRestClientUsage;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.datedialog.DateTimePickerDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

/**
 * Created by 刘畅 on 2017/6/24.
 */
@ContentView(R.layout.lc_activity_gd_cl_tt)
public class GDDealWithMultiFildsActivity extends BaseActivity {


    private int type;
    private String id;
    private String powerFailureDate,powerRecoveryDate,electricityStartDate,
            electricityEndDate,electricityReason,enduranceReasonRemark,
            electricityType,address,interruptMinute,remark;
    public MyProgressDialog progressDialog;
    private DateTimePickerDialog dialog ;





    public static String getStringDate(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);

        return dateString;
    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        Intent intent = getIntent();
        setTitle("工单处理");
        id = intent.getStringExtra("id");
        type = intent.getIntExtra("type",5);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        dialog = new DateTimePickerDialog(oThis,System.currentTimeMillis());

        et_sdtdsj.setInputType(InputType.TYPE_NULL);
        et_sdtdsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
                    @Override
                    public void OnDateTimeSet(AlertDialog dialog, long date) {
                        et_sdtdsj.setText(getStringDate(date));
                    }
                });
                dialog.show();
            }
        });
        et_sdhfsj.setInputType(InputType.TYPE_NULL);//市电恢复时间
        et_sdhfsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
                    @Override
                    public void OnDateTimeSet(AlertDialog dialog, long date) {
                        et_sdhfsj.setText(getStringDate(date));
                    }
                });
                dialog.show();
            }
        });
        et_fdkssj.setInputType(InputType.TYPE_NULL);//发电开始时间
        et_fdkssj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
                    @Override
                    public void OnDateTimeSet(AlertDialog dialog, long date) {
                        et_fdkssj.setText(getStringDate(date));
                    }
                });
                dialog.show();
            }
        });
        et_fdjssj.setInputType(InputType.TYPE_NULL);//发电结束时间
        et_fdjssj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
                    @Override
                    public void OnDateTimeSet(AlertDialog dialog, long date) {
                        et_fdjssj.setText(getStringDate(date));
                    }
                });
                dialog.show();
            }
        });
        et_fdyy.setInputType(InputType.TYPE_NULL);
        et_fdyy.setOnClickListener(new View.OnClickListener() {//发电原因
            @Override
            public void onClick(View v) {
                et_fdyy.setText("");
                final AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                builder.setTitle("请[选择]发电原因");
                final String[] re = new String[]{"其它原因",
                        "计划停电",
                        "设备故障停电（铁塔）",
                        "有纠纷欠电费",
                        "自然灾害",
                        "设备故障停电（自留）",
                        "设备故障停电（电业局）"
                };
                builder.setItems(re, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_fdyy.setText(re[which]);
                    }
                });
                builder.show();
            }
        });
        et_jtfdd.setInputType(InputType.TYPE_NULL);
        et_jtfdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_jtfdd.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                builder.setTitle("请[选择]具体发电点");
                final String[] re = new String[]{"变压器至交流屏间",
                        "交流屏至开关电源间",
                        "设备直发"};
                builder.setItems(re, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_jtfdd.setText(re[which]);
                    }
                });
                builder.show();
            }
        });
        et_wnwwfd.setInputType(InputType.TYPE_NULL);
        et_wnwwfd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_wnwwfd.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                builder.setTitle("屋内屋外发电");
                final String[] re = new String[]{"屋内",
                        "屋外"};
                builder.setItems(re, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_wnwwfd.setText(re[which]);
                    }
                });
                builder.show();
            }
        });
        et_sctp.setInputType(InputType.TYPE_NULL);
        et_sctp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(oThis,PhotoActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

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

        powerFailureDate = et_sdtdsj.getText().toString().trim();
        powerRecoveryDate = et_sdhfsj.getText().toString().trim();
        electricityStartDate = et_fdkssj.getText().toString().trim();
        electricityEndDate = et_fdjssj.getText().toString().trim();
        electricityReason = et_fdyy.getText().toString().trim();
        enduranceReasonRemark = et_sdtxfdyy.getText().toString().trim();
        electricityType = et_wnwwfd.getText().toString().trim();
        address = et_jtfdd.getText().toString().trim();
        interruptMinute = et_fdzdsjjg.getText().toString().trim();
        remark = et_bz.getText().toString().trim();



        if (TextUtils.isEmpty(powerFailureDate)) {
            Toast.makeText(oThis, "市电停电时间不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(powerRecoveryDate)) {
            Toast.makeText(oThis, "市电恢复时间不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(electricityStartDate)) {
            Toast.makeText(oThis, "发电开始时间不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(electricityEndDate)) {
            Toast.makeText(oThis, "发电结束时间不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(electricityReason)) {
            Toast.makeText(oThis, "发电原因不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (electricityReason.equals("其他原因")&&
                enduranceReasonRemark.equals(null)
                ) {
            Toast.makeText(oThis, "手动填写发电原因不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(electricityType)) {
            Toast.makeText(oThis, "屋内屋外发电不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(oThis, "具体发电点不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(interruptMinute)) {
            Toast.makeText(oThis, "发电中断时间间隔不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog = new MyProgressDialog(oThis, "操作中..");

        try {
            new CTCSRestClientUsage().dealGDCL(oThis, id, type,
                    powerFailureDate, powerRecoveryDate,electricityStartDate,
                    electricityEndDate, electricityReason, enduranceReasonRemark,electricityType,
                    address,interruptMinute,remark);
        }catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    @ViewInject(R.id.et_gdcl_fdyy)
    private EditText et_fdyy;
    @ViewInject(R.id.et_gdcl_sdtdsj)
    private EditText et_sdtdsj;
    @ViewInject(R.id.et_gdcl_sdhfsj)
    private EditText et_sdhfsj;
    @ViewInject(R.id.et_gdcl_fdkssj)
    private EditText et_fdkssj;
    @ViewInject(R.id.et_gdcl_fdjssj)
    private EditText et_fdjssj;
    @ViewInject(R.id.et_gdcl_jtfdd)
    private EditText et_jtfdd;
    @ViewInject(R.id.et_gdcl_wnwwfd)
    private EditText et_wnwwfd;
    @ViewInject(R.id.et_gdcl_fdzdsjjg)
    private EditText et_fdzdsjjg;
    @ViewInject(R.id.et_gdcl_sdtxfdyy)
    private EditText et_sdtxfdyy;
    @ViewInject(R.id.et_gdcl_bz)
    private EditText et_bz;
    @ViewInject(R.id.et_gdcl_sctp)
    private EditText et_sctp;

}
