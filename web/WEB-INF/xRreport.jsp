<%@ page import="entity.CashDeskItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>X report</title>
<head>

</head>
<body>
<form action="xReport" method="post">
    <table>
        <tr>
            <td>Date:</td>
            <td><input type="date" name="date"></td>
            <td><input type="submit" name="btn" value="Generate"></td>
        </tr>
    </table>
    <table>
        <thead>
            <tr>
                <td>Name</td>
                <td>Count</td>
            </tr>
        </thead>
        <tbody>
        <% ArrayList<CashDeskItem> listOfItems = (ArrayList <CashDeskItem>) request.getAttribute("listOfItems");
            request.setAttribute("listOfItems",listOfItems);%>
        <c:set var="listOfItems" value="${listOfItems}" scope="session"/>
        <c:forEach var="item" items="${listOfItems}">
            <tr>
                <td> ${item.getItem().getName()}</td>
                <td> ${item.getCount()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>