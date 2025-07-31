package pj2.projektnizadatak.radSaFajlovima;

import pj2.projektnizadatak.Simulacija;
import pj2.projektnizadatak.iznajmljivanje.Iznajmljivanje;
import pj2.projektnizadatak.kvar.Kvar;
import pj2.projektnizadatak.vozila.Automobil;
import pj2.projektnizadatak.vozila.Bicikl;
import pj2.projektnizadatak.vozila.Trotinet;
import pj2.projektnizadatak.vozila.Vozilo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility klasa IznajmljivanjeParser sluzi za parsiranje podataka o iznajmljivanju vozila iz CSV datoteke,
 * kreiranje objekata tipa Iznajmljivanje i njihovo grupisanje i manipulaciju.
 */
public class IznajmljivanjeParser {

    /**
     * Parsira CSV datoteku koja sadrzi informacije o iznajmljivanju i kreira listu objekata Iznajmljivanje.
     * Provjerava ispravnost datuma, vozila, korisnika, koordinata i drugih podataka.
     * Takodje, kreira kvar na vozilu ukoliko je oznaceno da postoji kvar.
     *
     * @return Lista iznajmljivanja koja je sortirana prema datumu i vremenu iznajmljivanja.
     */

    public static ArrayList<Iznajmljivanje> listaIznajmljivanja() {
        ArrayList<Iznajmljivanje> listaIznajmljivanja = new ArrayList<>();
        HashMap<String, Vozilo> mapa=Simulacija.vozila;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(Simulacija.FAJL_IZNAJMLJIVANJA))) {
            String linija;
            //Prvi red preskacemo
            boolean jePrviRed = true;
            while ((linija = br.readLine()) != null) {
                if (jePrviRed) {
                    jePrviRed = false;
                    continue;
                }

                String[] niz = linija.split(",");
                if (niz.length != 10) {
                    System.out.println("Linija ne sadrži 8 kompletnih polja: " + linija);
                    continue;
                }

                LocalDateTime datumVrijeme = null;
                try {
                    datumVrijeme = LocalDateTime.parse(niz[0], format);
                } catch (DateTimeParseException e) {
                    System.out.println("Greška u parsiranju datuma: " + niz[0]);
                    e.printStackTrace();
                    continue;
                }
                LocalDateTime finalDatumVrijeme = datumVrijeme;

                String korisnikId = niz[1];

                if (korisnikId.isEmpty()) {
                    System.out.println("Polje za ID korisnika je prazno. ");
                    continue;
                }

                String voziloId = niz[2];

                // Provjera da li vozilo sa ucitanim ID-jem postoji u mapi vozila
                Vozilo vozilo = mapa.get(voziloId);
                if (vozilo == null) {
                    System.out.println("Vozilo sa ID-jem " + voziloId + " nije pronadjeno.");
                    continue;
                }

                // Provjera da li je vozilo vec iznajmljeno u isto vrijeme
                boolean voziloVecIznajmljeno = listaIznajmljivanja.stream()
                        .anyMatch(izn -> izn.getVozilo().getIdentifikator().equals(voziloId) && izn.getDatumVrijeme().equals(finalDatumVrijeme));
                if (voziloVecIznajmljeno) {
                    System.out.println("Vozilo sa ID-jem " + voziloId + " je vec iznajmljeno u isto vrijeme.");
                    continue;//u ovom slucaju preskacemo liniju smatrajuci je nevalidnom
                }

                //Provjera za format lokacija, pomocu regex obrazaca

                String regex1 = "\"\\d+";
                String regex2="\\d+\"";

                String XpocetnaLokacija="";
                String YpocetnaLokacija="";
                String Xodrediste="";
                String Yodrediste="";
                if(niz[3].matches(regex1)&&niz[4].matches(regex2)&&niz[5].matches(regex1)&&niz[6].matches(regex2))
                {

                     XpocetnaLokacija = niz[3].replace("\"", "").trim();
                     YpocetnaLokacija = niz[4].replace("\"", "").trim();
                     Xodrediste = niz[5].replace("\"", "").trim();
                     Yodrediste = niz[6].replace("\"", "").trim();
                }
                else
                {
                    System.out.println("Greska pri parsiranju lokacija. ");
                    continue;//ukoliko lokacije nisu u zeljenom formatu, preskacemo liniju

                }

                if (XpocetnaLokacija.isEmpty() || YpocetnaLokacija.isEmpty() || Xodrediste.isEmpty() || Yodrediste.isEmpty()) {
                    System.out.println("Prazne koordinate: " + linija);
                    continue;
                }

                int pocetnaX;
                int pocetnaY;
                int odredisteX;
                int odredisteY;
                try {
                    pocetnaX = Integer.parseInt(XpocetnaLokacija);
                    pocetnaY = Integer.parseInt(YpocetnaLokacija);
                    odredisteX = Integer.parseInt(Xodrediste);
                    odredisteY = Integer.parseInt(Yodrediste);
                } catch (NumberFormatException e) {
                    System.out.println("Greska u konverziji koordinata: " + linija);
                    e.printStackTrace();
                    continue;
                }

                // Provjera da li su koordinate unutar opsega
                if (pocetnaX < 0 || pocetnaX > (Simulacija.DIM-1) || pocetnaY < 0 || pocetnaY >(Simulacija.DIM-1) || odredisteX < 0 || odredisteX > (Simulacija.DIM-1) || odredisteY < 0 || odredisteY > (Simulacija.DIM-1)) {
                    System.out.println("Koordinate su van opsega: " + linija);
                    continue;
                }
                int trajanjeVoznje = Integer.parseInt(niz[7]);

                boolean postojiKvar;
                boolean postojiPromocija;

                if ("da".equals(niz[8])) {
                    postojiKvar = true;
                } else {
                    postojiKvar = false;
                }

                if ("da".equals(niz[9])) {
                    postojiPromocija = true;
                } else {
                    postojiPromocija = false;
                }


                if (postojiKvar) {
                    LocalDateTime vrijemeKvara = generateRandomDateTime(finalDatumVrijeme, finalDatumVrijeme.plusSeconds(trajanjeVoznje));

                    // Izracunavanje cijene popravke na osnovu tipa vozila
                    double koeficijent = 0.0;
                    if (vozilo instanceof Automobil) {
                        koeficijent = Simulacija.KOEF_KVARA_AUTOMOBIL;
                    } else if (vozilo instanceof Bicikl) {
                        koeficijent = Simulacija.KOEF_KVARA_BICIKL;
                    } else if (vozilo instanceof Trotinet) {
                        koeficijent = Simulacija.KOEF_KVARA_TROTINET;
                    }
                    double cijenaPopravke = koeficijent * vozilo.getCijenaNabavke();

                    // Kreiranje i postavljanje objekta Kvar
                    Kvar kvar = new Kvar(vrijemeKvara, cijenaPopravke);//opis kvara se kreira direktno u konstruktoru
                    vozilo.dodajKvar(kvar);
                }

                Iznajmljivanje iznajmljivanje = new Iznajmljivanje(datumVrijeme, vozilo, korisnikId, pocetnaX, pocetnaY, odredisteX, odredisteY, trajanjeVoznje, postojiKvar, postojiPromocija,1);
                listaIznajmljivanja.add(iznajmljivanje);
            }


        } catch (IOException e) {
            System.out.println("Greska pri citanju fajla");
            e.printStackTrace();
        }
        //Lista treba biti sortirana po datumu i vremenu, pa to i radimo prije vracanja liste
        Collections.sort(listaIznajmljivanja, Comparator.comparing(Iznajmljivanje::getDatumVrijeme));
        return listaIznajmljivanja;
    }

    /**
     * Azurira broj iznajmljivanja za svakog korisnika u listi objekata Iznajmljivanje.
     * Broji koliko je puta korisnik iznajmljivao vozilo i postavlja taj broj u objektu, zbog toga sto korisnik ostvaruje popust za svako 10-o iznajmljivanje.
     *
     * @param listaIznajmljivanja Lista objekata Iznajmljivanje.
     * @return Azurirana lista objekata Iznajmljivanje sa postavljenim brojem iznajmljivanja za svakog korisnika.
     */
    public static ArrayList<Iznajmljivanje> azurirajBrojIznajmljivanja(ArrayList<Iznajmljivanje> listaIznajmljivanja) {
        // NovA lista za vraćanje rezultata
        ArrayList<Iznajmljivanje> novaLista = new ArrayList<>(listaIznajmljivanja);

        // Iteracija kroz listu od posljednjeg do prvog elementa
        for (int i = listaIznajmljivanja.size() - 1; i >= 0; i--) {
            Iznajmljivanje trenutni = listaIznajmljivanja.get(i);
            String korisnikId = trenutni.getKorisnik();
            int brojIznajmljivanja = 0;

            for (int j = i; j >=0; j--) {
                Iznajmljivanje iznajmljivanje = listaIznajmljivanja.get(j);
                if (iznajmljivanje.getKorisnik().equals(korisnikId)) {
                    brojIznajmljivanja++;
                }
            }

            // Postavljamo tacan broj iznajmljivanja za konkretnog korisnika u trenutnom objektu klase Iznajmljivanje
            trenutni.setBrojIznajmljivanja(brojIznajmljivanja);

        }

        return novaLista;
    }

    /**
     * Generise slucajan datum i vrijeme izmedju dva datuma.
     *
     * @param start Pocetno vrijeme.
     * @param end Kraj vremena.
     * @return Slucajan datum i vrijeme izmedju pocetnog i krajnjeg vremena.
     */
    public static LocalDateTime generateRandomDateTime(LocalDateTime start, LocalDateTime end) {
        long startMillis = start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endMillis = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(randomMillis), ZoneId.systemDefault());
    }

    /**
     * Grupise iznajmljivanja prema vremenu u kojem su se dogodila.
     * Ova metoda koristi TreeMap da grupise iznajmljivanja po vremenu i sortira ih po datumu i vremenu.
     * Rezultat je lista listi, gde svaka unutrasnja lista sadrzi iznajmljivanja za odredjeno vrijeme.
     *
     * @return Lista listi iznajmljivanja, gdje su unutrasnje liste sortirane po vremenu iznajmljivanja.
     */
    public static ArrayList<ArrayList<Iznajmljivanje>> grupisiIznajmljivanjaPoVremenu() {
        Map<LocalDateTime, ArrayList<Iznajmljivanje>> mapaPoVremenu = new TreeMap<>();

        // Prolazimo kroz sva iznajmljivanja i grupišemo ih po vremenu
        for (Iznajmljivanje iznajmljivanje : Simulacija.iznajmljivanja) {
            LocalDateTime vrijeme = iznajmljivanje.getDatumVrijeme();
            mapaPoVremenu.putIfAbsent(vrijeme, new ArrayList<>());
            mapaPoVremenu.get(vrijeme).add(iznajmljivanje);
        }

        // Kreiramo listu listi iznajmljivanja za svako vrijeme
        ArrayList<ArrayList<Iznajmljivanje>> listaIznajmljivanjaPoVremenu = new ArrayList<>();

        // Dodajemo sortirane liste u listu
        for (ArrayList<Iznajmljivanje> lista : mapaPoVremenu.values()) {
            lista.sort(Comparator.comparing(Iznajmljivanje::getDatumVrijeme));
            listaIznajmljivanjaPoVremenu.add(lista);
        }

        return listaIznajmljivanjaPoVremenu;
    }

}
