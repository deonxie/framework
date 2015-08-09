<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>我的产品</title>
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
	<form action="${ctx }${baseMapper}/mylist" method="post" id="searchFrm">
		<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
	    <div class="input-group" style="margin-top: 5px;">
		    <span class="input-group-addon" onclick="filterBtn(this)">筛选
		    <span class="glyphicon glyphicon-triangle-bottom"></span></span>
		    <input class="form-control" name="search_keyword" placeholder="请输入关键字"
		    style="height: 43px;" value="${page.field.keyword }"/>
		    <span class="input-group-addon" onclick="searchFrm.submit();">
		     <span class="glyphicon glyphicon-search"></span>搜索</span>
	    </div>
	    <div id="filterDiv" class="panel panel-default" style="display: none;">
			<div class="panel-body" style="padding-top: 5px;padding-bottom: 5px;">
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
	    </div>
	</div>
	</form> 
	<c:if test="${!empty error }"><font color="red">${error }</font></c:if>
   	<c:forEach items="${page.content }" var="product" varStatus="num">
   	<div class="row myrow">
   	 	<div class="col-xs-4 col-sm-2 mycol">
			<a href="${ctx }/mobile/product/option/update?id=${product.id}" class="thumbnail mythumbnail">
				<img src="${ctxStatic }${product.coverimg }" />
			</a>
		</div>
		<div class="col-xs-8 col-sm-10 mycol" >
		<div class="pull-left">
			<font color="red">￥${product.price gt 0?product.price:'面议' }/${product.units }</font>
				<font color="red">
				<c:if test="${product.model eq 0 }">现货(${product.sotcknum }${product.units })</c:if>
				<c:if test="${product.model eq 1 }">样板</c:if>
			</font>
		</div>
		<div class="pull-right">
		<c:choose><c:when test="${product.status eq 0}">
			<a class="btn-sm btn-warning" href="${ctx }${baseMapper}/updatesutats?id=${product.id}&status=1">下架</a>
			</c:when><c:otherwise>
			<a class="btn-sm btn-danger" href="${ctx }${baseMapper}/updatesutats?id=${product.id}&status=0"">发布</a>
			</c:otherwise>
			</c:choose>
		</div><br>
		<div class="pull-right"><fmt:formatDate value="${product.createtime }" pattern="yy-MM-dd"/></div>
		<%-- <span class="badge">销量${product.salenum }</span> --%>
		<p>${product.name }</p>
		</div>
	</div>
   	</c:forEach>
   	<!-- 分页 -->
   	<div class="row myrow">
 	<c:choose>
 		<c:when test="${page.pageNum eq 1 && fn:length(page.content) eq page.pageSize}">
 			<div class="col-xs-12 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control" 
	   			onclick="gotoPage(${page.pageNum+1})">下一页</button></div>
	   		</div>
 		</c:when>
 		<c:when test="${page.pageNum gt 1 && fn:length(page.content) eq page.pageSize}">
 			<div class="col-xs-6 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control"
	   			onclick="gotoPage(${page.pageNum-1})">上一页</button></div>
	   		</div>
 			<div class="col-xs-6 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control" 
	   			onclick="gotoPage(${page.pageNum+1})">下一页</button></div>
	   		</div>
 		</c:when>
 		<c:when test="${page.pageNum gt 1 && fn:length(page.content) lt page.pageSize}">
	   		<div class="col-xs-12 mycol">
	   			<div class="form-group"><button class="btn btn-xs btn-warning form-control"
	   			onclick="gotoPage(${page.pageNum-1})">上一页</button></div>
	   		</div>
 		</c:when>
 	</c:choose>
   	</div>
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
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>