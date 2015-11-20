<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 修改或添加角色-->
<div id="editRoleDialog" class="easyui-dialog" title="修改用户" data-options="iconCls:'icon-save',closed: true,modal:true,buttons:'#editRole-buttons'" 
	style="width:700px;height:600px;padding:10px;" >		
		<form id="editRoleForm" method="post">
			<input type="hidden" name="id" value="0"/>
			<div align="center">
				<label>角色名称:</label>
				<input id="roleName"  name="name" class="easyui-validatebox" data-options="required:true" style="width: 50%"/>
			</div>
		</form>
		<div id="editRole-buttons" align="center">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:saveRole()" style="width: 80px">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#editRoleDialog').dialog('close')" style="width: 80px">取消</a>
		</div>
</div>