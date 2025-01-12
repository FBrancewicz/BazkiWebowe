<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entity.Product" %>
<html>
<head>
    <title>Lista produktów z obiektowo-relacyjnej bazy danych</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e8f5e9;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #2e7d32;
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
            text-align: center;
        }

        .product-card h2 {
            font-size: 18px;
            color: #2e7d32;
            margin-bottom: 10px;
        }

        .product-card p {
            margin: 8px 0;
            color: #444;
        }

        .product-card .price {
            font-weight: bold;
            color: #388e3c;
            font-size: 16px;
        }
    </style>
</head>
<body>
<h1>Lista produktów z obiektowo-relacyjnej bazy danych</h1>
<div class="product-container">
    <%
        List<Product> products = (List<Product>) request.getAttribute("productsOBDatabase");

        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
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
    <p style="text-align: center; color: #999;">Brak produktów w bazie danych.</p>
    <%
        }
    %>
</div>
</body>
</html>
