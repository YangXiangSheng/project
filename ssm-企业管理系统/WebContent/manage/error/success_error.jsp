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
	alert("操作失败，此产品库存不存在或库存不足！请认真检查");
	//window.parent.frames.location.href="houtai/shop_message.jsp";
	window.location.href="../../order_message?pageNo="+<%=pageNo%>;
</script>
</body>
</html>