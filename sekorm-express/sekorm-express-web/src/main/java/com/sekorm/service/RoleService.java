package com.sekorm.service;

import com.sekorm.entity.Role;

/**
 * 角色
 */
public interface RoleService {


    public Role createRole(Role role) throws Exception;
    public void deleteRole(String roleId) throws Exception;

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(String roleId, String... permissionIds) throws Exception;

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(String roleId, String... permissionIds) throws Exception;

}
