package org.example.obiektowa;

import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ObiektowaBazaDanych")
public class ServletObiektowaBazaDanychProduct extends HttpServlet {
    private OBProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new OBProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<OBMongoProduct> products = productDAO.getAllProducts();

            if (products.size() > 5) {
                products = products.subList(0, 5);
            }

            if (products.isEmpty()) {
                System.out.println("DEBUG: Brak produktów w kolekcji MongoDB.");
            } else {
                System.out.println("DEBUG: Liczba produktów odczytanych z MongoDB: " + products.size());
                for (OBMongoProduct product : products) {
                    System.out.println("DEBUG: Produkt - " + product.getName() + ", Cena: " + product.getPrice());
                }
            }

            request.setAttribute("ormProducts", products);

        } catch (Exception e) {
            System.out.println("ERROR: Wystąpił błąd podczas pobierania produktów z MongoDB.");
            e.printStackTrace();
            request.setAttribute("errorMessage", "Nie można załadować produktów. Skontaktuj się z administratorem.");
        }

        request.getRequestDispatcher("/WEB-INF/views/ObiektowaBazaDanych.jsp").forward(request, response);
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        long startTime = System.currentTimeMillis();

        try {
            switch (action) {
                case "addSingleProduct":
                    productDAO.addProducts(1);
                    break;
                case "addHundredProducts":
                    productDAO.addProducts(100);
                    break;
                case "addTwoThousandProducts":
                    productDAO.addProducts(2000);
                    break;
                case "addFourThousandProducts":
                    productDAO.addProducts(4000);
                    break;
                case "addEightThousandProducts":
                    productDAO.addProducts(8000);
                    break;
                case "addTwentyThousandProducts":
                    productDAO.addProducts(20000);
                    break;
                case "deleteAllProducts":
                    productDAO.deleteAllProducts();
                    break;
                case "addSingleCategory":
                    productDAO.addCategories(1);
                    break;
                case "addHundredCategories":
                    productDAO.addCategories(100);
                    break;
                case "addTwoThousandCategories":
                    productDAO.addCategories(2000);
                    break;
                case "addFourThousandCategories":
                    productDAO.addCategories(4000);
                    break;
                case "addEightThousandCategories":
                    productDAO.addCategories(8000);
                    break;
                case "addTwentyThousandCategories":
                    productDAO.addCategories(20000);
                    break;
                case "deleteAllCategoriesExcept":
                    productDAO.deleteCategoriesExcluding(List.of(
                            new ObjectId("6783f7bf0068352c8ac42567"),
                            new ObjectId("67966f840af41372ada666d8")
                    ));
                    break;
                case "updateCategoryDescriptions":
                    productDAO.updateCategoryDescriptionsExcluding(
                            List.of(
                                    new ObjectId("6783f7bf0068352c8ac42567"),
                                    new ObjectId("67966f840af41372ada666d8")
                            ),
                            "Ciekawy opis"
                    );
                    break;
                case "updateProductDescriptions":
                    productDAO.updateProductDescriptions("Ciekawy opis produktu");
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
