//package com.avicsafety.ShenYangTowerComService.activity;
//
//import android.content.Intent;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Constants;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.ToastUtils;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Utils;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.View.AbPullToRefreshView;
//import com.avicsafety.ShenYangTowerComService.PowerManager.push.presenter.SubAddressPresenter;
//import com.avicsafety.ShenYangTowerComService.R;
//import com.avicsafety.ShenYangTowerComService.adapter.PowerAdapter;
//import com.avicsafety.ShenYangTowerComService.model.PowerWork;
//import com.avicsafety.lib.CustomView.AvicSelectButton;
//import com.avicsafety.lib.tools.L;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.reflect.TypeToken;
//import com.ydtx.powermanger.Module.BaseModule;
//
//import org.xutils.common.Callback;
//import org.xutils.http.RequestParams;
//import org.xutils.view.annotation.ContentView;
//import org.xutils.view.annotation.ViewInject;
//import org.xutils.x;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by 刘畅 on 2017/8/25.
// */
//@ContentView(R.layout.activity_power_list)
//public class PlanListActivity extends BaseActivity implements AdapterView.OnItemClickListener,AbPullToRefreshView.OnHeaderRefreshListener,AbPullToRefreshView.OnFooterLoadListener {
//    @ViewInject(R.id.mRefreshView)
//    AbPullToRefreshView mRefreshView;//上下拉刷新
//    @Nullable//防止缺少id造成崩溃
//    @ViewInject(R.id.list_power)
//    ListView mListView;//工单数据
//    @Nullable//防止缺少id造成崩溃
//    @ViewInject(R.id.tv_prompt)
//    TextView tv_prompt;
//
//    private List<PowerWork> list=new ArrayList<PowerWork>();
//    //list展示的数据
//    private List<PowerWork> datas=new ArrayList<PowerWork>();
//    private int pageNo=1;
//    private int pageCount;
//    private PowerAdapter adapter;
//    private String userAccoutn;
//    private BaseModule.SubAddress subs;
//    private Map<String, String> params=new HashMap<String, String>();
//    private String path= Constants.AUDITED_POWER_WORK;
//    private int type=2;
//
//
//    @Override
//    protected void InitializeEvent() {
//        super.InitializeEvent();
//        mListView.setOnItemClickListener(this);
//    }
//
//    @Override
//    protected void InitializeComponent() {
//        super.InitializeComponent();
//        setTitle("计划工单");
//        mRefreshView.setOnHeaderRefreshListener(this);
//        mRefreshView.setOnFooterLoadListener(this);
//        userAccoutn= Utils.readOAuth(this).getAccount();
//        if(getIntent()!=null){
//            type=getIntent().getIntExtra("type", 2);
//        }
//        subs= SubAddressPresenter.getInstance();
//    }
//    @Override
//    protected void onStart() {
//        pageNo=1;
//        loadDate();
//        super.onStart();
//    }
//
//    private void loadDate() {
//        RequestParams params = new RequestParams(path);
//        params.addParameter("pageNo", pageNo+"");
//        params.addParameter("pageSize", "10");
//        params.addParameter("fapgperson",userAccoutn);
//        params.addParameter("orderStates",type+"");
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                try {
//                    parserJson(result);
//                } catch (Exception e) {
//                    ToastUtils.showToast(oThis, "数据解析出错!");
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                int count=mListView.getChildCount();
//                if(count==0){
//                    tv_prompt.setVisibility(View.VISIBLE);
//                }
//                L.v("PlanListActivityError!!~:",ex.getMessage());
//                Utils.hideProgress(oThis);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
//    private void parserJson(String response) throws Exception {
//        Gson gson=new Gson();
//        JsonParser jsonParser=new JsonParser();
//        JsonElement jsonElement=jsonParser.parse(response);
//        JsonObject jsonObject=jsonElement.getAsJsonObject();
//        int dataCcount=jsonObject.get("totalRows").getAsInt();
//        pageCount=Utils.totalPages(dataCcount);
//        JsonArray jar=jsonObject.getAsJsonArray("rows");
//        int size=jar.size();
//        if(size>0){
//            if(pageNo==1){
//                datas.clear();
//            }
//            list=gson.fromJson(jar,new TypeToken<List<PowerWork>>(){}.getType());
//            datas.addAll(list);
//            tv_prompt.setVisibility(View.GONE);
//        }else{
//            datas.clear();
//            ToastUtils.showToast(oThis, "未查找到数据!");
//            tv_prompt.setVisibility(View.VISIBLE);
//        }
//        if(adapter==null){
//            adapter=new PowerAdapter(oThis, datas,1);
//            mListView.setAdapter(adapter);
//        }else{
//            adapter.setList(datas);
//            adapter.notifyDataSetChanged();
//        }
//        if(pageNo>=pageCount){
//            mRefreshView.setLoadMoreEnable(false);
//        }
//    }
//
//
//    @Override
//    public void onFooterLoad(AbPullToRefreshView view) {
//        if(pageNo<pageCount){
//            pageNo+=1;
//            loadDate();
//        }else{
//            Utils.hideRefresh(mRefreshView);
//        }
//    }
//
//    @Override
//    public void onHeaderRefresh(AbPullToRefreshView view) {
//        mRefreshView.setLoadMoreEnable(true);
//        pageNo=1;
//        loadDate();
//        Utils.hideRefresh(mRefreshView);
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        PowerWork pb=(PowerWork) adapter.getItem(position);
//        Intent intent=new Intent(this,PlanDetailsActivity.class);
//        pb.setType("power");
//        intent.putExtra("pb", pb);
//        startActivity(intent);
//    }
//}
