<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>404页面</title>
<%@ include file="/webpage/common.jsp" %>
<style type="text/css">
body{font-family: YouYuan;}
</style>
</head>

<body style="background-color: #fff;">
	<div style="margin: auto;text-align: center;">
		<h3>404错误！！！</h3>
		<h2>哎呀！您访问的页面不存在。。。</h2>
		<img alt="xxxx" src="${ baseUrl }/webpage/images/bad-luck.gif">
			
		<p>您还可以以下操作：</p>
		<button class="btn btn-primary" type="button">返回首页</button>
		<button class="btn btn-default" type="button" onclick="window.history.back();">后退</button>
	</div>
	
</body>
</html>
