<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String pageNo = request.getParameter("pageNo").trim();
%>
<script type="text/javascript">
	alert("操作成功！");
	//window.parent.frames.location.href="houtai/shop_message.jsp";
	window.parent.frames.location.href="../../shop_message?pageNo="+<%=pageNo%>;
</script>
</body>
</html>