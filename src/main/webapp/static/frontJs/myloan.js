/**
 * Created by suyx on 2016/12/18.
 */
var currentrepaypageno;
$(function () {

    $("#toModify-pwd").on({
        click:function () {
            var oldP = $("#oldPassword").val();
            var newP = $("#newPassword").val()
            var newPA = $("#newPasswordAffirm").val();
            if(newP != newPA){
                alert("两次新密码输入不一致！");
                return;
            }

            $.ajax({
                type:'post',
                url: projectUrl + '/sign/modifyPassword',
                dataType: 'json',
                data:{userId:userId,newPassword:newP,oldPassword:oldP},
                // async:false,
                success:function (data) {
                    if(data.code >= 1){
                        alert(data.msg);
                    }
                    if(data.code == 1){
                        $("#oldPassword").val("");
                        $("#newPassword").val("")
                        $("#newPasswordAffirm").val("");
                        $("#password").val(newP);
                        $("#modifyModal").modal('hide');
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
    
    $("#torepay").click(function () {
        var repaychk = $("input[name='repaychk']:checked");
        if(repaychk.length <= 0){
            alert("请选择要还款的期次!");
            return;
        }
        var repays = "";
        for(var i=0;i<repaychk.length;i++){
            var ii = repaychk[i];
            repays += "," + $(ii).val();
        }
        repays = repays.substring(1);
        getrepyaqrcode(repays);
    });

    myloanTablePageChange(1);
});
function myloanTablePageChange(pageNo) {

    var params = {};
    params.pageNo = pageNo;
    params.tableId = "myloanTable";

    $.ajax({
        type:'post',
        url: projectUrl + '/loan/myloanlist',
        dataType: 'json',
        data:params,
        success:function (data) {
            var myloanList = data.loanList;
            var loan = "";
            if(myloanList.length > 0){
                for(var i=0;i<myloanList.length;i++){
                    var ii = myloanList[i];
                    var ispayoff = ii.ispayoff;
                    var state = ii.state;
                    loan += "<tr>";
                    loan += "<td>" + ii.loanpurpose + "</td>";
                    loan += "<td>" + ii.loanamount + "元</td>";
                    loan += "<td>" + new Date(ii.createdate).Format("yyyy-MM-dd hh:mm:ss") + "</td>";
                    loan += "<td>" + ii.loanage + "月</td>";
                    loan += "<td>" + ii.repayyet + "元</td>";
                    if(ii.updatedate){
                        loan += "<td>" + new Date(ii.updatedate).Format("yyyy-MM-dd hh:mm:ss") + "</td>";
                    }else{
                        loan += "<td>&nbsp;</td>";
                    }
                    if(state == 1){
                        loan += "<td>已还" + (ii.stagenumyet * 1) + "期&nbsp;&nbsp;";
                        loan += "<a href='javascript:void 0;' onclick=\"todetail('" + ii.id + "')\">查看还款明细</a>";
                        loan += "</td>";

                        if(ispayoff == 1){
                            loan += "<td>已还清</td>";
                        }else{
                            loan += "<td>";
                            loan += "<a href='javascript:void 0;' onclick=\"todetail('" + ii.id + "')\">去还款</a>";
                            loan += "</td>";
                        }
                    }else if(state == 0){
                        loan += "<td>等待审核放款</td>";
                        loan += "<td>&nbsp;</td>";
                    }else{
                        loan += "<td>申请失败</td>";
                        loan += "<td>&nbsp;</td>";
                    }



                    loan += "</tr>";
                }
            }
            $("#myloanTable tbody").html(loan);
            $("#pageChanger").html(data.pageChanger);
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

function todetail(loanid) {
    repaydetailTablePageChange(1,loanid);
    $("#repaydetailModal").modal("show");
}

function repaydetailTablePageChange(pageNo,loanid) {
    currentrepaypageno = pageNo;
    var params = {};
    params.loanid = loanid;
    params.pageNo = pageNo;
    params.tableId = "repaydetailTable";

    $.ajax({
        type:'post',
        url: projectUrl + '/loan/myrepaydetaillist',
        dataType: 'json',
        data:params,
        success:function (data) {
            var repaydetailList = data.repaydetailList;
            var repaydetail = "";
            if(repaydetailList.length > 0){
                for(var i=0;i<repaydetailList.length;i++){
                    var ii = repaydetailList[i];
                    var isrepay = ii.isrepay;
                    repaydetail += "<tr>";
                    if(isrepay != 1){
                        repaydetail += "<td><input type='checkbox' name='repaychk' value='" + ii.loanid + "::" + ii.id + "' /></td>";
                    }else{
                        repaydetail += "<td>&nbsp;</td>";
                    }
                    repaydetail += "<td>" + ii.stagenum + "</td>";
                    repaydetail += "<td>" + ii.repaymoney + "元</td>";
                    repaydetail += "<td>" + new Date(ii.repaydateplan).Format("yyyy-MM-dd hh:mm:ss") + "</td>";
                    if(ii.repaydatereal){
                        repaydetail += "<td>" + new Date(ii.repaydatereal).Format("yyyy-MM-dd hh:mm:ss") + "</td>";
                    }else{
                        repaydetail += "<td>&nbsp;</td>";
                    }
                    if(isrepay == 1){
                        repaydetail += "<td>已还款</td>";
                    }else{
                        repaydetail += "<td>";
                        repaydetail += "<a href='javascript:void 0;' onclick=\"getrepyaqrcode('" + ii.loanid + "::" + ii.id + "')\">还款</a>";
                        repaydetail += "</td>";
                    }

                    repaydetail += "</tr>";
                }
            }
            $("#repaydetailTable tbody").html(repaydetail);
            $("#pageChanger02").html(data.pageChanger);
            $("input[name='repaychk']").click(function () {
                var chknum = $("input[name='repaychk']").length;
                var chkednum = $("input[name='repaychk']:checked").length;
                if(chkednum == chknum){
                    $("#allrepay").prop("checked",true);
                }else{
                    $("#allrepay").prop("checked",false);
                }
            });
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

function getrepyaqrcode(repays){
    $.ajax({
        type:'post',
        url: projectUrl + '/loan/getrepyaqrcode',
        dataType: 'json',
        data:{repays:repays},
        success:function (data) {
            var qrcodeurl = data.qrcodeurl;
            $("#repayqrcode").attr("src",projectUrl + qrcodeurl);
            $("#repayqrcodeModal").modal("show");
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

function torepay(repays) {
    $.ajax({
        type:'post',
        url: projectUrl + '/loan/repay',
        dataType: 'json',
        data:{repays:repays},
        success:function (data) {
            alert(data.message);
            repaydetailTablePageChange(currentrepaypageno,repays.split("::")[0]);
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