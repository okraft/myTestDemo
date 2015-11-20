<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 添加模块-->
<div id="addModelDialog" class="easyui-dialog" title="添加模块" data-options="iconCls:'icon-save',closed: true,modal:true,buttons:'#addModel-buttons'" 
	style="width:400px;height:300px;padding:10px;" >
	<form id="addModelForm" method="post">
		<input type="hidden" name="id" value="0"/>
		<input type="hidden" name="pid" />	
		<input  type="hidden" name="isLeaf" id="isLeaf" value="0"/>
		<div>
			<label>模块名称:</label>
			<input name="name" class="easyui-validatebox" required="true" style="width: 100%"/>
		</div>	
	</form>
	<div id="addModel-buttons" align="center">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:saveModel()" style="width: 80px">保存</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#addModelDialog').dialog('close')" style="width: 80px">取消</a>
	</div>		
</div>
