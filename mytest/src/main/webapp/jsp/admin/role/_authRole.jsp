<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 角色授权-->
<div id="authRoleDialog" class="easyui-dialog" title="角色授权" data-options="iconCls:'icon-save',closed: true,modal:true,buttons:'#authRole-buttons'" 
	style="width:350px;height:450px;padding:10px;" >
	<div class="zTreeDemoBackground left">
		<ul id="authTree" class="ztree"></ul>
	</div>
	
	<div id="authRole-buttons" align="center">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:saveRoleResource()" style="width: 80px">保存</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#authRoleDialog').dialog('close')" style="width: 80px">取消</a>
	</div>		
</div>
