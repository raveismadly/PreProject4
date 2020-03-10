<%--
  Created by IntelliJ IDEA.
  User: raves
  Date: 10.03.2020
  Time: 5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h1>Hello User</h1>
<form action="${pageContext.request.contextPath}/login" method="get">
    <input type="submit" value="Go to Authorisation page">
</form>

</body>
</html>
