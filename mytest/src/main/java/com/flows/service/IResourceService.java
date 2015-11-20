package com.flows.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.flows.bean.DataGrid;
import com.flows.bean.ResourceBean;
import com.flows.entity.Resource;

public interface IResourceService {
	
	public DataGrid<Resource> findByPage(int page, int rows, Specification<Resource> spec);
	
	public void saveResource(ResourceBean resourceBean) throws Exception;
	
	public void deleteResouce(int id);
	
	/**
	 * 查询角色对应的所有资源url集合
	 * @param roleIds
	 * @return
	 */
	public Set<String> findUrlsByRoleIds(List<Integer> roleIds);
	
	public List<Resource> findAll();
	
	
}
