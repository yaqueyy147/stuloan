<%--
  Created by IntelliJ IDEA.
  User: suyx
  Date: 2016/12/18
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>世纪动漫</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="<%=request.getContextPath()%>/static/css/fronts/index.css" rel="stylesheet" type="text/css" />
    <%@include file="common/commonCss.jsp"%>
    <style>
        .loading{
            z-index: 8888;
            width: 100%;
            height: 100%;
            background-color: #999999;
            opacity: 0.5;
            text-align: center;
            position: fixed;
            margin-top: -50px;
            display: none;
        }
        .loading div{
            z-index: 9999;
            width: 200px;
            height:200px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 10%;
            color: #ff0000;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="loading">
    <div>查询中,请稍后...</div>
</div>
<%@include file="common/header.jsp" %>
<div class="container-fluid" style="margin-top: 50px;width: 90%; margin-bottom: 50px">
    <nav class="navbar navbar-default">
        <form class="navbar-form navbar-left searchForm" role="search">
            <div class="form-group">
                <input type="text" id="cartoonname" name="cartoonname" class="form-control" placeholder="动漫名">
            </div>

            <button type="button" id="searchBtn" class="btn btn-default">查询</button>
        </form>
    </nav>
    <div class="row" id="familyContent">
        <c:forEach var="family" items="${familyList}">

            <div class="col-sm-3 col-md-2 familyDiv">
                <div class="thumbnail">
                    <a href="javascript:void(0)" onclick="viewFamily('${family.id}','${family.visitStatus}','${family.visitPassword}')" style="float: none;width: 100%;">
                        <img class="familyImgFF" src="${family.photoUrl}" class="img-thumbnail"/></a>
                        <%--<img data-src="holder.js/300x300" alt="...">--%>
                    <div class="caption">
                        <h6>${family.familyFirstName}（${family.id}）</h6>
                            <%--<h6>世界何氏族谱（${family.id}）</h6>--%>
                        <p>家族人数：${family.zspeopleCount}&nbsp;/&nbsp;${family.peopleCount}人</p>
                            <%--<p>在世人数：${family.zspeopleCount}人</p>--%>
                        <p>状态：
                            <c:if test="${family.visitStatus == 0}">加密</c:if>
                            <c:if test="${family.visitStatus == 1}">开放</c:if>
                                <%--<c:if test="${family.visitStatus == 2}">仅族人查看</c:if>--%>

                        </p>
                        <p style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden">${family.familyName}</p>
                        <p name="familyDesc" onmouseover="pPopover(this,1)" onmouseout="pPopover(this,2)" style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden" data-container="body" data-toggle="popover" data-placement="right" data-content="${family.familyDesc}">
                                ${family.familyDesc}
                        </p>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
</div>

<%@include file="common/springUrl.jsp"%>
<%@include file="common/footer.jsp" %>
<%@include file="common/commonJS.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.data.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/frontJs/index.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery/jquery.MD5.js"></script>
</body>
</html>
