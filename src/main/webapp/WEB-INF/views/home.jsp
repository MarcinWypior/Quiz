<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>odpowiedz na pytanie</title>
  <link rel="stylesheet" href="<c:url value="../../resources/css/home.css"/>"/>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<%--  <meta http-equiv="X-UA-Compatible" content="ie=edge"/>--%>
</head>
<body>
<table class="menu">


  <th class="dropdown-menu">
    <a href="/selectCategories">
      graj
    </a>
  </th>



  <th class="dropdown-menu">
    <a href="/formQuestion">
      dodaj swoje pytanie
    </a>
  </th>

  <th class="dropdown-menu">
    <a href="/formCategory">
      dodaj kategorie
    </a>
  </th>

  <th class="dropdown-menu">
    <a href="/questionList">
      dodaj swoje pytanie
    </a>
  </th>



</table>

<div class="container">
  <h1 class="ml4">
    <span class="letters letters-1">Quizz!</span>
    <span class="letters letters-2">czy?</span>
    <span class="letters letters-3">Wiesz?</span>
  </h1>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>
<script src="<c:url value="resources/js/home.js"/>"></script>

</body>
</html>
