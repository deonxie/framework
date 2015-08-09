<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>我的产品</title>
    <meta name="decorator" content="mobile"/>
     <style type="text/css">
       .myrow{margin-left: 0px;margin-right: 0px;border: 1px solid #ccc;border-right: 1px solid #ccc;}
       .mycol{padding: 0px;}
    </style>
</head>
<body>
<div class="container-fluid">
<div class="panel panel-default">
	<form action="${ctx }${baseMapper}/save" method="post" >
		<div class="form-group" style="margin-top: 5px;">
			<input type="text" name="address" class="form-control"
			placeholder="请输入省、市、区、县镇，街道名称门牌号"/>
		</div><div class="form-group">
			<input type="text" name="receiveName" class="form-control" 
			placeholder="收货人" maxlength="20"/>
		</div><div class="form-group">
			<input type="number" name="receiveTel" class="form-control"
			placeholder="收货人电话" maxlength="12"/>
		</div><div class="form-group">
			<input type="submit" class="btn btn-danger form-control" value="保 存"/>
		</div>
	</form> 
</div>
	
   	<c:forEach items="${address }" var="place" varStatus="num">
   	<div class="row myrow"><div class="col-xs-8 mycol">
		 	地址 ${num.count} : ${place.address }<br>收货人: ${place.receiveName }<br>电  话: ${place.receiveTel }
	 	</div><div class="col-xs-4 mycol" style="text-align: right;">
		 	<label><input type="radio" <c:if test="${place.status eq 1 }"> checked="checked"</c:if>>
		 	<a href="${ctx }${baseMapper}/default?id=${place.id}">默认地址</a></label><br><br>
	 		 <a href="${ctx }${baseMapper}/delete?id=${place.id}" 
	 		 onclick="return confirmx('确认要删除吗？', this.href)"> 删 除 </a>
	 </div></div>
   	</c:forEach>
   	
</div>
<script type="text/javascript">
	
</script>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>