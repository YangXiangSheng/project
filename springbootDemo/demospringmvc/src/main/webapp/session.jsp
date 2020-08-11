<%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 2020/8/9
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>Session</title>
</head>
<body>
<%
    session.setAttribute("id",1L);
    response.sendRedirect("./session/test");
%>
</body>
</html>
