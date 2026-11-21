package com.example.uloha_8_1;
import javafx.fxml.FXML;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloController {

    @FXML private TableView<Invoice> invoiceTable;
    @FXML private TableView<Item> itemTable;

    @FXML private TableColumn<Invoice, Integer> invoiceId;
    @FXML private TableColumn<Invoice, LocalDate> invoiceDate;
    @FXML private TableColumn<Invoice, String> invoiceStatus;

    @FXML private TableColumn<Item, String> itemName;
    @FXML private TableColumn<Item, Integer> itemAmount;
    @FXML private TableColumn<Item, BigDecimal> itemPrice;
    @FXML private TableColumn<Item, BigDecimal> itemTotal;

    @FXML private Label totalLabel;

    @FXML
    private void initialize() {

        invoiceId.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getId()
                ));

        invoiceDate.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getDate()
                ));

        invoiceStatus.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getStatus()
                ));

        itemName.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getName()
                ));

        itemAmount.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getAmount()
                ));

        itemPrice.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getPrice()
                ));

        itemTotal.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getTotal()
                ));

        invoiceTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    if (newValue != null) {
                        loadItems(newValue.getId());
                    } else {
                        itemTable.getItems().clear();
                        totalLabel.setText("Suma: 0.00 €");
                    }
                });

        loadInvoices();
    }

    private void loadInvoices() {

        try {
            invoiceTable.setItems(Databaza.getInvoices());

        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    private void loadItems(int invoiceId) {

        try {
            ObservableList<Item> items =
                    Databaza.getItems(invoiceId);

            itemTable.setItems(items);

            BigDecimal total = BigDecimal.ZERO;

            for (Item item : items) {
                total = total.add(item.getTotal());
            }

            totalLabel.setText(
                    "Suma: " + total.toPlainString() + " €"
            );

        } catch (SQLException e) {
            itemTable.getItems().clear();
            totalLabel.setText("Suma: --");
            showError(e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba databázy");
        alert.setHeaderText("Nedalo sa načítať úaje");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

