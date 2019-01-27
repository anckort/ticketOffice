<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.User" %>
<%@ page import="entity.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
    <%User user = (User) request.getAttribute("user");%>
</head>
<body>
<form action="/items" method="post">
    <input type="submit" name="btn" value="To menu">
    <div class="title">List of Items</div>
    <table>
        <tr>
            <td><input type="submit" name="btn" value="Add new item"></td>
            <td><input type="submit" name="btn" value="Delete selected"></td>
        </tr>
    </table>
    <table border="1">
        <thead>
        <tr>
            <th></th>
            <th>#</th>
            <th>Name</th>
            <th>Code</th>
        </tr>
        </thead>
        <tbody>
        <% ArrayList <Item> listOfItems = (ArrayList <Item>) request.getAttribute("listOfItems");%>
        <c:forEach var="item" items="${listOfItems}">
            <tr>
                <td><input type="checkbox" name="id" value="${item.getId()}"></td>
                <td> ${item.getId()}</td>
                <td> ${item.getName()}</td>
                <td> ${item.getCode()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
