/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#creditmoneyDialog").dialog({
        width: 450,
        height: 300,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"提交",
                handler:function(){
                    var formData = {};
                    var postUrl = projectUrl + "/consoles/savecreditmoney";
                    var testData = $("#creditmoneyForm").serializeArray();

                    for (var item in testData) {
                        formData["" + testData[item].name + ""] = testData[item].value;
                    }
                    $.ajax({
                        type:'post',
                        url: postUrl,
                        // async:false,
                        dataType:'json',
                        data:formData,
                        success:function (data) {

                            alert(data.message);
                            if(data.code >= 1){
                                var params = {};
                                loadDataGrid(params);
                                $("#creditmoneyForm").form('clear');
                                closeDialog("creditmoneyDialog");
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
            },
            {
                "text":"取消",
                handler:function () {
                    $("#creditmoneyForm").form('clear');
                    closeDialog("creditmoneyDialog");
                }
            }
        ]
    });

    $("#toAdd").click(function () {
        $("#creditmoneyForm").form('clear');
        $("#creditmoneyId").val(0);
        $("#state").combobox("setValue",1);
        $("#creditmoneyDialog").dialog('open');
    });

    $("#toEdit").click(function () {
        $("#creditmoneyForm").form('clear');
        var selectRows = $("#creditmoneyList").datagrid('getSelections');
        if(selectRows.length > 1){
            alert("只能编辑一条数据!");
            return;
        }
        if(selectRows.length < 1){
            alert("请选择一条数据!");
            return;
        }
        loadDataToForm(selectRows[0]);
        $("#creditmoneyDialog").dialog('open');
    });

    $("#toDel").click(function () {

        var selectRows = $("#creditmoneyList").datagrid('getSelections');
        if(selectRows.length < 1){
            alert("请至少选择一条数据!");
            return;
        }
        var selectIds = "";
        for(var i=0;i<selectRows.length;i++){
            var ii = selectRows[i];
            selectIds += "," + ii.id;
        }
        selectIds = selectIds.substring(1);
        $.messager.confirm('提示','确定要删除选中数据吗?',function(r){
            if (r){
                $.ajax({
                    type:'post',
                    url: projectUrl + "/consoles/deletecreditmoney",
                    // async:false,
                    dataType:'json',
                    data:{ids:selectIds},
                    success:function (data) {
                        alert(data.msg);
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
    $("#creditmoneyForm").form('clear');
    $("#" + dialogId).dialog("close");
}

function loadDataGrid(params) {
    params.pageNumber = 1;
    params.pageSize = 10;
    $("#creditmoneyList").datagrid({
        url:"/consoles/creditmoneylist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true"},
            {field:"id",title:"额度Id",width:"80",hidden:true},
            {field:"creditlevel",title:"信用分等级",width:"200"},
            {field:"thresholdscore",title:"信用分阈值",width:"200"},
            {field:"loanmoney",title:"最大可贷款金额",width:"200",
                formatter: function(value,row,index){
                    if(value){
                        return value + "元";
                    }
                    return '';
                }}
        ]]
    });
    datagridpager($("#creditmoneyList"),"/consoles/creditmoneylist");
}

function loadDataToForm(data){

    $("#creditmoneyId").val(data.id);
    $("#creditlevel").val(data.creditlevel);
    $("#thresholdscore").val(data.thresholdscore);
    $("#loanmoney").val(data.loanmoney);
}