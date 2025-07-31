package pj2.projektnizadatak.radSaFajlovima;

import pj2.projektnizadatak.Simulacija;
import pj2.projektnizadatak.vozila.Automobil;
import pj2.projektnizadatak.vozila.Bicikl;
import pj2.projektnizadatak.vozila.Trotinet;
import pj2.projektnizadatak.vozila.Vozilo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

/**
 * Utility klasa koja omogucava parsiranje fajla sa podacima o vozilima i kreiranje objekata vozila na osnovu tih podataka.
 */
public class VozilaParser {

    /**
     * Parsira fajl sa podacima o vozilima i vraca mapu u kojoj su kljucevi ID-jevi vozila, a vrijednosti objekti klase Vozilo.
     *
     * @return Mapa u kojoj su kljucevi ID-jevi vozila, a vrijednosti objekti Vozilo.
     */
    public static HashMap<String, Vozilo> mapaVozila() {
        HashMap<String, Vozilo> mapa = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy.");

        try (BufferedReader br = new BufferedReader(new FileReader(Simulacija.FAJL_VOZILA))) {
            String linija;
            //Prvi red preskacemo
            boolean jePrviRed = true;
            while ((linija = br.readLine()) != null) {
                if (jePrviRed) {
                    jePrviRed = false;
                    continue;
                }

                String[] niz = linija.split(",");

                if (niz.length != 9) {
                    System.out.println("Linija ne sadrzi 9 polja: " + linija);
                    continue;//preskacemo liniju uz ispis poruke na konzolu
                }

                String id = niz[0];
                if (id.isEmpty()) {
                    System.out.println("Polje za ID korisnika je prazno. ");
                    continue;
                }
                String proizvodjac = niz[1];
                if (proizvodjac.isEmpty()) {
                    System.out.println("Polje za proizvodjaca je prazno. ");
                    continue;
                }
                String model = niz[2];
                if (model.isEmpty()) {
                    System.out.println("Polje za model je prazno. ");
                    continue;
                }
                LocalDate datumNabavke=null;

                if (!niz[3].isEmpty()) {
                    try {
                        datumNabavke = LocalDate.parse(niz[3], formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Greska u parsiranju datuma: " + niz[3]);
                        e.printStackTrace();
                        continue;
                    }
                }

                double cijena;
                int domet = 0;
                double maxBrzina = 0.0;
                if (!niz[4].isEmpty()) {
                    try {
                        cijena = Double.parseDouble(niz[4]);
                    } catch (NumberFormatException e) {
                        System.out.println("Greska u parsiranju cijene: " + niz[4]);
                        e.printStackTrace();
                        continue;
                    }
                } else {
                    System.out.println("Polje za cijenu je prazno. ");
                    continue;
                }
                if (!niz[5].isEmpty()) {
                    try {
                        domet = Integer.parseInt(niz[5]);
                    } catch (NumberFormatException e) {
                        System.out.println("Greska u parsiranju dometa: " + niz[5]);
                        e.printStackTrace();
                        continue;
                    }
                }
                if (!niz[6].isEmpty()) {
                    try {
                        maxBrzina = Double.parseDouble(niz[6]);
                    } catch (NumberFormatException e) {
                        System.out.println("Greska u parsiranju maksimalne brzine: " + niz[4]);
                        e.printStackTrace();
                        continue;
                    }
                }


                String opis = niz[7];

                String vrsta = niz[8].toLowerCase();
                if (vrsta.isEmpty()) {
                    System.out.println("Polje za vrstu vozila je prazno. ");
                    continue;
                }

                // Provjera duplikata
                if (mapa.containsKey(id)) {
                    System.out.println("Vozilo sa ID-jem: " + id + " vec postoji");
                    continue;
                }

                Vozilo vozilo = null;
                switch (vrsta) {
                    case "automobil":
                            vozilo = new Automobil(id, cijena, proizvodjac, model, datumNabavke, opis);
                            break;
                    case "bicikl":
                            vozilo = new Bicikl(id, cijena, proizvodjac, model, domet);
                            break;
                    case "trotinet":
                            vozilo = new Trotinet(id, cijena, proizvodjac, model, maxBrzina);
                            break;
                    default:
                        System.out.println("Nepoznat tip vozila: " + vrsta);
                        continue;
                }

                mapa.put(id, vozilo);
            }
        } catch (IOException e) {
            System.out.println("Greska pri citanju fajla");
            e.printStackTrace();
        }

        return mapa;
    }


}

