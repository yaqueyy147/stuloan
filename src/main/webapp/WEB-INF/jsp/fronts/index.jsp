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
    <title>校园贷款</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <%@include file="common/commonCss.jsp"%>

</head>
<body>
<%@include file="common/header.jsp" %>
<div class="zxcf_menu_wper">
    <div class="zxcf_menu px1000">
        <a href="<%=request.getContextPath()%>/fronts/index" class="zm_cura">首页</a>
        <%--<a href="invest.html">我要投资</a>--%>
        <a href="<%=request.getContextPath()%>/loan/applyloan">我要借款</a>
        <%--<a href="#">实时财务</a>--%>
        <%--<a href="noticelist.html">新手指引</a>--%>
        <%--<a href="#" style="margin-right:0;">关于我们</a>--%>
    </div>
</div>
<!-- end  -->
<div class="zscf_banner_wper">
    <div class="zscf_banner px1000">
        <div class="zscf_box">
            <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<strong>无 需 抵 押</strong>    也 能 借</p>
            <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 最 快  <strong>当 天</strong>  到 账</p>
            <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<strong>超 长</strong>   还 款 期 限</p>
            <c:choose>
                <c:when test="${not empty sysuser}">
                    <a href="<%=request.getContextPath()%>/loan/applyloan" class="btn btn1">我要借款</a><br>
                </c:when>
                <c:otherwise>
                    <a href="<%=request.getContextPath()%>/sign/" class="btn btn1">立即登录</a><br>
                    <a href="<%=request.getContextPath()%>/sign/regeditPersonal" class="btn btn2">立即注册</a>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>
<!-- end banner -->

<!-- footer start -->
<div class="zscf_aboutus_wper">
    <div class="zscf_aboutus px1000 clearfix">
        <div class="zscf_aboutus_l fl">
            <h3>大学生一站式信贷服务平台简称-学信贷（xuexindai.com)  __专注于大学生应急借款，大学生贷款，大学生校园贷款，大学生网贷,大学生贷款平台等服务</h3>
            <p class="con1LP">“学生有信用，信用有价值”是我们一直秉持的理念，在校大学生凭借身份证，学生证即可办理。额度最高10000元，最低仅需0.99%每月，最长分36个月还款，手续简单，下款快。</p>
            <p class="con1LP">目前提供消费分期，助学基金，预借现金等服务，均无任何前期费用。 </p>
            <p class="con1LP">如有业务需求请点击右上角《业务申请》在线申请，申请后将有工作人员提供全程服务。诚招全国校园大使</p>

        </div>
        <!-- end left -->

        <!-- end right -->

    </div>
</div>

<%@include file="common/springUrl.jsp"%>
<%@include file="common/footer.jsp" %>
<%@include file="common/commonJS.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.data.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.min.js"></script>
</body>
</html>
