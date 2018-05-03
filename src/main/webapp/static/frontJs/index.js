/**
 * Created by suyx on 2017/1/5 0005.
 */
$(function () {

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

    $("#tocreditidentity").click(function () {
        var stuidcard = $("#stuidcard").val();
        if($.trim(stuidcard).length <= 0){
            $("#stuidcardtips").show();
            return;
        }
        $.ajax({
            type:'post',
            url: projectUrl + '/loan/tocreditidentity',
            dataType: 'json',
            data:{stuidcard:stuidcard},
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

    $("#stuidentity").click(function () {
        $("#stuidentityModal").modal("show");
    });
    $("#creditidentity").click(function () {
        $("#creditidentityModal").modal("show");
    });

});
