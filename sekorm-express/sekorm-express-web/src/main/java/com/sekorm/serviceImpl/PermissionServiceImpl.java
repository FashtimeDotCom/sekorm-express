package com.sekorm.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekorm.dao.PermissionDao;
import com.sekorm.dao.RoleDao;
import com.sekorm.dao.UserDao;
import com.sekorm.entity.Permission;
import com.sekorm.entity.UserRole;
import com.sekorm.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public Permission createPermission(Permission permission) throws Exception {
		if(permissionDao.createPermission(permission)<=0){
			permission=null;
		}
		return permission;
	}

	@Override
	public void deletePermission(String permissionId) throws Exception {
		permissionDao.deleteByRolePermission(permissionId);
		permissionDao.deletePermission(permissionId);
	}

	@Override
	public List<HashMap<String, String>> findInfo(String name) throws Exception {
		return roleDao.findInfo(name);
	}

	@Override
	public List<HashMap<String, String>> getUsersInfo(HashMap<String,String> hm) throws Exception {
		return userDao.getUsersInfo(hm);
	}

	@Override
	public int getUsersInfoCount(HashMap<String,String> hm) throws Exception {
		return userDao.getUsersInfoCount(hm);
	}

	@Override
	public int updateRole(String id) throws Exception {
		int i=1;
		try{
			 List<HashMap<String,String>> list= roleDao.findRoleId();
			 String roleId=null;
			 for(HashMap<String,String> hm:list){
				 if("edit".equals(hm.get("ROLE"))){
					 roleId=hm.get("ID");
				 }
			 }
			 userDao.correlationRoles(new UserRole(id,roleId));
		}catch(Exception e){
			i=0;
		}
		return i;
	}

	@Override
	public int deleteRole(String id) throws Exception {
		int i=1;
		try{
			 List<HashMap<String,String>> list= roleDao.findRoleId();
			 String roleId=null;
			 for(HashMap<String,String> hm:list){
				 if("edit".equals(hm.get("ROLE"))){
					 roleId=hm.get("ID");
				 }
			 }
			 userDao.uncorrelationRoles(new UserRole(id,roleId));
		}catch(Exception e){
			i=0;
		}
		return i;
	}

}
