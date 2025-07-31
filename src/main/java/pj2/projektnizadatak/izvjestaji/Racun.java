package pj2.projektnizadatak.izvjestaji;

import pj2.projektnizadatak.Simulacija;
import pj2.projektnizadatak.enumi.StatusRegije;
import pj2.projektnizadatak.iznajmljivanje.Iznajmljivanje;
import pj2.projektnizadatak.vozila.Automobil;
import pj2.projektnizadatak.vozila.Bicikl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa koja predstavlja racun za iznajmljivanje vozila.
 * Obuhvata sve relevantne informacije o iznajmljivanju, ukljucujuci podatke o vozilu, korisniku, lokaciji, popustima, promocijama i ukupnoj cijeni.
 */
public class Racun {

    private LocalDateTime datumVrijeme;
    private String idVozila;
    private String vrstaVozila;
    private String brojVozacke;
    private String pocetnaLokacija;
    private String finalnaLokacija;
    private double ukupnaCijena;
    private String opisPopust;
    private String opisPromocija;
    private String opisKvar;
    private double vrijednostPopusta = 0.0;
    private double vrijednostPromocije = 0.0;
    private String imeKorisnika;
    private String dokumentKorisnika;
    private StatusRegije dioGrada;


    /**
     * Konstruktor koji inicijalizuje racun na osnovu iznajmljivanja.
     *
     * @param i objekat tipa Iznajmljivanje koji sadrži podatke o iznajmljivanju.
     */
    public Racun(Iznajmljivanje i) {
        Random random = new Random();
        datumVrijeme = i.getDatumVrijeme();
        idVozila = i.getVozilo().getIdentifikator();
        vrstaVozila = i.getVozilo().tip();
        imeKorisnika = i.getKorisnik();
        if (random.nextBoolean()) {
            dokumentKorisnika = generisiRandomString(9);
        } else {
            dokumentKorisnika = generisiRandomString(8);
        }
        brojVozacke = generisiRandomString(9);
        pocetnaLokacija = "[ " + i.getXPocetnaLokacija() + ", " + i.getYPocetnaLokacija() + " ]";
        finalnaLokacija = "[ " + i.getXFinalnaLokacija() + ", " + i.getYFinalnaLokacija() + " ]";
        StatusRegije dioGrada = i.provjeraRegije();
        this.dioGrada = dioGrada;

        if (i.isPostojiKvar())
            opisKvar = "Na vozilu se dogodio kvar, pa je za placanje ukupno 0.0. ";
        else
            opisKvar = "";

        if (i.isPostojiPromocija()) {
            if (i.getVozilo() instanceof Automobil) {
                vrijednostPromocije = promocija(i.getTrajanjeVoznje(), Simulacija.CAR_UNIT_PRICE, dioGrada);
            } else if (i.getVozilo() instanceof Bicikl) {
                vrijednostPromocije = promocija(i.getTrajanjeVoznje(), Simulacija.BIKE_UNIT_PRICE, dioGrada);
            } else
                vrijednostPromocije = promocija(i.getTrajanjeVoznje(), Simulacija.SCOOTER_UNIT_PRICE, dioGrada);
        }
        else vrijednostPromocije=0;
        opisPromocija = "Promocija je u iznosu od " + vrijednostPromocije + ". ";

        if (i.getBrojIznajmljivanja()%10==0) {
            if (i.getVozilo() instanceof Automobil) {
                vrijednostPopusta = popust(i.getTrajanjeVoznje(), Simulacija.CAR_UNIT_PRICE, dioGrada);
            } else if (i.getVozilo() instanceof Bicikl) {
                vrijednostPopusta = popust(i.getTrajanjeVoznje(), Simulacija.BIKE_UNIT_PRICE, dioGrada);
            } else
                vrijednostPopusta = popust(i.getTrajanjeVoznje(), Simulacija.SCOOTER_UNIT_PRICE, dioGrada);
        }
        opisPopust = "Popust je u iznosu od " + vrijednostPopusta + ". ";

        if (i.getVozilo() instanceof Automobil) {
            ukupnaCijena = ukupnaCijena(i.isPostojiKvar(), i.getTrajanjeVoznje(), dioGrada, Simulacija.CAR_UNIT_PRICE);

        } else if (i.getVozilo() instanceof Bicikl) {
            ukupnaCijena = ukupnaCijena(i.isPostojiKvar(), i.getTrajanjeVoznje(), dioGrada, Simulacija.BIKE_UNIT_PRICE);

        } else
            ukupnaCijena = ukupnaCijena(i.isPostojiKvar(), i.getTrajanjeVoznje(), dioGrada, Simulacija.SCOOTER_UNIT_PRICE);


    }

