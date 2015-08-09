<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>辅料代购用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}${baseMapper}">辅料代购用户列表</a></li>
    <li class="active"><a href="#">辅料代购用户添加</a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}/tosave" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${entity.id }">
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">登录名:</label>
        <div class="controls">
            <input type="text" maxlength="50" class="required form-control" id="account"
            name="account" value="${entity.account}" placeholder="请填写手机号码">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            <input type="text" maxlength="50" class="required form-control" id="userName" 
            name="userName" value="${entity.userName}" placeholder="请填写中文">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">联系电话:</label>
        <div class="controls">
            <input type="text" maxlength="50" id="telPhone" class="form-control"
            name="telPhone" value="${entity.telPhone}" placeholder="请填写联系电话">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">地址:</label>
        <div class="controls">
            <input type="text" maxlength="50" id="address" class="form-control"
            name="address" value="${entity.address}" placeholder="请填写联系地址">
        </div>
    </div>
    <div class="form-actions">
    <shiro:hasPermission name="fluser:edit">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
    </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>