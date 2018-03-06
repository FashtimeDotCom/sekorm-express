package com.sekorm.service;

import java.util.Set;

import com.sekorm.entity.User;

/**
 * 用户
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user) throws Exception;
    
    /**
     * 删除用户
     * @param user
     */
    public void deleteUser(User user) throws Exception;
    
    /**
     * 锁定用户
     * @param user
     */
    public void updateUser(User user) throws Exception;
    
    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(String userId, String newPassword) throws Exception;

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(String userId, String... roleIds) throws Exception;

    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(String userId, String... roleIds) throws Exception;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) throws Exception;

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) throws Exception;

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) throws Exception;

}
