<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>注册</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/demo.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/vendor/jgrowl/css/jquery.jgrowl.min.css">
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/component_2.css" />
<!--[if IE]>
<script src="${pageContext.request.contextPath }/js/html5.js"></script>
<![endif]-->
<style>
	input::-webkit-input-placeholder{
		color:rgba(0, 0, 0, 0.726);
	}
	input::-moz-placeholder{   /* Mozilla Firefox 19+ */
		color:rgba(0, 0, 0, 0.726);
	}
	input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
		color:rgba(0, 0, 0, 0.726);
	}
	input:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
		color:rgba(0, 0, 0, 0.726);
	}
	.title_text{font-size: 25px;color:black;text-align: center;height: 20px;line-height: 20px;padding:10px 50px;margin-bottom: 25px;}
</style>
</head>
<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas" ></canvas>
				<div class="logo_box">
				<Strong class="title_text">麦可顿后台管理系统</Strong>
				<p></p>
					<form action="toregister" name="f" method="post">
						<div class="input_outer">
							<span class="u_user"></span>
							<input id="ID" name="name" class="text" style="color: #000000 !important" type="text" placeholder="请输入工号">
						</div>
						<div class="input_outer">
							<span class="phone_user"></span>
							<input id="FLAG" name="flag" class="text" style="color: #000000 !important; position:absolute; z-index:100;" type="text" placeholder="请输入注册码">
						</div>			
						<div class="input_outer">
							<span class="us_uer"></span>
							<input id="PASSWORD" name="password" class="text" style="color: #000000 !important; position:absolute; z-index:100;" type="password" placeholder="请输入密码">
						</div>
						<div class="input_outer">
							<span class="us_uer"></span>
							<input id="PASSWORD2" name="password2" class="text" style="color: #000000 !important; position:absolute; z-index:100;" type="password" placeholder="请再次输入密码">
						</div>
						<div id="REGISTER" class="mb2"><a class="act-but submit" href="javascript:;" onclick="userregister()" style="color: #FFFFFF">注册</a></div>
						<div id="TOLOGIN" class="mb2"><a class="act-but submit" href="javascript:;" onclick="initlogin()" style="color: #FFFFFF">返回登录</a></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath }/js/TweenLite.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/EasePack.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath }/js/rAF.js"></script>
	<script src="${pageContext.request.contextPath }/js/demo-1.js"></script>
	<script src="${pageContext.request.contextPath }/js/vendor/jgrowl/jquery.jgrowl.min.js"></script>
	<script type="text/javascript">
		function userregister(){
		    var username = document.getElementById("ID").value;
		    var password = document.getElementById("PASSWORD").value;
		    var password2 = document.getElementById("PASSWORD2").value;
		    var flag = document.getElementById("FLAG").value;
		    if(username==""){
		        $.jGrowl("工号不能为空！", { header: '提醒' });
		    }else if(flag==""){
		        $.jGrowl("注册码不能为空！", { header: '提醒' });
		    }else if(password==""){
		        $.jGrowl("密码不能为空！", { header: '提醒' });
		    }else if(password2==""){
		        $.jGrowl("确认密码不能为空！", { header: '提醒' });
		    }else if(password2 != password){
		        $.jGrowl("输入的两次密码不一致！", { header: '提醒' });
		    }else{
		    	AjaxFunc();
		    }
		}
		function AjaxFunc()
		{
			alert("如注册成功会跳转登录页面，请等待！");
			document.forms[0].submit();
		}
		function initlogin(){
			window.location.href='initlogin';
		}
	</script>
		<div style="text-align:center;"></div>
	</body>
</html>