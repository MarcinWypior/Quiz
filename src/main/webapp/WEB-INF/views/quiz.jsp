<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>odpowiedz na pytanie</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
</head>
<body>

<div class="content">
<form:form method="post" modelAttribute="question" action="/results">
    <form:hidden path="id" />
        <br>
        <c:if test="${not empty question.picture}">
            <img src="<c:url value="${question.picture}"/>" alt="picture for question ${question_id}"/>
        </c:if>

        <br>
    <p>${question.query}</p>

    <c:if test="${howManyProperAnswers==1}">
        tylko jedna odpowiedź jest poprawna
    </c:if>

    <c:if test="${howManyProperAnswers>1}">
        ${howManyProperAnswers} poprawnych odpowiedzi na to pytanie
    </c:if>

    <p class="visibleAnswers">
        <form:checkboxes cssClass="input" delimiter="<br>" path="answerList" items="${answerList}" itemLabel="text"  itemValue="id"/>
    </p>

    <p class="invisible"></p>

    <input type="submit" title="sprawdź odpowiedzi"/>

</form:form>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
