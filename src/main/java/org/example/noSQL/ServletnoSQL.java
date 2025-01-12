package org.example.noSQL;

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

        // Pobieranie produktów
        List<noSQLProduct> products = noSQLProductDAO.getAllProducts();
        System.out.println("DEBUG: Pobieranie produktów zakończone. Liczba produktów: " + products.size());

        // Przekazanie danych do widoku JSP
        request.setAttribute("mongoProducts", products);

        // Przekierowanie do widoku
        request.getRequestDispatcher("/WEB-INF/views/noSQLBazaDanych.jsp").forward(request, response);
    }
}
