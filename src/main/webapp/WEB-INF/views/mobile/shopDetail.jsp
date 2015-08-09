<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>商铺详情</title>
    <meta name="decorator" content="mobile"/>
    <style type="text/css">
     hr{margin: 0px;margin-bottom: 10px;}
    </style>
</head>
<body>
<div class="panel panel-default">
	<div class="panel-heading"></div>
	<div class="panel-body"  style="font-size: 16px;">
	<div>
		<span class="label label-primary">店铺名称:</span>${entity.shopName }
		<legend></legend>
	</div><div>
		<span class="label label-primary">联 系 人:</span>${entity.telName }
		<legend></legend>
	</div><div>
		<span class="label label-primary">联系电话:</span>${entity.telNum }
		<legend></legend>
	</div><div>
		<span class="label label-primary">店铺地址:</span>${entity.address }
		<legend></legend>
	</div><div>
		<span class="label label-primary">店铺简介:</span>${entity.descript }
		<legend></legend>
	</div>
	<a href="${ctx }${baseMapper}/update" class="btn btn-danger form-control">修改</a>
	
</div>
</div>
<c:set value="3" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>
    