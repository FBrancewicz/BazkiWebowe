<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.orm.ProductORM" %>
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

        .product-card .highlight {
            font-weight: bold;
            color: #1565c0;
        }
    </style>
</head>
<body>
<h1>Lista produktów z obiektowo-relacyjnej bazy danych</h1>
<div class="product-container">
    <%
        List<ProductORM> products = (List<ProductORM>) request.getAttribute("productsOBDatabase");

        if (products != null && !products.isEmpty()) {
            for (ProductORM product : products) {
    %>
    <div class="product-card">
        <h2><%= product.getName() %></h2>
        <p><strong>Opis:</strong> <%= product.getDetails() %></p>
        <p class="price"><strong>Cena:</strong> <%= product.getPrice() %> PLN</p>
        <p><strong>Kategoria:</strong>
            <span class="highlight"><%= product.getCategory() != null ? product.getCategory().getName() : "Brak" %></span>
        </p>
        <p><strong>Opis kategorii:</strong>
            <%= product.getCategory() != null ? product.getCategory().getDescription() : "Brak" %>
        </p>
        <p><strong>Producent:</strong>
            <%= product.getManufacturer() != null ? product.getManufacturer().getName() : "Brak" %>
        </p>
        <p><strong>Promocja:</strong>
            <%= product.getSaleORM() != null ? product.getSaleORM().getName() : "Brak" %>
        </p>
        <p><strong>Data rozpoczęcia promocji:</strong>
            <%= product.getSaleORM() != null ? product.getSaleORM().getStartDate() : "Brak" %>
        </p>
        <p><strong>Data zakończenia promocji:</strong>
            <%= product.getSaleORM() != null ? product.getSaleORM().getEndDate() : "Brak" %>
        </p>
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
