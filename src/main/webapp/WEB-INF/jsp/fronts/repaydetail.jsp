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
    <title>校园贷--全部待还</title>
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
<div class="container-fluid" style="width: 90%; margin-top: 40px "><!--margin-bottom: 50px-->
    <div class="tab-content container-fluid">
        <div id="userDetail" class=" container-fluid">
            <div class="leftInfo infoDetail col-lg-3 col-md-3 col-sm-3 col-xs-3">
                <div class="col-sm-10 col-md-10 col-md-offset-1">
                    <div class="thumbnail">
                        <a href="javascript:void(0)" id="userPhotoBox">
                            <c:if test="${sysuser.userphoto == null || sysuser.userphoto == '' || sysuser.userphoto == 'null'}">
                                <img src="<%=request.getContextPath()%>/static/images/defaultMan.png" height="150px" width="150px" />
                            </c:if>
                            <c:if test="${sysuser.userphoto != null && sysuser.userphoto != '' && sysuser.userphoto != 'null'}">
                                <img src="${sysuser.userphoto}"  height="150px" width="150px" />
                            </c:if>
                        </a>
                        <%--<img data-src="holder.js/300x300" alt="...">--%>
                        <div class="caption">
                            <h3>${sysuser.username}</h3>

                            <p>${sysuser.province}${sysuser.city}${sysuser.district}</p>

                            <p id="loanopera">
                                <c:choose>
                                    <c:when test="${sysuser.isstuidentity == 1}">
                                        已通过认证&nbsp;&nbsp;
                                        <a href="<%=request.getContextPath()%>/loan/applyloan">申请贷款</a>
                                    </c:when>
                                    <c:when test="${sysuser.isstuidentity == 2}">
                                        认证未通过
                                    </c:when>
                                    <c:when test="${sysuser.isstuidentity == 5}">
                                        <span>已申请认证，请等待审核！</span>
                                    </c:when>
                                    <c:otherwise>
                                    <button type="button" class="btn btn-primary" id="toidentity" >申请认证</button>
                                    </c:otherwise>

                                </c:choose>
                            </p>
                            <p><a href="<%=request.getContextPath()%>/fronts/personalInfo">个人信息</a></p>
                            <p><a href="<%=request.getContextPath()%>/loan/myloan">我的贷款</a></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="regedit-content rightInfo infoDetail col-lg-8 col-md-8 col-sm-8 col-xs-8">
                <div>
                    <button type="button" class="btn btn-primary" id="repaybatch" >批量还款</button>
                </div>
                <div class="table-responsive" id="repaydetailDiv">
                    <table class="table table-hover" id="repaydetailTable" style="background: #ffffff">
                        <thead>
                        <tr>
                            <td><input type="checkbox" id="allrepay" /></td>
                            <td>贷款用途</td>
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
        </div>
    </div>
</div>

<%@include file="common/springUrl.jsp"%>
<%@include file="common/footer.jsp" %>
<%@include file="common/commonJS.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.data.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/frontJs/repaydetail.js"></script>
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
