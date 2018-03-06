package com.sekorm.service;

import java.util.HashMap;
import java.util.List;

import com.sekorm.entity.Permission;


/**
 * 权限
 */
public interface PermissionService {
	
    public abstract Permission createPermission(Permission permission) throws Exception;
    
    public abstract void deletePermission(String permissionId) throws Exception;
    
    public abstract List<HashMap<String,String>> findInfo(String name) throws Exception;
    
    public abstract List<HashMap<String, String>> getUsersInfo(HashMap<String,String> hm) throws Exception;
    
    public abstract int getUsersInfoCount(HashMap<String,String> hm) throws Exception;
    
    public abstract int updateRole(String id) throws Exception;
    
    public abstract int deleteRole(String id) throws Exception;
}
