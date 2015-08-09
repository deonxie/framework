<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>微信安全支付</title>
    <meta name="decorator" content="mobile"/>
    <script src="${ctxStatic}/jweixin-1.0.0.js" type="text/javascript"></script>
</head>
<body>
<div class="panel panel-default">
	<div class="panel-heading">
	</div>
	<div class="panel-body">
		<c:choose>
			<c:when test="${params[0] eq 'false' }">
				<font color="red">${params[1] }</font>
			</c:when><c:otherwise>
				<script type="text/javascript">
				 $(document).ready(function(){
				     var url = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port;
				     document.addEventListener('WeixinJSBridgeReady',
				                function onBridgeReady() {
				                    WeixinJSBridge.invoke('getBrandWCPayRequest', {
				                        "appId": "${params[0]}","timeStamp":"${params[1]}","nonceStr":"${params[2]}",
					                  	"package":"${params[3]}","signType":"${params[4]}", "paySign": "${params[5]}"
				                    }, function (res) {
											alert(res.err_msg);
				                     		if (res.err_msg == "get_brand_wcpay_request:ok") {
				                     			$.ajax({url:'${ctx}/mobile/payfor/paystatus',
				                     					data:{'id':'${orderid}','option':'${option}'},ansyc:true});
				                         		alert("微信支付成功!");
				                    		}else if(res.err_msg == "get_brand_wcpay_request:cancel"){
						                         alert("用户取消支付!");
				                       		}else{
				                          		alert("微信支付失败!");
				                        	}
				                    })
				                }, false);
				});
				 
				</script>
			</c:otherwise>
		</c:choose>
	</div>
</div>

</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>
    