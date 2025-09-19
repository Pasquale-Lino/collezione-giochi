package com.example.collezione.util;

import com.example.collezione.model.*;
import com.example.collezione.service.Collezione;
import com.example.collezione.exception.*;

import net.datafaker.Faker;
import java.util.Locale;

public class FakeDataGenerator {

    public static void popolaCollezione(Collezione collezione, int numVideogiochi, int numGiochiDaTavolo) {
        Faker faker = new Faker(new Locale("it"));

        // Videogiochi fake
        for (int i = 1; i <= numVideogiochi; i++) {
            String id = "VG" + i;
            String titolo = faker.app().name() + " " + faker.number().numberBetween(1, 100);
            int anno = faker.number().numberBetween(2000, 2025);
            double prezzo = faker.number().randomDouble(2, 10, 100);
            String piattaforma = faker.options().option("PC", "PS5", "XBox", "Switch");
            int durata = faker.number().numberBetween(1, 100);
            Genere genere = Genere.values()[faker.number().numberBetween(0, Genere.values().length)];

            try {
                collezione.aggiungiGioco(new Videogioco(id, titolo, anno, prezzo, piattaforma, durata, genere));
            } catch (GiocoDuplicatoException e) {
                i--; // rigenera se ID duplicato
            }
        }

        // Giochi da tavolo fake
        for (int i = 1; i <= numGiochiDaTavolo; i++) {
            String id = "GT" + i;
            String titolo = faker.book().title();
            int anno = faker.number().numberBetween(1950, 2025);
            double prezzo = faker.number().randomDouble(2, 5, 80);
            int numGiocatori = faker.number().numberBetween(2, 10);
            int durataMinuti = faker.number().numberBetween(30, 180);

            try {
                collezione.aggiungiGioco(new GiocoDaTavolo(id, titolo, anno, prezzo, numGiocatori, durataMinuti));
            } catch (GiocoDuplicatoException e) {
                i--; // rigenera se ID duplicato
            }
        }
    }
}
