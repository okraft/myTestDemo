package com.flows.service;

import java.util.List;

import com.flows.bean.TreeNode;

public interface IAdminMainService {
	/**
	 * 根据用户获取菜单树
	 * @param userId
	 * @return
	 */
	public  List<TreeNode> getMenuTree(int userId);
	
}
