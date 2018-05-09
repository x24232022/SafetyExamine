package com.avicsafety.ShenYangTowerComService.xfd;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.Utils.OnRecyclerItemClickListener;
import com.avicsafety.ShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.ShenYangTowerComService.adapter.PlanAdapter;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.view.DateTimePickerDialog;
import com.avicsafety.ShenYangTowerComService.view.DividerItemDecoration;
import com.avicsafety.ShenYangTowerComService.view.SwipeRecyclerView;
import com.avicsafety.lib.tools.L;


import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by 刘畅 on 2017/12/26.
 */
@ContentView(R.layout.n_activity_rwlb1)
public class PlanListActivityXin extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.rcv_gdlb)
    private SwipeRecyclerView rcy_gdlb;
    @ViewInject(R.id.btn_page)
    private Button btn_page;
    @ViewInject(R.id.btn_pro)
    private TextView btn_pro;
    @ViewInject(R.id.btn_next)
    private TextView btn_next;
    public MyProgressDialog progressDialog;
    private List<Rwlb.ResponseBean> listItems;
    private int nowStart = 0, nextStart;
    private int totalC = 0;// 总条数
    private int listtype = 0;
    private PlanAdapter mAdapter;
    public static MUser userAccoutn;
    private ItemTouchHelper mItemTouchHelper;
    private EditText etDate;
    private EditText etTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private String timeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        listtype = getIntent().getIntExtra("listtype", 0);

    }

    //设置菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (listtype == 0) {
            getMenuInflater().inflate(R.menu.towermain1, menu);
        } else {
            getMenuInflater().inflate(R.menu.towermain, menu);
        }

        return true;
    }

    //设置菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                initData(nowStart);
                break;

            case R.id.action_sendLocation:

                showDialog();


                break;
            case R.id.action_inquire:
                Intent intent = new Intent(oThis, InquireActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //初始化提示框
    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(PlanListActivityXin.this);
        View view = View.inflate(PlanListActivityXin.this, R.layout.dialog, null);
        etDate = (EditText) view.findViewById(R.id.et_setdate);
        etTime = (EditText) view.findViewById(R.id.et_settime);

        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                    }
                };
                TimePicker.OnTimeChangedListener onTimeChangedListener = new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;
                    }
                };
                DialogInterface.OnClickListener yesListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String time = showTimeMsg(mYear, mMonth, mDay, mHour, mMinute);
                        etDate.setText(time);
                    }
                };
                DialogInterface.OnClickListener noListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                DateTimePickerDialog dialog = new DateTimePickerDialog(oThis, onDateChangedListener, onTimeChangedListener, yesListener, noListener, true);

            }


        });


        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                listItems.get(0).setEdeparturetime(etDate.getText().toString());
                listItems.get(0).setInterstationtime(etTime.getText().toString());
                uploadLocation();
                Toast.makeText(getApplicationContext(), "发送成功",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setView(view);
        builder.show();


    }

    private String showTimeMsg(int year, int month, int day, int hour, int minute) {
        StringBuffer dateTime = new StringBuffer();
        dateTime.append(year);
        dateTime.append("-");
        if (month < 10) {
            dateTime.append("0" + (month + 1));
        } else {
            dateTime.append((month + 1));
        }

        dateTime.append("-");
        if (day < 10) {
            dateTime.append("0" + day);
        } else {
            dateTime.append(day);
        }

        dateTime.append(" ");
        if (hour < 10) {
            dateTime.append("0" + hour);
        } else {
            dateTime.append(hour);
        }
        dateTime.append(":");
        if (minute < 10) {
            dateTime.append("0" + minute);
        } else {
            dateTime.append(minute);
        }
        timeMsg = String.valueOf(dateTime);
        return timeMsg;
    }
    private void initData(int start) {
        nowStart = start;
        nextStart = start + 15;

        progressDialog = new MyProgressDialog(oThis, "获取中..");

        loadDate();

    }

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("任务列表");
        userAccoutn = com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis);

    }

    //网络请求工单数据
    public void loadDate() {
        RequestParams params = new RequestParams(Constants.BASE_URL);
        params.setConnectTimeout(60000);
        params.addParameter("userid", userAccoutn.getUserName());
        params.addBodyParameter("blackoutdate","2018-03-30");
        params.addParameter("type", 3);
        if (listtype == 0) {
            params.addParameter("typeinfo", "wjgd");
        } else if (listtype == 1) {
            params.addParameter("typeinfo", "ycfgd");
        } else if (listtype == 2) {
            params.addParameter("typeinfo", "ydzgd");
        } else if (listtype == 3) {
            params.addParameter("typeinfo", "yjsgd");
        }

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");

                        if (code.equals("200")) {
                            String datas = res.getString("Response").toString();
                            // 登录成功保存用户信息

                            listItems=JSON.parseArray(datas,Rwlb.ResponseBean.class);

                        initList(res.getInt("total"));

                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(oThis,
                                (CharSequence) res.get("Msg"),
                                Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                L.v("PlanListActivityError!!~:", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        btn_next.setOnClickListener(this);
        btn_pro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        btn_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(nowStart - 15);
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(nextStart);
            }
        });
    }

    //设置翻页按钮点击监听
    public void initList(int count) {
        // TODO 自动生成的方法存根
        totalC = count;

        if (totalC >= 0) {
            btn_page.setVisibility(View.VISIBLE);


            if (nowStart <= 0) {
                btn_pro.setVisibility(View.INVISIBLE);
            } else {
                btn_pro.setVisibility(View.VISIBLE);
            }


            Log.d("totalC", totalC + "");

            if ((totalC) % 15 == 0) {
                btn_page.setText((nowStart / 15 + 1) + "/" + totalC / 15);

                if ((nowStart / 15 + 1) == totalC / 15) {
                    btn_next.setVisibility(View.INVISIBLE);
                } else {
                    btn_next.setVisibility(View.VISIBLE);
                }

            } else {
                btn_page.setText((nowStart / 15 + 1) + "/" + (totalC / 15 + 1));

                if ((nowStart / 15 + 1) == (totalC / 15 + 1)) {
                    btn_next.setVisibility(View.INVISIBLE);
                } else {
                    btn_next.setVisibility(View.VISIBLE);
                }
            }
            initRecyclerView();
        }
    }

    //设置RecyclerView初始化集合参数
    private void initRecyclerView() {

        rcy_gdlb.setLayoutManager(new LinearLayoutManager(this));
        //设置点击跳转到工单详情页
        rcy_gdlb.addOnItemTouchListener(new OnRecyclerItemClickListener(rcy_gdlb) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {


                startActivity(new Intent(oThis,
                        PlanXqActivity.class).
                        putExtra("id", listItems.get(vh.getLayoutPosition()).getTicketid()).
                        putExtra("taskType", vh.getLayoutPosition()).
                        putExtra("activityId","0").
                        putExtra("blackoutdate","").
                        putExtra("url", Constants.BASE_URL));

                // Toast.makeText(mContext, datas.get(vh.getLayoutPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
                if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                    mItemTouchHelper.startDrag(vh);

                    //获取系统震动服务
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                    vib.vibrate(70);

                }
            }
        });
        //设置滑动拖拽时间
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            /**
             * 是否处理滑动事件 以及拖拽和滑动的方向 如果是列表类型的RecyclerView的只存在UP和DOWN，如果是网格类RecyclerView则还应该多有LEFT和RIGHT
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(listItems, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(listItems, i, i - 1);
                    }
                }
                mAdapter.notifyItemMoved(fromPosition, toPosition);

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            /**
             * 重写拖拽可用
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        });
        mItemTouchHelper.attachToRecyclerView(rcy_gdlb);
        mAdapter = new PlanAdapter(this, listItems);
        rcy_gdlb.setAdapter(mAdapter);
        //设置侧滑删除时间
        rcy_gdlb.setRightClickListener(new SwipeRecyclerView.OnRightClickListener() {
            @Override
            public void onRightClick(int position, String id) {
                listItems.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
        rcy_gdlb.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    //上传工单列表位置信息
    private void uploadLocation() {
        //获取当前数据上传
        List<Rwlb.ResponseBean> list = new ArrayList<>();
        for (int i = 0; i < listItems.size(); i++) {
            listItems.get(i).setSortfield(Integer.toString(i + 1));
            list.add(listItems.get(i));
        }
        String jsonString = JSON.toJSONString(list);
        Log.i("_________msg__________", jsonString);
        RequestParams params = new RequestParams(Constants.BASE_URL/*?type=9\n"*/);//"http://192.168.1.121:8080/phoneServices/fd/geographicalPositionReceiveServlet?type=9\n"
        params.addParameter("type",9);
        params.addParameter("jsonBean", jsonString);
        params.setConnectTimeout(60000);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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

    @Override
    protected void onResume() {
        // TODO 自动生成的方法存根
        super.onResume();
        initData(nowStart);
    }

    //设置返回按钮点击事件
    @Override
    public void onBackPressed() {

        PlanListActivityXin.this.finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlanListActivityXin.this.finish();
    }
}
