<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>待处理求购列表</title>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <meta name="decorator" content="mobile"/>
     <style type="text/css">
       .thumbnail img{width: 100%;height: 80px;}
       .myrow{margin-left: 0px;margin-right: 0px;}
       .mycol{padding: 0px;}
       .mythumbnail{margin-bottom: 0px;border-radius:0px;font-size: 12px;position: relative;}
       .mycaption{padding: 4px;}
       .mybadge{position: absolute;right: 5px;top: 5px;}
       h5{margin: 0px;}
       .mylabel{padding: 4px;border: 1px solid #ccc;}
    </style>
</head>
<body>
<div class="panel panel-default">
	<div class="panel-body">
    <!-- 展示信息 -->
   	<c:forEach items="${page.content }" var="ask" varStatus="num">
    <div class="row myrow">
   	 	<div class="col-xs-4 col-sm-2 mycol">
			<a href="${ctx }${baseMapper}/matching?id=${ask.id}" class="thumbnail mythumbnail">
				<img src="${ctxStatic }${ask.coverimg }" />
			</a>
		</div>
		<div class="col-xs-8 col-sm-10 mycol">
			<h5><span class="badge">数量${ask.requestnum }/${ask.units }</span></h5> 
			<h5>${ask.requirement }</h5>
			
		</div>
 	</div>
   	</c:forEach>
   	<!-- 分页 -->
   	<div class="row myrow">
 	<c:choose>
 		<c:when test="${page.pageNum eq 1 && fn:length(page.content) eq page.pageSize}">
 			<div class="col-xs-12 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control" 
	   			onclick="gotoPage(${page.pageNum+1})">下一页</button></div>
	   		</div>
 		</c:when><c:when test="${page.pageNum gt 1 && fn:length(page.content) eq page.pageSize}">
 			<div class="col-xs-6 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control"
	   			onclick="gotoPage(${page.pageNum-1})">上一页</button></div>
	   		</div>
 			<div class="col-xs-6 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control" 
	   			onclick="gotoPage(${page.pageNum+1})">下一页</button></div>
	   		</div>
 		</c:when><c:when test="${page.pageNum gt 1 && fn:length(page.content) lt page.pageSize}">
	   		<div class="col-xs-12 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control"
	   			onclick="gotoPage(${page.pageNum-1})">上一页</button></div>
	   		</div>
 		</c:when>
 	</c:choose>
   	</div>
</div>   	
</div>
<script type="text/javascript">
function gotoPage(num){
	$("#pageNum").val(num);
	$("#searchFrm").submit();
}
</script>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>