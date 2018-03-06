package com.sekorm.dao;

import java.util.HashMap;
import java.util.List;

import com.sekorm.entity.User;
import com.sekorm.entity.UserRole;


public interface UserDao {
	
    public abstract int createUser(User user) throws Exception;
    
    public abstract void updateUser(User user) throws Exception;
    
    public abstract int exists(UserRole userRole) throws Exception;
    
    public abstract void deleteUser(String userId) throws Exception;
    
    public abstract void correlationRoles(UserRole userRole) throws Exception;
    
    public abstract void uncorrelationRoles(UserRole userRole) throws Exception;
    
    public abstract User findOne(String userId) throws Exception;
    
    public abstract User findByUsername(String username) throws Exception;
    
    public abstract List<String> findRoles(String username) throws Exception;
    
    public abstract List<String> findPermissions(String username) throws Exception;
    
    public abstract List<HashMap<String,String>> getUsersInfo(HashMap<String,String> hm) throws Exception;
    
    public abstract int getUsersInfoCount(HashMap<String,String> hm) throws Exception;
    
}
