<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.relacyjna.Product" %>
<html>
<head>
    <title>Lista produktów z relacyjnej bazy danych</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fff8e1;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #ff6f00;
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
            color: #ff6f00;
            margin-bottom: 10px;
        }

        .product-card p {
            margin: 8px 0;
            color: #555;
        }

        .product-card .price {
            font-weight: bold;
            color: #e65100;
            font-size: 16px;
        }
    </style>
</head>
<body>
<h1>Lista produktów z relacyjnej bazy danych</h1>
<div class="product-container">
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");

        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
    %>
    <div class="product-card">
        <h2><%= product.getName() %></h2>
        <p><strong>Opis:</strong> <%= product.getDetails() %></p>
        <p class="price"><strong>Cena:</strong> <%= product.getPrice() %> PLN</p>
        <p><strong>Kategoria:</strong> <%= product.getCategoryName() != null ? product.getCategoryName() : "Brak" %></p>
        <p><strong>Producent:</strong> <%= product.getManufacturerName() != null ? product.getManufacturerName() : "Brak" %></p>

        <% if (product.getSaleName() != null) { %>
        <p><strong>Promocja:</strong> <%= product.getSaleName() %></p>
        <p><strong>Data rozpoczęcia promocji:</strong> <%= product.getSaleStartDate() != null ? product.getSaleStartDate() : "Brak" %></p>
        <p><strong>Data zakończenia promocji:</strong> <%= product.getSaleEndDate() != null ? product.getSaleEndDate() : "Brak" %></p>
        <% } %>
    </div>

    <%
        }
    } else {
    %>
    <p style="text-align: center; color: #999;">Brak przedmiotów w bazie danych.</p>
    <%
        }
    %>
</div>
</body>
</html>
