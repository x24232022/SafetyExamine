package com.avicsafety.ShenYangTowerComService.photoUtils;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.activity.BaseActivity;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.ydtx.powermanger.event.LatLngEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * 图片管理界面
 */

@ContentView(R.layout.activity_com_avicsafety_photo_photomanager_photo_list_lc)
public class PhotoListActivity extends BaseActivity {

	private File tempFile;
	private String newFilePath = "";
	private String DEFAULT_PATH = "";
	private List<String> mData = new ArrayList<String>();
	private List<String> selectedData = new ArrayList<String>();
	private int max = 0;
	private int min = 0;
	private double latitude;
	private double longitude;
	private BDLocation location;
	private LocationClient mLocationClient;
//	private MyBDLocationListner mListner;
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



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.submit, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.action_submit:
				boolean flag = true;
				if(min!=0&&photoAdapter.getSelectedImage().size()<min){
					Toast.makeText(this,"最少选择"+min+"张图片",Toast.LENGTH_LONG).show();
					flag=false;
				}
				if(max!=0&&photoAdapter.getSelectedImage().size()>max){
					Toast.makeText(this,"选择的图片不可以超过"+ max +"张",Toast.LENGTH_LONG).show();
					flag=false;
				}
				if(flag){
					Intent intent = new Intent();
					if(photoAdapter.getSelectedImage().size()>0){
						List<String> list = photoAdapter.getSelectedImage();
						String[] array =new String[list.size()];
						list.toArray(array);
						Bundle bundle = new Bundle();
						bundle.putStringArray("result", array);
						bundle.putStringArrayList("result_list", (ArrayList<String>) list);
						intent.putExtras(bundle);
						this.setResult(RESULT_OK, intent);
					}else{
						Bundle bundle = new Bundle();
						bundle.putStringArray("result", new String[]{});
						bundle.putStringArrayList("result_list", new ArrayList<String>());
						intent.putExtras(bundle);
						setResult(RESULT_OK, intent);
					}
					Log.v("tag", "getSelectedImage is "+photoAdapter.getSelectedImage().size());


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

        if(android.os.Build.VERSION.SDK_INT>=20) {
            DEFAULT_PATH = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        }else{
            DEFAULT_PATH = Environment.getExternalStorageDirectory()
                    + File.separator + this.getPackageName()
                    + File.separator + "Pictures";
        }

		photoAdapter = new PhotoAdapter(this, mData, selectedData, R.layout.item_com_avicsafety_photo_photomanager_photo, "");
		grid_view.setAdapter(photoAdapter);
		initPhotoData();
//		initLocation();
	}

	private void initPhotoData() {
		// 指定调用相机拍照后照片的储存路径
		File dir =  new File(DEFAULT_PATH);
		if(!dir.exists()){
			dir.mkdir();
		}
		tempFile = new File(dir+File.separator+"temp.jpg");
		if(!tempFile.exists()){
			try {
				tempFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		if(bundle==null){
			getData();
		}else{

			String[] data =  bundle.getStringArray("data");
			List<String> list =	getIntent().getStringArrayListExtra("list");
			//String path =  bundle.getString("path","");
			//废弃参数path传递， 系统会自动找到 Android/data/包名/files/Pictures 为保存目录
			boolean readonly =  bundle.getBoolean("readonly", false);
			max = bundle.getInt("max",0);
			min = bundle.getInt("min",0);


			if(data!=null){
				fromArrayToCollection(data, selectedData);
			}

			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					selectedData.add(list.get(i));
				}
			}
			if(readonly){
				mData.clear();
				for(int i = 0;i<selectedData.size();i++){
					mData.add(selectedData.get(i));
				}
			}else{
				getData();
			}
		}
	}

