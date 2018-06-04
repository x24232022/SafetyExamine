package com.avicsafety.ShenYangTowerComService.service;

import com.avicsafety.lib.interfaces.OnNetworkAccessToListListener;
import com.avicsafety.lib.tools.GsonUtils;
import com.avicsafety.lib.tools.L;

import com.avicsafety.ShenYangTowerComService.conf.Constants;
import com.avicsafety.ShenYangTowerComService.dao.D_LoginInfo;
import com.avicsafety.ShenYangTowerComService.dao.D_IpLib;
import com.avicsafety.ShenYangTowerComService.model.M_LoginInfo;
import com.avicsafety.ShenYangTowerComService.model.M_IpLib;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.EventListener;
import java.util.List;

import static com.avicsafety.ShenYangTowerComService.conf.Constants.BASE_URL;

public class LoginManager extends abstract_service {

	static {
		abstract_service.init();
	}
	
	public LoginManager(){
		super();
	}
	
	/**
	 * 本地是否有登陆信息
	 * @return
	 */
	public static boolean isLogined(){
		D_LoginInfo dao = new D_LoginInfo();
		long count = dao.getActiveCount(base_db);
		return count > 0;
	}

//	/**
//	 * 登陆到服务器
//	 * @param username
//	 * @param password
//	 */
//	public void login(final Context context, final String username, final String password, final TelephonyManager imei, final OnNetworkAccessToModelListener listener) {
//
//		RequestParams params = new RequestParams(Constants.LOGIN);
//		params.setConnectTimeout(30000);
//		params.addQueryStringParameter("userName", username);
//		params.addQueryStringParameter("password", password);
//		params.addQueryStringParameter("imei", String.valueOf(imei));
//		params.addQueryStringParameter("version", String.valueOf(AppInfo.getVersionCode(context)));
//		x.http().get(params, new Callback.CommonCallback<String>() {
//			@Override
//			public void onCancelled(CancelledException arg0) {}
//			@Override
//			public void onError(Throwable arg0, boolean arg1) {
//				arg0.getLocalizedMessage();
//				L.v("TTTTTTTTTTTTTTTTT:",arg0.getLocalizedMessage());
//				listener.onFail("无法连接到服务器,请稍候再试!");
//			}
//
//			@Override
//			public void onFinished() {}
//
//			@Override
//			public void onSuccess(String result) {
//				JSONObject res;
//				try {
//					res = new JSONObject(result);
//					String code = res.getString("Code");
//					if (code.equals("200")) {
//						String datas = res.get("Response").toString();
//						Constants.setUserInfo(context, datas);
//						Constants.setSubmitUser(username, context);
//						context.startActivity(new Intent(context, ChangeOneActivity.class));
//						((Activity) context).finish();
//					}else{
//						listener.onFail("用户名或密码不正确,请核对后重新尝试!");
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//					L.v(e.getMessage());
//					listener.onFail("无法连接到服务器,请稍候再试!123");
//				}
//			}
//		});
//	}

//	public void loginDD(final Context context, String userName, String userPwd){
//		RequestParams params = new RequestParams(Constants.DDLOGIN);
//		params.addParameter("userName",userName);
//		params.addParameter("password",userPwd);
//		x.http().post(params, new Callback.CommonCallback<String>() {
//			@Override
//			public void onSuccess(String result) {
//				Utils.hideProgress((LoginActivity)context);
//				Gson g=new Gson();
//				com.google.gson.JsonParser jp=new com.google.gson.JsonParser();
//				JsonElement je=jp.parse(result);
//				JsonObject jo=je.getAsJsonObject();
//				UserBean user=g.fromJson(jo, new TypeToken<UserBean>(){}.getType());
//				int flag=user.getCode();
//				Utils.saveOAuth(user,context);
//				switch (flag) {
//					case 10000:
////						L.v("DanDongLoing:~","丹东登录成功!");
////						((LoginActivity)context).goToMain();
////						context.startActivity(new Intent(context, ChangeOneActivity.class));
////						((LoginActivity)context).startServer();
////						((Activity) context).finish();
//						break;
//					case 10006:
////						ToastUtils.showToast(context, "用户名或密码不正确!");
//						break;
//					case 10022:
////						ToastUtils.showToast(context, "账号不存在");
//						break;
//					case 11000:
////						ToastUtils.showToast(context, "服务器异常");
//						break;
//					case 0x2717:
////						ToastUtils.showToast(context, "账号冻结");
//						break;
//					default:
////						ToastUtils.showToast(context, "发生未知错误!");
//						break;
//				}
//			}
//
//			@Override
//			public void onError(Throwable ex, boolean isOnCallback) {
//
//			}
//
//			@Override
//			public void onCancelled(CancelledException cex) {
//
//			}
//
//			@Override
//			public void onFinished() {
//
//			}
//		});
//
//	}

	
	/**
	 * 设置登陆人信息
	 */
	public void setLoginInfo(M_LoginInfo model){
		D_LoginInfo dao = new D_LoginInfo();
		dao.save(base_db, model);
	}
	
