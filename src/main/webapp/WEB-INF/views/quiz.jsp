<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>odpowiedz na pytanie</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<%--@elvariable id="question" type="coderslab.quiz.entities.Question"--%>
<form:form method="post" modelAttribute="question" action="/results">
    <form:hidden path="id" />
    <div>
        <c:if test="${not empty question.picture}">
        </c:if>

        <c:if test="${empty question.picture}">
<%--            src/main/resources/static/uploadedFiles/2020-06-12 21:11:44.903894.--%>
            <img src="<c:url value="${question.picture}"/>
        </c:if>
    </div>
    ${question.query}
    <br>

    <form:select path="answerList" items="${answerList}" itemLabel="text"  itemValue="id"/>
    <input type="submit" title="sprawdź odpowiedź"/>

</form:form>
</body>
</html>
