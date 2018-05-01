/**
 * Created by suyx on 2016/12/18.
 */
var currentrepaypageno;
$(function () {

    $("#toidentity").click(function () {
        $.ajax({
            type:'post',
            url: projectUrl + '/loan/toidentity',
            dataType: 'json',
            data:{},
            // async:false,
            success:function (data) {
                if(data.code >= 1){
                    alert(data.msg);
                    $("#loanopera").replaceWith("<span>已申请认证，请等待审核！</span>");
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

    $("#repaybatch").click(function () {
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

    repaydetailTablePageChange(1);
});

function repaydetailTablePageChange(pageNo,loanid) {
    currentrepaypageno = pageNo;
    var params = {};
    params.pageNo = pageNo;
    params.tableId = "repaydetailTable";

    $.ajax({
        type:'post',
        url: projectUrl + '/loan/myrepaydetaillistall',
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
                    }
                    else{
                        repaydetail += "<td>&nbsp;</td>";
                    }
                    repaydetail += "<td>" + ii.loanpurpose + "</td>";
                    repaydetail += "<td>" + ii.stagenum + "</td>";
                    repaydetail += "<td>" + ii.repaymoney + "元</td>";
                    repaydetail += "<td>" + new Date(ii.repaydateplan).Format("yyyy-MM-dd hh:mm:ss") + "</td>";
                    if(ii.repaydatereal){
                        repaydetail += "<td>" + new Date(ii.repaydatereal).Format("yyyy-MM-dd hh:mm:ss") + "</td>";
                    }else{
                        repaydetail += "<td>&nbsp;</td>";
                    }
                    if(isrepay == 1){
                        repaydetail += "<td>已还</td>";
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