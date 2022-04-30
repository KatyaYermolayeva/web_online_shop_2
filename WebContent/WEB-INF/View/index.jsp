<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Role" %>
<html>
    <head>
        <title>Интернет-магазин</title>
        <style><%@include file="/WEB-INF/View/styles.css"%></style>
    </head>
    <body>
        <h1>
            <a href="home">
                Интернет-магазин
            </a>
        </h1>
        <p>Разрабатываемая система представляет собой интернет-магазин. Имеется каталог товаров, в который можно добавить новые товары.
            Клиенты формируют Заказы на произвольный перечень Товаров. Можно просмотреть список сделанных Заказов, список всех Товаров,
            список Товаров в определенном заказе, а также создать новый Заказ, новый Товар или добавить имеющийся Товар в имеющийся Заказ.
        </p>
        <h2><a href="products">Товары</a></h2>
        <c:if test="${role != Role.GUEST}">
            <h2><a href="orders">Заказы</a></h2>
            <h2><a href="log-out">Выйти</a></h2>
        </c:if>
        <c:if test="${role == Role.GUEST}">
            <h2><a href="log-in">Войти</a></h2>
            <h2><a href="sign-up">Зарегистрироваться</a></h2>
        </c:if>
    </body>
</html>