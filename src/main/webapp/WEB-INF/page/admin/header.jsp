<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'header.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!--字体图标样式-->
<link href="/blog/static/javaex/pc/css/icomoon.css" rel="stylesheet" />
<!--动画样式-->
<link href="/blog/static/javaex/pc/css/animate.css" rel="stylesheet" />
<!--核心样式-->
<link href="/blog/static/javaex/pc/css/style.css" rel="stylesheet" />
<!--jquery，当前版本不可更改jquery版本-->
<script src="/blog/static/javaex/pc/lib/jquery-1.7.2.min.js"></script>
<!--全局动态修改-->
<script src="/blog/static/javaex/pc/js/common.js"></script>
<!--核心组件-->
<script src="/blog/static/javaex/pc/js/javaex.min.js"></script>
<!--表单验证-->
<script src="/blog/static/javaex/pc/js/javaex-formVerify.js"></script>
  </head>
  
  <body>

	 <!--左侧logo-->
			<div>
				<a href="#"> <img class="logo-img"
					src="/blog/static/javaex/pc/images/logo.png">
				</a>
			</div>
			<!--右侧功能-->
			<ul class="pull-right header-right">
				<li><a class="pull-left user-photo" href="#"><img
						src="/blog/static/javaex/pc/images/user.jpg" alt=""></a>
					<p class="pull-left margin-left-10">
						欢迎您，<span>管理员</span>
					</p> <label class="margin-left-10 margin-right-10">|</label>
					<a href="/blog/admin/loginout.action">退出</a></li>
				<li><a href="/blog/portal/index.action">首页</a></li>
			</ul>
		
  </body>
</html>
