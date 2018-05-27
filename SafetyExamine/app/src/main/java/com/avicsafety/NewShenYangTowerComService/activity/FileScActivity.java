package com.avicsafety.NewShenYangTowerComService.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.avicsafety.NewShenYangTowerComService.R;
import com.avicsafety.NewShenYangTowerComService.Utils.MyProgressDialog;
import com.avicsafety.NewShenYangTowerComService.adapter.MyFileAdapter;
import com.avicsafety.NewShenYangTowerComService.conf.Constants;
import com.avicsafety.NewShenYangTowerComService.yd.activity.ydUtil.CTCSRestClientUsage;
import com.leon.lfilepickerlibrary.LFilePicker;

import org.apache.commons.lang3.StringUtils;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_file_sc)
public class FileScActivity extends BaseActivity{
    @ViewInject(R.id.file_list_scwj)
    private ListView listView;
//    public File mFile;
    int REQUESTCODE_FROM_ACTIVITY = 1000;
    @ViewInject(R.id.file_button)
    private Button file_button;
    private File mFile;
    private String id;
    private File mMFile;
    public MyProgressDialog progressDialog;
    private List<String> mList;
    private String mSrc;
    private String mStr;

    @Override
    protected void InitializeComponent() {
        super.InitializeComponent();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        file_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LFilePicker()
                        .withActivity(oThis)
                        .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                        .withTitle("选取文件")
                        .start();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {
                //List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);//Constant.RESULT_INFO == "paths"
                mList = data.getStringArrayListExtra("paths");
                List<String> listMap = new ArrayList<String>();
                for(String s : mList){
                    mFile = new File(s);
                    mSrc = mFile.getName();
//                    Map<String,Object> lst = new HashMap<String, Object>();
//                    lst.put("name",src);
                    listMap.add(mSrc);

                }
                    MyFileAdapter adapter = new MyFileAdapter(oThis, listMap, mList,R.layout.activity_file_sc);
                    listView.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "选中了" + mList.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_submit:
                try {
                    submit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void submit() throws Exception {
        for(String s: mList){

            mStr = new String(s);
        }
            new CTCSRestClientUsage().dealSCWJ(oThis,id,mStr,3);

        progressDialog = new MyProgressDialog(oThis, "上传中..");
    }

    }

//        List<File> list = new ArrayList<>();
//        mFile = new File(SDCardUtils.getSDCardPath());
//        for (File f : mFile.listFiles()){
//            if(f.isFile()||f.isDirectory()){
////                if(f.isDirectory()){
////
////                }
//                f.getName();
//
//                list.add(f);
//            }
//        }
//        MyFileAdapter adapter = new MyFileAdapter(oThis,list,R.layout.activity_file_sc);
//
//        listView.setAdapter(adapter);

