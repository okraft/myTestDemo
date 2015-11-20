package com.flows.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.flows.bean.TreeNode;
import com.flows.core.bean.SearchParam;
import com.flows.core.bean.SpecificationUtil;
import com.flows.entity.Model;
import com.flows.service.IModelService;
import com.flows.service.IResourceService;

/**
 * 模块管理
 * @author Wanggg
 *
 */
@Controller
@RequestMapping("/admin/model")
public class ModelController {

	private static final Logger logger = LoggerFactory.getLogger(ModelController.class);
	
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IModelService modelService;

	@RequestMapping("index")
	public String index(HttpServletRequest request) throws JsonProcessingException {
		return "admin/model/modelList";
	}
	
	@RequestMapping("list.json")
	@ResponseBody
	public DataGrid<Model> list(HttpServletRequest request, @RequestParam(value = "searchParams", required = false) String searchParams) throws JsonParseException, JsonMappingException, IOException {
		List<SearchParam> params = null;
		if (searchParams != null) {
			ObjectMapper objectMapper = new ObjectMapper();
				params = objectMapper.readValue(searchParams, new TypeReference<List<SearchParam>>() {
			});
		} else {
			params = new ArrayList<SearchParam>();
		}
		Specification<Model> spec = new SpecificationUtil<Model>().buildSpecification(params);
		DataGrid<Model> dataGrid = new DataGrid<Model>();
		List<Model> modelList = modelService.findByPage(spec);
		if (modelList != null) {
			dataGrid.setRows(modelList);
			dataGrid.setTotal(modelList.size());
		}
		return dataGrid;
	}

	@RequestMapping("saveModel")
	@ResponseBody
	public boolean saveModel(Model model) {
		if(StringUtils.isBlank(model.getName())){
			return false;
		}
		try {
			modelService.saveModel(model);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	@RequestMapping("deleteModel")
	@ResponseBody
	public boolean deleteModule(@RequestParam("mId") int id) {
		try {
			modelService.deleteById(id);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
	
	@RequestMapping("getModelTree.json")
	@ResponseBody
	public List<TreeNode> getModelTree(){
		return	modelService.getModelTree();
	}

}
