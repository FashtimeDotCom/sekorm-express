package com.sekorm.entity;

import java.io.Serializable;

public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String permission; //权限标识 程序中判断使用,如"user:create"
    private String description; //权限描述,UI界面显示使用
    private String available ="0"; //是否可用,如果不可用将不会添加给用户

    public Permission() {
    }

    public Permission(String permission, String description, String available) {
        this.permission = permission;
        this.description = description;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
    
}
