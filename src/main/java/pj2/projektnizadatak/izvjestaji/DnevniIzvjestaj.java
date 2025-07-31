package pj2.projektnizadatak.izvjestaji;

import pj2.projektnizadatak.Simulacija;
import pj2.projektnizadatak.kvar.Kvar;
import pj2.projektnizadatak.vozila.Vozilo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Predstavlja dnevni izvjestaj koji sadrzi finansijske podatke za odredjeni datum.
 * Izvjestaj ukljucuje ukupne prihode, popuste, promocije, troskove odrzavanja i popravki kvarova.
 */
public class DnevniIzvjestaj {

    private LocalDate datum;
    private double ukupanPrihod = 0.0;
    private double ukupanPopust = 0.0;
    private double ukupnoPromocije = 0.0;
    private double iznosUzegDijela = 0.0;
    private double iznosSiregDijela = 0.0;
    private double iznosOdrzavanja = 0.0;
    private double iznosPopravkeKvarova = 0.0;

    /**
     * Kreira novi dnevni izvjestaj za dati datum na osnovu liste racuna.
     * @param datum Datum za koji se pravi izvjestaj.
     * @param racuni Lista racuna koji se koriste za izracunavanje podataka u izvjestaju.
     */
    public DnevniIzvjestaj(LocalDate datum, List<Racun> racuni) {
        this.datum = datum;
        for (Racun r : racuni) {
            this.ukupanPrihod += Math.round(r.getUkupnaCijena() * 100.0) / 100.0;
            this.ukupanPopust += Math.round(r.getVrijednostPopusta() * 100.0) / 100.0;
            this.ukupnoPromocije += Math.round(r.getVrijednostPromocije() * 100.0) / 100.0;
            switch(r.getDioGrada())
            {
                case SIRI:
                    this.iznosSiregDijela += Math.round(r.getUkupnaCijena() * 100.0) / 100.0;
                    break;
                case UZI:
                    this.iznosUzegDijela += Math.round(r.getUkupnaCijena() * 100.0) / 100.0;
                    break;

            }


            this.iznosPopravkeKvarova += Math.round(cijenaKvara(r.getIdVozila(), datum)*100.0)/100.0;

        }
        this.iznosOdrzavanja = Math.round(ukupanPrihod * 0.2 * 100.0) / 100.0;



    }

    /**
     * Vraca datum za koji je napravljeni izvjestaj.
     *
     * @return Datum izvjestaja.
     */
    public LocalDate getDatum() {
        return datum;
    }

    /**
     * Vraca ukupni prihod za dati datum.
     *
     * @return Ukupan prihod.
     */
    public double getUkupanPrihod() {
        return ukupanPrihod;
    }

    /**
     * Vraca ukupnu vrijednost popusta za dati datum.
     *
     * @return Ukupan popust.
     */
    public double getUkupanPopust() {
        return ukupanPopust;
    }

    /**
     * Vraca ukupnu vrijednost promocija za dati datum.
     *
     * @return Ukupno promocije.
     */
    public double getUkupnoPromocije() {
        return ukupnoPromocije;
    }

    /**
     * Vraca iznos za uzi dio grada za dati datum.
     *
     * @return Iznos uzeg dijela grada.
     */
    public double getIznosUzegDijela() {
        return iznosUzegDijela;
    }

    /**
     * Vraca iznos za siri dio grada za dati datum.
     *
     * @return Iznos sireg dijela grada.
     */
    public double getIznosSiregDijela() {
        return iznosSiregDijela;
    }

    /**
     * Vraca iznos troskova odrzavanja za dati datum.
     *
     * @return Iznos odrzavanja.
     */
    public double getIznosOdrzavanja() {
        return iznosOdrzavanja;
    }

    /**
     * Vraća iznos troskova popravke kvarova za dati datum.
     *
     * @return Iznos popravke kvarova.
     */
    public double getIznosPopravkeKvarova() {
        return iznosPopravkeKvarova;
    }

    /**
     * Postavlja datum za izvjestaj.
     *
     * @param datum Datum izvjestaja.
     */
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    /**
     * Postavlja ukupni prihod za izvjestaj.
     *
     * @param ukupanPrihod Ukupan prihod.
     */
    public void setUkupanPrihod(double ukupanPrihod) {
        this.ukupanPrihod = ukupanPrihod;
    }

    /**
     * Postavlja ukupnu vrijednost popusta za izvjestaj.
     *
     * @param ukupanPopust Ukupan popust.
     */
    public void setUkupanPopust(double ukupanPopust) {
        this.ukupanPopust = ukupanPopust;
    }

    /**
     * Postavlja ukupnu vrijednost promocija za izvjestaj.
     *
     * @param ukupnoPromocije Ukupno promocije.
     */
    public void setUkupnoPromocije(double ukupnoPromocije) {
        this.ukupnoPromocije = ukupnoPromocije;
    }

