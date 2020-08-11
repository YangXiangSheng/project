<%@page import="com.MaikeDun.bean.Shop"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.HttpServletRequest"  %>
<%@ page import="javax.servlet.http.HttpSession"  %>
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
String name = (String)request.getParameter("name");
%>
<body onload="init();">
	<div class="admin-content-body">
		<div class="am-cf am-padding am-padding-bottom-0">
			<div class="am-fl am-cf">
				<strong class="am-text-primary am-text-lg">合同订单管理</strong><small></small>
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
					<input id="shop_search" type="text" class="am-form-field" placeholder="请输入产品ID或客户ID"  value=<%=name %>>
				<%}else{ %>	
					<input id="shop_search" type="text" class="am-form-field" placeholder="请输入产品ID或客户ID"" >
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
								<th class="table-title">产品ID</th>
								<th class="table-title">客户ID</th>
								<th class="table-number">交易数量</th>
								<th class="table-DATE">交易日期</th>
								<th class="table-DATE">完成日期</th>
								<th class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
						   <c:forEach items="${pageInfo.list}" var="shop">
							<tr>
								<td><input type="checkbox"></td>
								<td>${shop.o_id}</td>
								<td>${shop.s_id}</td>
								<td><a href="javascript:;" onclick="c_message('客户信息','${shop.c_id}')">${shop.c_id}</a></td>
								<td>${shop.o_number}</td>
								<td>${shop.o_time}</td>
								<td>${shop.publish_time}</td>
								<td>
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">
											<!-- 判断是否已完成交易 -->
											<c:set var="flag_pub" scope="request" value="${shop.flag}" />										
											<%
											int flag = (int)request.getAttribute("flag_pub"); 
											if(flag == 0){
											%> 										
											<button type="button"
												class="am-btn am-btn-default am-btn-xs am-text-secondary"
												onclick="updateshop('编辑产品',' ${shop.o_id}','${shop.s_id}','${shop.c_id}','${shop.o_number}','${shop.o_time}','${shop.photo}')">
												<span class="am-icon-pencil-square-o"></span> 编辑
											</button>
											<%}else{ } %>
											<button type="button"
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
												onclick="deleteshop('删除产品',' ${shop.o_id}')">
												<span class="am-icon-trash-o"></span> 删除
											</button>
											<%
											if(flag == 0){
											%> 																							
											<button type="button" id="success"
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
												onclick="success_order('完成订单',' ${shop.o_id}','${shop.s_id}','${shop.o_number}')">
												<span class="am-icon-check"></span> 完成
											</button>
											<%}else{ } %>
											<button type="button" id="bottons" style="VISIBILITY: hidden"
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
												onclick="seephoto('${shop.photo}')">
												<span class="am-icon-pencil-square-o"></span> 查看
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
										<a href="../order_message?pageNo=${pageInfo.pageNum-1}">«</a>
									</c:if>
								</li>
								<c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
									<li>
										<span class="color"><a href="../order_message?pageNo=${pageNum}">${pageNum}</a></span>
									</li>
								</c:forEach>
								<li>
									<c:if test="${pageInfo.hasNextPage }">
										<a href="../order_message?pageNo=${pageInfo.pageNum+1}">»</a>
									</c:if>
								</li>
							</ul>
						</div>
					</div>
					<hr>
				</form>
				<hr style=" height:2px;border:none;border-top:2px dotted #185598;"/>
				<div align="center">
					<img src="" id="oImg" style='display:none' width="800px" hight="1400px"> 
				</div>
				
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="myplugs/js/plugs.js"></script>
	<script type="text/javascript">
		function seephoto(photo){
			 document.getElementById('oImg').src = "${pageContext.request.contextPath}/photo/"+photo;
			 document.getElementById('oImg').style.display = "block";  			 
		}
		function init(){
		      document.getElementById("bottons").click();
		}
		function addshop(title, id) {
			window.location.href="order_add.jsp";			
		}
		function deleteshop(title, id) {
			 if (window.confirm ("你确定真的要删除吗")){
				 window.location.href="../deleteorder?id="+id+"&page="+${pageInfo.pageNum};
				 alert("合同"+id+"已经被删除");
				 }else{
					 alert("你已取消删除"); 
				 }
		    }
		function updateshop(title,o_id,s_id,c_id,o_number,o_time,photo) {			 
			window.location.href="order_update.jsp?o_id="+o_id+"&s_id="+s_id+"&c_id="+c_id+"&o_number="+o_number+"&o_time="+o_time+"&photo="+photo+"&page="+${pageInfo.pageNum};
		    }
		function searchshop() {
			var name = document.getElementById("shop_search").value
			window.location.href="../searchorder?name="+name;
		}
		function success_order(title,o_id,s_id,number){
			if (window.confirm ("你确定已完成此合同订单？无法取消!")){
				if(window.confirm ("再次确认！")){
					var page = ${pageInfo.pageNum};
					window.location.href="../successorder?o_id="+o_id+"&pageNo="+page+"&s_id="+s_id+"&number="+number;
					alert("合同"+id+"已确认完成！");
				}
             }else{
                alert("你已取消完成");
            }
		}
		function c_message(title,c_id){
			$.jq_Panel(
					{
					title : title,
					iframeWidth : 500,
					iframeHeight : 300,
					url : "../c_message?c_id="+c_id
					}
						);
			
		}
	</script>

</body>

</html>