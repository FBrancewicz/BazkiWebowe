<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.noSQL.noSQLProduct" %>
<html>
<head>
    <title>Lista produktów z bazy noSQL</title>
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
            text-align: center;
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

        .product-card .highlight {
            font-weight: bold;
            color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Lista produktów z bazy noSQL</h1>
<div class="product-container">
    <%
        List<noSQLProduct> products = (List<noSQLProduct>) request.getAttribute("mongoProducts");

        if (products != null && !products.isEmpty()) {
            for (noSQLProduct product : products) {
    %>
    <div class="product-card">
        <h2><%= product.getName() %></h2>
        <p><strong>Opis:</strong> <%= product.getDetails() %></p>
        <p><strong>Cena:</strong> <span class="price"><%= product.getPrice() %> PLN</span></p>
        <p><strong>Kategoria:</strong> <span class="highlight"><%= product.getCategory() != null ? product.getCategory().getName() : "Brak" %></span></p>
        <p><strong>Opis kategorii:</strong> <%= product.getCategory() != null ? product.getCategory().getDescription() : "Brak" %></p>
        <p><strong>Producent:</strong> <%= product.getManufacturer() != null ? product.getManufacturer().getName() : "Brak" %></p>
        <p><strong>Promocja:</strong> <%= product.getSale() != null ? product.getSale().getName() : "Brak" %></p>
        <p><strong>Data rozpoczęcia promocji:</strong> <%= product.getSale() != null ? product.getSale().getStartDate() : "Brak" %></p>
        <p><strong>Data zakończenia promocji:</strong> <%= product.getSale() != null ? product.getSale().getEndDate() : "Brak" %></p>
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
