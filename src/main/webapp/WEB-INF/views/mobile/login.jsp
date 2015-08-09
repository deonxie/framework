<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>登录辅料代购平台</title>
    <meta name="decorator" content="mobile"/>
    <style type="text/css">
    input{height: 43px;}
    </style>
</head>
<body>
<div class="thumbnail">
	<img src="${ctxStatic }/images/logo_zd.png" />
</div>
<div class="container" style="margin-top: -10px;">
	<label><font color="red">${message }</font></label>
    <form action="${ctx }/mobile/login" method="post" >
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
    		<input type="number" name="username" class="required form-control input-lg" 
    		maxlength="20" placeholder="请输入手机号码" value="${username }"/>
    	</div></div>
    	<div class="form-group"><div class="input-group">
    		<span class="input-group-addon"><span class="glyphicon glyphicon-eye-open"></span></span>
    		<input name="password" class="required form-control input-lg" maxlength="20" 
    		placeholder="请输入密码" type="password" value=""/>
    	</div></div>
    	<div class="form-group" style="position: relative;">
    		 <a href="${ctx }/mobile/path/register" >&nbsp;
    			<span class="glyphicon glyphicon-user"></span>注&nbsp;&nbsp;&nbsp;&nbsp;册</a>
    		 <a href="${ctx }/mobile/path/forgetpwd" style="position: absolute;right: 0px; ">
    			<span class="glyphicon glyphicon-question-sign"></span>找回密码！</a>
    	</div>
    	<div class="form-group">
    		<input class="btn btn-warning form-control input-lg" type="submit" 
    		value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" />
    	</div>
    </form>
</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>
    