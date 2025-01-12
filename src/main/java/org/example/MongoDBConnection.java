package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "noSQLBazaDanych"; // Nazwa bazy danych

    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }

    public static void main(String[] args) {
        try {
            MongoDatabase database = MongoDBConnection.getDatabase();
            System.out.println("Połączono z bazą danych: " + database.getName());
        } catch (Exception e) {
            System.out.println("Błąd podczas łączenia z bazą danych.");
            e.printStackTrace();
        }
    }
}
