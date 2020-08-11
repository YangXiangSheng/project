<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
		<title>支出管理首页</title>
		<link rel="stylesheet" href="css/amazeui.min.css" />
		<link rel="stylesheet" href="css/admin.css" />
	</head>
	
<body>
<div class="admin-content-body">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">支出管理</strong><small></small></div>
			</div>

			<hr>

			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button type="button" class="am-btn am-btn-default" onclick="window.location.href='addExpendInit'"><span class="am-icon-plus"></span> 新增</button>&emsp;
							<button type="button" class="am-btn am-btn-default" onclick="window.location.href='listExpend'"><span class="am-icon-plus"></span> 返回支出管理首页</button>
						</div>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-3">

				</div>
				
				<div class="am-u-sm-12 am-u-md-3">
				<form   action="listOneDateExpend" method="post" enctype="multipart/form-data">
					<div class="am-input-group am-input-group-sm">
						<input type="date" class="am-form-field"
						name="date">
						<input type="hidden" name="pageNo" value="${pageInfo.pageNum}" />
						<span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="submit" id="submitBtn" >搜索某一天的支出</button>
          </span>
					</div>
				</form>${DateEmpty}${NotExpend}
				<form   action="countExpend" method="post" enctype="multipart/form-data">
					<div class="am-input-group am-input-group-sm">
						<input type="date" class="am-form-field" 
						name="date">
						<input type="hidden" name="pageNo" value="${pageInfo.pageNum}" />
						<span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="submit"  >搜索某一月的支出</button>
          </span>
					</div>
				</form>
				</div>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<form class="am-form">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th class="table-set">日期</th>
									<th class="table-author am-hide-sm-only">支出</th>
									<th class="table-type">备注</th>
									<th class="table-set">操作</th>
								</tr>
							</thead>
							<c:forEach items="${Expend}" var="expend"><tbody>
							
								<tr>
									<td >${expend.date }</td>
									<td >${expend.expenditure }元</td>
									<td>${expend.information}</td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button type="button"class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='updateExpendInit?date=${expend.date }'">><span class="am-icon-pencil-square-o"></span> 编辑该天支出</button>
												<button  type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="window.location.href='deleteExpend?date=${expend.date }'"><span class="am-icon-trash-o" ></span> 删除</button>
											</div>
										</div>
									</td>
								</tr>
								</tbody></c:forEach>
								</table>
						
</form></div></div></div>
</body>
</html>