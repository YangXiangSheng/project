<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.MaikeDun.bean.Staff" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
	<title>麦可顿后台管理系统</title>
	<link rel="stylesheet" href="css/layui.css"/>
	<link rel="stylesheet" href="css/amazeui.min.css" />
	<link rel="stylesheet" href="css/admin.css" />
</head>
	<body class="layui-layout-body">
	<%
	Staff staff = (Staff)session.getAttribute("staff"); 
	if(staff == null){
	%>
	<script>alert("你尚未登录，无法进入！");window.location.href="../initlogin"</script>
	<% 	
	}else{
	int flag = Integer.valueOf(staff.getFlag());
	%>
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<div class="layui-logo">麦可顿后台管理系统</div>
				<!-- 头部区域（可配合layui已有的水平导航） -->

				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item">
						<a href="javascript:;">
							<img src="images/1.gif" class="layui-nav-img"/> ${staff.name}</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="">基本资料</a>
							</dd>
							<dd>
								<a href="">安全设置</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="../logout"><span class="am-icon-power-off" style="padding-right: 5px"></span>注销</a>
					</li>
				</ul>
			</div>

			<div class="layui-side layui-bg-black">
				<div class="layui-side-scroll">
					<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
					<ul class="layui-nav layui-nav-tree" lay-filter="test">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="" href="javascript:;">
								<span class="am-icon-user" style="padding-right: 15px"></span>账号管理
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="account_message.jsp" target="right">账号信息</a>
								</dd>
								<dd>
									<a href="javascript:void(0)"  onclick="updatePwd('修改密码',1)">更换密码</a>
								</dd>
								
							</dl>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<span class="am-icon-home" style="padding-right: 15px"></span>公司情报
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${pageContext.request.contextPath}/manage/sex_pie.jsp" target="right">信息概览</a>
								</dd>								
							</dl>
						</li>
						<% if(flag == 1 || flag == 4 || flag == 5){ %>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<span class="am-icon-wrench" style="padding-right: 15px"></span>产品管理
							</a>
							<dl class="layui-nav-child">
								<% if(flag == 1 || flag == 5){%>
								<dd>
									<a href="../shop_message" target="right">产品信息</a>
								</dd>	
								<dd>
									<a href="../shop_production" target="right">生产详细</a>
								</dd>
								<%} %>		
								<% if(flag == 1 || flag == 4){%>
								<dd>
									<a href="../shop_sale" target="right">销售详细</a>
								</dd>	
								<%} %>							
							</dl>
						</li>
						<%} %>
						<% if(flag == 1 || flag == 5){%>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<span class="am-icon-leaf " style="padding-right: 15px"></span>购买管理
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="../buy_message" target="right">购买原料记录</a>
								</dd>								
							</dl>
						</li>
						<% } %>
						<% if(flag == 1 || flag == 4){%>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<span class="am-icon-file" style="padding-right: 15px"></span>合同管理
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="../order_message" target="right">合同订单</a>
								</dd>								
							</dl>
						</li>
						<% } %>
						<% if(flag == 1 || flag == 4){%>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<span class="am-icon-magnet" style="padding-right: 15px"></span>客户管理
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="../client_message" target="right">客户详细</a>
								</dd>								
							</dl>
						</li>
						<% } %>
						<% if(flag == 1 || flag == 3){%>						
						<li class="layui-nav-item">
							<a href="javascript:;">
								<span class="am-icon-money" style="padding-right: 15px"></span>财务管理
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="../listExpend" target="right">支出</a>
								</dd>
								<dd>
									<a href="../listAchievement" target="right">收入</a>
								</dd>								
							</dl>
						</li>
						<%} %>
						<% if(flag == 1 || flag == 2){%>
						<li class="layui-nav-item">
							<a href="javascript:;">
								<span class="am-icon-group" style="padding-right: 15px"></span>人力资源管理
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="../listStaff" target="right">员工信息</a>
								</dd>	
							</dl>
						</li>
						<%}} %>

					</ul>
				</div>
			</div>

			<div class="layui-body" style="z-index: 0;">
				<!-- 内容主体区域 -->
				<div style="padding: 10px;">
					<iframe src="account_message.jsp"  name="right" frameborder="0"  width="100%" height="1500"></iframe>
				</div>
			</div>

			<div class="layui-footer">
				第26小组
			</div>  

		</div>
		
		<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="myplugs/js/plugs.js">
		</script>
		<script type="text/javascript">
			//添加编辑弹出层
			function updatePwd(title, id) {
				$.jq_Panel({
					title: title,
					iframeWidth: 500,
					iframeHeight: 300,
					url: "account_updatePwd.jsp"
				});
			}
		</script>
		<script src="js/layui.js"></script>
		<script>
			//JavaScript代码区域
			layui.use('element', function() {
				var element = layui.element;

			});
		</script>
		
	</body>

</html>