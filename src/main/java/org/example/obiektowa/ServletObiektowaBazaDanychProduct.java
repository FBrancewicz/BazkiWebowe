package org.example.obiektowa;

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
        // Fetch products from MongoDB
        List<OBMongoProduct> products = productDAO.getAllProducts();

        // Debugowanie listy produktów
        System.out.println("DEBUG: Liczba produktów odczytanych z MongoDB: " + products.size());
        for (OBMongoProduct product : products) {
            System.out.println("DEBUG: Produkt - " + product.getName() + ", Cena: " + product.getPrice());
        }

        // Set attributes for JSP
        request.setAttribute("ormProducts", products);

        // Forward request to JSP
        request.getRequestDispatcher("/WEB-INF/views/ObiektowaBazaDanych.jsp").forward(request, response);
    }




}
