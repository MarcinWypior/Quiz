<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz pytania</title>
</head>
<body>
<%--@elvariable id="question" type="coderslab.quiz.entities.Question"--%>
<form:form method="post" modelAttribute="question" action="/formQuestion" enctype="multipart/form-data" >
    <div>
        <c:if test="${not empty question.picture}">
        </c:if>

        <c:if test="${empty question.picture}">
            załącz obrazek
        </c:if>
    </div>
    ${question.query}

    <form:select path="answerList" items="${answers}"  />
    <input type="submit"/>

</form:form>
</body>
</html>