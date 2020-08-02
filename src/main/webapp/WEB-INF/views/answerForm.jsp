<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html ;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>odpowiedz na pytanie</title>
        <link rel="stylesheet" href="<c:url value="../../resources/css/answerForm.css"/>"/>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    </head>
    <body>
        <div class="wrapper">
        <form:form cssClass="form" method="post" modelAttribute="answer" action="/formAnswer/${question.id}">
            Dodaj nową odpowiedz na pytanie ${question.query}
            <form:hidden path="id"/>
            <div>
                <label><form:input cssClass="inputText" path="text" placeholder="nowa odpowiedź"/></label>
                <form:errors path="text"/>
                <br>
                <label>prawdziwa odpowiedz
                    <input type="checkbox"  name="proper" value="1"/>
                </label>
            </div>
            <input name="dodaj" type="submit" value="zapisz odpowiedź"/>
        </form:form>
        </div>

        <script src="<c:url value="../../resources/js/answerForm.js"/>"></script>

    </body>
</html>
