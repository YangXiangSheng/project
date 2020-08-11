<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest"  %>
<!DOCTYPE html>
<html>
<%
String id = request.getParameter("id");
String b_id = request.getParameter("b_id");
String b_name = request.getParameter("b_name");
String number = request.getParameter("number");
String price = request.getParameter("price").trim();
String date = request.getParameter("date").trim();
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
					<form action="../updatebuy" class="am-form am-form-horizontal"
						 method="post"
						style="padding-top:30px;" data-am-validator>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							产品ID</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-1" required placeholder=<%=b_id %>
									name="b_id" value=<%=b_id %>> <small>输入产品ID。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								产品名称</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder=<%=b_name %>
									name="b_name"  data-equal-to="#doc-vld-pwd-1"  required value=<%=b_name %>> <small>输入产品名称。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								购买数量</label>
							<div class="am-u-sm-9">
								<input type="text" id="doc-vld-pwd-2" required placeholder=<%=number %> 
									name="number"  data-equal-to="#doc-vld-pwd-1"  required value=<%=number %>> <small>输入购买数量。</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								购买单价</label>
							<div class="am-u-sm-9">
							<% if(price.isEmpty()){ %>
							<input type="text" id="doc-vld-pwd-2" required placeholder="暂无设定价格"
									name="price"  data-equal-to="#doc-vld-pwd-1" > <small>输入购买单价。</small>
						   	<%}else{ %>
								<input type="text" id="doc-vld-pwd-2" required placeholder=<%= price %> 
									name="price"  data-equal-to="#doc-vld-pwd-1"  required value=<%=price %>> <small>输入购买单价。</small>
							<%}%>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								购买日期</label>
							<div class="am-u-sm-9">
								<input type="date" id="doc-vld-pwd-2" required placeholder=<%=date %> 
									name="date"  data-equal-to="#doc-vld-pwd-1"  required value=<%=date %>> <small>输入购买日期。</small>
							</div>
						</div>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="确认修改" />
							</div>
						</div>
						<input name="pageNo" type="hidden" value=<%=pageNo %> >
						<input name="id" type="hidden" value=<%=id %> >
						<input type="hidden" name="old_date" value=<%=date %>>
						<input type="hidden" name="old_number" value=<%=number %>>
						<input type="hidden" name="old_price" value=<%=price %>>
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