<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理</title>
	<%@ include file="/jsp/include/easyuiInclude.jsp"%>
</head>
<body class="easyui-layout">
	欢迎： <shiro:principal/>
	<div region="center" style="padding:5px;" border="false">
		<table id="dataTable"></table>
		
		<div id="toolbar" class="datagrid-toolbar">
			<form id="queryForm" method="post">
				<div style="position: relative;margin-top:5px">
					<label>用户名:
						<input class="textbox specSearch" fieldName="code" dataType="string" operator="EQ" name="code" />
					</label>
					<label>用户姓名:
						<input class="textbox specSearch" fieldName="name" dataType="string" operator="EQ" name="name" />
					</label>
					<label>邮箱:
						<input class="textbox specSearch" fieldName="email" dataType="string" operator="EQ" name="email" />
					</label>
					<%-- <label>角色:
						<select data-options="panelHeight:'auto'" class="easyui-combobox specSearch" fieldName="role" dataType="string" operator="EQ" name="role" editable="false" style="width:120px" >
							<option value="">全部</option>	
							<c:forEach var="role" items="${roleList}">
								<option value="${role.id }">${role.name}</option>
							</c:forEach>
						</select>
					</label> --%>
					<label>手机号:
						<input class="textbox specSearch" fieldName="mobile" dataType="string" operator="EQ"  name="mobile" />
					</label>
				</div>
				<div style="position: relative;margin-top:5px">
					<label>
						日期: <input class="easyui-datebox specSearch" id="startDate" name="ctimeGTE"  fieldName="ctime" dataType="date" operator="GTE" format="yyyy-MM-dd"  style="width:100px" />
							至<input class="easyui-datebox specSearch" id="endDate" name="ctimeLTE" fieldName="ctime" dataType="date" operator="LTE" format="yyyy-MM-dd" style="width:100px" />
					</label>
					<label>
						<a href="#"  class="easyui-linkbutton" iconCls="icon-search" onclick="searchDataGrid()">查询</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="$('#queryForm').form('reset')">重置</a>
					</label>
				</div>
				<div style="text-align: right;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改角色</a>
					<shiro:hasPermission name="user:add">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="addUser()">增加用户</a>
					</shiro:hasPermission>
				</div>
			</form>
		</div>

		<!-- 修改角色 -->	
		<%@ include file="_editRole.jsp" %>
		<!-- 修改用户 -->	
		<%@ include file="_editUser.jsp" %>
		<!-- 查看权限 -->	
		<%@ include file="_showPermission.jsp" %>
		
	</div>
	<script src="${ctx }/static/admin/user/user.js"></script>
</body>
</html>