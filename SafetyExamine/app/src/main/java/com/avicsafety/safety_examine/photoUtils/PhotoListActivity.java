package com.avicsafety.safety_examine.photoUtils;


import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.avicsafety.safety_examine.PowerManager.push.Utils.Utils;
import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.model.Location;
import com.avicsafety.safety_examine.model.AddressBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

@ContentView(R.layout.activity_com_avicsafety_photo_photomanager_photo_list_lc)
public class PhotoListActivity extends BaseActivity {
    private String mAddr;
    private String mAddName;

    private File tempFile;
    private String newFilePath = "";
    private String DEFAULT_PATH = "";
    private List<String> mData = new ArrayList<String>();
    private List<String> selectedData = new ArrayList<String>();
    private int max = 0;
    private int min = 0;
    private double latitude;
    private double longitude;

    private final int PHOTO_REQUEST_TAKEPHOTO = 9000;
    private final int PHOTO_REQUEST_CUT = 9001;
    private String address;
    private static final String[] STORE_IMAGES = {
            MediaStore.Images.Thumbnails.DATA
    };
    private PhotoAdapter photoAdapter = null;

    @ViewInject(R.id.grid_view)
    private GridView grid_view;
    @ViewInject(R.id.btn_photo_list_take_photo)
    private Button btn_photo_list_take_photo;
    private Bitmap mTextBitmap;
    private String mTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);


    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        getAddress(String.valueOf(latitude), String.valueOf(longitude));
        address = mAddr;
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        mTime = year + "/" + month + "/" + date + " " + hour + ":" + minute + ":" + second;
    }

    private void getAddress(String log, final String lat) {
        RequestParams params = new RequestParams("http://api.map.baidu.com/geocoder/v2/?ak=BkR4Stg163OYDwt9I6WHTsZ2tdRttBWv&callback=renderReverse&location=" + lat + "," + log + "&output=json&pois=1\n");
        params.setConnectTimeout(60000);
        x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                int a = result.indexOf("(");
                String msg = result.substring(a+1, (result.length() - 1));


                AddressBean bean = JSON.parseObject(msg, AddressBean.class);
                List<AddressBean.ResultBean.PoisBean> list = bean.getResult().getPois();
                mAddr = list.get(1).getAddr()+list.get(1).getDirection();


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_submit:
                boolean flag = true;
                if (min != 0 && photoAdapter.getSelectedImage().size() < min) {
                    Toast.makeText(this, "最少选择" + min + "张图片", Toast.LENGTH_LONG).show();
                    flag = false;
                }
                if (max != 0 && photoAdapter.getSelectedImage().size() > max) {
                    Toast.makeText(this, "选择的图片不可以超过" + max + "张", Toast.LENGTH_LONG).show();
                    flag = false;
                }
                if (flag) {
                    Intent intent = new Intent();
                    if (photoAdapter.getSelectedImage().size() > 0) {
                        List<String> list = photoAdapter.getSelectedImage();
                        String[] array = new String[list.size()];
                        list.toArray(array);
                        Bundle bundle = new Bundle();
                        bundle.putStringArray("result", array);
                        bundle.putStringArrayList("result_list", (ArrayList<String>) list);
                        intent.putExtras(bundle);
                        this.setResult(RESULT_OK, intent);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putStringArray("result", new String[]{});
                        bundle.putStringArrayList("result_list", new ArrayList<String>());
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                    }
                    Log.v("tag", "getSelectedImage is " + photoAdapter.getSelectedImage().size());
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        setTitle("图片管理");

        if (android.os.Build.VERSION.SDK_INT >= 20) {
            DEFAULT_PATH = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        } else {
            DEFAULT_PATH = Environment.getExternalStorageDirectory()
                    + File.separator + this.getPackageName()
                    + File.separator + "Pictures";
        }

        photoAdapter = new PhotoAdapter(this, mData, selectedData, R.layout.item_com_avicsafety_photo_photomanager_photo, "");
        grid_view.setAdapter(photoAdapter);
        initPhotoData();

    }

    private void initPhotoData() {
        // 指定调用相机拍照后照片的储存路径
        File dir = new File(DEFAULT_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        tempFile = new File(dir + File.separator + "temp.jpg");
        if (!tempFile.exists()) {
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            getData();
        } else {

            String[] data = bundle.getStringArray("data");
            List<String> list = getIntent().getStringArrayListExtra("list");

            boolean readonly = bundle.getBoolean("readonly", false);
            max = bundle.getInt("max", 0);
            min = bundle.getInt("min", 0);


            if (data != null) {
                fromArrayToCollection(data, selectedData);
            }

            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    selectedData.add(list.get(i));
                }
            }
            if (readonly) {
                mData.clear();
                for (int i = 0; i < selectedData.size(); i++) {
                    mData.add(selectedData.get(i));
                }
            } else {
                getData();
            }
        }
    }

    private void getData() {
        mData.clear();
        String selection = MediaStore.Images.Media.DATA + " like '" + DEFAULT_PATH + "%'";
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES, selection, null, MediaStore.Images.Media.DATE_ADDED + " desc");
        while (cursor.moveToNext()) {
            String strValue = cursor.getString(0);

            File image_file = new File(strValue);
            if (!image_file.exists()) {
                this.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Thumbnails.DATA + "=?", new String[]{strValue});
            } else {
                if (!strValue.contains("temp.jpg")) {
                    mData.add(strValue);
                }

            }
        }
        cursor.close();
    }


    static <T> void fromArrayToCollection(T[] array, Collection<T> b) {
        for (T str : array) {
            b.add(str);
        }
    }

    private void startPhotoZoom(Uri uri) {


        Intent intent = new Intent("com.android.camera.action.CROP");
        //intent.setDataAndType(uri, "image/*");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        //intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 4);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 720);
        intent.putExtra("outputY", 960);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);

        //生成一个随机文件
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        File dir = new File(DEFAULT_PATH);
        newFilePath = new String(dir + File.separator + str + rannum + ".jpg");

        //插入到系统库
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA, newFilePath);
        values.put(MediaStore.Images.Media.TITLE, str + rannum + ".jpg");
        Log.v("tag", newFilePath);
        this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        //打开
        Uri photoURI = Uri.fromFile(new File(newFilePath));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:// 当选择拍照时调用
                if (resultCode == RESULT_OK) {
                    File dir = new File(DEFAULT_PATH);
                    tempFile = new File(dir + File.separator + "temp.jpg");
                    Uri photoURI = getUriByFile(tempFile);
                    grantUriPermission("com.android.camera", photoURI,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startPhotoZoom(photoURI);
                }
                break;
            case PHOTO_REQUEST_CUT:
                // 返回的结果
                if (resultCode == RESULT_OK) {
                    if (longitude != 0.0 && latitude != 0.0) {
                        createWaterMap(newFilePath, longitude, latitude);
                    }
                    photoAdapter.addSelectedImage(newFilePath);
                } else {
                    getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Thumbnails.DATA + "=?", new String[]{newFilePath});
                }
                getData();
                photoAdapter.notifyDataSetChanged();
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void createWaterMap(String newFilePath, double longitude, double latitude) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(newFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap sourcebitmap = BitmapFactory.decodeStream(fis);

        Random rand = new Random();
        int i = rand.nextInt(100);
        int b = rand.nextInt(100);


        String msg = "经度：" + Utils.doubleFormat(longitude, 6) + "\r\n" + "纬度：" +  Utils.doubleFormat(latitude, 6) + "\r\n" + "地址：" + address + "\r\n" + "时间：" + mTime;
        //mTextBitmap=WaterMarkUtils.drawTextToLeftBottom(this,sourcebitmap,msg,8, Color.WHITE, 0, 1);
        mTextBitmap = WaterMarkUtils.drawText(this, sourcebitmap, msg, 10);

        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File f = new File(newFilePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            mTextBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i(TAG, "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        btn_photo_list_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCamera();
            }
        });
    }

    private void OpenCamera() {

        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoURI = getUriByFile(tempFile);
        grantUriPermission("com.android.camera", photoURI,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        cameraintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cameraintent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(cameraintent, PHOTO_REQUEST_TAKEPHOTO);
    }


    private Uri getUriByFile(File file) {
        Uri photoURI;
        if (Build.VERSION.SDK_INT >= 24) {

            photoURI = FileProvider.getUriForFile(this, "com.avicsafety.safety_examine", file);
        } else {
            photoURI = Uri.fromFile(file);
        }
        return photoURI;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
