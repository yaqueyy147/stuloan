/**
 * Created by suyx on 2016/12/18.
 */
var checkCodePre;
var chkcode;
var countdown = 60;
$(function () {
    // checkCodePre = drawPic();
    $("#province").val("");
    $("#province").change();
    $("#regedit").click(function () {

        var loginName = $("#loginname").val();
        var password = $("#password").val();
        var passwordAffirm = $("#passwordAffirm").val();
        var phone = $("#phone").val();

        var checkCode = $("#checkCode").val();

        if($.trim(loginName).length <= 0){
            alert("登录名不能为空！");
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

        if($.trim(phone).length != 11){
            alert("手机号输入有误！如果是固定电话，请加上区号！");
            return ;
        }

        // if(checkCodePre.toUpperCase() != checkCode.toUpperCase()){
        //     alert("验证码错误！");
        //     return;
        // }

        $("#regeditForm").attr("action",projectUrl + "/sign/regesterPersonal");
        $("#regeditForm").submit();
    });

    $("#getphonecheckcode").click(function () {
        var obj = $(this);
        var phone = $("#phone").val();
        if($.trim(phone).length != 11){
            alert("手机号输入有误！如果是固定电话，请加上区号！");
            return ;
        }
        $.ajax({
            type:'post',
            url: projectUrl + '/sign/smscode',
            dataType: 'json',
            data:{phone:phone},
            // async:false,
            success:function (data) {
                if(data.code >= 1){
                    chkcode = data.chkcode;
                    settime(obj);
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

    // document.getElementById("canvas").onclick = function(e){
    //     e.preventDefault();
    //     checkCodePre = drawPic();
    // };
});

function settime(obj) { //发送验证码倒计时
    if (countdown == 0) {
        $(obj).attr('disabled', false);
        $(obj).val("免费获取验证码");
        $(obj).text("免费获取验证码");
        countdown = 60;
        return;
    } else {
        $(obj).attr('disabled', true);
        $(obj).val("重新发送(" + countdown + ")");
        $(obj).text("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function() {
            settime(obj) }
        ,1000)
}