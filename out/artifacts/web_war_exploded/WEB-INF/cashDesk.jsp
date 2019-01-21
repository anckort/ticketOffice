<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.CashDeskItem" %>
<html>
<title>
    Cash desk
</title>
<head>

</head>
<body>
<form action="cashDesk" method="post">
    <table>
        <tr>
            <input type="submit" name="btn" value="To menu">
        </tr>
        <tr>
            <td>Please input code or name:</td>
            <td><input type="text" name="fieldForSearch" value=<%= request.getParameter("fieldForSearch")%>></td>
            <td><input type="submit" name="btn" value="Find"></td>
            <td><%
                Item item = (Item) request.getAttribute("item");
                if (item != null) {
                    out.println("(" + item.getCode() + ") " + item.getName());
                }
            %>
            </td>
            <td><input type="text" name="count"></td>
            <td><input type="submit" name="btn" value="Add"></td>
        </tr>
    </table>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Count</th>
        </tr>
        </thead>
        <tbody>
        <% ArrayList <CashDeskItem> listOfItems = (ArrayList <CashDeskItem>) request.getAttribute("listOfItems");
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
    <table>
    <tr><td><input type="submit" name="btn" value="Sale"></td><td><input type="submit" name="btn" value="Cancel"></td></tr>
    </table>
</form>
</body>
</html>