    /**
     * Vraca status dijela grada.
     *
     * @return Dio grada.
     */
    public StatusRegije getDioGrada() {
        return dioGrada;
    }


    /**
     * Vraca ID vozila.
     *
     * @return ID vozila.
     */
    public String getIdVozila() {
        return idVozila;
    }

    /**
     * Vraca vrstu vozila.
     *
     * @return Vrsta vozila.
     */
    public String getVrstaVozila() {
        return vrstaVozila;
    }

    /**
     * Vraca ime korisnika.
     *
     * @return Ime korisnika.
     */
    public String getImeKorisnika() {
        return imeKorisnika;
    }

    /**
     * Vraca dokument korisnika.
     *
     * @return Dokument korisnika.
     */
    public String getDokumentKorisnika() {
        return dokumentKorisnika;
    }

    /**
     * Vraca broj vozacke dozvole.
     *
     * @return Broj vozacke dozvole.
     */
    public String getBrojVozacke() {
        return brojVozacke;
    }

    /**
     * Vraca pocetnu lokaciju.
     *
     * @return Pocetna lokacija.
     */
    public String getPocetnaLokacija() {
        return pocetnaLokacija;
    }

    /**
     * Vraca finalnu lokaciju.
     *
     * @return Finalna lokacija.
     */
    public String getFinalnaLokacija() {
        return finalnaLokacija;
    }


    /**
     * Vraca ukupnu cijenu iznajmljivanja.
     *
     * @return Ukupna cijena.
     */
    public double getUkupnaCijena() {
        return ukupnaCijena;
    }

    /**
     * Vraca opis popusta.
     *
     * @return Opis popusta.
     */
    public String getOpisPopust() {
        return opisPopust;
    }

    /**
     * Vraca opis promocija.
     *
     * @return Opis promocija.
     */
    public String getOpisPromocija() {
        return opisPromocija;
    }

    /**
     * Vraca opis kvara.
     *
     * @return Opis kvara.
     */
    public String getOpisKvar() {
        return opisKvar;
    }

    /**
     * Vraca vrijednost popusta.
     *
     * @return Vrijednost popusta.
     */
    public double getVrijednostPopusta() {
        return vrijednostPopusta;
    }

    /**
     * Vraca vrijednost promocije.
     *
     * @return Vrijednost promocije.
     */
    public double getVrijednostPromocije() {
        return vrijednostPromocije;
    }

