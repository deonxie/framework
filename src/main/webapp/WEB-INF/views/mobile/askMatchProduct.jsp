<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>处理求购</title>
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
		<legend><font color="red">${entity.requestNum}/${entity.units }</font> 
		<fmt:formatDate value="${entity.createTime }" pattern="yy年MM月dd"/>
	  	<span class="glyphicon glyphicon-earphone" style="color: purple;"></span>：${entity.ownUser.telPhone}</span>
		</legend>
		${entity.requirement }
		<p class="text-danger">${entity.remark }</p>
	</fieldset>
	
	<c:forEach items="${mapps }" var="mapp">
	<div class="row">
		<div class="col-xs-4 ">
			<a class="thumbnail mythumbnail"><img src="${ctxStatic }${mapp.product.coverimg }" style="height: 80px;"></a>
		</div><div class="col-xs-8">
			<div class="input-group">
			<a>数量<span class="badge">${mapp.bookNum}</span></a>
			<a>单价<span class="badge">${mapp.price}元</span></a>
			<a>总价<span class="badge">${mapp.amount}元</span></a>
			<c:if test="${entity.status ne 2}">
			<a href="${ctx }${baseMapper}/deleteMatching?id=${mapp.id}&aid=${entity.id}" 
			class="btn btn-xs btn-primary">移除</a>
			</c:if>
			</div>
			${mapp.product.name }<p>${mapp.remark}</p>
		</div>
	</div>
	</c:forEach>
	<!-- 完成产品匹配 -->
<c:if test="${entity.status eq 0}">
	<div class="well well-sm">
		<form action="${ctx }${baseMapper}/finishMatching" method="post">
			<input type="hidden" name="aid" value="${entity.id}">
			<div class="input-group">	
				<span class="input-group-addon">定金</span>
				<input type="number" name="handsel" class="form-control" placeholder="定金"/>
			</div><div class="input-group">
				<span class="input-group-addon">备注</span>
				<textarea name="remark" class="form-control" ></textarea>
			</div>
			<input type="submit" value="完成匹配" class="btn btn-warning form-control">
		</form>
	</div>

	<!-- 搜索产品表单 -->
	<div class="well well-sm" style="margin-bottom: -4px;">
		<form id="searchFrm" action="${ctx }${baseMapper}/matching" method="post">
 			<input type="hidden" name="search_typeid"/>
 			<input type="hidden" name="id" value="${entity.id }"/>
	 		<div class="input-group">
	 			<label class="input-group-addon">
	 			<input type="checkbox" name="search_typename" value="1"
	 				${empty page.field.typename?'':'checked="checked"' }/>产品类型</label>
	 			<label class="input-group-addon">
	 			<input type="checkbox" name="search_texture" value="1"
	 				${empty page.field.texture?'':'checked="checked"' }/>产品材质</label>
	 			<label class="input-group-addon">
	 			<input type="checkbox" name="search_descript" value="1"
	 				${empty page.field.descript?'':'checked="checked"' }/>产品描述</label>
	 		</div><div class="input-group">
		 		<input type="text" name="search_keyword" placeholder="输入匹配求购的关键字🔍" 
		 			value="${page.field.keyword }" class="form-control"/>
		 		<span class="input-group-addon" onclick="searchFrm.submit();">搜索产品</span>
	 		</div>
		</form>
	</div>
	<!-- 产品列表 -->
