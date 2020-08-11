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
		</style>
	</head>
	<body>
		<div class="am-cf admin-main">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-g">
					<form action="../addbuy" class="am-form am-form-horizontal"
						 method="post"
						style="padding-top:30px;" data-am-validator>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							购买商品ID</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-1" required placeholder="请输入购买商品ID" 
									name="b_id"> <small>输入购买商品ID。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								购买商品名称</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder="请输入购买商品名称" 
									name="b_name"  data-equal-to="#doc-vld-pwd-1"  required> <small>输入购买商品名称。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								购买数量</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder="请输入购买数量" 
									name="number"  data-equal-to="#doc-vld-pwd-1"  required> <small>输入购买数量。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								购买商品单价</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder="请输入购买商品单价" 
									name="price"  data-equal-to="#doc-vld-pwd-1"  required> <small>输入购买商品单价。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								购买日期</label>
							<div class="am-u-sm-9">
								<input type="date" id="doc-vld-pwd-2" required placeholder="请输入购买日期 " 
									name="date"  data-equal-to="#doc-vld-pwd-1"  required> <small>输入购买日期。</small>
							</div>
						</div>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="确认添加" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="assets/js/libs/jquery-1.10.2.min.js">
	</script>
	<script type="text/javascript" src="myplugs/js/plugs.js">
	</script>
	</body>
</html>