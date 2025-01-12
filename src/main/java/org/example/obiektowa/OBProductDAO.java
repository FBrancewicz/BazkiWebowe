package org.example.obiektowa;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class OBProductDAO {

    public List<OBMongoProduct> getAllProducts() {
        List<OBMongoProduct> products = new ArrayList<>();
        MongoCollection<Document> collection = OBMongoDBConnection.getDatabase().getCollection("products");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Pobieranie głównych pól
                ObjectId id = doc.getObjectId("_id");
                String name = doc.getString("name");
                String details = doc.getString("details");
                double price = doc.getDouble("price");

                OBMongoProduct product = new OBMongoProduct(id, name, details, price);

                // Pobieranie kategorii
                Document categoryDoc = doc.get("category", Document.class);
                if (categoryDoc != null) {
                    OBCategory category = new OBCategory();
                    Object categoryId = categoryDoc.get("_id");
                    if (categoryId instanceof ObjectId) {
                        category.setId((ObjectId) categoryId);
                    } else if (categoryId instanceof String) {
                        category.setId(new ObjectId((String) categoryId));
                    }
                    category.setName(categoryDoc.getString("name"));
                    category.setDescription(categoryDoc.getString("description"));
                    product.setCategory(category);
                }

                // Pobieranie producenta
                Document manufacturerDoc = doc.get("manufacturer", Document.class);
                if (manufacturerDoc != null) {
                    OBManufacturer manufacturer = new OBManufacturer();
                    Object manufacturerId = manufacturerDoc.get("_id");
                    if (manufacturerId instanceof ObjectId) {
                        manufacturer.setId((ObjectId) manufacturerId);
                    } else if (manufacturerId instanceof String) {
                        manufacturer.setId(new ObjectId((String) manufacturerId));
                    }
                    manufacturer.setName(manufacturerDoc.getString("name"));
                    product.setManufacturer(manufacturer);
                }

                // Pobieranie promocji
                Document saleDoc = doc.get("sale", Document.class);
                if (saleDoc != null) {
                    OBSale sale = new OBSale();
                    Object saleId = saleDoc.get("_id");
                    if (saleId instanceof ObjectId) {
                        sale.setId((ObjectId) saleId);
                    } else if (saleId instanceof String) {
                        sale.setId(new ObjectId((String) saleId));
                    }
                    sale.setName(saleDoc.getString("name"));
                    sale.setStartDate(saleDoc.getString("startDate"));
                    sale.setEndDate(saleDoc.getString("endDate"));
                    product.setSale(sale);
                }

                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }


    private Document getRelatedDocument(String collectionName, ObjectId id) {
        MongoCollection<Document> collection = OBMongoDBConnection.getDatabase().getCollection(collectionName);
        return collection.find(new Document("_id", id)).first();
    }
}
