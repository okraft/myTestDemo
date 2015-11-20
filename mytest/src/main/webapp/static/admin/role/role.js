$(function(){
	$('#dataTable').datagrid({
		/* title:'查询用户', //标题 */
		method:'post',
		//iconCls:'icon-edit', //图标
		fit:true,
		nowrap:false,
		singleSelect:true, //多选
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true,  //奇偶行颜色不同
		collapsible:true,//可折叠
		url:_ctx_+'/admin/role/list.json',    //数据来源
		pagination:true,
		pageSize:30,
		pageList:[20,30,50,100],
		rownumbers:true,
		remoteSort: false, //服务器端排序
		idField:'id', //主键字段
		queryParams:{}, //查询条件
		toolbar:'#toolbar',
		rownumbers:true, //显示行号
	    columns:[[
				{field:'name',title:'角色名称',width:$(this).width() * 0.20,sortable:false},
				{field:'ctime',title:'创建时间',width:$(this).width() * 0.20,sortable:false},
				{field:'id',title:'操作',width:$(this).width() * 0.20,sortable:false,formatter:optFormatter}
		]],
	    onLoadSuccess:function(data){
			$('#dataTable').datagrid('clearSelections'); 	//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
});

function optFormatter(value,row,index){
	var html = "&nbsp;<a href='javascript:showPermission("+row.id+")'>角色授权</a>" +
			"&nbsp;<a href='javascript:editRole("+row.id+","+index+")'>修改</a>" +
			"&nbsp;<a href='javascript:delRole("+row.id+")'>删除</a>";
	return html;
}

function addRole(){
	$('#editRoleForm').form('clear');
	$("#editRoleDialog").dialog('open');
}

function saveRole(){
	var url = _ctx_+"/admin/role/saveRole";
	$('#editRoleForm').form('submit',{
		url:url,
		success:function(data){
			if(data){
				$.messager.show({
	                title:'消息',
	                msg:'保存成功',
	                timeout:500,
	                showType:'slide'
	            });
				searchDataGrid();
				$('#editRoleForm').form('clear');
				$("#editRoleDialog").dialog('close');
			}else{
				$.messager.alert('消息','操作失败，请联系管理员!','error');
			}
		}
	});
}

function delRole(roleId){
	$.messager.confirm('确认','确认要删除所选用户?',function(r){
	    if (r){
	    	var conf = {"roleId":roleId};
	    	var url=_ctx_ + "/admin/role/deleteRole";
	    	$.post(url, conf, function(result) {
	    		if(result){
	    			$.messager.show({
	                    title:'消息',
	                    msg:'操作成功',
	                    timeout:500,
	                    showType:'slide'
	                });   
	    			searchDataGrid();
	    		}else{
	    			alert("修改失败！请稍后再试！");
	    		}
	       	});
	    }
	});
}

function editRole(rowId,index) {
	var row = $('#dataTable').datagrid('getData').rows[index];
	if(row){
		$('#editRoleForm').form('load',row);
		$("#editRoleDialog").dialog('open');
	}else{
		$.messager.alert('消息','请选择一条记录!','error');
	}
}

function showPermission(roleId){
	$('#authRoleDialog').dialog('open');
	var conf = {"roleId":roleId};
	var setting = {
			check: {
				enable: true
			},
			async:{
				enable:true,
				url:_ctx_+'/admin/role/showRolePermission',
				autoParam:["id"],
				otherParam:conf
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	$.fn.zTree.init($("#authTree"), setting);
}

function saveRoleResource(){
	var row = $('#dataTable').datagrid('getSelections')[0];
	var treeObj = $.fn.zTree.getZTreeObj("authTree");
	var nodes = treeObj.getCheckedNodes(true);
	var resIds = new Array();
	$.each(nodes,function(i,node){
		if(node.nodeType == 1){
			resIds.push(node.id);
		}
	});
	resIds = resIds.join();
	var conf = {"roleId":row.id,"resIds":resIds};
	var url = _ctx_+'/admin/role/saveRoleResource';
	$.post(url, conf,function(data){
		if(data){
			$.messager.show({
                title:'消息',
                msg:'操作成功',
                timeout:500,
                showType:'slide'
            });   
			$('#authRoleDialog').dialog('close');
		}else{
			alert("修改失败！请稍后再试！");
		}
	});
}