	private void getData() {
		mData.clear();
		String selection = MediaStore.Images.Media.DATA + " like '"+ DEFAULT_PATH +"%'";
		Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES, selection, null, MediaStore.Images.Media.DATE_ADDED+" desc");
		while(cursor.moveToNext())
		{
			String strValue= cursor.getString(0);

			File image_file = new File(strValue);
			if(!image_file.exists()) {
				this.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Thumbnails.DATA + "=?", new String[]{strValue});
			}else {
				if(!strValue.contains("temp.jpg")){
					mData.add(strValue);
				}

			}
		}
		cursor.close();
	}


	static <T> void fromArrayToCollection(T[] array, Collection<T> b){
		for (T str : array) {
			b.add(str);
		}
	}

	private void startPhotoZoom(Uri uri)   {


		Intent intent = new Intent("com.android.camera.action.CROP");
		//intent.setDataAndType(uri, "image/*");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");

		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 400);
		intent.putExtra("outputY", 400);
		intent.putExtra("outputFormat",  Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("return-data", false);
		intent.putExtra("noFaceDetection", true);

/*		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			getLocation();
		}else {
			toggleGPS();
		}*/

		//生成一个随机文件
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		File dir =  new File(DEFAULT_PATH);
		newFilePath = new String(dir+File.separator+ str + rannum +".jpg");

		//插入到系统库
		ContentValues values = new ContentValues();
		values.put(MediaStore.Images.Media.DATA, newFilePath);
		values.put(MediaStore.Images.Media.TITLE, str + rannum +".jpg");
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
				if(resultCode==RESULT_OK){
					File dir =  new File(DEFAULT_PATH);
					tempFile = new File(dir+File.separator+"temp.jpg");
					Uri photoURI = getUriByFile(tempFile);
					grantUriPermission("com.android.camera",photoURI,
							Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
					startPhotoZoom(photoURI);
				}
				break;
			case PHOTO_REQUEST_CUT:
				// 返回的结果
				if(resultCode==RESULT_OK){

					createWaterMap(newFilePath);
					photoAdapter.addSelectedImage(newFilePath);
				}else{
					getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,  MediaStore.Images.Thumbnails.DATA+"=?", new String[]{newFilePath});
				}
				getData();
				photoAdapter.notifyDataSetChanged();
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void createWaterMap(String newFilePath){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(newFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Bitmap sourcebitmap  = BitmapFactory.decodeStream(fis);

		Random rand = new Random();
		int i = rand.nextInt(100);
		int b = rand.nextInt(100);
		EventBus.getDefault().register(this);

		longitude = location.getLongitude();
		latitude = location.getLatitude();
		address = location.getAddress().address;
		String time = location.getTime();
		Bitmap textBitmap = WaterMarkUtils.drawTextToLeftBottom(this, sourcebitmap, "经度："+longitude + "\r\n" +"纬度："+latitude + "\r\n" + "地址：" + address + "\r\n" + "时间：" + time, 4, Color.WHITE, 0, 1);

		if(fis!=null){
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
			textBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
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

	@Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
	public void onThreadMainEvent(LatLngEvent event){
		location = event.getLocation();
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
//		initLocation();
		Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri photoURI = getUriByFile(tempFile);
		grantUriPermission("com.android.camera",photoURI,
				Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
		cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
		cameraintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		cameraintent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
		startActivityForResult(cameraintent, PHOTO_REQUEST_TAKEPHOTO);
	}

//	private void initLocation() {
//		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//		} else {
//			//定位
//			mLocationClient = new LocationClient(getApplicationContext());
//			mListner = new MyBDLocationListner();
//			mLocationClient.registerLocationListener(mListner);
//			LocationClientOption option = new LocationClientOption();
//			option.setOpenGps(true);  //打开GPS
//			option.setCoorType("bd09ll");//设置坐标系类型
//			mLocationClient.setLocOption(option);
//			mLocationClient.start();
//		}
//	}

	private Uri getUriByFile(File file){
		Uri photoURI;
		if (Build.VERSION.SDK_INT >= 24) {
//			photoURI = FileProvider.getUriForFile(this, this.getPackageName(), file);
			photoURI = FileProvider.getUriForFile(this, "com.avicsafety.ShenYangTowerComService", file);
		}else{
			photoURI = Uri.fromFile(file);
		}
		return photoURI;
	}

//	private class MyBDLocationListner implements BDLocationListener {
//
//		@Override
//		public void onReceiveLocation(BDLocation bdLocation) {
//
//			L.v("照片的开启定位方法:");
//			address = bdLocation.getAddress().toString();
//			//mapView 销毁后不再处理新接收的位置
//			if (bdLocation == null)
//				return;
//			latitude = bdLocation.getLatitude();
//			longitude = bdLocation.getLongitude();
//		}
//
//	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
