<%--
  Created by IntelliJ IDEA.
  Sysuser: suyx
  Date: 2016/12/18
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>校园贷款--个人信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <%@include file="common/commonCss.jsp"%>
    <link href="<%=request.getContextPath()%>/static/css/fronts/personalInfo.css" rel="stylesheet" type="text/css" />
    <style>
        .bbtt {
            margin-bottom: 30px;
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
        <%--<a href="<%=request.getContextPath()%>/loan/myloan">我的贷款</a>--%>
        <%--<a href="<%=request.getContextPath()%>/loan/repaydetail">全部待还</a>--%>
        <a href="<%=request.getContextPath()%>/fronts/personalInfo?xxx=1" class="zm_cura" style="margin-right:0;">个人中心</a>
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
                    <button type="button" class="btn btn-primary" id="stuidentity" >申请学生认证</button>
                    </c:when>
                    <c:when test="${sysuser.isstuidentity == 5}">
                    <span>已申请学生认证，请等待审核！</span>
                    </c:when>
                    <c:otherwise>
                    <button type="button" class="btn btn-primary" id="stuidentity" >申请学生认证</button>
                    </c:otherwise>

                    </c:choose>
                    </p>
                    <p id="creditopera">
                    <c:choose>
                    <c:when test="${sysuser.iscreditidentity == 1}">
                    已通过信用认证,您的信用额度为<span style="color: #ff0000">${sysuser.loanlimit}</span>元
                    &nbsp;&nbsp;
                    <a href="#creditModal" id="viewcredit" data-toggle="modal" data-target="#creditModal">查看信用信息</a>
                    </c:when>
                    <c:when test="${sysuser.iscreditidentity == 2}">
                    信用认证未通过&nbsp;
                    <button type="button" class="btn btn-primary" id="creditidentity" >申请信用认证</button>
                    </c:when>
                    <c:when test="${sysuser.iscreditidentity == 5}">
                    <span>已申请信用认证，请等待审核！</span>
                    </c:when>
                    <c:otherwise>
                    <button type="button" class="btn btn-primary" id="creditidentity" >申请信用认证</button>
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
                <div class="row">
                    <div class="col-xs-6 col-xs-offset-3 col-md-3 col-md-offset-5">
                        <a href="javascript:void(0)" id="userPhotoBox" class="thumbnail">
                            <c:choose>
                                <c:when test="${userphoto.headstate == 1}">
                                    <img src="${userphoto.headphoto}"  height="150px" width="150px" />
                                </c:when>
                                <c:otherwise>
                                    <img src="<%=request.getContextPath()%>/static/images/defaultMan.png" height="150px" width="150px" />
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </div>
                </div>
                <p id="modifyPhotoBox" style="text-align: center; border-bottom: 2px solid #cccccc">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#photoModal">修改头像</button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modifyModal">修改密码</button>
                </p>
                <div class="form active" id="personalRegedit">
                    <form id="personalForm" action="" method="post" class="form-horizontal">
                        <input type="hidden" name="id" value="${sysuser.id}" />
                        <input type="hidden" name="userPhoto" id="userPhoto" value="${sysuser.userphoto}" />

                        <div class="form-group form-actions">
                            <label for="loginname" class="col-sm-2 control-label">登录账号:</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="loginname" name="loginname" value="${sysuser.loginname}" placeholder="登录账号" type="text" readonly />
                            </div>
                        </div>

                        <div class="form-group col-xs-8 form-actions" style="margin-top: 15px;display: none">
                            <input class="form-control" id="password" name="password" value="${sysuser.password}" type="password" />
                        </div>
                        <div class="form-group form-actions" style="margin-top: 15px">
                            <label class="col-sm-2 control-label">家庭住址:</label>
                            <div data-toggle="distpicker" class="col-sm-10">
                                <select id="province" name="province" data-province="---- 选择省 ----"></select>
                                <select id="city" name="city" data-city="---- 选择市 ----"></select>
                                <select id="district" name="district" data-district="---- 选择区 ----"></select>
                            </div>
                        </div>
                        <div class="form-group form-actions" style="margin-top: 15px">
                            <label for="detailaddr" class="col-sm-2 control-label">详细地址:</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="detailaddr" name="detailaddr" value="${sysuser.detailaddr}" placeholder="详细地址" type="text" />
                            </div>
                        </div>
                        <div class="form-group form-actions" style="margin-top: 15px">
                            <label for="phone" class="col-sm-2 control-label">手机号码:</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="phone" name="phone" value="${sysuser.phone}" placeholder="手机号码" type="text" readonly />
                            </div>
                        </div>
                        <div class="form-group form-actions" style="margin-top: 15px">
                            <label for="wechart" class="col-sm-2 control-label">微信:</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="wechart" name="wechart" value="${sysuser.wechart}" placeholder="微 信" type="text" />
                            </div>
                        </div>
                        <div class="form-group form-actions" style="margin-top: 15px">
                            <label for="qqNum" class="col-sm-2 control-label">QQ号码:</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="qqnum" name="qqnum" value="${sysuser.qqnum}" placeholder="QQ" type="text" />
                            </div>
                        </div>
                        <div class="form-group col-sm-12 form-actions" style="margin-top: 15px;text-align: center">
                            <button style="text-align: center" class="btn btn-primary bbtt" id="regedit" type="button">保存</button>
                        </div>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<!--学生认证-->
<div class="modal fade" id="stuidentityModal" tabindex="-1" role="dialog" aria-labelledby="stuidentityModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="stuidentitytitle">学生认证</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="stuidetityform" action="" method="post">
                    <div class="form-group">
                        <label for="school" class="col-sm-2 control-label">学校</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="school" name="school" placeholder="请输入完整的学校名">
                        </div>
                        <span id="schooltips" style="color: #ff0000;display: none;font-size: 12px">学校不能为空</span>
                    </div>
                    <div class="form-group">
                        <label for="classgrade" class="col-sm-2 control-label">班级</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="classgrade" name="classgrade" placeholder="班级">
                        </div>
                        <span id="classgradetips" style="color: #ff0000;display: none;font-size: 12px">班级不能为空</span>
                    </div>
                    <div class="form-group">
                        <label for="stunum" class="col-sm-2 control-label">学号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="stunum" name="stunum" placeholder="学号">
                        </div>
                        <span id="stunumtips" style="color: #ff0000;display: none;font-size: 12px">学号不能为空</span>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="tostuidentity">提交</button>
            </div>
        </div>
    </div>
</div>

<!--信用认证-->
<div class="modal fade" id="creditidentityModal" tabindex="-1" role="dialog" aria-labelledby="creditidentityModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="creditidentitytitle">信用认证</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="creditidetityform" action="" method="post">
                    <div class="form-group">
                        <label for="school" class="col-sm-4 control-label">支付宝账号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="alipayname" name="alipayname" placeholder="请输入正确的支付宝账号">
                        </div>
                        <span id="stuidcardtips" style="color: #ff0000;display: none;font-size: 12px">支付宝账号</span>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="tocreditidentity">提交</button>
            </div>
        </div>
    </div>
</div>

<!--修改密码-->
<div class="modal fade bs-example-modal-sm" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="modifyModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="modifyModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <form id="peopleForm" action="" method="post">
                    <div class="form-group">
                        <label for="oldPassword">原密码</label>
                        <input type="password" class="form-control" id="oldPassword" placeholder="原密码">
                    </div>
                    <div class="form-group">
                        <label for="newPassword">新密码</label>
                        <input type="password" class="form-control" id="newPassword" placeholder="新密码">
                    </div>
                    <div class="form-group">
                        <label for="newPasswordAffirm">确认新密码</label>
                        <input type="password" class="form-control" id="newPasswordAffirm" placeholder="确认新密码">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="toModify-pwd">确认修改</button>
            </div>
        </div>
    </div>
</div>
<!--修改头像-->
<div class="modal fade bs-example-modal-sm" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="photoModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="photoModalLabel">修改头像</h4>
            </div>
            <div class="modal-body" >
                <div class="photoBox" style="margin-left: auto;margin-right: auto;width: 150px;">
                    <div id="progress_bar" style="display: none"></div>
                    <input id="photoUrl" name="photoUrl" type="hidden" />
                    <input type="file" name="imgFile" id="imgFile" />
                    <a id="show_img"><img style="display: none;" id="result_img" class="img-responsive" /></a>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="toModify-photo">确认修改</button>
            </div>
        </div>
    </div>
</div>
<!--信用信息-->
<div class="modal fade" id="creditModal" tabindex="-1" role="dialog" aria-labelledby="creditModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="creditModalLabel">我的信用信息</h4>
            </div>
            <div class="modal-body" >
                <div>
                    <label>信用总分:</label>
                    ${creditscore.creditscoretotal}
                </div>
                <div>
                    <label>信用分:</label>
                    ${creditscore.identityscore}
                </div>
                <div>
                    <label>履约分:</label>
                    ${creditscore.performscore}
                </div>
                <div>
                    <label>历史信用分:</label>
                    ${creditscore.historyscore}
                </div>
                <div>
                    <label>人脉分:</label>
                    ${creditscore.peoplekeepscore}
                </div>
                <div>
                    <label>行为分:</label>
                    ${creditscore.behaviorscore}
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!--学生信息-->
<div class="modal fade bs-example-modal-lg" id="studentModal" tabindex="-1" role="dialog" aria-labelledby="studentModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="studentModalLabel">我的学生信息</h4>
            </div>
            <div class="modal-body" >
                <form>
                    <div class="form-group">
                        <label class="col-sm-2">姓名:</label>
                        <span class="col-sm-4">${studentinfo.stuname}</span>
                        <label class="col-sm-2">性别:</label>
                        <span>
                        <c:choose>
                            <c:when test="${studentinfo.stusex == 1}">
                                男
                            </c:when>
                            <c:otherwise>女</c:otherwise>
                        </c:choose>
                    </span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2">身份证号:</label>
                        <span class="col-sm-4">${studentinfo.stuidcard}</span>
                        <label class="col-sm-2">学校:</label>
                        <span>${studentinfo.school}</span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2">学历层次:</label>
                        <span class="col-sm-4">${studentinfo.edulevel}</span>
                        <label class="col-sm-2">专业:</label>
                        <span>${studentinfo.major}</span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2">学制:</label>
                        <span class="col-sm-4">${studentinfo.edusystem}</span>
                        <label class="col-sm-2">学历类别:</label>
                        <span>${studentinfo.edutype}</span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2">学习形式:</label>
                        <span class="col-sm-4">${studentinfo.edumode}</span>
                        <label class="col-sm-2">分院:</label>
                        <span>${studentinfo.branch}</span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2">班级:</label>
                        <span class="col-sm-4">${studentinfo.classgrade}</span>
                        <label class="col-sm-2">学号:</label>
                        <span>${studentinfo.stunum}</span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2">入学时间:</label>
                        <span class="col-sm-4">
                            <fmt:formatDate value="${studentinfo.admissiondate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                        </span>
                        <label class="col-sm-2">离校时间:</label>
                        <span>
                            <fmt:formatDate value="${studentinfo.leavedate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                        </span>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2">学籍状态:</label>
                        <span>
                    <c:choose>
                        <c:when test="${studentinfo.stustate == 1}">
                            在籍
                        </c:when>
                        <c:otherwise>不在籍</c:otherwise>
                    </c:choose>
                    </span>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%@include file="common/springUrl.jsp"%>
<%@include file="common/footer.jsp" %>
<%@include file="common/commonJS.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.data.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/frontJs/personalInfo.js"></script>
<script type="text/javascript">
    var winHeigth = $(window).height();
    var userId = "${sysuser.id}";
    var loancode = ''${loancode};
    $(document).ready(function () {
        if(loancode == -2){
            alert("您还没有进行学生认证或者信用认证，请先申请认证!");
        }
        $("#province").val("${sysuser.province}");
        $("#province").change();
        $("#city").val("${sysuser.city}");
        $("#city").change();
        $("#district").val("${sysuser.district}");
        $("#district").change();

        setTimeout(function() {
            $('#imgFile').uploadify({
                'swf': projectUrl + '/static/uploadify/uploadify.swf',
                'uploader': projectUrl + '/upload/uploadImg',
                'cancelImg': projectUrl + '/static/uploadify/cancel.png',
                'auto': true,
                "formData": {targetFile: '/upload/userImg'},
                'queueID': 'progress_bar',
                'fileObjName': 'uploadFile',
                "buttonCursor": "hand",
                "buttonText": "选择图片",
                "buttonImage": projectUrl + "/static/images/defaultUpload.gif",
                "buttonClass": "img-thumbnail",
                "height": "140",
                'fileDesc': '支持格式:jpg,jpeg,gif,png,bmp', //如果配置了以下的'fileExt'属性，那么这个属性是必须的
                'fileExt': '*.jpg;*.jpeg;*.gif;*.png;*.bmp',//允许的格式
                'onUploadSuccess': function (file, data, response) {
                    var result = eval('(' + data + ')');
                    var imgPath = result.filePath;
                    $("#result_img").attr('src', imgPath);
                    $("#result_img").show();
                    $("#imgFile").hide();
                    $("#photoUrl").attr('value', imgPath);
                    $("#show_img").mouseover(function () {
                        $("#result_img").attr('src', projectUrl + "/static/images/deleteImg.png");
                    });
                    $("#show_img").mouseout(function () {
                        $("#result_img").attr('src', imgPath);
                    });
                    $("#result_img").click(function () {
                        $("#result_img").hide();
                        $("#imgFile").show();
                        $("#photoUrl").removeAttr('value');
                        $("#show_img").unbind('mouseover');
                        $("#show_img").unbind('mouseout');

                    });
                },
                onUploadError: function (file, errorCode, errorMsg, errorString) {
                    alert("error-->" + errorString);
                }
            });
        },10);

    });

</script>
</body>
</html>
