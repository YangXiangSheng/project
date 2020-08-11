<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/city-picker.js"></script>
<script type="text/javascript" src="js/city-picker.data.js"></script>
<script type="text/javascript">
	function changeInput()
	{
	//地址
	var item0 = document.getElementById("item0");
	var itemA = item0.innerHTML;
	item0.innerHTML = "<input name='address' id='city-picker3' class='addr' data-toggle='city-picker' type='text' value='"+itemA+"'/>";
	//QQ
	var item1 = document.getElementById("item1");
	var itemB = item1.innerHTML;
	item1.innerHTML = "<input name='qq' class='textput' type='text' value='"+itemB+"'/>";
	//保存修改
	var item2 = document.getElementById("item2");
	var itemC = item2.innerHTML;
	item2.innerHTML = "<a class='greenbtn' href='javascript:;' onclick='save()'>保存</a>";
	}
	function save(){
		document.forms[1].submit();
	}
    function changepic(input) {
        var file = input.files[0];
        var reader = new FileReader()
        // 图片读取成功回调函数
        reader.onload = function(e) {
            document.getElementById('upload').src=e.target.result
        }
        reader.readAsDataURL(file)
    }
</script>
<style type="text/css">
.btn {margin:5px 20px;position:relative; display:inline-block; width:75px;height:25px;overflow:hidden;text-align:center;font-size:14px;line-height:25px;vertical-align:center;border:1px solid #23c6c8;background-color:#23c6c8;color:#fff;border-radius:3px;}
.btn:hover{border:1px solid #23babc;background-color:#23babc;}
.btn input{position:absolute;left:0;top:0;opacity:0;}
</style>
</head>
<body style="background:#328f46;">
    <div class="messageright">
        	<div class="clear" style="height:20px"></div>
        	<h2 style="padding:0px 35px;">账户信息</h2>
            <div class="clear" style="height:30px"></div>
            <span class="information">
            	<div class="clear" style="height:10px"></div>
     	        
     	        <form action="../saveimg" method="post" enctype="multipart/form-data">
     	       
     	        <div class="headpic1">
					<img src="${pageContext.request.contextPath}/photo/${staff.img}" id="upload">
				 	<div style="display: none">
						<label type="button" class="btn">
							<span>上传头像</span>
							<input type="file" name="file" onchange="changepic(this)" accept="image/jpg,image/jpeg,image/png,image/PNG">
						</label>
						<label class="btn">
						<input type="submit" value="提交">保存头像
						</label>
					</div>
				</div>
				</form>
				
                <form action="../updateaccount" method="post">
                    <table>
                        <tr>
                            <td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                            <td colspan="2">${staff.name}</td></tr>
                        <tr>
                            <td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
                            <td colspan="2">${staff.sex}</td></tr>
                        <tr>
                            <td>身份证号：</td>
                            <td colspan="2">${staff.idcard}</td></tr>    
                        <tr>
                            <td>工作工号：</td>
                            <td colspan="2">${staff.id}</td></tr>
                        <tr>
                            <td>电子邮箱：</td>
                            <td id="email">${staff.email}</td>
                       </tr>
                        <tr>
                            <td>手机号码：</td>
                            <td id="phone">${staff.phone}</td>
                       </tr>
                        <tr>
                            <td>详细住址：</td>
                            <td class="address" id="item0">${staff.address}</td>
                            <td><p class="wrrong"></p></td>
                        </tr>
                        <tr>
                            <td>QQ：</td>
                            <td id="item1">${staff.QQ}</td>
                            <td><p class="wrrong"></p></td>
                        </tr>                                               
                        <tr>
                            <td></td>
                            <td colspan="2" id="item2"><a  class="greenbtn" href="javascript:;" onclick="changeInput()">修改</a></td>
                        </tr>
                        <tr>
                        	<td></td>
                        	<td colspan="2">
                        		Tips:如需更改其它信息，请联系管理员审核更改！
                        	</td>
                        </tr>
                    </table>
                </form>
                <div class="clear"></div>
            </span>
            <div class="clear" style="height:50px"></div>

    </div>
</body>
</html>
<script>
	$(".Fstage>li").click(function(){
		if($(this).children("ul").hasClass("Tstage"))
		$(this).siblings("li").children("ul").hide();
		$(this).children("ul").slideDown();
	});
</script>
