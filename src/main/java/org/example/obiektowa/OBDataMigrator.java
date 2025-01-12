package org.example.obiektowa;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.relacyjna.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OBDataMigrator {

    public static void migrateProducts() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {

            MongoDatabase mongoDatabase = OBMongoDBConnection.getDatabase();
            MongoCollection<Document> productsCollection = mongoDatabase.getCollection("products");

            while (resultSet.next()) {
                Document productDoc = new Document()
                        .append("_id", resultSet.getInt("id"))
                        .append("name", resultSet.getString("name"))
                        .append("details", resultSet.getString("details"))
                        .append("price", resultSet.getDouble("price"))
                        .append("category_id", resultSet.getInt("category_id"))
                        .append("manufacturer_id", resultSet.getInt("manufacturer_id"))
                        .append("sale_id", resultSet.getInt("sale_id"));
                productsCollection.insertOne(productDoc);
            }
            System.out.println("Migracja produktów zakończona.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        migrateProducts();
    }
}
