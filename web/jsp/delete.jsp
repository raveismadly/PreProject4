<%--
  Created by IntelliJ IDEA.
  User: raves
  Date: 05.03.2020
  Time: 6:02
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
<a href="create">Create</a>
<a href="read">Read</a>
<jsp:useBean id="user" scope="request" type="model.User"/>
<form method="post" name="delete">
    <table>
        <c:if test="${user !=null}">
            <input type="hidden" name="id" value="<c:out value='${user.id}'/>"/>
        </c:if>
        <tr>
            <td>Name</td>
            <th><c:out value="${user.name}"/></th>
            <td>Surname</td>
            <th><c:out value="${user.surname}"/></th>
            <td>Age</td>
            <th><c:out value="${user.age}"/></th>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Delete">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
