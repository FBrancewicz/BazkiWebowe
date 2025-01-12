package org.example.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseSeeder {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectrelationaldb-unit");

    public static void addRelatedEntities() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Dodanie kategorii
            Category category = new Category();
            category.setName("Smartphones");
            category.setDescription("Mobile devices");
            em.persist(category);

            // Dodanie producenta
            Manufacturer samsung = new Manufacturer();
            samsung.setName("Samsung");
            em.persist(samsung);

            Manufacturer apple = new Manufacturer();
            apple.setName("Apple");
            em.persist(apple);

            Manufacturer google = new Manufacturer();
            google.setName("Google");
            em.persist(google);

            // Dodanie promocji
            Sale sale = new Sale();
            sale.setName("Winter Sale");
            sale.setStartDate("2025-01-01");
            sale.setEndDate("2025-01-31");
            em.persist(sale);

            em.getTransaction().commit();
            System.out.println("Kategorie, producenci i promocje zostały dodane do WebAppDatabase!");
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
            Category category = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                    .setParameter("name", "Smartphones")
                    .getSingleResult();


            Manufacturer samsung = em.createQuery("SELECT m FROM Manufacturer m WHERE m.name = :name", Manufacturer.class)
                    .setParameter("name", "Samsung")
                    .getSingleResult();

            Manufacturer apple = em.createQuery("SELECT m FROM Manufacturer m WHERE m.name = :name", Manufacturer.class)
                    .setParameter("name", "Apple")
                    .getSingleResult();

            Manufacturer google = em.createQuery("SELECT m FROM Manufacturer m WHERE m.name = :name", Manufacturer.class)
                    .setParameter("name", "Google")
                    .getSingleResult();

            Sale sale = em.createQuery("SELECT s FROM Sale s WHERE s.name = :name", Sale.class)
                    .setParameter("name", "Winter Sale")
                    .getSingleResult();

            // Dodanie produktów
            Product product1 = new Product();
            product1.setName("Samsung Galaxy S23");
            product1.setDetails("High-end Android smartphone");
            product1.setPrice(899.99);
            product1.setCategory(category);
            product1.setManufacturer(samsung);
            product1.setSale(sale);
            em.persist(product1);

            Product product2 = new Product();
            product2.setName("Samsung Galaxy A54");
            product2.setDetails("Mid-range Android smartphone");
            product2.setPrice(449.99);
            product2.setCategory(category);
            product2.setManufacturer(samsung);
            product2.setSale(sale);
            em.persist(product2);

            Product product3 = new Product();
            product3.setName("iPhone 15");
            product3.setDetails("Latest Apple smartphone");
            product3.setPrice(999.99);
            product3.setCategory(category);
            product3.setManufacturer(apple);
            product3.setSale(sale);
            em.persist(product3);

            Product product4 = new Product();
            product4.setName("Google Pixel 8");
            product4.setDetails("Google's flagship Android smartphone");
            product4.setPrice(799.99);
            product4.setCategory(category);
            product4.setManufacturer(google);
            product4.setSale(sale);
            em.persist(product4);

            em.getTransaction().commit();
            System.out.println("Produkty zostały dodane do WebAppDatabase!");
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
