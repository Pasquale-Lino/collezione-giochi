package com.example.collezione.model;

public class GiocoDaTavolo extends Gioco {
    private int numeroGiocatori;
    private int durataMinuti;

    public GiocoDaTavolo(String id, String titolo, int anno, double prezzo,
                         int numeroGiocatori, int durataMinuti) {
        super(id, titolo, anno, prezzo);
        if (numeroGiocatori < 2 || numeroGiocatori > 10)
            throw new IllegalArgumentException("Il numero di giocatori deve essere tra 2 e 10");
        this.numeroGiocatori = numeroGiocatori;
        this.durataMinuti = durataMinuti;
    }

    public int getNumeroGiocatori() { return numeroGiocatori; }
    public int getDurataMinuti() { return durataMinuti; }

    public void setNumeroGiocatori(int numeroGiocatori) {
        if (numeroGiocatori < 2 || numeroGiocatori > 10)
            throw new IllegalArgumentException("Il numero di giocatori deve essere tra 2 e 10");
        this.numeroGiocatori = numeroGiocatori;
    }
    public void setDurataMinuti(int durataMinuti) { this.durataMinuti = durataMinuti; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Gioco da Tavolo [%d giocatori, %d min]", numeroGiocatori, durataMinuti);
    }
}
