<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>发布产品</title>
    <meta name="decorator" content="mobile"/>
    <script src="${ctxStatic}/jweixin-1.0.0.js" type="text/javascript"></script>
    <style type="text/css">
    .imgs{width: 100%;height: 100px;}
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
	    	<span class="input-group-addon" ><font color="red">*</font>类型</span>
	  		<select class="form-control input-lg" name="typeid" >
	  		<c:forEach items="${types }" var="type">
	  			<option value="${type.id }" ${entity.type.id eq type.id ?'selected="selected"':'' }>
	  					${type.typeName }</option>
	  		</c:forEach>
	  		</select>
	    </div>
	    <div class="form-group input-group">
	    	<span class="input-group-addon" ><font color="red">*</font>名称</span>
	  		<input name="name" maxlength="100" class="form-control input-lg" placeholder="产品的名称" 
	  			value="${entity.name }" />
	    </div>
	    <div class="form-group input-group">
	    	<span class="input-group-addon" >材质</span>
	  		<input name="texture" maxlength="200" class="form-control input-lg" placeholder="产品的材质要求" 
	  			value="${entity.texture }" />
	    </div>
	    <div class="form-group input-group">
	   		<span class="input-group-addon" >详情</span>
			<textarea name="descript" class="form-control" placeholder="对产品的服务描述，如最晚发货时间、质检不合格包退等" >${entity.descript }</textarea>
	   	</div>
	    <div class="form-group"><div class="row" style="margin: 0px;">
	    	<div class="col-xs-6" style="padding: 0px;">
		    	<div class="input-group">
		    		<span class="input-group-addon">单价</span>
		  			<input type="number"  name="price" class="form-control input-lg" placeholder="输入产品单价" 
		  				value="${entity.price }" />
		  		</div>
	    	</div>
	    	<div class="col-xs-6" style="padding: 0px;">
	    		<div class="input-group">
	    			<span class="input-group-addon">单位</span>
	    			<input type="text"  name="units" class="form-control input-lg" placeholder="输入单位" 
		  				value="${entity.units }" />
		    		</select>
		    	</div>
	    	</div>
	    </div></div>
		<div class="form-group"><div class="row" style="margin: 0px;">
		    <div class="col-xs-6" style="padding: 0px;">
				<div class="input-group">
			    	<span class="input-group-addon">库存</span>
			  		<input type="number" name="stockNum" class="form-control input-lg" 
			  				placeholder="输入库存数量" value="${entity.stockNum }" />
			    </div>
			</div>
			<div class="col-xs-6" style="padding: 0px;">
			    <div class="input-group">
				    <span class="input-group-addon input-lg" ><label><input type="radio" 
      					name="model" value="0" checked="checked" >现货</label></span>
      				<span class="input-group-addon input-lg"><label><input type="radio" 
				    	name="model" value="1" ${entity.model eq 1 ?'checked="checked"':'' }/>样板</label></span>
			    </div>
			</div>	
    	</div></div>
	    <c:set value="${entity.imgs}" var="imgs"></c:set>
    	<input type="hidden" id="imgs0" name="imgs0" value="${empty imgs[0]?'':'1' }">
    	<input type="hidden" id="imgs1" name="imgs1" value="${empty imgs[1]?'':'1' }">
    	<input type="hidden" id="imgs2" name="imgs2" value="${empty imgs[2]?'':'1' }">
	    <div class="row">
	    	<div class="col-xs-6" style="position: relative;">
	    		<img id="imgsrc0" src="${ctxStatic }${imgs[0]}" class="imgs" onclick="chooseImg('0')">
	    		<label class="closeBtn" onclick="removeImgsrc('0')">&nbsp;&times;&nbsp;</label>
	    	</div>
	    	<div class="col-xs-6" style="position: relative;">
	    		<img id="imgsrc1" src="${ctxStatic }${imgs[1]}" class="imgs" onclick="chooseImg('1')">
	    		<label class="closeBtn" onclick="removeImgsrc('1')">&nbsp;&times;&nbsp;</label>
	    	</div>
	    </div><br>
	    <div class="row">
	    	<div class="col-xs-6" style="position: relative;">
	    		<img id="imgsrc2" src="${ctxStatic }${imgs[2]}" class="imgs" onclick="chooseImg('2')">
	    		<label class="closeBtn" onclick="removeImgsrc('2')">&nbsp;&times;&nbsp;</label>
	    	</div>
	    </div><br>
	    <div class="form-group">
			<input class="btn btn-primary form-control input-lg" type="submit" 
				value="发 布 产 品" onclick="$(this).attr('disabled',true);"/>&nbsp;
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

function chooseImg(num){
	wx.chooseImage({success:function(lres){
		if(lres.localIds.length>0){
			var src = lres.localIds[0];
			wx.uploadImage({localId: src,success:function(sres) {
					$("#imgs"+num).val(sres.serverId);
					$("#imgsrc"+num).attr('src',src);
				},fail:function(res){
					alert("上传到微信端失败！");
				}
			});
		}else{
			alert('请选择一张需要上传的图片！')
		}
	}});
}

function removeImgsrc(num){
	$("#imgs"+num).val('');
	$("#imgsrc"+num).attr("src",'${ctxStatic}/images/delete.png');
}
</script>
</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>
    