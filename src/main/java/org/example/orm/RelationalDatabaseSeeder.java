package org.example.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.text.SimpleDateFormat;

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

            // Pobierz powiązane dane
            CategoryORM category = em.createQuery("SELECT c FROM CategoryORM c WHERE c.name = :name", CategoryORM.class)
                    .setParameter("name", "Smartphones")
                    .getSingleResult();
            ManufacturerORM manufacturer = em.createQuery("SELECT m FROM ManufacturerORM m WHERE m.name = :name", ManufacturerORM.class)
                    .setParameter("name", "Samsung")
                    .getSingleResult();
            SaleORM sale = em.createQuery("SELECT s FROM SaleORM s WHERE s.name = :name", SaleORM.class)
                    .setParameter("name", "Winter Sale")
                    .getSingleResult();
            ClientORM client = em.createQuery("SELECT c FROM ClientORM c WHERE c.username = :username", ClientORM.class)
                    .setParameter("username", "exampleUser")
                    .getSingleResult();

            // Sprawdzenie, czy istnieje produkt do aktualizacji
            ProductORM existingProduct = null;
            try {
                existingProduct = em.createQuery("SELECT p FROM ProductORM p WHERE p.name = :name", ProductORM.class)
                        .setParameter("name", "Samsung Galaxy S2")
                        .getSingleResult();
            } catch (jakarta.persistence.NoResultException e) {
                System.out.println("Brak istniejącego produktu. Dodawanie nowego produktu...");
            }

            if (existingProduct != null) {
                existingProduct.setName("BazaObiektowoRelacyjnaProdukt1");
                em.persist(existingProduct);
            } else {
                // Dodanie nowego produktu
                ProductORM product1 = new ProductORM();
                product1.setName("BazaObiektowoRelacyjnaProdukt1");
                product1.setDetails("High-end Android smartphone");
                product1.setPrice(899.99);
                product1.setCategory(category);
                product1.setManufacturer(manufacturer);
                product1.setSaleORM(sale);
                product1.setClient(client);
                em.persist(product1);
            }

            // Dodanie nowych produktów
            ProductORM product2 = new ProductORM();
            product2.setName("BazaObiektowoRelacyjnaProdukt2");
            product2.setDetails("Mid-range Android smartphone");
            product2.setPrice(499.99);
            product2.setCategory(category);
            product2.setManufacturer(manufacturer);
            product2.setSaleORM(sale);
            product2.setClient(client);
            em.persist(product2);

            ProductORM product3 = new ProductORM();
            product3.setName("BazaObiektowoRelacyjnaProdukt3");
            product3.setDetails("Flagship foldable Android smartphone");
            product3.setPrice(1799.99);
            product3.setCategory(category);
            product3.setManufacturer(manufacturer);
            product3.setSaleORM(sale);
            product3.setClient(client);
            em.persist(product3);

            em.getTransaction().commit();
            System.out.println("Produkty zostały dodane do webappdatabase");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }



    public static void main(String[] args) {
        // Dodanie powiązanych danych
        addRelatedEntities();

        // Dodanie przykładowych produktów
        addProduct();
    }
}
