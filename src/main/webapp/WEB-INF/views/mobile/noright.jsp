<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>辅料代购平台</title>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <meta name="decorator" content="mobile"/>
</head>
<body>
<div class="panel panel-default">
	<div class="panel-heading">
	</div>
	<div class="panel-body">
		<font color="red">您没有权限，请联系平台管理员。</font>
	</div>
</div>
<c:set value="4" var="navTag"/>
<%@ include file="/WEB-INF/views/include/mobileFooter.jsp" %>
</body>
</html>