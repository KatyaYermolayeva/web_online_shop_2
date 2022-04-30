<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Role" %>
<html>
    <head>
        <title>Товары</title>
        <style><%@include file="/WEB-INF/View/styles.css"%></style>
    </head>
    <body>
        <h1>
            <a href="home">
                Интернет-магазин
            </a>
        </h1>
        <c:if test="${role == Role.ADMIN}">
            <form action = "add-product" method = "POST" autocomplete="off">
                <label>Название:
                    <input type="text" name="name" placeholder="Название" required="true" errormessage="Укажите названите товара"></label>
                <label>Описание:
                    <textarea name="description" placeholder="Описание" required="true" errormessage="Укажите описание товара"></textarea></label>
                <label>Цена:
                    <input type="number" name="price" min="0" step="0.01" placeholder="0.00" required="true" errormessage="Укажите неотрицательную цену"></label>
                <label>Добавить товар: <input type="submit" value="Добавить"></label>
            </form>
        </c:if>
        <table>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Цена</th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><c:out value="${product.getId()}" /></td>
                    <td><c:out value="${product.getName()}" /></td>
                    <td><c:out value="${product.getDescription()}" /></td>
                    <td><c:out value="${product.getPrice()}" /></td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${role != Role.GUEST}">
            <h2><a href="log-out">Выйти</a></h2>
        </c:if>
    </body>
</html>