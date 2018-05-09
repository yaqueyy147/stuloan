/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#doSearch").click(function () {
        var params = {};
        params.stuname = "%" + $("#loanpp").val() + "%";
        loadDataGrid(params);
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

    $("a[name='audit']").click(function () {
        var state = $(this).attr("data-state");
        var selectRows = $("#loanList").datagrid('getSelections');
        if(selectRows.length < 1){
            $.messager.alert('提示',"请选择一条数据!");
            return;
        }
        var selectIds = "";
        for(var i=0;i<selectRows.length;i++){
            var ii = selectRows[i];
            selectIds += "," + ii.id;
        }
        selectIds = selectIds.substring(1);
        var tipmsg = "确定要同意选择的贷款申请吗？";
        if(state == 2){
            tipmsg = "确定要驳回选择的贷款申请吗？";
        }
        $.messager.confirm('提示',tipmsg,function(r){
            if (r){
                $.ajax({
                    type:'post',
                    url: projectUrl + "/consoles/auditloan",
                    dataType:'json',
                    data:{ids:selectIds,state:state},
                    success:function (data) {
                        alert(data.message);
                        var params = {};
                        loadDataGrid(params);
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
    params.state = 0;
    // var dataList = getData("/consoles/roleList",params).dataList;
    // dataList = formatDataList(dataList);
    $("#loanList").datagrid({
        url:"/consoles/loanlist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true"},
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
            {field:"state",title:"状态",width:"200",
                formatter: function(value,row,index){
                if(value == 1){
                    return "已同意";
                }
                if(value == 0){
                    var audit = "<a href='javascript:void 0;' onclick=\"toaudit(this,'" + row.id + "','5')\">同意贷款</a>&nbsp;&nbsp;";
                    audit += "&nbsp;&nbsp;<a href='javascript:void 0;' onclick=\"toaudit(this,'" + row.id + "','2')\">不同意贷款</a>";
                    return audit;
                }
                if(value == 5){
                    return "已同意，待放款";
                }
                return '不同意';
            }}
        ]]
    });
    datagridpager($("#loanList"),"/consoles/loanlist");
}

function toaudit(obj,ids,state) {
    $.ajax({
        type:'post',
        url: projectUrl + "/consoles/auditloan",
        dataType:'json',
        data:{ids:ids,state:state},
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
