package com.flows.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flows.bean.DataGrid;
import com.flows.bean.UserBean;
import com.flows.bean.ZTreeNode;
import com.flows.core.bean.SearchParam;
import com.flows.core.bean.SpecificationUtil;
import com.flows.entity.Role;
import com.flows.entity.User;
import com.flows.security.realm.UserRealm;
import com.flows.service.IRoleService;
import com.flows.service.IUserService;

/**
 * 系统用户管理
 * 
 * @author Wanggg
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private UserRealm userRealm;

	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		List<Role> roleList = roleService.findAll();
		request.setAttribute("roleList", roleList);
		return "admin/user/userList";
	}

	@RequestMapping("list.json")
	@ResponseBody
	public DataGrid<User> list(HttpServletRequest request, @RequestParam("rows") int rows, @RequestParam("page") int page, @RequestParam(value = "searchParams", required = false) String searchParams) throws JsonParseException, JsonMappingException, IOException {
		List<SearchParam> params = null;
		if (searchParams != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			params = objectMapper.readValue(searchParams, new TypeReference<List<SearchParam>>() {
			});
		} else {
			params = new ArrayList<SearchParam>();
		}
		Specification<User> spec = new SpecificationUtil<User>().buildSpecification(params);
		return userService.findUsers(page, rows, spec);
	}

	@RequestMapping("deleteUser")
	@ResponseBody
	public boolean deleteUser(@RequestParam("userId") int userId) {
		try {
			userService.deleteUser(userId);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	@RequiresPermissions(value = "user:add")
	@RequestMapping("saveUser")
	@ResponseBody
	public boolean saveUser(UserBean userBean) {
		try {
			userService.saveUser(userBean);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	@RequestMapping("saveUserRole")
	@ResponseBody
	public boolean saveUserRole(@RequestParam("userId") int userId, @RequestParam("roleIds") List<Integer> roleIds) {
		try {
			userService.saveUserRole(userId, roleIds);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	@RequestMapping("showPermission")
	@ResponseBody
	public List<ZTreeNode> showPermission(@RequestParam("userId") int userId) {
		return userService.showPermission(userId);
	}

	@RequestMapping("cacheClear")
	public void cacheClear() {
		Subject subject = SecurityUtils.getSubject();
		userRealm.clearCache(subject.getPrincipals());
	}

}
