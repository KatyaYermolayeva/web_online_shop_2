<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Role" %>
<html>
    <head>
        <title>Товары в заказе</title>
        <style><%@include file="/WEB-INF/View/styles.css"%></style>
    </head>
    <body>
        <h1>
            <a href="home">
                Интернет-магазин
            </a>
        </h1>
        <c:if test="${role == Role.USER && isPending}">
            <form action = "add-order-product" method = "POST" autocomplete="off">
                <input type="hidden" name="id" value="${id}">
                <label>Товар:
                    <input type="number" min="1" placeholder="1" name="product" required="true" errormessage="Укажите идентификатор товара (положительное целое число)"></label>
                <label>Количество:<input type="number" name="amount" min="1" placeholder="1" required="true" errormessage="Укажите положительно целое количество"></label>
                <label>Добавить товар: <input type="submit" value="Добавить"></label>
            </form>
        </c:if>
        <table>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Цена</th>
                <th>Количество</th>
            </tr>
            <c:forEach items="${ordersProducts}" var="product">
                <tr>
                    <td><c:out value="${product.key.getId()}" /></td>
                    <td><c:out value="${product.key.getName()}" /></td>
                    <td><c:out value="${product.key.getDescription()}" /></td>
                    <td><c:out value="${product.key.getPrice()}" /></td>
                    <td><c:out value="${product.value}" /></td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${role != Role.GUEST}">
            <h2><a href="log-out">Выйти</a></h2>
        </c:if>
    </body>
</html>