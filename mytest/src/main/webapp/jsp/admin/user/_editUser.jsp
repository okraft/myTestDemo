<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 修改用户-->
<div id="editUserDialog" class="easyui-dialog" title="修改用户" data-options="iconCls:'icon-save',closed: true,modal:true,buttons:'#editUser-buttons'" 
	style="width:700px;height:600px;padding:10px;" >		
		<form id="editUserForm" method="post">
			<input type="hidden" name="id" value="0"/>
			<div align="center">
				<label>用户名:</label>
				<input id="code"  name="code" class="easyui-validatebox" data-options="required:true"  validType="checkCode[]" style="width: 50%"/>
			</div>
			<div align="center">
				<label>密码:</label> 
				<input id="register_password" name="password" type="password" class="easyui-validatebox" data-options="required:true" validType="length[6,32]" style="width: 50%"/>
			</div>
			<div align="center">
				<label>密码确认:</label> 
				<input name="confrimPassword" type="password" class="easyui-validatebox" required="required" validType="equals['#register_password']" style="width: 50%"/>
			</div>
			<div align="center">
				<label>邮箱:</label> 
				<input id="email" name="email" class="easyui-validatebox" required="required" validType="email" style="width: 50%"/>
			</div>
			<div align="center">
				<label>手机:</label> 
				<input id="mobile" name="mobile" class="easyui-validatebox" required="required" validType="mobile" style="width: 50%"/>
			</div>
		</form>
		<div id="editUser-buttons"  align="center">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:saveUser()" style="width: 80px">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#editUserDialog').dialog('close')" style="width: 80px">取消</a>
		</div>
</div>