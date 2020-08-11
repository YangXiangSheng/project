<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	    <link rel="stylesheet" href="css/amazeui.min.css">
		<link rel="stylesheet" href="css/admin.css">
		<link rel="stylesheet" href="css/app.css">
		<style>
			.admin-main{
				padding-top: 0px;
			}
			.btn {margin:5px 20px;position:relative; display:inline-block; width:75px;height:25px;overflow:hidden;text-align:center;font-size:14px;line-height:25px;vertical-align:center;border:1px solid #23c6c8;background-color:#23c6c8;color:#fff;border-radius:3px;}
			.btn:hover{border:1px solid #23babc;background-color:#23babc;}
			.btn input{position:absolute;left:0;top:0;opacity:0;}
			.headpic1 img { width:400px; height:500px;align:center;display:inline-block;}
			.headpic1 {margin:50px 300px;}
		</style>
	</head>
	<body>
		<div class="am-cf admin-main">
		<!-- content start -->
			<div class="admin-content-body">
				<div class="am-g">
					<form action="../addorder" class="am-form am-form-horizontal"
						 method="post" enctype="multipart/form-data"
						style="padding-top:30px;" data-am-validator>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="添加" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" onclick="return_order()" class="am-btn am-btn-success" value="返回" />
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							产品ID</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-1" required placeholder="请输入产品ID" 
									name="s_id"> <small>输入产品ID。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							客户ID</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-1" required placeholder="请输入客户ID" 
									name="c_id"> <small>输入客户ID。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								交易量</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder="请输入交易量" 
									name="o_number"  data-equal-to="#doc-vld-pwd-1"  required> <small>输入交易量。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								交易日</label>
							<div class="am-u-sm-9">
								<input type="date" id="doc-vld-pwd-2" required placeholder="请输入交易日" 
									name="o_time"  data-equal-to="#doc-vld-pwd-1"  required> <small>输入交易日。</small>
							</div>
						</div>
						<!-- 上传合同 -->
						<div class="headpic1">
							<img src="upload/purity.jpg" id="upload" style="background-color:#FFF0F5"> 
							<label type="button" class="btn"> 
								<span>上传合同</span> 
									<input type="file" name="file" onchange="changepic(this)" 
										accept="image/jpg,image/jpeg,image/png,image/PNG">
							</label> 
						</div>
						<!-- 图片 -->
					</form>
				</div>
			</div>
		</div>
	<script type="text/javascript">
	    function changepic(input) {
	        var file = input.files[0];
	        var reader = new FileReader()
	        // 图片读取成功回调函数
	        reader.onload = function(e) {
	            document.getElementById('upload').src=e.target.result
	        }
	        reader.readAsDataURL(file)
	    }
	    function return_order(){
	    	window.location.href="order_message.jsp";
	    }
	</script>
	<script type="text/javascript"
		src="assets/js/libs/jquery-1.10.2.min.js">
	</script>
	<script type="text/javascript" src="myplugs/js/plugs.js">
	</script>
	</body>
</html>