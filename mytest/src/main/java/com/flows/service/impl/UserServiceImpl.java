package com.flows.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.flows.bean.DataGrid;
import com.flows.bean.UserBean;
import com.flows.bean.ZTreeNode;
import com.flows.entity.Role;
import com.flows.entity.User;
import com.flows.entity.UserRole;
import com.flows.repository.UserDao;
import com.flows.repository.UserRoleDao;
import com.flows.service.IRoleService;
import com.flows.service.IUserService;
import com.flows.utils.MD5Util;
import com.flows.utils.ValidatorUtil;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private IRoleService roleService;
	
	public User findByUserName(String userName) {
		if(StringUtils.isBlank(userName)){
			return null;
		}
		if(ValidatorUtil.isEmail(userName)){
			return userDao.findByEmail(userName);
		}else if(ValidatorUtil.isMobile(userName)){
			return userDao.findByMobile(userName);
		}else{
			return userDao.findByCode(userName);
		}
	}
	
	public Set<String> findRoles(String userName) {
		User user = findByUserName(userName);
		if(user == null){
			return Collections.EMPTY_SET;
		}
		//获取用户所有角色
		Set<String> roleNameSet = new HashSet<String>();
		roleNameSet.add("admin");
		return roleNameSet;
	}

	public Set<String> findPermissions(String userName) {
		User user = findByUserName(userName);
		if(user == null){
			return Collections.EMPTY_SET;
		}
		
		Set<String> permsSet = new HashSet<String>();
		permsSet.add("user:add");
		return permsSet;
	}
	
	public DataGrid<User> findUsers() {
		DataGrid<User> dataGrid = new DataGrid<User>();
		List<User> userList = (List<User>) userDao.findAll();
		dataGrid.setRows(userList);
		if(userList != null && userList.size() > 0){
			dataGrid.setTotal(userList.size());
		}
		return dataGrid;
	}
	
	public DataGrid<User> findUsers(int page,int rows,Specification<User> spec) {
		DataGrid<User> dataGrid = new DataGrid<User>();
		Page<User> userPage = userDao.findAll(spec, new PageRequest(page - 1, rows, Direction.DESC, new String[] { "ctime" }));
		if(userPage !=null){
			dataGrid.setRows(setUserRole(userPage.getContent()));
			dataGrid.setTotal(userPage.getTotalElements());
		}
		return dataGrid;
	}
	
	private List<User> setUserRole(List<User> userList){
		if(userList !=null && userList.size() > 0){
			for (User user : userList) {
				StringBuilder userRoles = new StringBuilder();
				StringBuilder userRoleIds = new StringBuilder();
				List<Role> rolesList = roleService.findByUserId(user.getId());
				if(rolesList != null && rolesList.size() > 0){
					for (int i = 0; i < rolesList.size(); i++) {
						Role role = rolesList.get(i);
						if(i == rolesList.size() -1){
							userRoles.append(role.getName());
							userRoleIds.append(role.getId());
						}else{
							userRoles.append(role.getName() +",");
							userRoleIds.append(role.getId()+",");
						}
					}
					user.setRole(userRoles.toString());
					user.setRoleIds(userRoleIds.toString());
				}
			}
		}
		return userList;
	}

	public void deleteUser(int userId) {
		userDao.delete(userId);
	}

	public void saveUser(UserBean userBean) throws Exception {
		User user = null;
		if(userBean.getId() !=null && userBean.getId() > 0){
			user = userDao.findOne(userBean.getId());
			if(user == null){
				throw new Exception("update user is null");
			}
		}else{
			user = new User();
		}
		user.setCode(userBean.getCode());
		user.setName(userBean.getName());
		user.setEmail(userBean.getEmail());
		user.setMobile(userBean.getMobile());
		user.setPassword(MD5Util.string2MD5(userBean.getPassword()) );
		user.setCtime(new Date());
		userDao.save(user);
	}

	public void saveUserRole(int userId, List<Integer> roleIds) {
		List<UserRole> userRoles = userRoleDao.findByUserId(userId);
		if(userRoles != null && userRoles.size() > 0){
			userRoleDao.deleteInBatch(userRoles);
		}
		for (Integer roleId : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRole.setCuser(1);
			userRole.setCtime(new Date());
			userRoleDao.save(userRole);
		}
	}

	public List<ZTreeNode> showPermission(int userId) {
		List<Integer> roleIds = userRoleDao.findRoleIdsByUserId(userId);
		List<ZTreeNode> treeNodes = roleService.getRolePermissions(roleIds);
		for (ZTreeNode treeNode : treeNodes) {
			if(treeNode.getpId() == 0){
				treeNode.setChkDisabled(true);
			}
		}
		return treeNodes;
	}

	public String test() {
		return "hello world";
	}

}
