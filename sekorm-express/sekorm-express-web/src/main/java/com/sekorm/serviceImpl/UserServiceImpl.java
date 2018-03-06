package com.sekorm.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekorm.dao.UserDao;
import com.sekorm.entity.User;
import com.sekorm.entity.UserRole;
import com.sekorm.service.UserService;
import com.sekorm.util.PasswordHelper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordHelper passwordHelper;
	
	
	@Override
	public User createUser(User user) throws Exception {
		 passwordHelper.encryptPassword(user);
		 User u=userDao.findByUsername(user.getUsername());
		 if(u==null){
			 if(userDao.createUser(user)<=0){
				 user=null;
			 }
		 }else{
			 user=u;
		 }
	     return user;
	}

	@Override
	public void changePassword(String userId, String newPassword) throws Exception {
		User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
	}

	@Override
	public void correlationRoles(String userId, String... roleIds) throws Exception {
		 if(roleIds == null || roleIds.length == 0) {
	            return;
	     }
		 for(int i=0;i<roleIds.length;i++){
			 if(userDao.exists(new UserRole(userId,roleIds[i]))<=0)
				 userDao.correlationRoles(new UserRole(userId,roleIds[i]));
		 }
	}

	@Override
	public void uncorrelationRoles(String userId, String... roleIds) throws Exception {
		if(roleIds == null || roleIds.length == 0) {
            return;
        }
		for(int i=0;i<roleIds.length;i++){
			if(userDao.exists(new UserRole(userId,roleIds[i]))>0)
				userDao.uncorrelationRoles(new UserRole(userId,roleIds[i]));
		}
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return userDao.findByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) throws Exception {
		Set<String> set=new HashSet<>();
		List<String> list=userDao.findRoles(username);
		if(list!=null && !list.isEmpty()){
			for(String str:list){
				set.add(str);
			}
		}
		return set;
	}

	@Override
	public Set<String> findPermissions(String username) throws Exception {
		Set<String> set=new HashSet<>();
		List<String> list=userDao.findPermissions(username);
		if(list!=null && !list.isEmpty()){
			for(String str:list){
				set.add(str);
			}
		}
		return set;
	}
	
	@Override
	public void deleteUser(User user) throws Exception {
		userDao.deleteUser(user.getId());
	}
	
	@Override
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}

}
