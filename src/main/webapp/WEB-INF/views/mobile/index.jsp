<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>辅料代购平台</title>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <meta name="decorator" content="mobile"/>
    <style type="text/css">
       .thumbnail img{width: 100%;height: 100px;}
       .myrow{margin-left: 0px;margin-right: 0px;}
       .mycol{padding: 0px;}
       .mythumbnail{margin-bottom: 0px;border-radius:0px;font-size: 12px;position: relative;}
       .mycaption{padding: 4px;}
       h5{margin: 0px;}
    </style>
</head>
<body>
<div class="container-fluid">
<c:if test="${!empty adsImgs }">
    <div id="mycarousel" class="carousel slide" data="carousel">
		<ol class="carousel-indicators">
		<c:forEach items="${adsImgs }" var="bg" varStatus="index">
			<li data-target="#mycarousel" data-slide-to="${index.index }" class="${index.index eq 0 ?'active':''}"></li>
		</c:forEach>
		</ol>
		<div class="carousel-inner" role="listbox">
			<c:forEach items="${adsImgs }" var="ads" varStatus="index">
			<div class="item ${index.index eq 0 ?'active':''}">
				<a href="${ads.remark }"><img src="${ctxStatic }${ads.value}" alt="${ads.description }">
				<div class="carousel-caption" >${ads.description }</div></a>
			</div>
			</c:forEach>
		</div>
		<a class="left carousel-control" href="#mycarousel" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			<span class="sr-only">前一个</span>
		</a>
		<a class="right carousel-control" href="#mycarousel" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">下一个</span>
		</a>
	</div>
</c:if>
<c:if test="${!empty products }">
<div class="panel" style="margin-bottom: 0px;">
	<div class="panel-heading btn-warning" style="position: relative;padding-bottom: 0px;"">
		<label>推荐产品</label>
	</div>
	<div class="row myrow">
	<c:forEach items="${products }" var="product" varStatus="num">
		<div class="col-xs-6 col-sm-3 mycol">
			<a href="${ctx }/mobile/product/detail?id=${product.id}" class="thumbnail mythumbnail">
				<img src="${ctxStatic }${product.coverimg }"/>
				<div class="caption" style="padding: 0px;overflow: hidden;">
					<h5><font color="red">￥${product.price gt 0?product.price:'面议' }/${product.units }</font></h5> 
					${product.name }
				</div>
			</a>
		</div>
		<c:if test="${num.count % 4 eq 0 }"></div><div class="row myrow"></c:if>		
	</c:forEach>
	</div>
</div>
</c:if>
</div>
<c:set value="1" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>