<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>找回密码</title>
    <meta name="decorator" content="mobile"/>
</head>
<body>
<div class="container-fluid">
<div class="panel panel-default">
    <div class="panel-heading"></div>
    <form action="${ctx }/mobile/user/forgetpwd" method="post">
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
    		<input name="account" class="required form-control input-lg" 
    		type="number" id="longname" maxlength="20" placeholder="请输入手机号码"/>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-check"></span></span>
    		<input class="required form-control input-lg" id="checkcode" name="code" 
    		maxlength="6" placeholder="请输入验证码" type="text"/>
    		<span class="input-group-addon btn-primary" 
    		onclick="startTimer()">获取验证码 <span class="badge" id="codeTimer">0</span></span>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-eye-open"></span></span>
    		<input name="password" id="pwd1" class="required form-control input-lg" maxlength="20"
    		 placeholder="请输入新密码" type="password"/>
    	</div></div>
    	<div class="form-group"><font color="red">${message }</font></div>
    	<div class="form-group">
    		<input class="btn btn-danger form-control input-lg" onclick="return checkPwd();" 
    		type="submit" value="找&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回"/>
    	</div>
    	<div class="form-group">
    		<a class="btn btn-primary form-control input-lg" 
    		href="${ctx }/mobile/login">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</a>
    	</div>
    </form>
</div>
</div>
    <script type="text/javascript">
    	var timer ;
    	function startTimer(){
    		var time = $("#codeTimer").text();
       		var telnum = $("#longname").val();
       		if(time==0){
       		  if(/^\d{11}$/.test(telnum)){
       			$.ajax({url:'${ctx}/mobile/user/sendCode',type:'post',
       				data:{'telNum':telnum,'type':'forget'},success:function(data){
    	   			if(data){
    			   		$("#codeTimer").text(180);
    			   		timer = setInterval(function(){
    			   			var count = parseInt($("#codeTimer").text());
    			   			if(count==0){
    			   				 clearInterval(timer);
    			   			}else
    			   	        	$("#codeTimer").text(count-1);
    				   	},1000);
    	   				
    	   			}else
    	   				alert("短信发送失败");
       			}});
       		  }else{
       			  alert("手机号码不对");
       		  }
       		}
    	}
    	function checkPwd(){
    		if($.trim($("#longname").val())==''){
    			alert("请输入手机号码");
    			$("#longname").focus();
    			return false;
    		}
    		if($.trim($("#checkcode").val())==''){
    			alert("请输入验证码");
    			$("#checkcode").focus();
    			return false;
    		}	
    		var pwd1 = $("#pwd1").val();
    		if(pwd1.length<6){
    			alert("密码最低6位数");
    			$("#pwd1").focus();
    			return false;
    		}
    		return ture;
    	}
    </script>
</body>
</html>
    