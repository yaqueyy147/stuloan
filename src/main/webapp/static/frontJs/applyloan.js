/**
 * Created by suyx on 2016/12/18.
 */
var checkCodePre;
$(function () {
    checkCodePre = drawPic();
    $("#regedit").click(function () {

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

        if(loanamount > loanlimt){
            alert("申请贷款金额超过可贷款额度！");
            return ;
        }

        $("#regeditForm").attr("action",projectUrl + "/loan/toapplyloan");
        $("#regeditForm").submit();
    });

    document.getElementById("canvas").onclick = function(e){
        e.preventDefault();
        checkCodePre = drawPic();
    };

    $("#loanage").bind("propertychange input",function () {
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
                if(data){
                    var fee = data.stagefee.feepercent;
                    var orirepay = parseFloat(loanamount) / parseInt(age);
                    var totalrepay = parseFloat(loanamount) + parseFloat(loanamount * fee / 100);
                    var avgrepay = parseFloat(totalrepay) / parseInt(age);
                    var avgfee = parseFloat(orirepay) * parseFloat(fee) / 100;
                    var loandesc = "每期本金" + orirepay.toFixed(2) + "元,每期费率" + fee + "%,每期手续费" + avgfee.toFixed(2) + "元,每期总额" + avgrepay.toFixed(2) + "元";
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

});
