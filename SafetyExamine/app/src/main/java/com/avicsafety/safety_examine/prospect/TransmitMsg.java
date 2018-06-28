package com.avicsafety.safety_examine.prospect;


import com.avicsafety.safety_examine.model.ProspectBean;
import com.avicsafety.safety_examine.model.ProspectTotal;

import java.util.List;

/**
 * Created by Administrator on 2018/6/15 0015.
 * 将网络获取的数据传递到界面的接口
 */

public interface TransmitMsg {
    //获取changeOne界面的工单数量（勘察模块）
    void getTotal(ProspectTotal bean, String userName);
//    void getDateList(List<ProspectBean.ResponseBean> list,String userName);
//    void getDataItemDetails(List<ProspectBean.ResponseBean> list,String userName,String tickId);
//    void setWorkOrderState(String userName,String tickId,String status);
//    void workOrderDot(String userName,String tickId,Double longitude,Double latitude);
}
