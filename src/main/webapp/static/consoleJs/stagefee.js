/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#stagefeeDialog").dialog({
        width: 400,
        height: 300,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"提交",
                handler:function(){
                    var rows = $("#stagefeeList").datagrid("getRows");
                    var stagenum = $("#stagenum").val();
                    for(var i=0;i<rows.length;i++)
                    {
                        //获取每一行的数据
                        if(parseInt(rows[i].stagenum) == parseInt(stagenum)){
                            $.messager.alert("提示","已存在分(" + stagenum + ")期的数据");
                            return;
                        }
                    }

                    var formData = {};
                    var postUrl = projectUrl + "/consoles/savestagefee";
                    var testData = $("#stagefeeForm").serializeArray();

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

                            $.messager.alert("提示",data.message);
                            if(data.code >= 1){
                                var params = {};
                                loadDataGrid(params);
                                $("#stagefeeForm").form('clear');
                                closeDialog("stagefeeDialog");
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
                    $("#stagefeeForm").form('clear');
                    closeDialog("stagefeeDialog");
                }
            }
        ]
    });

    $("#toAdd").click(function () {
        $("#stagefeeForm").form('clear');
        $("#stagefeeId").val(0);
        $("#state").combobox("setValue",1);
        $("#stagefeeDialog").dialog('open');
    });

    $("#toEdit").click(function () {
        $("#stagefeeForm").form('clear');
        var selectRows = $("#stagefeeList").datagrid('getSelections');
        if(selectRows.length > 1){
            alert("只能编辑一条数据!");
            return;
        }
        if(selectRows.length < 1){
            alert("请选择一条数据!");
            return;
        }
        loadDataToForm(selectRows[0]);
        $("#stagefeeDialog").dialog('open');
    });

    $("#toDel").click(function () {

        var selectRows = $("#stagefeeList").datagrid('getSelections');
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
                    url: projectUrl + "/consoles/deletestagefee",
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
    $("#stagefeeForm").form('clear');
    $("#" + dialogId).dialog("close");
}

function loadDataGrid(params) {
    params.pageNumber = 1;
    params.pageSize = 10;
    params.orderby = "stagenum asc";
    $("#stagefeeList").datagrid({
        url:"/consoles/stagefeelist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true"},
            {field:"id",title:"费率Id",width:"80",hidden:true},
            {field:"stagenum",title:"分期期数",width:"150"},
            {field:"feepercent",title:"分期费率",width:"80",
                formatter: function(value,row,index){
                    if(value){
                        return value + "%";
                    }
                    return '';
                }},
            {field:"state",title:"状态",width:"80",
                formatter: function(value,row,index){
                if(value == 1){
                    return "可用";
                }
                return '不可用';
            }}
        ]]
    });
    datagridpager($("#stagefeeList"),"/consoles/stagefeelist");
}

function loadDataToForm(data){

    $("#stagefeeId").val(data.id);
    $("#stagenum").val(data.stagenum);
    $("#feepercent").val(data.feepercent);
    $("#state").combobox("setValue",data.state);
}