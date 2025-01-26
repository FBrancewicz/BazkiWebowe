package org.example.noSQL;

import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/noSQLBazaDanych")
public class ServletnoSQL extends HttpServlet {
    private final noSQLProductDAO noSQLProductDAO = new noSQLProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DEBUG: Obsługa żądania /noSQLBazaDanych");

        List<noSQLProduct> products = noSQLProductDAO.getAllProducts();
        System.out.println("DEBUG: Pobieranie produktów zakończone. Liczba produktów: " + products.size());

        if (products.size() > 4) {
            products = products.subList(0, 4);
        }

        request.setAttribute("mongoProducts", products);

        request.getRequestDispatcher("/WEB-INF/views/noSQLBazaDanych.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        noSQLProductDAO noSQLProductDAO = new noSQLProductDAO();
        noSQLProductSeeder noSQLProductSeeder = new noSQLProductSeeder();

        long startTime = System.currentTimeMillis();

        try {
            switch (action) {
                case "addSingleProduct":
                    noSQLProductSeeder.addProducts(1);
                    break;
                case "addHundredProducts":
                    noSQLProductSeeder.addProducts(100);
                    break;
                case "addTwoThousandProducts":
                    noSQLProductSeeder.addProducts(2000);
                    break;
                case "addFourThousandProducts":
                    noSQLProductSeeder.addProducts(4000);
                    break;
                case "addEightThousandProducts":
                    noSQLProductSeeder.addProducts(8000);
                    break;
                case "addTwentyThousandProducts":
                    noSQLProductSeeder.addProducts(20000);
                    break;
                case "deleteAllProducts":
                    noSQLProductDAO.deleteAllProducts();
                    break;
                case "updateCategoryToSport":
                    noSQLProductDAO.updateCategoryToSport();
                    break;
                case "addSingleCategory":
                    noSQLProductDAO.addCategories(1);
                    break;
                case "addHundredCategories":
                    noSQLProductDAO.addCategories(100);
                    break;
                case "addTwoThousandCategories":
                    noSQLProductDAO.addCategories(2000);
                    break;
                case "addFourThousandCategories":
                    noSQLProductDAO.addCategories(4000);
                    break;
                case "addEightThousandCategories":
                    noSQLProductDAO.addCategories(8000);
                    break;
                case "addTwentyThousandCategories":
                    noSQLProductDAO.addCategories(20000);
                    break;
                case "deleteCategoriesExcluding":
                    noSQLProductDAO.deleteCategoriesExcluding(
                            List.of(
                                    new ObjectId("674396ccec35e056394e90a9"),
                                    new ObjectId("6783ecec0068352c8ac42539")
                            )
                    );
                    break;
                case "updateCategoryDescriptions":
                    noSQLProductDAO.updateCategoryDescriptionsExcluding(
                            List.of(
                                    new ObjectId("674396ccec35e056394e90a9"),
                                    new ObjectId("6783ecec0068352c8ac42539")
                            )
                    );
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported action: " + action);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
            return;
        }

        long endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000.0;

        request.setAttribute("executionTimeMessage", String.format("Operacja trwała: %.4f sekund", durationInSeconds));

        doGet(request, response);
    }



}
