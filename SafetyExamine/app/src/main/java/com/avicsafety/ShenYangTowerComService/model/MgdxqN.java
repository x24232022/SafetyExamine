package com.avicsafety.ShenYangTowerComService.model;

public class MgdxqN {

	private String faulttitle;
	private String no;
	private String status;
	private String serialNo;
	private String alarmId;
	private String alarmStaId;
	private String oriAlarmId;
	private String alarmtime;
	private String alarmdevtype;
	private String vendor;
	private String equipType;
	private String tyneid;
	private String bak1;
	private String alarmlevel;
	private String alarmtype;
	private String alarmSubType;
	private String alarmProvince;
	private String alarmlocation;
	private String orientation;
	private String belongs;
	private String alarmHandleLevel;
	private String createType;
	private String belongsuburb;
	private String btslevel;
	private String nodeCn;
	private String findTime;
	private String acknowledged;
	private String failuredescription;
	private String alarmServEffect;
	private String alarmAutoHandle;
	private String chinaMobileUser;
	private String chinaMobileUserTel;
	private String chinaMobileTogetherDate;
	private String transferUserName;
	private String transferUserId;
	private String transferUserTel;
	private String lineMaintenanceName;
	private String lineMaintenanceNameid;
	private String lineMaintenanceNametp;
	private String faultrectime;
	private String faultautorectime;
	private String teleAlarmreaanalysis;
	private String teleSubAlarmreaanalysis;
	private String alarmrealist;
	private String resprocess;
	private String other;
	private String rejectreason;
	private String rejectreason2;
	private String validateContent;
	private String togetherContent;
	private String towerUser;
	private String towerUserTel;
	private String mustTogetherDate;
	private String sla_createtime;
	private String sla_assignetime;
	private String sla_resolutiontime;
	private String sla_closetime;
	private String transferDate;
	private String signInDate;
	private String location;
	private String isReject;
	private String specialist;
	private String operatorSiteLevel;// 运营商
	private String siteNo;//站址编码
	private String bak3;//资源归属

	public String getIsNeedSupervise() {
		return isNeedSupervise;
	}

	public void setIsNeedSupervise(String isNeedSupervise) {
		this.isNeedSupervise = isNeedSupervise;
	}

	public String getIsSupervise() {
		return isSupervise;
	}

	public void setIsSupervise(String isSupervise) {
		this.isSupervise = isSupervise;
	}

	public String getSupervise() {
		return supervise;
	}

	public void setSupervise(String supervise) {
		this.supervise = supervise;
	}

	public String getSuperviseBak() {
		return superviseBak;
	}

	public void setSuperviseBak(String superviseBak) {
		this.superviseBak = superviseBak;
	}

	public String getSuperviseDate() {
		return superviseDate;
	}

	public void setSuperviseDate(String superviseDate) {
		this.superviseDate = superviseDate;
	}

	public String getSuperviseBakDate() {
		return superviseBakDate;
	}

	public void setSuperviseBakDate(String superviseBakDate) {
		this.superviseBakDate = superviseBakDate;
	}

	public String getIsNeedReminders() {
		return isNeedReminders;
	}

	public void setIsNeedReminders(String isNeedReminders) {
		this.isNeedReminders = isNeedReminders;
	}

	public String getIsReminders() {
		return isReminders;
	}

	public void setIsReminders(String isReminders) {
		this.isReminders = isReminders;
	}

	public String getReminders() {
		return reminders;
	}

	public void setReminders(String reminders) {
		this.reminders = reminders;
	}

	public String getRemindersBak() {
		return remindersBak;
	}

	public void setRemindersBak(String remindersBak) {
		this.remindersBak = remindersBak;
	}

	public String getRemindersDate() {
		return remindersDate;
	}

	public void setRemindersDate(String remindersDate) {
		this.remindersDate = remindersDate;
	}

	public String getRemindersBakDate() {
		return remindersBakDate;
	}

	public void setRemindersBakDate(String remindersBakDate) {
		this.remindersBakDate = remindersBakDate;
	}

	private String isNeedSupervise;		//是否需要督办
	private String isSupervise;		//是否督办
	private String supervise;		//督办内容
	private String superviseBak;	//督办反馈内容
	private String superviseDate;		//督办时间
	private String superviseBakDate;	//督办反馈时间

	private String isNeedReminders;		//是否需要催办
	private String isReminders;		//是否催办
	private String reminders;		//催办内容
	private String remindersBak;	//催办反馈内容
	private String remindersDate;		//催办时间
	private String remindersBakDate;	//催办反馈时间
	private String signProcessDate; //处理人签收时间
	private String processBakDate;
    private String processBak;// 处理人反馈



    public String getProcessBak() {
        return processBak;
    }

    public void setProcessBak(String processBak) {
        this.processBak = processBak;
    }

	public String getSignProcessDate() {
		return signProcessDate;
	}

