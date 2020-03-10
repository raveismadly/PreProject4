<%--
  Created by IntelliJ IDEA.
  User: raves
  Date: 05.03.2020
  Time: 6:03
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<a href="admin">ADMIN MAIN TITLE</a>

<jsp:useBean id="haveThisUser" scope="request" type="model.User"/>
<c:if test="${haveThisUser!=null}">
<form action="update" METHOD="post">
    </c:if>
    <table>
        <c:if test="${haveThisUser!=null}">
            <input type="hidden" name="id" value="<c:out value='${haveThisUser.id}'/>"/>
        </c:if>
        <tr><th>Mail</th>
            <td><input name="mail" type="text" value="<c:out value='${haveThisUser.mail}'/>"/></td>

            <th>Password</th>
            <td><input name="password" type="text" value="<c:out value='${haveThisUser.password}'/>"/></td>
            <th>Role</th>
            <td><input name="role" type="text"value="<c:out value='${haveThisUser.role}'/>"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Save">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
