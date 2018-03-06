package com.sekorm.dao;

import com.sekorm.entity.Permission;

public interface PermissionDao {

    public abstract int createPermission(Permission permission) throws Exception;
    
    public abstract void deletePermission(String permissionId) throws Exception;
    
    public abstract void deleteByRolePermission(String permissionId) throws Exception;
}
