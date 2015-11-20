package com.flows.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.flows.entity.UserRole;

public interface UserRoleDao extends PagingAndSortingRepository<UserRole, Integer>, JpaSpecificationExecutor<UserRole>, JpaRepository<UserRole, Integer> {

	public List<UserRole> findByUserId(int userId);
	
	/**
	 * 查询用户的角色Id集合
	 * @param userId
	 * @return
	 */
	@Query("select ur.roleId from UserRole ur where ur.userId= ?")
	public List<Integer> findRoleIdsByUserId(int userId);

	public void deleteByUserId(@Param("userId") int userId);
	
	

}
