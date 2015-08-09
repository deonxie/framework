<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
	<title>辅料代购商家管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
		function page(n){
			$("#pageNo").val(n);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}${baseMapper}">辅料代购商家列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}${baseMapper}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.number+1}"/>
		<input id="orderDir" name="orderDir" type="hidden" value="${pageRequest.orderDir}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${pageRequest.orderBy}"/>
		<div>
			<label>店铺名称：</label><input type="text" name="search_LIKE_shopName" class="input-small" 
			value="${param.search_LIKE_shopName}">
            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead><tr>
            <th>#</th>
            <th>店铺名称</th>
            <th>联系电话</th>
            <th>联系人</th>
            <th>地址</th>
            <th>创建时间</th>
            <th>状态</th>
            <th>店铺登录用户</th>
            <th>操作</th>
        </tr></thead>
		<tbody>
        <c:forEach items="${page.content}" var="user" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td><a href="${ctx }/fuliao/shops/update?id=${user.id}">${user.shopName}&nbsp;&nbsp;</a></td>
                <td>${user.telNum}&nbsp;</td>
                <td>${user.telName}&nbsp;</td>
                <td>${user.address}&nbsp;</td>
                <td><fmt:formatDate value="${user.createTime}" pattern="yyyy年MM月dd日"/></td>
                <td>${user.status eq '1'?'可用':'<font color="red">禁用</font>'}&nbsp;</td>
				<td>${user.shopkeeper.userName }</td>
				<td><a href="${ctx }/fuliao/shops/update?id=${user.id}">详情</a></td>
            </tr>
        </c:forEach>
		</tbody>
	</table>
    <div class="row">
        <ul class="pager">
            <c:if test="${!pageRequest.prePage}">
                <li class=" disabled"><a href="#">上一页</a></li>
            </c:if>
            <c:if test="${pageRequest.prePage}">
                <li class=""><a href="#" id="prePage"
                                onClick="page(${page.number-1})">上一页</a></li>
            </c:if>
            <li class="controls"><strong>总页数${page.totalPages},当前第${page.number
                    + 1}页 ${page[hasNextPage]}</strong></li>
            <c:if test="${!pageRequest.nextPage}">
                <li class="disabled"><a href="#">下一页 </a></li>
            </c:if>
            <c:if test="${pageRequest.nextPage}">
                <li class=""><a href="#" id="nextPage"
                                onclick="page(${page.number+2})">下一页</a></li>
            </c:if>
        </ul>
    </div>
</body>
</html>