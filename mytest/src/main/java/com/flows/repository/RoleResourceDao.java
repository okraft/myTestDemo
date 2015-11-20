package com.flows.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.flows.entity.RoleResource;

public interface RoleResourceDao extends PagingAndSortingRepository<RoleResource, Long>, JpaSpecificationExecutor<RoleResource> {

	@Query("select r.resourceId from RoleResource r where r.roleId = ?")
	public List<Integer> findResourceIdsByRoldId(int roleId);

	@Query(value = "select r.url from sys_role_resource rr left join test_resource r on rr.resource_id = r.id where rr.role_id in (:roleIds)", nativeQuery = true)
	public Set<String> findResourceUrlsByRoldIds(@Param("roleIds") List<Integer> roleIds);

	@Modifying
	@Query(value = "DELETE FROM sys_role_resource WHERE role_id = :roleId ", nativeQuery = true)
	public void delByRoleId(@Param("roleId") long roleId);
	
	
	@Query("select r.resourceId from RoleResource r where r.roleId in (:roleIds)")
	public List<Integer> findResourceIdsByRoldIds(@Param("roleIds") List<Integer> roleIds);
	
	
	

}
