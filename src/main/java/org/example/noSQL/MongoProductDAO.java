package org.example.noSQL;

import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoProductDAO {
    private MongoCollection<Document> productCollection;

    public MongoProductDAO() {
        productCollection = MongoDBConnection.getDatabase().getCollection("products");
    }

    public List<MongoProduct> getAllProducts() {
        List<MongoProduct> products = new ArrayList<>();

        try {
            System.out.println("DEBUG: Pobieranie kolekcji 'products'.");
            MongoCursor<Document> cursor = productCollection.find().iterator();
            System.out.println("DEBUG: Rozpoczęto iterację po dokumentach w kolekcji 'products'.");

            while (cursor.hasNext()) {
                Document doc = cursor.next();

                System.out.println("DEBUG: Wczytano dokument: " + doc);

                // Pobierz dane produktu
                ObjectId id = doc.getObjectId("_id");
                String name = doc.getString("name");
                String details = doc.getString("details");
                double price = doc.getDouble("price");

                // Obsłuż DBRef dla kategorii
                ObjectId categoryId = extractObjectIdFromDBRef(doc.get("category"));

                // Obsłuż DBRef dla producenta
                ObjectId manufacturerId = extractObjectIdFromDBRef(doc.get("manufacturer"));

                // Obsłuż DBRef dla promocji
                ObjectId saleId = extractObjectIdFromDBRef(doc.get("sale"));

                // Tworzenie obiektu MongoProduct
                MongoProduct product = new MongoProduct(id, name, details, price, categoryId, manufacturerId, saleId);
                products.add(product);
            }

            System.out.println("DEBUG: Zakończono iterację. Znaleziono " + products.size() + " produktów.");
        } catch (Exception e) {
            System.out.println("ERROR: Wystąpił błąd podczas pobierania danych z MongoDB.");
            e.printStackTrace();
        }

        return products;
    }

    private ObjectId extractObjectIdFromDBRef(Object dbRef) {
        if (dbRef instanceof DBRef) {
            Object id = ((DBRef) dbRef).getId();
            if (id instanceof ObjectId) {
                return (ObjectId) id;
            } else if (id instanceof String) {
                // Konwersja String na ObjectId
                try {
                    return new ObjectId((String) id);
                } catch (IllegalArgumentException e) {
                    System.out.println("ERROR: Nie można przekonwertować String na ObjectId: " + id);
                    return null;
                }
            }
        } else if (dbRef instanceof String) {
            // Jeśli całe pole to String
            try {
                return new ObjectId((String) dbRef);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Nie można przekonwertować String na ObjectId: " + dbRef);
                return null;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        MongoProductDAO dao = new MongoProductDAO();
        List<MongoProduct> products = dao.getAllProducts();

        System.out.println("DEBUG: Liczba produktów w kolekcji: " + products.size());
        for (MongoProduct product : products) {
            System.out.println("Produkt: ID=" + product.getId() + ", Name=" + product.getName() + ", Cena=" + product.getPrice());
        }
    }
}