    /**
     * Postavlja iznos za uzi dio grada za izvjestaj.
     *
     * @param iznosUzegDijela Iznos uzeg dijela grada.
     */
    public void setIznosUzegDijela(double iznosUzegDijela) {
        this.iznosUzegDijela = iznosUzegDijela;
    }

    /**
     * Postavlja iznos za siri dio grada za izvjestaj.
     *
     * @param iznosSiregDijela Iznos sireg dijela grada.
     */
    public void setIznosSiregDijela(double iznosSiregDijela) {
        this.iznosSiregDijela = iznosSiregDijela;
    }

    /**
     * Postavlja iznos troskova odrzavanja za izvjestaj.
     *
     * @param iznosOdrzavanja Iznos odrzavanja.
     */
    public void setIznosOdrzavanja(double iznosOdrzavanja) {
        this.iznosOdrzavanja = iznosOdrzavanja;
    }

    /**
     * Postavlja iznos troskova popravke kvarova za izvjestaj.
     *
     * @param iznosPopravkeKvarova Iznos popravke kvarova.
     */
    public void setIznosPopravkeKvarova(double iznosPopravkeKvarova) {
        this.iznosPopravkeKvarova = iznosPopravkeKvarova;
    }

    /**
     * Kreira listu dnevnih izvještaja na osnovu svih racuna u simulaciji, grupisanih po datumu.
     *
     * @return Lista dnevnih izvjestaja za svaki datum.
     */
    public static ArrayList<DnevniIzvjestaj> listaIzvjestaja() {
        // Grupisanje racuna po datumu
        Map<LocalDate, List<Racun>> racuniPoDatumu = Simulacija.racuni.stream()
                .collect(Collectors.groupingBy(racun -> racun.getDatumVrijeme().toLocalDate()));

        // Kreiranje liste izvještaja za svaki datum
        ArrayList<DnevniIzvjestaj> izvjestaji = new ArrayList<>();

        for (Map.Entry<LocalDate, List<Racun>> entry : racuniPoDatumu.entrySet()) {
            LocalDate datum = entry.getKey();
            List<Racun> racuniZaDatum = entry.getValue();

            // Kreiranje dnevnog izvještaja za trenutni datum
            DnevniIzvjestaj izvjestaj = new DnevniIzvjestaj(datum, racuniZaDatum);
            izvjestaji.add(izvjestaj);
        }

        return izvjestaji;
    }

/**
 * Izracunava cijenu popravke kvarova za vozilo na osnovu ID-a vozila i datuma.
 *
 * @param idVozila ID vozila za koje se traze kvarovi.
 * @param datum Datum za koji se traze kvarovi.
 * @return Ukupna cijena popravke kvara
 */
    public double cijenaKvara(String idVozila, LocalDate datum) {
        // Pretraga vozila po ID-ju u mapi vozila
        Vozilo vozilo = Simulacija.vozila.get(idVozila);

        // Ako vozilo sa datim ID-jem ne postoji, vrati 0
        if (vozilo == null) {
            System.out.println("Vozilo sa ID-jem " + idVozila + " nije pronađeno.");
            return 0.0;
        }

        // Dobijanje liste kvarova za pronađeno vozilo
        ArrayList<Kvar> kvarovi = vozilo.getKvarovi();
        double rezultat=0.0;
        // Pretraga kroz listu kvarova
        for (Kvar kvar : kvarovi) {
            if(kvar.getDatumVrijeme().toLocalDate().equals(datum)){
           rezultat+= kvar.getCijena();}
        }
        return rezultat;
    }

    /**
     * Vraca string reprezentaciju objekta `DnevniIzvjestaj` u formatu koji sadrzi sve kljucne informacije
     * o izvjestaju. Formatirani string ukljucuje datum izvjestaja, ukupne prihode, popuste, promocije,
     * iznose za uzi i siri dio grada, troskove odrzavanja i troskove popravke kvarova.
     *
     * @return String reprezentacija objekta `DnevniIzvjestaj`, ukljucujuci sve relevantne atribute.
     */
    @Override
    public String toString() {
        return "DnevniIzvjestaj{" +
                "datum=" + datum +
                ", ukupanPrihod=" + ukupanPrihod +
                ", ukupanPopust=" + ukupanPopust +
                ", ukupnoPromocije=" + ukupnoPromocije +
                ", iznosUzegDijela=" + iznosUzegDijela +
                ", iznosSiregDijela=" + iznosSiregDijela +
                ", iznosOdrzavanja=" + iznosOdrzavanja +
                ", iznosPopravkeKvarova=" + iznosPopravkeKvarova +
                '}';
    }
}
