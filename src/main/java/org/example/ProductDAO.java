package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {



    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT Product_ID, Name, Details, Price FROM product";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("Product_ID"),  // Poprawna nazwa kolumny
                        resultSet.getString("Name"),
                        resultSet.getString("Details"),
                        resultSet.getDouble("Price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
