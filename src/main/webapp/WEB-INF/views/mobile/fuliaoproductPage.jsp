<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>平台产品</title>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <meta name="decorator" content="mobile"/>
     <style type="text/css">
       .myrow{margin-left: 0px;margin-right: 0px;}
       .mycol{padding: 0px;}
       .mythumbnail{margin-bottom: 0px;border-radius:0px;font-size: 12px;position: relative;}
       .mycaption{padding: 4px;}
       .mybadge{position: absolute;right: 5px;top: 5px;}
       h5{margin: 0px;}
    </style>
</head>
<body>
	 <form action="${ctx }${baseMapper}" method="post" id="searchFrm">
	 	<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
<div class="container btn-warning" style="padding-top: 5px;padding-bottom: 5px;">
	    <div class="input-group" style="margin-top: 5px;">
	    	<div class="input-group-btn">
	    	<span  class="btn" onclick="filterBtn(this)">筛选<i class="glyphicon glyphicon-triangle-bottom"></i></span>
	    	</div>
		    <input class="form-control" name="search_keyword" placeholder="请输入关键字"
		    value="${page.field.keyword }"/>
		    <div class="input-group-btn">
		    <button class="btn" onclick="searchFrm.submit();">
		     <span class="glyphicon glyphicon-search"></span>搜索</button>
		     </div>
	    </div>
</div>	    
	    <div id="filterDiv" class="panel" style="display: none;">
			<div class="row">
	    		<div class="col-xs-12">
	    			<label class="btn btn-sm btn-warning"><input type="checkbox" onclick="allSselect(this)">全选</label>
	    			<c:forEach items="${types }" var="type">
	    				<label class="btn btn-sm btn-default"><input type="checkbox" value="${type.id }" name="search_typeid"
		    			<c:choose><c:when test="${!empty page.field.typeid }">
			    			${page.field.typeid eq type.id ?'checked="checked"':''}
			    			</c:when><c:otherwise>
			    			${fn:contains(page.fields.typeid ,type.id) ?'checked="checked"':'' }
			    			</c:otherwise>
		    			</c:choose>
		    			/>${type.typeName }</label>&nbsp;&nbsp;
	    			</c:forEach>
	    		</div>
			</div><div class="row" style="padding-top: 5px;">
	    		<div class="col-xs-12">
	    			<label class="btn btn-sm btn-warning" onclick="$('#orderfield').removeAttr('name');searchFrm.submit();">默认</label>
	    		<c:choose>
	    			<c:when test="${!empty page.field.createTime }">
		    			<c:set value="createTime" var="fielname"/>
		    			<c:set value="${page.field.createTime }" var="fielvalue"/>
	    			</c:when>
					<c:when test="${!empty page.field.price }">
		    			<c:set value="price" var="fielname"/>
		    			<c:set value="${page.field.price }" var="fielvalue"/>
		    		</c:when>
					<c:when test="${!empty page.field.saleNum }">
		    			<c:set value="saleNum" var="fielname"/>
		    			<c:set value="${page.field.saleNum }" var="fielvalue"/>
		    		</c:when>
	    		</c:choose>
	    			<label class="btn btn-sm ${fielname eq 'createTime' ?'btn-default':'' }" onclick="orderByFilter('order_createTime','desc')"
	    			>发布日期<span class="glyphicon glyphicon-arrow-up"></span></label>&nbsp;&nbsp;
	    			<label class="btn btn-sm ${fielname eq 'price' ?'btn-default':'' }" onclick="orderByFilter('order_price','desc')"
	    			>单价<span class="glyphicon glyphicon-arrow-up"></span></label>&nbsp;&nbsp;
	    			<label class="btn btn-sm ${fielname eq 'saleNum' ?'btn-default':'' }" onclick="orderByFilter('order_saleNum','desc')"
	    			>销量<span class="glyphicon glyphicon-arrow-up"></span></label>&nbsp;&nbsp;
	    			<input type="hidden" id="orderfield" name="order_${fielname }" value="${fielvalue }"/>
	    		</div>
			</div>
	</form>
	</div>
<div class="container-fluid">
    <div class="row myrow">
   	<c:forEach items="${page.content }" var="product" varStatus="num">
   	 	<div class="col-xs-6 col-sm-3 mycol">
			<div class="thumbnail mythumbnail">
				<a href="${ctx }${baseMapper}/detail?id=${product.id}">
					<img src="${ctxStatic }${product.coverimg }" style="height:100px;width: 100%; "/>
				</a>
				<div class="caption" style="padding: 0px;">
					<h5><font color="red">￥${product.price gt 0?product.price:'面议' }/${product.units }</font>
					<font color="red" style="position: absolute;right: 5px; ">
					</font></h5> 
					${product.name }
				</div>
				<span class="badge mybadge">销量${product.salenum }</span>
			</div>
		</div>
		<c:if test="${num.count % 4 eq 0 }"></div><div class="row myrow"></c:if>	
   	</c:forEach>
 	</div>
 	<!-- 分页 -->
 	<%@ include file="/WEB-INF/views/include/mobilePage.jsp" %>
</div>
<script type="text/javascript">
	function allSselect(obj){
		var chs = document.getElementsByName('search_typeid');
		for(var i=0 ;i<chs.length;i++)
			chs[i].checked = obj.checked;
	}
	function filterBtn(obj){
		if(obj.tag == 1){
			$("#filterDiv").hide();
			obj.tag =0;
			$(obj).find("span:first").removeClass('glyphicon-triangle-top').addClass('glyphicon-triangle-bottom');
		}else{
			$("#filterDiv").show();
			obj.tag =1;
			$(obj).find("span:first").removeClass('glyphicon-triangle-bottom').addClass('glyphicon-triangle-top');
		}
	}
	function orderByFilter(name,order){
		$("#orderfield").attr('name',name).val(order);
		$("#searchFrm").submit();
	}
	function gotoPage(num){
		$("#pageNum").val(num);
		$("#searchFrm").submit();
	}
</script>
<c:set value="3" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>