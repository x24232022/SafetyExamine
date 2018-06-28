package com.avicsafety.safety_examine.xfd;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.avicsafety.safety_examine.R;
import com.avicsafety.safety_examine.Utils.MyProgressDialog;
import com.avicsafety.safety_examine.activity.BaseActivity;
import com.avicsafety.safety_examine.conf.Configuration;
import com.avicsafety.safety_examine.model.MUser;
import com.avicsafety.safety_examine.photoUtils.PhotoListActivity;
import com.avicsafety.lib.CustomView.AvicPhotoList;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘畅 on 2017/12/28.
 */
@ContentView(R.layout.xfd_sc_photo_activity)
public class PhotoActivityXin extends BaseActivity{


    private String id;
    private String userid = "boot";
    public MyProgressDialog progressDialog;
    private MUser userAccoutn;
    private AvicPhotoList apl_company_detail_photo,apl_company_detail_photo1,apl_company_detail_photo2;
    private String status;



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
                    if(status.equals("异常")){
                        SctpType1();
                        SctpType11();
                    }else if(status.equals("失败")){
                        SctpType2();
                        SctpType22();
                    }else if(status.equals("成功")){
                        SctpType3();
                        SctpType33();
                    }
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
            new XinFDMethod().dealSCTP(oThis,id,buffer.toString(),7,"异常",userAccoutn.getUserName(),1);

        }
        progressDialog = new MyProgressDialog(oThis, "上传中..");
    }
    private void SctpType11(){

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
            new XinFDMethod().dealSCTP(oThis,id,buffer.toString(),7,"异常",userAccoutn.getUserName(),2);

        }
        progressDialog = new MyProgressDialog(oThis, "上传中..");
    }
    private void SctpType2(){

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
            new XinFDMethod().dealSCTP(oThis,id,buffer.toString(),7,"失败",userAccoutn.getUserName(),1);

        }
        progressDialog = new MyProgressDialog(oThis, "上传中..");
    }
    private void SctpType22(){

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
            new XinFDMethod().dealSCTP(oThis,id,buffer.toString(),7,"失败",userAccoutn.getUserName(),2);

        }
        progressDialog = new MyProgressDialog(oThis, "上传中..");
    }
    private void SctpType3(){

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
            new XinFDMethod().dealSCTP(oThis,id,buffer.toString(),7,"成功",userAccoutn.getUserName(),1);

        }
        progressDialog = new MyProgressDialog(oThis, "上传中..");
    }
    private void SctpType33(){

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
            new XinFDMethod().dealSCTP(oThis,id,buffer.toString(),7,"成功",userAccoutn.getUserName(),2);

        }
        progressDialog = new MyProgressDialog(oThis, "上传中..");
    }


    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.
//                SOFT_INPUT_ADJUST_PAN);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("添加图片");
        userAccoutn = com.avicsafety.safety_examine.yd.activity.ydUtil.Constants.getUserInfo(oThis);
        id = getIntent().getStringExtra("ticketid");
        progressDialog = new MyProgressDialog(this);
        progressDialog.dismiss();
        apl_company_detail_photo = (AvicPhotoList) findViewById(R.id.xfd_sc_photo);
        apl_company_detail_photo1 = (AvicPhotoList) findViewById(R.id.xfd_sc_photo1);

        status = getIntent().getStringExtra("status");

    }

    @Override
    protected void InitializeEvent() {
        super.InitializeEvent();
        apl_company_detail_photo.setOnClickListener(new AvicPhotoList.OnClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(oThis, PhotoListActivity.class);
                intent.putExtra("max", 9);   //选择最大图片数量
                intent.putExtra("min", 1);   //选择最小图片数量
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
                intent.putExtra("max", 9);   //选择最大图片数量
                intent.putExtra("min", 1);   //选择最小图片数量
                //intent.putExtra("data", temp);  //选中的图片数组  数组中为图片路径
                intent.putStringArrayListExtra("list", (ArrayList<String>) apl_company_detail_photo1.getData());  //也可以传递一个ArrayList<String>
                intent.putExtra("path", Configuration.BASE_PATH);   //图片默认存放的地址
                intent.putExtra("title", apl_company_detail_photo.getLable());  //图标管理页面的TITLE
                intent.putExtra("readonly", false);  //是否为只读模式
                startActivityForResult(intent, 3001);
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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PhotoActivityXin.this.finish();
    }
}
