<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest"  %>
<%@page import="java.util.List" %>
<%@page import="com.MaikeDun.bean.Client" %>
<%@page import="com.MaikeDun.service.UserService" %>
<!DOCTYPE html>
<html>
<%
String c_id = request.getParameter("c_id");
String c_name = request.getParameter("c_name");
String phone = request.getParameter("phone");
String address = request.getParameter("address");
%>
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
					<form action="../updateshop" class="am-form am-form-horizontal"
						 method="post"
						style="padding-top:30px;" data-am-validator>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							客户ID</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-1" readonly="readonly" placeholder=<%=c_id %>
									name="s_id" value=<%=c_id %>> 
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								客户名称</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder=<%=c_name %>
									name="s_name"  data-equal-to="#doc-vld-pwd-1" readonly="readonly" required value=<%=c_name %>>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								联系方式</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder=<%=phone %> 
									name="number"  data-equal-to="#doc-vld-pwd-1" readonly="readonly" required value=<%=phone %>> 
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								联系地址</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder=<%= address %> 
									name="price"  data-equal-to="#doc-vld-pwd-1" readonly="readonly" required value=<%=address %>>
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