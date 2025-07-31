package pj2.projektnizadatak.vozila;
/**
 * Klasa koja predstavlja bicikl i nasledjuje apstraktnu klasu Vozilo.
 * Sadrzi dodatne atribut specifican za bicikl, a to je domet sa jednim punjenjem baterije.
 */
public class Bicikl extends Vozilo {
    private int dometSaJednimPunjenjem=0;

    /**
     * Konstruktor klase Bicikl.
     *
     * @param identifikator Jedinstveni identifikator bicikla.
     * @param cijenaNabavke Cena po kojoj je bicikl nabavljen.
     * @param proizvodjac Naziv proizvodjaca bicikla.
     * @param model Model bicikla.
     * @param dometSaJednimPunjenjem Domet bicikla sa jednim punjenjem baterije.
     */
    public Bicikl(String identifikator, double cijenaNabavke, String proizvodjac, String model, int dometSaJednimPunjenjem) {
        super(identifikator, cijenaNabavke, proizvodjac, model);
        this.dometSaJednimPunjenjem = dometSaJednimPunjenjem;
    }

    /**
     * Vraca tip vozila, u ovom slucaju "Bicikl".
     *
     * @return Tip vozila.
     */
    public String tip() {
        return "Bicikl";
    }

    /**
     * Vraca domet bicikla sa jednim punjenjem baterije.
     *
     * @return Domet bicikla sa jednim punjenjem.
     */
    public int getDometSaJednimPunjenjem() {
        return dometSaJednimPunjenjem;
    }

    /**
     * Postavlja domet bicikla sa jednim punjenjem baterije.
     *
     * @param dometSaJednimPunjenjem Novi domet bicikla sa jednim punjenjem.
     */
    public void setDometSaJednimPunjenjem(int dometSaJednimPunjenjem) {
        this.dometSaJednimPunjenjem = dometSaJednimPunjenjem;
    }

    /**
     * Vraca string reprezentaciju bicikla, ukljucujuci informacije o dometu i detalje iz superklase Vozilo.
     *
     * @return String reprezentacija bicikla.
     */
    @Override
    public String toString() {
        return super.toString() + "/\n" + "Bicikl{" +
                "dometSaJednimPunjenjem=" + dometSaJednimPunjenjem +
                '}';
    }


}
