<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>我的订单</title>
    <meta name="decorator" content="mobile"/>
    <script src="${ctxStatic}/jweixin-1.0.0.js" type="text/javascript"></script>
     <style type="text/css">
       .myrow{margin-left: 0px;margin-right: 0px;border-bottom: 1px solid #ccc;}
       .mycol{padding: 5px;padding-top: 10px;font-size: 16px;}
    </style>
</head>
<body>
<div class="panel">
<div class="panel-heading bg-warning">
	<ul class="nav nav-tabs" style="margin-bottom: -11px;">
    <li ${page.field.option eq 1?'class="active"':'' }><a href="${ctx }${baseMapper}/user?search_option=1">待付款</a></li>
    <li ${page.field.option eq 2?'class="active"':'' }><a href="${ctx }${baseMapper}/user?search_option=2">已付定金</a></li>
    <li ${page.field.option eq 3?'class="active"':'' }><a href="${ctx }${baseMapper}/user?search_option=3">已付全款</a></li>
    <li ${page.field.option eq 4?'class="active"':'' }><a href="${ctx }${baseMapper}/user?search_option=4">已完成</a></li>
	</ul>
	<form action="${ctx }${baseMapper}/user" method="post" id="searchFrm">
	  	<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
	  	<input type="hidden" name="search_option" value="${page.field.option }">
    </form>
</div>
	
<div class="panel-body">
	   	<c:forEach items="${page.content }" var="order" varStatus="num">
	   	<div>
	   		<p>${num.count }<a href="${ctx }${baseMapper}/detail?id=${order.id}">订单：${order.orderid }</a></p>
			<span style="color:red;">总价格:￥${order.amount }元,定金:￥${order.handsel }元</span>
			<!-- 1待付款 2已付定金 3 已付全款 4 已完成 -->
	    	<div class="button-group pull-right">
	    	<fmt:formatDate value="${order.createtime}" pattern="yy-MM-dd"/>
			<c:choose>
	    	<c:when test="${page.field.option eq 1}"> 
				<button class="btn" ><a onclick="pay('${order.id}','one')">支付定金</a></button>
			    <button class="btn" ><a onclick="pay('${order.id}','all')">支付全款</a></button>
			</c:when>
	    	<c:when test="${page.field.option eq 2}">
			    <button class="btn" ><a onclick="pay('${order.id}','two')">支付余款</a></button>
			    <button class="btn" ><a onclick="reback('${order.id}')">退款</a></button>
			</c:when>
	        <c:when test="${page.field.option eq 3}">
	        	 <button class="btn" ><a onclick="reback('${order.id}')">退款</a></button>
	        </c:when> 
	        <c:when test="${page.field.option eq 4}">
	        	<c:if test="${order.dealstatus eq 2}">已完成</c:if>
	        	<c:if test="${order.dealstatus eq -1}"><font color="red">已取消</font></c:if>
	        </c:when>
	    </c:choose> 
		</div>
		</div><br>
		<hr>
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
	function pay(id,type){
		var url = window.location.href;
		url = url.substring(0,url.indexOf('/mobile/')+'/mobile/'.length);
		url = url+'payfor/'+type;
		var herf= 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri='
				+url+'&response_type=code&scope=snsapi_base&state='+id+'#wechat_redirect';
		
		window.location.href = herf;
	}
	function reback(id){
		
	}
</script>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>