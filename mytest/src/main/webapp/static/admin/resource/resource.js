$(function(){
	$('#dataTable').treegrid({
		/* title:'查询用户', //标题 */
		method:'post',
		//iconCls:'icon-edit', //图标
		fit:true,
		nowrap:false,
		singleSelect:true, //多选
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true,  //奇偶行颜色不同
		collapsible:true,//可折叠
		url:_ctx_+'/admin/resource/list.json',    //数据来源
		pagination:true,
		pageSize:30,
		pageList:[20,30,50,100],
		rownumbers:true,
		remoteSort: false, //服务器端排序
		idField:'id', //主键字段
	    treeField:'name',
		queryParams:{}, //查询条件
		toolbar:'#toolbar',
		rownumbers:true, //显示行号
	    columns:[[
				{field:'name',title:'资源名称',width:$(this).width() * 0.20,sortable:false},
				{field:'url',title:'资源url',width:$(this).width() * 0.20,sortable:false},
				{field:'type',title:'资源类型',width:$(this).width() * 0.20,sortable:false,formatter:typeFormatter},
				{field:'ctime',title:'创建时间',width:$(this).width() * 0.20,sortable:false},
				{field:'id',title:'操作',width:$(this).width() * 0.20,sortable:false,formatter:optFormatter}
		]],
	    onLoadSuccess:function(data){
			$('#dataTable').datagrid('clearSelections'); 	//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
	
});

function optFormatter(value,row,index){
	var html = "";
	if(row.type == 'menu'){
		html += "<a href='javascript:addSubResource("+row.id+","+index+")'>添加子资源</a>";
	}
	html += "&nbsp;<a href='javascript:delResource("+row.id+")'>修改</a>";
			"&nbsp;<a href='javascript:delResource("+row.id+")'>删除</a>";
	return html;
}

function typeFormatter(value,row,index){
	if(value == 'menu'){
		return "菜单";
	}
	return "按钮";
}

function addResource(){
	$('#editResourceForm').form('clear');
	$("#editResourceDialog").dialog('open');
}

function editResource(rowId,index) {
	var row = $('#dataTable').treegrid('getData').rows[index];
	if(row){
		$('#editResourceForm').form('load',row);
		$("#editResourceDialog").dialog('open');
	}else{
		$.messager.alert('消息','请选择一条记录!','error');
	}
}

function saveResource(){
	var url = _ctx_+"/admin/resource/saveResource";
	$('#editResourceForm').form('submit',{
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
				$('#editResourceForm').form('clear');
				$("#editResourceDialog").dialog('close');
			}else{
				$.messager.alert('消息','操作失败，请联系管理员!','error');
			}
		}
	});
}

function delResource(rId){
	$.messager.confirm('确认','确认要删除所选资源记录?',function(r){
	    if (r){
	    	var conf = {"rId":rId};
	    	var url=_ctx_ + "/admin/resource/deleteResource";
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

