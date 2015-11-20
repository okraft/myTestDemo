package com.flows.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.flows.bean.TreeNode;
import com.flows.entity.Model;
import com.flows.repository.ModelDao;
import com.flows.service.IModelService;

@Service
public class ModelServiceImpl implements IModelService {

	@Autowired
	private ModelDao modelDao;

	public List<Model> findAll() {
		return (List<Model>) modelDao.findAll();
	}

	public List<Model> findByPage(Specification<Model> spec) {
		return modelDao.findAll(spec);
	}

	public void deleteById(int id) {
		modelDao.delete(id);
	}

	public void saveModel(Model model) throws Exception {
		Model model2 = null;
		if(model.getId() > 0){
			model2 = modelDao.findOne(model.getId());
			if(model2 == null){
				throw new Exception("model is null");
			}
		}else{
			model2 = new Model();
		}
		model2.setPid(model.getPid());
		model2.setIsLeaf(model.getIsLeaf());
		model2.setName(model.getName());
		model2.setCtime(new Date());
		model2.setCuser(1);
		modelDao.save(model2);
	}

	public List<TreeNode> getModelTree() {
		List<TreeNode> treeList = new LinkedList<TreeNode>();
		List<Model> modelList = (List<Model>) modelDao.findAll();
		if(modelList != null && modelList.size() > 0){
			Map<Integer, TreeNode> treeNodeMap = new HashMap<Integer, TreeNode>();
			for (Model model : modelList) {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(model.getId());
				treeNode.setpId(model.getPid());
				treeNode.setText(model.getName());
				treeNode.setState(TreeNode.NODE_STATE_OPEN);
				treeNodeMap.put(treeNode.getId(), treeNode);
			}
			
			for (Model model : modelList) {
				TreeNode treeNode = treeNodeMap.get(model.getId());
				if(treeNode.getpId() > 0){
					TreeNode ptreeNode = treeNodeMap.get(treeNode.getpId());
					ptreeNode.addNode(treeNode);
				}else{
					treeList.add(treeNode);
				}
			}
		}
		return treeList;
	}

}
