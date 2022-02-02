<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<form action="<c:url value="/product/addProduct"/>">
    <%--    <p>Id: <input type="number" th:field="*{id}" value="0"/></p>--%>
    <p>Title: <input type="text" name="title"/></p>
    <p>Cost: <input type="number" name="cost"/></p>
    <p><input type="submit" value="Add"/> <input type="reset" value="Reset"/></p>
</form>
</body>
</html>
