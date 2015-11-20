package com.flows.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flows.bean.DataGrid;
import com.flows.bean.ZTreeNode;
import com.flows.entity.Model;
import com.flows.entity.Resource;
import com.flows.entity.Role;
import com.flows.entity.RoleResource;
import com.flows.repository.ModelDao;
import com.flows.repository.RoleDao;
import com.flows.repository.RoleResourceDao;
import com.flows.service.IResourceService;
import com.flows.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ModelDao modelDao;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	public DataGrid<Role> findRolesByPage() {
		DataGrid<Role> dataGrid = new DataGrid<Role>();
		List<Role> roleList = (List<Role>) roleDao.findAll();
		dataGrid.setRows(roleList);
		dataGrid.setTotal(roleList.size());
		return dataGrid;
	}
	
	public List<Role> findAll() {
		return (List<Role>) roleDao.findAll();
	}

	public void saveOrUpdateRole(Integer id, String name) throws Exception {
		Role role = null;
		if(id !=null && id > 0){
			role = roleDao.findOne(id);
			if(null == role){
				throw new Exception("update role is null");
			}
		}else{
			role = new Role();
		}
		role.setName(name);
		role.setCuser(1);
		role.setCtime(new Date());
		roleDao.save(role);
	}

	public void deleteRole(int roleId) {
		roleDao.delete(roleId);
	}

	public List<Role> findByUserId(int userId) {
		return roleDao.findByUserId(userId);
	}

	public List<ZTreeNode> getRolePermissions(int roleId) {
		
		return null;
	}
	
	public List<ZTreeNode> getRolePermissions(List<Integer> roleIds) {
		List<ZTreeNode> treeNodeList = new ArrayList<ZTreeNode>();
		/*List<Integer> resourceIds = roleResourceDao.findResourceIdsByRoldIds(roleIds);
		List<Model> modelList = (List<Model>) modelDao.findAll();
		for (Model model : modelList) {
			ZTreeNode treeNode = new ZTreeNode();
			treeNode.setId(model.getId());
			treeNode.setpId(model.getPid());
			treeNode.setName(model.getName());
			treeNode.setOpen(true);
			treeNodeList.add(treeNode);
			//获取当前模块对应的所有资源
			List<Resource> resources = resourceService.findByModelId(model.getId());
			if(resources!= null && resources.size() > 0){
				for (Resource resource : resources) {
					ZTreeNode rTreeNode = new ZTreeNode();
					rTreeNode.setId(resource.getId());
					rTreeNode.setpId(model.getId());
					rTreeNode.setName(resource.getName());
					rTreeNode.setOpen(true);
					rTreeNode.setNodeType(1);
					if(resourceIds.contains(rTreeNode.getId())){
						rTreeNode.setChecked(true);
					}
					treeNodeList.add(rTreeNode);
				}
			}
		}*/
		return treeNodeList;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void saveRoleResource(int roleId, List<Integer> resIds) {
		//删除角色对应的所有资源
		roleResourceDao.delByRoleId(roleId);
		if(resIds != null && resIds.size() > 0){
			for (Integer resId : resIds) {
				if(resId != null && resId > 0){
					RoleResource roleResource = new RoleResource();
					roleResource.setRoleId(roleId);
					roleResource.setResourceId(resId);
					roleResource.setCuser(1);
					roleResource.setCtime(new Date());
					roleResourceDao.save(roleResource);
				}
			}
		}
	}

	public Set<String> findNamesByUserId(int userId) {
		
		return null;
	}

}
