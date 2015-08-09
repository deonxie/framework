<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>求购处理详情</title>
    <meta name="decorator" content="mobile"/>
    <style type="text/css">
     .thumbnail img{width: 100%;height: 200px;}
     .mythumbnail{margin-bottom: 0px;border-radius:0px;font-size: 12px;position: relative;}
    </style>
</head>
<body>
<div class="thumbnail"><img src="${ctxStatic }${entity.imgName}" /></div> 
<div class="container">
	<fieldset>
		<legend>
	  		<font color="red">${entity.requestNum}/${entity.units }</font> 
			<fmt:formatDate value="${entity.createTime }" pattern="yy年MM月dd"/>
		</legend>
		${entity.requirement }
		<p class="text-danger">${entity.remark }</p>
	</fieldset>
	
	<c:forEach items="${mapps }" var="mapp">
	<div class="row">
		<div class="col-xs-4 ">
			<a href="${ctx }/mobile/product/detail?id=${mapp.product.id}"
			class="thumbnail mythumbnail"><img src="${ctxStatic }${mapp.product.coverimg }" style="height: 80px;"></a>
		</div><div class="col-xs-8">
			<div class="input-group">
			<a>数量<span class="badge">${mapp.bookNum}</span></a>
			<a>总价<span class="badge">${mapp.amount}元</span></a>
			</div>
			${mapp.product.name }
		</div>
	</div>
	</c:forEach>
	<c:if test="${entity.status eq 1}">
	<div class="well well-sm">
		<form action="${ctx }${baseMapper}/againMatching" method="post">
			<input type="hidden" name="id" value="${entity.id}">
			<div class="input-group">
				<span class="input-group-addon">备注</span>
				<textarea name="remark" class="form-control" ></textarea>
			</div>
			<input type="submit" value="确认平台匹配结果" class="btn btn-warning form-control">
		</form>
	</div>
	</c:if>
</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>
    