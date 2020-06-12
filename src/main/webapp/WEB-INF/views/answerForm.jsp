<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz odpowiedzi na pytanie

<%--        <c:if test="${not empty answer.question.query}">--%>
<%--        ${answer.question.query}--%>
<%--    </c:if>${answer.question.query}--%>

    </title>
</head>
<body>
Dodaj nową odpowiedz na pytanie ${question.query}
<form:form method="post" modelAttribute="answer" action="/formAnswer/${question.id}">
    <form:hidden path="id" />
    <div>
        <label> <form:input path="text" placeholder="nowa odpowiedź"/></label>
        <form:errors path="text" />
        <label>prawdziwa odpowiedz<input type="checkbox" name="proper" value="1"/></label>
    </div>
    <input name="dodaj" type="submit"/>
</form:form>
</body>
</html>
