package com.avicsafety.safety_examine.lt.activity;

import android.app.AlertDialog;
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
import com.avicsafety.safety_examine.datedialog.DateTimePickerDialog;
import com.avicsafety.safety_examine.lt.activity.ltUtil.CTCSRestClientUsage;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

/**
 * Created by 刘畅 on 2017/7/27.
 */
@ContentView(R.layout.n_activity_gd_dz_yd)
public class GDGTDZActivity extends BaseActivity {

    private String id;
    private int opFlag;
    private String togetherContent, towerUser, towerUserTel, togetherDate;
    private String et_towerUser_str;

    @ViewInject(R.id.et_togetherContent)
    private EditText et_togetherContent;
    @ViewInject(R.id.et_towerUser)
    private EditText et_towerUser;
    @ViewInject(R.id.et_towerUserTel)
    private EditText et_towerUserTel;
    @ViewInject(R.id.et_mustTogetherDate)
    private EditText et_togetherDate;

    public MyProgressDialog progressDialog;

    public static String getStringDate(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);

        return dateString;
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

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("共同到站");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        opFlag = intent.getIntExtra("opFlag", 0);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        et_towerUser.setInputType(InputType.TYPE_NULL);
        et_towerUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivityForResult(
                        new Intent(oThis, UserListActivity.class), 1000);
            }
        });

        et_togetherDate.setInputType(InputType.TYPE_NULL);
        et_togetherDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(et_togetherDate);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch (resultCode) {
            case 1000:
                if (data.hasExtra("uCNname")) {

                    et_towerUser_str = data.getStringExtra("uCNname");
                }

                if (data.hasExtra("uIDs")) {

                    et_towerUser.setText(data.getStringExtra("uIDs"));

                }
                if (data.hasExtra("mobile")) {

                    et_towerUserTel.setText(data.getStringExtra("mobile"));

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void showDialog(final EditText et_mustTogetherDate) {
        DateTimePickerDialog dialog = new DateTimePickerDialog(this,
                System.currentTimeMillis());
        dialog.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
            @Override
            public void OnDateTimeSet(AlertDialog dialog, long date) {

                et_mustTogetherDate.setText(getStringDate(date));

            }
        });
        dialog.show();
    }

    protected void submitInfo() {
        // TODO Auto-generated method stub

        togetherContent = et_togetherContent.getText().toString().trim();
        towerUser = et_towerUser.getText().toString().trim();
        towerUserTel = et_towerUserTel.getText().toString().trim();
        togetherDate = et_togetherDate.getText().toString().trim();

        if (TextUtils.isEmpty(togetherContent)) {
            Toast.makeText(oThis, "共同到站原因不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(towerUser)) {
            Toast.makeText(oThis, "铁塔到站联系人不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(towerUserTel)) {
            Toast.makeText(oThis, "铁塔到站联系人电话不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(togetherDate)) {
            Toast.makeText(oThis, "要求到站时间不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = new MyProgressDialog(oThis, "操作中..");

        try {
            new CTCSRestClientUsage().dealGDdz(oThis, opFlag, id,
                    togetherContent, towerUser, towerUserTel, togetherDate);
        } catch (JSONException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

}
