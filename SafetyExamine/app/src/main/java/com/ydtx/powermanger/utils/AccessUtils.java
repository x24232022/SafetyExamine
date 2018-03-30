package com.ydtx.powermanger.utils;

public class AccessUtils {
	//服务器地址	
	//public static final String URL_SERVER="http://szydtx.f3322.net:9559/DDTT/"; //
//	public static final String URL_SERVER="http://113.107.235.66:9928/DDTT/";//外网地址
	//public static final String URL_SERVER="http://192.168.0.211:8082/DDTT/";//本地
	public static final String URL_SERVER="http://119.6.202.171:1240/DDTT";
	//public static final String URL_SERVER="http://192.168.0.14:8081/DDTT/";//测试服务器的ip
	 //public static final String URL_SERVER="http://192.168.0.69:8080/DDTT/";//商正威的ip
	//public static final String URL_SERVER="http://113.107.235.66:8080/DDTT/";//致远211唐寅建67测试14 端口都是8080
	//登录接口
	public static final String URL_LOGIN="/system/terminalLoginout!login";
	//加载已审核发电工单接口
	public static final String AUDITED_POWER_WORK="/androidMgt/androidFacadeAction!loadCheckedData";
	//接单
	public static final String POWER_ORDERS_WORK="/androidMgt/androidFacadeAction!reportReceiving";	
	//开始发电
	public static final String POWER_START_GENERATION="/androidMgt/androidFacadeAction!reportGenerating";
	//结束发电
	public static final String POWER_END_GENERATION="/androidMgt/androidFacadeAction!reportGenerateFinish";
	//人员位置
	public static final String ADD_PERSONNEL_POSITION="/androidMgt/androidFacadeAction!StaffAddressBack";
	//上传错误日志接口
	public static final	String POST_ERROR_LOG="/androidMgt/androidFacadeAction!androidErrorLog";
	//加载所有故障工单
	public static final String LOAD_FAULT_WORK="/androidMgt/androidFacadeAction!loadOrderList";
	//故障工单接单
	public static final String POWER_FAULT_WORK="/androidMgt/androidFacadeAction!orderReceivingInfo";
	//故障工单开始发电
	public static final String POWER_FAULT_START_WORK="/androidMgt/androidFacadeAction!powerGenerationStart";
	//故障工单结束发电
	public static final String POWER_FAULT_END_WORK="/androidMgt/androidFacadeAction!powerGenerationStop";
	//计划发电工单驳回
	public static final String POWER_TURN_DOWN="/androidMgt/androidFacadeAction!turnDown";
	//计划发电转派加载发电负责人
	public static final String POWER_TRANSFER_PERSON="/androidMgt/androidFacadeAction!getFapgperson";
	//计划发电转派工单
	public static final String POWER_TRANSFER_WORK="/androidMgt/androidFacadeAction!redeploy";
	//事故工单转派工单
	public static final String FAULT_TRANSFER_WORK="/androidMgt/androidFacadeAction!sendLeader";
	//事故发电驳回工单
	public static final String FAULT_TURN_DOWN="/androidMgt/androidFacadeAction!rejectedOrder";
	//修改密码接口
	public static final String UPDATE_PASSWORD="/system/usersAction!editUserPassword";
	//版本更新
	public static final String UPLOAD_VESION="/system/androidVersionMainupdateAction!getVersionNum";
	//事故工单所有状态接口
	public static final String FAULT_LOAD_ALL_WORK="/androidMgt/androidFacadeAction!loadOrderListAll";
}
