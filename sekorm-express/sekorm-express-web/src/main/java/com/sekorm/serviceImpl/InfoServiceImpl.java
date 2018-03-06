package com.sekorm.serviceImpl;

import javax.security.auth.x500.X500Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekorm.dao.UserDao;
import com.sekorm.entity.User;
import com.sekorm.service.InfoService;
import com.sekorm.util.PasswordHelper;
@Service
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Override
	public int updatePassWord(String name,String newPassWord)
			throws Exception {
		int i=1;
		try{
			User user =userDao.findByUsername(name);
	        user.setPassword(newPassWord);
	        passwordHelper.encryptPassword(user);
	        userDao.updateUser(user);
		}catch(Exception e){
			i=0;
		}
		return i;
	}

}
