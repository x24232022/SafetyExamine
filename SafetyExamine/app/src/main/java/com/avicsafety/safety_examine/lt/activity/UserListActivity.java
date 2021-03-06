package com.avicsafety.safety_examine.lt.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.adapter.TreeAdapterN;
import com.avicsafety.safety_examine.lt.activity.ltUtil.CTCSRestClientUsage;
import com.avicsafety.safety_examine.lt.activity.ltUtil.Constants;
import com.avicsafety.safety_examine.model.MUsers;
import com.avicsafety.safety_examine.model.Mdept;
import com.avicsafety.safety_examine.model.Mgroup;
import com.avicsafety.safety_examine.model.NodeN;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘畅 on 2017/7/27.
 */

@ContentView(R.layout.tree_list)
public class UserListActivity extends BaseActivity {
    public ProgressDialog progressDialog;
    private int type = 0;// 0 部门 1分组 2 员工
    private String groupId, departmentId, locationValue, location;


    @ViewInject(R.id.code_list)
    private ListView code_list;

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        departmentId = Constants.getUserInfo(oThis).getDepartmentId();
        locationValue = Constants.getUserInfo(oThis).getLocationId();// getLocationId
        location = Constants.getUserInfo(oThis).getLocation();// getLocation
        setTitle(location);
        initData(locationValue);
    }

    public void setDept(List<Mdept> list) {
        // 创建根节点

        // 创建根节点
        NodeN root = new NodeN("部门", "0");
        root.setIcon(R.drawable.t_i_ckid);// 设置图标
        root.setCheckBox(false);// 设置节点前有无复选框

        List<NodeN> nodes = new ArrayList<NodeN>();

        for (int i = 0; i < list.size(); i++) {
            // 创建1级子节点
            NodeN n1 = new NodeN(list.get(i).getDepartmentName(), list.get(i)
                    .getDepartmentId());
            // n1.setParent(root);// 设置父节点
            n1.setIcon(R.drawable.icon_group);
            n1.setCheckBox(false);
            nodes.add(n1);
            root.add(n1);
        }

        TreeAdapterN ta = new TreeAdapterN(oThis, nodes);

        // 设置整个树是否显示复选框
        ta.setCheckBox(true);
        // 设置展开和折叠时图标
        ta.setExpandedCollapsedIcon(R.drawable.tree_ex, R.drawable.tree_ec);
        // 设置默认展开级别
        ta.setExpandLevel(1);

        code_list.setAdapter(ta);
    }

    // 设置节点,可以通过循环或递归方式添加节点
    public void setGroup(List<Mgroup> list) {
        // 创建根节点

        // 创建根节点
        NodeN root = new NodeN("分组", "0");
        root.setIcon(R.drawable.t_i_ckid);// 设置图标
        root.setCheckBox(false);// 设置节点前有无复选框

        List<NodeN> nodes = new ArrayList<NodeN>();

        for (int i = 0; i < list.size(); i++) {
            // 创建1级子节点
            NodeN n1 = new NodeN(list.get(i).getGroupName(), list.get(i)
                    .getGroupId());
            // n1.setParent(root);// 设置父节点
            n1.setIcon(R.drawable.icon_group);
            n1.setCheckBox(false);
            nodes.add(n1);
            root.add(n1);
        }

        TreeAdapterN ta = new TreeAdapterN(oThis, nodes);

        // 设置整个树是否显示复选框
        ta.setCheckBox(true);
        // 设置展开和折叠时图标
        ta.setExpandedCollapsedIcon(R.drawable.tree_ex, R.drawable.tree_ec);
        // 设置默认展开级别
        ta.setExpandLevel(1);

        code_list.setAdapter(ta);
    }

    // 设置节点,可以通过循环或递归方式添加节点
    public void setPreson(List<MUsers> list) {
        // 创建根节点

        // 创建根节点
        NodeN root = new NodeN("员工", "0", "0");
        root.setIcon(R.drawable.t_i_ckid);// 设置图标
        root.setCheckBox(false);// 设置节点前有无复选框

        List<NodeN> nodes = new ArrayList<NodeN>();

        for (int i = 0; i < list.size(); i++) {
            // 创建1级子节点
            NodeN n1 = new NodeN(list.get(i).getName(), list.get(i)
                    .getUserName(), list.get(i).getMobile());
            // n1.setParent(root);// 设置父节点
            n1.setIcon(R.drawable.icon_user);
            n1.setCheckBox(true);
            nodes.add(n1);
            root.add(n1);
        }

        TreeAdapterN ta = new TreeAdapterN(oThis, nodes);

        // 设置整个树是否显示复选框
        ta.setCheckBox(true);
        // 设置展开和折叠时图标
        ta.setExpandedCollapsedIcon(R.drawable.tree_ex, R.drawable.tree_ec);
        // 设置默认展开级别
        ta.setExpandLevel(1);

        code_list.setAdapter(ta);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.locationid, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_locationId:
                showSelList();
                break;
            case R.id.action_querentijiao:
                switch (type) {

                    case 0:

                        break;

                    case 1:
                        break;

                    case 2:
                        String name = "";
                        String userName = "";
                        String mobile = "";

                        List<NodeN> nodes = ((TreeAdapterN) code_list.getAdapter())
                                .getSeletedNodes();

                        if (nodes.size() > 0) {
                            for (int j = 0; j < nodes.size(); j++) {
                                NodeN n = nodes.get(j);

                                if (n.isChecked()) {
                                    name = name + "," + n.getValue();
                                    userName = userName + "," + n.getText();
                                    mobile = mobile + "," + n.getMobile();
                                }
                            }

                            name = name.replaceFirst(",", "");
                            userName = userName.replaceFirst(",", "");
                            mobile = mobile.replaceFirst(",", "");

                            System.out.println(name + userName + mobile);

                            Intent intent = new Intent(oThis,
                                    GDDealWithOneFileActivity.class);
                            intent.putExtra("uCNname", name);
                            intent.putExtra("uIDs", userName);
                            intent.putExtra("groupId", groupId);
                            intent.putExtra("mobile", mobile);
                            setResult(1000, intent);
                            finish();
                        } else {
                            Toast.makeText(oThis, "至少选择一位员工", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();



        code_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO 自动生成的方法存根

                switch (type) {

                    case 0:
                        type = 1;
                        NodeN n1 = (NodeN) code_list.getAdapter().getItem(arg2);
                        departmentId = n1.getValue();
                        initData(departmentId);
                        break;
                    case 1:
                        type = 2;
                        NodeN n2 = (NodeN) code_list.getAdapter().getItem(arg2);
                        groupId = n2.getValue();
                        initData(groupId);
                        break;

                    case 2:

                        // NodeN n3 = (NodeN) code_list.getAdapter().getItem(arg2);
                        //
                        // if (n3.isChecked()) {
                        // n3.setChecked(false);
                        // arg1.findViewById(R.id.chbSelect).setSelected(false);
                        // } else {
                        // n3.setChecked(true);
                        // arg1.findViewById(R.id.chbSelect).setSelected(true);
                        // }
                        //
                        // code_list.notifyAll();

                        break;
                }

                // 这句话写在最后面
                ((TreeAdapterN) arg0.getAdapter()).ExpandOrCollapse(arg2);
            }
        });

    }

    private void showSelList() {
        new AlertDialog.Builder(oThis)
                .setTitle("请选择地市")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setSingleChoiceItems(Constants.cities, 0,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                setTitle(Constants.cities[which]);

                                location = Constants.cityValues[which] + "";

                                initData(location);

                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", null).show();
    }

    private void initData(String id) {
        progressDialog = new MyProgressDialog(oThis, "获取中..");

        try {

            System.out.println("@@opFlag" + type + "@@id" + id);

            new CTCSRestClientUsage().getUser(oThis, type, id);
        } catch (JSONException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
