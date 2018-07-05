<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'menu.jsp' starting page</title>

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
	<div id="menu" class="menu" style="width: 260px;">

		<div class="menu-title">
			<h1>
				<i>菜单</i>
			</h1>
		</div>
		<ul>
			<li class="menu-item"><a href="javascript:;"><span>分类管理</span><i
					class="my-icon menu-more"></i></a>
				<ul>
					<li><a href="/blog/type_info/list.action"><span>文章分类</span></a></li>
				</ul>
			</li>
			<li class="menu-item"><a href="javascript:;"><span>文章管理</span><i
					class="my-icon menu-more"></i></a>
				<ul>
					<li><a href="/blog/article_info/articlelist.action"><span>文章列表</span></a></li>
					<li><a href="/blog/article_info/recyclelist.action"><span>回收站</span></a></li>
				</ul>
			</li>
		</ul>
	</div>
	<script>
		javaex.menu({
			id : "menu"
		});
	</script>
</body>
</html>
