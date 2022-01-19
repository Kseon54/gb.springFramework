<%@ page import='java.util.List' %>
<%@ page import='models.Product' %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0">
    <title>Products</title>
</head>
<body>
<table>
    <% for (Product product : (List<Product>)request.getAttribute("products")) { %>
    <tr>
    <td align="center"><%= product.getId() %></td>
    <td align="center"><%= product.getTitle() %></td>
    <td align="right"><%= product.getCost() %></td>
     </tr>
    <% } %>
    </table>
</body>
</html>