package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.entity.Product;

import java.util.ArrayList;
import java.util.List;


public class OBProductDAO {
    public List<MongoProduct> getAllProducts() {
        List<MongoProduct> products = new ArrayList<>();
        try {
            MongoCollection<Document> collection = OBMongoDBConnection.getDatabase().getCollection("products");
            MongoCursor<Document> cursor = collection.find().iterator();

            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Odczyt głównych pól
                String name = doc.getString("name");
                String details = doc.getString("details");
                double price = doc.getDouble("price");

                // Obsługa referencji jako String (jeśli są zapisane jako ciągi znaków)
                String categoryId = doc.get("category", Document.class).getString("$id");
                String manufacturerId = doc.get("manufacturer", Document.class).getString("$id");
                String saleId = doc.get("sale", Document.class).getString("$id");

                // Tworzenie obiektu MongoProduct
                MongoProduct product = new MongoProduct(
                        doc.getObjectId("_id"), // Używaj ObjectId dla samego ID dokumentu
                        name,
                        details,
                        price,
                        categoryId != null ? new ObjectId(categoryId) : null,
                        manufacturerId != null ? new ObjectId(manufacturerId) : null,
                        saleId != null ? new ObjectId(saleId) : null
                );

                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }



    public Document getRelatedDocument(String collectionName, ObjectId id) {
        MongoCollection<Document> collection = OBMongoDBConnection.getDatabase().getCollection(collectionName);
        return collection.find(new Document("_id", id)).first();
    }
}