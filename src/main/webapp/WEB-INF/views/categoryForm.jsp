<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz kategorii</title>
</head>
<body>
Dodaj nową kategorie
<form:form method="post" modelAttribute="category" action="/formCategory">
    <form:hidden path="id" />
    <div>
        <label> <form:input path="categoryName" /></label>
        <form:errors path="categoryName" />
    </div>
    <input type="submit"/>
</form:form>
</body>
</html>
