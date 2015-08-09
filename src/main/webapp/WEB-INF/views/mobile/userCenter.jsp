<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>个人中心</title>
    <meta name="decorator" content="mobile"/>
    <style type="text/css">
	a:HOVER { text-decoration: none; }
	legend{font-size: 15px;margin-bottom: 5px;}
	.img-rounded{width: 50px;height: 50px;}
    </style>
</head>
<body>
<div class="container-fluid">
<div class="panel panel-default">
	<div class="panel-heading" >
		<div class="row">
			<div class="col-xs-8">
				<fieldset>
					<legend>名称:&nbsp;&nbsp;${entity.userName }</legend>
					<legend>电话:&nbsp;&nbsp;${entity.telPhone }</legend>
					<legend>地址:&nbsp;&nbsp;${entity.address }</legend>
				</fieldset>
			</div>
			<div class="col-xs-4">
				<a href="" class="thumbnail" style="margin-bottom:0px;">
					<c:choose><c:when test="${!empty entity.headImg }">
						<img alt="头像" src="${ctxStatic }${entity.headImg}" />
					</c:when><c:otherwise>
						<img alt="头像" src="${ctxStatic }${userImg}" />
					</c:otherwise>
					</c:choose>
				</a>
			</div>
		</div>
	</div>
<!-- items config -->
	<div class="panel-body">
<shiro:hasPermission name="shop:isShop">
  		<div class="row">
	  		<%-- <div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/shop/option/detail" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/262.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">店铺信息</div>
	  			</a>
	  		</div> --%>
	  		<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/product/option/update" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/133.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">上传产品</div>
	  			</a>
	  		</div>
	  		<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/product/mylist" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/139.png" class="img-rounded"/>
		  			<div class="caption" style="text-align: center;padding: 0px;">产品管理</div>
	  			</a>
	  		</div>
	  		<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/shoporder" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/chart.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">销售订单</div>
	  			</a>
	  		</div>
  		<!-- </div> -->
</shiro:hasPermission>
		<!-- <div class="row"> -->
			<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/askbuyinfo/add" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/248.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">发布求购</div>
	  			</a>
	  		</div>
			<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/askbuyinfo/list" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/40.png" class="img-rounded"/>
		  			<div class="caption" style="text-align: center;padding: 0px;">求购管理</div>
	  			</a>
	  		</div>
	  		<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/order/user" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/chart.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">我的订单</div>
	  			</a>
	  		</div>
  			<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/address/list" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/home.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">收货地址</div>
	  			</a>
	  		</div>
  		<!-- </div>
  		<div class="row"> -->
  			<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/store/list/product" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/130.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">我的收藏</div>
	  			</a>
	  		</div>
	  		<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="#" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/5.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">平台意见</div>
	  			</a>
	  		</div>				  		
	  		<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/logout" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/255.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">退出</div>
	  			</a>
	  		</div>
  		<!-- </div>
  		
  		系统服务功能
  		<div class="row"> -->
  		<shiro:hasPermission name="ask:matching">
	  		<div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/matchaskbuy/list" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/260.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">匹配求购</div>
	  			</a>
	  		</div>
		</shiro:hasPermission>
	  		<%-- <div class="col-xs-3" style="padding-left: 2px;padding-right: 2px;">
  				<a href="${ctx }/mobile/product/types" class="thumbnail">
		  			<img alt="" src="${ctxStatic }/images/items/133.png" class="img-rounded"/>
		  			<div  class="caption" style="text-align: center;padding: 0px;">产品类型</div>
	  			</a>
	  		</div> --%>
  		</div>
  		
	</div>	
</div>
</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>