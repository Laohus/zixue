window.onload=function () {
    var user1=document.getElementById("passmod");
    var user2=document.getElementsByClassName("pop-close");
    var form_1=document.getElementsByClassName("pop-con");
    var user3=document.getElementsByClassName("pop-submit-a");

    user1.addEventListener("click",function () {
        form_1[0].className="pop-con open";
    })
    user2[0].addEventListener("click",function () {
        form_1[0].className="pop-con";
    })

}

$(document).ready(function() {
    $("#message").text("");
    $("#error").text("");

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
                if(data==="success"){
                    $("#error").text("");
                    $("input[ type='text']").val("");
                    $("#message").text("修改密码成功！");
                    return true;
                }else if(data==="session is timeout"){
                    $(location).prop("href","/login")
                    return true;

                } else {
                    $("#message").text("");
                    $("input[ type='text']").val("");
                    $("#error").text(data);
                    return false;

                }

            }
        })

    });
});