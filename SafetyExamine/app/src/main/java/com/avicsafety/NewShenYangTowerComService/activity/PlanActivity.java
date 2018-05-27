//package com.avicsafety.ShenYangTowerComService.activity;
//
//import android.content.Intent;
//import android.view.View;
//import android.widget.TextView;
//
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.CTCSRestClientUsage;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Utils;
//import com.avicsafety.ShenYangTowerComService.R;
//import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
//
//import org.xutils.view.annotation.ContentView;
//import org.xutils.view.annotation.ViewInject;
//
///**
// * Created by 刘畅 on 2017/8/22.
// */
//@ContentView(R.layout.activity_power_work)
//public class PlanActivity extends BaseActivity implements View.OnClickListener{
//    private String userAccoutn;
//    public MyProgressDialog progressDialog;
//
//
//    @Override
//    protected void InitializeComponent() {
//        super.InitializeComponent();
//        userAccoutn= Utils.readOAuth(this).getUserName();
//        laodata();
//        setTitle("任务数量");
//        tv_work_wj.setOnClickListener(this);
//        tv_work_yw.setOnClickListener(this);
//        tv_work_zf.setOnClickListener(this);
//        tv_work_yj.setOnClickListener(this);
//        tv_fault_yj.setOnClickListener(this);
//        tv_fault_zf.setOnClickListener(this);
//        tv_fault_yw.setOnClickListener(this);
//        tv_fault_wj.setOnClickListener(this);
//
//
//    }
//    private void laodata() {
//        new CTCSRestClientUsage().RWSLWJ(oThis,1,10,userAccoutn);
//        new CTCSRestClientUsage().RWSLYJ(oThis,1,10,userAccoutn);
//        new CTCSRestClientUsage().RWSLZF(oThis,1,10,userAccoutn);
//        new CTCSRestClientUsage().RWSLYW(oThis,1,10,userAccoutn);
////        new CTCSRestClientUsage().RWSLGZWJ(oThis,1,10,userAccoutn);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        laodata();
//    }
//
//    public void onClick(View v) {
//        Intent intent = new Intent(oThis,PlanListActivity.class);
//        Intent intent1 = new Intent(oThis,PlanFaultListActivity.class);
//        switch (v.getId()){
//            case R.id.tv_work_wj:
//                intent.putExtra("type",2);
//                startActivity(intent);
//                break;
//            case R.id.tv_work_yw:
//                intent.putExtra("type",5);
//                startActivity(intent);
//                break;
//            case R.id.tv_work_zf:
//                intent.putExtra("type",4);
//                startActivity(intent);
//                break;
//            case R.id.tv_work_yj:
//                intent.putExtra("type",3);
//                startActivity(intent);
//                break;
//            case R.id.tv_fault_wj:
//                intent1.putExtra("type",2);
//                startActivity(intent1);
//                break;
//            case R.id.tv_fault_yw:
//                intent1.putExtra("type",5);
//                startActivity(intent1);
//                break;
//            case R.id.tv_fault_zf:
//                intent1.putExtra("type",4);
//                startActivity(intent1);
//                break;
//            case R.id.tv_fault_yj:
//                intent1.putExtra("type",3);
//                startActivity(intent1);
//                break;
//        }
//
//    }
//
//    @ViewInject(R.id.tv_fault_wj)
//    private TextView tv_fault_wj;
//    @ViewInject(R.id.tv_fault_yw)
//    private TextView tv_fault_yw;
//    @ViewInject(R.id.tv_fault_zf)
//    private TextView tv_fault_zf;
//    @ViewInject(R.id.tv_fault_yj)
//    private TextView tv_fault_yj;
//    @ViewInject(R.id.tv_work_wj)
//    private TextView tv_work_wj;
//    @ViewInject(R.id.tv_work_yw)
//    private TextView tv_work_yw;
//    @ViewInject(R.id.tv_work_zf)
//    private TextView tv_work_zf;
//    @ViewInject(R.id.tv_work_yj)
//    private TextView tv_work_yj;
//    @ViewInject(R.id.tv_work_wj_number)
//    public TextView tv_work_wj_number;
//    @ViewInject(R.id.tv_work_yj_number)
//    public TextView tv_work_yj_number;
//    @ViewInject(R.id.tv_work_yw_number)
//    public TextView tv_work_yw_number;
//    @ViewInject(R.id.tv_work_zf_number)
//    public TextView tv_work_zf_number;
//    @ViewInject(R.id.tv_fault_wj_number)
//    public TextView tv_fault_wj_number;
//}
