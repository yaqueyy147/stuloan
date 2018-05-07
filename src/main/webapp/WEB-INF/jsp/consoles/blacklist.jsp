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
    <title>黑名单</title>
    <%@include file="common/commonCss.jsp"%>
</head>
<body>
<table id="blackList" class="easyui-datagrid" style="width:100%;height:100%"
       title="黑名单列表" data-options="
				rownumbers:true,
				singleSelect:true,
				pagination:true,
				pageSize:10">

</table>

<div id="repaydetailDialog" class="easyui-dialog" title="逾期明细" style="width:400px;height:200px;padding:10px;top: 10%;left: 10%;text-align: center">
    <table id="repayList" class="easyui-datagrid" style="width:100%;height:100%"
           title="逾期明细" data-options="
				rownumbers:true,
				singleSelect:true,
				pagination:true,
				pageSize:10">

    </table>
</div>

<%@include file="common/springUrl.jsp"%>
<%@include file="common/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/consoleJs/blacklist.js"></script>
</body>
</html>
