<%@ page import="com.stuloan.web.util.Userutils" %><%--
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
    <title>校园贷--申请</title>
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
        #loanage{
            width:90%
        }
        #loandesc{
            color: #ff0000;
        }
    </style>
</head>
<body>
<%@include file="common/header.jsp" %>
<div class="zxcf_menu_wper">
    <div class="zxcf_menu px1000">
        <a href="<%=request.getContextPath()%>/fronts/index">首页</a>
        <a href="javascript:void 0;" class="zm_cura">我要借款</a>
        <a href="<%=request.getContextPath()%>/fronts/personalInfo?xxx=1">个人中心</a>
    </div>
</div>
<%--<div class="bor_banner01">--%>

<%--</div>--%>
<div class="bor_con_wper">
    <div class="bor_con px1000">
        <div class="bor_detail">
            <%--<h2 class="bor_detail_tit">--%>
                <%--<span class="bor_decurspan">房产抵押</span>--%>
                <%--<span>车辆抵押</span>--%>
                <%--<span>信用贷款</span>--%>
                <%--<span>零首付车贷</span>--%>
            <%--</h2>--%>
            <div class="bor_detail_box">
                <div class="bor_det_one clearfix pt30 pb30">
                    <div class="bor_det_onel fl">
                        <%--<p class="bor_p1">中兴财富平台的借款功能旨在帮助借款用户以--%>
                            <%--低成本获得借款。用户在需要资金时，可以将--%>
                            <%--自有房产和车产作为抵押物，小油菜线下审核--%>
                            <%--通过后，根据抵押物的价值融资。</p>--%>
                        <%--<p class="bor_p2">中兴财富平台的借款功能旨在帮助借款用户以--%>
                            <%--低成本获得借款。用户在需要资金时，可以将--%>
                            <%--自有房产和车产作为抵押物，小油菜线下审核--%>
                            <%--通过后，根据抵押物的价值融资。</p>--%>
                        <h3 class="bor_onel_tit"><span>申请条件</span></h3>
                        <ul class="bor_onel_ul">
                            <li><img src="images/bor_pic01.png" alt="">年满18周岁以上的公民
                            </li>
                            <li><img src="images/bor_pic02.png" alt="">需要北京房产或车产抵押
                            </li>
                            <li><img src="images/bor_pic03.png" alt="">个人或企业银行征信记录良好
                            </li>
                            <li><img src="images/bor_pic04.png" alt="">
                                无现有诉讼记录
                            </li>

                        </ul>
                        <h3 class="bor_onel_tit"><span>提交资料</span></h3>
                        <ul class="bor_onel_ul">
                            <li>&nbsp;<img src="images/bor_pic05.png" alt="">省份证
                            </li>
                            <li><img src="images/bor_pic06.png" alt="">申请资料
                            </li>
                            <li><img src="images/bor_pic07.png" alt="">其他
                            </li>


                        </ul>
                    </div>
                    <!-- end l -->
                    <div class="bor_det_oner fl">
                        <form id="regeditForm" action="" method="post" class="form-horizontal">
                            <fieldset>
                                <input type="hidden" id="userid" name="userid" value="${studentinfo.userid}" />
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">*姓名:</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="name" name="name" value="${studentinfo.stuname}" type="text" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="schoolandclass" class="col-sm-4 control-label">*学校和年级:</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="schoolandclass" name="schoolandclass" value="${studentinfo.school}${studentinfo.classgrade}" type="text" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone" class="col-sm-4 control-label">*联系手机:</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="phone" name="phone" value="${sysuser.phone}" type="text" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="qq" class="col-sm-4 control-label">*QQ号:</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="qq" name="qq" value="" type="text" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="loanpurpose" class="col-sm-4 control-label">*贷款用途:</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="loanpurpose" name="loanpurpose" value="" type="text" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="loanamount" class="col-sm-4 control-label">*贷款金额:</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="loanamount" name="loanamount" value="" type="text" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="loanage" class="col-sm-4 control-label">贷款期限:</label>
                                    <div class="col-sm-8">
                                        <select class="form-control col-sm-1" id="loanage" name="loanage">
                                            <option value="0">0</option>
                                            <c:forEach var="stagefee" items="${stagefeelist}">
                                                <option value="${stagefee.stagenum}">${stagefee.stagenum}</option>
                                            </c:forEach>
                                        </select>
                                        <%--<input class="form-control col-sm-10" id="loanage" name="loanage" value="" type="text" />月--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="loanage" class="col-sm-4 control-label">*贷款说明:</label>
                                    <div class="col-sm-8" id="loandesc"></div>
                                </div>
                                <div class="form-group" style="margin-top: 15px">
                                    <label for="checkCode" class="col-sm-4 control-label">*验证码:</label>
                                    <div class="col-sm-8">
                                        <div class="col-sm-5 chkcls">
                                            <input class="form-control" id="checkCode" name="checkCode" placeholder="验证码" type="text" />
                                        </div>
                                        <div class="col-sm-5 chkcls">
                                            <canvas class="form-control" id="canvas" name="canvas"  width="150" height="34"></canvas>
                                        </div>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-4 col-sm-10">
                                        <button class="btn btn-primary bbtt" style="margin-bottom: 20px;" id="regedit" type="button">提  交</button>
                                    </div>
                                </div>

                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<div class="login-box">--%>
    <%--<div class="login-title text-center">贷款申请</div>--%>
    <%--<div class="login-content">--%>
        <%--<div class="form">--%>
            <%--<form id="regeditForm" action="" method="post" class="form-horizontal">--%>
                <%--<input type="hidden" id="userid" name="userid" value="${studentinfo.userid}" />--%>
            <%--<div class="form-group">--%>
                <%--<label for="name" class="col-sm-4 control-label">姓名:</label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input class="form-control" id="name" name="name" value="${studentinfo.stuname}" type="text" />--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label for="schoolandclass" class="col-sm-4 control-label">学校和年级:</label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input class="form-control" id="schoolandclass" name="schoolandclass" value="${studentinfo.school}${studentinfo.classgrade}" type="text" />--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label for="phone" class="col-sm-4 control-label">联系手机:</label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input class="form-control" id="phone" name="phone" value="${sysuser.phone}" type="text" />--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label for="qq" class="col-sm-4 control-label">QQ号:</label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input class="form-control" id="qq" name="qq" value="" type="text" />--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label for="loanpurpose" class="col-sm-4 control-label">贷款用途:</label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input class="form-control" id="loanpurpose" name="loanpurpose" value="" type="text" />--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label for="loanamount" class="col-sm-4 control-label">贷款金额:</label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input class="form-control" id="loanamount" name="loanamount" value="" type="text" />--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label for="loanage" class="col-sm-4 control-label">贷款期限:</label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input class="form-control col-sm-10" id="loanage" name="loanage" value="" type="text" />月--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label for="loanage" class="col-sm-4 control-label">贷款说明:</label>--%>
                <%--<div class="col-sm-8" id="loandesc"></div>--%>
            <%--</div>--%>
            <%--<div class="form-group" style="margin-top: 15px">--%>
                <%--<label for="checkCode" class="col-sm-4 control-label">验证码:</label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<div class="col-sm-7 chkcls">--%>
                        <%--<input class="form-control" id="checkCode" name="checkCode" placeholder="验证码" type="text" />--%>
                    <%--</div>--%>
                    <%--<div class="col-sm-5 chkcls">--%>
                        <%--<canvas class="form-control" id="canvas" name="canvas"  width="150" height="34"></canvas>--%>
                    <%--</div>--%>

                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="col-sm-offset-4 col-sm-10">--%>
                    <%--<button class="btn btn-primary bbtt" style="margin-bottom: 20px;" id="regedit" type="button">提  交</button>--%>
                <%--</div>--%>
            <%--</div>--%>

            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%@include file="common/springUrl.jsp"%>
<%@include file="common/footer.jsp" %>
<%@include file="common/commonJS.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkCode_2.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.data.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/distpicker.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/frontJs/applyloan.js"></script>
<script type="text/javascript">
    var loanlimt = '${sysuser.loanlimit}';
    $(function () {

        $("#loanage").onkeyup(function () {
            var xx = parseInt($(this).val());
            if(isNaN(ival)){
                alert("请输入正确的贷款年限，请输入正确的数字年限！");
                return;
            }
        });

    });

</script>
</body>
</html>
