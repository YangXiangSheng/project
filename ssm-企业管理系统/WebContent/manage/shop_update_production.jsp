<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest"  %>
<!DOCTYPE html>
<html>
<%
String id = request.getParameter("id");
String s_id = request.getParameter("s_id");
String name = request.getParameter("s_name");
String number = request.getParameter("number");
String date = request.getParameter("date");
String pageNo = request.getParameter("page").trim();
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
					<form action="../updateshopproduct" class="am-form am-form-horizontal"
						 method="post"
						style="padding-top:30px;" data-am-validator>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							产品ID</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-1" required readonly="readonly" placeholder=<%=id %>
									name="s_id" value=<%=s_id %>> <small>输入产品ID。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								产品名称</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required readonly="readonly" placeholder=<%=name %>
									name="s_name"  data-equal-to="#doc-vld-pwd-1"  required value=<%=name %>> <small>输入产品名称。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								生产数量</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder=<%=number %> 
									name="number"  data-equal-to="#doc-vld-pwd-1"  required value=<%=number %>> <small>输入生产数量。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								生产日期</label>
							<div class="am-u-sm-9">
								<input type="date" id="doc-vld-pwd-2" required placeholder=<%=date %> 
									name="date"  data-equal-to="#doc-vld-pwd-1"  required value=<%=date %>> <small>输入生产日期。</small>
							</div>
							<input type="hidden" name="old_number" value=<%=number %>>
							<input type="hidden" name="id" value=<%=id %>>
						</div>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="确认修改" />
							</div>
						</div>
						<input name="pageNo" type="hidden" value=<%=pageNo %> >
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