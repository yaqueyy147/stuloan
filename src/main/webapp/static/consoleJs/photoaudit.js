/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#photoDialog").dialog({
        width: 350,
        height: 250,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"关闭",
                handler:function () {
                    closeDialog("photoDialog");
                }
            }
        ]
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
    $("#photoList").datagrid({
        url:"/consoles/photolist",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true",hidden:true},
            {field:"loginname",title:"账号",width:"120"},
            {field:"headphoto",title:"头像",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return "<img src=\"" + value + "\" width=\"100px\" height=\"50px\" onclick=\"viewphoto('" + row.id + "','" + value + "','1')\" />";
                    }
                    return '';
                }},
            {field:"headstate",title:"头像状态",width:"200",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "头像合格";
                    }else if(value == 5){
                        var oo = "待审核";
                        oo += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"toaudit('" + row.id + "','1','1')\">合格</a>";
                        oo += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"toaudit('" + row.id + "','2','1')\">不合格</a>";
                        return oo;
                    }else if(value == 2){
                        return "头像不合格";
                    }
                    return '';
                }},
            {field:"idcardphoto",title:"身份证照",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return "<img src=\"" + value + "\" width=\"100px\" height=\"50px\" onclick=\"viewphoto('" + row.id + "','" + value + "','2')\" />";
                    }
                    return '';
                }},
            {field:"idcardstate",title:"身份证照状态",width:"200",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "身份证照合格";
                    }else if(value == 5){
                        var oo = "待审核";
                        oo += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"toaudit('" + row.id + "','1','2')\">合格</a>";
                        oo += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"toaudit('" + row.id + "','2','2')\">不合格</a>";
                        return oo;
                    }else if(value == 2){
                        return "身份证照不合格";
                    }
                    return '';
                }},
            {field:"stuidcardphoto",title:"学生证照",width:"100",
                formatter: function(value,row,index){
                    if(value){
                        return "<img src=\"" + value + "\" width=\"100px\" height=\"50px\" onclick=\"viewphoto('" + row.id + "','" + value + "','3')\" />";
                    }
                    return '';
                }},
            {field:"stuidcardstate",title:"学生证照状态",width:"200",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "学生证照合格";
                    }else if(value == 5){
                        var oo = "待审核";
                        oo += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"toaudit('" + row.id + "','1','3')\">合格</a>";
                        oo += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"toaudit('" + row.id + "','2','3')\">不合格</a>";
                        return oo;
                    }else if(value == 2){
                        return "学生证照不合格";
                    }
                    return '';
                }},
            {field:"operate",title:"操作",width:"200",
                formatter: function(value,row,index){
                    var oo = "图片已审核通过";
                    if(row.stuidcardstate != 1 || row.idcardstate != 1 || row.headstate != 1){
                        oo = "<a href=\"javascript:void 0;\" onclick=\"toaudit('" + row.id + "','1','0')\">全部合格</a>";
                        oo += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"toaudit('" + row.id + "','2','0')\">全部不合格</a>";
                    }
                    return oo;
                }}
        ]],
        loadFilter:pagerFilter
    });
}
function toaudit(photoid,state,type){
    $.ajax({
        type: 'post',
        url: projectUrl + "/consoles/auditphoto",
        dataType: 'json',
        data: {photoid: photoid,state:state,type:type},
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

function viewphoto(photoid,photourl,type){
    if(type == 1){
        $("#photoDialog").dialog({title: "头像"});
    }
    if(type == 2){
        $("#photoDialog").dialog({title: "身份证照"});
    }
    if(type == 3){
        $("#photoDialog").dialog({title: "学生证照"});
    }
    var licenseImg = "<img src=\"" + photourl + "\" style=\"width:100%;height:100%\" />";
    $("#photoDialog").html(licenseImg);
    $("#photoDialog").dialog("open");
}