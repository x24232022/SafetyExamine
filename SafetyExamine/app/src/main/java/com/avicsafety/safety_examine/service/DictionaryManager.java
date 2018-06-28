package com.avicsafety.safety_examine.service;

import com.avicsafety.lib.tools.DateDeserializerUtils;
import com.avicsafety.lib.tools.L;
import com.avicsafety.safety_examine.dao.D_Dictionary;
import com.avicsafety.lib.interfaces.OnNetworkAccessToListListener;
import com.avicsafety.lib.interfaces.OnNetworkAccessToMessageListener;
import com.avicsafety.lib.interfaces.OnNetworkAccessToModelListener;
import com.avicsafety.safety_examine.model.M_Dictionary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class DictionaryManager extends abstract_service {

    /**企业类型*/
    public static final String COMPANY_TYPE = "company_type";

    /**经营状态*/
    public static final String COMPANY_STATE = "company_state";

    /**标准化级别*/
    public static final String COMPANY_LEVEL = "company_level";

    /**企业隶属关系*/
    public static final String COMPANY_RELATION = "company_relation";

    /**管辖部门级别*/
    public static final String COMPANY_SUPERIOR_LEVEL = "company_superior_level";

    public DictionaryManager(){
        super();
    }

    static {
        abstract_service.init();
    }

    /**
     * 获取一个对象实体
     */
    public M_Dictionary getModel(int id){
        D_Dictionary dao = new D_Dictionary();
        return dao.getModel(law_db, id);
    }

    /**
     * 获取所有列表
     */
    public List<M_Dictionary> getList(){
        D_Dictionary dao = new D_Dictionary();
        return dao.getList(law_db);
    }

    /**
     * 通过条件检索  wb不建议暴露到逻辑层
     */
    public List<M_Dictionary> getList(WhereBuilder wb, String order){
        D_Dictionary dao = new D_Dictionary();
        return dao.getList(law_db, wb, order);
    }

    /**
     * 通过一个sql语句获取列表 sql不建议暴露到逻辑层
     */
    public List<M_Dictionary> getList(String sql){
        D_Dictionary dao = new D_Dictionary();
        return  dao.getList(law_db, sql);
    }

    /**
     * 分页和排序获取列表
     */
    public List<M_Dictionary> getList(WhereBuilder wb, String order, int pageIndex, int pageSize ){
        D_Dictionary dao = new D_Dictionary();
        return  dao.getList(law_db, wb, order, pageIndex, pageSize);
    }

    /**
     * 插入一个对象
     */
    public void add(M_Dictionary model){
        D_Dictionary dao = new D_Dictionary();
        dao.add(law_db, model);
    }

    /**
     * 更新一个对象
     */
    public void update(M_Dictionary model){
        D_Dictionary dao = new D_Dictionary();
        dao.update(law_db, model);
    }

    /**
     * 删除一个对象
     */
    public void delete(int id){
        D_Dictionary dao = new D_Dictionary();
        dao.delete(law_db, id);
    }

    /**
     * 删除一个对象
     */
    public void delete(M_Dictionary model){
        D_Dictionary dao = new D_Dictionary();
        this.delete(model.getId());
    }

    /*
     * 这只是一个按条件搜索的例子  他是 private 可以组成任意WhereBuilder 对象传递过去
     */
    private List<M_Dictionary> getList(int pageIndex, int pageSize, String order, String... where){
        //假设这里 where 是  "id=2"  order = "guid desc" or "guid"
        D_Dictionary dao = new D_Dictionary();
        WhereBuilder wb = WhereBuilder.b();
        for(String s : where){
            if(s.contains("=")){
                String[] array = s.split("=");
                wb.and(array[0],"=",array[1]);
            }
        }
        return  dao.getList(law_db,wb, order, pageIndex, pageSize);
    }

    /**
     * 解析json 获取企业列表
     */
    protected List<M_Dictionary> getListByJSON(JSONArray jsonArray) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializerUtils()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        java.lang.reflect.Type type = new TypeToken<List<M_Dictionary>>() {}.getType();
        List<M_Dictionary> list = gson.fromJson(jsonArray.toString(), type);
        return list;
    }

    /**
     * 解析json 获取一个Model
     */
    protected M_Dictionary getModelByJSON(JSONObject jsonObject) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializerUtils()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        java.lang.reflect.Type type = new TypeToken<M_Dictionary>() {}.getType();
        M_Dictionary model = gson.fromJson(jsonObject.toString(), type);
        return model;
    }

    /**
     * getListByServ
     *
     * @param params
     * @param listener
     */
    public void getListByServ(RequestParams params, final OnNetworkAccessToListListener<M_Dictionary> listener) {
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException arg0) {

            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                listener.onFail("获取数据失败");
            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");
                    if (code.equals("200")) {
                        JSONArray array = res.getJSONArray("response");
                        if (array == null || array.length() == 0) {
                            listener.onSuccess(null);
                        } else {
                            List<M_Dictionary> list = getListByJSON(array);
                            listener.onSuccess(list);
                        }

                    } else {
                        listener.onFail("获取数据失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    L.v(e.getMessage());
                    listener.onFail("服务器出错，请稍后再试");
                }
            }
        });
    }

    /**
     * getModel
     *
     * @param params
     * @param listener
     */
    public void getModelByServ(RequestParams params, final OnNetworkAccessToModelListener<M_Dictionary> listener) {
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException arg0) {

            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                listener.onFail("获取数据失败");
            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");
                    if (code.equals("200")) {
                        JSONObject obj = res.getJSONObject("Response");
                        if (obj == null) {
                            listener.onSuccess(null);
                        } else {
                            M_Dictionary model = getModelByJSON(obj);
                            listener.onSuccess(model);
                        }
                    } else {
                        listener.onFail("获取数据失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    L.v(e.getMessage());
                    listener.onFail("服务器出错，请稍后再试");
                }
            }
        });
    }


    /**
     * postData
     *
     * @param params
     * @param listener
     */
    public void postData(RequestParams params, final OnNetworkAccessToMessageListener listener) {
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException arg0) {

            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                listener.onFail("提交数据失败");
            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onSuccess(String result) {
                JSONObject res;
                try {
                    res = new JSONObject(result);
                    String code = res.getString("Code");
                    if (code.equals("200")) {
                        String message = res.getString("Message");
                        listener.onSuccess(message);

                    } else {
                        listener.onFail("提交数据失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    L.v(e.getMessage());
                    listener.onFail("服务器出错，请稍后再试");
                }
            }
        });
    }

    public static  List<M_Dictionary> getListByName(String name){
        D_Dictionary dao = new D_Dictionary();
        return dao.getList(law_db, WhereBuilder.b("name" , " = ", name), "sortId");
    }

    /**
     * 获取数据并分解成Key-Value的形式，返回二维数组
     * [0][0] 则为获取key数租
     * [0][1] 则为获取value数租
     * @param name
     * @return
     */
    public static String [][] getArrayByName(String name) {

        D_Dictionary dao = new D_Dictionary();
        List<M_Dictionary> m_dictionaries =  dao.getList(law_db,WhereBuilder.b("name","=" ,name),"sortId");
        StringBuilder builderKey = new StringBuilder("");
        StringBuilder builderValue = new StringBuilder("");
        for (int i = 0;i< m_dictionaries.size() ; i++) {
            builderKey.append(m_dictionaries.get(i).getText()).append("~");
            builderValue.append(m_dictionaries.get(i).getValue()).append("~");
        }
        String key = builderKey.toString();
        String value = builderValue.toString();
        String [] keys = key.split("~");
        String [] values = value.split("~");
        String [][] array = {keys,values};

        return array;

    }

    public static String getTextByValue(String value, String name){
        if(StringUtils.isEmpty(value)||StringUtils.isEmpty(name)){
            return "";
        }else {
            D_Dictionary dao = new D_Dictionary();
            List<M_Dictionary> list = dao.getList(law_db, WhereBuilder.b("value", "=", value).and("name", "=", name));
            if (list == null || list.size() == 0) {
                return "";
            } else {
                return list.get(0).getText();
            }
        }
    }

//    String [][] array = getArray("company_state");
//    String [] key = array[0];
//    String [] value = array[1];
//		Log.e(TAG,key.toString());
//		Log.e(TAG,value.toString());
//		for (int i =0;i<array[0].length;i++) {
//        Log.e(TAG,array[0][i]);
//        Log.e(TAG,array[1][i]);
//    }

}
