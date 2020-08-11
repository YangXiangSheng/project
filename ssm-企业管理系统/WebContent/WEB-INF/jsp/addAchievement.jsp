<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>录入收入信息</title>
<meta charset="UTF-8">
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
<hr>
<hr>
<div class="am-g">
	<div class="am-u-sm-12 am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button type="button" class="am-btn am-btn-default" onclick="window.location.href='listAchievement'"><span class="am-icon-plus"></span> 返回收入管理首页</button>
						</div>
					</div>
				</div>
	</div>
<div class="am-cf admin-main">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-g">
					<form class="am-form am-form-horizontal"
						 action="addAchievement" method="post" style="padding-top:30px;" >
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							日期</label>
							<div class="am-u-sm-9">
								<input type="date" name="date" placeholder="请输入日期"><small>${DateEmpty}${dateOnly}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								收入</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入改天收入" name="turnover" /><small> ${turnoverEmptyError}</small>
							</div>
						</div>
						
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								备注</label>
							<div class="am-u-sm-9">
								<textarea placeholder="请输入备注" name="information" ></textarea><small> ${informationEmptyError}</small>
							</div>
						</div>
						
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="添加" />
								<input type="reset" class="am-btn am-btn-success" value="重置" />
							</div>
						</div>
						
					</form>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>