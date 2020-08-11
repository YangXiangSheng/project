function userregister(){
    var username = document.getElementById("ID").value;
    var password = document.getElementById("PASSWORD").value;
    var idcard = document.getElementById("IDCARD").value;
    var email = document.getElementById("EMAIL").value;
    var phone = document.getElementById("PHONE").value;
    var flag = document.getElementById("FLAG").value;
    if(username==""){
        $.jGrowl("姓名不能为空！", { header: '提醒' });
    }else if(idcard==""){
        $.jGrowl("身份证号不能为空！", { header: '提醒' });
    }else if(idcard.length<18){
        $.jGrowl("请输入正确的身份证号！", { header: '提醒' });
    }else if(email==""){
        $.jGrowl("邮箱不能为空！", { header: '提醒' });
    }else if(password==""){
        $.jGrowl("密码不能为空！", { header: '提醒' });
    }else if(phone==""){
        $.jGrowl("手机号不能为空！", { header: '提醒' });
    }else if(flag==""){
        $.jGrowl("注册码不能为空！", { header: '提醒' });
    }else{
    	AjaxFunc();
    }
}
function AjaxFunc()
{
	alert("如注册成功会跳转登录页面，请等待！");
	document.forms[0].submit();
}