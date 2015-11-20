package com.flows.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flows.bean.TreeNode;
import com.flows.entity.Resource;
import com.flows.repository.MTreeDao;
import com.flows.repository.ResourceDao;
import com.flows.repository.UserRoleDao;
import com.flows.service.IAdminMainService;
import com.flows.service.IResourceService;

@Service
public class AdminMainServiceImpl implements IAdminMainService {

	@Autowired
	private MTreeDao mTreeDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private IResourceService resourceService;

	public List<TreeNode> getMenuTree(int userId) {
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		// 获取用户所有角色Id
		List<Integer> userRoleIds = userRoleDao.findRoleIdsByUserId(userId);
		// 获取用户所有菜单资源
		List<Resource> menuResourceList = resourceDao.findMenuByRoleIds(userRoleIds);
		// 将菜单资源转换为树节点指定格式
		return convertToTreeNode(treeNodeList, menuResourceList);
	}

	private List<TreeNode> convertToTreeNode(List<TreeNode> treeNodeList, List<Resource> menuResourceList) {
		Map<Integer, TreeNode> treeNodeMap = new HashMap<Integer, TreeNode>();
		for (Resource resource : menuResourceList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(resource.getId());
			treeNode.setpId(resource.getParentId());
			treeNode.setText(resource.getName());
			if (StringUtils.isNotBlank(resource.getUrl())) {
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", resource.getUrl());
				treeNode.setAttributes(attributes);
			}
			treeNodeMap.put(treeNode.getId(), treeNode);
		}

		for (Entry<Integer, TreeNode> nodeMap : treeNodeMap.entrySet()) {
			TreeNode treeNode = nodeMap.getValue();
			if (treeNode.getpId() > 0) {
				TreeNode pNode = treeNodeMap.get(treeNode.getpId());
				pNode.addNode(treeNode);
			} else {
				treeNodeList.add(treeNode);
			}
		}
		return treeNodeList;
	}

}
