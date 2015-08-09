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
<div class="container-fluid">
	<form action="${ctx }${baseMapper}/list/shop" method="post" id="searchFrm">
		<input type="hidden" id="pageNo" name="pageNo" value="${pageRequest.pageNo }"/>
	</form>
	<ul class="nav nav-tabs" role="tablist">
    	<li role="presentation">
    		<a href="${ctx }${baseMapper}/list/product" >收藏的产品</a>
    	</li>
	    <li role="presentation" class="active"><a>收藏的商家</a></li>
	</ul>
	
	<div class="tab-content">
   	<c:forEach items="${page.content }" var="store" varStatus="num">
   	<div class="row myrow">
		<div class="col-xs-4  mycol">
			<a href="${ctx }/mobile/product/shopDetail?sid=${store.shop.id}" class="thumbnail mythumbnail">
				<c:choose><c:when test="${empty store.shop.logoImg}">
					<img src="${ctxStatic }${shopDefLogoImg}" />
				</c:when><c:otherwise>
					<img src="${ctxStatic }${store.shop.logoImg }" />
				</c:otherwise></c:choose>
			</a>
		</div>
		<div class="col-xs-8 mycol" >
			<div class="input-group">
			<span class="input-group-addon"><fmt:formatDate value="${store.createTime }" pattern="yyyy-MM-dd"/></span>
			<a href="${ctx }${baseMapper}/remove/shop?id=${store.id}" class="input-group-addon btn btn-danger">移除</a>
			</div>
			${store.shop.shopName }
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