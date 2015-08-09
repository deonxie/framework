<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
	<title>辅料代购用户管理</title>
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
		<li class="active"><a href="${ctx}${baseMapper}">辅料代购用户列表</a></li>
		<li><a href="${ctx}${baseMapper}/toadd">辅料代购用户添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}${baseMapper}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.number+1}"/>
		<input id="orderDir" name="orderDir" type="hidden" value="${pageRequest.orderDir}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${pageRequest.orderBy}"/>
		<div>
			<label>登录名：</label><input type="text" name="search_LIKE_account" class="input-small" value="${param.search_LIKE_account}">
            <label>姓&nbsp;&nbsp;&nbsp;名：</label><input type="text" name="search_LIKE_userName" class="input-small" value="${param.search_LIKE_userName}">
            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>登录名</th>
            <th>姓名</th>
            <th>绑定微信</th>
            <th>注册日期</th>
            <th>联系电话</th>
            <th>用户类型</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        </thead>
   
		<tbody>
        <c:forEach items="${page.content}" var="user" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${user.account}&nbsp;</td>
                <td>${user.userName}&nbsp;</td>
                <td>${empty user.openId?'未绑定':'已绑定'}&nbsp;</td>
                <td><fmt:formatDate value="${user.registerTime}" pattern="yyyy年MM月dd日"/></td>
                <td>${user.telPhone}&nbsp;</td>
                <td><c:choose><c:when test="${fn:contains(user.permissions,'shop:isShop')}">商家</c:when>
                	<c:otherwise>未开通商店</c:otherwise>
                </c:choose> &nbsp;
               	</td>
                <td>${user.address}&nbsp;</td>
				<td>
                   <shiro:hasPermission name="fluser:edit">
                   <a href="${ctx}${baseMapper}/addrole?id=${user.id}">开通权限</a>
                   </shiro:hasPermission>
                   <shiro:hasPermission name="fluser:view">
                   <a href="${ctx}${baseMapper}/toadd?id=${user.id}">详情</a>
                   </shiro:hasPermission>
               	</td>
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