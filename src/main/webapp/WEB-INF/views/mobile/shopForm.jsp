<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>店铺信息</title>
    <meta name="decorator" content="mobile"/>
</head>
<body>
<div>
<div class="panel panel-default">
	<div class="panel-heading">
	</div>
	<div class="panel-body">
   <form action="${ctx }${baseMapper }/save" method="post" >
    	<input name="id" value="${entity.id }" type="hidden"/>
    	<div class="form-group input-group">
    		<span class="input-group-addon" >店铺名称</span>
  			<input class="form-control" name="shopName" value="${entity.shopName }"/>
    	</div>
    	<div class="form-group input-group">
    		<span class="input-group-addon" > 联 系 人 </span>
  			<input class="form-control" name="telName" value="${entity.telName }"/>
    	</div>
    	<div class="form-group input-group">
    		<span class="input-group-addon" >联系电话</span>
  			<input class="form-control" name="telNum" value="${entity.telNum }"/>
    	</div>
		<div class="form-group input-group">
    		<span class="input-group-addon" >店铺地址</span>
  			<input class="form-control" name="address" value="${entity.address }"/>
    	</div>
    	<div class="form-group input-group">
    		<span class="input-group-addon" >店铺简介</span>
  			<textarea class="form-control" name="descript" >${entity.descript }</textarea>
    	</div>
		<div class="form-group" >
		    <input id="btnSubmit" class="btn btn-primary form-control" 
		    	type="submit" value="保 存 修 改" style="height: 43px;"/>
		</div>
	</form>
	</div>
</div>
<script type="text/javascript">
    	
</script>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>