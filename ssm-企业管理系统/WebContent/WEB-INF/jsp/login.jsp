<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/demo.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/vendor/jgrowl/css/jquery.jgrowl.min.css">
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/component.css" />
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
	.title_text{font-size: 25px;color: 	#444444;text-align: center;height: 20px;line-height: 20px;padding:0px 50px;}
</style>
<script type="text/javascript">
	function login(){
		
		document.forms[0].submit();
	}
	function register(){
		window.location.href='initregister';
	}
</script>
</head>
<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box">
				<Strong class="title_text">麦可顿后台管理系统</Strong>
				    <p style="color:red;font-weight: bold;">${msg}</p>
					<form action="tologin" name="f" method="post">
						<div class="input_outer">
							<span class="u_user"></span>
							<input id="ID" name="name" class="text" style="color: #000000 !important" type="text" placeholder="请输入工号或者身份证号">
						</div>
						<div class="input_outer">
							<span class="us_uer"></span>
							<input id="PASSWORD" name="password" class="text" style="color: #000000 !important; position:absolute; z-index:100;" type="password" placeholder="请输入密码">
						</div>
						<div class="input_outer">
							<span class="us_uer"></span>
							<input id="VertifyCode" name="vertify" class="text" style="color: #000000 !important; position:absolute; z-index:100;" type="text" placeholder="请输入验证码（区分大小写）">
							<div style="margin-left: 330px">
								<canvas id="canvas"  width="100" height="43" onclick="dj()" style="border: 1px solid #ccc;background-color:#F8F8FF;border-radius: 5px;"></canvas>
							</div>
							<input type="hidden" id="vcode" name="vcode">
						</div>						
						<div id="LOGIN" class="mb2"><a class="act-but submit" href="javascript:;" onclick="login()" style="color: #FFFFFF">登录</a></div>
						<div id="REGISTER" class="mb2"><a class="act-but submit" href="javascript:;" onclick="register()" style="color: #FFFFFF">前往注册</a></div>
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
	<script src="${pageContext.request.contextPath }/js/Longin.js"></script>
	<!-- 验证码 -->
	<script>
		 var show_num = [];
		 draw(show_num);
		function dj(){
		 draw(show_num);
			var code = show_num.join(""); 
			var vercode = document.getElementById("vcode");
			vercode.value = code;
		 }
		// 传值给input
		var code = show_num.join(""); 
		var vercode = document.getElementById("vcode");
		vercode.value = code;
		//
		function draw(show_num) {
		        var canvas_width=document.getElementById('canvas').clientWidth;
		        var canvas_height=document.getElementById('canvas').clientHeight;
		        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
		        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
		        canvas.width = canvas_width;
		        canvas.height = canvas_height;
		        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0,q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m";
		        var aCode = sCode.split(",");
		        var aLength = aCode.length;//获取到数组的长度
					
		        for (var i = 0; i <= 3; i++) {
		            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
		            var deg = Math.random() * 30 * Math.PI / 180; //产生0~30之间的随机弧度
		            var txt = aCode[j];//得到随机的一个内容
		            show_num[i] = txt;
		            var x = 10 + i * 20;//文字在canvas上的x坐标
		            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
		            context.font = "bold 23px 微软雅黑";
		
		            context.translate(x, y);
		            context.rotate(deg);
		
		            context.fillStyle = randomColor();
		            context.fillText(txt, 0, 0);
		
		            context.rotate(-deg);
		            context.translate(-x, -y);
		        }
		        for (var i = 0; i <= 5; i++) { //验证码上显示线条
		            context.strokeStyle = randomColor();
		            context.beginPath();
		            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
		            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
		            context.stroke();
		        }
		        for (var i = 0; i <= 30; i++) { //验证码上显示小点
		            context.strokeStyle = randomColor();
		            context.beginPath();
		            var x = Math.random() * canvas_width;
		            var y = Math.random() * canvas_height;
		            context.moveTo(x, y);
		            context.lineTo(x + 1, y + 1);
		            context.stroke();
		        }
				    }
				function randomColor() {//得到随机的颜色值
				        var r = Math.floor(Math.random() * 256);
				        var g = Math.floor(Math.random() * 256);
				        var b = Math.floor(Math.random() * 256);
				        return "rgb(" + r + "," + g + "," + b + ")";
				    }
				</script>
			<div style="text-align:center;"></div>
			<script>
			//提示
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
			</script>
	</body>
</html>