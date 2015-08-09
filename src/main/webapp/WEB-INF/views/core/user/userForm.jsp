<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#inputForm").validate({
                <c:if test="${empty user.id}">
                rules: {
                    loginName: {remote: "${ctx}${baseMapper}/validate"}
                },
                messages: {
                    account: {remote: "用户登录名已存在"}
                },
                </c:if>
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}${baseMapper}">用户列表</a></li>
    <li class="active"><a href="#">用户<shiro:hasPermission
            name="user:edit">${!empty user.id ? '修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="user:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}/update" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${user.id}"/>
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">登录名:</label>
        <div class="controls">
            <input type="text" maxlength="50" class="required" id="account"
                   name="account" value="${user.account}"
            <c:choose>
            <c:when test="${empty user.account}"> placeholder="请填写数字或者字母"</c:when>
                   <c:otherwise>readonly="readonly" </c:otherwise>
            </c:choose>>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">姓名:</label>

        <div class="controls">
            <input type="text" maxlength="50" class="required" id="userName" name="userName"
                   value="${user.userName}"
            <c:if test="${empty user.userName }"> placeholder="请填写中文"</c:if>>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">密码:</label>

        <div class="controls">
            <input type="password" maxlength="50" class="${empty user.id ?'required':''}" id="plainPwd"
                   name="plainPwd"
                   value="${user.plainPwd}"><c:if test="${!empty user.id}"><span
                class="help-inline">若不修改密码，请留空。</span></c:if>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">用户角色:</label>

        <div class="controls">
            <c:forEach items="${allRoles}" var="role">
                <label class="checkbox inline">
                 <input type="checkbox" class="required" value="${role.id }" name="roleIds"
                <c:if test="${fn:contains(user.roles,role)}"> checked </c:if>>
                        ${role.roleName }
                </label>
            </c:forEach>
        </div>
    </div>

    <div class="form-actions">
        <shiro:hasPermission name="user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                     value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>