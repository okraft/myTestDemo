package com.flows.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.flows.bean.DataGrid;
import com.flows.bean.ResourceBean;
import com.flows.entity.Resource;
import com.flows.repository.ResourceDao;
import com.flows.repository.RoleResourceDao;
import com.flows.service.IResourceService;

@Service
public class ResourceServiceImpl implements IResourceService {
	
	@Autowired
	private ResourceDao  resourceDao;
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	public DataGrid<Resource> findByPage(int page, int rows, Specification<Resource> spec) {
		DataGrid<Resource> dataGrid = new DataGrid<Resource>();
		Page<Resource> resourcePage = resourceDao.findAll(spec, new PageRequest(page - 1, rows, Direction.DESC, new String[] { "ctime" }));
		dataGrid.setRows(resourcePage.getContent());
		dataGrid.setTotal(resourcePage.getTotalElements());
		return dataGrid;
	}

	public void deleteResouce(int id) {
		resourceDao.delete(id);
	}

	public void saveResource(ResourceBean resourceBean) throws Exception {
		Resource resource = null;
		if(resourceBean.getId() != null && resourceBean.getId() >0){
			resource = resourceDao.findOne(resourceBean.getId());
			if(null == resource){
				throw new Exception("resource is null");
			}
		}else{
			resource = new Resource();
		}
		resource.setName(resourceBean.getName());
		resource.setUrl(resourceBean.getUrl());
		resource.setParentId(resourceBean.getParentId());
		resource.setCuser(1);
		resource.setCtime(new Date());
		resourceDao.save(resource);
	}

	public Set<String> findUrlsByRoleIds(List<Integer> roleIds) {
		return roleResourceDao.findResourceUrlsByRoldIds(roleIds);
	}

	
	public List<Resource> findAll() {
		return (List<Resource>) resourceDao.findAll();
	}

}
