$(function(){
	$('#dataTable').datagrid({
		/* title:'查询用户', //标题 */
		method:'post',
		//iconCls:'icon-edit', //图标
		fit:true,
		nowrap:false,
		singleSelect:true, //单选
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true,  //奇偶行颜色不同
		collapsible:true,//可折叠
		url:_ctx_+'/admin/user/list.json',    //数据来源
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
				{field:'code',title:'用户名',width:$(this).width() * 0.20,sortable:false},
				{field:'name',title:'真实姓名',width:$(this).width() * 0.20,sortable:false},
				{field:'email',title:'邮箱',width:$(this).width() * 0.20,sortable:false},
				{field:'mobile',title:'手机',width:$(this).width() * 0.20,sortable:false},
				{field:'role',title:'角色',width:$(this).width() * 0.20,sortable:false},
				{field:'ctime',title:'创建时间',width:$(this).width() * 0.20,sortable:false},
				{field:'id',title:'操作',width:$(this).width() * 0.20,sortable:false,formatter:optFormatter}
		]],
	    onLoadSuccess:function(data){
			$('#dataTable').datagrid('clearSelections'); 	//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
});

function optFormatter(value,row,index){
	var html = "&nbsp;<a href='javascript:showPermission("+row.id+")'>查看权限</a>" +
			"&nbsp;<a href='javascript:editUser("+row.id+","+index+")'>修改</a>" +
			"&nbsp;<a href='javascript:delUser("+row.id+")'>删除</a>";
	return html;
}

function showPermission(userId){
	var conf = {"userId":userId};
	var setting = {
			check: {
				enable: true,
				chkDisabledInherit: true
			},
			async:{
				enable:true,
				url:_ctx_+'/admin/user/showPermission',
				autoParam:["id"],
				otherParam:conf
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	$.fn.zTree.init($("#userPermissonTree"), setting);
	$('#userPermsDialog').dialog('open');

}

function editUser(rowId,index){
	var row = $('#dataTable').datagrid('getData').rows[index];
	if(row){
		$('#editUserForm').form('load',row);
		$("#editUserDialog").dialog('open');
	}else{
		$.messager.alert('消息','请选择一条记录!','error');
	}
}

function addUser(){
	$('#editUserForm').form('clear');
	$("#editUserDialog").dialog('open');
}

function saveUser(){
	var url = _ctx_+"/admin/user/saveUser";
	$('#editUserForm').form('submit',{
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
				$('#editUserForm').form('clear');
				$("#editUserDialog").dialog('close');
			}else{
				$.messager.alert('消息','操作失败，请联系管理员!','error');
			}
		}
	});
}

function delUser(userid){
	$.messager.confirm('确认','确认要删除所选用户?',function(r){
	    if (r){
	    	var conf = {"userId":userid};
	    	var url=_ctx_ + "/admin/user/deleteUser";
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

function editRole() {
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length == 0) {
		alert('请选择要修改的用户');
		return false;
	}else {
		$(".roleItem").attr('checked', false);
		//获取所选用户的角色
		var userRoleIds = rows[0].roleIds;
		if(userRoleIds != null && userRoleIds != ""){
			userRoleIds = userRoleIds.split(",");
			$.each(userRoleIds,function(){
				$('#role_'+this).prop("checked", true);
			});
		}
		$("#editRole").dialog('open');
	}
}

function updateUserRole(){
	var $roleItems = $(".roleItem:checked");
	var roleIds = "";
	if($roleItems != null && $roleItems.size() > 0){
		roleIds = new Array();
		$.each($roleItems,function(i){
			roleIds.push($(this).val());
		});
	}
	if(roleIds !=""){
		roleIds = roleIds.join();
	}
	var rows = $('#dataTable').datagrid('getSelections');
	var userId = rows[0].id;
	$.post(_ctx_ +'/admin/user/saveUserRole',{'roleIds':roleIds,'userId':userId},function(result){
		if(result) {
			$.messager.show({
	            title:'消息',
	            msg:'操作成功',
	            timeout:500,
	            showType:'slide'
	        });   
			$("#editRole").dialog('close');
			searchDataGrid();
		}
	},'json');
	
}


