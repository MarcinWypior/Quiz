<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz rejestracji nowego urzytkownika

    </title>
</head>
<body>
<form:form method="post" modelAttribute="user" action="/registration">
    <form:hidden path="id" />
    <div>
        <label> <form:input path="username" placeholder="nazwa urzytkownika"/></label>
        <form:errors path="username" />
        <label> <form:input path="email" placeholder="em@il"/></label>
        <form:errors path="email" />
        <label> <form:input path="password" placeholder="haslo"/></label>
        <form:errors path="password"/>
<%--        <label>prawdziwa odpowiedz<form:checkbox path="isTrue"/></label>--%>
<%--        nie wiem czemu nie widzi atrybutu boolean --%>
    </div>
    <input name="dodaj" type="submit"/>
</form:form>
</body>
</html>
