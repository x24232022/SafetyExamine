package com.avicsafety.safety_examine.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.CTCSRestClientUsage;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.dx.activity.MainActiity;
import com.avicsafety.safety_examine.lt.activity.MainActivityss;
import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.safety_examine.model.Mrwsl;
import com.avicsafety.safety_examine.model.MrwslTT;
import com.avicsafety.safety_examine.model.ProspectBean;
import com.avicsafety.safety_examine.model.ProspectTotal;
import com.avicsafety.safety_examine.prospect.NetWork;
import com.avicsafety.safety_examine.prospect.ProspectListActivity;
import com.avicsafety.safety_examine.prospect.TransmitMsg;
import com.avicsafety.safety_examine.xfd.PlanActivityXin;
import com.avicsafety.safety_examine.xfd.Rwsl;
import com.avicsafety.safety_examine.xfd.XinFDMethod;
import com.avicsafety.safety_examine.yd.activity.MainActiitys;
import com.avicsafety.safety_examine.yd.activity.ydUtil.Constants;
import com.avicsafety.lib.tools.L;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 刘畅 on 2017/6/21.
 * 主页面
 */
@ContentView(R.layout.view_main)
public class ChangeOneActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView top_toolbar_title;
    private TextView tv_tips;
    public MyProgressDialog progressDialog;
    private MrwslTT mTT;
    private MUser userAccoutn;
    private MUser user;
    private String userid = "boot";
    private ProspectTotal mTotal;


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

    public static void startActivity(Activity activity){
        Intent intent=new Intent(activity,ChangeOneActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    protected void init() {
        InitializeActionBar();
        InitializeComponent();
        InitializeEvent();
        InitializeData();
    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        progressDialog = new MyProgressDialog(oThis, "获取中....");
        userAccoutn = Constants.getUserInfo(oThis);
        GetWorkOrderNumber(userAccoutn.getUserName(), 0);

        L.v("Login username is " + userAccoutn);
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


    public void initGvMain() {
        /**
         * 设置图标,显示条数
         */

        listItems.removeAll(listItems);

        GridView gv_main = (GridView) findViewById(R.id.gv_main);

        int[] iv_btnImg = {R.drawable.fadiandelogo, R.drawable.img_x, R.drawable.img_z,
                R.drawable.img_w, R.drawable.xddttfd, R.drawable.work, R.drawable.prospect};

        String[] tv_btnName = {"发电", "移动", "电信", "联通", "新发电", "工单预览", "勘察"};

        fdCode = getMfd().getTotal();
        int fdNumberCode = fdCode == null ? 0 : Integer.parseInt(fdCode);
        int ddNumberCode = xcode == null ? 0 : Integer.parseInt(xcode);
        zCode = fdNumberCode + ddNumberCode;

        for (int i = 0; i < iv_btnImg.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("iv_btnImg", iv_btnImg[i]);
            map.put("tv_btnName", tv_btnName[i]);

            switch (i) {
                // 发电
                case 0:

                    map.put("tv_count", zCode);

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
                    //现在获取不到，
                    map.put("tv_count", getMlt().getTotal());

                    break;
                case 4:
                    map.put("tv_count", rwsl.getSygd());

                    break;
                case 5:
                    map.put("tv_count", rwsl.getYlgd());
                    break;
                case 6:
                    map.put("tv_count", mTotal.getTotal());
                    break;

            }

            listItems.add(map);
        }


        SimpleAdapter mainListSA = new SimpleAdapter(this, listItems,
                R.layout.item_gv_main,
                new String[]{"iv_btnImg", "tv_btnName", "tv_count"},
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
                        startActivity(new Intent(oThis, PlanActivityXin.class));
                        //TODO 手动更新代码
                        break;

                    case 5:
                        startActivity(new Intent(oThis, TomorrowTitleListActivity.class));
                        break;

                    case 6:
                        startActivity(new Intent(oThis, ProspectListActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }




    public void initDatafd() {
        try {
            new CTCSRestClientUsage().GetRWSL(oThis, 2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initDatayd() {
        try {
            new com.avicsafety.safety_examine.yd.activity.ydUtil.CTCSRestClientUsage().GetRWSL(oThis, 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initDatadx() {
        try {
            new com.avicsafety.safety_examine.dx.activity.dxUtil.CTCSRestClientUsage().GetRWSL(oThis, 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initDatalt() {
        try {
            new com.avicsafety.safety_examine.lt.activity.ltUtil.CTCSRestClientUsage().GetRWSL(oThis, 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initDataXfd() {
        new XinFDMethod().GetXinFdRWSL(oThis, userAccoutn.getUserName(), 2);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initDatafd();
    }

    //-------------------------获取工单数量---------------------------
    public void GetWorkOrderNumber(String userid, int type) {
        RequestParams params = new RequestParams(NetWork.getNetWork().BASE_URL);
        params.addParameter("userid", userid);
        params.addParameter("type", type);
        params.addParameter("s", "ss");
        params.setConnectTimeout(60000);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mTotal = JSON.parseObject(result, ProspectTotal.class);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
