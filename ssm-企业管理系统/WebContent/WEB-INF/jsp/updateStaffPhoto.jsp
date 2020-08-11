<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>修改员工照片</title>
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
							<button type="button" class="am-btn am-btn-default" onclick="window.location.href='listStaff'"><span class="am-icon-plus"></span> 返回首页</button>
						</div>
					</div>
				</div>
	</div>
<div class="am-cf admin-main">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-g">
					<form class="am-form am-form-horizontal" action="updateStaffPhoto" method="post" enctype="multipart/form-data"style="padding-top:30px;" >
						<c:forEach items="${Staff}" var="staff">
						<input type="hidden" name="staffId" value="${staff.staffId}">
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							${staff.staffName }未修改前的照片 </label>
							<div class="am-u-sm-9">
								<img width="150"src="${pageContext.request.contextPath}/photo/${staff.photo }">
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							选择照片 </label>
							<div class="am-u-sm-9">
								<input type="file" multiple class="file" data-upload-url="${pageContext.request.contextPath}/updateStaffPhoto"
			name="photo" required="required"><small>${imageErro}</small>
							</div>
						</div>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="修改" />
								<input type="reset" class="am-btn am-btn-success" value="重置" />
							</div>
						</div>
	</c:forEach>
</form></div></div></div></div>
</body>
</html>