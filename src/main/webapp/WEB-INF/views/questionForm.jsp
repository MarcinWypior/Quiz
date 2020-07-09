<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<html>
<head>
    <title>Formularz pytania</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>"/>
</head>
<body>
<div class="content">
<form:form method="post" modelAttribute="question" action="/formQuestion" enctype="multipart/form-data" >
    <form:hidden path="id" />
    <input type="hidden" name="proposition" value="false" />
    <div>
        <label class="query">
            o co bedziesz pytać gracza ?
            <br>
            <form:textarea cssClass="query" path="query" />
        </label>
        <form:errors path="query" />

        <label>
            <br>
            do jakiej kategori bedzie należeć pytanie
            <form:select cssClass="query" path="category.id" items="${category}" itemLabel="categoryName" itemValue="id" />
        </label>
        <br>
        <form:errors path="category" />

        <c:if test="${not empty question.picture}">
            zmień obrazek
        </c:if>

        <c:if test="${empty question.picture}">
            załącz obrazek
        </c:if>

        <input class="query" type="file" name="file" />
    </div>

    <br>
    <c:if test="${not empty question.picture}">
        <img class="picture" src="<c:url value="../../${question.picture}"/>" alt="picture for question ${question_id}"/>
    </c:if>

    <br>
    <c:forEach items="${answers}" var="answer">
        <tr>
            <td>
                <c:if test="${answer.proper==true}">
                <b>
                    </c:if>

                        ${answer.text}

                    <c:if test="${answer.proper==true}">
                </b>
                </c:if>


            </td>
            <td>
                <a href="/deleteAnswer/${answer.id}">x</a>
                &nbsp;&nbsp;&nbsp;
            </td>
        </tr>

    </c:forEach>

    <br>
    <c:if test="${not empty question.id}">
        <a href="/formAnswer/${question.id}">dodaj odpowiedz</a>
    </c:if>



    <div>
    <input type="submit" value="zapisz" />
    </div>

</form:form>
</div>
<br>
<a href="/questionList">Lista pytań</a>
<br>
<a href="/categoryList">Lista kategorii</a>
<br>
</body>
</html>
