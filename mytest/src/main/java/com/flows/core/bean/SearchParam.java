package com.flows.core.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询公用参数
 * 
 * @author Administrator
 *
 */
public class SearchParam {
	private String fieldName;
	private String value;
	private String dataType;
	private String operator;
	private String format;
	private Object objectValue;

	public SearchParam() {

	}

	public SearchParam(String fieldName, String value, String operator) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
		this.dataType = "string";
	}

	public String getDataType() {
		return dataType;
	}

	public String getFormat() {
		return format;
	}

	public String getOperator() {
		return operator;
	}

	public String getValue() {
		return value;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object getObjectValue() {
		if (objectValue == null) {
			if (value != null) {
				value = value.trim();
			}
			parseValue();
		}
		return objectValue;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void parseValue() {
		if ("string".equals(dataType)) {
			objectValue = this.value;
			return;
		}
		if ("int".equals(dataType)) {
			objectValue = Integer.parseInt(value);
			return;
		}
		if ("date".equals(dataType)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				objectValue = sdf.parse(value);
				return;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if ("list".equals(dataType)) {
			value = value.replace("[", "").replace("]", "");
			if (value.trim().equals("")) {
				objectValue = "";
				return;
			}
			String[] arr = value.split(",");
			List list = new ArrayList(arr.length);
			for (String s : arr) {
				list.add(s.trim());
			}
			objectValue = list;
			return;
		}
		objectValue = value;
	}

	public void setObjectValue(Object objectValue) {
		this.objectValue = objectValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
