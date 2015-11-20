package com.flows.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.flows.entity.Resource;

public interface ResourceDao extends PagingAndSortingRepository<Resource, Integer>, JpaSpecificationExecutor<Resource> {

	/**
	 * 查询角色对应的所有资源
	 * 
	 * @param roleIds
	 * @return
	 */
	@Query(value = "select r.* from  sys_resource r join sys_role_resource t on r.id = t.resource_id where t.role_id in (:roleIds) ", nativeQuery = true)
	public List<Resource> findByRoleIds(@Param("roleIds") List<Integer> roleIds);

	/**
	 * 查询用户菜单资源
	 * @param roleIds
	 * @return
	 */
	@Query(value = "select r.* from  sys_resource r join sys_role_resource t on r.id = t.resource_id where t.role_id in (:roleIds) and r.type ='menu' order by r.priority asc", nativeQuery = true)
	public List<Resource> findMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);
	
}
