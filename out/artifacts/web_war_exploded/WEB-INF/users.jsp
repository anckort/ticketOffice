<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>
    Users
</title>
<head>

</head>
<body>
<form action="/users" method="post">
    <input type="submit" name="btn" value="To menu">
    <input type="submit" name="btn" value="Add new user">
    <table border="1">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <% ArrayList <User> listOfUsers = (ArrayList <User>) request.getAttribute("listOfUsers");%>
        <c:forEach var="item" items="${listOfUsers}">
            <tr>
                <td> ${item.getId()}</td>
                <td> ${item.getUsername()}</td>
                <td> ${item.getRole()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</form>
</body>
</html>