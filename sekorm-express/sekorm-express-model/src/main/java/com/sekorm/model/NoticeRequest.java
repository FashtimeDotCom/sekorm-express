package com.sekorm.model;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

public class NoticeRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	private static XStream xstream;
	private String uuid;
	private String status;
	private String billstatus;
	private String message;
	private String autoCheck;
	private String comOld;
	private String comNew;
	private String exceptionStatus;
	private Result lastResult = new Result();
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getExceptionStatus() {
		return exceptionStatus;
	}

	public void setExceptionStatus(String exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result getLastResult() {
		return lastResult;
	}

	public void setLastResult(Result lastResult) {
		this.lastResult = lastResult;
	}
	
	public String getAutoCheck() {
		return autoCheck;
	}

	public void setAutoCheck(String autoCheck) {
		this.autoCheck = autoCheck;
	}

	public String getComOld() {
		return comOld;
	}

	public void setComOld(String comOld) {
		this.comOld = comOld;
	}

	public String getComNew() {
		return comNew;
	}

	public void setComNew(String comNew) {
		this.comNew = comNew;
	}

	private static XStream getXStream() {
		if (xstream == null) {
			xstream = new XStream();
			xstream.autodetectAnnotations(true);
			xstream.alias("pushRequest", NoticeRequest.class);
			xstream.alias("item", ResultItem.class);
			
		}
		return xstream;
	}

	public String toXml() {
		return "<?xml version='1.0' encoding='UTF-8'?>\r\n" + getXStream().toXML(this);
	}

	public static NoticeRequest fromXml(String sXml) {
		return (NoticeRequest) getXStream().fromXML(sXml);
	}

}
