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
                    hh += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"frozenuser('" + row.id + "','" + row.loginname + "')\">冻结账号</a>";
                    return hh;
                }}
        ]]
    });
    datagridpager($("#loanList"),"/consoles/overdueloanlist");
}

function frozenuser(userid,loginname) {
    $.messager.confirm('提示','确定要冻结账号(' + loginname + ')  吗?',function(r) {
        if (r) {
            $.ajax({
                type: 'post',
                url: projectUrl + "/consoles/frozenuser",
                dataType: 'json',
                data: {userid: userid},
                success: function (data) {
                    $.messager.alert('提示', data.message);
                    if (data.code >= 1) {
                        var params = {};
                        loadDataGrid(params);
                    }
                },
                error: function (data) {
                    var responseText = data.responseText;
                    if (responseText.indexOf("登出跳转页面") >= 0) {
                        ajaxErrorToLogin();
                    } else {
                        alert(JSON.stringify(data));
                    }
                }
            });
        }
    });
}
