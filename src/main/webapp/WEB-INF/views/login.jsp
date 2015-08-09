<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>登录</title>
    <meta name="decorator" content="default"/>
    <%@ include file="/WEB-INF/views/include/head.jsp" %>
  <link rel="stylesheet" href="${ctxStatic}/common/typica-login.css">
    <style type="text/css">
        .control-group{border-bottom:0px;}
        body{background-image: url("${ctxStatic}/images/bg1.jpg");}
    </style>
    <script src="${ctxStatic}/common/backstretch.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#loginForm").validate({
                messages: {
                    username: {required: "请填写用户名."},password: {required: "请填写密码."}
                },
                errorLabelContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        });
        // 如果在框架中，则跳转刷新上级页面
        if(self.frameElement && self.frameElement.tagName=="IFRAME"){
            parent.location.reload();
        }
    </script>
</head>
<body>

<div class="container">
    <div id="login-wraper">
        <form id="loginForm"  class="form login-form" action="${ctx}/login" method="post">
            <legend><span style="color:#08c;">系统登陆</span></legend>
            <div class="body" style="text-align: center;">
                <div class="form-group" style="text-align: center;">
                        <input type="text" id="username" name="username" class="required form-control" 
                        value="${username}" placeholder="登录名" style="width: 60%;margin: auto;">
                </div>

                <div class="form-group" style="text-align: center;margin-bottom: 0px;">
                        <input type="password" id="password" name="password" class="required form-control"
                         placeholder="密码" style="width: 60%;margin: auto;"/>
                </div>
                 <c:if test="${isValidateCodeLogin}"><div class="validateCode">
                    <label for="password">密　码：</label>
                    <tags:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
                </div></c:if> 
            </div>
                <div class="footer">
                <label class="checkbox inline">
                    <input type="checkbox" id="rememberMe" name="rememberMe"> <span style="color:#08c;">记住我</span>
                </label>
                <input class="btn btn-primary" type="submit" value="登 录"/>
            </div>
        </form>
    </div>
</div>
<footer class="transparent navbar-fixed-bottom">
    Copyright &copy; 2012-2014 - Powered By <a href="#" target="_blank">ThinkJoy_ZUH</a>
</footer>
</body>
</html>