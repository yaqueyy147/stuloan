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
    $("#blackList").datagrid({
        url:"/consoles/blacklistdata",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        pageSize:10,
        nowrap: true,
        columns:[[
            {field:"ck",checkbox:"true",hidden:true},
            {field:"loginname",title:"账号",width:"120"},
            {field:"stuname",title:"姓名",width:"120"},
            {field:"overduecount",title:"逾期次数",width:"100",
                formatter: function(value,row,index){
                    if(value && value > 0){
                        return value + "次";
                    }
                    return '0次';
                }},
            {field:"operate",title:"操作",width:"120",
                formatter: function(value,row,index){
                    var hh = "无";
                    if(row.overduecount > 0){
                        hh = "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"viewrepay('" + row.id + "')\">逾期明细</a>";
                    }
                    return hh;
                }}
        ]]
    });

    datagridpager($("#blackList"),"/consoles/blacklistdata");
}

function viewrepay(balckid){
    $("#repaydetailDialog").dialog("open");
    loadrepaydetail(balckid);
}
function loadrepaydetail(balckid) {
    var params = {};
    params.balckid = balckid;
    params.pageNumber = 1;
    params.pageSize = 10;
    params.orderby = "stagenum desc";
    $("#repayList").datagrid({
        url:"/consoles/overduedetail",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true",hidden:true},
            {field:"id",title:"还款Id",width:"80",hidden:true},
            {field:"loanpurpose",title:"贷款用途",width:"120"},
            {field:"stagenum",title:"逾期期次",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return "第" + value + "期";
                    }
                    return '';
                }},
            {field:"repaymoney",title:"应还款金额",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return value + "元";
                    }
                    return '';
                }},
            {field:"repaydateplan",title:"应还款时间",width:"150",
                formatter: function(value,row,index){
                    if(value){
                        return new Date(value).Format("yyyy-MM-dd");
                    }
                    return '';
                }}
        ]]
    });
    datagridpager($("#repayList"),"/consoles/overduedetail");
}