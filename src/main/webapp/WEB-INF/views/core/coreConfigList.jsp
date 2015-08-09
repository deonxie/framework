<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>系统配置管理</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
        function page(n) {
            $("#pageNo").val(n);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}${baseMapper}">配置列表</a></li>
    <shiro:hasPermission name="config:edit">
        <li><a href="${ctx}${baseMapper}/update">添加配置</a></li>
    </shiro:hasPermission>
</ul>
<form id="searchForm" action="${ctx}${baseMapper}" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.number+1}"/>
    <input id="orderDir" name="orderDir" type="hidden" value="${pageRequest.orderDir}"/>
    <input id="orderBy" name="orderBy" type="hidden" value="${pageRequest.orderBy}"/>
    <div>
        <label>描述：</label>
        <input type="text" name="search_LIKE_description" class="input-small" placeholder="描述"
               value="${param.search_LIKE_description}">
        &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
<c:if test="${constKeyNum gt 0 }">
<span class="label label-danger" type="button">系统必须参数，未配置数
<span class="badge">${constKeyNum }</span>,请点击‘添加配置’进行配置</span>
</c:if>
    </div>
</form>
<tags:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>#</th>
        <th>key</th>
        <th>值</th>
        <th>描述</th>
        <th>备注</th>
        <th>排序</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.content}" var="conf" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td><a href="${ctx}${baseMapper}/update?id=${conf.id}">${conf.key}&nbsp;</a></td>
            <td>${conf.value }</td>
            <td>${conf.description }</td>
            <td>${conf.remark }</td>
            <td>${conf.index }</td>
            <td>${conf.status }</td>
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
                            onClick="submitForm(${page.number-1})">上一页</a></li>
        </c:if>
        <li class="controls"><strong>总页数${page.totalPages},当前第${page.number
                + 1}页 ${page[hasNextPage]}</strong></li>
        <c:if test="${!pageRequest.nextPage}">
            <li class="disabled"><a href="#">下一页 </a></li>
        </c:if>
        <c:if test="${pageRequest.nextPage}">
            <li class=""><a href="#" id="nextPage"
                            onclick="submitForm(${page.number+2})">下一页</a></li>
        </c:if>
    </ul>
</div>
</body>
</html>