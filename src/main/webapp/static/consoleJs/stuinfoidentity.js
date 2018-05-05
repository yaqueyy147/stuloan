/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("a[name='auditidentity']").click(function () {
        var identitystate = $(this).arrr("data-identity");
        var selectRows = $("#stuinfoList").datagrid('getSelections');
        if(selectRows.length < 1){
            $.messager.alert('提示',"请选择一条数据!");
            // alert("请选择一条数据!");
            return;
        }
        var selectIds = "";
        for(var i=0;i<selectRows.length;i++){
            var ii = selectRows[i];
            selectIds += "," + ii.id;
        }
        selectIds = selectIds.substring(1);
        toauditidentity(selectIds,identitystate);
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
    $("#stuinfoList").datagrid({
        url:"/consoles/stuinfolist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true"},
            {field:"id",title:"费率Id",width:"80",hidden:true},
            {field:"userid",title:"用户Id",width:"80",hidden:true},
            {field:"stuname",title:"姓名",width:"100"},
            {field:"stusex",title:"性别",width:"100",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "男";
                    }
                    return "女";
                }},
            {field:"stuidcard",title:"身份证号",width:"100"},
            {field:"school",title:"学校",width:"100"},
            {field:"edulevel",title:"学历层次",width:"100"},
            {field:"major",title:"专业",width:"100"},
            {field:"edusystem",title:"学制",width:"100"},
            {field:"edutype",title:"学历类别",width:"100"},
            {field:"edumode",title:"学习形式",width:"100"},
            {field:"branch",title:"分院",width:"100"},
            {field:"classgrade",title:"班级",width:"100"},
            {field:"stunum",title:"学号",width:"100"},
            {field:"admissiondate",title:"入校时间",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return value;
                    }
                    return "";
                }},
            {field:"leavedate",title:"离校时间",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return value;
                    }
                    return "";
                }},
            {field:"stustate",title:"学籍状态",width:"100"},
            {field:"remark",title:"备注",width:"100"},
            {field:"isstuidentity",title:"状态",width:"200",
                formatter: function(value,row,index){
                if(value == 1){
                    return "已通过认证";
                }else if(value == 2){
                    return "未通过认证";
                }
                var identity = "<a href='javascript:void 0;' onclick=\"toauditidentity('" + row.id + "','1')\">同意认证</a>&nbsp;&nbsp;";
                identity += "<a href='javascript:void 0;' onclick=\"toauditidentity('" + row.id + "','0')\">不同意认证</a>";
                return identity;
            }}
        ]],
        loadFilter:pagerFilter
    });
}
function toauditidentity(ids,state) {
    $.ajax({
        type:'post',
        url: projectUrl + "/consoles/toauditstuinfo",
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