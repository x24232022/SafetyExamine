//package com.avicsafety.ShenYangTowerComService.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.avicsafety.ShenYangTowerComService.R;
//import com.avicsafety.lib.CustomView.AvicInputActivity;
//import com.avicsafety.lib.CustomView.AvicTextView;
//import com.avicsafety.ShenYangTowerComService.conf.Configuration;
//import com.avicsafety.lib.interfaces.OnNetworkAccessToModelListener;
//import com.avicsafety.ShenYangTowerComService.model.M_IpLib;
//import com.avicsafety.ShenYangTowerComService.service.LoginManager;
//
//import org.apache.commons.lang3.StringUtils;
//import org.xutils.view.annotation.ContentView;
//import org.xutils.view.annotation.ViewInject;
//
//@ContentView(R.layout.activity_server_config)
//public class ServerConfigActivity extends BaseActivity implements View.OnClickListener {
//
//    private int clickCount = 0;
//
//    @Override
//    protected void InitializeComponent() {
//        super.InitializeComponent();
//        this.setTitle("服务器设置");
//    }
//
//    @Override
//    protected void InitializeData() {
//        super.InitializeData();
//        String ipaddr = LoginManager.getServerIpAddr();
//        String[] ipinfo = new String[]{"",""};
//        if(ipaddr.contains(":")){
//            atv_server_config_ip.setText(ipaddr.split(":")[0]);
//            atv_server_config_port.setText(ipaddr.split(":")[1]);
//        }else{
//            atv_server_config_ip.setText(ipaddr);
//        }
//    }
//
//    @Override
//    protected void InitializeEvent() {
//        super.InitializeEvent();
//        atv_server_config_ip.setOnClickListener(this);
//        atv_server_config_port.setOnClickListener(this);
//        if(Configuration.DEBUG){
//            tv_server_config_tip.setOnClickListener(this);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.atv_server_config_ip: {
//                Intent intent = new Intent(this, AvicInputActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("title", atv_server_config_ip.getLable());
//                bundle.putString("value", atv_server_config_ip.getValue());
//                bundle.putInt("type", AvicInputActivity.TYPE.STRING);
//                bundle.putString("help", "服务器的IP地址:例如:192.168.1.1");
//                intent.putExtras(bundle);
//                startActivityForResult(intent, 8001);
//                break;
//            }
//            case R.id.atv_server_config_port: {
//                Intent intent = new Intent(this, AvicInputActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("title", atv_server_config_port.getLable());
//                bundle.putString("value", atv_server_config_port.getValue());
//                bundle.putInt("type", AvicInputActivity.TYPE.STRING);
//                bundle.putString("help", "服务器的端口号:例如:8090, 若不填写默认为80");
//                intent.putExtras(bundle);
//                startActivityForResult(intent, 8002);
//                break;
//            }
//            case R.id.tv_server_config_tip:{
//                if(clickCount>3) {
//                    atv_server_config_ip.setText("218.25.87.62");
//                    atv_server_config_port.setText("208");
//                }
//                clickCount++;
//                break;
//            }
//        }
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case 8001: {
//                if (resultCode == RESULT_OK) {
//                    atv_server_config_ip.setText(data.getStringExtra("value"));
//                }
//                break;
//            }
//            case 8002: {
//                if (resultCode == RESULT_OK) {
//                    atv_server_config_port.setText(data.getStringExtra("value"));
//                }
//                break;
//            }
//            case 2019: {
//                if (resultCode == RESULT_OK) {
//                    finish();
//                }
//                break;
//            }
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.server_config, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_save:
//                String ip = atv_server_config_ip.getValue();
//                String port = atv_server_config_port.getValue();
//                if(Configuration.CHECK_SERVER_IP) {
//                    if (StringUtils.isBlank(ip)) {
//                        Toast.makeText(oThis, "请填写服务器端口信息", Toast.LENGTH_SHORT).show();
//                    } else {
//                        loadingDialog.show();
//                        if (StringUtils.isBlank(port)) {
//                            port = "80";
//                        }
//                        LoginManager.checkServerIpAddr(oThis, ip, port, new OnNetworkAccessToModelListener<M_IpLib>() {
//                            @Override
//                            public void onSuccess(M_IpLib model) {
//                                loadingDialog.close();
//                                Toast.makeText(oThis, model.getName() + "服务器设置成功!", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//
//                            @Override
//                            public void onFail(String error) {
//                                loadingDialog.close();
//                                Toast.makeText(oThis, error, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                    }
//                }else{
//                    M_IpLib model = new M_IpLib();
//                    model.setName("CLOUND");
//                    model.setEnname("云端");
//                    model.setIp(ip+":"+port);
//                    LoginManager.setServerIpAddr(model);
//                    Toast.makeText(oThis, model.getName()+"服务器设置成功!", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                break;
//            case R.id.action_search:{
////                Intent intent = new Intent(oThis, ServerSearchActivity.class);
////                startActivityForResult(intent, 2019);
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//
//
//    /////////////////////////////
//
//
//    @ViewInject(R.id.atv_server_config_ip)
//    private AvicTextView atv_server_config_ip;
//
//    @ViewInject(R.id.atv_server_config_port)
//    private AvicTextView atv_server_config_port;
//
//    @ViewInject(R.id.tv_server_config_tip)
//    private TextView tv_server_config_tip;
//
//}