<c:if test="${!empty page.content }">
<div class="well well-sm">
 	<c:set value="" var="shop"/>
 	<c:forEach items="${page.content }" var="prodcut" varStatus="num">
 		<c:choose>
 		<c:when test="${prodcut.shopname eq shop}">
	 		<div class="row" onclick="chooseProduct('${prodcut.id}','${prodcut.name}')">
				<div class="col-xs-4 "><a class="thumbnail mythumbnail">
					<img src="${ctxStatic }${prodcut.coverimg}" style="height: 80px;"></a>
				</div><div class="col-xs-8">
					${prodcut.name }${prodcut.texture }
				</div>
			</div>
 		</c:when>
 		<c:otherwise>
 			<div class="well well-sm" style="margin-bottom: 0px;">
	 		<fieldset><legend>商铺名称：${prodcut.shopname }</legend>
	 		${prodcut.telname }:&nbsp;&nbsp;${prodcut.telnum }&nbsp;&nbsp;${prodcut.address }<br>
	 		${prodcut.othercontact }
	 		</fieldset> 
			</div>
			<div class="row" onclick="chooseProduct('${prodcut.id}','${prodcut.name}')">
				<div class="col-xs-4 "><a class="thumbnail mythumbnail">
					<img src="${ctxStatic }${prodcut.coverimg}" style="height: 80px;"></a>
				</div><div class="col-xs-8">
					${prodcut.name }<br>${prodcut.texture }<br>${prodcut.remark }
				</div>
			</div>
 		</c:otherwise>
 		</c:choose>
 		<c:set value="${prodcut.shopname }" var="shop" />
 	</c:forEach>
 	<!-- 分页 -->
 	 <c:choose>
 		<c:when test="${page.pageNum eq 1 && fn:length(page.content) eq page.pageSize}">
		   	<div class="form-group">
		   		<button class="form-control" onclick="gotoPage(${page.pageNum+1})">下一页</button>
		   	</div>
 		</c:when><c:when test="${page.pageNum gt 1 && fn:length(page.content) eq page.pageSize}"> --%>
	   		<div class="form-group input-group">
	   			<span class="input-group-addon"onclick="gotoPage(${page.pageNum-1})" >上一页</span>
	   			<span class="input-group-addon" style="border-left: 1px solid;"
	   				onclick="gotoPage(${page.pageNum+1})">下一页</span>
	   		</div>
 		</c:when><c:when test="${page.pageNum gt 1 && fn:length(page.content) lt page.pageSize}">
	   		<div class="form-group">
	   			<button class="form-control" onclick="gotoPage(${page.pageNum-1})">上一页</button>
	   		</div>
 		</c:when>
 	</c:choose>
 	</div>
	<div class="well well-sm">
		<form id="saveFrm" action="${ctx }${baseMapper}/saveMatching" method="post">
			<input type="hidden" name="pid" id="pid"/>
			<input type="hidden" name="aid" value="${entity.id }"/>
			<div class="input-group">
				<span class="input-group-addon">产品</span>
				<input class="form-control" id="pname" disabled="disabled" placeholder="单击上面产品列表中产品既可选取"/>
			</div><div class="input-group">
				<span class="input-group-addon">数量</span>
				<input type="number" id="bookNum" name="bookNum" class="form-control" value="0" placeholder="预定数量"/>
			</div><div class="input-group">	
				<span class="input-group-addon">单价</span>
				<input type="number" id="price" name="price" class="form-control" value="0" placeholder="预定价格"/>
				<span class="input-group-addon">总价</span>
				<input type="number" id="amount" name="amount" class="form-control" value="0" placeholder="总价"/>
			</div><div class="input-group">
				<span class="input-group-addon">备注</span>
				<textarea name="remark" class="form-control" ></textarea>
			</div>
			<input type="button" onclick="dosubmit()" value="确  定" class="btn btn-danger form-control" 
				style="margin-top: 10px;"/>
			<c:if test="${!empty message }"><p class="text-danger">${message }</p></c:if>
		</form>
 	</div>
</c:if>
</c:if>
</div>
<script type="text/javascript">
	function dosubmit(){
		if($("#pid").val()==''){
			alert('请在产品列表中点击选取产品');
			return false;
		}if($("#bookNum").val()<1){
			alert('订购数量不能小于1');
			return false;
		}if($("#price").val()<1 && $("#amount").val()<1){
			alert('单价、总价至少填写一个');
			return false;
		}
		return saveFrm.submit();
	}
	function chooseProduct(id,name){
		$("#pid").val(id);
		$("#pname").val(name);
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
    