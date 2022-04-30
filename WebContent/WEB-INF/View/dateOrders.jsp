<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Role" %>
<html>
    <head>
        <title>Заказы на дату</title>
        <style><%@include file="/WEB-INF/View/styles.css"%></style>
    </head>
    <body>
        <h1>
            <a href="home">
                Интернет-магазин
            </a>
        </h1>
        <form action = "date-orders" method = "POST" autocomplete="off">
            <label>Дата доставки:
                <input type="date" name="deliveryDate" required="true" error-message="Укажите дату доставки (не ранее сегодняшней)" min="2022-04-18"></label>
            <label>Найти: <input type="submit" value="Найти"></label>
        </form>
        <table>
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
                    <td><a href="${pageContext.request.contextPath}/orders-products?id=${item.getId()}"><c:out value="Список товаров" /></a></td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${role != Role.GUEST}">
            <h2><a href="log-out">Выйти</a></h2>
        </c:if>
    </body>
</html>