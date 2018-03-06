package com.sekorm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekorm.dao.RoleDao;
import com.sekorm.entity.Role;
import com.sekorm.entity.RolePermssion;
import com.sekorm.entity.UserRole;
import com.sekorm.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role createRole(Role role) throws Exception {
		if(roleDao.createRole(role)<=0){
			role=null;
		}
		return role;
	}

	@Override
	public void deleteRole(String roleId) throws Exception {
		roleDao.deleteByUserRole(roleId);
		roleDao.deleteRole(roleId);
	}

	@Override
	public void correlationPermissions(String roleId, String... permissionIds) throws Exception {
		 if(permissionIds == null || permissionIds.length == 0) {
	            return;
	     }
		 for(int i=0;i<permissionIds.length;i++){
			 if(roleDao.exists(new RolePermssion(roleId,permissionIds[i]))<=0)
				 roleDao.correlationPermissions(new RolePermssion(roleId,permissionIds[i]));
		 }
	}
	
	@Override
	public void uncorrelationPermissions(String roleId, String... permissionIds) throws Exception {
		 if(permissionIds == null || permissionIds.length == 0) {
	         return;
	     }
		 for(int i=0;i<permissionIds.length;i++){
			 if(roleDao.exists(new RolePermssion(roleId,permissionIds[i]))>0)
				 roleDao.uncorrelationPermissions(new RolePermssion(roleId,permissionIds[i]));
		 }
	       
	}

}
