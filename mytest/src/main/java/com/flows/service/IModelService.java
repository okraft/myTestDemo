package com.flows.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.flows.bean.TreeNode;
import com.flows.entity.Model;

public interface IModelService {
	
	public List<Model> findAll();
	
	public List<Model> findByPage(Specification<Model> spec);
	
	public void deleteById(int id);
	
	public void saveModel(Model model) throws Exception;
	
	public List<TreeNode> getModelTree();
	
}
