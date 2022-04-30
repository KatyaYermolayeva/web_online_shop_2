<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Role" %>
<html>
    <head>
        <title>Вход</title>
        <style><%@include file="/WEB-INF/View/styles.css"%></style>
    </head>
    <body>
        <h1>
            <a href="home">
                Интернет-магазин
            </a>
        </h1>
          <form action = "log-in" method = "POST" autocomplete="off">
            <label>Имя пользователя:
            <input type="text" name="username" placeholder="Имя пользователя" required="true" errormessage="Укажите имя пользователя"></label>
            <label>Пароль:
            <input type="password" name="password" placeholder="Пароль" required="true" errormessage="Укажите пароль"></label>
            <label>Войти: <input type="submit" value="Войти"></label>
          </form>
    </body>
</html>