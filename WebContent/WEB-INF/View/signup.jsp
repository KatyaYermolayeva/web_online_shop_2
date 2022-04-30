<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Role" %>
<html>
    <head>
        <title>Регистрация</title>
        <style><%@include file="/WEB-INF/View/styles.css"%></style>
    </head>
    <body>
        <h1>
            <a href="home">
                Интернет-магазин
            </a>
        </h1>
        <form action = "sign-up" method = "POST" autocomplete="off">
            <label>Имя пользователя:
                <input type="text" name="username" placeholder="Имя пользователя" required="true" errormessage="Укажите имя пользователя"></label>
            <label>Email:
                <input type="email" name="email" placeholder="Email" required="true" errormessage="Укажите email"></label>
            <label>Пароль:
                <input type="password" name="password" placeholder="Пароль" minlength="8" required="true" errormessage="Укажите пароль"></label>
            <label>Зарегистрироваться: <input type="submit" value="Зарегистрироваться"></label>
        </form>
    </body>
</html>