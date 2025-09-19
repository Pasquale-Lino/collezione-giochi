package com.example;

import com.example.collezione.model.*;
import com.example.collezione.service.Collezione;
import com.example.collezione.exception.*;
import com.example.collezione.util.FakeDataGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collezione collezione = new Collezione();

        // Popola la collezione con dati fake
        FakeDataGenerator.popolaCollezione(collezione, 15, 15);
        System.out.println("📦 Dati fake iniziali caricati:");
        collezione.stampaTutti();

        boolean running = true;
        while (running) {
            System.out.println("\n===== MENU COLLEZIONE GIOCHI =====");
            System.out.println("1. Aggiungi gioco");
            System.out.println("2. Cerca gioco per ID");
            System.out.println("3. Cerca giochi per prezzo");
            System.out.println("4. Cerca giochi da tavolo per numero giocatori");
            System.out.println("5. Rimuovi gioco per ID");
            System.out.println("6. Aggiorna gioco");
            System.out.println("7. Mostra statistiche");
            System.out.println("8. Stampa tutti i giochi");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            int scelta;
            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Inserisci un numero valido!");
                continue;
            }

            try {
                switch (scelta) {
                    case 1 -> aggiungiGioco(scanner, collezione);
                    case 2 -> {
                        System.out.print("Inserisci ID: ");
                        String id = scanner.nextLine();
                        System.out.println(collezione.ricercaPerId(id));
                    }
                    case 3 -> {
                        System.out.print("Prezzo massimo: ");
                        double prezzo = Double.parseDouble(scanner.nextLine());
                        collezione.ricercaPerPrezzo(prezzo).forEach(System.out::println);
                    }
                    case 4 -> {
                        System.out.print("Numero giocatori: ");
                        int num = Integer.parseInt(scanner.nextLine());
                        collezione.ricercaPerNumeroGiocatori(num).forEach(System.out::println);
                    }
                    case 5 -> {
                        System.out.print("ID da rimuovere: ");
                        String id = scanner.nextLine();
                        collezione.rimuoviGioco(id);
                        System.out.println("✅ Gioco rimosso.");
                    }
                    case 6 -> aggiornaGioco(scanner, collezione);
                    case 7 -> collezione.statistiche();
                    case 8 -> collezione.stampaTutti();
                    case 0 -> running = false;
                    default -> System.out.println("⚠️ Scelta non valida!");
                }
            } catch (GiocoDuplicatoException | GiocoNonTrovatoException | IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        System.out.println("👋 Uscita dal programma.");
        scanner.close();
    }

    private static void aggiungiGioco(Scanner scanner, Collezione collezione) {
        System.out.println("Vuoi aggiungere un (1) Videogioco o (2) Gioco da Tavolo?");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Prezzo: ");
        double prezzo = Double.parseDouble(scanner.nextLine());

        if (tipo == 1) {
            System.out.print("Piattaforma: ");
            String piattaforma = scanner.nextLine();
            System.out.print("Durata (ore): ");
            int ore = Integer.parseInt(scanner.nextLine());

            System.out.println("Scegli genere: ");
            for (Genere g : Genere.values()) {
                System.out.println("- " + g);
            }
            Genere genere = Genere.valueOf(scanner.nextLine().toUpperCase());

            collezione.aggiungiGioco(new Videogioco(id, titolo, anno, prezzo, piattaforma, ore, genere));
            System.out.println("✅ Videogioco aggiunto!");

        } else if (tipo == 2) {
            System.out.print("Numero giocatori (2-10): ");
            int giocatori = Integer.parseInt(scanner.nextLine());
            System.out.print("Durata media partita (minuti): ");
            int durata = Integer.parseInt(scanner.nextLine());

            collezione.aggiungiGioco(new GiocoDaTavolo(id, titolo, anno, prezzo, giocatori, durata));
            System.out.println("✅ Gioco da tavolo aggiunto!");
        } else {
            System.out.println("⚠️ Scelta non valida.");
        }
    }

    private static void aggiornaGioco(Scanner scanner, Collezione collezione) {
        System.out.print("Inserisci ID del gioco da aggiornare: ");
        String id = scanner.nextLine();

        System.out.println("Vuoi aggiornare a (1) Videogioco o (2) Gioco da Tavolo?");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nuovo titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Prezzo: ");
        double prezzo = Double.parseDouble(scanner.nextLine());

        if (tipo == 1) {
            System.out.print("Piattaforma: ");
            String piattaforma = scanner.nextLine();
            System.out.print("Durata (ore): ");
            int ore = Integer.parseInt(scanner.nextLine());

            System.out.println("Scegli genere: ");
            for (Genere g : Genere.values()) {
                System.out.println("- " + g);
            }
            Genere genere = Genere.valueOf(scanner.nextLine().toUpperCase());

            collezione.aggiornaGioco(id, new Videogioco(id, titolo, anno, prezzo, piattaforma, ore, genere));
            System.out.println("✅ Videogioco aggiornato!");
        } else if (tipo == 2) {
            System.out.print("Numero giocatori (2-10): ");
            int giocatori = Integer.parseInt(scanner.nextLine());
            System.out.print("Durata media partita (minuti): ");
            int durata = Integer.parseInt(scanner.nextLine());

            collezione.aggiornaGioco(id, new GiocoDaTavolo(id, titolo, anno, prezzo, giocatori, durata));
            System.out.println("✅ Gioco da tavolo aggiornato!");
        } else {
            System.out.println("⚠️ Scelta non valida.");
        }
    }
}
