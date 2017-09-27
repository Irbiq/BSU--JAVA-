<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="com.custom" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>table</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</head>

<jsp:include page="/pages/navbar.jsp"></jsp:include>
<%@ page errorPage="error.jsp" %>
<body>
<jsp:useBean id="list" scope="session" class="java.util.ArrayList" ></jsp:useBean>
<%--<div id="content">
    <div id="content-header">
    </div>
    <div>
        <h5>You can write here anything u wanna</h5>

        &lt;%&ndash;list-style-type: circle | disc | square | armenian | decimal | decimal-leading-zero | georgian |
         lower-alpha | lower-greek | lower-latin | lower-roman | upper-alpha | upper-latin | upper-roman | none | inherit&ndash;%&gt;
        <div>
            <ct:lu type="circle">
                <c:forEach var="row" items="${list}" varStatus="index" >
                    <c:if test="${index.index % 3 eq 0}">
                        <ct:li font="Arial" color="#7149ff"> <c:out value="${row}"/></ct:li>
                    </c:if>
                    <c:if test="${index.index % 3 eq 1}">
                        <ct:li font="Times New Roman" color="#544e68"><c:out value="${row}"/></ct:li>
                    </c:if>
                    <c:if test="${index.index % 3 eq 2}">
                        <ct:li font="Comic Sans MS" color="#0340c1"><c:out value="${row}"/></ct:li>
                    </c:if>
                </c:forEach>
            </ct:lu>
        </div>
        <form action="/controller?command=add" method="post">
            <input type="text" name="message" placeholder="Enter message">
            <button type="submit" class="btn btn-primary"> Add</button>
        </form>
        <div>
        </div>
    </div>--%>
</body>
</html>