package org.example.noSQL;

import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class noSQLProductSeeder {
    private static final MongoCollection<Document> productCollection = noSQLDBConnection.getDatabase().getCollection("products");

    public static void addProducts(int count) {
        List<Document> products = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Document product = new Document("_id", new ObjectId())
                    .append("name", "noSQLBazaDanychProdukt " + i)
                    .append("details", "Szczegóły produktu " + i)
                    .append("price", 100.0 + i)
                    .append("category", new DBRef("categories", new ObjectId("674396ccec35e056394e90a9")))
                    .append("manufacturer", new DBRef("manufacturers", new ObjectId("674396ccec35e056394e90aa")))
                    .append("sale", new DBRef("sales", new ObjectId("674396ccec35e056394e90ab")));
            products.add(product);
        }

        productCollection.insertMany(products);
    }

    public void deleteAllProducts() {
        try {
            long deletedCount = productCollection.deleteMany(new Document()).getDeletedCount();

        } catch (Exception e) {
            System.err.println("ERROR: Wystąpił problem podczas usuwania produktów.");
            e.printStackTrace();
        }
    }


    public void updateCategoryToSport() {
        try {
            MongoCollection<Document> categoryCollection = noSQLDBConnection.getDatabase().getCollection("categories");

            Document sportCategory = categoryCollection.find(Filters.eq("name", "Sport")).first();

            if (sportCategory != null) {
                DBRef sportCategoryRef = new DBRef("categories", sportCategory.getObjectId("_id"));

                long updatedCount = productCollection.updateMany(
                        new Document(),
                        Updates.set("category", sportCategoryRef)
                ).getModifiedCount();

            } else {
            }
        } catch (Exception e) {
            System.err.println("ERROR: Wystąpił problem podczas aktualizacji kategorii produktów.");
            e.printStackTrace();
        }
    }


}
