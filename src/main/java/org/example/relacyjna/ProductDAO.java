package org.example.relacyjna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ProductDAO {

    public List<Product> getAllProducts() {
        String sql = """
        SELECT 
            p.Product_ID, p.Name, p.Details, p.Price,
            c.Name AS CategoryName, c.Description AS CategoryDescription,
            m.Name AS ManufacturerName,
            s.Name AS SaleName, s.Start_date AS SaleStartDate, s.End_date AS SaleEndDate
        FROM product p
        LEFT JOIN category c ON p.Category_ID = c.Category_ID
        LEFT JOIN manufacturer m ON p.Manufacturer_ID = m.Manufacturer_ID
        LEFT JOIN sale s ON p.Sale_ID = s.Sale_ID
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            return mapResultSetToProducts(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Product> mapResultSetToProducts(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product(
                    resultSet.getString("Product_ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Details"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("CategoryName"),
                    resultSet.getString("CategoryDescription"),
                    resultSet.getString("ManufacturerName"),
                    resultSet.getString("SaleName"),
                    resultSet.getString("SaleStartDate"),
                    resultSet.getString("SaleEndDate")
            );
            products.add(product);
        }
        return products;
    }

    public void deleteAllProducts() {
        String sql = "DELETE FROM product";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("DEBUG: Usunięto wszystkie produkty. Liczba usuniętych wierszy: " + rowsDeleted);

        } catch (SQLException e) {
            System.out.println("ERROR: Problem podczas usuwania wszystkich produktów");
            e.printStackTrace();
        }
    }


    public void clearProducts() {
        String disableForeignKeyCheck = "SET FOREIGN_KEY_CHECKS = 0";
        String enableForeignKeyCheck = "SET FOREIGN_KEY_CHECKS = 1";
        String deleteProducts = "DELETE FROM product";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Wyłącz klucze obce
            statement.execute(disableForeignKeyCheck);

            // Usuń produkty
            int rowsDeleted = statement.executeUpdate(deleteProducts);
            System.out.println("DEBUG: Usunięto produkty. Liczba usuniętych wierszy: " + rowsDeleted);

            // Włącz klucze obce
            statement.execute(enableForeignKeyCheck);

        } catch (SQLException e) {
            System.out.println("ERROR: Problem podczas usuwania produktów");
            e.printStackTrace();
        }
    }



    public void updateCategoryForAllProductsToBooks() {
        String sql = "UPDATE product SET Category_ID = 2 WHERE Category_ID != 2"; // Zmień tylko produkty z inną kategorią

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("DEBUG: Updated Category_ID to 2 (Books) for " + rowsUpdated + " products.");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not update Category_ID to 2.");
            e.printStackTrace();
        }
    }



    public void addProducts(Connection connection, int count) throws SQLException {
        String sql = """
        INSERT INTO product (Name, Details, Price, Category_ID, Manufacturer_ID, Sale_ID) 
        VALUES (?, ?, ?, ?, ?, ?)
        """;

        String productNameBase = "Produkt";
        String productDetails = "Opis produktu";
        double productPrice = 199.99;
        int categoryId = 1;
        int manufacturerId = 1;
        int saleId = 1;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
                statement.setString(1, productNameBase + " " + i);
                statement.setString(2, productDetails);
                statement.setDouble(3, productPrice + i);
                statement.setInt(4, categoryId);
                statement.setInt(5, manufacturerId);
                statement.setInt(6, saleId);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }


    public List<Product> getProductsByPage(int page, int recordsPerPage) {
        String sql = """
        SELECT 
            p.Product_ID, p.Name, p.Details, p.Price,
            c.Name AS CategoryName, c.Description AS CategoryDescription,
            m.Name AS ManufacturerName,
            s.Name AS SaleName, s.Start_date AS SaleStartDate, s.End_date AS SaleEndDate
        FROM product p
        LEFT JOIN category c ON p.Category_ID = c.Category_ID
        LEFT JOIN manufacturer m ON p.Manufacturer_ID = m.Manufacturer_ID
        LEFT JOIN sale s ON p.Sale_ID = s.Sale_ID
        LIMIT ? OFFSET ?
    """;

        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * recordsPerPage;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, recordsPerPage);
            preparedStatement.setInt(2, offset);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                products = mapResultSetToProducts(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // Liczy całkowitą liczbę produktów
    public int getTotalProductsCount() {
        String sql = "SELECT COUNT(*) AS total FROM product";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void addCategories(Connection connection, int count) throws SQLException {
        String sql = """
    INSERT INTO category (Name, Description) 
    VALUES (?, ?)
    """;

        String categoryNameBase = "Category";
        String categoryDescription = "Default category description";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
                statement.setString(1, categoryNameBase + " " + i);
                statement.setString(2, categoryDescription);
                statement.addBatch();
            }
            int[] rowsInserted = statement.executeBatch();
            System.out.println("DEBUG: Added " + rowsInserted.length + " categories to the database.");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not add categories.");
            e.printStackTrace();
        }
    }


    public void deleteCategoriesExcludingIds(Connection connection, List<Integer> excludedIds) {
        String placeholders = excludedIds.stream()
                .map(id -> "?")
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        String sql = "DELETE FROM category WHERE Category_ID NOT IN (" + placeholders + ")";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < excludedIds.size(); i++) {
                preparedStatement.setInt(i + 1, excludedIds.get(i));
            }

            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("DEBUG: Deleted categories excluding IDs " + excludedIds + ". Rows affected: " + rowsDeleted);
        } catch (SQLException e) {
            System.out.println("ERROR: Could not delete categories.");
            e.printStackTrace();
        }
    }

    public void updateCategoryDescriptionsExcludingIds(String newDescription, List<Integer> excludedIds) {
        String sql = """
        UPDATE category
        SET Description = ?
        WHERE Category_ID NOT IN (""" + excludedIds.stream().map(id -> "?").collect(Collectors.joining(", ")) + ")";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newDescription);

            // Ustawienie parametrów dla ID do pominięcia
            for (int i = 0; i < excludedIds.size(); i++) {
                preparedStatement.setInt(i + 2, excludedIds.get(i));
            }

            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("DEBUG: Updated description for " + rowsUpdated + " categories.");
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to update category descriptions.");
            e.printStackTrace();
        }
    }


}
