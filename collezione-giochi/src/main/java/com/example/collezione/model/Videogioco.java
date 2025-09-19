package com.example.collezione.model;

public class Videogioco extends Gioco {
    private String piattaforma;
    private int durataOre;
    private Genere genere;

    public Videogioco(String id, String titolo, int anno, double prezzo,
                      String piattaforma, int durataOre, Genere genere) {
        super(id, titolo, anno, prezzo);
        this.piattaforma = piattaforma;
        this.durataOre = durataOre;
        this.genere = genere;
    }

    public String getPiattaforma() { return piattaforma; }
    public int getDurataOre() { return durataOre; }
    public Genere getGenere() { return genere; }

    public void setPiattaforma(String piattaforma) { this.piattaforma = piattaforma; }
    public void setDurataOre(int durataOre) { this.durataOre = durataOre; }
    public void setGenere(Genere genere) { this.genere = genere; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Videogioco [%s, %dh, %s]", piattaforma, durataOre, genere);
    }
}
