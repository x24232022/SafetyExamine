package com.avicsafety.NewShenYangTowerComService.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.avicsafety.NewShenYangTowerComService.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2018/3/21.
 */

public class DateTimePickDialogUtil /*implements DatePicker.OnDateChangedListener,TimePicker.OnTimeChangedListener */{

//    private DatePicker datePicker;
//    private TimePicker timePicker;
//    private AlertDialog ad;
//    private String dateTime;
//    private String initDateTime;
//    private Activity activity;
//
//    /**
//     * 日期时间弹出选择框构造函数
//     *
//     * @param activity
//     *            ：调用的父activity
//     * @param initDateTime
//     *            初始日期时间值，作为弹出窗口的标题和日期时间初始值
//     */
//    public DateTimePickDialogUtil(Activity activity, String initDateTime) {
//        this.activity = activity;
//        this.initDateTime = initDateTime;
//
//    }
//
//    public void init(DatePicker datePicker, TimePicker timePicker) {
//        Calendar calendar = Calendar.getInstance();
//        if (!(null == initDateTime || "".equals(initDateTime))) {
//            calendar = this.getCalendarByInintData(initDateTime);
//        } else {
//            initDateTime = calendar.get(Calendar.YEAR) + "年"
//                    + calendar.get(Calendar.MONTH) + "月"
//                    + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
//                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
//                    + calendar.get(Calendar.MINUTE);
//        }
//
//        datePicker.init(calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH), this);
//        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
//        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
//    }
//
//    /**
//     * 弹出日期时间选择框方法
//     *
//     * @param inputDate
//     *            :为需要设置的日期时间文本编辑框
//     * @return
//     */
//    public AlertDialog dateTimePicKDialog(final EditText inputDate) {
//        LinearLayout dateTimeLayout = (LinearLayout) activity
//                .getLayoutInflater().inflate(R.layout.timedialog, null);
//        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.date_picker);
//        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.time_picker);
//        init(datePicker, timePicker);
//        timePicker.setIs24HourView(true);
//        timePicker.setOnTimeChangedListener(this);
//
//        ad = new AlertDialog.Builder(activity)
//                .setTitle(initDateTime)
//                .setView(dateTimeLayout)
//                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        inputDate.setText(dateTime);
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        inputDate.setText("");
//                    }
//                }).show();
//
//        onDateChanged(null, 0, 0, 0);
//        return ad;
//    }
//
//    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//        onDateChanged(null, 0, 0, 0);
//    }
//
//    public void onDateChanged(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//        // 获得日历实例
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(datePicker.getYear(), datePicker.getMonth(),
//                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
//                timePicker.getCurrentMinute());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
//
//        dateTime = sdf.format(calendar.getTime());
//        ad.setTitle(dateTime);
//    }
//
//    /**
//     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
//     *
//     * @param initDateTime
//     *            初始日期时间值 字符串型
//     * @return Calendar
//     */
//    private Calendar getCalendarByInintData(String initDateTime) {
//        Calendar calendar = Calendar.getInstance();
//
//        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
//        String date = spliteString(initDateTime, "日", "index", "front"); // 日期
//        String time = spliteString(initDateTime, "日", "index", "back"); // 时间
//
//        String yearStr = spliteString(date, "年", "index", "front"); // 年份
//        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日
//
//        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
//        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日
//
//        String hourStr = spliteString(time, ":", "index", "front"); // 时
//        String minuteStr = spliteString(time, ":", "index", "back"); // 分
//
//        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
//        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
//        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
//        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
//        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();
//
//        calendar.set(currentYear, currentMonth, currentDay, currentHour,
//                currentMinute);
//        return calendar;
//    }
//
//    /**
//     * 截取子串
//     *
//     * @param srcStr
//     *            源串
//     * @param pattern
//     *            匹配模式
//     * @param indexOrLast
//     * @param frontOrBack
//     * @return
//     */
//    public static String spliteString(String srcStr, String pattern,
//                                      String indexOrLast, String frontOrBack) {
//        String result = "";
//        int loc = -1;
//        if (indexOrLast.equalsIgnoreCase("index")) {
//            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
//        } else {
//            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
//        }
//        if (frontOrBack.equalsIgnoreCase("front")) {
//            if (loc != -1)
//                result = srcStr.substring(0, loc); // 截取子串
//        } else {
//            if (loc != -1)
//                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
//        }
//        return result;
//    }
public interface ResultHandler {
    void handle(String date,String time);
}

    private DatePicker datePicker;
    private TimePicker timePicker;
    private TextView tv_ok;
    private TextView tv_cancle;

    private ResultHandler handler;
    private String date;
    private String time;
    private Context context;
    private String initDateTime;


    private Dialog datePickerDialog;

    public DateTimePickDialogUtil(Context context, ResultHandler resultHandler, String initDateTime) {
        this.context = context;
        this.handler = resultHandler;
        this.initDateTime = initDateTime;
        initDialog();
    }

    private void initDialog() {
        if (datePickerDialog == null) {
            datePickerDialog = new Dialog(context, R.style.mytime_dialog);
            datePickerDialog.setCancelable(false);
            datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            datePickerDialog.setContentView(R.layout.timedialog);
            Window window = datePickerDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = dm.widthPixels;
            window.setAttributes(lp);
        }
        initView();
    }

    private void initView() {
        datePicker = (DatePicker) datePickerDialog.findViewById(R.id.date_picker);
        timePicker= (TimePicker) datePickerDialog.findViewById(R.id.time_picker);

        datePickerDialog.show();
        initDate(datePicker,timePicker);

    }

    public void initDate(DatePicker datePicker,TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {
            calendar = this.getCalendarByInintData(initDateTime);
        } else {
            initDateTime = calendar.get(Calendar.YEAR) + "年"
                    + calendar.get(Calendar.MONTH) + "月"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                      //  date=year+"年"+monthOfYear+"月"+dayOfMonth+"日";
                        Calendar calendar = Calendar.getInstance();

                        calendar.set(year, monthOfYear,
                                dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

                        date = sdf.format(calendar.getTime());
                    }
                });
        //timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time="\t"+hourOfDay+":"+minute;
            }
        });


    }


    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
     *
     * @param initDateTime
     *            初始日期时间值 字符串型
     * @return Calendar
     */
    private Calendar getCalendarByInintData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();

        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
        String date = spliteString(initDateTime, "日", "index", "front"); // 日期
        String time = spliteString(initDateTime, "日", "index", "back"); // 时间

        String yearStr = spliteString(date, "年", "index", "front"); // 年份
        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

        String hourStr = spliteString(time, ":", "index", "front"); // 时
        String minuteStr = spliteString(time, ":", "index", "back"); // 分

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay, currentHour,
                currentMinute);
        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr
     *            源串
     * @param pattern
     *            匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }


//    @Override
//    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
//        // 获得日历实例
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(datePicker.getYear(), datePicker.getMonth(),
//                datePicker.getDayOfMonth());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//
//        date = sdf.format(calendar.getTime());
//
//    }
//
//    @Override
//    public void onTimeChanged(TimePicker timePicker, int i, int i1) {
//        this.timePicker=timePicker;
//    }



}
