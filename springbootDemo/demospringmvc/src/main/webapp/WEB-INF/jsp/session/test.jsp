<%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 2020/8/9
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.springboot.demospringmvc.pojo.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>i!IJi式＠SessionAttributes</title>
</head>
<body>
<% User user=(User) session.getAttribute("user");
Long id=(Long) session.getAttribute("id_new");
out.print("<br>user_name="+user.getUserName());
out.println("<br>id_name="+id);
%>

</body>
</html>
