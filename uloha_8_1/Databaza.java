package com.example.uloha_8_1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Databaze {

    private static final String URL =
            "jdbc:mysql://localhost:3306/javafx";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
                URL, USER, PASSWORD
        );
    }

    public static ObservableList<Invoice> getInvoices()
            throws SQLException {

        ObservableList<Invoice> invoices =
                FXCollections.observableArrayList();

        String sql = "SELECT id, date, status FROM invoice";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Invoice invoice = new Invoice(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("status")
                );

                invoices.add(invoice);
            }
        }

        return invoices;
    }

    public static ObservableList<Item> getItems(int id)
            throws SQLException {

        ObservableList<Item> items =
                FXCollections.observableArrayList();

        String sql = """
                SELECT p.name, p.price, ii.amount
                FROM invoice_items ii
                JOIN product p ON ii.id_product = p.idS
                WHERE ii.id_invoice = ?
                ORDER BY ii.id
                """;

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Item item = new Item(
                            rs.getString("name"),
                            rs.getInt("amount"),
                            rs.getBigDecimal("price")
                    );

                    items.add(item);
                }
            }
        }

        return items;
    }
}
