package pj2.projektnizadatak.vozila;

import pj2.projektnizadatak.interfejsi.PunjenjeBaterije;
import pj2.projektnizadatak.kvar.Kvar;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Apstraktna klasa koja predstavlja osnovne karakteristike i ponasanje vozila u sistemu.
 * Svako vozilo ima identifikator, cijenu nabavke, proizvodjaca, model, trenutni nivo baterije
 * i listu kvarova koji mogu nastati tokom upotrebe. Ova klasa implementira interfejs
 * PunjenjeBaterije i Serializable.
 */
 public abstract class Vozilo implements PunjenjeBaterije, Serializable {

    private String identifikator;
    private double cijenaNabavke;
    private String proizvodjac;
    private String model;
    private double trenutniNivoBaterije;
    private ArrayList<Kvar> kvarovi; // vozilo moze da se pokvari vise od jednom
    private double ukupnoKvara=0.0; //atribut potreban za dodatnu funkcionalnost - GUI

    /**
     * Konstruktor koji inicijalizuje vozilo sa osnovnim karakteristikama.
     *
     * @param identifikator Jedinstveni identifikator vozila.
     * @param cijenaNabavke Cijena nabavke vozila.
     * @param proizvodjac Proizvodjac vozila.
     * @param model Model vozila.
     */
    public Vozilo(String identifikator, double cijenaNabavke, String proizvodjac, String model) {
        this.identifikator = identifikator;
        this.cijenaNabavke = cijenaNabavke;
        this.proizvodjac = proizvodjac;
        this.model = model;
        this.kvarovi = new ArrayList<>();
        punjenjeBaterije(); // pri inicijalizaciji nivo baterije je 100%

    }

    /**
     * Postavlja listu kvarova za vozilo.
     *
     * @param kvarovi Lista kvarova koja se dodaje vozilu.
     */
    public void setKvarovi(ArrayList<Kvar> kvarovi) {
        this.kvarovi = kvarovi;
    }

    /**
     * Vraca ukupnu cijenu svih kvarova na vozilu.
     *
     * @return Ukupna cijena kvarova.
     */
    public double getUkupnoKvara() {
        return ukupnoKvara;
    }

    /**
     * Postavlja ukupnu cijenu kvarova na vozilu.
     *
     * @param ukupnoKvara Ukupna cijena kvarova.
     */
    public void setUkupnoKvara(double ukupnoKvara) {
        this.ukupnoKvara = ukupnoKvara;
    }

    /**
     * Vraca identifikator vozila.
     *
     * @return Identifikator vozila.
     */
    public String getIdentifikator() {
        return identifikator;
    }

    /**
     * Postavlja identifikator vozila.
     *
     * @param identifikator Jedinstveni identifikator vozila.
     */
    public void setIdentifikator(String identifikator) {
        this.identifikator = identifikator;
    }

    /**
     * Vraca cijenu nabavke vozila.
     *
     * @return Cijena nabavke vozila.
     */
    public double getCijenaNabavke() {
        return cijenaNabavke;
    }

    /**
     * Postavlja cijenu nabavke vozila.
     *
     * @param cijenaNabavke Cijena nabavke vozila.
     */
    public void setCijenaNabavke(double cijenaNabavke) {
        this.cijenaNabavke = cijenaNabavke;
    }

    /**
     * Vraca proizvodjaca vozila.
     *
     * @return Proizvodjac vozila.
     */
    public String getProizvodjac() {
        return proizvodjac;
    }

    /**
     * Postavlja proizvodjaca vozila.
     *
     * @param proizvodjac Proizvodjac vozila.
     */
    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    /**
     * Vraca model vozila.
     *
     * @return Model vozila.
     */
    public String getModel() {
        return model;
    }

    /**
     * Postavlja model vozila.
     *
     * @param model Model vozila.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Vraca trenutni nivo baterije vozila.
     *
     * @return Trenutni nivo baterije.
     */
    public double getTrenutniNivoBaterije() {
        return trenutniNivoBaterije;
    }

    /**
     * Postavlja trenutni nivo baterije vozila.
     *
     * @param trenutniNivoBaterije Novi nivo baterije.
     */
    public void setTrenutniNivoBaterije(double trenutniNivoBaterije) {
        this.trenutniNivoBaterije = trenutniNivoBaterije;
    }

    /**
     * Vraca listu kvarova na vozilu.
     *
     * @return Lista kvarova.
     */
    public ArrayList<Kvar> getKvarovi() {
        return kvarovi;
    }

    /**
     * Dodaje novi kvar vozilu.
     *
     * @param kvar Kvar koji se dodaje.
     */
    // Dodavanje novog kvara u listu
    public void dodajKvar(Kvar kvar) {
        if (kvar != null && !this.kvarovi.contains(kvar)) {
            this.kvarovi.add(kvar);
        }
    }

    /**
     * Apstraktna metoda koja vraća tip vozila.
     *
     * @return Tip vozila.
     */
    public abstract  String tip();

    /**
     * Vraca string reprezentaciju vozila.
     *
     * @return Tekstualni opis vozila.
     */
    @Override
    public String toString() {
        return "Vozilo{" +
                "identifikator='" + identifikator + '\'' +
                ", cijenaNabavke=" + cijenaNabavke +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", model='" + model + '\'' +
                ", trenutniNivoBaterije=" + trenutniNivoBaterije +
                ", kvarovi=" + kvarovi +
                '}';
    }

    /**
     * Puni bateriju vozila na 100%.
     * Ova metoda je implementacija interfejsa PunjenjeBaterije.
     */
    @Override
    public void punjenjeBaterije() {
        this.trenutniNivoBaterije = 100.0;
    }

    /**
     * Smanjuje nivo baterije za jedan procenat pri svakom pozivu.
     */
    public void praznjenjeBaterije() {
        this.trenutniNivoBaterije--;
    }
}
