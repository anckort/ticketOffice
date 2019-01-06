<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Warehouse</title>
    <%User user = (User) request.getAttribute("user");%>
</head>
<body>
<div class="title">List of Items</div>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Code</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${listOfItems}">
            <tr>
                <td>${item.iditem}</td>
                <td>${item.name}</td>
                <td>${item.code}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
