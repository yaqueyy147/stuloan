/**
 * Created by suyx on 2017/1/12.
 */
$(function () {
    $("#province4Search").val("");
    $("#province4Search").change();
    $("#province4Search2").val("");
    $("#province4Search2").change();

    $("#doSearch").click(function () {
        var params = {};
        params.loginname = $("#loginName4Search").val();
        params.province = $("#province4Search").val();
        params.city = $("#city4Search").val();
        params.district = $("#district4Search").val();
        params.usertype = $("#usertype4Search").val();
        loadDataGrid(params);
    });
    var params = {};
    loadDataGrid(params);

    $("#userDialog").dialog({
        width: 800,
        height: 400,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"提交",
                handler:function(){
                    var loginName = $("#loginname").val();
                    var password = $("#password").val();
                    var passwordAffirm = $("#passwordAffirm").val();
                    var phone = $("#phone").val();
                    var province = $("#province").val();
                    var city = $("#city").val();
                    var district = $("#district").val();

                    if($.trim(loginName).length <= 0){
                        alert("登录名不能为空！");
                        // $("#loginName").parent().addClass("has-error");
                        return ;
                    }
                    if($.trim(password).length <= 0){
                        alert("密码不能为空！");
                        return ;
                    }
                    if($.trim(passwordAffirm).length <= 0){
                        alert("确认密码不能为空！");
                        return ;
                    }
                    if(password != passwordAffirm){
                        alert("密码输入不一致！");
                        return;
                    }
                    if($.trim(province).length <= 0){
                        alert("请选择省！");
                        return ;
                    }
                    if($.trim(city).length <= 0){
                        alert("请选择市！");
                        return ;
                    }
                    if($.trim(district).length <= 0){
                        alert("请选择区！");
                        return ;
                    }
                    if($.trim(phone).length != 11){
                        alert("手机号输入有误！如果是固定电话，请加上区号！");
                        return ;
                    }
                    var formData = {};
                    var postUrl = projectUrl + "/consoles/saveUserBase";
                    var testData = $("#userInfoForm").serializeArray();

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

                            alert(data.msg);
                            if(data.code >= 1){
                                var params = {};
                                loadDataGrid(params);
                                $("#userInfoForm").form('clear');
                                closeDialog("userDialog");
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
                    closeDialog("userDialog");
                }
            }
        ]
    });

    $("#resourceDialog").dialog({
        width: 400,
        height: 400,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"提交",
                handler:function(){
                    var userid = $("#userId4Tree").val();
                    var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
                    var nodes = treeObj.getCheckedNodes(true);
                    var selectIds = "";
                    for(var i=0;i<nodes.length;i++){
                        var ii = nodes[i];
                        selectIds += "," + ii.id;
                    }
                    if($.trim(selectIds).length > 1){
                        selectIds = selectIds.substring(1);
                    }
                    $.ajax({
                        type:'post',
                        url: projectUrl + "/consoles/saveAuth",
                        // async:false,
                        dataType:'json',
                        data:{userid:userid,sourceIds:selectIds},
                        success:function (data) {

                            alert(data.msg);
                            if(data.code >= 1){
                                $("#resourceDialog").dialog("close");
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
                    $("#resourceDialog").dialog("close");
                }
            }
        ]
    });

    $("#modifyPasswordDialog").dialog({
        width: 400,
        height: 200,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"提交",
                handler:function(){

                    var newPassword = $("#newPassword").val();
                    var newPasswordAffirm = $("#newPasswordAffirm").val();
                    if(newPassword != newPasswordAffirm){
                        alert("密码输入不一致!");
                        return;
                    }

                    var postUrl = projectUrl + "/consoles/modifyPassword";
                    var params = {};
                    params.userId = $("#userIdForModify").val();
                    params.newPassword = newPassword;
                    params.isAdmin = 1;
                    $.ajax({
                        type:'post',
                        url: postUrl,
                        // async:false,
                        dataType:'json',
                        data:params,
                        success:function (data) {
                            alert(data.msg);
                            if(data.code >= 1){

                                var params2 = {};
                                loadDataGrid(params2);
                                $("#modifyPasswordForm").form('clear');
                                $("#modifyPasswordDialog").dialog("close");
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
                    $("#modifyPasswordForm").form('clear');
                    $("#modifyPasswordDialog").dialog("close");
                }
            }
        ]
    });

    $("#loanlimitDialog").dialog({
        width: 400,
        height: 200,
        closed: true,
        cache: false,
        modal: true,
        "buttons":[
            {
                "text":"提交",
                handler:function(){
                    var userid = $("#userId4loanlimit").val();
                    var loanlimit = $("#loanlimit").val();
                    if($.trim(loanlimit).length <= 0){
                        $.messager.alert('提示',"请输入贷款额度")
                        return;
                    }
                    $.ajax({
                        type:'post',
                        url: projectUrl + "/consoles/loanlimitset",
                        // async:false,
                        dataType:'json',
                        data:{userid:userid,loanlimit:loanlimit},
                        success:function (data) {
                            $.messager.alert('提示',data.message);
                            if(data.code >= 1){
                                $("#loanlimitDialog").dialog("close");
                            }
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
            },
            {
                "text":"取消",
                handler:function () {
                    $("#loanlimitDialog").dialog("close");
                }
            }
        ]
    });

    $("#toAdd").click(function () {

        $("#userInfoForm").form('clear');
        $("#state").combobox("setValue",1);
        $("#isfront").combobox("setValue",0);
        $("#isconsole").combobox("setValue",1);
        $("#isvolunteer").combobox("setValue",1);
        $("#userId").val(0);
        $("#passwordTr").removeAttr("style");
        $("#userDialog").dialog('open');
        $("#loginname").removeAttr("readonly");
    });
    var setting = {
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        }
    };
    $("#toSetAuth").click(function () {
        var selectRows = $("#userList").datagrid('getSelections');
        if(selectRows.length > 1){
            alert("只能编辑一条数据!");
            return;
        }
        if(selectRows.length < 1){
            alert("请选择一条数据!");
            return;
        }
        if(selectRows[0].isconsole != 1){
            alert("该用户不是后台用户，不能授权！");
            return;
        }
        $("#userId4Tree").val(selectRows[0].id);
        var params = {userid:selectRows[0].id};
        var dataList = getData("/consoles/userResourceList",params).resourceList;
        $.fn.zTree.init($("#resourceTree"), setting, dataList);

        $("#resourceDialog").dialog('open');
    });

    $("#toEdit").click(function () {
        $("#userInfoForm").form('clear');
        $("#passwordTr").hide();
        var selectRows = $("#userList").datagrid('getSelections');
        if(selectRows.length > 1){
            alert("只能编辑一条数据!");
            return;
        }
        if(selectRows.length < 1){
            alert("请选择一条数据!");
            return;
        }
        loadDataToForm(selectRows[0]);
        $("#userDialog").dialog('open');
    });

    $("#toModifyPassword").click(function () {
        $("#modifyPasswordForm").form('clear');
        var selectRows = $("#userList").datagrid('getSelections');
        if(selectRows.length > 1){
            alert("只能编辑一条数据!");
            return;
        }
        if(selectRows.length < 1){
            alert("请选择一条数据!");
            return;
        }
        $("#userIdForModify").val(selectRows[0].id);
        $("#modifyPasswordDialog").dialog('open');
    });

    $("#toDel").click(function () {
        var selectRows = $("#userList").datagrid('getSelections');
        if(selectRows.length < 1){
            alert("请至少选择一条数据!");
            return;
        }
        var selectIds = "";
        var selectNames = [];
        for(var i=0;i<selectRows.length;i++){
            var ii = selectRows[i];
            selectIds += "," + ii.id;
            selectNames.push(ii.loginname);
        }
        selectIds = selectIds.substring(1);
        $.messager.confirm('提示','确定要删除用户(' + selectNames + ')  吗?',function(r){
            if (r){
                $.ajax({
                    type:'post',
                    url: projectUrl + "/consoles/deleteUser",
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



});

function closeDialog(dialogId){
    $("#userInfoForm").form('clear');
    $("#" + dialogId).dialog("close");
}

function loadDataGrid(params) {
    params.pageNumber = 1;
    params.pageSize = 10;
    params.orderby = "createdate desc";
    // var dataList = getData("/consoles/userList",params).dataList;
    // dataList = formatDataList(dataList);
    $("#userList").datagrid({
        // data:dataList,
        url:"/consoles/userList",
        queryParams:params,
        loadMsg:"加载中...",
        selectOnCheck:true,
        singleSelect:false,
        columns:[[
            {field:"ck",checkbox:"true"},
            {field:"id",title:"用户Id",width:"80",hidden:true},
            {field:"loginname",title:"用户账号",width:"120"},
            {field:"username",title:"用户昵称",width:"120",hidden:true},
            {field:"password",title:"密码",width:"80",hidden:true},
            {field:"phone",title:"电话",width:"120"},
            {field:"qqnum",title:"QQ",width:"100"},
            {field:"wechart",title:"微信",width:"100"},
            {field:"address",title:"所在地",width:"150",
                formatter: function(value,row,index){
                    var address = "";
                    if($.trim(row.province).length > 0){
                        address += row.province;
                    }
                    if($.trim(row.city).length > 0){
                        address += row.city;
                    }
                    if($.trim(row.district).length > 0){
                        address += row.district;
                    }
                    return '<span title='+ address + '>'+address+'</span>';
                }},
            {field:"createman",title:"创建人",width:"120"},
            {field:"createdate",title:"创建时间",width:"150",
                formatter: function(value,row,index){
                    if($.trim(value).length > 0){
                        return value;
                    }
                    return '';
                }},
            {field:"state",title:"状态",width:"80",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "可用";
                    }
                    return '不可用';
                }},
            {field:"isstuidentity",title:"是否已通过学生认证",width:"80",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "已通过";
                    }
                    return '未通过';
                }},
            {field:"iscreditidentity",title:"是否已通过信用认证",width:"80",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "已通过";
                    }
                    return '未通过';
                }},
            {field:"loanlimit",title:"贷款额度",width:"120",
                formatter: function(value,row,index){
                    if(row.isfront == 1){
                        var hh = "";
                        if(value){
                            hh += value + "元";
                        }else{
                            hh += "0.0元";
                        }
                        if(row.iscreditidentity == 1 && row.isstuidentity == 1){
                            hh += "&nbsp;&nbsp;<a href='javascript:void 0' onclick=\"setloanlimit('" + row.id + "', '" + value + "')\">设置</a>";
                        }
                        return hh;
                    }
                    return '0.0元';
                }},
            {field:"userfrom",title:"用户来源",width:"120",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "前台注册";
                    }
                    return '后台管理员设置';
                }},
            {field:"isfront",title:"是否可登录前台",width:"100",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "是";
                    }
                    return '否';
                }},
            {field:"isconsole",title:"是否可登录后台",width:"100",
                formatter: function(value,row,index){
                    if(value == 1){
                        return "是";
                    }
                    return '否';
                }}
            // {field:"operate",title:"操作",width:"120"}
        ]],
        loadFilter:pagerFilter
    });
}

function setloanlimit(userid,loanlimit) {
    $("#userId4loanlimit").val(userid);
    $("#loanlimit").val(loanlimit);
    $("#loanlimitDialog").dialog("open");
}

function loadDataToForm(data){
    $("#userId").val(data.id);
    $("#loginname").val(data.loginname);
    $("#loginname").attr("readonly","readonly");
    $("#password").val(data.password);
    $("#passwordAffirm").val(data.password);
    $("#phone").val(data.phone);
    $("#qqnum").val(data.qqnum);
    $("#wechart").val(data.wechart);
    $("#province").val(data.province);
    $("#province").change();
    $("#city").val(data.city);
    $("#city").change();
    $("#district").val(data.district);

    $("#state").combobox("setValue",data.state);
    $("#isfront").combobox("setValue",data.isfront);
    $("#isconsole").combobox("setValue",data.isconsole);
    $("#isvolunteer").combobox("setValue",data.isvolunteer);
    $("#userfrom").val(data.userfrom);
    $("#userphoto").val(data.userphoto);
    $("#idcardphoto").val(data.idcardphoto);
}