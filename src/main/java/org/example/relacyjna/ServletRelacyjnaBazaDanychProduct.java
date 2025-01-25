package org.example.relacyjna;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/RelacyjnaBazaDanych")
public class ServletRelacyjnaBazaDanychProduct extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1; // Default page
        int recordsPerPage = 5; // Number of products per page

        // Get page number from query parameters
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Default page in case of error
            }
        }

        List<Product> products = productDAO.getProductsByPage(page, recordsPerPage);
        int totalRecords = productDAO.getTotalProductsCount();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("products", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/WEB-INF/views/RelacyjnaBazaDanych.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        long startTime = System.currentTimeMillis();

        try (Connection connection = DatabaseConnection.getConnection()) {
            switch (action) {
                case "addSingleProduct":
                    productDAO.addProducts(connection, 1);
                    break;
                case "addTenProducts":
                    productDAO.addProducts(connection, 10);
                    break;
                case "addHundredProducts":
                    productDAO.addProducts(connection, 100);
                    break;
                case "addTwoThousandProducts":
                    productDAO.addProducts(connection, 2000);
                    break;
                case "addFourThousandProducts":
                    productDAO.addProducts(connection, 4000);
                    break;
                case "addEightThousandProducts":
                    productDAO.addProducts(connection, 8000);
                    break;
                case "addTwentyThousandProducts":
                    productDAO.addProducts(connection, 20000);
                    break;
                case "addSingleCategory":
                    productDAO.addCategories(connection, 1);
                    break;
                case "addHundredCategories":
                    productDAO.addCategories(connection, 100);
                    break;
                case "addTwoThousandCategories":
                    productDAO.addCategories(connection, 2000);
                    break;
                case "addFourThousandCategories":
                    productDAO.addCategories(connection, 4000);
                    break;
                case "addEightThousandCategories":
                    productDAO.addCategories(connection, 8000);
                    break;
                case "addTwentyThousandCategories":
                    productDAO.addCategories(connection, 20000);
                    break;
                case "deleteAllProducts":
                    productDAO.deleteAllProducts();
                    break;
                case "deleteCategories":
                    productDAO.deleteCategoriesExcludingIds(connection, List.of(1, 2, 3));
                    break;
                case "updateCategoryToBooks":
                    productDAO.updateCategoryForAllProductsToBooks();
                    break;
                case "updateCategoryDescription":
                    productDAO.updateCategoryDescriptionsExcludingIds("Fajny opis", List.of(1, 2, 3));
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
        request.setAttribute("executionTimeMessage", "Operacja trwala: " + durationInSeconds + " seconds");

        doGet(request, response);
    }





}
