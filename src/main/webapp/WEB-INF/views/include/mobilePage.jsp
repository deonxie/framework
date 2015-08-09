<%@ page contentType="text/html;charset=UTF-8" %>
<!-- 分页 -->
<c:choose>
	<c:when test="${page.pageNum eq 1 && fn:length(page.content) eq page.pageSize}">
  		<div class="form-group"><button class="btn btn-xs btn-warning form-control" 
  			onclick="gotoPage(${page.pageNum+1})">下一页</button></div>
		</c:when>
	<c:when test="${page.pageNum gt 1 && fn:length(page.content) eq page.pageSize}">
  		<div class="form-group">
   		<button class="btn btn-xs btn-warning" style="width: 49%;height: 34px;"
   			onclick="gotoPage(${page.pageNum-1})">上一页</button>
   		<button class="btn btn-xs btn-warning " style="width: 49%;height: 34px;"
   			onclick="gotoPage(${page.pageNum+1})">下一页</button>
  		</div>
	</c:when>
	<c:when test="${page.pageNum gt 1 && fn:length(page.content) lt page.pageSize}">
  		<div class="form-group"><button class="btn btn-xs btn-warning form-control"
  			onclick="gotoPage(${page.pageNum-1})">上一页</button>
  		</div>
	</c:when>
</c:choose>