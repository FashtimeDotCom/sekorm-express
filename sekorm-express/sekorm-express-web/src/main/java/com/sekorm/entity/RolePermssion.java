package com.sekorm.entity;

import java.io.Serializable;

public class RolePermssion implements Serializable {

	private static final long serialVersionUID = 1L;
	private String roleId;
    private String permissionId;
    
    public RolePermssion (){
    	
    }
    public RolePermssion(String roleId,String permissionId){
    	this.roleId=roleId;
    	this.permissionId=permissionId;
    }
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
}
