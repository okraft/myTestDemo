<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx }/static/bootstrap/css/bootstrap.min.css"> 
<title>用户登录</title>
</head>
<body>
<div class="container">
<form id="loginForm" method="post" class="form-horizontal" >
   <h2>欢迎登录</h2>
   <span style="color: red">${error}</span>
   <div class="form-group">
      <label class="col-sm-2 control-label">用户名</label>
      <div class="col-sm-4">
         <input type="text" id="userName" name="username" class="form-control"  placeholder="请输入用户名" value="${userName}"/>
      </div>
   </div>
   <div class="form-group">
      <label  class="col-sm-2 control-label">密码</label>
      <div class="col-sm-4">
         <input type="password" id="password" name="password" class="form-control"  placeholder="请输入密码" />
      </div>
   </div>
   <div class="form-group">
      <label  class="col-sm-2 control-label">验证码</label>
      <div class="col-sm-4">
      	<input type="text" name="captcha" />
      	<img id="imgObj" alt="验证码" src="${ctx}/captcha" />
      	<a href="#" onclick="changeImg()">换一张</a>
      </div>
   </div>
   <div class="form-group">
      <div class="col-sm-offset-2 col-sm-4">
         <div class="checkbox">
            <label>
               <input type="checkbox" id="rememberMe" > 请记住我
            </label>
         </div>
      </div>
   </div>
   <div class="form-group">
      <div class="col-sm-offset-2 col-sm-4">
         <button id="loginBtn" type="button" class="btn btn-primary btn-block" >登录</button>
      </div>
   </div>
</form>
</div>
<script src="${ctx }/static/jquery/jquery-1.10.2.min.js"></script>
<script language="JavaScript" src="${ctx }/static/js/cookie.js"></script>
<script language="JavaScript" src="${ctx }/static/js/md5.js"></script>
<script type="text/javascript">
$(function(){
	$("#loginBtn").bind("click",doLogin);
	document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				doLogin();
			}
		}
	});

	var REGEX_MD5 = /^[0-9a-f]+$/;
	function doLogin() {
		/* if(document.forms[0].username.value == ""){
			alert('请输入用户名');
			return false;
		}
		if(document.forms[0].password.value == ""){
			alert('请输入密码');
			return false;
		}
		document.forms[0].password.value = hex_md5(document.forms[0].password.value);
		setCookie("username", document.forms[0].username.value);
		if(document.forms[0].rememberMe && document.forms[0].rememberMe.checked){
			setCookie("password", document.forms[0].password.value);
		} */
		$('#loginForm').get(0).submit();
	}

	function changeImg() {
		var imgSrc = $("#imgObj");
		var url = "${ctx}/captcha?" + (new Date()).valueOf();
		imgSrc.attr("src", url);
	}
</script>
</body>
</html>