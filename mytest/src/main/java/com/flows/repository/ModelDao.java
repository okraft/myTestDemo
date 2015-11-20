package com.flows.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.flows.entity.Model;

public interface ModelDao extends PagingAndSortingRepository<Model, Integer>, JpaSpecificationExecutor<Model> {

}
