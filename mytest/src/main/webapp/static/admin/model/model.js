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
		url:_ctx_+'/admin/model/list.json',    //数据来源
		pagination:false,
		rownumbers:true,
		animate:true,
		remoteSort: false, //服务器端排序
		idField:'id', //主键字段
		treeField:"name",
		queryParams:{}, //查询条件
		toolbar:'#toolbar',
		rownumbers:true, //显示行号
	    columns:[[
				{field:'name',title:'模块名称',width:$(this).width() * 0.20,sortable:false},
				{field:'ctime',title:'创建时间',width:$(this).width() * 0.20,sortable:false},
				{field:'id',title:'操作',width:$(this).width() * 0.20,sortable:false,formatter:optFormatter}
		]],
	    onLoadSuccess:function(data){
			$('#dataTable').treegrid('clearSelections'); 	//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
});

function optFormatter(value,row,index){
	var html = "<a href='javascript:editModel("+row.id+","+index+")'>修改</a>" +
			"&nbsp;<a href='javascript:delModel("+row.id+")'>删除</a>";
	if(row.pid == 0){
		html +="&nbsp;<a href='javascript:addModel("+row.id+")'>添加子模块</a>";
	}
	return html;
}

function addModel(pid){
	$('#addModelForm').form('clear');
	$("#addModelDialog").dialog('open');
	var $id = $('#addModelForm').find('input[name="id"]').val(0);
	var $pid = $('#addModelForm').find('input[name="pid"]');
	if(pid !=null && pid >0){
		$pid.val(pid);
		$('#addModelForm').find('input[name="isLeaf"]').val(1);
	}else{
		$pid.val(0);
		$('#addModelForm').find('input[name="isLeaf"]').val(0);
	}
}

function editModel(rowId,index) {
	var row = $('#dataTable').treegrid('getSelected');
	if(row){
		$('#addModelForm').form('load',row);
		$("#addModelDialog").dialog({
			title: '修改模块名称',
		}).dialog('open');
	}else{
		$.messager.alert('消息','请选择一条记录!','error');
	}
}

function saveModel(){
	var url = _ctx_+"/admin/model/saveModel";
	$('#addModelForm').form('submit',{
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
				$('#addModelForm').form('clear');
				$("#addModelDialog").dialog('close');
			}else{
				$.messager.alert('消息','操作失败，请联系管理员!','error');
			}
		}
	});
}

function delModel(mId){
	$.messager.confirm('确认','确认要删除所选模块记录?',function(r){
	    if (r){
	    	var conf = {"mId":mId};
	    	var url=_ctx_ + "/admin/model/deleteModel";
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

//列表查询
function searchDataGrid(){
	var dataJson = getQueryParams();
	$('#dataTable').treegrid({
		queryParams: {
			searchParams: dataJson
		}
	});
}

