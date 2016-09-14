package com.flows.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataGrid<T> {
	private long total;
	private Collection<T> rows;
	@SuppressWarnings("rawtypes")
	private Collection footer;
	private int i;

	public long getTotal() {
		return total;
	}

	public void setTotal(long l) {
		this.total = l;
	}

	public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}

	@SuppressWarnings("rawtypes")
	public Collection getFooter() {
		return footer;
	}

	@SuppressWarnings("rawtypes")
	public void setFooter(Collection footer) {
		this.footer = footer;
	}

	public void setFooter(Map<String, Object> countMap) {
		List<Object> countList = new ArrayList<Object>();
		countList.add(countMap);
		this.footer = countList;
	}

}
