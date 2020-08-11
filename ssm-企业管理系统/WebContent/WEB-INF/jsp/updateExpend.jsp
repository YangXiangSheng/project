<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>修改支出信息</title>
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
							<button type="button" class="am-btn am-btn-default" onclick="window.location.href='listExpend'"><span class="am-icon-plus"></span> 返回支出管理首页</button>
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
						 action="updateExpend" method="post"
						style="padding-top:30px;" >
						<c:forEach items="${Expend}" var="expend">
						<center>
						<td align="center">修改${expend.date}支出的信息</td></center>
	<input type="hidden" name="date" value="${expend.date}">
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							支出 </label>
							<div class="am-u-sm-9">
								<input type="text" name="expenditure" value="${expend.expenditure}" placeholder="请输入支出"><small>${expenditureEmptyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								备注</label>
							<div class="am-u-sm-9">
								<textarea placeholder="请输入备注" name="information" >${expend.information}</textarea><small>${informationEmptyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="修改" />
								<input type="reset" class="am-btn am-btn-success" value="重置" />
							</div>
						</div>
						</c:forEach>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>