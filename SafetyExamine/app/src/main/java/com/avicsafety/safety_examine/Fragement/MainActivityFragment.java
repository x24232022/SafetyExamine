package com.avicsafety.safety_examine.Fragement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.avicsafety.lib.Adapter.CommonAdapter;
import com.avicsafety.lib.Adapter.ViewHolder;
import com.avicsafety.lib.tools.ToastUtil;
import com.avicsafety.safety_examine.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.fragment_main)
public class MainActivityFragment extends BaseFragment {


    private List<Map<String, Object>> data_list;
    private int[] icons = {R.mipmap.enterprise, R.mipmap.newly_build, R.mipmap.law_enforcement, R.mipmap.hidden_danger,
            R.mipmap.statute, R.mipmap.data, R.mipmap.notepad, R.mipmap.photo, R.mipmap.mail_list, R.mipmap.accident, R.mipmap.study,
            R.mipmap.number, R.mipmap.emergency};
    private String[] iconName = {"企业管理", "新建查询", "执法检查", "隐患复查", "法规反查", "数据同步", "记事本",
            "快速取证", "通讯录", "事故案例", "法规学习", "企业证号", "应急信息"};

    private CommonAdapter<Map<String, Object>> mAdapter;
    @ViewInject(R.id.grid_view)
    private GridView grid_view;


    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();


    }

    @Override
    protected void InitializeData() {
        super.InitializeData();
        data_list = getData(new String[] {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"});
        mAdapter = new CommonAdapter<Map<String, Object>>(
                oThis.getApplicationContext(), data_list,
                R.layout.item_function) {
            @Override
            public void convert(ViewHolder helper, Map<String, Object> item) {
                InputStream is = getResources().openRawResource(
                        (Integer) item.get("image"));
                Bitmap mBitmap = BitmapFactory.decodeStream(is);
                helper.setImageBitmap(R.id.image, mBitmap);
                helper.setText(R.id.text, item.get("text").toString());
            }
        };
        grid_view.setAdapter(mAdapter);
        setHeightBasedOnChildren(grid_view);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
//                        Toast.makeText(oThis,"功能1",Toast.LENGTH_SHORT).show();
                       // ToastUtil.showToast(oThis,"功能1");
                        //Intent intent1=new Intent(getActivity(), CompanyListActivity.class);
                        //startActivity(intent1);
                        break;
                    case 1:
//                        Toast.makeText(oThis,"功能2",Toast.LENGTH_SHORT).show();
                      // ToastUtil.showToast(oThis,"功能2");
                        //Intent intent2=new Intent(getActivity(), TaskListActivity.class);
                       // startActivity(intent2);
                        break;
                    case 2:
//                        Toast.makeText(oThis,"功能3",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能3");
                        break;
                    case 3:
//                        Toast.makeText(oThis,"功能4",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能4");
                        break;
                    case 4:
//                        Toast.makeText(oThis,"功能5",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能5");
                        break;
                    case 5:
//                        Toast.makeText(oThis,"功能6",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能6");
                        break;
                    case 6:
//                        Toast.makeText(oThis,"功能7",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能7");
                        break;
                    case 7:
//                        Toast.makeText(oThis,"功能8",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能8");
                        break;
                    case 8:
//                        Toast.makeText(oThis,"功能9",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能9");
                        break;
                    case 9:
//                        Toast.makeText(oThis,"功能10",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能10");
                        break;
                    case 10:
//                        Toast.makeText(oThis,"功能11",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能11");
                        break;
                    case 11:
//                        Toast.makeText(oThis,"功能12",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能12");
                        break;
                    case 12:
//                        Toast.makeText(oThis,"功能13",Toast.LENGTH_SHORT).show();
                        ToastUtil.showToast(oThis,"功能13");

                        break;
                }
            }
        });
    }

    public List<Map<String, Object>> getData(String[] counters) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icons[i]);
            map.put("text", iconName[i]);
            map.put("counter", counters[i]);
            list.add(map);
        }
        return list;
    }


    public void setHeightBasedOnChildren(GridView grid_view) {
        // 获取listview的adapter
        ListAdapter listAdapter = grid_view.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, grid_view);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = grid_view.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        //((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        grid_view.setLayoutParams(params);
    }
}