    /**
     * Vraca datum i vrijeme iznajmljivanja.
     *
     * @return Datum i vrijeme iznajmljivanja.
     */
    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    /**
     * Postavlja datum i vrijeme iznajmljivanja.
     *
     * @param datumVrijeme Novi datum i vrijeme iznajmljivanja.
     */
    public void setDatumVrijeme(LocalDateTime datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    /**
     * Postavlja ID vozila.
     *
     * @param idVozila ID vozila.
     */
    public void setIdVozila(String idVozila) {
        this.idVozila = idVozila;
    }

    /**
     * Postavlja vrstu vozila.
     *
     * @param vrstaVozila Vrsta vozila.
     */
    public void setVrstaVozila(String vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }

    /**
     * Postavlja broj vozacke dozvole.
     *
     * @param brojVozacke Broj vozacke dozvole.
     */
    public void setBrojVozacke(String brojVozacke) {
        this.brojVozacke = brojVozacke;
    }

    /**
     * Postavlja pocetnu lokaciju.
     *
     * @param pocetnaLokacija Pocetna lokacija.
     */
    public void setPocetnaLokacija(String pocetnaLokacija) {
        this.pocetnaLokacija = pocetnaLokacija;
    }

    /**
     * Postavlja finalnu lokaciju.
     *
     * @param finalnaLokacija Finalna lokacija.
     */
    public void setFinalnaLokacija(String finalnaLokacija) {
        this.finalnaLokacija = finalnaLokacija;
    }



    /**
     * Postavlja ukupnu cijenu iznajmljivanja.
     *
     * @param ukupnaCijena Ukupna cijena.
     */
    public void setUkupnaCijena(double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    /**
     * Postavlja opis popusta.
     *
     * @param opisPopust Opis popusta.
     */
    public void setOpisPopust(String opisPopust) {
        this.opisPopust = opisPopust;
    }

    /**
     * Postavlja opis promocija.
     *
     * @param opisPromocija Opis promocija.
     */
    public void setOpisPromocija(String opisPromocija) {
        this.opisPromocija = opisPromocija;
    }

    /**
     * Postavlja opis kvara.
     *
     * @param opisKvar Opis kvara.
     */
    public void setOpisKvar(String opisKvar) {
        this.opisKvar = opisKvar;
    }

    /**
     * Postavlja vrijednost popusta.
     *
     * @param vrijednostPopusta Vrijednost popusta.
     */
    public void setVrijednostPopusta(double vrijednostPopusta) {
        this.vrijednostPopusta = vrijednostPopusta;
    }

    /**
     * Postavlja vrijednost promocije.
     *
     * @param vrijednostPromocije Vrijednost promocije.
     */
    public void setVrijednostPromocije(double vrijednostPromocije) {
        this.vrijednostPromocije = vrijednostPromocije;
    }

    /**
     * Postavlja ime korisnika.
     *
     * @param imeKorisnika Ime korisnika.
     */
    public void setImeKorisnika(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
    }

    /**
     * Postavlja dokument korisnika.
     *
     * @param dokumentKorisnika Dokument korisnika.
     */
    public void setDokumentKorisnika(String dokumentKorisnika) {
        this.dokumentKorisnika = dokumentKorisnika;
    }

    /**
     * Generise nasumicni string sa odredjenom duzinom.
     *
     * @param duzina Duzina stringa.
     * @return Generisani string.
     */
    public static String generisiRandomString(int duzina) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < duzina; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }


    /**
     * Izracunava popust na osnovu trajanja voznje i cijene po jedinici.
     *
     * @param trajanje Trajanje voznje.
     * @param koef Koeficijent za racunanje koji se razlikuje za svaku vrstu vozila.
     * @param dioGrada Dio grada u kojem se voznja obavlja (uzi ili siri).
     * @return Vrijednost popusta.
     */
    public double popust(double trajanje, double koef, StatusRegije dioGrada) {
        double osnovnaCijena = 0.0;
        double iznos = 0.0;
        double udaljenost = 0.0;
        double vrPopusta = 0.0;


        osnovnaCijena = koef * trajanje;
        switch(dioGrada)
        {
            case SIRI:
                udaljenost = Simulacija.DISTANCE_WIDE;
                break;
            case UZI:
                udaljenost = Simulacija.DISTANCE_NARROW;
                break;


        }

        iznos = osnovnaCijena * udaljenost;


        vrPopusta = Simulacija.DISCOUNT * iznos;

        return Math.round(vrPopusta * 100.0) / 100.0;


    }

    /**
     * Izracunava cijenu sa promocijama na osnovu trajanja voznje i cijene po jedinici.
     *
     * @param trajanje Trajanje voznje.
     * @param koef Koeficijent za racunanje koji se razlikuje za svaku vrstu vozila.
     * @param dioGrada Dio grada u kojem se voznja obavlja(uzi ili siri).
     * @return Vrijednost promocije.
     */
    public double promocija(double trajanje, double koef, StatusRegije dioGrada) {
        double osnovnaCijena = 0.0;
        double iznos = 0.0;
        double udaljenost = 0.0;
        double vrPromocije = 0.0;


        osnovnaCijena = koef * trajanje;
        switch(dioGrada)
        {
            case SIRI:
                udaljenost = Simulacija.DISTANCE_WIDE;
                break;
            case UZI:
                udaljenost = Simulacija.DISTANCE_NARROW;
                break;

        }

        iznos = osnovnaCijena * udaljenost;


        vrPromocije = Simulacija.DISCOUNT_PROM * iznos;

        return Math.round(vrPromocije * 100.0) / 100.0;


    }

    /**
     * Izracunava ukupnu cijenu uzimajući u obzir popuste i promocije.
     *
     * @param kvar Informacija (true/false) da li postoji kvar.
     * @param trajanje Trajanje voznje.
     * @param dioGrada Dio grada u kojem se voznja obavlja(uzi ili siri).
     * @param koef Koeficijent za racunanje koji se razlikuje za svaku vrstu vozila.
     * @return Ukupna cijena.
     */
    public double ukupnaCijena(boolean kvar, double trajanje, StatusRegije dioGrada, double koef) {
        double osnovnaCijena = 0.0;
        double iznos = 0.0;
        double udaljenost = 0.0;


        if (kvar) {
            return 0.0;

        } else {

            osnovnaCijena = koef * trajanje;
            switch(dioGrada)
            {
                case SIRI:
                    udaljenost = Simulacija.DISTANCE_WIDE;
                    break;
                case UZI:
                    udaljenost = Simulacija.DISTANCE_NARROW;
                    break;
            }

            iznos = osnovnaCijena * udaljenost;


            double rezultat = iznos - vrijednostPopusta - vrijednostPromocije;
            return Math.round(rezultat * 100.0) / 100.0;


        }


    }


    /**
     * Generise listu racuna na osnovu liste iznajmljivanja.
     * Za svako iznajmljivanje u ulaznoj listi kreira se novi racun i dodaje u izlaznu listu racuna.
     *
     * @param iznajmljivanja Lista objekata tipa {@link Iznajmljivanje} koji sadrže podatke o iznajmljivanjima vozila.
     * @return Lista objekata tipa {@link Racun}.
     */
    public static ArrayList<Racun> listaRacuna(ArrayList<Iznajmljivanje> iznajmljivanja) {
        ArrayList<Racun> racuni = new ArrayList<>();
        for (Iznajmljivanje i : iznajmljivanja) {
            racuni.add(new Racun(i));
        }
        return racuni;
    }


    /**
     * Generise string predstavljen u formatu racuna.
     *
     * @return Predstavljen format racuna.
     */
    @Override
    public String toString() {

        return
                "Datum i vrijeme iznajmljivanja: " + datumVrijeme + "\n" + "---------------------------------------------------------------------------------" + "\nVOZILO" + "\n" +
                        " Id: " + idVozila + "\n" +
                        " Vrsta: " + vrstaVozila + "\n" + "---------------------------------------------------------------------------------" + "\nKORISNIK" + "\n" +
                        " Ime: " + imeKorisnika + "\n" +
                        " Dokument: " + dokumentKorisnika + "\n" +
                        " Broj vozacke dozvole: " + brojVozacke + "\n" + "---------------------------------------------------------------------------------" + "\nLOKACIJA" + "\n" +
                        " Pocetna lokacija: " + pocetnaLokacija + "\n" +
                        " Finalna lokacija: " + finalnaLokacija + "\n" + " Dio grada: " + dioGrada + "\n---------------------------------------------------------------------------------" + "\nDETALJI" + "\n" +
                        opisPopust + "\n" + opisPromocija + "\n" + opisKvar + "\n" + "---------------------------------------------------------------------------------" + "\nUKUPNA CIJENA" + "\n" +
                        ukupnaCijena;
    }


    /**
     * Memorise racun u datoteku.
     *
     * @param i Iznajmljivanje za koje se kreira racun.
     */
    public static void kreirajFajlZaRacun(Iznajmljivanje i) {
        // Formatiranje datuma za naziv podfoldera (samo datum, bez vremena)
        DateTimeFormatter datumFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Formatiranje vremena za naziv fajla
        DateTimeFormatter fajlFormat = DateTimeFormatter.ofPattern("HH-mm");


        Racun racun = new Racun(i);
        // Generisanje naziva podfoldera i fajla
        String datumFolder = racun.datumVrijeme.format(datumFormat);
        String imeFajla = racun.idVozila + "_" + racun.datumVrijeme.format(fajlFormat) + ".txt";

        // Kreiranje podfoldera za datum ako ne postoji
        File folder = new File(Simulacija.FOLDER_RACUNI, datumFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }


            // Kreiranje fajla u zadatom podfolderu
            File fajl = new File(folder, imeFajla);
            try (FileWriter writer = new FileWriter(fajl)) {
                writer.write(racun.toString());
            } catch (IOException e) {
                System.out.println("Greska pri upisu fajla: " + imeFajla);
                e.printStackTrace();
            }

        System.out.println("Racun je sacuvan.");
    }

}
