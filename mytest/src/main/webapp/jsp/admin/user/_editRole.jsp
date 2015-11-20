<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 修改角色-->
<div id="editRole" class="easyui-dialog" title="角色信息" data-options="iconCls:'icon-save',closed: true,modal:true,buttons:'#editRole-buttons'" 
	style="width:700px;height:600px;padding:10px;" >
	<div class="zTreeDemoBackground left" style="max-height:500px;overflow:auto;">
		<div id="roleDiv" class="roles" >
			<c:forEach var="role" items="${roleList}">
				<span style='float:left;width:220px;margin-top:15px;'><input class="roleItem" type="checkbox" id="role_${role.id }" value="${role.id }">${role.name}</span>
			</c:forEach>
		</div>
	</div>
	<div id="editRole-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:updateUserRole()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#editRole').dialog('close')">关闭</a>
	</div>
</div>