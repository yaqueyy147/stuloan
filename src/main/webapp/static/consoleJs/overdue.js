/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#repaydetailDialog").dialog({
        width: 800,
        height: 500,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"关闭",
                handler:function () {
                    closeDialog("repaydetailDialog");
                }
            }
        ]
    });

    $("#doSearch").click(function () {
        var loanstate = $("#loanstate").val();
        var params = {state:loanstate};
        loadDataGrid(params);
    });

    var params = {};
    loadDataGrid(params);

});

function closeDialog(dialogId){
    $("#stagefeeForm").form('clear');
    $("#" + dialogId).dialog("close");
}

function loadDataGrid(params) {
    params.pageNumber = 1;
    params.pageSize = 10;
    // var dataList = getData("/consoles/roleList",params).dataList;
    // dataList = formatDataList(dataList);
    $("#loanList").datagrid({
        url:"/consoles/overdueloanlist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true",hidden:true},
            {field:"loginname",title:"账号",width:"120"},
            {field:"stuname",title:"姓名",width:"120"},
            {field:"overduecount",title:"逾期次数",width:"100",
                formatter: function(value,row,index){
                    if(value && value > 0){
                        // return "<a href=\"javascript:void 0;\" onclick=\"viewrepay('" + row.id + "')\">" + value + "次</a>";
                        return value + "次";
                    }
                    return '0次';
                }},
            {field:"operate",title:"操作",width:"120",
                formatter: function(value,row,index){
                    var hh = "";
                    hh += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"frozenuser('" + row.id + "','" + row.userid + "')\">冻结账号</a>";
                    return hh;
                }}
        ]],
        loadFilter:pagerFilter
    });
}

function frozenuser(loanid,userid) {
    $.ajax({
        type:'post',
        url: projectUrl + "/consoles/frozenuser",
        dataType:'json',
        data:{userid:userid},
        success:function (data) {
            $.messager.alert('提示',data.message);
            if(data.code >= 1){
                var params = {};
                loadDataGrid(params);
                // if(state == 1){
                //     var qrcodeurl = data.qrcodeurl;
                //     var qrcode = "<img src=\"" + qrcodeurl + "\" style=\"width:100%;height:100%\" />";
                //     $("#payqrcodeDialog").html(qrcode);
                //     $("#payqrcodeDialog").dialog("open");
                // }
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
function viewrepay(loanid){
    $("#repaydetailDialog").dialog("open");
    loadrepaydetail(loanid);
}
function loadrepaydetail(loanid) {
    var params = {};
    params.loanid = loanid;
    params.pageNumber = 1;
    params.pageSize = 10;
    params.isoverdue = 1;
    params.orderby = "stagenum desc";
    $("#repayList").datagrid({
        url:"/consoles/viewrepaydetail",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true",hidden:true},
            {field:"id",title:"还款Id",width:"80",hidden:true},
            {field:"stagenum",title:"还款期次",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return "第" + value + "期";
                    }
                    return '';
                }},
            {field:"repaymoney",title:"金额",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return value + "元";
                    }
                    return '';
                }},
            {field:"repaydateplan",title:"预计还款时间",width:"150",
                formatter: function(value,row,index){
                    if(value){
                        return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                    }
                    return '';
                }},
            {field:"repaydatereal",title:"实际还款时间",width:"150",
                formatter: function(value,row,index){
                    if(value){
                        return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
                    }
                    return '';
                }},
            {field:"isrepay",title:"是否已还本期",width:"80",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "<span style='color:#00ff00'>已还</span>";
                    }
                    return "<span style='color:#ff0000'>未还</span>";
                }},
            {field:"isoverdue",title:"是否逾期",width:"80",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "已逾期";
                    }
                    return '';
                }}
        ]],
        loadFilter:pagerFilter
    });
}