<%@ page import="org.example.relacyjna.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista produktow</title>
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
        .button-container {
            text-align: center;
            margin: 20px;
            padding: 10px;
        }
        .button-container form {
            display: inline;
        }
        .button-container button {
            background-color: #ff6f00;
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 5px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        .execution-time {
            text-align: center;
            margin-top: 10px;
            color: green;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Lista produktow</h1>

<div class="button-container">
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="addSingleProduct">
        <button type="submit">Dodaj 1 produkt</button>
    </form>
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="addTenProducts">
        <button type="submit">Dodaj 10 produktow</button>
    </form>
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="addHundredProducts">
        <button type="submit">Dodaj 100 produktow</button>
    </form>
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="addTwoThousandProducts">
        <button type="submit">Dodaj 2000 produktow</button>
    </form>
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="addFourThousandProducts">
        <button type="submit">Dodaj 4000 produktow</button>
    </form>
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="addEightThousandProducts">
        <button type="submit">Dodaj 8000 produktow</button>
    </form>
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="addTwentyThousandProducts">
        <button type="submit">Dodaj 20000 produktow</button>
    </form>
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="deleteAllProducts">
        <button type="submit" style="background-color: red;">Usun produkty</button>
    </form>
    <form action="RelacyjnaBazaDanych" method="POST">
        <input type="hidden" name="action" value="updateCategoryToBooks">
        <button type="submit" style="background-color: #007BFF; color: white;">Update kategorie produktow</button>
    </form>
    <div class="button-container">
        <form action="RelacyjnaBazaDanych" method="POST">
            <input type="hidden" name="action" value="addSingleCategory">
            <button type="submit" style="background-color: #28a745; color: white;">Dodaj 1 kategorie</button>
        </form>
        <form action="RelacyjnaBazaDanych" method="POST">
            <input type="hidden" name="action" value="addHundredCategories">
            <button type="submit" style="background-color: #28a745; color: white;">Dodaj 100 kategorii</button>
        </form>
        <form action="RelacyjnaBazaDanych" method="POST">
            <input type="hidden" name="action" value="addTwoThousandCategories">
            <button type="submit" style="background-color: #007bff; color: white;">Dodaj 2000 kategorii</button>
        </form>
        <form action="RelacyjnaBazaDanych" method="POST">
            <input type="hidden" name="action" value="addFourThousandCategories">
            <button type="submit" style="background-color: #007bff; color: white;">Dodaj 4000 kategorii</button>
        </form>
        <form action="RelacyjnaBazaDanych" method="POST">
            <input type="hidden" name="action" value="addEightThousandCategories">
            <button type="submit" style="background-color: #007bff; color: white;">Dodaj 8000 kategorii</button>
        </form>
        <form action="RelacyjnaBazaDanych" method="POST">
            <input type="hidden" name="action" value="addTwentyThousandCategories">
            <button type="submit" style="background-color: #007bff; color: white;">Dodaj 20000 kategorii</button>
        </form>
        <form action="RelacyjnaBazaDanych" method="POST">
            <input type="hidden" name="action" value="deleteCategories">
            <button type="submit" style="background-color: red; color: white;">Usun Kategorie</button>
        </form>
        <form action="RelacyjnaBazaDanych" method="POST">
            <input type="hidden" name="action" value="updateCategoryDescription">
            <button type="submit" style="background-color: #FFA500; color: white;">Update kategorii</button>
        </form>
    </div>

</div>

<div class="execution-time">
    <%
        String executionTimeMessage = (String) request.getAttribute("executionTimeMessage");
        if (executionTimeMessage != null) {
    %>
    <p><%= executionTimeMessage %></p>
    <%
        }
    %>
</div>

<div class="product-container">
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");


        if (products != null && !products.isEmpty()) {
            int limit = Math.min(5, products.size());
            for (int i = 0; i < limit; i++) {
                Product product = products.get(i);
    %>
    <div class="product-card">
        <h2><%= product.getName() %></h2>
        <p><strong>Description:</strong> <%= product.getDetails() %></p>
        <p class="price"><strong>Price:</strong> <%= product.getPrice() %> PLN</p>
        <p><strong>Category:</strong> <%= product.getCategoryName() != null ? product.getCategoryName() : "None" %></p>
        <p><strong>Manufacturer:</strong> <%= product.getManufacturerName() != null ? product.getManufacturerName() : "None" %></p>
        <% if (product.getSaleName() != null) { %>
        <p><strong>Sale:</strong> <%= product.getSaleName() %></p>
        <p><strong>Sale start:</strong> <%= product.getSaleStartDate() %></p>
        <p><strong>Sale end:</strong> <%= product.getSaleEndDate() %></p>
        <% } %>
    </div>
    <%
        }
    } else {
    %>
    <p style="text-align: center; color: #999;">No products in the database.</p>
    <%
        }
    %>
</div>
</body>
</html>
