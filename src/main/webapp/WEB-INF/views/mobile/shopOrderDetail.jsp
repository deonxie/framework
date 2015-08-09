<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>订单详情</title>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <meta name="decorator" content="mobile"/>
     <style type="text/css">
       .thumbnail img{width: 100%;height: 80px;}
       .myrow{margin-left: 0px;margin-right: 0px;border-top: 1px solid #ccc;border-right: 1px solid #ccc;}
       .mycol{padding: 0px;}
       .mythumbnail{margin-bottom: 0px;border-radius:0px;font-size: 12px;position: relative;}
    </style>
</head>
<body>
	<div class="panel panel-default">
	 	<div class="panel-heading">
	 		<p class="text text-danger">订单：${entity.orderId }</p>
		 	收货地址: ${entity.address }<br>
			收 货 人: ${entity.receiveName }<br>
			联系电话: ${entity.receiveTel }<br>
			<div class="pull-right"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd"/></div>
		 	<span class="label label-danger"><b>总价:￥${entity.amount }元 定金:￥${entity.handsel }元</b></span>
		 	<div class="container" style="color:red;">${error }</div><br>
		 	<c:if test="${entity.dealStatus eq 0 }">
		 	<form action="${ctx }${baseMapper}/send" method="post">
			 	<input type="hidden" name="id" value="${entity.id }">
			 	<div class="input-group">
			 		<span class="input-group-addon">快递名称</span>
			 		<input class="form-control" type="text" name="sendCompany" id="sendCompany"
			 		placeholder="快递公司名称"/>
			 	</div>
			 	<div class="input-group">
			 		<span class="input-group-addon">快递单号</span>
			 		<input class="form-control" type="text" name="sendCode" id="sendCode"
			 		placeholder="快递单号"/>
			 	</div>
			 	<input type="button" class="btn btn-warning form-control" value="发 货">
		 	</form>
		 	</c:if>
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