package com.sekorm.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.XStream;
@XmlRootElement
public class TaskResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static XStream xstream;
	private Boolean result;
	private String returnCode;
	private String message;
	@XmlElement
	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	@XmlElement
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	@XmlElement
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private static XStream getXStream() {
		if (xstream == null) {
			xstream = new XStream();
			xstream.autodetectAnnotations(true);
			xstream.alias("orderResponse", TaskResponse.class);
		}
		return xstream;
	}

	public String toXml(){
		return "<?xml version='1.0' encoding='UTF-8'?>\r\n" + getXStream().toXML(this);
	}

	public static TaskResponse fromXml(String sXml){
		return (TaskResponse)getXStream().fromXML(sXml);
	}

}
