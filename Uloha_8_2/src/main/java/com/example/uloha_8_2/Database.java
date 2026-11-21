package com.example.uloha_8_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/movies";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ObservableList<Film> getMovies() throws SQLException {
        ObservableList<Film> movies = FXCollections.observableArrayList();
        String sql = "SELECT id, title, length, year, link FROM movie ORDER BY id";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                movies.add(new Film(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("length"),
                        rs.getInt("year"),
                        rs.getString("link")
                ));
            }
        }
        return movies;
    }

    public static ObservableList<Zaner> getGenres(int movieId) throws SQLException {
        ObservableList<Zaner> genres = FXCollections.observableArrayList();
        String sql = """
                SELECT g.id, g.name
                FROM genre g
                JOIN movie_genres mg ON mg.genre_id = g.id
                WHERE mg.movie_id = ?
                ORDER BY g.name
                """;

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    genres.add(new Zaner(rs.getInt("id"), rs.getString("name")));
                }
            }
        }
        return genres;
    }
}

