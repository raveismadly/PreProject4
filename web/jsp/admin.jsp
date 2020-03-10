<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>

<a href="login">Read</a>
<table>
    <th>Id</th>
    <th>Mail</th>
    <th>Password</th>
    <th>Role</th>
    <th>Actions</th>
    <jsp:useBean id="allUsers" scope="request" type="java.util.List"/>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.mail}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><a href="update?id=<c:out value='${user.id}'/>">Update</a></td>
            <td><a href="delete?id=<c:out value='${user.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
