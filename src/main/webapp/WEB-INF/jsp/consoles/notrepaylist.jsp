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
<table id="notrepayList" class="easyui-datagrid" style="width:100%;height:100%"
       title="未还列表" toolbar="#tb" data-options="
				rownumbers:true,
				singleSelect:true,
				pagination:true,
				pageSize:10">

</table>
<div id="tb">
    <%--<span>账号:</span>--%>
    <%--<input type="text" id="loginname" />--%>
    <span>贷款人:</span>
    <input type="text" id="stuname" />
    <span>状态:</span>
    <select id="isrepay">
        <option value="">全部</option>
        <option value="1">已还款</option>
        <option value="0">未还款</option>
    </select>
    <span>应还款时间:</span>
    <input type="text" id="begindate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
    <input type="text" id="enddate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="doSearch">查询</a>
</div>
<%@include file="common/springUrl.jsp"%>
<%@include file="common/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/consoleJs/notrepaylist.js"></script>
</body>
</html>
