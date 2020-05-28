<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz pytania</title>
</head>
<body>
<form:form method="post" modelAttribute="question" action="formQuestion">
    <form:hidden path="id" />
    <input type="hidden" name="proposition" value="false" />
    <div>
        <label>
            o co bedziesz pytać gracza ?
            <br>
            <form:input path="query" />
        </label>
        <form:errors path="query" />
    </div>

    <div>
        <label>
            do jakiej kategori bedzie należeć pytanie
            <form:select path="category.id" items="${category}" itemLabel="categoryName" itemValue="id" />
        </label>
        <form:errors path="category" />
    </div>
<div>
    zaladuj obrazek
    <input name="img1" type="file" />
</div>
    <input type="submit" />
</form:form>
</body>
</html>
