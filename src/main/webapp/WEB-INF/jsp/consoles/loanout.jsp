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
    <title>贷款放款</title>
    <%@include file="common/commonCss.jsp"%>
</head>
<body>
<table id="loanList" class="easyui-datagrid" style="width:100%;height:100%"
       title="贷款列表" toolbar="#tb" data-options="
				rownumbers:true,
				singleSelect:true,
				pagination:true,
				pageSize:10">

</table>
<div id="tb">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" name="audit" data-state="1">批量同意</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" name="audit" data-state="2">批量不同意</a>
</div>
<div id="payqrcodeDialog" class="easyui-dialog" title="放款二维码" style="width:400px;height:200px;padding:10px;top: 10%;left: 10%;text-align: center">

</div>
<div id="repaydetailDialog" class="easyui-dialog" title="还款明细" style="width:400px;height:200px;padding:10px;top: 10%;left: 10%;text-align: center">
    <table id="repayList" class="easyui-datagrid" style="width:100%;height:100%"
           title="还款明细" data-options="
				rownumbers:true,
				singleSelect:true,
				pagination:true,
				pageSize:10">

    </table>
</div>

<%@include file="common/springUrl.jsp"%>
<%@include file="common/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/consoleJs/loanout.js"></script>
</body>
</html>
