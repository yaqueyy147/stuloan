<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .userInfo{
        float: right;
        margin-right: 0px;
    }
</style>
<div class="zxcf_top_wper">
    <div class="zxcf_top px1000 clearfix">
        <div class="zxcf_top_r fr">
            <a href="<%=request.getContextPath()%>/fronts/index">首页</a><span>|</span>
            <c:choose>
                <c:when test="${not empty sysuser}">
                    【欢迎您，
                    <a href="<%=request.getContextPath()%>/fronts/personalInfo?xxx=1">
                            ${sysuser.loginname}</a>
                    <span>|</span>
                    <a href="<%=request.getContextPath()%>/sign/logout">退出</a>
                    】
                </c:when>
                <c:otherwise>
                    【<a href="<%=request.getContextPath()%>/sign/" class="curspan">立即登录</a>
                    <span>|</span>
                    <a href="<%=request.getContextPath()%>/sign/regeditPersonal">免费注册</a>
                    】
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%--<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="min-height: 0;">--%>
    <%--<div class="container">--%>
        <%--<div class="userInfo">--%>
            <%--<c:choose>--%>
                <%--<c:when test="${not empty userInfo}">--%>
                    <%--【欢迎您，--%>
                    <%--<a href="<%=request.getContextPath()%>/fronts/personalInfo?xxx=1">--%>
                            <%--${userInfo.username}</a>--%>
                    <%--&nbsp;|&nbsp;--%>
                    <%--<a href="<%=request.getContextPath()%>/sign/logout">退出</a>--%>
                    <%--】--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--【<a href="<%=request.getContextPath()%>/sign/">登录</a>--%>
                    <%--&nbsp;|&nbsp;--%>
                    <%--<a href="<%=request.getContextPath()%>/sign/regeditPersonal">注册</a>--%>
                    <%--】--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>

        <%--</div>--%>
    <%--</div>--%>
<%--</nav>--%>

