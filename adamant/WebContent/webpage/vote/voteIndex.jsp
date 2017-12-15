<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>xtest</title>
<%@ include file="/webpage/common.jsp" %>

<style type="text/css">
	body{background: url('${baseUrl}/webpage/images/bg7.jpg') no-repeat;background-size: 100% 100%;}
	#left_menu .navbar-nav > li {float: left;width: 150px;}
	#left_menu .navbar-nav > li > .dropdown-menu {margin-top: -50px;border-top-left-radius: 0;border-top-right-radius: 0;margin-left: 150px;}
	
	
</style>

</head>

<body>
	<%@ include file="/webpage/head.jsp" %>
	
	<div class="container" style="background-color: #fff;">
		<div class="row">

		</div>
	</div>
	
<script type="text/javascript">
	
	function ajaxTest(){
		$.ajax({
			type: "POST",
			url: baseUrl+"/userController/ajaxTest.do",
			data: {},
			dataType: "json",
			success:function(result){
				var msg = result.msg;
				layer.alert(msg);
			}
		});
	}
	

	
	function ajaxSubmitPic(){
// 		$("#uploadForm").ajaxSubmit({
// 			type: 'post',
//             url: baseUrl+'/fileController/fileUpload.do', // 需要提交的 url
//             data: {
//                 'testParam': $("#testParam").val()
//             },
//             beforeSubmit:function(){
//             	//提交前的操作，如：弹出loading
            	
//             },
//             success: function(data) {
//             	var xdata = jQuery.parseJSON(data);
//             	var msg = xdata.msg;
//             	var success = xdata.success;
//             	if(success==1){
//             		console.log("success");
//             		$("#uploadForm").resetForm();//将表单恢复到初始状态。
//             	}
//             	layer.msg(msg);
            	
//             	return false; // 阻止表单自动提交事件
//             },
//             timeout: 5000    // 限制请求的时间，当请求大于5秒后，跳出请求
// 		});
	}
	
	$(function(){
		console.log("baseUrl:"+baseUrl);
		
		
		
	});
	
	

</script>
</body>
</html>
