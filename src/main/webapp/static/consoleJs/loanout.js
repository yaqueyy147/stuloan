/**
 * Created by suyx on 2017/1/12.
 */
$(function () {
    $("#payqrcodeDialog").dialog({
        width: 400,
        height: 300,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"关闭",
                handler:function () {
                    closeDialog("payqrcodeDialog");
                }
            }
        ]
    });

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
    params.state = 5;
    // var dataList = getData("/consoles/roleList",params).dataList;
    // dataList = formatDataList(dataList);
    $("#loanList").datagrid({
        url:"/consoles/loanlist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true",hidden:true},
            {field:"id",title:"贷款Id",width:"80",hidden:true},
            {field:"stuname",title:"贷款人",width:"120"},
            {field:"loanpurpose",title:"贷款用途",width:"150"},
            {field:"loanamount",title:"贷款金额",width:"80",
                formatter: function(value,row,index){
                    if(value){
                        return value + "元";
                    }
                    return '';
                }},
            {field:"loanage",title:"贷款期限",width:"80",
                formatter: function(value,row,index){
                    if(value){
                        return value + "月";
                    }
                    return '';
                }},
            {field:"repayyet",title:"已还款金额",width:"80",
                formatter: function(value,row,index){
                    if(value){
                        return value + "元";
                    }
                    return '';
                }},
            {field:"stagenumyet",title:"已还款期数",width:"80",
                formatter: function(value,row,index){
                    if(value){
                        return value + "月";
                    }
                    return '';
                }},
            {field:"state",title:"状态",width:"200",
                formatter: function(value,row,index){
                if(value == 1){
                    return "已放款";
                }
                if(value == 0){
                    var audit = "<a href='javascript:void 0;' onclick=\"toloanout(this,'" + row.id + "','1')\">放款</a>&nbsp;&nbsp;";
                    audit += "&nbsp;&nbsp;<a href='javascript:void 0;' onclick=\"toloanout(this,'" + row.id + "','2')\">不同意贷款</a>";
                    return audit;
                }
                if(value == 5){
                    var html = "已同意，待";
                    html += "<a href='javascript:void 0;' onclick=\"toloanout(this,'" + row.id + "','1')\">放款</a>";
                    return html;
                }
                return '不同意';
            }},
            {field:"ispayoff",title:"是否已还清",width:"80",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "已还清";
                    }
                    return '未还清';
                }},
            {field:"operate",title:"操作",width:"120",
                formatter: function(value,row,index){
                    return "<a href=\"javascript:void 0;\" onclick=\"viewrepay('" + row.id + "')\">查看还款明细</a>";
                }}
        ]],
        loadFilter:pagerFilter
    });
}

function toloanout(obj,id,state) {
    $.ajax({
        type:'post',
        url: projectUrl + "/consoles/getloanqrcode",
        dataType:'json',
        data:{id:id,state:state},
        success:function (data) {

            if(data.code >= 1){
                var params = {};
                loadDataGrid(params);
                if(state == 1){
                    var qrcodeurl = data.qrcodeurl;
                    var qrcode = "<img src=\"" + qrcodeurl + "\" style=\"width:100%;height:100%\" />";
                    $("#payqrcodeDialog").html(qrcode);
                    $("#payqrcodeDialog").dialog("open");
                }
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