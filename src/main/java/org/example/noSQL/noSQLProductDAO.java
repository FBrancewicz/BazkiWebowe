package org.example.noSQL;

import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
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

            while (cursor.hasNext()) {
                Document doc = cursor.next();


                ObjectId id = doc.getObjectId("_id");
                String name = doc.getString("name");
                String details = doc.getString("details");
                double price = doc.getDouble("price");


                noSQLProduct product = new noSQLProduct(id, name, details, price);


                if (doc.get("category") instanceof DBRef categoryRef) {
                    Document categoryDoc = getReferencedDocument(categoryRef);
                    if (categoryDoc != null) {
                        noSQLCategory category = new noSQLCategory();
                        category.setId((ObjectId) categoryDoc.get("_id"));
                        category.setName(categoryDoc.getString("name"));
                        category.setDescription(categoryDoc.getString("description"));
                        product.setCategory(category);
                    } else {

                    }
                }


                if (doc.get("manufacturer") instanceof DBRef manufacturerRef) {
                    Document manufacturerDoc = getReferencedDocument(manufacturerRef);
                    if (manufacturerDoc != null) {
                        noSQLManufacturer manufacturer = new noSQLManufacturer();
                        manufacturer.setId((ObjectId) manufacturerDoc.get("_id"));
                        manufacturer.setName(manufacturerDoc.getString("name"));
                        product.setManufacturer(manufacturer);
                    } else {
                    }
                }


                if (doc.get("sale") instanceof DBRef saleRef) {
                    Document saleDoc = getReferencedDocument(saleRef);
                    if (saleDoc != null) {

                        noSQLSale sale = new noSQLSale(
                                (ObjectId) saleDoc.get("_id"),
                                saleDoc.getString("name"),
                                saleDoc.getString("startDate"),
                                saleDoc.getString("endDate")
                        );
                        product.setSale(sale);
                    } else {

                    }
                }

                products.add(product);
            }

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

            e.printStackTrace();
            return null;
        }
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

            e.printStackTrace();
        }
    }

    public void addCategories(int count) {
        MongoCollection<Document> categoryCollection = noSQLDBConnection.getDatabase().getCollection("categories");

        List<Document> categories = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Document category = new Document("_id", new ObjectId())
                    .append("name", "noSQLCategory " + i)
                    .append("description", "Description for category " + i);
            categories.add(category);
        }

        try {
            categoryCollection.insertMany(categories);
            System.out.println("DEBUG: Added " + count + " categories to the 'categories' collection.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to add categories to the database.");
            e.printStackTrace();
        }
    }

    public void deleteCategoriesExcluding(List<ObjectId> excludedIds) {
        MongoCollection<Document> categoryCollection = noSQLDBConnection.getDatabase().getCollection("categories");

        try {
            long deletedCount = categoryCollection.deleteMany(Filters.nin("_id", excludedIds)).getDeletedCount();
            System.out.println("DEBUG: Deleted " + deletedCount + " categories, excluding specified IDs.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to delete categories.");
            e.printStackTrace();
        }
    }

    public void updateCategoryDescriptionsExcluding(List<ObjectId> excludedIds) {
        MongoCollection<Document> categoryCollection = noSQLDBConnection.getDatabase().getCollection("categories");

        try {
            long updatedCount = categoryCollection.updateMany(
                    Filters.nin("_id", excludedIds),
                    Updates.set("description", "Fajny opis")
            ).getModifiedCount();

        } catch (Exception e) {
            System.err.println("ERROR: Failed to update category descriptions.");
            e.printStackTrace();
        }
    }

}
