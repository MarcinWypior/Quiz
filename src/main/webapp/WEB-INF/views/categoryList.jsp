<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marcin
  Date: 27.05.2020
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lista kategorii</title>
</head>
<body>
<table>
    <tr>
        <th>Nazwa kategorii</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${category}" var="cat">
        <tr>
            <td>${cat.categoryName}</td>
            <td>
                <a href="/formCategory/${cat.id}">Edytuj</a>
            </td>
            <td>
                <a href="/deleteCategory/${cat.id}">usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/formCategory">Dodaj</a>
<br>

<a href="questionList">Lista pytań</a>
<br>
<a href="/home">strona glowna</a>
<br>
</body>
</html>
