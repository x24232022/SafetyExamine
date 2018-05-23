package com.avicsafety.ShenYangTowerComService.xfd;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.avicsafety.ShenYangTowerComService.PowerManager.push.Utils.Utils;
import com.avicsafety.ShenYangTowerComService.R;
import com.avicsafety.ShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.ShenYangTowerComService.activity.BaseActivity;
import com.avicsafety.ShenYangTowerComService.conf.Configuration;
import com.avicsafety.ShenYangTowerComService.model.MUser;
import com.avicsafety.ShenYangTowerComService.photoUtils.PhotoListActivity;
import com.avicsafety.lib.CustomView.AvicPhotoList;
import com.avicsafety.lib.tools.L;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘畅 on 2017/12/28.
 * 添加图片界面
 */
@ContentView(R.layout.xfd_dzfd_photo_activity)
public class PhotoActivityDzXin extends BaseActivity{


    private String id;
    public MyProgressDialog progressDialog;
    private MUser userAccoutn;
    private AvicPhotoList apl_company_detail_photo,apl_company_detail_photo1,apl_company_detail_photo2
            ,apl_company_detail_photo3;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit:

                if(apl_company_detail_photo.getData() != null){
                    SctpType1();
                }if(apl_company_detail_photo1.getData() != null){
                SctpType2();
            }if(apl_company_detail_photo2.getData() != null){
                SctpType3();
            }if(apl_company_detail_photo3.getData() != null){
                SctpType4();
            }
            startActivity(new Intent(oThis,PlanActivityXin.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SctpType1(){

        StringBuffer buffer = new StringBuffer("[");
        for(String s:apl_company_detail_photo.getData()){
            if(buffer.length()>1){
                buffer.append(",");
            }
            buffer.append("{");
            buffer.append("\"filedata\":\"");
            buffer.append(Constants.Bitmap2StrByBase64(s).replace("\n",""));
            buffer.append("\",\"name\":\"temp.jpg\"}");
        }
        buffer.append("]");
        if(buffer.toString() != null){
            L.v("发电图片上传第一张!!!!!!!!!!  :",buffer.toString());
            new XinFDMethod().dealdzSCTP(oThis,id,buffer.toString(),5,userAccoutn.getUserName(),1);

        }
        progressDialog = new MyProgressDialog(oThis, "上传中..");
    }
    private void SctpType2(){

        StringBuffer buffer = new StringBuffer("[");
        for(String s:apl_company_detail_photo1.getData()){
            if(buffer.length()>1){
                buffer.append(",");
            }
            buffer.append("{");
            buffer.append("\"filedata\":\"");
            buffer.append(Constants.Bitmap2StrByBase64(s).replace("\n",""));
            buffer.append("\",\"name\":\"temp.jpg\"}");
        }
        buffer.append("]");
        if(buffer.toString() != null){
            L.v("发电图片上传第二张!!!!!!!!!!  :",buffer.toString());
            new XinFDMethod().dealdzSCTP(oThis,id,buffer.toString(),5,userAccoutn.getUserName(),2);

        }
    }
    private void SctpType3(){

        StringBuffer buffer = new StringBuffer("[");
        for(String s:apl_company_detail_photo2.getData()){
            if(buffer.length()>1){
                buffer.append(",");
            }
            buffer.append("{");
            buffer.append("\"filedata\":\"");
            buffer.append(Constants.Bitmap2StrByBase64(s).replace("\n",""));
            buffer.append("\",\"name\":\"temp.jpg\"}");
        }
        buffer.append("]");
        if(buffer.toString() != null){
            L.v("发电图片上传第三张!!!!!!!!!!  :",buffer.toString());
            new XinFDMethod().dealdzSCTP(oThis,id,buffer.toString(),5,userAccoutn.getUserName(),3);

        }
    }
    private void SctpType4(){

        StringBuffer buffer = new StringBuffer("[");
        for(String s:apl_company_detail_photo3.getData()){
            if(buffer.length()>1){
                buffer.append(",");
            }
            buffer.append("{");
            buffer.append("\"filedata\":\"");
            buffer.append(Constants.Bitmap2StrByBase64(s).replace("\n",""));
            buffer.append("\",\"name\":\"temp.jpg\"}");
        }
        buffer.append("]");
        if(buffer.toString() != null){
            L.v("发电图片上传第四张!!!!!!!!!!  :",buffer.toString());
            new XinFDMethod().dealdzSCTP(oThis,id,buffer.toString(),5,userAccoutn.getUserName(),4);

        }
    }


    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.
//                SOFT_INPUT_ADJUST_PAN);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("添加图片");
        userAccoutn = com.avicsafety.ShenYangTowerComService.yd.activity.ydUtil.Constants.getUserInfo(oThis);
        id = getIntent().getStringExtra("ticketid");
        progressDialog = new MyProgressDialog(this);
        progressDialog.dismiss();
        apl_company_detail_photo = (AvicPhotoList) findViewById(R.id.xfd_dzfd_photo);
        apl_company_detail_photo1 = (AvicPhotoList) findViewById(R.id.xfd_dzfd_photo1);
        apl_company_detail_photo2 = (AvicPhotoList) findViewById(R.id.xfd_dzfd_photo2);
        apl_company_detail_photo3 = (AvicPhotoList) findViewById(R.id.xfd_dzfd_photo3);

    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        apl_company_detail_photo.setOnClickListener(new AvicPhotoList.OnClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(oThis, PhotoListActivity.class);
                intent.putExtra("max", 1);   //选择最大图片数量
                intent.putExtra("min", 0);   //选择最小图片数量
                //intent.putExtra("data", temp);  //选中的图片数组  数组中为图片路径
                intent.putStringArrayListExtra("list", (ArrayList<String>) apl_company_detail_photo.getData());  //也可以传递一个ArrayList<String>
                intent.putExtra("path", Configuration.BASE_PATH);   //图片默认存放的地址
                intent.putExtra("title", apl_company_detail_photo.getLable());  //图标管理页面的TITLE
                intent.putExtra("readonly", false);  //是否为只读模式
                startActivityForResult(intent, 3000);
            }
        });
        apl_company_detail_photo1.setOnClickListener(new AvicPhotoList.OnClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(oThis, PhotoListActivity.class);
                intent.putExtra("max", 1);   //选择最大图片数量
                intent.putExtra("min", 0);   //选择最小图片数量
                //intent.putExtra("data", temp);  //选中的图片数组  数组中为图片路径
                intent.putStringArrayListExtra("list", (ArrayList<String>) apl_company_detail_photo1.getData());  //也可以传递一个ArrayList<String>
                intent.putExtra("path", Configuration.BASE_PATH);   //图片默认存放的地址
                intent.putExtra("title", apl_company_detail_photo.getLable());  //图标管理页面的TITLE
                intent.putExtra("readonly", false);  //是否为只读模式
                startActivityForResult(intent, 3001);
            }
        });
        apl_company_detail_photo2.setOnClickListener(new AvicPhotoList.OnClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(oThis, PhotoListActivity.class);
                intent.putExtra("max", 1);   //选择最大图片数量
                intent.putExtra("min", 0);   //选择最小图片数量
                //intent.putExtra("data", temp);  //选中的图片数组  数组中为图片路径
                intent.putStringArrayListExtra("list", (ArrayList<String>) apl_company_detail_photo2.getData());  //也可以传递一个ArrayList<String>
                intent.putExtra("path", Configuration.BASE_PATH);   //图片默认存放的地址
                intent.putExtra("title", apl_company_detail_photo.getLable());  //图标管理页面的TITLE
                intent.putExtra("readonly", false);  //是否为只读模式
                startActivityForResult(intent, 3002);
            }
        });
        apl_company_detail_photo3.setOnClickListener(new AvicPhotoList.OnClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(oThis, PhotoListActivity.class);
                intent.putExtra("max", 1);   //选择最大图片数量
                intent.putExtra("min", 0);   //选择最小图片数量
                //intent.putExtra("data", temp);  //选中的图片数组  数组中为图片路径
                intent.putStringArrayListExtra("list", (ArrayList<String>) apl_company_detail_photo3.getData());  //也可以传递一个ArrayList<String>
                intent.putExtra("path", Configuration.BASE_PATH);   //图片默认存放的地址
                intent.putExtra("title", apl_company_detail_photo.getLable());  //图标管理页面的TITLE
                intent.putExtra("readonly", false);  //是否为只读模式
                startActivityForResult(intent, 3003);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case 3000:
            {
                if (resultCode == RESULT_OK) {
                    List<String> list = data.getStringArrayListExtra("result_list");
                    apl_company_detail_photo.setData(list);
                }
                break;
            }
            case 3001:
            {
                if (resultCode == RESULT_OK) {
                    List<String> list = data.getStringArrayListExtra("result_list");
                    apl_company_detail_photo1.setData(list);
                }
                break;
            }
            case 3002:
            {
                if (resultCode == RESULT_OK) {
                    List<String> list = data.getStringArrayListExtra("result_list");
                    apl_company_detail_photo2.setData(list);
                }
                break;
            }
            case 3003:
            {
                if (resultCode == RESULT_OK) {
                    List<String> list = data.getStringArrayListExtra("result_list");
                    apl_company_detail_photo3.setData(list);
                }
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
