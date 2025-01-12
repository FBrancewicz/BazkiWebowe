<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.MongoProduct" %>
<html>
<head>
    <title>Lista produktów z obiektowej bazy danych</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
            padding: 20px;
        }

        .product-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            padding: 20px;
        }

        .product-card {
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 15px;
            width: 300px;
            text-align: left;
        }

        .product-card h2 {
            font-size: 18px;
            color: #555;
            margin-bottom: 10px;
        }

        .product-card p {
            margin: 8px 0;
            color: #666;
        }

        .product-card .price {
            font-weight: bold;
            color: #28a745;
            font-size: 16px;
        }
    </style>
</head>
<body>
<h1>Lista produktów z obiektowej bazy danych</h1>
<div class="product-container">
    <%
        List<MongoProduct> products = (List<MongoProduct>) request.getAttribute("ormProducts");

        if (products != null && !products.isEmpty()) {
            for (MongoProduct product : products) {
    %>
    <div class="product-card">
        <h2><%= product.getName() %></h2>
        <p><strong>Opis:</strong> <%= product.getDetails() %></p>
        <p class="price"><strong>Cena:</strong> <%= product.getPrice() %> PLN</p>
    </div>
    <%
        }
    } else {
    %>
    <p style="text-align: center; color: #999;">Brak produktów w bazie MongoDB.</p>
    <%
        }
    %>
</div>
</body>
</html>
