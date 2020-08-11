<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
		<title>员工管理首页</title>
		<link rel="stylesheet" href="css/amazeui.min.css" />
		<link rel="stylesheet" href="css/admin.css" />
	</head>
	
<body>
<div class="admin-content-body">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">员工管理</strong><small></small></div>
			</div>

			<hr>

			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button type="button" class="am-btn am-btn-default" onclick="window.location.href='addStaffInit'"><span class="am-icon-plus"></span> 新增</button>&emsp;
							<button type="button" class="am-btn am-btn-default" onclick="window.location.href='listStaff'"><span class="am-icon-plus"></span> 返回首页</button>
						</div>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-3">

				</div>
				
				<div class="am-u-sm-12 am-u-md-3">
				<form action="listOneStaff" method="post" enctype="multipart/form-data">
					<div class="am-input-group am-input-group-sm">
						<input type="text" class="am-form-field" placeholder="请输入要搜索的员工编号"
						name="staffId">
						<span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="submit" id="submitBtn" >搜索</button>
          </span>
					</div>
				</form>${staffIdEmptyError}${staffIdNotFound}</div>
				
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<form class="am-form">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
									<th class="table-set" style="width: 94px">员工编号</th>
									<th class="table-author am-hide-sm-only" style="width:74px">员工姓名</th>
									<th class="table-type">相片</th>
									<th class="table-author am-hide-sm-only" style="width:44px">性别</th>
									<th class="table-date am-hide-sm-only">电话</th>
									<th class="table-set">邮件</th>
									<th class="table-set">身份证</th>
									<th class="table-set" style="width: 114px">部门</th>
									<th class="table-set" style="width: 84px">职位</th>
									<th class="table-set">操作</th>
								</tr>
							</thead>
							<c:forEach items="${pageInfo.list}" var="staff"><tbody>
							
								<tr>
									<td >${staff.staffId }</td>
									<td >${staff.staffName }</td>
									<td >
										<img width="150"
						src="${pageContext.request.contextPath}/photo/${staff.photo }">
									</td>
									<td>${staff.sex}</td>
									<td >${staff.phone}</td>
									<td >${staff.email}</td>
									<td>${staff.idcard}</td>
									<td>${staff.department}</td>
									<td>${staff.position}</td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button type="button"class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="window.location.href='upadteStaff?staffId=${staff.staffId }'">><span class="am-icon-pencil-square-o"></span> 编辑员工信息</button>
												<button type="button" class="am-btn am-btn-default am-btn-xs  am-text-secondary" onclick="window.location.href='upadteStaffImage?staffId=${staff.staffId }'"><span class="am-icon-trash-o" ></span> 修改照片</button>
												<button  type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="window.location.href='deleteStaff?staffId=${staff.staffId }&photo=${staff.photo}&pageNo=${pageInfo.pageNum}'"><span class="am-icon-trash-o" ></span> 删除</button>
											</div>
										</div>
									</td>
								</tr>
								</tbody></c:forEach>
								</table>
								<div class="am-cf">
						共 ${pageInfo.total} 条记录,当前正在访问第${pageInfo.pageNum}页
						<div class="am-fr">
							<ul class="am-pagination">
								<li class="">
									<c:if test="${pageInfo.hasPreviousPage }">
										<a href="listStaff?pageNo=${pageInfo.pageNum-1}">«</a>
									</c:if>
								</li>
								<c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
									<li>
										<span class="color"><a href="listStaff?pageNo=${pageNum}">${pageNum}</a></span>
									</li>
								</c:forEach>
								<li>
									<c:if test="${pageInfo.hasNextPage }">
										<a href="listStaff?pageNo=${pageInfo.pageNum+1}">»</a>
									</c:if>
								</li>
							</ul>
						</div>
					</div>
					<hr>
</form></div></div></div>
</body>
</html>