<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>gg的test</title>
<%@ include file="../include/easyuiInclude.jsp"%>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;padding:10px;overflow: hidden; background: #0092DC;">
		<div style="float: left;">
			<h1 style="margin-left: 10px; margin-top: 10px;color: #fff">我的测试<span style="color: #3F4752">管理系统</span></h1>
		</div>
		<div style="float: right;">
			<div class="easyui-panel" style="padding:5px;float: right;">
				<a href="#" class="easyui-linkbutton" data-options="plain:true">选择皮肤</a>
				<select name="state" style="width:120px;" id="cb-theme"></select>
				<a href="#" onclick="openPassword()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改密码</a>
				<a href="#" onclick="logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'">退出</a>
			</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'功能菜单'" style="width:180px;padding:10px;">
		<ul id="mtree"></ul>
	</div>
	<div data-options="region:'center'">
		<div id="mainTabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎登录后台系统" style="padding:20px;overflow:hidden;" closable="true"> 
				<div style="margin-top:20px;">
					<h3>我的测试</h3>
					<li>用户权限功能</li> 
					<li>右键tab页，更多快捷操作</li> 
					<li>可选择个性皮肤</li> 
				</div>
			</div>
		</div>
	</div>
	<div id="tabsMenu" class="easyui-menu" style="width:150px;">
		<div id="newtab">新标签页打开</div>
		<div id="close">关闭标签页</div>
		<div id="closeother">关闭其他标签页</div>
		<div id="closeleft">关闭左侧标签页</div>
		<div id="closeright">关闭右侧标签页</div>
		<div id="closeall">关闭全部标签页</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
<script type="text/javascript" >
$("#mtree").tree({
    url:_ctx_+'/admin/main/getMenuTree',
    formatter:function(node){
		var url = node.attributes["url"];
		if(url==undefined){
			return node.text;
		}else{
			var str =  '<a class="e-link" href="#" onclick="openTab(\''+node.text+'\',\''+node.attributes["url"]+'\')">'+node.text+'</a>';
			return str;
		}
	}
});

function openTab(title,url){
	if ($('#mainTabs').tabs('exists',title)){
		var tab = $('#mainTabs').tabs('getTab', title);
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+_ctx_+'/'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#mainTabs').tabs('update', {
			tab: tab,
			options: {
				content:content
			}
		});
		$('#mainTabs').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+_ctx_+'/'+url+'" style="width:100%;height:100%;"></iframe>';
		var opts = {
			title : title,
			content:content,
			closable : true,
			border : false,
			fit : true
		};
		$('#mainTabs').tabs('add',opts);
	}
}

function logout(){
	window.location.href = _ctx_ + "/logout";
}
</script>
</body>
</html>