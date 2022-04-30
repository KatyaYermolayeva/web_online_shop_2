<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Model.Role" %>
<html>
    <head>
        <title>Ошибка</title>
        <style><%@include file="/WEB-INF/View/styles.css"%></style>
    </head>
    <body>
        <h1>
            <a href="home">
                Интернет-магазин
            </a>
        </h1>
        <h2>
            Возникла ошибка:
        </h2>
        <p>
            <c:out value="${error}" />
        </p>
    </body>
</html>