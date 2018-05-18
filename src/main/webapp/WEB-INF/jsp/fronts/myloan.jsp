<%--
  Created by IntelliJ IDEA.
  Sysuser: suyx
  Date: 2016/12/18
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>校园贷--我的贷款</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <%@include file="common/commonCss.jsp"%>
    <link href="<%=request.getContextPath()%>/static/css/fronts/personalInfo.css" rel="stylesheet" type="text/css" />
    <style>
        /*html,body {*/
            /*height: 98%;*/
        /*}*/
        <%--body{--%>
            <%--background: url("<%=request.getContextPath()%>/static/images/bg-front.jpg") no-repeat;--%>
            <%--filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";--%>
            <%---moz-background-size:100% 100%;--%>
            <%--background-size:100% 100%;--%>
        <%--}--%>
        .bbtt {
            margin-bottom: 30px;
        }
        table tr td{
            font-size: 12px;
        }
    </style>
</head>
<body>
<%@include file="common/header.jsp" %>
<div class="zxcf_menu_wper">
    <div class="zxcf_menu px1000">
        <a href="<%=request.getContextPath()%>/fronts/index">首页</a>
        <%--<a href="invest.html">我要投资</a>--%>
        <a href="<%=request.getContextPath()%>/loan/applyloan">我要借款</a>
        <%--<a href="javascript:void 0;" id="stuidentity">学生认证</a>--%>
        <%--<a href="javascript:void 0;" id="creditidentity">信用认证</a>--%>
        <%--<a href="<%=request.getContextPath()%>/loan/myloan" class="zm_cura">我的贷款</a>--%>
        <a href="<%=request.getContextPath()%>/fronts/personalInfo?xxx=1" style="margin-right:0;" class="zm_cura">个人中心</a>
        <a href="#" style="margin-right:0;">关于我们</a>
    </div>
</div>
<div class="container-fluid" style="width: 90%; "><!--margin-bottom: 50px-->
    <div class="tab-content container-fluid">
        <div id="userDetail" class=" container-fluid">
            <div class="leftInfo infoDetail col-lg-3 col-md-3 col-sm-3 col-xs-3">
                <div class="menu1">
                    <label class="tt">我的账号</label>
                    <p><a href="<%=request.getContextPath()%>/fronts/personalInfo?xxx=1" style="margin-right:0;">个人资料</a></p>
                    <label class="tt">认证信息</label>
                    <p id="loanopera">
                        <c:choose>
                            <c:when test="${sysuser.isstuidentity == 1}">
                                已通过学生认证&nbsp;&nbsp;
                                <a href="#studentModal" data-toggle="modal" data-target="#studentModal">查看学生信息</a>
                            </c:when>
                            <c:when test="${sysuser.isstuidentity == 2}">
                                学生认证未通过&nbsp;
                                <a href="javascript:void 0;" id="stuidentity" >申请学生认证</a>
                            </c:when>
                            <c:when test="${sysuser.isstuidentity == 5}">
                                <span>已申请学生认证，请等待审核！</span>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void 0;" id="stuidentity" >申请学生认证</a>
                            </c:otherwise>

                        </c:choose>
                    </p>
                    <p id="creditopera">
                        <c:choose>
                            <c:when test="${sysuser.iscreditidentity == 1}">
                                已通过信用认证,您的总信用额度为<span style="color: #00ff00">${sysuser.loanlimit}</span>元
                                ,剩余信用额度为<span style="color: #ff0000">${sysuser.loanlimitleft}</span>元
                                &nbsp;&nbsp;
                                <a href="#creditModal" id="viewcredit" data-toggle="modal" data-target="#creditModal">查看信用信息</a>
                            </c:when>
                            <c:when test="${sysuser.iscreditidentity == 2}">
                                信用认证未通过&nbsp;
                                <a href="javascript:void 0;" id="creditidentity" >申请信用认证</a>
                            </c:when>
                            <c:when test="${sysuser.iscreditidentity == 5}">
                                <span>已申请信用认证，请等待审核！</span>
                            </c:when>
                            <c:otherwise>
                                <a href="#creditidentity" id="creditidentity" >申请信用认证</a>
                            </c:otherwise>

                        </c:choose>
                    </p>
                    <p><a href="<%=request.getContextPath()%>/loan/userphoto">图片认证</a></p>
                    <label class="tt">我的贷款</label>
                    <c:if test="${sysuser.isstuidentity == 1 && sysuser.iscreditidentity == 1 && sysuser.loanlimit > 0 && sysuser.photostate == 1}">
                        <p><a href="<%=request.getContextPath()%>/loan/applyloan" target="_blank">申请贷款</a></p>
                        <p><a href="<%=request.getContextPath()%>/loan/myloan">我的贷款</a></p>
                        <p><a href="<%=request.getContextPath()%>/loan/repaydetail">全部待还</a></p>
                    </c:if>
                </div>
            </div>
            <div class="regedit-content rightInfo infoDetail col-lg-8 col-md-8 col-sm-8 col-xs-8">
                <div class="table-responsive" id="myloanDiv">
                    <table class="table table-hover" id="myloanTable" style="background: #ffffff">
                        <thead>
                            <tr>
                                <td>贷款用途</td>
                                <td>贷款金额</td>
                                <td>贷款时间</td>
                                <td>贷款期限</td>
                                <td>已还金额</td>
                                <td>最后还款时间</td>
                                <td>状态</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>

                </div>
                <div id="pageChanger">

                </div>

            </div>
        </div>
    </div>
</div>

<!--还款明细-->
<div class="modal fade bs-example-modal-lg" id="repaydetailModal" tabindex="-1" role="dialog" aria-labelledby="repaydetailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="repaydetailModalLabel">还款明细</h4>
            </div>
            <div class="modal-body">
                <div class="table-responsive" id="repaydetailDiv">
                    <input type="hidden" id="loanid4detail" />
                    <table class="table table-hover" id="repaydetailTable" style="background: #ffffff">
                        <thead>
                        <tr>
                            <td><input type="checkbox" id="allrepay" /></td>
                            <td>还款期次</td>
                            <td>还款金额</td>
                            <td>预计还款时间</td>
                            <td>实际还款时间</td>
                            <td>状态</td>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>

                </div>
                <div id="pageChanger02">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="torepay">确认还款</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="repayqrcodeModal" tabindex="-1" role="dialog" aria-labelledby="repayqrcodeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="repayqrcodeModalLabel">还款二维码</h4>
            </div>
            <div class="modal-body" style="width: 300px;height: 300px;text-align: center">
                <input type="hidden" id="orderid" />
                <input type="hidden" id="orderno" />
                <img src="" width="100%" id="repayqrcode" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="completerepay">完成还款</button>
            </div>
        </div>
    </div>
</div>
<%@include file="common/springUrl.jsp"%>
<%@include file="common/footer.jsp" %>
<%@include file="common/commonJS.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.data.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/frontJs/myloan.js"></script>
<script type="text/javascript">
    var winHeigth = $(window).height();
    var userId = "${sysuser.id}";
    $(document).ready(function () {

        $("#allrepay").click(function () {
            if($(this).is(":checked")){
                $("input[name='repaychk']").prop("checked",true);
            }else{
                $("input[name='repaychk']").prop("checked",false);
            }
        });

    });

</script>
</body>
</html>
