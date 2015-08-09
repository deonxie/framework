<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
	<title>辅料类型管理</title>
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
		<li class="active"><a href="${ctx}${baseMapper}">辅料类型列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}${baseMapper}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.number+1}"/>
		<input id="orderDir" name="orderDir" type="hidden" value="${pageRequest.orderDir}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${pageRequest.orderBy}"/>
		<div>
			<input class="btn btn-primary" type="button" onclick="update('','')" value="新增"/>
			<label>类型名称：</label><input type="text" name="search_LIKE_typeName"
			 class="input-small" value="${param.search_LIKE_typeName}">
            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form>
	
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>类型名称</th>
            <th>最新修改时间</th>
            <th>操作</th>
        </tr>
        </thead>
   
		<tbody>
        <c:forEach items="${page.content}" var="type" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${type.typeName}&nbsp;</td>
                <td><fmt:formatDate value="${type.updateTime}" pattern="yyyy年MM月dd日"/></td>
				<td>
                   <a class="btn btn-primary" onclick="update('${type.id}','${type.typeName }')" >修改</a> 
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
    
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" 
        aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalLabel">类型管理</h4>
      </div>
      <form action="${ctx }/fuliao/producttype/save" method="post">
      <div class="modal-body">
            <input type="hidden" name="id" id="tid"/>
          <div class="form-group">
            <label for="recipient-name" class="control-label">类型名称:</label>
            <input type="text" name="typeName" id="typeName" class="required form-control" 
            placeholder="请输入类型名称"/>
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary">保存</button>
      </div>
      </form>
    </div>
  </div>
</div>
    <script type="text/javascript">
    function update(id,name){
    	$("#tid").val(id);
    	$("#typeName").val(name);
    	$("#myModal").modal();
    }
    </script>
</body>
</html>