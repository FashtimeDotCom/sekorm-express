package com.sekorm.entity;

import java.io.Serializable;

public class LdapVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cn;
	private String dn;
	private String displayName;
	private String mobile;
	private String name;
	private String sAMAccountName;
	private String telephoneNumber;
	private String title;
	private String userPrincipalName;
	private String whenChanged;
	private String whenCreated;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getsAMAccountName() {
		return sAMAccountName;
	}

	public void setsAMAccountName(String sAMAccountName) {
		this.sAMAccountName = sAMAccountName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getWhenChanged() {
		return whenChanged;
	}

	public void setWhenChanged(String whenChanged) {
		this.whenChanged = whenChanged;
	}

	public String getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(String whenCreated) {
		this.whenCreated = whenCreated;
	}

	@Override
	public String toString() {
		return "LdapEntity [cn=" + cn + ", dn=" + dn + ", displayName="
				+ displayName + ", mobile=" + mobile + ", name=" + name
				+ ", sAMAccountName=" + sAMAccountName + ", telephoneNumber="
				+ telephoneNumber + ", title=" + title + ", userPrincipalName="
				+ userPrincipalName + ", whenChanged=" + whenChanged
				+ ", whenCreated=" + whenCreated + "]";
	}

}
