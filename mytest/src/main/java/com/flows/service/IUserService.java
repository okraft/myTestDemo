package com.flows.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.flows.bean.DataGrid;
import com.flows.bean.UserBean;
import com.flows.bean.ZTreeNode;
import com.flows.entity.User;

/**
 * @author Wanggg
 *
 */
public interface IUserService {
	
	/**
	 * 通过名查询用户
	 * @param userName 用户注册手机号，邮箱或登录代码
	 * @return
	 */
	public User findByUserName(String userName);
	
	public Set<String> findRoles(String userName);
	
	public Set<String> findPermissions(String userName);

	public DataGrid<User> findUsers();

	public DataGrid<User> findUsers(int page, int rows, Specification<User> spec);

	public void deleteUser(int userId);

	public void saveUser(UserBean userBean) throws Exception;

	public void saveUserRole(int userId, List<Integer> roleIds);
	
	public List<ZTreeNode> showPermission(int userId);
	
	public String test();

}
