<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>系统配置管理</title>
    <meta name="decorator" content="default"/>
        
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}${baseMapper}">系统配置列表</a></li>
    <li class="active"><a href="#">配置操作</a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}/save" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${entity.id}"/>
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">key:</label>
        <div class="controls">
        	<c:choose>
        		<c:when test="${empty entity.id }">
            	<input type="text" class="required" id="key"
                   name="key" value="${entity.key}" placeholder="请填写需要配置的key">
        		</c:when><c:otherwise>
        			<span>${entity.key}</span>
        		</c:otherwise>
        	</c:choose>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">值:</label>
        <div class="controls">
            <input type="text" class="required" 
                   name="value" value="${entity.value}" placeholder="请填写需要配置的值">
        </div>
    </div>

	<div class="control-group">
        <label class="control-label">描述:</label>
        <div class="controls">
        <textarea  class="" id="description" name="description"
        placeholder="描述">${entity.description}</textarea>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注:</label>
        <div class="controls">
            <textarea placeholder="若是图片类型，则对应图片点击后的超链接地址"
            name="remark">${entity.remark}</textarea>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">状态:</label>
        <div class="controls">
            <label class="checkbox label-inline">
            <input type="radio" name="status" value="0" checked="checked">可用</label>
            <label class="checkbox label-inline">
            <input type="radio" name="status" value="1" ${entity.status eq 1?'checked="checked"':'' }>禁用</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">排序:</label>
        <div class="controls">
            <input type="number" name="index" 
            value="${empty entity.index ?0: entity.index}" placeholder="排序">
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="config:edit">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>

<div class="list-group">
	<c:forEach items="${constKey }" var="map" varStatus="num">
	<div class="list-group-item" onclick="maskconfig(${num.index })">
		<p><label><input type="radio" id="mastradio${num.index }" name="mastconf">
		<font id="mastkey${num.index }">${map.key }</font> </label></p>
		<font id="mastdesc${num.index }">${map.value }</font>
	</div>
	</c:forEach>
</div>
<script type="text/javascript">
function maskconfig(num){
	$("#mastradio"+num).attr('checked',true);
	$("#key").val($("#mastkey"+num).text());
	$("#description").val($("#mastdesc"+num).text());
}
</script>
</body>
</html>