package com.avicsafety.safety_examine.dx.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.dx.activity.dxUtil.CTCSRestClientUsage;
import com.avicsafety.safety_examine.dx.activity.dxUtil.Constants;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;

/**
 * Created by 刘畅 on 2017/7/25.
 */
@ContentView(R.layout.n_activity_gd_othop)
public class GDDealWithOneFileActivity extends BaseActivity{

    public MyProgressDialog progressDialog;
    private String id;
    private int opFlag;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.et_value)
    private EditText et_value;
    private String et_value_str;

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();

        id = getIntent().getStringExtra("id");
        opFlag = getIntent().getIntExtra("opFlag", 0);

        if (Constants.GDPF == opFlag) {
            setTitle("工单派发");
            tv_name.setText("处理人");
            et_value.setInputType(InputType.TYPE_NULL);
            et_value.setHint("请[选择]处理人");

        } else if (Constants.GDZP == opFlag) {
            setTitle("工单转派");
            tv_name.setText("处理人");
            et_value.setInputType(InputType.TYPE_NULL);
            et_value.setHint("请[选择]处理人");

        } else if (Constants.GDTH == opFlag) {
            setTitle("工单退回");
            tv_name.setText("本次退回原因");
            et_value.setHint("请[输入]本次退回原因");

        } else if (Constants.GDBH == opFlag) {
            setTitle("工单驳回");
            tv_name.setText("驳回原因");
            et_value.setHint("请[输入]驳回原因");
        }


        if (Constants.GDPF == opFlag) {

            et_value.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO 自动生成的方法存根
                    startActivityForResult(new Intent(oThis,
                            UserListActivity.class), 1000);
                }
            });

        } else if (Constants.GDZP == opFlag) {

            et_value.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO 自动生成的方法存根
                    startActivityForResult(new Intent(oThis,
                            UserListActivity.class), 2000);
                }
            });

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO 自动生成的方法存根

        switch (requestCode) {
            case 1000:
            case 2000:

                switch (resultCode) {
                    case 1000:
                        if (data.hasExtra("uCNname")) {
                            et_value_str = data.getStringExtra("uCNname");
                        }

                        if (data.hasExtra("uIDs")) {
                            et_value.setText(data.getStringExtra("uIDs"));
                        }
                        break;
                }

        }

        super.onActivityResult(requestCode, resultCode, data);

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

        progressDialog = new MyProgressDialog(oThis, "操作中..", true);

        if (TextUtils.isEmpty(et_value_str)) {
            et_value_str = et_value.getText().toString().trim();
        }

        if (TextUtils.isEmpty(et_value_str)) {
            Toast.makeText(oThis, "必填字段不能为空，请填写后再提交", 500).show();
        }
        try {

            System.out.println("@@opFlag	" + opFlag + "id	" + id
                    + "et_value_str	" + et_value_str);

            new CTCSRestClientUsage().dealWithOneFild(oThis, opFlag, id,
                    et_value_str);

        } catch (JSONException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

}
