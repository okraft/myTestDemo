package com.flows.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.flows.entity.MTree;

public interface MTreeDao extends PagingAndSortingRepository<MTree, Integer>, JpaSpecificationExecutor<MTree> {

	@Query("select t from MTree t where t.id in (:idsList)")
	public List<MTree> findByIds(@Param("idsList") List<Integer> idsList);

}
