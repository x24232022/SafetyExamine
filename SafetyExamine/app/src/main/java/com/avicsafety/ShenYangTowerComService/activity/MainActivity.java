package com.avicsafety.ShenYangTowerComService.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.conf.Configuration;
import com.avicsafety.ShenYangTowerComService.conf.DbConfig;
import com.avicsafety.ShenYangTowerComService.service.LoginManager;
import com.avicsafety.lib.AutoUpdateManager.UpdateManager;
import com.avicsafety.lib.interfaces.OnNetworkAccessToMessageListener;
import com.avicsafety.lib.tools.SPUtils;
import com.avicsafety.lib.tools.ToastUtil;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.view_welcome)
public class MainActivity extends BaseActivity {

    String username = "";
    String password = "";
    String userId = "";

//    //声明碎片的数组
//    private static Fragment [] fragmentes;
//    //声明底部文字数组
//    private static String [] strings ;
//    //传递底部图片
//    private static int [][] icons_intent;

    //创建Tab图标数组
    private int[][] ICONS_RES = new int[][]{{R.mipmap.ic_home_normal, R.mipmap.ic_home_focus}, {R.mipmap.ic_mine_normal, R.mipmap.ic_mine_focus}};
    //创建碎片实例数组
    private Fragment[] fragments;
    //创建底部文字数组
    private String[] titles = new String[]{"功能", "设置"};

    /**
     * tab 颜色值
     */
    private final int[] TAB_COLORS = new int[]{
            R.color.main_bottom_tab_textcolor_normal,
            R.color.main_bottom_tab_textcolor_selected};
    private List<String> permissionList;

    @Override
    protected void init() {
        permissionGain();
        wel();
    }

    //软件运行权限动态获取
    private void permissionGain() {

        permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }
        //获取GPS
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
        //获取当前网络状态权限
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        //相机权限
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            permissionList.add(Manifest.permission.CAMERA);
        }

        //获取WIFI权限
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_WIFI_STATE);

        }
        //获取电话信息权限
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);

        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
        if (!permissionList.isEmpty()) {
            String[] pewmissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, pewmissions, 1);

        }


    }

    private void wel() {

        //获取权限后 建立所有路径
        Configuration.InitPath();
        DbConfig.initDb(oThis, Configuration.DB_NAME);

        Bundle bundle = oThis.getIntent().getExtras();
        if (bundle == null || bundle.getString("param") == null
                || !bundle.getString("param").equals("login")) {
            myHandler.sendEmptyMessage(2);
        } else {
            myHandler.sendEmptyMessage(1);
        }
    }

    @Override
    protected void InitializeComponent() {
        // TODO Auto-generated method stub
        super.InitializeComponent();
        this.setTitle("运维保障平台");
        toolbar.setNavigationIcon(null);

    }

    @Override
    protected void InitializeData() {
        super.InitializeData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                LoginManager.logout();
                //退出登陆时 初始化步骤 修改为0
                SPUtils.put(oThis, "init_step", 0);
                Intent intent = new Intent(oThis, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_exit:
                finish();
                System.exit(0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    wel();
                } else {
                    finish();
                }
                return;
            }
        }
    }


    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    if (!LoginManager.isLogined()) {
                        Intent intent = new Intent();
                        intent.setClass(oThis, LoginActivity.class);
                        oThis.startActivity(intent);


                    } else {
                        InitializeActionBar();
                        InitializeComponent();
                        InitializeData();
                        InitializeEvent();
                    }

                }
                break;
                case 2: {
                    //自动更新代码调整 2017-5-28 shili
                    final UpdateManager manager = new UpdateManager(oThis);
                    manager.setmDownloadPath(Configuration.DOWNLOAD_PATH);
                    //manager.checkUpdate();

                    //以前在 UpdateManager 弹出dialog会导致进度条被强制销毁 在这里访问更新服务器并进行回调
                    manager.checkNewVersion(new OnNetworkAccessToMessageListener() {
                        @Override
                        public void onSuccess(String message) {

//                   //有新版本 是否更新
                            AlertDialog.Builder builder = new AlertDialog.Builder(oThis);
                            builder.setTitle("版本更新");
                            builder.setMessage(message);
                            builder.setPositiveButton("确认更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    manager.update();
                                }
                            });

                            builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
                                    System.exit(0);
                                }
                            });
                            Dialog noticeDialog = builder.create();
                            noticeDialog.show();

                        }

                        @Override
                        public void onFail(String error) {
                            ToastUtil.showToast(oThis, error);
                           new Thread(new Runnable() {
                               @Override
                               public void run() {
                                   try {
                                       Thread.sleep(3000);
                                       myHandler.sendEmptyMessage(1);
                                   } catch (InterruptedException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }).start();
                        }
                    });


                }
                break;
                default:
                    break;
            }

        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
