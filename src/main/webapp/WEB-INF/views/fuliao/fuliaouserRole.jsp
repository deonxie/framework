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
<br/>
<form id="inputForm" action="${ctx}${baseMapper}/saverole" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${entity.id }">
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            ${entity.userName}
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">权限:</label>
        <div class="controls">
        	<c:forEach items="${allPermissisons }" var="perm">
            <label class="checkbox inline">
            	<input type="checkbox" value="${perm.value }" name="permissions"
            	${fn:contains(entity.permissions,perm.value) ?'checked="checked"':''}/>${perm.displayName }</label>
        	</c:forEach>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>