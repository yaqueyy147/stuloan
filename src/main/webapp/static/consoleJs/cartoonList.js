/**
 * Created by suyx on 2017/1/12.
 */
$(function () {

    $("#province4Search").val("");
    $("#province4Search").change();

    $("input[name='visitStatus']").click(function () {
        var status = $(this).val();
        if(status == 0){
            $("#visitPwdTitle").show();
            $("#visitPwd").show();
        }else{
            $("#visitPwdTitle").hide();
            $("#visitPwd").hide();
            $("#visitPassword").val("");
        }
    });

    $("#familyDialog").dialog({
        width: 800,
        height: 550,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"提交",
                handler:function(){
                    if($.trim($("#familyName")).length <= 0){
                        alert("请输入家族名称！");
                        return;
                    }
                    if($.trim($("#province")).length <= 0){
                        alert("请选择家族所在省！");
                        return;
                    }
                    if($.trim($("#city")).length <= 0){
                        alert("请选择家族所在市！");
                        return;
                    }
                    if($.trim($("#district")).length <= 0){
                        alert("请选择家族所在区县！");
                        return;
                    }
                    var formData = {};
                    var postUrl = projectUrl + "/consoles/saveFamily";
                    var testData = $("#familyForm").serializeArray();
                    for (var item in testData) {
                        formData["" + testData[item].name + ""] = testData[item].value;
                    }
                    $.ajax({
                        type:'post',
                        url: postUrl,
                        dataType:'json',
                        data:formData,
                        // async:false,
                        success:function (data) {

                            alert(data.msg);
                            if(data.code >= 1){
                                var params = {};
                                loadFamilyList(params);
                                $("#familyForm").form('clear');
                                closeDialog("familyDialog");
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
                    $("#userInfoForm").form('clear');
                    closeDialog("familyDialog");
                }
            }
        ]
    });

    $("#doSearch").click(function () {
        var params = {};
        params.familyName = $("#familyName4Search").val();
        params.province = $("#province4Search").val();
        params.city = $("#city4Search").val();
        params.district = $("#district4Search").val();
        loadFamilyList(params);
    });

    $("#toAdd").click(function () {
        $("#familyForm")[0].reset();
        $("#familyId").val(0);
        $("#createMan").val("");
        $("#familyArea").val(0);

        $("#result_img").hide();
        $("#imgFile").show();
        $("#photoUrl").removeAttr('value');
        $("#show_img").unbind('mouseover');
        $("#show_img").unbind('mouseout');
        $("#province").val("");
        $("#province").change();

        $("#familyDialog").dialog('open');
    });

    $("#toEdit").click(function () {
        $("#familyForm").form('clear');
        var selectRows = $("#familyList").datagrid('getSelections');
        if(selectRows.length > 1){
            alert("只能编辑一条数据!");
            return;
        }
        if(selectRows.length < 1){
            alert("请选择一条数据!");
            return;
        }
        loadDataToForm(selectRows[0]);
        $("#familyDialog").dialog('open');
    });

    $("#toDel").click(function () {
        var selectRows = $("#familyList").datagrid('getSelections');
        if(selectRows.length < 1){
            alert("请至少选择一条数据!");
            return;
        }
        var selectIds = "";
        var selectNames = [];
        var peopleCount = [];
        for(var i=0;i<selectRows.length;i++){
            var ii = selectRows[i];
            selectIds += "," + ii.id;
            selectNames.push(ii.familyName);
            if(ii.peopleCount > 0){
                peopleCount.push(ii.familyName + "(" + ii.id + ")");
            }

        }
        if(peopleCount.length > 0){
            alert("家族(" + peopleCount + ")中含有成员，不能删除！如需删除，请先删除其成员！");
            return;
        }
        selectIds = selectIds.substring(1);
        $.messager.confirm('提示','确定要删除族谱(' + selectNames + ')  吗?',function(r){
            if (r){
                $.ajax({
                    type:'post',
                    url: projectUrl + "/consoles/deleteFamily",
                    // async:false,
                    dataType:'json',
                    data:{ids:selectIds},
                    success:function (data) {
                        alert(data.msg);
                        var params = {};
                        loadFamilyList(params);
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
    loadcartoonList(params);

});

function loadcartoonList(params){
    var dataList = getData("/consoles/cartoonList",params).dataList;
    // dataList = formatDataList(dataList);
    $("#cartoonList").datagrid({
        data:dataList,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        nowrap: true,
        columns:[[
            {field:"ck",checkbox:"true"},
            {field:"id",title:"动漫Id",width:"80",hidden:true},
            {field:"cartoonname",title:"动漫名称",width:"200"},
                // ,formatter: function(value,row,index){
                //     return "<a href=\"javascript:void 0;\" onclick='' title='" + value + "'>" + value +" </a>";
                // }},
            {field:"cartoonauthor",title:"作者",width:"80"},
            {field:"cartoonduration",title:"时长",width:"80"},
            {field:"cartoonlangue",title:"语言",width:"80"},
            {field:"cartoonseriesnum",title:"总集数",width:"80",
                formatter: function(value,row,index){
                    var html = "<a href=\"javascript:void 0\" onclick=\"viewseries('" + row.id + "')\" title='" + value + "' target='_blank'>导出 </a>";
                    return html;
                }},
            {field:"cartooninfo",title:"简介",width:"180",
                formatter: function(value,row,index){
                    if($.trim(value).length > 0){
                        if($.trim(value).length > 20){
                            return '<span title='+ value + '>'+value.substring(0,20)+'...</span>';
                        }
                        return '<span title='+ value + '>'+value+'</span>';
                    }
                    return '';
                }},
            {field:"export",title:"操作",width:"300",
                formatter: function(value,row,index){
                    var html = "<a href=\"" + projectUrl + "/output/exportfamily?familyId=" + row.id + "&familyname=" + row.familyName + "\" title='" + value + "' target='_blank'>导出 </a>";
                    html += "&nbsp;&nbsp;<a href=\"javascript:void 0;\" onclick=\"loadTab('','族谱合并','" + projectUrl + "/consoles/familyJoint?familyId=" + row.id + "&familyname=" + row.familyName + "')\" title='" + value + "'>合并 </a>";
                    return html;
                }}
        ]],
        loadFilter:pagerFilter
    });
}

function closeDialog(dialogId){
    $("#familyForm").form('clear');
    $("#" + dialogId).dialog("close");
}

function formatDataList(data){
    if(data){

        for(var i=0;i<data.length;i++){
            data[i].createTime = new Date(data[i].createTime).Format("yyyy-MM-dd hh:mm:ss");
        }
    }
    return data;
}

function loadDataToForm(data) {

    $("#familyId").val(data.id);
    $("#createMan").val(data.createMan);
    var createTime = data.createTime;
    if($.trim(createTime).length <= 0){
        createTime = new Date().Format("yyyy-MM-dd hh:mm:ss");
    }
    $("#createTime4Modify").val(createTime);
    $("#familyFirstName").val(data.familyFirstName);
    $("#familyName").val(data.familyName);
    $("#visitPassword").val(data.visitPassword);
    $("#province").val(data.province);
    $("#province").change();
    $("#city").val(data.city);
    $("#city").change();
    $("#district").val(data.district);
    $("#district").change();
    $("#familyDesc").val(data.familyDesc);
    $("#familyState").val(data.state);
    $("#familyArea").val(0);

    var visitStatus = data.visitStatus;
    $("input:radio[name='visitStatus'][value = " + visitStatus + "]").prop("checked","checked");
    $("input:radio[name='visitStatus'][value = " + visitStatus + "]").click();

    var imgPath = data.photoUrl;
    $("#result_img").attr('src',imgPath);
    $("#result_img").show();
    $("#imgFile").hide();
    $("#photoUrl").attr('value',imgPath);
    $("#show_img").mouseover(function(){
        $("#result_img").attr('src',"/ImgFile/images/deleteImg.png");
    });
    $("#show_img").mouseout(function(){
        $("#result_img").attr('src',imgPath);
    });

    $("#result_img").click(function(){
        $("#result_img").hide();
        $("#imgFile").show();
        $("#photoUrl").removeAttr('value');
        $("#show_img").unbind('mouseover');
        $("#show_img").unbind('mouseout');

    });
}

function loadTab(tabId,tabTitle,tabUrl) {
    parent.loadTab(tabId,tabTitle,tabUrl);

}