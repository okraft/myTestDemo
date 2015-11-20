package com.flows.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flows.bean.DataGrid;
import com.flows.bean.ResourceBean;
import com.flows.core.bean.SearchParam;
import com.flows.core.bean.SpecificationUtil;
import com.flows.entity.Model;
import com.flows.entity.Resource;
import com.flows.service.IModelService;
import com.flows.service.IResourceService;

/**
 * 资源管理:处理系统权限资源
 * 
 * @author Wanggg
 *
 */
@Controller
@RequestMapping("/admin/resource")
public class ResourceController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IModelService modelService;

	@RequestMapping("index")
	public String index(HttpServletRequest request) throws JsonProcessingException {
		//setModelJson(request);
		return "admin/resource/resourceList";
	}
	
	/*private void setModelJson(HttpServletRequest request) throws JsonProcessingException{
		List<Model> modelList =	modelService.findAll();
		request.setAttribute("modelList", modelList);
		Map<Integer, String> modelMap = new HashMap<Integer, String>();
		for (Model model : modelList) {
			modelMap.put(model.getId(), model.getName());
		}
		ObjectMapper objectMapper = new ObjectMapper();
		String modelJson = objectMapper.writeValueAsString(modelMap);
		request.setAttribute("modelJson", modelJson);
	}*/

	@RequestMapping("list.json")
	@ResponseBody
	public DataGrid<Resource> list(HttpServletRequest request, @RequestParam("rows") int rows, @RequestParam("page") int page,
			@RequestParam(value = "searchParams", required = false) String searchParams) throws JsonParseException, JsonMappingException, IOException {
		List<SearchParam> params = null;
		if (searchParams != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			params = objectMapper.readValue(searchParams, new TypeReference<List<SearchParam>>() {
			});
		} else {
			params = new ArrayList<SearchParam>();
		}
		Specification<Resource> spec = new SpecificationUtil<Resource>().buildSpecification(params);
		return resourceService.findByPage(page, rows, spec);
	}

	@RequestMapping("saveResource")
	@ResponseBody
	public boolean saveResource(ResourceBean resourceBean) {
		if(StringUtils.isBlank(resourceBean.getName()) || StringUtils.isBlank(resourceBean.getUrl())){
			return false;
		}
		try {
			resourceService.saveResource(resourceBean);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	@RequestMapping("deleteResource")
	@ResponseBody
	public boolean deleteResource(@RequestParam("rId") int id) {
		try {
			resourceService.deleteResouce(id);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

}
