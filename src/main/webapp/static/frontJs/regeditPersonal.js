/**
 * Created by suyx on 2016/12/18.
 */
var checkCodePre;
$(function () {
    checkCodePre = drawPic();
    $("#province").val("");
    $("#province").change();
    $("#regedit").click(function () {

        var loginName = $("#loginname").val();
        var userName = $("#username").val();
        var password = $("#password").val();
        var passwordAffirm = $("#passwordAffirm").val();
        var phone = $("#phone").val();

        var checkCode = $("#checkCode").val();

        if($.trim(loginName).length <= 0){
            alert("登录名不能为空！");
            return ;
        }
        if($.trim(userName).length <= 0){
            alert("用户名不能为空！");
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

        if(checkCodePre.toUpperCase() != checkCode.toUpperCase()){
            alert("验证码错误！");
            return;
        }

        $("#regeditForm").attr("action",projectUrl + "/sign/regesterPersonal");
        $("#regeditForm").submit();
    });

    document.getElementById("canvas").onclick = function(e){
        e.preventDefault();
        checkCodePre = drawPic();
    };
});
