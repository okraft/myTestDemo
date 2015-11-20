<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理</title>
	<%@ include file="/jsp/include/easyuiInclude.jsp"%>
</head>
<body class="easyui-layout">
	<div region="center" style="padding:5px;" border="false">
		<table id="dataTable"></table>
		
		<div id="toolbar" class="datagrid-toolbar">
			<form id="queryForm" method="post">
				<div style="position: relative;margin-top:5px">
					<label>资源名称:
						<input class="textbox specSearch" fieldName="name" dataType="string" operator="EQ"  name="name" />
					</label>
					<label>资源Url:
						<input class="textbox specSearch" fieldName="url" dataType="string" operator="EQ"  name="url" />
					</label>
					<label>
						<a href="#"  class="easyui-linkbutton" iconCls="icon-search" onclick="cusSearchDataGrid()">查询</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="$('#queryForm').form('reset')">重置</a>
					</label>
				</div>
				<div style="text-align: right;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="addResource()">增加资源</a>
				</div>
			</form>
		</div>

		<!-- 添加或修改资源 -->	
		<%@ include file="_addOrEditResource.jsp" %>
	</div>
	<script type="text/javascript">
		var modelJson = ${modelJson};
	</script>
	<script src="${ctx }/static/admin/resource/resource.js"></script>
</body>
</html>