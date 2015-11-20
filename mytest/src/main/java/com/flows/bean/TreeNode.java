package com.flows.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * EasyUI TreeNode
 * @author Wanggg
 *
 */
public class TreeNode {
	
	public static final String NODE_STATE_CLOSED = "closed";
	public static final String NODE_STATE_OPEN = "open";

	private int id;
	
	private int pId;
	
	private String text;
	
	private String state;
	
	private Map<String,Object> attributes = new HashMap<String,Object>();
	
	private List<TreeNode> children;
	
	@SuppressWarnings("unused")
	public void addNode(TreeNode treeNode){
		if(children == null){
			children = new ArrayList<TreeNode>();
		}
		children.add(treeNode);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		if(StringUtils.isNotBlank(state)){
			return state;
		}
		if(children != null && children.size() >0){
			state = "closed";
		}else{
			state = "open";
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, Object> getAttributes() {
		if(attributes == null){
			attributes = new HashMap<String, Object>();
		}
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
}