	public void setSignProcessDate(String signProcessDate) {
		this.signProcessDate = signProcessDate;
	}

	public String getProcessBakDate() {
		return processBakDate;
	}

	public void setProcessBakDate(String processBakDate) {
		this.processBakDate = processBakDate;
	}




	public String getAcknowledged() {
		return acknowledged;
	}

	public String getAlarmAutoHandle() {
		return alarmAutoHandle;
	}

	public String getAlarmdevtype() {
		return alarmdevtype;
	}

	public String getAlarmHandleLevel() {
		return alarmHandleLevel;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public String getAlarmlevel() {
		return alarmlevel;
	}

	public String getAlarmlocation() {
		return alarmlocation;
	}

	public String getAlarmProvince() {
		return alarmProvince;
	}

	public String getAlarmrealist() {
		return alarmrealist;
	}

	public String getAlarmServEffect() {
		return alarmServEffect;
	}

	public String getAlarmStaId() {
		return alarmStaId;
	}

	public String getAlarmSubType() {
		return alarmSubType;
	}

	public String getAlarmtime() {
		return alarmtime;
	}

	public String getAlarmtype() {
		return alarmtype;
	}

	public String getBak1() {
		return bak1;
	}

	public String getBelongs() {
		return belongs;
	}

	public String getBelongsuburb() {
		return belongsuburb;
	}

	public String getBtslevel() {
		return btslevel;
	}

	public String getChinaMobileTogetherDate() {
		return chinaMobileTogetherDate;
	}

	public String getChinaMobileUser() {
		return chinaMobileUser;
	}

	public String getChinaMobileUserTel() {
		return chinaMobileUserTel;
	}

	public String getCreateType() {
		return createType;
	}

	public String getEquipType() {
		return equipType;
	}

	public String getFailuredescription() {
		return failuredescription;
	}

	public String getFaultautorectime() {
		return faultautorectime;
	}

	public String getFaultrectime() {
		return faultrectime;
	}

	public String getFaulttitle() {
		return faulttitle;
	}

	public String getFindTime() {
		return findTime;
	}

	public String getIsReject() {
		return isReject;
	}

	public String getLineMaintenanceName() {
		return lineMaintenanceName;
	}

	public String getLineMaintenanceNameid() {
		return lineMaintenanceNameid;
	}

	public String getLineMaintenanceNametp() {
		return lineMaintenanceNametp;
	}

	public String getLocation() {
		return location;
	}

	public String getMustTogetherDate() {
		return mustTogetherDate;
	}

	public String getNo() {
		return no;
	}

	public String getNodeCn() {
		return nodeCn;
	}

	public String getOriAlarmId() {
		return oriAlarmId;
	}

	public String getOrientation() {
		return orientation;
	}

	public String getOther() {
		return other;
	}

	public String getRejectreason() {
		return rejectreason;
	}

	public String getRejectreason2() {
		return rejectreason2;
	}

	public String getResprocess() {
		return resprocess;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public String getSignInDate() {
		return signInDate;
	}

	public String getSla_assignetime() {
		return sla_assignetime;
	}

	public String getSla_closetime() {
		return sla_closetime;
	}

	public String getSla_createtime() {
		return sla_createtime;
	}

	public String getSla_resolutiontime() {
		return sla_resolutiontime;
	}

	public String getStatus() {
		return status;
	}

	public String getTeleAlarmreaanalysis() {
		return teleAlarmreaanalysis;
	}

	public String getTeleSubAlarmreaanalysis() {
		return teleSubAlarmreaanalysis;
	}

	public String getTogetherContent() {
		return togetherContent;
	}

	public String getTowerUser() {
		return towerUser;
	}

	public String getTowerUserTel() {
		return towerUserTel;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public String getTransferUserId() {
		return transferUserId;
	}

	public String getTransferUserName() {
		return transferUserName;
	}

	public String getTransferUserTel() {
		return transferUserTel;
	}

	public String getTyneid() {
		return tyneid;
	}

	public String getValidateContent() {
		return validateContent;
	}

	public String getVendor() {
		return vendor;
	}

	public void setAcknowledged(String acknowledged) {
		this.acknowledged = acknowledged;
	}

	public void setAlarmAutoHandle(String alarmAutoHandle) {
		this.alarmAutoHandle = alarmAutoHandle;
	}

	public void setAlarmdevtype(String alarmdevtype) {
		this.alarmdevtype = alarmdevtype;
	}

	public void setAlarmHandleLevel(String alarmHandleLevel) {
		this.alarmHandleLevel = alarmHandleLevel;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public void setAlarmlevel(String alarmlevel) {
		this.alarmlevel = alarmlevel;
	}

	public void setAlarmlocation(String alarmlocation) {
		this.alarmlocation = alarmlocation;
	}

	public void setAlarmProvince(String alarmProvince) {
		this.alarmProvince = alarmProvince;
	}

	public void setAlarmrealist(String alarmrealist) {
		this.alarmrealist = alarmrealist;
	}

	public void setAlarmServEffect(String alarmServEffect) {
		this.alarmServEffect = alarmServEffect;
	}

	public void setAlarmStaId(String alarmStaId) {
		this.alarmStaId = alarmStaId;
	}

	public void setAlarmSubType(String alarmSubType) {
		this.alarmSubType = alarmSubType;
	}

	public void setAlarmtime(String alarmtime) {
		this.alarmtime = alarmtime;
	}

	public void setAlarmtype(String alarmtype) {
		this.alarmtype = alarmtype;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	public void setBelongs(String belongs) {
		this.belongs = belongs;
	}

	public void setBelongsuburb(String belongsuburb) {
		this.belongsuburb = belongsuburb;
	}

	public void setBtslevel(String btslevel) {
		this.btslevel = btslevel;
	}

	public void setChinaMobileTogetherDate(String chinaMobileTogetherDate) {
		this.chinaMobileTogetherDate = chinaMobileTogetherDate;
	}

	public void setChinaMobileUser(String chinaMobileUser) {
		this.chinaMobileUser = chinaMobileUser;
	}

	public void setChinaMobileUserTel(String chinaMobileUserTel) {
		this.chinaMobileUserTel = chinaMobileUserTel;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}

	public void setFailuredescription(String failuredescription) {
		this.failuredescription = failuredescription;
	}

	public void setFaultautorectime(String faultautorectime) {
		this.faultautorectime = faultautorectime;
	}

	public void setFaultrectime(String faultrectime) {
		this.faultrectime = faultrectime;
	}

	public void setFaulttitle(String faulttitle) {
		this.faulttitle = faulttitle;
	}

	public void setFindTime(String findTime) {
		this.findTime = findTime;
	}

	public void setIsReject(String isReject) {
		this.isReject = isReject;
	}

	public void setLineMaintenanceName(String lineMaintenanceName) {
		this.lineMaintenanceName = lineMaintenanceName;
	}

	public void setLineMaintenanceNameid(String lineMaintenanceNameid) {
		this.lineMaintenanceNameid = lineMaintenanceNameid;
	}

	public void setLineMaintenanceNametp(String lineMaintenanceNametp) {
		this.lineMaintenanceNametp = lineMaintenanceNametp;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMustTogetherDate(String mustTogetherDate) {
		this.mustTogetherDate = mustTogetherDate;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setNodeCn(String nodeCn) {
		this.nodeCn = nodeCn;
	}

	public void setOriAlarmId(String oriAlarmId) {
		this.oriAlarmId = oriAlarmId;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}

	public void setRejectreason2(String rejectreason2) {
		this.rejectreason2 = rejectreason2;
	}

	public void setResprocess(String resprocess) {
		this.resprocess = resprocess;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public void setSignInDate(String signInDate) {
		this.signInDate = signInDate;
	}

	public void setSla_assignetime(String sla_assignetime) {
		this.sla_assignetime = sla_assignetime;
	}

	public void setSla_closetime(String sla_closetime) {
		this.sla_closetime = sla_closetime;
	}

	public void setSla_createtime(String sla_createtime) {
		this.sla_createtime = sla_createtime;
	}

	public void setSla_resolutiontime(String sla_resolutiontime) {
		this.sla_resolutiontime = sla_resolutiontime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTeleAlarmreaanalysis(String teleAlarmreaanalysis) {
		this.teleAlarmreaanalysis = teleAlarmreaanalysis;
	}

	public void setTeleSubAlarmreaanalysis(String teleSubAlarmreaanalysis) {
		this.teleSubAlarmreaanalysis = teleSubAlarmreaanalysis;
	}

	public void setTogetherContent(String togetherContent) {
		this.togetherContent = togetherContent;
	}

	public void setTowerUser(String towerUser) {
		this.towerUser = towerUser;
	}

	public void setTowerUserTel(String towerUserTel) {
		this.towerUserTel = towerUserTel;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	public void setTransferUserId(String transferUserId) {
		this.transferUserId = transferUserId;
	}

	public void setTransferUserName(String transferUserName) {
		this.transferUserName = transferUserName;
	}

	public void setTransferUserTel(String transferUserTel) {
		this.transferUserTel = transferUserTel;
	}

	public void setTyneid(String tyneid) {
		this.tyneid = tyneid;
	}

	public void setValidateContent(String validateContent) {
		this.validateContent = validateContent;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public String getOperatorSiteLevel() {
		return operatorSiteLevel;
	}

	public void setOperatorSiteLevel(String operatorSiteLevel) {
		this.operatorSiteLevel = operatorSiteLevel;
	}

	public String getSiteNo() {
		return siteNo;
	}

	public void setSiteNo(String siteNo) {
		this.siteNo = siteNo;
	}

	public String getBak3() {
		return bak3;
	}

	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

}
