<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="baseUrl" value="<%=basePath %>" scope="request"/>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="网站关键字：xman">
<meta http-equiv="description" content="网站描述信息：xman's test">
<link type="text/css" rel="stylesheet" href="${ baseUrl }/webpage/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="${ baseUrl }/webpage/css/layer.css"/>
<script type="text/javascript" src="${ baseUrl }/webpage/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${ baseUrl }/webpage/js/bootstrap.js"></script>
<script type="text/javascript" src="${ baseUrl }/webpage/js/layer.js"></script>
<script type="text/javascript" src="${ baseUrl }/webpage/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ baseUrl }/webpage/js/jquery.form.js"></script>

<style>
	body{font-family: MicrosoftYaHei;margin: 60px 15px 30px 15px;}
	table{border-collapse: collapse;border-spacing: 0;text-align: center;}
	table thead tr th{border-bottom-width: 1px !important;text-align: center;}
	
</style>
<script type="text/javascript">
	var baseUrl = '${baseUrl}';
	console.log("baseUrl:"+baseUrl);
</script>