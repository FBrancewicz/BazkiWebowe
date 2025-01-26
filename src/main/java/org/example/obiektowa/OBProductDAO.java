package org.example.obiektowa;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class OBProductDAO {

    private final MongoCollection<Document> productCollection;
    private final MongoCollection<Document> categoryCollection;

    public OBProductDAO() {
        productCollection = OBMongoDBConnection.getDatabase().getCollection("products");
        categoryCollection = OBMongoDBConnection.getDatabase().getCollection("categories");
    }

    public List<OBMongoProduct> getAllProducts() {
        List<OBMongoProduct> products = new ArrayList<>();
        MongoCollection<Document> collection = OBMongoDBConnection.getDatabase().getCollection("products");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();


                ObjectId id = doc.getObjectId("_id");
                String name = doc.getString("name");
                String details = doc.getString("details");
                double price = doc.getDouble("price");

                OBMongoProduct product = new OBMongoProduct(id, name, details, price);


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




    public void addProducts(int count) {
        List<Document> products = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Document product = new Document("_id", new ObjectId())
                    .append("name", "OBProduct " + i)
                    .append("details", "Details of OBProduct " + i)
                    .append("price", 100.0 + i)
                    .append("category", new Document("_id", new ObjectId("674396ccec35e056394e90a9"))
                            .append("name", "Electronics")
                            .append("description", "Devices and gadgets"))
                    .append("manufacturer", new Document("_id", new ObjectId("674396ccec35e056394e90aa"))
                            .append("name", "Apple"))
                    .append("sale", new Document("_id", new ObjectId("674396ccec35e056394e90ab"))
                            .append("name", "Black Friday")
                            .append("startDate", "2024-11-25")
                            .append("endDate", "2024-11-30"));
            products.add(product);
        }
        productCollection.insertMany(products);
        System.out.println("DEBUG: Added " + count + " products to the 'products' collection.");
    }

    public void deleteAllProducts() {
        try {
            long deletedCount = productCollection.deleteMany(new Document()).getDeletedCount();
            System.out.println("DEBUG: Deleted " + deletedCount + " products from the 'products' collection.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to delete products.");
            e.printStackTrace();
        }
    }

    public void addCategories(int count) {
        List<Document> categories = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Document category = new Document("_id", new ObjectId())
                    .append("name", "OBCategory " + i)
                    .append("description", "Description for category " + i);
            categories.add(category);
        }

        try {
            categoryCollection.insertMany(categories);
            System.out.println("DEBUG: Added " + count + " categories to the 'categories' collection.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to add categories.");
            e.printStackTrace();
        }
    }

    public void deleteCategoriesExcluding(List<ObjectId> excludedIds) {
        MongoCollection<Document> categoryCollection = OBMongoDBConnection.getDatabase().getCollection("categories");

        try {
            long deletedCount = categoryCollection.deleteMany(Filters.nin("_id", excludedIds)).getDeletedCount();
            System.out.println("DEBUG: Deleted " + deletedCount + " categories, excluding specified IDs.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to delete categories.");
            e.printStackTrace();
        }
    }

    public void updateCategoryDescriptionsExcluding(List<ObjectId> excludedIds, String newDescription) {
        MongoCollection<Document> categoryCollection = OBMongoDBConnection.getDatabase().getCollection("categories");

        try {
            long updatedCount = categoryCollection.updateMany(
                    Filters.nin("_id", excludedIds),
                    Updates.set("description", newDescription)
            ).getModifiedCount();

            System.out.println("DEBUG: Updated description for " + updatedCount + " categories, excluding specified IDs.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to update category descriptions.");
            e.printStackTrace();
        }
    }

    public void updateProductDescriptions(String newDescription) {
        MongoCollection<Document> productCollection = OBMongoDBConnection.getDatabase().getCollection("products");

        try {
            long updatedCount = productCollection.updateMany(
                    new Document(), // No filter, apply to all products
                    Updates.set("details", newDescription)
            ).getModifiedCount();

            System.out.println("DEBUG: Updated description for " + updatedCount + " products to '" + newDescription + "'.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to update product descriptions.");
            e.printStackTrace();
        }
    }

}
