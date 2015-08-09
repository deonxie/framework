<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>我的收藏</title>
    <meta name="decorator" content="mobile"/>
     <style type="text/css">
       .thumbnail img{width: 100%;height: 80px;}
       .myrow{margin-left: 0px;margin-right: 0px;border-top: 1px solid #ccc;border-right: 1px solid #ccc;}
       .mycol{padding: 0px;}
       .mythumbnail{margin-bottom: 0px;border-radius:0px;font-size: 12px;position: relative;}
       .mycaption{padding: 4px;}
       .mybadge{position: absolute;right: 5px;top: 5px;}
       h5{margin: 0px;margin-top: 5px;}
    </style>
</head>
<body>
<div class="container-fluid"><br>
	<form action="${ctx }${baseMapper}/list/product" method="post" id="searchFrm">
		<input type="hidden" id="pageNo" name="pageNo" value="${pageRequest.pageNo }"/>
	</form>
	<!-- <ul class="nav nav-tabs" role="tablist">
	    <li role="presentation" class="active"><a>收藏的产品</a></li>
	</ul> -->
	
	<div class="tab-content">
   	<c:forEach items="${page.content }" var="store" varStatus="num">
   	<div class="row myrow">
		<div class="col-xs-4  mycol">
			<a href="${ctx }/mobile/product/detail?id=${store.product.id}" class="thumbnail mythumbnail">
				<img src="${ctxStatic }${store.product.coverimg }" />
			</a>
		</div>
		<div class="col-xs-8 mycol" >
			<div class="pull-right">
				<a href="${ctx }${baseMapper}/remove/product?id=${store.id}" class="btn btn-danger">移除</a><br>
				<font color="red">库存(${store.product.stockNum }${store.product.units })</font>
			</div>
			<fmt:formatDate value="${store.createTime }" pattern="yyyy-MM-dd"/>
			<font color="red">￥${store.product.price gt 0?store.product.price:'面议' }/${store.product.units }</font>
			<br>${store.product.name }
		</div>
	</div>
   	</c:forEach>
   	
   	<!-- 分页 -->
   	<div class="row myrow">
 	<c:choose>
 		<c:when test="${pageRequest.pageNo eq 1 && page.totalPages gt 1}">
 			<div class="col-xs-12 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control" 
	   			onclick="gotoPage(${pageRequest.pageNo+1})">下一页</button></div>
	   		</div>
 		</c:when>
 		<c:when test="${pageRequest.pageNo gt 1 && pageRequest.pageNo lt page.totalPages}">
 			<div class="col-xs-6 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control"
	   			onclick="gotoPage(${pageRequest.pageNo-1})">上一页</button></div>
	   		</div>
 			<div class="col-xs-6 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control" 
	   			onclick="gotoPage(${pageRequest.pageNo+1})">下一页</button></div>
	   		</div>
 		</c:when>
 		<c:when test="${pageRequest.pageNo gt 1 && pageRequest.pageNo eq page.totalPages }">
	   		<div class="col-xs-12 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control"
	   			onclick="gotoPage(${pageRequest.pageNo-1})">上一页</button></div>
	   		</div>
 		</c:when>
 	</c:choose>
   	</div>
   	</div>
</div>
<script type="text/javascript">
	function gotoPage(num){
		$("#pageNo").val(num);
		$("#searchFrm").submit();
	}
</script>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>