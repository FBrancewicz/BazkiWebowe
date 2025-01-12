package org.example.orm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/ORMBazaDanych")
public class ServletORMProduct extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {

        emf = Persistence.createEntityManagerFactory("webappdatabase-unit");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        try {

            List<ProductORM> products = em.createQuery(
                    "SELECT p FROM ProductORM p " +
                            "LEFT JOIN FETCH p.category " +
                            "LEFT JOIN FETCH p.manufacturer " +
                            "LEFT JOIN FETCH p.saleORM"

            ).getResultList();


            System.out.println("DEBUG: Lista produktów:");
            for (ProductORM product : products) {
                System.out.println("Produkt: ID=" + product.getId() + ", Name=" + product.getName() + ", Cena=" + product.getPrice());
                if (product.getCategory() != null) {
                    System.out.println("Kategoria: " + product.getCategory().getName());
                }
                if (product.getManufacturer() != null) {
                    System.out.println("Producent: " + product.getManufacturer().getName());
                }
                if (product.getSaleORM() != null) {
                    System.out.println("Promocja: " + product.getSaleORM().getName());
                }
            }


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
