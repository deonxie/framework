<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>订单详情</title>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <meta name="decorator" content="mobile"/>
     <style type="text/css">
       .thumbnail img{width: 100%;height: 80px;}
    </style>
</head>
<body>
<div class="panel panel-info">
	 <div class="panel-heading">
	 	<p class="text text-danger">订单：${entity.orderId }</p>
	 	收货地址: ${entity.address }<br>
		收 货 人: ${entity.receiveName }<br>
		联系电话: ${entity.receiveTel }<br>
		<div class="pull-right"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd"/></div>
		<c:if test="${entity.payStatus eq 0 }"><span class="label label-danger">未支付定金</span></c:if>
		<c:if test="${entity.payStatus eq 1 }"><span class="label label-danger">已支付定金</span></c:if>
		<c:if test="${entity.payStatus eq 2 }"><span class="label label-danger">已支付货款</span></c:if>
		<c:if test="${entity.payStatus eq -1 }"><span class="label label-danger">退款</span></c:if>
		<span class="label label-danger"><b>总价:￥${entity.amount }元 定金:￥${entity.handsel }元</b></span>
	 </div>
	 <table class="table">
	 <c:forEach items="${entity.products }" var="product" varStatus="index">
	 <tr>
 		<td width="40%">
		<a class="thumbnail" style="margin:0px; "><img src="${ctxStatic }${product.imgName}"/></a>
		</td><td>
			${product.productName }
			<span class="label label-primary">数量:${product.requestNum}</span>
			<span class="label label-danger">总价:￥${product.totalPrice }元</span>
			<p>${product.remark }</p>
		</td>	
 	</tr>
	</c:forEach>
	</table>
</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>