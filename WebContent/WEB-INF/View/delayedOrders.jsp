<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Role" %>
<html>
    <head>
        <title>Задержанные заказы</title>
        <style><%@include file="/WEB-INF/View/styles.css"%></style>
    </head>
    <body>
        <h1>
            <a href="home">
                Интернет-магазин
            </a>
        </h1>
        <table id="orders">
            <tr>
                <th>ID</th>
                <th>Клиент</th>
                <th>Дата доставки</th>
                <th>Состояние</th>
                <th>Товары</th>
            </tr>
            <c:forEach items="${orders}" var="item">
                <tr>
                    <td><c:out value="${item.getId()}" /></td>
                    <td><c:out value="${item.getClient().getUsername()}" /></td>
                    <td><c:out value="${item.getDeliveryDate()}" /></td>
                    <td><c:out value="${item.getStatus().getName()}" /></td>
                    <td><a href="${pageContext.request.contextPath}/orders-products?id={item.getId()}"><c:out value="Список товаров" /></a></td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${role != Role.GUEST}">
            <h2><a href="log-out">Выйти</a></h2>
        </c:if>
    </body>
</html>