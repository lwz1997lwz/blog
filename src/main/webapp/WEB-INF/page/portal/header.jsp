<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="grid-1-3 container">
	<div id="logo">
		<a href="/blog/portal/index.action">Drose的博客</a>
	</div>
	<div id="nav">
		<ul class="nav">
			<li class="active"><a href="/blog/portal/index.action">首页</a></li>
			<li style="position: relative;"><a href="javascript:;">文章目录</a>
				<ul class="classified-nav">
					<c:forEach items="${typeList}" var="entity" varStatus="status">
						<li><a
							href="/blog/portal/articleByTypelist.action?typeId=${entity.id}">${entity.name}</a></li>
					</c:forEach>
				</ul></li>
			<li><a href="#">关于我</a></li>
			<li><a href="javascript:;" onClick="openSearch()"><span
					class="icon-search" style="font-size: 14px;"></span></a></li>
		</ul>
		<div id="search" class="hide">
			<span class="search-field-wrapper form-group"> <input
				type="text" class="search" placeholder="搜索…" value="" onkeydown="if(event.keyCode==13){searchKeyword(this.value);}">
			</span> <span id="close-search" class="icon-close" style="font-size: 16px;"></span>
		</div>
	</div>
</div>
<script>
	function searchKeyword(keyWord){
		keyWord = keyWord.replace(/(^\s*)|(\s*$)/g, "");
		if(keyWord!=""){
			window.location.href="search.action?keyWord="+keyWord;
		}
	}
</script>
