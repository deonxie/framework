<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>辅料代购商家管理</title>
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
    <li class="active"><a href="#">辅料代购商家修改</a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}/save" method="post" class="form-horizontal">
	<input type="hidden" name="id" value="${entity.id }">
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">店铺名称:</label>
        <div class="controls">
            <input type="text" maxlength="50" class="required form-control" id="shopName"
            name="shopName" value="${entity.shopName}" placeholder="请填写店铺名称">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">联系电话:</label>
        <div class="controls">
            <input type="text" maxlength="50" class="required form-control" id="telNum" 
            name="telNum" value="${entity.telNum}" placeholder="联系电话">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">其他联系方式:</label>
        <div class="controls">
			<textarea name="otherContact" class="form-control" 
			placeholder="如：qq：99990,微信号：xxx等">${entity.otherContact }</textarea>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">联系人:</label>
        <div class="controls">
            <input type="text" maxlength="50" id="telName" class="required form-control"
            name="telName" value="${entity.telName}" placeholder="请填写联系人姓名">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">地址:</label>
        <div class="controls">
        	<textarea name="address" class="required form-control" 
        	placeholder="如：qq：99990,微信号：xxx等">${entity.address}</textarea>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">状态:</label>
        <div class="controls">
        	<label  class="checkbox inline">
            <input type="radio" id="status" class="required" name="status" value="-1" checked="checked">禁用
        	</label><label  class="checkbox inline">
            <input type="radio" id="status" class="required" name="status" value="1" 
            ${entity.status eq 1?'checked="checked"':''}>可用
        	</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">店铺描述:</label>
        <div class="controls">
        <textarea name="descript" class="form-control" placeholder="店铺描述">${entity.descript }</textarea>
        </div>
    </div>
    <div class="form-actions pull-right">
    <shiro:hasPermission name="shop:edit">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
    </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>