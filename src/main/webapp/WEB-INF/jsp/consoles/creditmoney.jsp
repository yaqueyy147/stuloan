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
    <title>贷款额度设置</title>
    <%@include file="common/commonCss.jsp"%>
</head>
<body>
<table id="creditmoneyList" class="easyui-datagrid" style="width:100%;height:100%"
       title="贷款额度列表" toolbar="#tb" data-options="
				rownumbers:true,
				singleSelect:true,
				pagination:true,
				pageSize:10">

</table>
<div id="tb">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="toAdd">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="toEdit">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="toDel">删除</a>
</div>
<div id="creditmoneyDialog" class="easyui-dialog" title="额度信息" style="width:100px;height:100px;padding:10px;top: 10%;left: 15%;">
    <div style="padding:10px 40px 20px 40px">
        <form id="creditmoneyForm" method="post">
            <input type="hidden" id="creditmoneyId" name="id" value="" />
            <table cellpadding="5">
                <tr>
                    <td>信用分等级:</td>
                    <td><input class="easyui-validatebox" type="text" id="creditlevel" name="creditlevel" data-options="required:true" /></td>
                </tr>
                <tr>
                    <td>信用分阈值:</td>
                    <td><input class="easyui-validatebox" id="thresholdscore" name="thresholdscore" data-options="required:true" />&nbsp;</td>
                </tr>
                <tr>
                    <td>最大可贷款金额:</td>
                    <td><input class="easyui-validatebox" id="loanmoney" name="loanmoney" data-options="required:true" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
</div>
<%@include file="common/springUrl.jsp"%>
<%@include file="common/commonJs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/consoleJs/creditmoney.js"></script>
</body>
</html>
