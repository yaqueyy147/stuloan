/**
 * Created by suyx on 2016/12/18.
 */
$(function () {
    if(headphoto && $.trim(headphoto).length > 0){
        $("#result_img").attr('src', projectUrl + headphoto);
        $("#result_img").show();
        $("#resultimg-div").show();
        $("#headphoto").attr('value', projectUrl + headphoto);
        $("#upload-div").hide();
        if(headstate == 1){
            $("#head-tips").hide();
        }
        if(headstate == 5){
            $("#head-tips").html("审核中...");
            $("#head-tips").show();
        }
        if(headstate == 2){
            $("#head-tips").html("审核未通过，请重新上传或者<br/><a href=\"javascript:void 0;\" onclick=\"getMedia(this,'1')\">点击拍照</a>");
            $("#head-tips").show();
        }
    }
    if(idcardphoto && $.trim(idcardphoto).length > 0){
        $("#result_img01").attr('src', projectUrl + idcardphoto);
        $("#result_img01").show();
        $("#resultimg-div01").show();
        $("#idcardphoto").attr('value', projectUrl + idcardphoto);
        $("#upload-div01").hide();
        if(idcardstate == 1){
            $("#idcard-tips").hide();
        }
        if(idcardstate == 5){
            $("#idcard-tips").html("审核中...");
            $("#idcard-tips").show();
        }
        if(idcardstate == 2){
            $("#idcard-tips").html("审核未通过，请重新上传或者<br/><a href=\"javascript:void 0;\" onclick=\"getMedia(this,'2')\">点击拍照</a>");
            $("#idcard-tips").show();
        }
    }
    if(stuidcardphoto && $.trim(stuidcardphoto).length > 0){
        $("#result_img02").attr('src', projectUrl + stuidcardphoto);
        $("#result_img02").show();
        $("#resultimg-div02").show();
        $("#stuidcardphoto").attr('value', projectUrl + stuidcardphoto);
        $("#upload-div02").hide();
        if(stuidcardstate == 1){
            $("#stuidcard-tips").hide();
        }
        if(stuidcardstate == 5){
            $("#stuidcard-tips").html("审核中...");
            $("#stuidcard-tips").show();
        }
        if(stuidcardstate == 2){
            $("#stuidcard-tips").html("审核未通过，请重新上传或者<br/><a href=\"javascript:void 0;\" onclick=\"getMedia(this,'3')\">点击拍照</a>");
            $("#stuidcard-tips").show();
        }
    }
    $("img[name='deleteimg']").click(function () {
        var type = $(this).attr("data-type");

        if(confirm("确定要删除该图片吗？")){
            $.ajax({
                type 		: "POST",
                dataType 	: "json",
                url 		: projectUrl + "/upload/deleteimg",
                data		: {id : photoid,type:type},
                success		: function( result ) {
                    if(result.code){
                        alert("删除成功");
                        if(type == 1){
                            $("#result_img").attr('src', "");
                            $("#headphoto").val("");
                            $("#resultimg-div").hide();
                            $("#upload-div").show();
                            var tiphtml = "请上传正确的jpg或者png格式的头像图片!";
                            tiphtml += "或者<br/><a href=\"javascript:void 0;\" onclick=\"getMedia(this,'1')\">点击拍照</a>";
                            $("#head-tips").text("");
                            $("#head-tips").html(tiphtml);
                            $("#head-tips").show();
                        }
                        if(type == 2){
                            $("#result_img01").attr('src', "");
                            $("#idcardphoto").val("");
                            $("#resultimg-div01").hide();
                            $("#upload-div01").show();
                            var tiphtml = "请上传正确的jpg或者png格式的身份证图片!";
                            tiphtml += "或者<br/><a href=\"javascript:void 0;\" onclick=\"getMedia(this,'1')\">点击拍照</a>";
                            $("#idcard-tips").text("");
                            $("#idcard-tips").html(tiphtml);
                            $("#idcard-tips").show();
                        }
                        if(type == 3){
                            $("#result_img02").attr('src', "");
                            $("#stuidcardphoto").val("");
                            $("#resultimg-div02").hide();
                            $("#upload-div02").show();
                            var tiphtml = "请上传正确的jpg或者png格式的学生证图片!";
                            tiphtml += "或者<br/><a href=\"javascript:void 0;\" onclick=\"getMedia(this,'1')\">点击拍照</a>";
                            $("#stuidcard-tips").text("");
                            $("#stuidcard-tips").html(tiphtml);
                            $("#stuidcard-tips").show();
                        }

                    }else{
                        alert("网络请求出错,请联系管理员");
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

    });

    $("#tocreditidentity").click(function () {
        var alipayname = $("#alipayname").val();
        if($.trim(alipayname).length <= 0){
            $("#stuidcardtips").show();
            return;
        }
        $.ajax({
            type:'post',
            url: projectUrl + '/loan/tocreditidentity',
            dataType: 'json',
            data:{alipayname:alipayname},
            // async:false,
            success:function (data) {
                if(data.code >= 1){
                    alert(data.message);
                    $("#creditopera").replaceWith("<span>已申请信用认证，请等待审核！</span>");
                    $("#creditidentityModal").modal("hide");
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
    });

    $("#tostuidentity").click(function () {
        var school = $("#school").val();
        var classgrade = $("#classgrade").val();
        var stunum = $("#stunum").val();
        if($.trim(school).length <= 0){
            $("#schooltips").show();
            return;
        }
        if($.trim(classgrade).length <= 0){
            $("#classgradetips").show();
            return;
        }
        if($.trim(stunum).length <= 0){
            $("#stunumtips").show();
            return;
        }
        $.ajax({
            type:'post',
            url: projectUrl + '/loan/tostuidentity',
            dataType: 'json',
            data:{school:school,classgrade:classgrade,stunum:stunum},
            // async:false,
            success:function (data) {
                if(data.code >= 1){
                    alert(data.message);
                    $("#loanopera").replaceWith("<span>已申请学生认证，请等待审核！</span>");
                    $("#stuidentityModal").modal("hide");
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
    });

});

function uploadvideoPP(resultimg,resultimgdiv,uploaddiv,tipsdiv,picinput,targetFile,type,imgdata) {

    $.ajax({
        type:'post',
        url: projectUrl + '/upload/uploadvideoimg',
        dataType: 'json',
        data:{targetFile: targetFile,id:photoid,type:type,imgdata:imgdata},
        success:function (data) {
            if(data.code == 1){
                var imgPath = data.filePath;
                $("#" + resultimg).attr('src', projectUrl + imgPath);
                $("#" + resultimg).show();
                $("#" + resultimgdiv).show();
                $("#" + picinput).attr('value', projectUrl + imgPath);
                $("#" + uploaddiv).hide();
                $("#photoModal").modal("hide");
                $("#" + tipsdiv).html("上传成功，请等待审核...");
            }else{
                alert("上传失败!");
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

function uploadPP(obj,resultimg,resultimgdiv,uploaddiv,tipsdiv,picinput,targetFile,type) {
    var filexx = $(obj).val();
    var suffix = filexx.substring(filexx.lastIndexOf("."));
    if(suffix != ".jpg" && suffix != ".png" && suffix != ".jpeg"){
        alert("请上传正确的jpg或者png格式的图片!");
        return;
    }

    var fileSize = obj.files[0].size;
    if(fileSize > 1 * 1024 * 1024){
        alert("请将图片大小限制在1M以内!");
        return;
    }

    var fileElementId = $(obj).attr("id");

    $.ajaxFileUpload
    (
        {
            url: projectUrl + '/upload/uploadImg?jsonCallBack=?', //用于文件上传的服务器端请求地址
            type: 'post',
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: fileElementId, //文件上传域的ID
            dataType: 'JSON', //返回值类型 一般设置为json
            data:{targetFile: targetFile,id:photoid,type:type},
            success: function (data, status)  //服务器成功响应处理函数
            {
                data = eval('(' + data + ')');
                if(data.code == 1){
                    var imgPath = data.filePath;
                    $("#" + resultimg).attr('src', projectUrl + imgPath);
                    $("#" + resultimg).show();
                    $("#" + resultimgdiv).show();
                    $("#" + picinput).attr('value', projectUrl + imgPath);
                    $("#" + uploaddiv).hide();
                    $("#" + tipsdiv).html("上传成功，请等待审核...");
                }else{
                    alert("上传失败!");
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        }
    )
}