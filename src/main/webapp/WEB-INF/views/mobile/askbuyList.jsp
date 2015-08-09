<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>我的求购</title>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
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
<div class="panel">
<div class="panel-heading bg-warning">
	<ul class="nav nav-tabs" style="margin-bottom: -11px;">
    <li ${page.field.option eq 1?'class="active"':'' }><a href="${ctx }${baseMapper}/list?search_option=1">处理中</a></li>
    <li ${page.field.option eq 2?'class="active"':'' }><a href="${ctx }${baseMapper}/list?search_option=2">待确认</a></li>
    <li ${page.field.option eq 3?'class="active"':'' }><a href="${ctx }${baseMapper}/list?search_option=3">匹配成功</a></li>
    <li ${page.field.option eq 4?'class="active"':'' }><a href="${ctx }${baseMapper}/list?search_option=4">已完成</a></li>
	</ul>
	<form action="${ctx }${baseMapper}/list" method="post" id="searchFrm">
	  	<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
	  	<input type="hidden" name="search_option" value="${page.field.option }">
	   <%--  <div class="input-group" style="margin-top: 5px;">
		    <input class="form-control" name="search_keyword" placeholder="请输入关键字"
		    style="height: 43px;" value="${page.field.keyword }"/>
		    <span class="input-group-addon" onclick="searchFrm.submit();">
		    <span class="glyphicon glyphicon-search"></span>搜索</span>
	    </div> --%>
    </form>
</div>
<div class="panel-body">
    <!-- 展示信息 -->
    <c:if test="${page.field.option eq 3}">
    <form id="orderFrm" action="${ctx }/mobile/order/create" method="post">
    </c:if>
   	<c:forEach items="${page.content }" var="ask" varStatus="num">
    <div class="row myrow">
   	 	<div class="col-xs-4 col-sm-2 mycol">
			<a href="${ctx }${baseMapper}/macthInfo?id=${ask.id}" class="thumbnail mythumbnail">
				<img src="${ctxStatic }${ask.coverimg }" />
			</a>
		</div>
		<div class="col-xs-8 col-sm-10 mycol">
			<c:if test="${ask.status eq 0}">
			<div class="pull-right">
				<span class="badge badge-danger">数量${ask.requestnum }/${ask.units }</span>
				<%-- <a href="${ctx }${baseMapper}/myoption?option=cancel&id=${ask.id}" class="btn btn-danger">取消</a> --%>
			</div>
			</c:if>
			${ask.requirement }
			<c:if test="${page.field.option eq 3}">
				<label style="color:red;"><input type="checkbox" name="askid" onchange="changeNum(this)" 
				value="${ask.id }">定金:${ask.handsel }|总价:${ask.amount }</label>
			</c:if>
		</div>
 	</div>
   	</c:forEach>
   	<c:if test="${page.field.option eq 3}"></form></c:if>
   	<!-- 分页 -->
   	<%@ include file="/WEB-INF/views/include/mobilePage.jsp" %>
</div>
<c:if test="${page.field.option eq 3}">
<span style="position: fixed ;bottom: 20%;right: 0px;">
<label class="btn btn-default"><input type="checkbox" onclick="selectAll(this)">全选</label>
<button class="btn btn-danger" onclick="buildOrder()">结算<span class="badge" id="total">0</span></button></span>	
</c:if>
</div>
<script type="text/javascript">
function gotoPage(num){
	$("#pageNum").val(num);
	$("#searchFrm").submit();
}
function selectAll(obj){
	if(obj.checked){
		var s = $(":checkbox[name='askid']").each(function(){this.checked=true;}).size();
		$("#total").text(s);
	}
}
function buildOrder(){
	if($("input:checked[name='askid']").size()>0)
		$("#orderFrm").submit();
	else
		alert('请选中需要下单的求购');
}
function changeNum(obj){
	var total = parseInt($("#total").text());
	if(obj.checked){
		total++;
	}else{
		total--;
	}
	$("#total").text(total);
}
</script>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>