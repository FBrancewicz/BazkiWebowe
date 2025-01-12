package org.example.noSQL;

import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class noSQLProductDAO {
    private final MongoCollection<Document> productCollection;

    public noSQLProductDAO() {
        productCollection = noSQLDBConnection.getDatabase().getCollection("products");
    }

    public List<noSQLProduct> getAllProducts() {
        List<noSQLProduct> products = new ArrayList<>();

        try (MongoCursor<Document> cursor = productCollection.find().iterator()) {
            System.out.println("DEBUG: Rozpoczęto pobieranie produktów z kolekcji 'products'.");

            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println("DEBUG: Wczytano dokument produktu: " + doc);


                ObjectId id = doc.getObjectId("_id");
                String name = doc.getString("name");
                String details = doc.getString("details");
                double price = doc.getDouble("price");

                System.out.println("DEBUG: ID produktu: " + id);
                System.out.println("DEBUG: Nazwa produktu: " + name);
                System.out.println("DEBUG: Szczegóły produktu: " + details);
                System.out.println("DEBUG: Cena produktu: " + price);

                noSQLProduct product = new noSQLProduct(id, name, details, price);


                if (doc.get("category") instanceof DBRef categoryRef) {
                    System.out.println("DEBUG: Znaleziono referencję kategorii: " + categoryRef);
                    Document categoryDoc = getReferencedDocument(categoryRef);
                    if (categoryDoc != null) {
                        System.out.println("DEBUG: Dokument kategorii: " + categoryDoc.toJson());
                        noSQLCategory category = new noSQLCategory();
                        category.setId((ObjectId) categoryDoc.get("_id"));
                        category.setName(categoryDoc.getString("name"));
                        category.setDescription(categoryDoc.getString("description"));
                        product.setCategory(category);
                    } else {
                        System.out.println("WARN: Nie znaleziono dokumentu dla kategorii: " + categoryRef);
                    }
                }


                if (doc.get("manufacturer") instanceof DBRef manufacturerRef) {
                    System.out.println("DEBUG: Znaleziono referencję producenta: " + manufacturerRef);
                    Document manufacturerDoc = getReferencedDocument(manufacturerRef);
                    if (manufacturerDoc != null) {
                        System.out.println("DEBUG: Dokument producenta: " + manufacturerDoc.toJson());
                        noSQLManufacturer manufacturer = new noSQLManufacturer();
                        manufacturer.setId((ObjectId) manufacturerDoc.get("_id"));
                        manufacturer.setName(manufacturerDoc.getString("name"));
                        product.setManufacturer(manufacturer);
                    } else {
                        System.out.println("WARN: Nie znaleziono dokumentu dla producenta: " + manufacturerRef);
                    }
                }


                if (doc.get("sale") instanceof DBRef saleRef) {
                    System.out.println("DEBUG: Znaleziono referencję promocji: " + saleRef);
                    Document saleDoc = getReferencedDocument(saleRef);
                    if (saleDoc != null) {
                        System.out.println("DEBUG: Dokument promocji: " + saleDoc.toJson());
                        noSQLSale sale = new noSQLSale(
                                (ObjectId) saleDoc.get("_id"),
                                saleDoc.getString("name"),
                                saleDoc.getString("startDate"),
                                saleDoc.getString("endDate")
                        );
                        product.setSale(sale);
                    } else {
                        System.out.println("WARN: Nie znaleziono dokumentu dla promocji: " + saleRef);
                    }
                }

                products.add(product);
            }
            System.out.println("DEBUG: Zakończono iterację po produktach. Łączna liczba produktów: " + products.size());
        }
        return products;
    }


    private Document getReferencedDocument(DBRef ref) {
        if (ref == null) {
            return null;
        }
        try {
            MongoCollection<Document> collection = noSQLDBConnection.getDatabase()
                    .getCollection(ref.getCollectionName());
            return collection.find(new Document("_id", ref.getId())).first();
        } catch (Exception e) {
            System.out.println("ERROR: Unable to fetch referenced document for DBRef: " + ref);
            e.printStackTrace();
            return null;
        }
    }
}
