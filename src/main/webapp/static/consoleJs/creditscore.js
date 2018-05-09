/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("a[name='auditcredit']").click(function () {
        var identitystate = $(this).arrr("data-identity");
        var selectRows = $("#creditList").datagrid('getSelections');
        if(selectRows.length < 1){
            alert("请选择一条数据!");
            return;
        }
        var selectIds = "";
        for(var i=0;i<selectRows.length;i++){
            var ii = selectRows[i];
            selectIds += "," + ii.id;
        }
        selectIds = selectIds.substring(1);
        toauditcredit(selectIds,identitystate);
    });

    var params = {};
    loadDataGrid(params);

});

function closeDialog(dialogId){
    $("#" + dialogId).dialog("close");
}

function loadDataGrid(params) {
    params.pageNumber = 1;
    params.pageSize = 10;
    // var dataList = getData("/consoles/roleList",params).dataList;
    // dataList = formatDataList(dataList);
    $("#creditList").datagrid({
        url:"/consoles/creditscorelist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true"},
            {field:"id",title:"费率Id",width:"80",hidden:true},
            {field:"userid",title:"用户Id",width:"80",hidden:true},
            {field:"loginname",title:"姓名",width:"100"},
            {field:"creditscoretotal",title:"信用总分",width:"100"},
            {field:"identityscore",title:"身份分",width:"100"},
            {field:"performscore",title:"履约分",width:"100"},
            {field:"historyscore",title:"历史信用分",width:"100"},
            {field:"peoplekeepscore",title:"人脉分",width:"100"},
            {field:"behaviorscore",title:"行为分",width:"100"},
            {field:"state",title:"状态",width:"200",
                formatter: function(value,row,index){
                if(value == 1){
                    return "已通过认证";
                }else if(value == 2){
                    return "未通过认证";
                }
                var identity = "<a href='javascript:void 0;' onclick=\"toauditcredit('" + row.id + "','1')\">同意认证</a>&nbsp;&nbsp;";
                identity += "<a href='javascript:void 0;' onclick=\"toauditcredit('" + row.id + "','0')\">不同意认证</a>";
                return identity;
            }}
        ]]
    });
    datagridpager($("#creditList"),"/consoles/creditscorelist");
}
function toauditcredit(ids,state) {
    $.ajax({
        type:'post',
        url: projectUrl + "/consoles/toauditcredit",
        dataType:'json',
        data:{ids:ids,state:state},
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