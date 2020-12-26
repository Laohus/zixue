window.onload=function () {
    var user1=document.getElementById("passmod");
    var user2=document.getElementsByClassName("close");
    var form_1=document.getElementsByClassName("form_1");

    user1.addEventListener("click",function () {
        form_1[0].className="form_1 open";
    })
    user2[0].addEventListener("click",function () {
        form_1[0].className="form_1";
    })

}