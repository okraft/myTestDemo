package com.flows.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flows.bean.DataGrid;
import com.flows.bean.ZTreeNode;
import com.flows.entity.Role;
import com.flows.service.IRoleService;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private IRoleService roleService;

	@RequestMapping("index")
	public String index() {
		return "admin/role/roleList";
	}

	@RequestMapping("list.json")
	@ResponseBody
	public DataGrid<Role> list() {
		return roleService.findRolesByPage();
	}

	@RequestMapping("saveRole")
	@ResponseBody
	public boolean saveRole(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		try {
			roleService.saveOrUpdateRole(id, name);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	@RequestMapping("deleteRole")
	@ResponseBody
	public boolean deleteRole(@RequestParam("roleId") int roleId) {
		try {
			roleService.deleteRole(roleId);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	
	@RequestMapping("showRolePermission")
	@ResponseBody
	public List<ZTreeNode> showRolePermission(@RequestParam("roleId") int roleId){
		return	roleService.getRolePermissions(roleId);
	}
	
	@RequestMapping("saveRoleResource")
	@ResponseBody
	public boolean saveRoleResource(@RequestParam("roleId") int roleId,@RequestParam("resIds") List<Integer> resIds){
		roleService.saveRoleResource(roleId, resIds);
		return true;
	}

}
