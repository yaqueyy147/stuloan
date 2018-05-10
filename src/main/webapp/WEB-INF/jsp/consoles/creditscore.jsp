<%--
  Created by IntelliJ IDEA.
  Sysuser: Administrator
  Date: 2017/1/25 0025
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信用</title>
    <%@include file="common/commonCss.jsp"%>
</head>
<body>
<table id="creditList" class="easyui-datagrid" style="width:100%;height:100%"
       title="学生信用" toolbar="#tb" data-options="
				rownumbers:true,
				singleSelect:true,
				pagination:true,
				pageSize:10">

</table>
<div id="tb">
    <span>账号:</span>
    <input type="text" id="loginname" />
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="doSearch">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" name="auditcredit" data-identity="1">同意认证</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" name="auditcredit" data-identity="0">不同意认证</a>
</div>

<%@include file="common/springUrl.jsp"%>
<%@include file="common/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/consoleJs/creditscore.js"></script>
</body>
</html>
