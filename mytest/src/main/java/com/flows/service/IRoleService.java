package com.flows.service;

import java.util.List;
import java.util.Set;

import com.flows.bean.DataGrid;
import com.flows.bean.ZTreeNode;
import com.flows.entity.Role;

public interface IRoleService {
	
	public DataGrid<Role> findRolesByPage();
	
	public List<Role> findAll();
	
	public void saveOrUpdateRole(Integer id,String name) throws Exception;
	
	public void deleteRole(int roleId);
	
	public List<Role> findByUserId(int userId);
	
	public Set<String> findNamesByUserId(int userId);
	
	public List<ZTreeNode> getRolePermissions(int roleId);

	public List<ZTreeNode> getRolePermissions(List<Integer> roleIds);
	
	public void saveRoleResource(int roleId,List<Integer> resIds);
	

}
