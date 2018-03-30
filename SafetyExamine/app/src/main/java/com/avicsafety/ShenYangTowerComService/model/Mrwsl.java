package com.avicsafety.ShenYangTowerComService.model;

public class Mrwsl {

	// "total": "34",
	// "new": "28",
	// "processing": "2",
	// "audit": "0",
	// "validate": "3",
	// "together": "1"

	private String total;
	private String newc;// newc
	private String processing;
	private String audit;
	private String validate;
	private String together;



	private String reminders;//催办数量
	private String remindersBak;//催办反馈数量
	private String supervise;//工单督办
	private String superviseBak;//督办反馈数量

	public String getAudit() {
		return audit;
	}

	public String getNewc() {
		return newc;
	}

	public String getProcessing() {
		return processing;
	}

	public String getTogether() {
		return together;
	}

	public String getTotal() {
		return total;
	}

	public String getValidate() {
		return validate;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public void setNewc(String newc) {
		this.newc = newc;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public void setTogether(String together) {
		this.together = together;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setValidate(String validate) {
		this.validate = validate;
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
}
