<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>wybierz kategorie </title>
</head>
<body>

<form:form method="post" modelAttribute="selectedCategories" action="/selectCategories">
<form:select path="categories" items="${categories}" itemLabel="categoryName" itemValue="id"/>
    <br>
    <input type="submit"/>
</form:form>
<br>
<a href="/questionList">Lista pytań</a>
<br>
<a href="/categoryList">Lista kategorii</a>
<br>
</body>
</html>
