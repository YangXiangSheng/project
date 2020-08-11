<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>修改员工信息</title>
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
					<form class="am-form am-form-horizontal"
						 action="updateStaffFinal" method="post"
						style="padding-top:30px;" >
						<c:forEach items="${Staff}" var="staff">
	<input type="hidden" name="staffOldId" value="${staff.staffId}">
	<input type="hidden" name="old_email" value="${staff.email}">
	<input type="hidden" name="old_phone" value="${staff.phone}">
	<input type="hidden" name="old_idcard" value="${staff.idcard}">
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							员工编号 </label>
							<div class="am-u-sm-9">
								<input type="text" name="staffNewId" value="${staff.staffId}" placeholder="请输入员工编号"><small>${staffIdEmptyError}${staffIdOnlyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								员工姓名</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入员工姓名" name="staffName"value="${staff.staffName }" /><small> ${staffNameEmptyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							性别 </label>
							<div class="am-u-sm-9">
								<input type="radio" name="sex" value="男" <c:if test="${staff.sex eq '男'}"> checked="checked"</c:if>/>男
		<input type="radio" name="sex" value="女"  <c:if test="${staff.sex eq '女'}"> checked="checked"</c:if>>女
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								电话</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入员工电话" name="phone" value="${staff.phone}"/><small> ${phoneEmptyError}${phoneOnly}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								邮件</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入员工邮件" name="email" value="${staff.email}"/><small> ${emailError}${emailOnly}</small>
							</div>
						</div>
					<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								身份证</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入员工邮件" name="idcard" value="${staff.idcard}"/><small> ${idcardError}${idcardOnly}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								部门</label>
							<div class="am-u-sm-9">
								<select id="initials" name="department" onchange="Change_second_selectwords();">
			    <option value="" style="color: #b6b6b6" disabled selected> 请选择部门</option>
			    <option value="总经理办公室" <c:if test="${staff.department eq '总经理办公室'}"> selected="selected"</c:if>>总经理办公室</option>
			    <option value="行政人事部" <c:if test="${staff.department eq '行政人事部'}"> selected="selected" </c:if>>行政人事部</option>
			    <option value="财务部" <c:if test="${staff.department eq '财务部'}"> selected="selected"</c:if>>财务部</option>
			    <option value="生产部" <c:if test="${staff.department eq '生产部'}"> selected="selected"</c:if>>生产部</option>
			    <option value="销售部" <c:if test="${staff.department eq '销售部'}"> selected="selected"</c:if>>销售部</option>
			   
			     </select><small>${departmentEmptyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								职位</label>
							<div class="am-u-sm-9">
								<select id="top_domains" name="position">
			    <option value="" style="color: #b6b6b6" disabled selected> 请选择职位</option>
			  
			    <option value="董事长" <c:if test="${staff.position eq '董事长'}"> selected="selected"</c:if>>董事长</option>
			    <option value="总经理" <c:if test="${staff.position eq '总经理'}"> selected="selected"</c:if>>总经理</option>
			    <option value="副经理" <c:if test="${staff.position eq '副经理'}"> selected="selected"</c:if>>副经理</option>
			    <option value="助理" <c:if test="${staff.position eq '助理'}"> selected="selected"</c:if>>助理</option>
			    <option value="部长" <c:if test="${staff.position eq '部长'}"> selected="selected"</c:if>>部长</option>
			    <option value="副部长" <c:if test="${staff.position eq '副部长'}"> selected="selected"</c:if>>副部长</option>
			    <option value="普通员工" <c:if test="${staff.position eq '普通员工'}"> selected="selected"</c:if>>普通员工</option>
			  
			     </select><small>${positionEmptyError}</small>
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
	<script type="text/javascript">
        var first_keywords = {};
        //定义每个字母对应的第二个下拉框
        first_keywords['总经理办公室'] = ['董事长','总经理','副经理','助理'];
        first_keywords['行政人事部'] = ['部长','副部长','普通员工'];
        first_keywords['财务部'] =['部长','副部长','普通员工'];
        first_keywords['生产部'] = ['部长','副部长','普通员工'];
        first_keywords['销售部'] = ['部长','副部长','普通员工'];
        function Change_second_selectwords() {
            //根据id找到两个下拉框对象
            var target1 = document.getElementById("initials");
            var target2 = document.getElementById("top_domains");
            //得到第一个下拉框的内容
            var selected_initial = target1.options[target1.selectedIndex].value;

            //清空第二个下拉框
            while (target2.options.length) {
                target2.remove(0);
            }
        //根据第一个下拉框的内容找到对应的列表
        var initial_list = first_keywords[selected_initial];
        if (initial_list) {
            for (var i = 0; i < initial_list.length; i++) {
                var item = new Option(initial_list[i],initial_list[i]);
                //将列表中的内容加入到第二个下拉框
                target2.options.add(item);
            }
        }
} 
</script>	
</body>
</html>