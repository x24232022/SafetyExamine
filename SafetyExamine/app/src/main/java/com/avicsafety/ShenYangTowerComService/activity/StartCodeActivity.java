package com.avicsafety.ShenYangTowerComService.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.CTCSRestClientUsage;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.model.MksyzmTT;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 刘畅 on 2017/6/23.
 */
@ContentView(R.layout.lc_activiti_startcode)
public class StartCodeActivity extends BaseActivity{

    @ViewInject(R.id.et_ks)
    private EditText et_startCoed;
    private String id;
    public MyProgressDialog progressDialog;


    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("获取到站验证码");
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void InitializeData() {
        super.InitializeData();
        initEVENT();
    }
    public void showCode(MksyzmTT mksyzmTT){

        et_startCoed.setText(mksyzmTT.getStartCode());

    }

    private void initEVENT() {
        try {
            new CTCSRestClientUsage().GetKSYZM(oThis,id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
