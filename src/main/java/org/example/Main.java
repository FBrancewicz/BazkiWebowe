package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Client;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Dodanie testowego klienta
        Client client = new Client();
        client.setUsername("johndoe");
        client.setEmail("johndoe@example.com");
        client.setPassword("securepassword");
        client.setRole("user");

        em.persist(client);

        em.getTransaction().commit();

        System.out.println("Baza danych została pomyślnie zainicjalizowana.");
        em.close();
        emf.close();
    }
}
