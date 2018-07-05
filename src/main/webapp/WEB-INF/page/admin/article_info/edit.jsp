<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'edit.jsp' starting page</title>

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
	<div class="wrap">
		<!-- 头部 -->
		<div id="header">
			<c:import url="../header.jsp"></c:import>
		</div>
		<!-- 左侧菜单和主体内容 -->
		<div class="grid-1-7" style="flex: 1;margin:0;">
			<!-- 左侧菜单 -->
			<div class="menu">
				<c:import url="../menu.jsp"></c:import>
			</div>
			<!-- 面包屑导航和主体内容 -->
			<div class="content">
				<!--面包屑导航-->
				<div class="content-header">
					<div class="breadcrumb">
						<span>文章管理</span> <span class="divider">/</span> <span
							class="active">文章编辑</span>
					</div>
				</div>
				<!--主体内容-->
				<div class="list-content">
					<!--块元素-->
					<div class="block">
						<!--修饰块元素名称-->
						<div class="banner">
							<p class="tab fixed">
								文章编辑<span class="hint">article edit</span>
							</p>
						</div>
						<!--正文-->
						<div class="main">
							<!--表单-->
							<form id="form">
								<!--输入框-->
								<input type="hidden" name="id" value="${articleInfo.id}">
								<div class="unit">
									<div class="left">
										<p class="subtitle">标题</p>
									</div>
									<div class="right">
										<input type="text" class="text" name="title" data-type="必填"
											placeholder="请输入文章标题" value="${articleInfo.title} " />
									</div>
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>

								<!--分割线-->
								<p class="divider"></p>

								<!--下拉选择框   其中通过foeach循环遍历输出文章分类-->
								<div class="unit">
									<div class="left">
										<p class="subtitle">所属类型</p>
									</div>
									<div class="right">
										<select id="type_id" name="typeId">
											<c:forEach items="${typeList}" var="typeinfo"
												varStatus="status">
												<option value="${typeinfo.id}"
													<c:if test="${articleInfo.typeId==typeinfo.id}">selected </c:if>>
													${typeinfo.name}</option>
											</c:forEach>

										</select>
									</div>
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
								<!--分割线-->
								<p class="divider"></p>
								<!--文章封面上传-->
								<div class="unit">
									<div class="left">
										<p class="subtitle">文章封面</p>
									</div>
									<div class="right">

										<!-- 图片承载容器 -->
										<!-- 已经在tomcat中配置过了通过upload来访问图片 -->
										<label id="container" for="upload"
											style="display: inline-block; width:132px;height:74px;">

											<c:choose>
												<c:when test="${empty articleInfo.cover}">
													<img src="/blog/static/javaex/pc/images/default.png"
														width="100%" height="100%" />
												</c:when>
												<c:otherwise>
													<img src="/upload/${articleInfo.cover}" width="100%"
														height="100%" />
												</c:otherwise>
											</c:choose>
										</label>
										<!-- 上传按钮 -->
										<input type="file" class="hide" id="upload"
											accept="image/gif, image/jpeg, image/jpg, image/png" />
									</div>
									<input type="hidden" name="cover" id="cover" value="">
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
								<!--文本域-->
								<div class="unit">
									<div class="left">
										<p class="subtitle">文章内容</p>
									</div>
									<div class="right">
										<div id="edit" class="edit-container"></div>
									</div>
									<input type="hidden" name="content" id="content" value="">
									<input type="hidden" name="contentText" id="contentText"
										value="">
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
								<!--分割线-->
								<p class="divider"></p>

								<!--提交按钮-->
								<div class="unit">
									<div style="margin-left: 200px;">
										<!--表单提交时，必须是input元素，并指定type类型为button，否则ajax提交时，会返回error回调函数-->
										<input type="button" id="return" class="button no" value="返回" />
										<input type="button" id="save" class="button yes" value="保存" />
									</div>
									<!--清浮动-->
									<span class="clearfix"></span>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
<script>
	javaex.upload({
			type : "image",
			url : "upload.json",	// 请求路径
			id : "upload",	// <input type="file" />的id
			param : "file",			// 参数名称，SSM中与MultipartFile的参数名保持一致
			dataType : "url",		// 返回的数据类型：base64 或 url
			callback : function (rtn) {
				// 后台返回的数据
				console.log(rtn);
				if (rtn.code=="000000") {
					var imgUrl=rtn.data.imgUrl
					$("#container img").attr("src", "/upload/"+imgUrl);
					$("#cover").val(imgUrl)
				} else {
					javaex.optTip({
						content : rtn.message,
						type : "error"
					});
				}
			}
		});
		var content='${articleInfo.content}';
	javaex.edit({
		id : "edit", 
		image : {
			url : "upload.json",	// 请求路径
			param : "file",		// 参数名称，SSM中与MultipartFile的参数名保持一致
			dataType : "url",	// 返回的数据类型：base64 或 url
			rtn : "rtnData",	// 后台返回的数据对象，在前面页面用该名字存储
			imgUrl : "data.imgUrl",	// 根据返回的数据对象，获取图片地址。  例如后台返回的数据为：{code: "000000", message: "操作成功！", data: {imgUrl: "1.jpg"}}
			prefix : "/upload/"	// 图片地址的前缀，根据实际情况决定是否需要填写
		}, 
		content : content,	// 这里必须是单引号，因为html代码中都是双引号，会产生冲突
		callback : function(rtn) {
			$("#content").val(rtn.html);
			$("#contentText").val(rtn.text.substring(0,100));
		}
	});
		javaex.select({
			id : "select" ,
			isSearch : false
		});
	$("#return").click(function(){
		history.back();
	})
	$("#save").click(function(){
		$.ajax({
			url : "save.json",
			type : "POST",
			dataType : "json",
			data : $("#form").serialize(),
			success : function(rtn) {
			     console.log(rtn);
				if(rtn.code=="000000"){
				   javaex.optTip({
				   content : "操作成功",
			       });
			       setTimeout(function(){
			       		window.location.href="articlelist.action";
			       },1500)
			 	}
			  }
		});
	})	

</script>
</html>
