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
        <th>o co pytamy ?</th>
    </tr>
    <c:forEach items="${questions}" var="question">
        <tr>
            <td>${question.category.categoryName}</td>
            <td>${question.query}</td>
            <td>
                <a href="/formQuestion/${question.id}">Edytuj</a>
            </td>
            <td>
                <a href="/deleteQuestion/${question.id}">usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/formQuestion">Dodaj</a>
<br>
<a href="/home">Strona glowna</a>
<br>
<a href="categoryList.jsp">Lista kategorii</a>
<br>
</body>
</html>
