<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Cost</th>
    </tr>

    <%--@elvariable id="products" type="java.util.List"--%>
    <c:forEach items="${products}" var="product">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.title}"/></td>
            <td><c:out value="${product.cost}"/></td>
        </tr>
    </c:forEach>

</table>

<a href="<c:url value="/product"/>">Add product</a>
</body>
</html>
