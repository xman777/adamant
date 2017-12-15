<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<style>
	#navUl li{font-weight: bold;}
	#navUl li a{color: #000;}
	.navbar-default #navUl > .active > a,
	.navbar-default #navUl > .active > a:hover,
	.navbar-default #navUl > .active > a:focus {
	  	color: #fff;
	  	background-color: #EF3A04;
	}
	.navbar-default #navUl > .open > a, 
	.navbar-default #navUl > .open > a:hover, 
	.navbar-default #navUl > .open > a:focus {
	    color: #EF3A04;
		background-color: #fff;
		border-bottom: 2px solid #EF3A04;
    	padding-bottom: 13px;
	}
	.navbar-right .dropdown-menu {
	    right: 0;
	    left: 0;
	}
	.dropdown-menu > li > a:hover,
	.dropdown-menu > li > a:focus {
		color: #fff !important;
		text-decoration: none;
		background-color: #EF3A04;
	}
	.dropdown-menu > .active > a,
	.dropdown-menu > .active > a:hover,
	.dropdown-menu > .active > a:focus {
		color: #fff;
		text-decoration: none;
		background-color: #337ab7;
		outline: 0;
	}
	.dropdown-menu > .disabled > a,
	.dropdown-menu > .disabled > a:hover,
	.dropdown-menu > .disabled > a:focus {
	  	color: #777;
	}
	
</style>


<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="background-color: #fff;box-shadow: 0 0 16px 0 rgba(0, 0, 0, 0.175);padding: 0 15px;">
	<div class="navbar-header">
		<a href="#">
			<img src="${ baseUrl }/webpage/images/logo.png" alt="logo" width="80px" height="50px">
		</a>
	</div>	
	<div>
		<ul id="navUl" class="nav navbar-nav navbar-right" style="margin-right: 20%;">
			<li class="active"><a href="#">iOS</a>
			</li>
			<li>
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"> SVN <b class="caret"></b> </a>
				<ul class="dropdown-menu">
					<li><a href="#">test1</a>
					</li>
					<li><a href="#">test2</a>
					</li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"> Java <b class="caret"></b> </a>
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
			<li><a href="#">HTML</a>
			</li>
		</ul>
		
	</div>
</nav>