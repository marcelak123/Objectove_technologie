package com.example.zoznam_nakupu;

public class Polozka {
    private String nazov;
    private int pocet;

    public Polozka(String nazov, int pocet) {
        this.nazov = nazov;
        this.pocet = pocet;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    @Override
    public String toString() {
        return nazov + " — " + pocet + " ks";
    }
}
