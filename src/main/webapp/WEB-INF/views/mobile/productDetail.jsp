<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>产品详情</title>
    <meta name="decorator" content="mobile"/>
    <style type="text/css">
     .myrow{margin-left: 0px;margin-right: 0px;}
     .mycol{padding: 0px;}
     .mythumbnail{margin-bottom: 0px;border-radius:0px;font-size: 12px;position: relative;}
     .mycaption{padding: 4px;}
     .mybadge{position: absolute;right: 5px;top: 5px;}
     h5{margin: 0px;}
     .mylabel{padding: 4px;border: 1px solid #ccc;}
    legend{margin-bottom: 10px;position: relative;font-size:16px;}
    .labTitle{color:#08c;padding-right:5px;}
    </style>
</head>
<body>
<div class="container-fluid" >
    <div id="myCarousel" class="carousel slide" >
      	<ol class="carousel-indicators">
      	<c:forEach items="${entity.imgs }" var="img" varStatus="num">
			<li data-target="#myCarousel" data-slide-to="${num.index }" ${num.index eq 0 ?'class="active"':''}></li>
      	</c:forEach>
	   </ol>
	   <div class="carousel-inner" >
	   	<c:forEach items="${entity.imgs }" var="img" varStatus="num">
			<div class="${num.index eq 0 ?'active ':''}item"><img src="${ctxStatic }${img}"  style="width:100%; height: 250px;"/></div>
		</c:forEach>
		</div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    <span class="sr-only">上一张</span>
	  </a>
	  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">下一张</span>
	  </a>
  </div> 
  <fieldset>
  	<legend>${entity.name }</legend>
  	<legend>
  		<font color="red">￥${entity.price gt 0?entity.price:'面议'}/${entity.units }</font> 
  		<div class="pull-right">
		  	<fmt:formatDate value="${entity.createTime }" pattern="yy年MM月dd"/>
		  	 <span class="badge" style="font-size: 14px;">库存(${entity.stockNum })</span>
		</div>
  	</legend>
  	<legend>
  		<div class="input-group">
  			<span class="input-group-addon" onclick="storeProduct()" style="color: red;"><i 
  			class="glyphicon glyphicon-heart" ></i>收藏</span>
  			<input type="number" id="askNum" class="form-control" placeholder="求购数量" style="height: 43px;">
  			<span class="input-group-addon" style="color: #FFF;background-color: #C72F29;border-color: #C72F29;"
  			onclick="askforbuy()">求购</span>
  		</div>
  		<div id="guest" class="panel panel-default" style="display: none;" >
  			<div class="panel-heading" style="color:red;text-align: center;">请先登录！</div>
			<div class="form-group">
			  	<input type="text" class="form-control" placeholder="账号" id="login"/>
			</div><div class="form-group">
			  	<input type="password" class="form-control" placeholder="密码" id="password"/>
			</div>
			<div class="form-group">
			  	<input type="button" class="btn btn-warning form-control" value="登录" onclick="submit()"/>
			</div>
  		</div>
  	</legend>
  	<legend><label class="labTitle">产品材质:</label>${entity.texture }</legend>
  	<legend><label class="labTitle">产品简介:</label>${entity.descript }</legend>
  </fieldset>
 
  <fieldset>
  	<legend>推荐产品</legend>
  	<div class="row myrow">
   	<c:forEach items="${electProduct }" var="product" varStatus="num">
   	 	<div class="col-xs-6 col-sm-3 mycol">
			<div class="thumbnail mythumbnail">
				<a href="${ctx }${baseMapper}/detail?id=${product.id}">
					<img src="${ctxStatic }${product.coverimg }" style="height: 100px;width: 100%;"/>
				</a>
				<div class="caption" style="padding: 0px;">
					<h5><font color="red">￥${product.price gt 0?product.price:'面议' }/${product.units }</font>
					${product.name }
				</div>
				<span class="badge mybadge">销量${product.salenum }</span>
			</div>
		</div>
		<c:if test="${num.count % 4 eq 0 }"></div><div class="row myrow"></c:if>	
   	</c:forEach>
  </fieldset>
</div>
<script type="text/javascript">
	function submit(){
		var name = $("#login").val();
		var pwd = $("#password").val();
		$.ajax({url:'${ctx}/mobile/ajaxlogin',async:true,dataType:'json',
			type:'POST',data:{'username':name,'password':pwd},
			success:function(data){
				if(data) $("#guest").hide();
				else alert('登录失败，请检查账号和密码');
			}});
	}
	function storeProduct(){
		$.ajax({url:'${ctx}/mobile/store/product?pid=${entity.id}',async:true,
			dataType:'json',type:'POST',
			success:function(data){
				if(data[0]=='true'){
					alert(data[1]);
				}else{
					alert(data[1]);
					if(data[1]=='请先登录平台')
						$("#guest").show();
				}
			}});
	}
	
	function askforbuy(){
		var num = parseInt($("#askNum").val());
		if(num>0){
			$.ajax({url:'${ctx}/mobile/askbuyinfo/buyProduct',async:true,type:'post',
				dataType:'json',data:{'pid':'${entity.id}','count':num},
				success:function(data){
					if(data[0]=='true'){
						alert('已加入到您的求购列表中');
					}else{
						alert(data[1]);
						if(data[1]=='未登录')
							$("#guest").show();
					}
				}});
		}else{
			alert('请输入求购数量');
		}
	}
</script>
<c:set value="3" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>
    