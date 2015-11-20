//列表查询
function searchDataGrid(dgId){
	dgId = dgId ||'dataTable';
	var dataJson = getQueryParams();
	$('#'+dgId).datagrid({
		queryParams: {
			searchParams: dataJson
		}
	});
}
//根据datagridid和url进行参数查询
function searchDGbyIdUrl(dgId,url){
	var dataJson = getQueryParams();
	$('#'+dgId).datagrid({
		url:url,
		queryParams: {
			searchParams: dataJson
		}
	});
}
//获取查询参数
function getQueryParams(){
	var datas = new Array();
	$(".specSearch").each(function(){
		var obj = $(this);
		var inputVal = obj.val();
		if(obj.attr("comboname")!=undefined){
			inputVal = $(":input[name='"+obj.attr("comboname")+"']").val();
		}
		if(inputVal!=""){
			datas.push({
				fieldName:obj.attr('fieldName'),
				value:inputVal,
				dataType:obj.attr('dataType'),
				operator:obj.attr('operator'),
				format:obj.attr('format')
			});
		}
	});
	var dataJson = JSON.stringify(datas);
	return dataJson;
}

$(function(){
	$("#toolbar input").keydown(function(event){
		if(event.keyCode == 13){
			searchDataGrid();
		}
	});
});