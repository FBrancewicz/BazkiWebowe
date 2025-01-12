package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/noSQLBazaDanych")
public class ServletnoSQLProduct extends HttpServlet {
    private MongoProductDAO mongoProductDAO = new MongoProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DEBUG: Obsługa żądania /mongoProducts.");

        // Pobierz listę produktów z MongoDB
        List<MongoProduct> products = mongoProductDAO.getAllProducts();
        System.out.println("DEBUG: Pobieranie produktów zakończone. Liczba produktów: " + products.size());

        // Przekaż listę produktów do widoku JSP
        request.setAttribute("mongoProducts", products);

        // Przekieruj do widoku
        request.getRequestDispatcher("/WEB-INF/views/noSQLBazaDanych.jsp").forward(request, response);
    }
}
