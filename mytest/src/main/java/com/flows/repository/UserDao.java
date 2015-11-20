package com.flows.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.flows.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Integer>,JpaSpecificationExecutor<User>{

	public User findByEmail(String email);
	
	public User findByMobile(String mobile);
	
	public User findByCode(String code);
	
}
