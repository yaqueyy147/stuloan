/**
 * Created by suyx on 2016/12/18.
 */
var checkCodePre;
$(function () {
    checkCodePre = drawPic();
    $("#regedit").click(function () {

        if(isfrozen == 1){
            alert("您的账号已被冻结，不能贷款。如需贷款，请联系管理员！");
            return ;
        }

        var loanpurpose = $("#loanpurpose").val();
        var loanamount = $("#loanamount").val();
        var loanage = $("#loanage").val();

        var checkCode = $("#checkCode").val();

        if($.trim(loanpurpose).length <= 0){
            alert("贷款用途不能为空！");
            return ;
        }
        if($.trim(loanamount).length <= 0){
            alert("贷款金额不能为空！");
            return ;
        }
        if($.trim(loanage).length <= 0){
            alert("贷款年限不能为空！");
            return ;
        }
        if(checkCodePre.toUpperCase() != checkCode.toUpperCase()){
            alert("验证码错误！");
            return;
        }

        if(Number(loanamount) > Number(loanlimt)){
            alert("申请贷款金额超过可贷款额度！");
            return ;
        }
        var formData = $("#regeditForm").serializeArray();
        var testData = {};
        for (var item in formData) {
            testData["" + formData[item].name + ""] = formData[item].value;
        }
        $.ajax({
            type:'post',
            url: projectUrl + '/loan/toapplyloan',
            dataType: 'json',
            data:testData,
            success:function (data) {
                alert(data.message);
                if(data.code >= 1){
                    window.location.href = projectUrl + '/loan/myloan';
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
        // $("#regeditForm").attr("action",projectUrl + "/loan/toapplyloan");
        // $("#regeditForm").submit();
    });

    document.getElementById("canvas").onclick = function(e){
        e.preventDefault();
        checkCodePre = drawPic();
    };

    $("#loanage").change(function () {
        var age = $(this).val();
        var loanamount = $("#loanamount").val();
        if($.trim(loanamount).length <= 0){
            alert("贷款金额不能为空！");
            return ;
        }
        $.ajax({
            type:'post',
            url: projectUrl + '/loan/getfeefromage',
            dataType: 'json',
            data:{stagenum:age},
            async:true,
            success:function (data) {
                if(data.code >= 1){
                    var fee = data.stagefee.feepercent;
                    var orirepay = loanamount;
                    var totalrepay = loanamount;
                    var avgrepay = loanamount;
                    var avgfee = 0;
                    var loandesc = "";
                    if(fee == 0 || fee == 0.0){
                        loandesc = "无分期";
                    }else{
                        orirepay = parseFloat(loanamount) / parseInt(age);
                        totalrepay = parseFloat(loanamount) + parseFloat(loanamount * fee / 100);
                        avgrepay = parseFloat(totalrepay) / parseInt(age);
                        avgfee = parseFloat(orirepay) * parseFloat(fee) / 100;

                        avgfee = avgfee.toFixed(2);
                        orirepay = orirepay.toFixed(2);
                        avgrepay = avgrepay.toFixed(2);
                        loandesc = "每期本金" + orirepay + "元,每期费率" + fee + "%,每期手续费" + avgfee + "元,每期总额" + avgrepay + "元";
                    }

                    $("#loandesc").html(loandesc);
                }else{
                    var loandesc = "系统错误，请联系管理员!";
                    $("#loandesc").html(loandesc);
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

    $("#loanamount").bind("propertychange input",function () {
        var xx = $(this).val();
        if(Number(xx) > Number(loanlimt)){
            alert("您的贷款额度为:" + loanlimt + ",输入金额不能超过额度");
            $(this).val(loanlimt);
            return;
        }
    });
});
