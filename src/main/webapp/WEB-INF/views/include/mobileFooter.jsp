<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-default navbar-fixed-bottom">
	<div class="container-fluid" style="text-align: center;">
		<div class="row">
			<div class="col-xs-4 col-sm-2">
				<a href="${ctx }/mobile" style="color: ${navTag eq '1'?'purple':'black'};">
				<i class="glyphicon glyphicon-home glyphicon-white"></i><br>主页</a></div>
			<div class="col-xs-4 col-sm-2">
				<a href="${ctx }/mobile/product" style="color: ${navTag eq '3'?'purple':'black'};">
				<i class="glyphicon glyphicon-shopping-cart"></i><br>产品</a></div>
			<div class="col-xs-4 col-sm-2">
				<a href="${ctx }/mobile/usercenter" style="color: ${navTag eq '4'?'purple':'black'}">
				<i class="glyphicon glyphicon-user"></i><br> 我 </a></div>
			<%-- <div class="col-xs-3 col-sm-2">
				<a href="${ctx }/mobile/askbuyinfo" style="color: ${navTag eq '2'?'purple':'black'};">
				<i class="glyphicon glyphicon-cog"></i><br>系统</a></div> --%>
		</div>
	</div>
</nav>