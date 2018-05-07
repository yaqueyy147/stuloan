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

        .tips{
            color: #ff0000;
            font-size: 16px;
            display: inline-block;
            position: absolute;
            margin-top: 80px;
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
                    <a href="#stuidentityModal" id="stuidentity" data-toggle="modal" data-target="#stuidentityModal">申请学生认证</a>
                    </c:when>
                    <c:when test="${sysuser.isstuidentity == 5}">
                    <span>已申请学生认证，请等待审核！</span>
                    </c:when>
                    <c:otherwise>
                    <a href="#stuidentityModal" id="stuidentity" data-toggle="modal" data-target="#stuidentityModal">申请学生认证</a>
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
                    <a href="#creditidentityModal" id="creditidentity" data-toggle="modal" data-target="#creditidentityModal" >申请信用认证</a>
                    </c:when>
                    <c:when test="${sysuser.iscreditidentity == 5}">
                    <span>已申请信用认证，请等待审核！</span>
                    </c:when>
                    <c:otherwise>
                    <a href="#creditidentityModal" id="creditidentity" data-toggle="modal" data-target="#creditidentityModal">申请信用认证</a>
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
                <div class="touxiang">
                    <input id="headphoto" name="headphoto" type="hidden"/>
                    <div class="photoID" id="resultimg-div" style="display: none;">
                        <label class="Mgetphoto">
                            <img id="result_img" src="" width="160" height="160">
                            <em><img src="/static/images/delete.png" id="deleteImg" name="deleteimg" data-type="1">删除头像 </em> </label>
                    </div>
                    <div class="photoID" id="upload-div">
                        <label class="Mupdata"><span>点击上传头像</span>
                            <input type="file" name="uploadFile" id="imgFile" onchange="uploadPP(this,'result_img','resultimg-div','upload-div','headphoto','/upload/userphoto/headphoto','1')" accept="image/png,image/jpeg" />
                        </label>
                    </div>
                    <span class="tips" id="head-tips">请上传正确的jpg或者png格式的头像图片!</span>

                </div>

                <div class="touxiang">
                    <input id="idcardphoto" name="idcardphoto" type="hidden"/>
                    <div class="photoID" id="resultimg-div01" style="display: none;">
                        <label class="Mgetphoto">
                            <img id="result_img01" src="" width="260" height="160">
                            <em><img src="/static/images/delete.png" id="deleteImg01" name="deleteimg" data-type="2">删除身份证图片 </em> </label>
                    </div>
                    <div class="photoID" id="upload-div01">
                        <label class="Mupdata"><span>点击上传身份证照</span>
                            <input type="file" name="uploadFile" id="imgFile01" onchange="uploadPP(this,'result_img01','resultimg-div01','upload-div01','idcardphoto','/upload/userphoto/idcardphoto','2')" accept="image/png,image/jpeg" />
                        </label>
                    </div>
                    <span class="tips" id="idcard-tips">请上传正确的jpg或者png格式的身份证照!</span>
                </div>

                <div class="touxiang">
                    <input id="stuidcardphoto" name="stuidcardphoto" type="hidden"/>
                    <div class="photoID" id="resultimg-div02" style="display: none;">
                        <label class="Mgetphoto">
                            <img id="result_img02" src="" width="260" height="160">
                            <em><img src="/static/images/delete.png" id="deleteImg02" name="deleteimg" data-type="3">删除学生证图片 </em> </label>
                    </div>
                    <div class="photoID" id="upload-div02">
                        <label class="Mupdata"><span>点击上传学生证照</span>
                            <input type="file" name="uploadFile" id="imgFile02" onchange="uploadPP(this,'result_img02','resultimg-div02','upload-div02','stuidcardphoto','/upload/userphoto/stuidcardphoto','3')" accept="image/png,image/jpeg" />
                        </label>
                    </div>
                    <span class="tips" id="stuidcard-tips">请上传正确的jpg或者png格式的学生证照!</span>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    var winHeigth = $(window).height();
    var userId = "${sysuser.id}";
    var photoid = "${userphoto.id}";
    var headphoto = "${userphoto.headphoto}";
    var idcardphoto = "${userphoto.idcardphoto}";
    var stuidcardphoto = "${userphoto.stuidcardphoto}";
    var headstate = "${userphoto.headstate}";
    var idcardstate = "${userphoto.idcardstate}";
    var stuidcardstate = "${userphoto.stuidcardstate}";
    $(document).ready(function () {

        if(headphoto && $.trim(headphoto).length > 0){
            $("#result_img").attr('src', projectUrl + headphoto);
            $("#result_img").show();
            $("#resultimg-div").show();
            $("#headphoto").attr('value', projectUrl + headphoto);
            $("#upload-div").hide();
            if(headstate == 1){
                $("#head-tips").hide();
            }
            if(headstate == 5){
                $("#head-tips").text("审核中...");
                $("#head-tips").show();
            }
            if(headstate == 2){
                $("#head-tips").text("审核未通过，请重新上传");
                $("#head-tips").show();
            }
        }
        if(idcardphoto && $.trim(idcardphoto).length > 0){
            $("#result_img01").attr('src', projectUrl + idcardphoto);
            $("#result_img01").show();
            $("#resultimg-div01").show();
            $("#idcardphoto").attr('value', projectUrl + idcardphoto);
            $("#upload-div01").hide();
            if(idcardstate == 1){
                $("#idcard-tips").hide();
            }
            if(idcardstate == 5){
                $("#idcard-tips").text("审核中...");
                $("#idcard-tips").show();
            }
            if(idcardstate == 2){
                $("#idcard-tips").text("审核未通过，请重新上传");
                $("#idcard-tips").show();
            }
        }
        if(stuidcardphoto && $.trim(stuidcardphoto).length > 0){
            $("#result_img02").attr('src', projectUrl + stuidcardphoto);
            $("#result_img02").show();
            $("#resultimg-div02").show();
            $("#stuidcardphoto").attr('value', projectUrl + stuidcardphoto);
            $("#upload-div02").hide();
            if(stuidcardstate == 1){
                $("#stuidcard-tips").hide();
            }
            if(stuidcardstate == 5){
                $("#stuidcard-tips").text("审核中...");
                $("#stuidcard-tips").show();
            }
            if(stuidcardstate == 2){
                $("#stuidcard-tips").text("审核未通过，请重新上传");
                $("#stuidcard-tips").show();
            }
        }
        $("img[name='deleteimg']").click(function () {
            var type = $(this).attr("data-type");

            if(confirm("确定要删除该图片吗？")){
                $.ajax({
                    type 		: "POST",
                    dataType 	: "json",
                    url 		: projectUrl + "/upload/deleteimg",
                    data		: {id : photoid,type:type},
                    success		: function( result ) {
                        if(result.code){
                            alert("删除成功");
                            if(type == 1){
                                $("#result_img").attr('src', "");
                                $("#headphoto").val("");
                                $("#resultimg-div").hide();
                                $("#upload-div").show();
                            }
                            if(type == 2){
                                $("#result_img01").attr('src', "");
                                $("#idcardphoto").val("");
                                $("#resultimg-div01").hide();
                                $("#upload-div01").show();
                            }
                            if(type == 3){
                                $("#result_img02").attr('src', "");
                                $("#stuidcardphoto").val("");
                                $("#resultimg-div02").hide();
                                $("#upload-div02").show();
                            }

                        }else{
                            alert("网络请求出错,请联系管理员");
                        }
                    },
                    error:function (data) {
                        var responseText = data.responseText;
                        if(responseText.indexOf("登出跳转页面") >= 0){
                            ajaxErrorToLogin();
                        }else{
                            alert(JSON.stringify(data));
                        }
                    }
                });
            }

        });

        $("#tocreditidentity").click(function () {
            var alipayname = $("#alipayname").val();
            if($.trim(alipayname).length <= 0){
                $("#stuidcardtips").show();
                return;
            }
            $.ajax({
                type:'post',
                url: projectUrl + '/loan/tocreditidentity',
                dataType: 'json',
                data:{alipayname:alipayname},
                // async:false,
                success:function (data) {
                    if(data.code >= 1){
                        alert(data.message);
                        $("#creditopera").replaceWith("<span>已申请信用认证，请等待审核！</span>");
                        $("#creditidentityModal").modal("hide");
                    }
                },
                error:function (data) {
                    var responseText = data.responseText;
                    if(responseText.indexOf("登出跳转页面") >= 0){
                        ajaxErrorToLogin();
                    }else{
                        alert(JSON.stringify(data));
                    }
                }
            });
        });

        $("#tostuidentity").click(function () {
            var school = $("#school").val();
            var classgrade = $("#classgrade").val();
            var stunum = $("#stunum").val();
            if($.trim(school).length <= 0){
                $("#schooltips").show();
                return;
            }
            if($.trim(classgrade).length <= 0){
                $("#classgradetips").show();
                return;
            }
            if($.trim(stunum).length <= 0){
                $("#stunumtips").show();
                return;
            }
            $.ajax({
                type:'post',
                url: projectUrl + '/loan/tostuidentity',
                dataType: 'json',
                data:{school:school,classgrade:classgrade,stunum:stunum},
                // async:false,
                success:function (data) {
                    if(data.code >= 1){
                        alert(data.message);
                        $("#loanopera").replaceWith("<span>已申请学生认证，请等待审核！</span>");
                        $("#stuidentityModal").modal("hide");
                    }
                },
                error:function (data) {
                    var responseText = data.responseText;
                    if(responseText.indexOf("登出跳转页面") >= 0){
                        ajaxErrorToLogin();
                    }else{
                        alert(JSON.stringify(data));
                    }
                }
            });
        });

    });
    function uploadPP(obj,resultimg,resultimgdiv,uploaddiv,picinput,targetFile,type) {
        var filexx = $(obj).val();
        var suffix = filexx.substring(filexx.lastIndexOf("."));
        if(suffix != ".jpg" && suffix != ".png" && suffix != ".jpeg"){
            alert("请上传正确的jpg或者png格式的图片!");
            return;
        }

        var fileSize = obj.files[0].size;
        if(fileSize > 1 * 1024 * 1024){
            alert("请将图片大小限制在1M以内!");
            return;
        }

        var fileElementId = $(obj).attr("id");

        $.ajaxFileUpload
        (
            {
                url: projectUrl + '/upload/uploadImg?jsonCallBack=?', //用于文件上传的服务器端请求地址
                type: 'post',
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: fileElementId, //文件上传域的ID
                dataType: 'JSON', //返回值类型 一般设置为json
                data:{targetFile: targetFile,id:photoid,type:type},
                success: function (data, status)  //服务器成功响应处理函数
                {
                    data = eval('(' + data + ')');
                    if(data.code == 1){
                        var imgPath = data.filePath;
                        $("#" + resultimg).attr('src', projectUrl + imgPath);
                        $("#" + resultimg).show();
                        $("#" + resultimgdiv).show();
                        $("#" + picinput).attr('value', projectUrl + imgPath);
                        $("#" + uploaddiv).hide();
                        alert("上传成功!");
                    }else{
                        alert("上传失败!");
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
    }
</script>
</body>
</html>
