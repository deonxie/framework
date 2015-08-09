<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>发布求购信息</title>
    <meta name="decorator" content="mobile"/>
    <script src="${ctxStatic}/jweixin-1.0.0.js" type="text/javascript"></script>
     <style type="text/css">
    .imgs{width: 100%;height: 200px;}
    .closeBtn{position: absolute;top: 1px;right: 16px;padding: 10px;background-color: #F5F5F5;border-color: #DDD}
    </style>
</head>
<body>
<div>
<div class="panel panel-default">
	<div class="panel-heading"><font color="red">${errorMsg }</font>
	</div>
	<div class="panel-body">
	<form action="${ctx }${baseMapper }/save" method="post" >
		<input name="id" value="${entity.id }" type="hidden"/>
		<div class="form-group input-group">
	    	<span class="input-group-addon" >类型</span>
	  		<select class="form-control" name="typeid" style="height: 43px;">
	  		<c:forEach items="${types }" var="type">
	  			<option value="${type.id }">${type.typeName }</option>
	  		</c:forEach>
	  		</select>
	    </div>
	    <div class="form-group input-group">
	   		<span class="input-group-addon" ><font color="red">*</font>需求描述</span>
			<textarea name="requirement" class="form-control" 
			placeholder="对供货商的要求，如最晚发货时间等" >${entity.requirement }</textarea>
	   	</div>
	   	<shiro:guest>
	   	<div class="form-group input-group">
	   		<span class="input-group-addon" ><font color="red">*</font>联系电话</span>
			<input type="text" name="telNum" class="form-control" 
				placeholder="联系电话" value="${entity.telNum }">
	   	</div>
	   	</shiro:guest>
	    <div class="form-group"><div class="row" style="margin: 0px;">
	    	<div class="col-xs-6" style="padding: 0px;">
		    	<div class="input-group">
		    		<span class="input-group-addon"><font color="red">*</font>数量</span>
			  		<input type="number" name="requestNum" class="form-control" 
			  			placeholder="输入求购数量" value="${entity.requestNum }" style="height: 43px;"/>
		  		</div>
	    	</div>
	    	<div class="col-xs-6" style="padding: 0px;">
	    		<div class="input-group">
	    			<span class="input-group-addon">单位</span>
		    		<input type="text" name="units" class="form-control" 
			  			placeholder="输入单位" value="${entity.units }" style="height: 43px;"/>		    	
			  	</div>
	    	</div>
	    </div></div>
	    <br>
		<input type="hidden" id="imgName" name="imgName">
	   	<div class="row">
	    	<div class="col-xs-10 col-xs-offset-1" style="position: relative;">
	    		<img id="imgNamesrc" src="" class="imgs" onclick="chooseImg()">
	    		<label class="closeBtn" onclick="removeImgsrc()">&nbsp;&times;&nbsp;</label>
	    	</div>
	   	</div><br>
	    <div class="form-group">
			<input class="btn btn-primary form-control" type="submit" 
				value="发 布 求 购" style="height: 43px;"/>&nbsp;
		</div>
    </form>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var url = window.location.href;
	$.ajax({url:'${ctx}/mobile/weixin/config/jsApiToken',async:false,
		data:{'url':url},success:function(data){
		wx.config({ debug: false, appId:data[0],nonceStr: data[1],
			timestamp: data[2],signature: data[3],
			jsApiList: ['chooseImage', 'previewImage','uploadImage'] });
	}});
});

function chooseImg() {
	wx.chooseImage({success:function(lres){
		if(lres.localIds.length>0){
			var src = lres.localIds[0];
			wx.uploadImage({localId: src,success:function(sres) {
					$("#imgName").val(sres.serverId);
					$("#imgNamesrc").attr('src',src);
				},fail:function(res){
	                alert("上传到微信端失败！");
				}
			});
		}else{
			alert('请选择一张需要上传的图片！')
		}
	}});
}
	
function removeImgsrc(){
	$("#imgName").val('');
	$("#imgNamesrc").attr("src",'${ctxStatic}/images/delete.png');
}
</script>
</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>
    