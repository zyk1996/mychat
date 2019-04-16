setInterval("showTime()",1000);
$("#loginModalBtn").click(function () {
    $("#loginModal").modal("show");
});
$(".loginModalCloseBtn").click(function () {
    modalHide("#loginModal");
});
$("#loginModalSubmitBtn").click(function () {
    $("#loginModal").find("form").submit();
    modalHide("#loginModal");
});

$("#registerModalBtn").click(function () {
    $("#registerModal").modal("show");
});
$(".registerModalCloseBtn").click(function () {
    modalHide("#registerModal");
});
$("#registerModalSubmitBtn").click(function () {
    $("#registerModal").find("form").submit();
    modalHide("#registerModal");
});

$("#findPasswordModalBtn").click(function () {
    $("#findPasswordModal").modal("show");
});
$(".findPasswordModalCloseBtn").click(function () {
    modalHide("#findPasswordModal");
});
$("#findPasswordModalSubmitBtn").click(function () {
    $("#findPasswordModal").find("form").submit();
    modalHide("#findPasswordModal");
});

$(".setPasswordModalCloseBtn").click(function () {
    modalHide("#setPasswordModal");
});
$("#setPasswordModalSubmitBtn").click(function () {
    $("#setPasswordModal").find("form").submit();
    modalHide("#setPasswordModal");
});

function modalHide(ele){
    $(ele).find("form").find("input").val("");
    $(ele).modal("hide");
}
function showTime(){
    var date=new Date();
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    var day=date.getDate();
    var hour=date.getHours();
    var minute=date.getMinutes();
    var second=date.getSeconds();
    if(month<10){
        month="0"+month;
    }
    if(day<10){
        day="0"+day;
    }
    if(hour<10){
        hour="0"+hour;
    }
    if(minute<10){
        minute="0"+minute;
    }
    if(second<10){
        second="0"+second;
    }
    $("#time").text(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second);
}