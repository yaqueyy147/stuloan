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
    <title>世纪动漫--注册</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="<%=request.getContextPath()%>/static/css/fronts/regedit.css" rel="stylesheet" type="text/css" />
    <%@include file="common/commonCss.jsp"%>
    <style>
        .form-group{
            padding-left: 0px !important;
            padding-right: 10px !important;
        }
        .control-label{
          text-align: right;
        }
        #canvas{
            padding: 0 !important;
        }
        .chkcls{
            padding: 0 !important;
        }
    </style>
</head>
<body>
<div class="login-box">
    <div class="login-title text-center">注&nbsp;&nbsp;&nbsp;&nbsp;册</div>
    <div class="login-content">
        <div class="form">
            <form id="regeditForm" action="" method="post" class="form-horizontal">
            <div class="form-group">
                <label for="loginname" class="col-sm-4 control-label">登录账号:</label>
                <div class="col-sm-8">
                    <input class="form-control" id="loginname" name="loginname" placeholder="登录账号" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-sm-4 control-label">昵 称:</label>
                <div class="col-sm-8">
                    <input class="form-control" id="username" name="username" placeholder="昵称" type="text" />
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-4 control-label">密 码:</label>
                <div class="col-sm-8">
                    <input class="form-control" id="password" name="password" placeholder="密 码" type="password" />
                </div>
            </div>
            <div class="form-group" style="margin-top: 15px">
                <label for="passwordAffirm" class="col-sm-4 control-label">确认密码:</label>
                <div class="col-sm-8">
                    <input class="form-control" id="passwordAffirm" name="passwordAffirm" placeholder="确认密码" type="password" />
                </div>
            </div>
            <div class="form-group" style="margin-top: 15px">
                <label for="phone" class="col-sm-4 control-label">手机号码:</label>
                <div class="col-sm-8">
                    <input class="form-control" id="phone" name="phone" placeholder="手机号码" type="text" />
                </div>
            </div>

            <div class="form-group" style="margin-top: 15px">
                <label for="checkCode" class="col-sm-4 control-label">验证码:</label>
                <div class="col-sm-8">
                    <div class="col-sm-7 chkcls">
                        <input class="form-control" id="checkCode" name="checkCode" placeholder="验证码" type="text" />
                    </div>
                    <div class="col-sm-5 chkcls">
                        <canvas class="form-control" id="canvas" name="canvas"  width="150" height="34"></canvas>
                    </div>

                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-10">
                    <button class="btn btn-primary bbtt" style="margin-bottom: 20px;" id="regedit" type="button">注 册</button>
                    &nbsp;&nbsp;
                    <a class="btn btn-primary bbtt" style="margin-bottom: 20px;" href="<%=request.getContextPath()%>/cartoon/index" type="button">取 消</a>
                </div>
            </div>

            </form>
        </div>
    </div>
</div>
<%@include file="common/springUrl.jsp"%>
<%@include file="common/commonJS.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkCode_2.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.data.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/frontJs/regeditPersonal.js"></script>
<script type="text/javascript">
    $(function () {
        var regCode = "${regCode}";
        if(regCode == -2){
            alert("该账号已被注册!");
        }
    });

</script>
</body>
</html>
