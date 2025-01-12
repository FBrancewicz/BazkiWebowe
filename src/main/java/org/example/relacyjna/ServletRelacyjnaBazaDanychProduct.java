package org.example.relacyjna;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RelacyjnaBazaDanych")
public class ServletRelacyjnaBazaDanychProduct extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pobranie listy produktów z DAO
        List<Product> products = productDAO.getAllProducts();

        // Debugowanie listy produktów
        System.out.println("DEBUG: Lista produktów zawiera " + products.size() + " elementów.");
        for (Product product : products) {
            System.out.println("DEBUG: Produkt - " + product.getName() + ", Cena: " + product.getPrice());
        }

        // Przekazanie listy do widoku JSP
        request.setAttribute("products", products);

        // Przekierowanie do JSP
        request.getRequestDispatcher("/WEB-INF/views/RelacyjnaBazaDanych.jsp").forward(request, response);
    }
}

