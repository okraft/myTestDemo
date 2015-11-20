package com.flows.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.flows.entity.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Serializable>,JpaSpecificationExecutor<Role> {

	@Query(value="select r.* from sys_role r join sys_user_role ur on r.id = ur.role_id where ur.user_id = ? ",nativeQuery=true)
	public List<Role> findByUserId(int userId);
	
}
