package com.example.uloha_8_2;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.SQLException;

public class HelloController {
    @FXML private TableView<Film> movieTable;
    @FXML private TableColumn<Film, Integer> movieId;
    @FXML private TableColumn<Film, String> movieTitle;
    @FXML private TableColumn<Film, Integer> movieLength;
    @FXML private TableColumn<Film, Integer> movieYear;

    @FXML private TableView<Zaner> genreTable;
    @FXML private TableColumn<Zaner, Integer> genreId;
    @FXML private TableColumn<Zaner, String> genreName;

    @FXML private Label infoLabel;

    @FXML
    private void initialize() {
        movieId.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getId()));
        movieTitle.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getTitle()));
        movieLength.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getLength()));
        movieYear.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getYear()));

        genreId.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getId()));
        genreName.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getName()));

        movieTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldMovie, movie) -> {
                    if (movie == null) {
                        genreTable.getItems().clear();
                        infoLabel.setText("Vyber film zo zoznamu.");
                    } else {
                        loadGenres(movie);
                    }
                }
        );

        try {
            movieTable.setItems(Database.getMovies());
        } catch (SQLException e) {
            showError(e);
        }
    }

    private void loadGenres(Film movie) {
        try {
            ObservableList<Zaner> genres = Database.getGenres(movie.getId());
            genreTable.setItems(genres);
            infoLabel.setText("Film: " + movie.getTitle() + " | Počet žánrov: " + genres.size());
        } catch (SQLException e) {
            genreTable.getItems().clear();
            infoLabel.setText("Nepodarilo sa načítať žánre.");
            showError(e);
        }
    }

    private void showError(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba databázy");
        alert.setHeaderText("Nepodarilo sa načítať údaje");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
