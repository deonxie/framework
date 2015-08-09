<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
    <title><sitemesh:title/></title>
    <%@include file="../include/mobile.jsp" %>
    <sitemesh:head/>
</head>
<body style="padding-bottom: 55px;">
<sitemesh:body/>
<style type="text/css">
    a:HOVER {text-decoration: none;}
    .active{color: purple;}
</style>
</body>
</html>
