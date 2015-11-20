package com.flows.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flows.bean.TreeNode;
import com.flows.service.IAdminMainService;

@Controller
@RequestMapping("admin/main")
public class AdminMainController {

	@Autowired
	private IAdminMainService adminMainService;
	
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("admin/index");
		return mav;
	}
	
	@RequestMapping("getMenuTree")
	@ResponseBody
	public List<TreeNode> getMenuTree(HttpServletRequest request){
		int userId = 3;
		List<TreeNode> menuTree = adminMainService.getMenuTree(userId);
		return menuTree;
	}
	
}
