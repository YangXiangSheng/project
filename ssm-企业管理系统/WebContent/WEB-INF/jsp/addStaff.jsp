<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>录入员工信息</title>
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
						 action="addStaff" method="post" enctype="multipart/form-data"
						style="padding-top:30px;" >
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							员工编号 </label>
							<div class="am-u-sm-9">
								<input type="text" name="staffId" placeholder="请输入员工编号"><small>${staffIdEmptyError}${staffIdOnlyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								员工姓名</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入员工姓名" name="staffName" /><small> ${staffNameEmptyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
							性别 </label>
							<div class="am-u-sm-9">
								<input type="radio" name="sex" value="男" checked/>男
		<input type="radio" name="sex" value="女" >女
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								电话</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入员工电话" name="phone" /><small> ${phoneEmptyError}${phoneOnly}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								邮件</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入员工邮件" name="email" /><small> ${emailError}${emailOnly}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								身份证号码</label>
							<div class="am-u-sm-9">
								<input type="text" placeholder="请输入员工身份证号码" name="idcard" /><small> ${idcardError}${idcardOnly}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								员工照片</label>
							<div class="am-u-sm-9">
								<input type="file" multiple class="file" data-upload-url="${pageContext.request.contextPath}/addStaff"
			name="photo" required="required"><small> ${imageErro}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								部门</label>
							<div class="am-u-sm-9">
							<select id="initials" name="department" onchange="Change_second_selectwords();">
			    <option value="" style="color: #b6b6b6" disabled selected> 请选择部门</option>
			    <option value="总经理办公室">总经理办公室</option>
			    <option value="行政人事部">行政人事部</option>
			    <option value="财务部">财务部</option>
			    <option value="生产部">生产部</option>
			    <option value="销售部">销售部</option>
			    
			     </select><small>${departmentEmptyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">
								职位</label>
							<div class="am-u-sm-9">
								<select id="top_domains" name="position">
			    <option value="" style="color: #b6b6b6" disabled selected> 请选择职位</option>
			     	</select>
			     	<!-- 
			    <option value="董事长">董事长</option>
			    <option value="总经理">总经理</option>
			    <option value="副经理">副经理</option>
			    <option value="助理">助理</option>
			    <option value="部长">部长</option>
			    <option value="副部长">副部长</option>
			    <option value="普通员工">普通员工</option>
			     -->
			    <small>${positionEmptyError}</small>
							</div>
						</div>
						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="submit" class="am-btn am-btn-success" value="添加" />
								<input type="reset" class="am-btn am-btn-success" value="重置" />
							</div>
						</div>
						
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