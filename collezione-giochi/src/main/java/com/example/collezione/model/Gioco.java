package com.example.collezione.model;

public abstract class Gioco {
    private String id;
    private String titolo;
    private int annoPubblicazione;
    private double prezzo;

    public Gioco(String id, String titolo, int annoPubblicazione, double prezzo) {
        if (prezzo <= 0) throw new IllegalArgumentException("Il prezzo deve essere positivo");
        this.id = id;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    public String getId() { return id; }
    public String getTitolo() { return titolo; }
    public int getAnnoPubblicazione() { return annoPubblicazione; }
    public double getPrezzo() { return prezzo; }

    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setAnnoPubblicazione(int anno) { this.annoPubblicazione = anno; }
    public void setPrezzo(double prezzo) {
        if (prezzo <= 0) throw new IllegalArgumentException("Il prezzo deve essere positivo");
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%d) - %.2f€", id, titolo, annoPubblicazione, prezzo);
    }
}
