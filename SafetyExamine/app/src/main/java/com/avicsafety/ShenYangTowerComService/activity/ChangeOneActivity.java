package com.avicsafety.ShenYangTowerComService.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.CTCSRestClientUsage;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.dx.activity.MainActiity;
import com.avicsafety.ShenYangTowerComService.lt.activity.MainActivityss;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.model.Mrwsl;
import com.avicsafety.ShenYangTowerComService.model.MrwslTT;
import com.avicsafety.ShenYangTowerComService.xfd.PlanActivityXin;
import com.avicsafety.ShenYangTowerComService.xfd.Rwsl;
import com.avicsafety.ShenYangTowerComService.xfd.XinFDMethod;
import com.avicsafety.ShenYangTowerComService.yd.activity.MainActiitys;
import com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Constants;
import com.avicsafety.lib.tools.L;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 刘畅 on 2017/6/21.
 * 主页面
 */
@ContentView(R.layout.view_main)
public class ChangeOneActivity extends BaseActivity{

    private Toolbar toolbar;
    private TextView top_toolbar_title;
    private TextView tv_tips;
    public MyProgressDialog progressDialog;
    private MrwslTT mTT;
    private MUser userAccoutn;
    private MUser user;
    private String userid = "boot";

    public Rwsl getRwsl() {
        return rwsl;
    }

    public void setRwsl(Rwsl rwsl) {
        this.rwsl = rwsl;
    }

    private Rwsl rwsl;
    private List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();


    public String getXcode() {
        return xcode;
    }

    public void setXcode(String xcode) {
        this.xcode = xcode;
    }

    private String xcode;
    private String fdCode;
    private Mrwsl myd;
    private int zCode;

    public Mrwsl getMdx() {
        return mdx;
    }

    public void setMdx(Mrwsl mdx) {
        this.mdx = mdx;
    }

    private Mrwsl mdx;

    public Mrwsl getMlt() {
        return mlt;
    }

    public void setMlt(Mrwsl mlt) {
        this.mlt = mlt;
    }

    private Mrwsl mlt;

    public MrwslTT getMfd() {
        return mTT;
    }

    public void setMfd(MrwslTT TT) {
        mTT = TT;
    }
    public Mrwsl getMyd() {
        return myd;
    }
    public void setMyd(Mrwsl myd) {
        this.myd = myd;
    }


protected void init(){
    InitializeActionBar();
    InitializeComponent();
    InitializeEvent();
    InitializeData();
}
    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        progressDialog = new MyProgressDialog(oThis,"获取中....");
        userAccoutn = Constants.getUserInfo(oThis);

        L.v("Login username is "+userAccoutn);
    }

    @Override
    protected void InitializeActionBar() {
        super.InitializeActionBar();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        top_toolbar_title = (TextView) findViewById(R.id.tv_toolbar_title);
        tv_tips = (TextView) findViewById(R.id.tv_tips);

        top_toolbar_title.setText("运维保障平台");
        tv_tips.setText("请选择运营商!");
    }


    public void initGvMain(){
        /**
         * 设置图标,显示条数
         */

        listItems.removeAll(listItems);

        GridView gv_main = (GridView) findViewById(R.id.gv_main);

        int[] iv_btnImg = {R.drawable.fadiandelogo,R.drawable.img_x,R.drawable.img_z,
                R.drawable.img_w,R.drawable.xddttfd,R.drawable.work};

        String[] tv_btnName = {"发电","移动","电信","联通","新发电","工单预览"};

        fdCode = getMfd().getTotal();
        int fdNumberCode = Integer.parseInt(fdCode);
        int ddNumberCode = Integer.parseInt(xcode);
        zCode = fdNumberCode + ddNumberCode ;

        for (int i = 0; i < iv_btnImg.length ; i++) {
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("iv_btnImg", iv_btnImg[i]);
            map.put("tv_btnName", tv_btnName[i]);

            switch (i) {
                // 发电
                case 0:
//                    map.put("tv_count", getMdx().getTotal());
//                    map.put("tv_count", getMfd().getTotal());
                    map.put("tv_count",zCode);
                    break;
                // 移动
                case 1:
                    map.put("tv_count", getMyd().getTotal());
                    break;

                // 电信
                case 2:
                    map.put("tv_count", getMdx().getTotal());
                    break;
                //联通
                case 3:
                    map.put("tv_count", getMlt().getTotal());
                    break;
                case 4:
                    map.put("tv_count", rwsl.getSygd());
                    break;
                case 5:
                    map.put("tv_count",rwsl.getYlgd());
            }

            listItems.add(map);
        }

        SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
                R.layout.item_gv_main,
                new String[]{"iv_btnImg","tv_btnName", "tv_count"},
                new int[]{R.id.iv_btnImg, R.id.tv_btnName, R.id.tv_count});

        gv_main.setAdapter(mainListSA);

        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO 自动生成的方法存根

                switch (arg2) {

                    // 发电
                    case 0:
//                        startActivity(new Intent(oThis, MainActiity.class));
                        startActivity(new Intent(oThis, TowerMainActivity.class));
                        break;
                    // 移动
                    case 1:
                        startActivity(new Intent(oThis, MainActiitys.class));
                        break;
                    // 电信
                    case 2:
                        startActivity(new Intent(oThis, MainActiity.class));
                        break;
                    // 联通
                    case 3:
                        startActivity(new Intent(oThis, MainActivityss.class));
                        break;
                    // 新发电的
                    case 4:
                        startActivity(new Intent(oThis,PlanActivityXin.class));
                        //TODO 手动更新代码
//                        UpdateManager manager = new UpdateManager(oThis);
//                        manager.checkUpdate();
                        break;
                    case 5:
                        startActivity(new Intent(oThis,TomorrowTitleListActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void initDatafd() {
        try {
            new CTCSRestClientUsage().GetRWSL(oThis,2);
            new CTCSRestClientUsage().GetXDDRWSL(oThis,userAccoutn.getUserName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void initDatayd(){
        try {
            new com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.CTCSRestClientUsage().GetRWSL(oThis,2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void initDatadx(){
        try {
            new com.avicsafety.ShenYangTowerComService.dx.activity.dxUtil.CTCSRestClientUsage().GetRWSL(oThis,2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void initDatalt(){
        try {
            new com.avicsafety.ShenYangTowerComService.lt.activity.ltUtil.CTCSRestClientUsage().GetRWSL(oThis,2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void initDataXfd(){
        new XinFDMethod().GetXinFdRWSL(oThis,userAccoutn.getUserName(),2);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();

    }



    @Override
    protected void onResume() {
        super.onResume();
        initDatafd();
    }
}
