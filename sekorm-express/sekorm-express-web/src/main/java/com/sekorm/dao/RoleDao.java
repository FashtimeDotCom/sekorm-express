package com.sekorm.dao;

import java.util.HashMap;
import java.util.List;

import com.sekorm.entity.Role;
import com.sekorm.entity.RolePermssion;


public interface RoleDao {
	
	public abstract int exists(RolePermssion rolePermssion) throws Exception;
	
	public abstract List<HashMap<String,String>> findInfo(String name) throws Exception;
	
	public abstract List<HashMap<String,String>> findRoleId() throws Exception;
    
	public abstract int createRole(Role role) throws Exception;
    
    public abstract void deleteRole(String roleId) throws Exception;
    
    public abstract void deleteByUserRole(String roleId) throws Exception;
    
    public abstract void correlationPermissions(RolePermssion rolePermssion) throws Exception;
    
    public abstract void uncorrelationPermissions(RolePermssion rolePermssion) throws Exception;
    
}
