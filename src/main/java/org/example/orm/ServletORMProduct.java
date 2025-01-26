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
                                    "LEFT JOIN FETCH p.saleORM",
                            ProductORM.class
                    )
                    .setMaxResults(4)
                    .getResultList();

            request.setAttribute("productsOBDatabase", products);
            request.getRequestDispatcher("/WEB-INF/views/ORMBazaDanych.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        long startTime = System.currentTimeMillis();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            switch (action) {
                case "addSingleProduct":
                    RelationalDatabaseSeeder.addProducts(1);
                    break;
                case "addTenProducts":
                    RelationalDatabaseSeeder.addProducts(10);
                    break;
                case "addHundredProducts":
                    RelationalDatabaseSeeder.addProducts(100);
                    break;
                case "addTwoThousandProducts":
                    RelationalDatabaseSeeder.addProducts(2000);
                    break;
                case "addFourThousandProducts":
                    RelationalDatabaseSeeder.addProducts(4000);
                    break;
                case "addEightThousandProducts":
                    RelationalDatabaseSeeder.addProducts(8000);
                    break;
                case "addTwentyThousandProducts":
                    RelationalDatabaseSeeder.addProducts(20000);
                    break;
                case "deleteAllProducts":
                    RelationalDatabaseSeeder.deleteAllProducts();
                    break;
                case "updateCategoryTo2":
                    RelationalDatabaseSeeder.updateProductCategoryTo(2);
                    break;
                case "addSingleCategory":
                    RelationalDatabaseSeeder.addCategory();
                    break;
                case "addHundredCategories":
                    RelationalDatabaseSeeder.addCategories(100);
                    break;
                case "addTwoThousandCategories":
                    RelationalDatabaseSeeder.addCategories(2000);
                    break;
                case "addFourThousandCategories":
                    RelationalDatabaseSeeder.addCategories(4000);
                    break;
                case "addEightThousandCategories":
                    RelationalDatabaseSeeder.addCategories(8000);
                    break;
                case "addTwentyThousandCategories":
                    RelationalDatabaseSeeder.addCategories(20000);
                    break;
                case "deleteAllCategoriesExcept":
                    RelationalDatabaseSeeder.deleteCategoriesExcludingIds(List.of(1, 2));
                    break;
                case "updateCategoryNames":
                    RelationalDatabaseSeeder.updateCategoryNames(em);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported action: " + action);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        long endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000.0;
        request.setAttribute("executionTimeMessage", "Operacja trwala: " + durationInSeconds + " sekund");

        doGet(request, response);
    }



    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
