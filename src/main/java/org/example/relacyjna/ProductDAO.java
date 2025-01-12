package org.example.relacyjna;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
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
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