	public static String getUserName(){
		D_LoginInfo dao = new D_LoginInfo();
		M_LoginInfo model = dao.getLoginInfo(base_db);
		return model.getUserName();
	}
	
	/**
	 * 获取当前登陆人的MODEL
	 */
	public static M_LoginInfo  getLoginInfo(){
		D_LoginInfo dao = new D_LoginInfo();
		M_LoginInfo model = dao.getLoginInfo(base_db);
		return model;
	}
	
	/**
	 * remote service 调用的本地退出登陆操作 
	 */
	public static void logout() {
		//登出
		D_LoginInfo dao = new D_LoginInfo();
		dao.logout(base_db);
	}

	public static void setServerIpAddr(String ip, String port) {
		M_IpLib model = new M_IpLib();
		model.setId(1);
		model.setIp(ip+":"+port);
		new D_IpLib().update(base_db,model);
	}

	public static String getServerIpAddr() {
		D_IpLib dao = new D_IpLib();
		List<M_IpLib> list = dao.getList(base_db, " id desc ");
		if(list==null||list.size()==0) {
			return BASE_URL;
		}else{
			return list.get(0).getIp();
		}
	}

	public static String getServerName() {
		D_IpLib dao = new D_IpLib();
		List<M_IpLib> list = dao.getList(base_db, " id desc ");
		if(list==null||list.size()==0) {
			return "";
		}else{
			return list.get(0).getName();
		}
	}

	public static String getServerEnName() {
		D_IpLib dao = new D_IpLib();
		List<M_IpLib> list = dao.getList(base_db, " id desc ");
		if(list==null||list.size()==0) {
			return "";
		}else{
			return list.get(0).getEnname();
		}
	}


	public static void setServerIpAddr(M_IpLib model){
		D_IpLib dao = new D_IpLib();
		dao.deleteAll(base_db);
		dao.update(base_db,model);
	}

	public static void getServerListByKey(String key, final OnNetworkAccessToListListener<M_IpLib> listener) {
		RequestParams params = new RequestParams(Constants.SEARCH_SERVERIP_URL);
		params.setConnectTimeout(60000);
		params.addParameter("key",key);

		x.http().get(params, new Callback.CommonCallback<String>(){
			@Override
			public void onSuccess(String result) {
				L.v(result);
				JSONObject res;
				try {
					res = new JSONObject(result);
					String Code= res.getString("Code");
					if(Code.equals("200")){
						JSONArray array = res.getJSONArray("Response");
						java.lang.reflect.Type type = new TypeToken<List<M_IpLib>>() {}.getType();
						List<M_IpLib> list = GsonUtils.tobean(array.toString(),type);
						listener.onSuccess(list);
					}else{
						listener.onFail("没有找到相关IP信息！");
					}
				} catch (JSONException e) {
					listener.onFail("服务器出错，请稍后再试！");
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				L.e(ex.getMessage());
				listener.onFail("服务器错误！");
			}

			@Override
			public void onCancelled(CancelledException cex) {

			}

			@Override
			public void onFinished() {

			}
		});
	}

	public interface OnLoginResultListener extends EventListener  {
    	void onSuccess();
    	void onFail();
    }

	public String getChatServicePassword() {
		D_LoginInfo dao = new D_LoginInfo();
		M_LoginInfo model = dao.getLoginInfo(base_db);
		return model.getPassword();
	}

	public static boolean isAdmin() {
		if(getLoginInfo().getType()!=null&&getLoginInfo().getType().equals("管理员")){
			return true;
		}else{
			return getLoginInfo().getUserName().equals("admin");
		}
	}
	


}
