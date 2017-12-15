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

			<!-- 左侧菜单begin -->
			<div id="left_menu" class="col-lg-2" style="min-height: 300px;">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">父菜单1</a>
					</li>
					<li><a href="#">父菜单2</a>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							父菜单3 <b class="caret" style="float: right;margin-top: 8px"></b> 
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
									父菜单4 <b class="caret" style="float: right;margin-top: 8px"></b>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">jmeter</a>
									</li>
									<li><a href="#">EJB</a>
									</li>
									<li><a href="#">Jasper Report</a>
									</li>
									<li class="divider"></li>
									<li><a href="#">分离的链接</a>
									</li>
									<li class="divider"></li>
									<li><a href="#">另一个分离的链接</a>
									</li>
								</ul>
							</li>
							<li><a href="#">EJB</a>
							</li>
							<li><a href="#">Jasper Report</a>
							</li>
						</ul>
					</li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							父菜单4 <b class="caret" style="float: right;margin-top: 8px"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="#">jmeter</a>
							</li>
							<li><a href="#">EJB</a>
							</li>
							<li><a href="#">Jasper Report</a>
							</li>
							<li class="divider"></li>
							<li><a href="#">分离的链接</a>
							</li>
							<li class="divider"></li>
							<li><a href="#">另一个分离的链接</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- 左侧菜单end -->
			
			<!-- 内容begin -->
			<div class="col-lg-10">
				<h3>id: ${user.id }</h3>
				<h3>userName: ${user.name }</h3>
				<h3>password: ${user.mobile }</h3>
			
				<div class="table-responsive" style="margin: 15px;">
					<table class="table table-bordered table-hover table-responsive">
						<thead>
							<tr>
								<th>名称</th>
								<th>城市</th>
								<th>密码</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Tanmay</td>
								<td>Bangalore</td>
								<td>560001</td>
							</tr>
							<tr>
								<td>Sachin</td>
								<td>Mumbai</td>
								<td>400003</td>
							</tr>
							<tr>
								<td>Uma</td>
								<td>Pune</td>
								<td>411027</td>
							</tr>
						</tbody>
					</table>
					
					<h3>xxxxxxx${xInitParam}</h3>
					
					<!-- method必须为post 及enctype属性-->  
					<form action="${ baseUrl }/fileController/fileUpload.do?" id="uploadForm" method="post" enctype="multipart/form-data">  
						<input type="file" name="file">  
						<input type="submit" value="上传" id="picUpload">
						<input type="text" value="ssss" name="testParam" id="testParam">
					</form>
					
					<a href="${ baseUrl }/fileController/downloadFile.do?param1=param1">下载</a>
					
					<p></p>
					<img alt="xxxx" src="${ baseUrl }/webpage/images/bad-luck.gif" onclick="ajaxTest();">
				</div>
			</div>
			<!-- 内容end -->
			
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
