$(document).ready(function() {

    // 用户登录
    $("#buttonlogin").click(function() {
        var username=$("#username").val();
        var password=$("#password").val();

        if (username.length===0 || password.length===0){
            $("input[ type='text']").val("");
            $("#error").text("账户信息不能为空");
            return false;
        }
        if (username==="NULL" || password==="NULL"){
            $("input[ type='text']").val("");
            $("#error").text("账户信息不能为特殊字符");
            return false;
        }
        if (username==="null" || password==="null"){
            $("input[ type='text']").val("");
            $("#error").text("密码不能为特殊字符");
            return false;
        }

        $.ajax({
            url:"/login/account",
            type:"POST",
            datatype:"JSON",
            data: $('#formlogin').serialize(),
            success:function (data) {
                if(data.code===200){
                    $("#error").text("");
                    $(location).prop("href","/home")
                    return true;
                }else {
                    $("input[ type='text']").val("");
                    $("input[ type='password']").val("");
                    $("#error").text(data.errormsg);
                    return false;

                }

            }
        })

    });

    // 修改密码
    $("#button").click(function() {
        var newpassword=$("#newpassword").val();
        var newpassword_t=$("#newpassword_t").val();

        if (newpassword.length===0 || newpassword_t.length===0){
            $("#message").text("");
            $("input[ type='text']").val("");
            $("#error").text("密码不能为空");
            return false;
        }
        if (newpassword==="NULL" || newpassword_t==="NULL"){
            $("#message").text("");
            $("input[ type='text']").val("");
            $("#error").text("密码不能为特殊字符");
            return false;
        }
        if (newpassword==="null" || newpassword_t==="null"){
            $("#message").text("");
            $("input[ type='text']").val("");
            $("#error").text("密码不能为特殊字符");
            return false;
        }

        $.ajax({
            url:"/home/edit-user",
            type:"POST",
            datatype:"JSON",
            data: $('#signinForm').serialize(),
            success:function (data) {
                if(data.code===200){
                    $("#error").text("");
                    $("input[ type='text']").val("");
                    $("#message").text("修改密码成功！");
                    return true;
                }else if(data.errormsg==="SESSION已过期，请重新登陆！"){
                    $(location).prop("href","/login")
                    return true;

                } else {
                    $("#message").text("");
                    $("input[ type='text']").val("");
                    $("#error").text(data.errormsg);
                    return false;

                }

            }
        })

    });
});
