package com.example.uloha_8_1;

import java.time.LocalDate;

public class Invoice {

    private int id;
    private LocalDate date;
    private String status;

    public Invoice(int id, LocalDate date, String status) {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}

