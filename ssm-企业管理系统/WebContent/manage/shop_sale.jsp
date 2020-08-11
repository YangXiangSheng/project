<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.HttpServletRequest"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="css/amazeui.min.css" />
<link rel="stylesheet" href="css/admin.css" />
</head>
<style>
.color a:hover{
	background-color: #00FFFF;
}
</style>
<%
String name = request.getParameter("name");
%>
<body>
	<div class="admin-content-body">
		<div class="am-cf am-padding am-padding-bottom-0">
			<div class="am-fl am-cf">
				<strong class="am-text-primary am-text-lg">销售记录管理</strong><small></small>
			</div>
		</div>

		<hr>

		<div class="am-g">
			<div class="am-u-sm-12 am-u-md-6">
				<div class="am-btn-toolbar">
					<div class="am-btn-group am-btn-group-xs">
						<button type="button" class="am-btn am-btn-default"
							onclick="addshop('添加产品',1)">
							<span class="am-icon-plus"></span> 新增
						</button>
					</div>
				</div>
			</div>
			<div class="am-u-sm-12 am-u-md-3"></div>
			<div class="am-u-sm-12 am-u-md-3">
				<div class="am-input-group am-input-group-sm">
				<%if(name!=null){ %>
					<input id="shop_search" type="text" class="am-form-field" placeholder="请输入产品ID或产品名称"  value=<%=name %>>
				<%}else{ %>	
					<input id="shop_search" type="text" class="am-form-field" placeholder="请输入产品ID或产品名称" >
				<%} %>	
					<span class="am-input-group-btn">
						<button class="am-btn am-btn-default" type="button" onclick="searchshop()">搜索</button>
					</span>
				</div>
			</div>
		</div>
		<div class="am-g">
			<div class="am-u-sm-12">
				<form class="am-form">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th class="table-check"><input type="checkbox"></th>
								<th class="table-id">ID</th>
								<th class="table-s_id">产品ID</th>
								<th class="table-title">产品名称</th>
								<th class="table-number">销售数量</th>
								<th class="table-date">销售日期</th>
								<th class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
						   <c:forEach items="${pageInfo.list}" var="shop">
							<tr>
								<td><input type="checkbox"></td>
								<td>${shop.id}</td>
								<td>${shop.s_id}</td>
								<td><a href="#">${shop.s_name}</a></td>
								<td>${shop.number}</td>
								<td>${shop.date}</td>
								<td>
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">
											<button type="button"
												class="am-btn am-btn-default am-btn-xs am-text-secondary"
												onclick="updateshop('编辑产品',' ${shop.id}','${shop.s_id}','${shop.s_name}','${shop.number}','${shop.date}')">
												<span class="am-icon-pencil-square-o"></span> 编辑
											</button>
											<button type="button"
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
												onclick="deleteshop('删除产品',' ${shop.id}','${shop.s_id}','${shop.number}','${shop.date}')">
												<span class="am-icon-trash-o"></span> 删除
											</button>
										</div>
									</div>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="am-cf">
						共 ${pageInfo.total} 条记录,当前正在访问第${pageInfo.pageNum}页
						<div class="am-fr">
							<ul class="am-pagination">
								<li class="">
									<c:if test="${pageInfo.hasPreviousPage }">
										<a href="../shop_sale?pageNo=${pageInfo.pageNum-1}">«</a>
									</c:if>
								</li>
								<c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
									<li>
										<span class="color"><a href="../shop_sale?pageNo=${pageNum}">${pageNum}</a></span>
									</li>
								</c:forEach>
								<li>
									<c:if test="${pageInfo.hasNextPage }">
										<a href="../shop_sale?pageNo=${pageInfo.pageNum+1}">»</a>
									</c:if>
								</li>
							</ul>
						</div>
					</div>
					<hr>
				</form>
			</div>

		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="myplugs/js/plugs.js"></script>
	<script type="text/javascript">
		//添加编辑弹出层
		function addshop(title, id) {
			$.jq_Panel({
				title : title,
				iframeWidth : 500,
				iframeHeight : 300,
				url : "shop_add_sale.jsp"
			});
		}
		function deleteshop(title, id,s_id,number,date) {
			 if (window.confirm ("你确定真的要删除吗")){
				 window.location.href="../deleteshopsale?id="+id+"&number="+number+"&date="+date+"&s_id="+s_id+"&page="+${pageInfo.pageNum};
				 alert(id+"已经被删除");
				 }else{
					 alert("你已取消删除"); 
				 }
		    }
		function updateshop(title, id,s_id,name,number,date) {		
			var page = ${pageInfo.pageNum};
			$.jq_Panel({
				title : title,
				iframeWidth : 500,
				iframeHeight : 300,
				url : "shop_update_sale.jsp?id="+id+"&s_name="+name+"&number="+number+"&date="+date+"&s_id="+s_id+"&page="+page
			});
		    }
		function searchshop() {
			var name = document.getElementById("shop_search").value
			window.location.href="../searchshopsale?name="+name;
		}
	</script>

</body>

</html>