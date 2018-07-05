<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>文章列表</title>
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
							class="active">文章列表</span>
					</div>
				</div>
				<!--主体内容-->
				<div class="list-content">
					<!--块元素-->
					<div class="block">
						<!--页面有多个表格时，可以用于标识表格-->
						<h2>文章列表管理</h2>
						<!--正文内容-->
						<!--表格上方的搜索操作-->
						<div style="text-align:right;margin-bottom:10px;">
							<!-- 文章分类下拉列表 -->
							<select id="type_id" class="no-shadow">
								<option value="">请选择</option>
								<c:forEach items="${typeList}" var="typeInfo" varStatus="status">
									<option value="${typeInfo.id}"
										<c:if test="${typeId==typeInfo.id}">selected </c:if> 
										>${typeInfo.name}
								</c:forEach>
							</select>
							<!-- 日期控件 -->
							<input type="text" id="date2" class="date" style="width: 300px;"
								value="" readonly />

							<!-- 标题检索 -->
							<input type="text" class="text" id="keyWord" value=""
								placeholder="检索标题" />
							<!-- 点击查询按钮 -->
							<button class="button blue" id="search" style="margin-top: -3px;">
								<span class="icon-search"></span>
							</button>
						</div>
						<!--表格上方的操作元素，添加、删除等-->
						<div class="operation-wrap">
							<div class="buttons-wrap">
								<a href="edit.action">
									<button id="edit" class="button blue">
										<span class="icon-plus"></span> 添加
									</button>
								</a>

								<button id="deleteArticle" class="button red">
									<span class="icon-plus"></span> 删除
								</button>

							</div>
						</div>
						<table id="table" class="table">
							<thead>
								<tr>
									<th style="width: 10%"><input type="checkbox"
										class="fill listen-1" /> </th>
									<th style="width: 15%">序号</th>
									<th style="width: 15%">分类</th>
									<th style="width: 15%">文章标题</th>
									<th style="width: 15%">撰写日期</th>
									<th style="width: 15%">阅读量</th>
									<th style="width: 15%">编辑</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(pageInfo.list)==0}">
										<tr>
											<td colspan="7" style="text-align: center">暂无记录</td>
										</tr>
									</c:when>
									<c:otherwise>
										<!-- 通过jstl标签循环遍历文章列表内容 -->
										<c:forEach items="${pageInfo.list}" var="entity"
											varStatus="status">
											<tr>
												<td><input type="checkbox" class="fill listen-1"
													name="id" value=" ${entity.id}" /> </td>
												<td>${status.index+1}</td>
												<td>${entity.name}</td>
												<td>${entity.title}</td>
												<td>${entity.updateDate}</td>
												<td>${entity.viewCount}</td>
												<td><a href="edit.action?id=${entity.id}">
														<button class="button blue">
															<span class="icon-mode_edit"></span>编辑
														</button>
												</a></td>

											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>

						<!-- 分页显示 -->
						<div class="page">
							<ul id="page" class="pagination"></ul>
						</div>
						<!--块元素-->
						<div class="block no-shadow">
							<!--banner用来修饰块元素的名称-->
							<div class="banner">
								<p class="tab fixed">批量操作<span class="hint">Batch Operation</span></p>
							</div>
							<!--正文内容-->
							<div class="main">
								<label zoom="1.5"><input type="radio"  name="radio" />&nbsp&nbsp批量删除</label>
								<br>
								<br>
								<button class="button green" id="deleteSubmit"><span class="icon-check"></span>提交</button>
							</div>
						</div>
					</div>
				</div>			
			</div>
		</div>
	</div>
</body>

<script>
var startDate="";
var endDate="";
javaex.select({
		id : "type_id"
	});
javaex.date({
		id : "date2",		// 承载日期组件的id
		monthNum : 2,		// 2代表选择范围日期
		alignment: "right",
		startDate : "",	// 开始日期
		endDate : "",		// 结束日期
		// 重新选择日期之后返回一个时间对象
		callback : function(rtn) {
			startDate=rtn.startDate;
			endDate=rtn.endDate;
		}
	});
	
 	var pageCount=${pageInfo.pages};
 	var currentPage=${pageInfo.pageNum};
	javaex.page({
		id : "page",
		pageCount : pageCount,	// 总页数
		currentPage : currentPage,// 默认选中第几页
		position : "right",
		// 返回当前选中的页数
		callback:function(rtn) {
			//alert(rtn.pageNum);
			pageNum=rtn.pageNum;
			//alert(pageNum);
			search(pageNum);
		}		
	});
		function search(){
			window.location.href="articlelist.action?pageNum="+pageNum;
		}
	$("#search").click(function (pageNum){
			pageNum=1;
		var typeId =$("#type_id").val();//通过select来获取值
		var keyWord = $("#keyWord").val();
		 //alert(typeId);
		//console.log(keyWord);
 	 	 window.location.href="articlelist.action"
 		 	+"?pageNum="+pageNum
		 	+"&typeId="+typeId
			+"&keyWord="+keyWord
		 	+"&startDate="+startDate 
		 	+"&endDate="+endDate;     
	});
 
		//用于批量删除文章
	$("#deleteSubmit").click(function() {
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
		$.ajax({
			url : "deleteArticle.json",
			type : "POST",
			dataType : "json",
			data : {
					"idArr" :idArr
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
			       		window.location.href="articlelist.action";
			       },1500)
			 	}
			  }
		   });	 				
		
		}
	); 
	//	var startDate="";
	//	var endDate="";
		

</script>
</html>
