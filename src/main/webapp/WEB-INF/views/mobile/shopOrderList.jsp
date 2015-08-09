<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>我的订单</title>
    <meta name="decorator" content="mobile"/>
     <style type="text/css">
       .myrow{margin-left: 0px;margin-right: 0px;border-bottom: 1px solid #ccc;}
       .mycol{padding: 5px;padding-top: 10px;font-size: 16px;}
    </style>
</head>
<body>
<div class="panel panel-default">
<div class="panel-heading">
	<ul class="nav nav-tabs" style="margin-bottom: -11px;">
    <li ${page.field.option eq 1?'class="active"':'' }><a href="${ctx }${baseMapper}?search_option=1">待处理</a></li>
    <li ${page.field.option eq 2?'class="active"':'' }><a href="${ctx }${baseMapper}?search_option=2">已处理</a></li>
    <li ${page.field.option eq 3?'class="active"':'' }><a href="${ctx }${baseMapper}?search_option=3">退 单</a></li>
	</ul>
	<form action="${ctx }${baseMapper}" method="post" id="searchFrm">
	  	<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
	  	<input type="hidden" name="search_option" value="${page.field.option }">
    </form>
</div>
	
<div class="panel-body">
   	<c:forEach items="${page.content }" var="order" varStatus="num">
   		<div class="alert alert-success">
	   		<p>${num.count }<a href="${ctx }${baseMapper}/detail?id=${order.id}">订单：${order.orderid }</a></p>
			<div class="pull-right"><fmt:formatDate value="${order.createtime}" pattern="yy-MM-dd"/></div>
			<span style="color:red;">总价格:￥${order.amount }元,定金:￥${order.handsel }元</span>
		</div>
   	</c:forEach>
   	
   	<!-- 分页 -->
   	<%@ include file="/WEB-INF/views/include/mobilePage.jsp" %>
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