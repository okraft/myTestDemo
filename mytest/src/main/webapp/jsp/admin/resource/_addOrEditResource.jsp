<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 修改或添加资源-->
<div id="editResourceDialog" class="easyui-dialog" title="修改资源" data-options="iconCls:'icon-save',closed: true,modal:true,buttons:'#editResource-buttons'" 
	style="width:500px;height:400px;padding:10px;" >		
		
		<form id="editResourceForm" method="post">
			<input type="hidden" name="id" value="0"/>
			<input type="hidden" name="parentId" value="0"/>
			<div align="center">
				<label>资源名称:</label>
				<input name="name" class="easyui-validatebox" data-options="required:true" style="width: 50%"/>
			</div>
			<div align="center">
				<label>资源Url:</label>
				<input  name="url" class="easyui-validatebox" data-options="required:true" style="width: 50%"/>
			</div>
			<div align="center">
				<label>资源类型:</label>
				<select class="easyui-combobox"   data-options="panelHeight:'auto'" style="width:200px">
					<option value="menu">菜单</option>
					<option value="button">按钮</option>
				</select>
			</div>
		</form>
		<div id="editResource-buttons" align="center">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:saveResource()" style="width: 80px">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#editResourceDialog').dialog('close')" style="width: 80px">取消</a>
		</div>
</div>