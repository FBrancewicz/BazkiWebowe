package org.example.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.text.SimpleDateFormat;
import java.util.List;

public class RelationalDatabaseSeeder {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("webappdatabase-unit");

    public static void addRelatedEntities() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();


            ClientORM client = new ClientORM();
            client.setUsername("exampleUser");
            client.setEmail("example@domain.com");
            client.setPassword("securepassword");
            client.setRole("USER");
            em.persist(client);


            CategoryORM category = new CategoryORM();
            category.setName("Smartphones");
            category.setDescription("Mobile devices");
            em.persist(category);

            ManufacturerORM manufacturer = new ManufacturerORM();
            manufacturer.setName("Samsung");
            em.persist(manufacturer);

            SaleORM sale = new SaleORM();
            sale.setName("Winter Sale");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sale.setStartDate(sdf.parse("2025-01-01"));
            sale.setEndDate(sdf.parse("2025-01-31"));
            em.persist(sale);

            em.getTransaction().commit();
            System.out.println("Kategorie, producenci, klienci i promocje zostały dodane do ObjectRelationalDB!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public static void addProduct() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();


            CategoryORM category = em.find(CategoryORM.class, 1);
            ManufacturerORM manufacturer = em.find(ManufacturerORM.class, 1);
            SaleORM sale = em.find(SaleORM.class, 1);
            ClientORM client = em.find(ClientORM.class, 1);

            if (category == null || manufacturer == null || sale == null || client == null) {
                throw new IllegalStateException("Powiązane encje muszą istnieć w bazie danych przed dodaniem produktu.");
            }

            ProductORM product = new ProductORM();
            product.setName("New Product");
            product.setDetails("Details about the new product");
            product.setPrice(299.99);
            product.setCategory(category);
            product.setManufacturer(manufacturer);
            product.setSaleORM(sale);
            product.setClient(client);

            em.persist(product);

            em.getTransaction().commit();
            System.out.println("Produkt został dodany pomyślnie.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }



    public static void main(String[] args) {

        addRelatedEntities();


        addProduct();
    }


    public static void addProducts(int count) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();


            CategoryORM category = em.find(CategoryORM.class, 1);
            ManufacturerORM manufacturer = em.find(ManufacturerORM.class, 1);
            SaleORM sale = em.find(SaleORM.class, 1);
            ClientORM client = em.find(ClientORM.class, 1);

            if (category == null || manufacturer == null || sale == null || client == null) {
                throw new IllegalStateException("Powiązane encje muszą istnieć w bazie danych przed dodaniem produktów.");
            }


            for (int i = 1; i <= count; i++) {
                ProductORM product = new ProductORM();
                product.setName("ProductORM " + i);
                product.setDetails("Product details for product " + i);
                product.setPrice(100.00 + i);
                product.setCategory(category);
                product.setManufacturer(manufacturer);
                product.setSaleORM(sale);
                product.setClient(client);
                em.persist(product);


                if (i % 500 == 0) {
                    em.getTransaction().commit();
                    em.clear();
                    em.getTransaction().begin();
                }
            }

            em.getTransaction().commit();
            System.out.println(count + " products added successfully.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public static void deleteAllProducts() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            int deletedCount = em.createQuery("DELETE FROM ProductORM").executeUpdate();

            em.getTransaction().commit();
            System.out.println("Deleted " + deletedCount + " products from the database.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public static void updateProductCategoryTo(int categoryId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();


            CategoryORM newCategory = em.find(CategoryORM.class, categoryId);

            if (newCategory == null) {
                throw new IllegalArgumentException("Category with ID " + categoryId + " does not exist.");
            }


            int updatedCount = em.createQuery("UPDATE ProductORM p SET p.category = :newCategory")
                    .setParameter("newCategory", newCategory)
                    .executeUpdate();

            em.getTransaction().commit();
            System.out.println("Updated category for " + updatedCount + " products.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void addCategory() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CategoryORM category = new CategoryORM();
            category.setName("New Category");
            category.setDescription("Description for the new category");
            em.persist(category);

            em.getTransaction().commit();
            System.out.println("Category added successfully.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void addCategories(int count) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            for (int i = 1; i <= count; i++) {
                CategoryORM category = new CategoryORM();
                category.setName("Category " + i);
                category.setDescription("Description for Category " + i);
                em.persist(category);
            }

            em.getTransaction().commit();
            System.out.println(count + " categories added successfully.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void deleteCategoriesExcludingIds(List<Integer> excludedIds) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            int deletedCount = em.createQuery("DELETE FROM CategoryORM c WHERE c.id NOT IN :excludedIds")
                    .setParameter("excludedIds", excludedIds)
                    .executeUpdate();

            em.getTransaction().commit();
            System.out.println("Deleted " + deletedCount + " categories, excluding IDs: " + excludedIds);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public static void updateCategoryNames(EntityManager em) {
        int updatedCount = em.createQuery("UPDATE CategoryORM c SET c.name = :newName WHERE c.id NOT IN (1, 2)")
                .setParameter("newName", "Fajna nazwa kategorii")
                .executeUpdate();

        System.out.println("Updated names for " + updatedCount + " categories.");
    }

}
