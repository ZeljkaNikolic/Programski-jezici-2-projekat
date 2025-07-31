package pj2.projektnizadatak.vozila;

import java.time.LocalDate;
import java.util.Random;


/**
 * Klasa koja predstavlja automobil i nasledjuje apstraktnu klasu Vozilo.
 * Sadrzi dodatne atribute specificne za automobil, kao sto su datum nabavke, opis i mogucnost prevozenja vise ljudi.
 */
public class Automobil extends Vozilo {

    private LocalDate datumNabavke=null;
    private String opis="";
    private int prevozenjeViseLjudi;

    /**
     * Konstruktor klase Automobil.
     *
     * @param identifikator Jedinstveni identifikator automobila.
     * @param cijenaNabavke Cena po kojoj je automobil nabavljen.
     * @param proizvodjac Naziv proizvodjaca automobila.
     * @param model Model automobila.
     * @param datumNabavke Datum kada je automobil nabavljen.
     * @param opis Kratak opis automobila.
     */
    public Automobil(String identifikator, double cijenaNabavke, String proizvodjac, String model, LocalDate datumNabavke, String opis) {
        super(identifikator, cijenaNabavke, proizvodjac, model);
        Random rand = new Random();
        this.datumNabavke = datumNabavke;
        this.opis = opis;
        this.prevozenjeViseLjudi =rand.nextInt(5) +1;

    }

    /**
     * Vraca kapacitet automobila za prevoz vise ljudi.
     *
     * @return Broj ljudi koje automobil moze da prevozi.
     */
    public int getPrevozenjeViseLjudi() {
        return prevozenjeViseLjudi;
    }

    /**
     * Postavlja kapacitet automobila za prevoz vise ljudi.
     *
     * @param prevozenjeViseLjudi Broj ljudi koje automobil moze da prevozi.
     */
    public void setPrevozenjeViseLjudi(int prevozenjeViseLjudi) {
        this.prevozenjeViseLjudi = prevozenjeViseLjudi;
    }

    /**
     * Vraca datum nabavke automobila.
     *
     * @return Datum nabavke automobila.
     */
    public LocalDate getDatumNabavke() {
        return datumNabavke;
    }

    /**
     * Postavlja datum nabavke automobila.
     *
     * @param datumNabavke Datum kada je automobil nabavljen.
     */
    public void setDatumNabavke(LocalDate datumNabavke) {
        this.datumNabavke = datumNabavke;
    }

    /**
     * Vraca opis automobila.
     *
     * @return Opis automobila.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis automobila.
     *
     * @param opis Opis automobila.
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraca tip vozila, u ovom slucaju "Automobil".
     *
     * @return Tip vozila.
     */
    @Override
    public String tip() {
        return "Automobil";
    }

    /**
     * Vraca string reprezentaciju automobila,ukljucujuci informacije o datumu nabavke, opisu, broju ljudi koji se prevoze i detalje iz superklase Vozilo.
     *
     * @return String koji predstavlja automobil.
     */
    @Override
    public String toString() {
        return super.toString() + "/\n" + "Automobil{" +
                "datumNabavke=" + datumNabavke +
                ", opis='" + opis +
                ", broj ljudi='" + prevozenjeViseLjudi +
                '}';
    }
}
