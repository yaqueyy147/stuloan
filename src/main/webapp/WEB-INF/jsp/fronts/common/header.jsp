<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .userInfo{
        float: right;
        margin-right: 0px;
    }
</style>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="min-height: 0;">
    <div class="container">
        <div class="userInfo">
            <a href="<%=request.getContextPath()%>/family/index">首页</a>&nbsp;|&nbsp;
            <c:choose>
                <c:when test="${not empty userInfo}">
                    【欢迎您，
                    <a href="<%=request.getContextPath()%>/family/personalInfo?xxx=1">
                            ${userInfo.username}
                    &nbsp;|&nbsp;
                    <a href="<%=request.getContextPath()%>/sign/logout">退出</a>
                    】
                </c:when>
                <c:otherwise>
                    【<a href="<%=request.getContextPath()%>/sign/">登录</a>
                    &nbsp;|&nbsp;
                    <a href="<%=request.getContextPath()%>/sign/regeditPersonal">注册</a>
                    】
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</nav>

