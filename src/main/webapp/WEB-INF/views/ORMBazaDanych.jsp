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

<div class="button-container" style="text-align: center; margin: 20px;">
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addSingleProduct">
        <button type="submit" class="button" style="background-color: #4caf50; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 1 produkt
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addHundredProducts">
        <button type="submit" class="button" style="background-color: #ff9800; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 100 produktow
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addTwoThousandProducts">
        <button type="submit" class="button" style="background-color: #795548; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 2000 produktow
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addFourThousandProducts">
        <button type="submit" class="button" style="background-color: #673ab7; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 4000 produktow
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addEightThousandProducts">
        <button type="submit" class="button" style="background-color: #009688; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 8000 produktow
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addTwentyThousandProducts">
        <button type="submit" class="button" style="background-color: #3f51b5; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 20000 produktow
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="deleteAllProducts">
        <button type="submit" class="button" style="background-color: #f44336; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Usun produkty
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="updateCategoryTo2">
        <button type="submit" class="button" style="background-color: #ff5722; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Update produkty (kategoria)
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addSingleCategory">
        <button type="submit" class="button" style="background-color: #ff5722; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 1 kategorie
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addHundredCategories">
        <button type="submit" class="button" style="background-color: #ff5722; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 100 kategorii
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addTwoThousandCategories">
        <button type="submit" class="button" style="background-color: #ff5722; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 2000 kategorii
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addFourThousandCategories">
        <button type="submit" class="button" style="background-color: #ff5722; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 4000 kategorii
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addEightThousandCategories">
        <button type="submit" class="button" style="background-color: #ff5722; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 8000 kategorii
        </button>
    </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addTwentyThousandCategories">
        <button type="submit" class="button" style="background-color: #ff5722; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Dodaj 20000 kategorii
        </button>
    </form>
        <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
            <input type="hidden" name="action" value="deleteAllCategoriesExcept">
            <button type="submit" class="button" style="background-color: #d32f2f; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
                Usun Kategorie
            </button>
        </form>
    <form action="ORMBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="updateCategoryNames">
        <button type="submit" class="button" style="background-color: #ff9800; color: white; padding: 10px 20px; border-radius: 5px; font-size: 16px; cursor: pointer;">
            Update Kategoria Nazwa
        </button>
    </form>
</div>



<div class="execution-time" style="display: flex; justify-content: center; align-items: center; margin: 20px;">
    <%
        String executionTimeMessage = (String) request.getAttribute("executionTimeMessage");
        if (executionTimeMessage != null) {
    %>
    <p style="font-size: 18px;
              font-weight: bold;
              color: #4caf50;
              padding: 15px 20px;
              text-align: center;">
        <%= executionTimeMessage %>
    </p>
    <%
        }
    %>
</div>


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
