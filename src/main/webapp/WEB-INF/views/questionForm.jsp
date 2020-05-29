<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz pytania</title>
</head>
<body>
<form:form method="post" modelAttribute="question" action="/formQuestion" enctype="multipart/form-data" >
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
        <c:if test="${not empty question.picture}">
            zmień obrazek
        </c:if>

        <c:if test="${empty question.picture}">
            załącz obrazek
        </c:if>

        <input type="file" name="file" />
    </div>


    <c:forEach items="${answers}" var="answer">
        <tr>
            <td>${answer.text}</td>
            <td>
                <a href="/deleteAnswer/${answer.id}">x</a>
                &nbsp;
                &nbsp;
            </td>
        </tr>
    </c:forEach>


    <c:if test="${not empty question.id}">
        <a href="/formAnswer/${question.id}">dodaj odpowiedz</a>
    </c:if>

        <br>

    <c:if test="${not empty question.picture}">
        <img src="${question.picture}">
    </c:if>

    <input type="submit" />

</form:form>
</body>
</html>
