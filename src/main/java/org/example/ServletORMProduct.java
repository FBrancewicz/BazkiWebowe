package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Product;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/ORMBazaDanych") // Ścieżka dla tego servletu
public class ServletORMProduct extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        // Powiązanie z właściwą jednostką persystencji
        emf = Persistence.createEntityManagerFactory("webappdatabase-unit");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        try {
            // Pobieranie danych produktów
            List<Product> products = em.createQuery(
                    "SELECT p FROM Product p " +
                            "LEFT JOIN FETCH p.category " +
                            "LEFT JOIN FETCH p.manufacturer " +
                            "LEFT JOIN FETCH p.sale", Product.class
            ).getResultList();

            // Debugowanie w konsoli
            System.out.println("DEBUG: Lista produktów:");
            for (Product product : products) {
                System.out.println("Produkt: ID=" + product.getId() + ", Name=" + product.getName() + ", Cena=" + product.getPrice());
                if (product.getCategory() != null) {
                    System.out.println("Kategoria: " + product.getCategory().getName());
                }
                if (product.getManufacturer() != null) {
                    System.out.println("Producent: " + product.getManufacturer().getName());
                }
                if (product.getSale() != null) {
                    System.out.println("Promocja: " + product.getSale().getName());
                }
            }

            // Przekazanie danych do JSP
            request.setAttribute("productsOBDatabase", products);
            request.getRequestDispatcher("/WEB-INF/views/ORMBazaDanych.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
