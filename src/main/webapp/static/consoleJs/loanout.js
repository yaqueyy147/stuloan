/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#doSearch").click(function () {
        var params = {};
        params.stuname = "%" + $("#loanpp").val() + "%";
        loadDataGrid(params);
    });

    $("#payqrcodeDialog").dialog({
        width: 400,
        height: 400,
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
            {field:"alipayname",title:"支付宝账号",width:"120"},
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
                    return "已放款";
                }
                if(value == 0){
                    var audit = "<a href='javascript:void 0;' onclick=\"toloanout(this,'" + row.id + "','1')\">放款</a>&nbsp;&nbsp;";
                    audit += "&nbsp;&nbsp;<a href='javascript:void 0;' onclick=\"toloanout(this,'" + row.id + "','2')\">不同意贷款</a>";
                    return audit;
                }
                if(value == 5){
                    var html = "已同意，待";
                    html += "<a href='javascript:void 0;' onclick=\"toloanout(this,'" + row.id + "','1','" + row.stuname + "','" + row.alipayname + "')\">放款</a>";
                    return html;
                }
                return '不同意';
            }}
        ]]
    });
    datagridpager($("#loanList"),"/consoles/loanlist");
}

function toloanout(obj,id,state,stuname,alipayname) {
    $.messager.confirm('提示',"您正在向(" + stuname + ")的放款转账，确认转账款项会转入对方支付宝账号(" + alipayname + ")，确认转账吗?",function(r){
        if (r){
            $.messager.progress({title:"等待",msg:"处理中，请稍后"});
            $.ajax({
                type:'post',
                url: projectUrl + "/consoles/transferpay",
                dataType:'json',
                data:{id:id,state:state},
                success:function (data) {
                    $.messager.confirm('提示',data.message);
                    if(data.code == 1){
                        var params = {};
                        loadDataGrid(params);

                        // if(state == 1){
                            // var qrcodeurl = data.qrcodeurl;
                            // var qrcode = "<img src=\"" + qrcodeurl + "\" style=\"width:256px;height:256px\" />";
                            // $("#payqrcodeDialog").html(qrcode);
                            // $("#payqrcodeDialog").dialog("open");
                        // }
                    }
                    $.messager.progress('close');
                },
                error:function (data) {
                    var responseText = data.responseText;
                    if(responseText.indexOf("登出跳转页面") >= 0){
                        ajaxErrorToLogin();
                    }else{
                        alert(JSON.stringify(data));
                    }
                    $.messager.progress('close');
                }
            });
        }
    });

}