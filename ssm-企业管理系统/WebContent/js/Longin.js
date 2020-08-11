function login(){
    var username = document.getElementById("ID").value;
    var password = document.getElementById("PASSWORD").value;
    var VertifyCode = document.getElementById("VertifyCode").value;
    var UserCode = document.getElementById("vcode").value;
    if(username==""){
        $.jGrowl("用户名不能为空！", { header: '提醒' });
    }else if(password==""){
        $.jGrowl("密码不能为空！", { header: '提醒' });
    }else if(VertifyCode!= UserCode){
    	$.jGrowl("验证码错误！", { header: '提醒' });
    }else{
        AjaxFunc();
    }
}
function AjaxFunc()
{
	document.forms[0].submit();
}