<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>分类列表</title>
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
						<span>分类管理</span> <span class="divider">/</span> <span
							class="active">文章分类</span>
					</div>
				</div>
				<!--主体内容-->
				<div class="list-content">
					<!--块元素-->
					<div class="block">
						<!--页面有多个表格时，可以用于标识表格-->
						<h2>文章分类管理</h2>
						<!--右上角的返回按钮-->
						<a href="javascript:history.back();">
							<button class="button wathet"
								style="position: absolute;right: 20px;top: 16px;">
								<span class="icon-arrow_back"></span> 返回
							</button>
						</a>

						<!--正文内容-->
						<div class="main-20">
							<!--表格上方的搜索操作-->
							<div style="text-align:right;margin-bottom:10px;">
								<!-- 标题检索 -->
								<input type="text" class="text" id="title" value=""
									placeholder="检索标题" />
								<!-- 点击查询按钮 -->
								<button class="button blue" style="margin-top: -3px;"
									onclick="search()">
									<span class="icon-search"></span>
								</button>
							</div>

							<!--表格上方的操作元素，添加、删除等-->
							<div class="operation-wrap">
								<div class="buttons-wrap">
									<button id="add" class="button blue">
										<span class="icon-plus"></span> 添加
									</button>
									<button id="save" class="button green">
										<span class="icon-check"></span> 保存
									</button>									
									<button id="delete" class="button red">
										<span class="icon-minus"></span> 删除
									</button>
								</div>
							</div>
							<table id="table" class="table">
								<thead>
									<tr>
										<th><input type="checkbox" class="fill listen-1"/> </th>
										<th>显示排序</th>
										<th>名称</th>
									</tr>
								</thead>
								<tbody>
								<!-- 通过jstl标签循环遍历文章分类内容 -->
									<c:forEach items="${sortlist}" var="entity" varStatus="status" >
										<tr>
											<td style="width:30px"><input type="checkbox" class="fill listen-1-2" name="id" value="${entity.id}"/> </td>
											<td style="width:30px"><input type="text" class="text" name="sort" data-type="正整数" error-msg="请输入正整数" value="${entity.sort}" /></td>
											<td style="width:30px"><input type="text" class="text" name="name" data-type="必填"  placeholder="请输入分类名称" value="${entity.name}" /></td>
										</tr>
									</c:forEach>									
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script >
	var idArr = new Array();
	var nameArr = new Array();
	var sortArr = new Array();
	//添加按钮添加事件
	$("#add").click(function(){
		var html='';
		html+='<tr>';
		html+='<td style="width:30px"><input type="checkbox" class="fill listen-1-2" name="id" value="${entity.id}"/> </td>';
		html+='<td style="width:30px"><input type="text" class="text" name="sort" data-type="正整数" error-msg="请输入正整数"/></td>';
	    html+='<td style="width:30px"><input type="text" class="text" name="name" data-type="必填"  placeholder="请输入分类名称" value="${entity.name}"/></td>';
		html+='</tr>';
		//jquery选取table.tbody元素并在其后面添加html语言 
	    $("#table tbody").append(html);
	    javaex.render();//重新渲染导入JavaScript
	})
	//添加按钮保存事件
	$("#save").click(function(){
		idArr = [];
		sortArr = [];
		nameArr = [];
		//jquery选取所有name为id的checkbox元素并为其循环遍历将value添加到数组之中
	 	$(':checkbox[name="id"]').each(function(){
	 		idArr.push($(this).val());	 		
	 	})
	 	$(':text[name="sort"]').each(function(){
	 		sortArr.push($(this).val());	 		
	 	})
	 	$(':text[name="name"]').each(function(){
	 		nameArr.push($(this).val());	 		
	 	})

	 /* 	console.log(idArr); //在浏览器控制台查看输出 用于调试
	 	console.log(sortArr);
	 	console.log(nameArr);  */
		$.ajax({
		url : "${pageContext.request.contextPath}/type_info/save.json",
		type : "POST",
		dataType : "json",
		data : {
				"idArr" :idArr,
				"nameArr" :nameArr,
				"sortArr" :sortArr
		},
		traditional : "true", 	//使用jquery的ajax提交json请求   需要通过traditional : "true" 允许提交数组,
                                //rtn为返回的result类型转成的json数据格式
		success : function(rtn) {
		    // console.log(rtn);
			if(rtn.code=="000000"){
			   javaex.optTip({
			   content : "操作成功",
		       });
		       setTimeout(function(){
		       		window.location.reload();
		       },1500)
		 	}
		 	
		  }
	   });	 	
	})
	$("#delete").click(function() {
		idArr = [];
		//遍历所有被勾选的复选框
		$(':checkbox[name="id"]:checked').each(function() {
			var id = $(this).val();

			if (id != "") {
				idArr.push(id);
			}
		})
		console.log(idArr);
		//判断是否为刚添加的数据的记录,若是则无刷新的删除记录
		if(idArr.length==0){
		$(':checkbox[name="id"]:checked').each(function() {
			console.log($(this).val())
			$(this).parent().parent().parent().remove();
			})
		}
		else{
		$.ajax({
			url : "${pageContext.request.contextPath}/type_info/delete.json",
			type : "POST",
			dataType : "json",
			data : {
					"idArr" :idArr
			},
			traditional : "true", 	//使用jquery的ajax提交json请求   需要通过traditional : "true" 允许提交数组,
	                                //rtn为返回的result类型转成的json数据格式
			success : function(rtn) {
			     console.log(rtn);
				if(rtn.code=="000000"){
				   javaex.optTip({
				   content : "操作成功",
			       });
			       setTimeout(function(){
			       		window.location.reload();
			       },1500)
			 	}
			 	else
					javaex.optTip({
						content : rtn.message,
						type : "error"
				});
			  }
		   });	 				
		
		}
	}
	);
</script>
</html>
