<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>下单结算</title>
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
<div class="container-fluid">
<br>
	<form action="${ctx }${baseMapper}/save" method="post">
		<c:forEach items="${askids }" var="id">
		<input name="askid" value="${id }" type="hidden">
		</c:forEach>
		<div class="input-group">
			<span class="input-group-addon">留言</span>
			<textarea class="form-control" name="remark" 
					placeholder="如需给商家留言，请在此填写"></textarea>
	 	</div>
	 	<c:choose>
	 		<c:when test="${empty address }">
	 			<font color="red">请先在个人中心添加收货地址,<a href="${ctx }/mobile/address/list">新增地址</a></font>
	 		</c:when><c:otherwise>
	 		<div class="form-group">
	 		<c:forEach items="${address }" var="place" varStatus="num">
	 			<div class="row myrow" style="border:1px solid #ccc;padding: 1px; "><div class="col-xs-8 mycol">
		 		<b>地址 ${num.count} :</b> ${place.address }<br>
		 		<b>收货人: </b>${place.receiveName }<br><b>电  话: </b>${place.receiveTel }
	 			</div><div class="col-xs-4 mycol" style="text-align: right;">
		 			<label><input type="radio" name="addressId" value="${place.id }"
		 			<c:if test="${place.status eq 1 }"> checked="checked"</c:if>>收货地址</label>
	 			</div></div>
	 		</c:forEach>
	 		</div>
		 	<div class="form-group">
		 		<button type="submit" class="btn btn-danger form-control" 
		 		onclick="return checkAddress()">下单</button>
		 	</div>	
	 		</c:otherwise>
	 	</c:choose>
    </form>
<script type="text/javascript">
function checkAddress(){
	var size = $("input[name='addressId']:checked").size();
	if(size>0)
		return true;
	alert('请选择收货地址');
	return false;
}
</script>
</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>