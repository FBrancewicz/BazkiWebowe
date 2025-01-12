package org.example;

import java.sql.Connection;
import java.util.List;

public class TestConnection {
//    public static void main(String[] args) {
//        ProductDAO productDAO = new ProductDAO();
//
//        try {
//            // Pobierz produkty z bazy danych
//            List<String> products = productDAO.getAllProducts();
//
//            // Wypisz produkty w konsoli
//            System.out.println("Produkty w tabeli Product:");
//            for (String product : products) {
//                System.out.println(product);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            System.out.println("Produkt: ID=" + product.getId() + ", Name=" + product.getName() + ", Cena=" + product.getPrice());
        }
    }

}