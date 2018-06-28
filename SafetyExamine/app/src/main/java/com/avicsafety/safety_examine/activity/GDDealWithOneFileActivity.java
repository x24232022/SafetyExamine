package com.avicsafety.safety_examine.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.CTCSRestClientUsage;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.conf.Constants;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.UnsupportedEncodingException;

/**
 * Created by 刘畅 on 2017/6/23.
 */
@ContentView(R.layout.n_activity_gd_othop_yd)
public class GDDealWithOneFileActivity extends BaseActivity{
    private String id;
    private int type;
    private String et_value_str;
    private String turnOpinion;
    public MyProgressDialog progressDialog;


    @ViewInject(R.id.et_value)
    private EditText et_value;
    @ViewInject(R.id.et_value1)
    private EditText et_value1;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_name1)
    private TextView tv_name1;
    @ViewInject(R.id.linearlayout1)
    private LinearLayout mLinearLayout;


    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        id = getIntent().getStringExtra("id");
        type = getIntent().getIntExtra("type", 1);

        if(Constants.ZP == type){
            setTitle("工单转派");
            tv_name.setText("处理人");
            et_value.setInputType(InputType.TYPE_NULL);
            et_value.setHint("请[选择]处理人");
            tv_name1.setText("转派意见");
            et_value1.setHint("请[填写]转派意见");
            et_value1.setVisibility(View.VISIBLE);
            tv_name1.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.VISIBLE);
        } else if (Constants.TH == type) {
            setTitle("工单退回");
            tv_name.setText("本次退回原因");
            et_value.setHint("请[输入]本次退回原因");
            et_value1.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.GONE);
        }

        if(Constants.ZP == type){
            et_value.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityForResult(new Intent(oThis,UserListActivity.class),2000);
                }
            });
        }

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

        turnOpinion = et_value1.getText().toString().trim();

        if (TextUtils.isEmpty(et_value_str)) {
            et_value_str = et_value.getText().toString().trim();
        }

        if (TextUtils.isEmpty(et_value_str)) {
            Toast.makeText(oThis, "必填字段不能为空，请填写后再提交", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = new MyProgressDialog(oThis, "操作中..");
        try {

            System.out.println("@@opFlag	" + type + "id	" + id
                    + "et_value_str	" + et_value_str);

            new CTCSRestClientUsage().dealWithOneFild(oThis, type, id,
                    et_value_str,turnOpinion);

        } catch (JSONException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1000:
            case 2000:
                switch (resultCode){
                    case 1000:
                        if(data.hasExtra("uCNname")){
                            et_value_str = data.getStringExtra("uCNname");
                        }
                        if(data.hasExtra("uIDs")){
                            et_value.setText(data.getStringExtra("uIDs"));
                        }
                        break;
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
