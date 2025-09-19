package com.example.collezione.service;

import com.example.collezione.model.*;
import com.example.collezione.exception.*;

import java.util.*;
import java.util.stream.Collectors;

public class Collezione {
    private final Map<String, Gioco> giochi = new HashMap<>();

    // ➤ Aggiunta
    public void aggiungiGioco(Gioco gioco) {
        if (giochi.containsKey(gioco.getId())) {
            throw new GiocoDuplicatoException("Gioco con ID " + gioco.getId() + " già presente!");
        }
        giochi.put(gioco.getId(), gioco);
    }

    // ➤ Ricerca per ID
    public Gioco ricercaPerId(String id) {
        return Optional.ofNullable(giochi.get(id))
                .orElseThrow(() -> new GiocoNonTrovatoException("Nessun gioco trovato con ID: " + id));
    }

    // ➤ Ricerca per prezzo (<= prezzo inserito)
    public List<Gioco> ricercaPerPrezzo(double maxPrezzo) {
        return giochi.values().stream()
                .filter(g -> g.getPrezzo() <= maxPrezzo)
                .collect(Collectors.toList());
    }

    // ➤ Ricerca per numero di giocatori (solo giochi da tavolo)
    public List<GiocoDaTavolo> ricercaPerNumeroGiocatori(int numGiocatori) {
        return giochi.values().stream()
                .filter(g -> g instanceof GiocoDaTavolo)
                .map(g -> (GiocoDaTavolo) g)
                .filter(gt -> gt.getNumeroGiocatori() == numGiocatori)
                .collect(Collectors.toList());
    }

    // ➤ Rimozione per ID
    public void rimuoviGioco(String id) {
        if (giochi.remove(id) == null) {
            throw new GiocoNonTrovatoException("Impossibile rimuovere: ID " + id + " non trovato.");
        }
    }

    // ➤ Aggiornamento (sostituisce il gioco con stesso ID)
    public void aggiornaGioco(String id, Gioco nuovoGioco) {
        if (!giochi.containsKey(id)) {
            throw new GiocoNonTrovatoException("Impossibile aggiornare: ID " + id + " non trovato.");
        }
        giochi.put(id, nuovoGioco);
    }

    // ➤ Statistiche
    public void statistiche() {
        long numVideogiochi = giochi.values().stream()
                .filter(g -> g instanceof Videogioco)
                .count();

        long numGiochiDaTavolo = giochi.values().stream()
                .filter(g -> g instanceof GiocoDaTavolo)
                .count();

        Gioco maxPrezzo = giochi.values().stream()
                .max(Comparator.comparingDouble(Gioco::getPrezzo))
                .orElse(null);

        double mediaPrezzi = giochi.values().stream()
                .mapToDouble(Gioco::getPrezzo)
                .average()
                .orElse(0.0);

        System.out.println("📊 Statistiche collezione:");
        System.out.println("- Videogiochi: " + numVideogiochi);
        System.out.println("- Giochi da tavolo: " + numGiochiDaTavolo);
        System.out.println("- Gioco più costoso: " + (maxPrezzo != null ? maxPrezzo : "Nessuno"));
        System.out.printf("- Prezzo medio: %.2f€%n", mediaPrezzi);
    }

    // ➤ Stampa tutti i giochi
    public void stampaTutti() {
        if (giochi.isEmpty()) {
            System.out.println("Nessun gioco in collezione.");
        } else {
            giochi.values().forEach(System.out::println);
        }
    }
}
