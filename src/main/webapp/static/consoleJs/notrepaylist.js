/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#doSearch").click(function () {
        var begindate = $("#begindate").val();
        var enddate = $("#enddate").val();
        var params = {begindate:begindate,enddate:enddate};
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
    $("#notrepayList").datagrid({
        url:"/consoles/notrepaylist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true",hidden:true},
            {field:"loginname",title:"账号",width:"120"},
            {field:"stuname",title:"姓名",width:"120"},
            {field:"loanpurpose",title:"贷款用途",width:"120"},
            {field:"stagenum",title:"期次",width:"120",
                formatter: function(value,row,index){
                    if(value) {
                        return "第" + value + "期";
                    }
                    return "";
                }},
            {field:"repaymoney",title:"应还款金额",width:"120",
                formatter: function(value,row,index){
                    if(value) {
                        return value + "元";
                    }
                    return "";
                }},
            {field:"repaydateplan",title:"应还款时间",width:"120",
                formatter: function(value,row,index){
                    if(value){
                        return new Date(value).Format("yyyy-MM-dd");
                    }
                    return '';
                }}
        ]],
        loadFilter:pagerFilter
    });
}
