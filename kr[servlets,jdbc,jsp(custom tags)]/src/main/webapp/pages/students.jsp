<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %><html>

<%@ taglib prefix="ct" uri="com.custom" %>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">

</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<table class="table table-striped"  >
    <thead class="thead-inverse">
    <tr>
        <th>#</th>
        <th>Id</th>
        <th>surname</th>
        <th>class</th>
        <th>letter</th>
        <
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${students}" varStatus="index">
        <tr>
            <td>${index.index}</td>
            <td><c:out value="${student.id}"></c:out></td>
            <td><c:out value="${student.surname}"></c:out></td>
            <td><c:out value="${student.klass}"></c:out></td>
            <td><c:out value="${student.letter}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>${sessionScope.count_st}</div>

<ct:table >
    <c:forEach var="student" items="${students}">
        <ct:row align="center"><c:out value="${student}"/></ct:row>
    </c:forEach>
</ct:table>

<%--
<my-tag list="list" columns="5" >ID USEr </my-tag>
--%>
<%--
<form name="loansForm" action="/controller?command=product" method="post">
    <button type="submit" class="btn btn-large btn btn-success btn-lg btn-block">back</button>
</form>--%>
</body>
</html>
