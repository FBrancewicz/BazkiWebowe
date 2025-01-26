<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.obiektowa.OBMongoProduct" %>
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
<h1>Lista produktów z obiektowej bazy danych</h1>
<div class="button-container" style="text-align: center; margin: 20px;">
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addSingleProduct">
        <button type="submit" class="button" style="background-color: #4caf50; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 1 produkt
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addHundredProducts">
        <button type="submit" class="button" style="background-color: #2196f3; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 100 produktow
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addTwoThousandProducts">
        <button type="submit" class="button" style="background-color: #ff9800; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 2000 produktow
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addFourThousandProducts">
        <button type="submit" class="button" style="background-color: #f57c00; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 4000 produktow
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addEightThousandProducts">
        <button type="submit" class="button" style="background-color: #e65100; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 8000 produktow
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addTwentyThousandProducts">
        <button type="submit" class="button" style="background-color: #d84315; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 20000 produktow
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="deleteAllProducts">
        <button type="submit" class="button" style="background-color: #f44336; color: white; padding: 10px 20px; border-radius: 5px;">
            Usuń produkty
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="updateProductDescriptions">
        <button type="submit" class="button" style="background-color: #42a5f5; color: white; padding: 10px 20px; border-radius: 5px;">
            Update produkt
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addSingleCategory">
        <button type="submit" class="button" style="background-color: #4caf50; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 1 kategorię
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addHundredCategories">
        <button type="submit" class="button" style="background-color: #2196f3; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 100 kategorii
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addTwoThousandCategories">
        <button type="submit" class="button" style="background-color: #ff9800; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 2000 kategorii
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addFourThousandCategories">
        <button type="submit" class="button" style="background-color: #f57c00; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 4000 kategorii
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addEightThousandCategories">
        <button type="submit" class="button" style="background-color: #e65100; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 8000 kategorii
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="addTwentyThousandCategories">
        <button type="submit" class="button" style="background-color: #d84315; color: white; padding: 10px 20px; border-radius: 5px;">
            Dodaj 20000 kategorii
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="deleteAllCategoriesExcept">
        <button type="submit" class="button" style="background-color: #d32f2f; color: white; padding: 10px 20px; border-radius: 5px;">
            Usuń kategorie
        </button>
    </form>
    <form action="ObiektowaBazaDanych" method="POST" style="display: inline-block; margin: 5px;">
        <input type="hidden" name="action" value="updateCategoryDescriptions">
        <button type="submit" class="button" style="background-color: #ffa726; color: white; padding: 10px 20px; border-radius: 5px;">
            Update kategorii
        </button>
    </form>
</div>

<div class="execution-time" style="display: flex; justify-content: center; align-items: center; margin: 20px;">
    <%
        String executionTimeMessage = (String) request.getAttribute("executionTimeMessage");
        if (executionTimeMessage != null) {
    %>
    <p style="font-size: 18px; font-weight: bold; color: #4caf50; text-align: center;">
        <%= executionTimeMessage %>
    </p>
    <%
        }
    %>
</div>

<div class="product-container">
    <%
        List<OBMongoProduct> products = (List<OBMongoProduct>) request.getAttribute("ormProducts");

        if (products != null && !products.isEmpty()) {
            for (OBMongoProduct product : products) {
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
