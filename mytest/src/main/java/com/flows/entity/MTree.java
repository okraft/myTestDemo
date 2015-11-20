package com.flows.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the test_tree database table.
 * 
 */
@Entity
@Table(name="sys_tree")
@NamedQuery(name="MTree.findAll", query="SELECT m FROM MTree m")
public class MTree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date ctime;

	private int cuser;

	private int isleaf;

	@Temporal(TemporalType.DATE)
	private Date mtime;

	private int muser;

	private int pid;

	@Column(name="sort_no")
	private int sortNo;

	private String url;
	
	private String text;
	
	@Transient
	private List<MTree> children;

	public MTree() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public int getCuser() {
		return this.cuser;
	}

	public void setCuser(int cuser) {
		this.cuser = cuser;
	}

	public int getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}

	public Date getMtime() {
		return this.mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public int getMuser() {
		return this.muser;
	}

	public void setMuser(int muser) {
		this.muser = muser;
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getSortNo() {
		return this.sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<MTree> getChildren() {
		return children;
	}

	public void setChildren(List<MTree> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}