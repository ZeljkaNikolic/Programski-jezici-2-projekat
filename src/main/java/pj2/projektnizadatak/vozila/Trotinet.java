package pj2.projektnizadatak.vozila;
/**
 * Klasa koja predstavlja trotinet i nasledjuje apstraktnu klasu Vozilo.
 * Sadrzi dodatni atribut specifican za trotinet, a to je maksimalna brzina.
 */
public class Trotinet extends Vozilo {

    private double maksimalnaBrzina=0.0;

    /**
     * Konstruktor klase Trotinet.
     *
     * @param identifikator Jedinstveni identifikator trotineta.
     * @param cijenaNabavke Cena po kojoj je trotinet nabavljen.
     * @param proizvodjac Naziv proizvodjaca trotineta.
     * @param model Model trotineta.
     * @param maksimalnaBrzina Maksimalna brzina trotineta.
     */
    public Trotinet(String identifikator, double cijenaNabavke, String proizvodjac, String model, double maksimalnaBrzina) {
        super(identifikator, cijenaNabavke, proizvodjac, model);
        this.maksimalnaBrzina = maksimalnaBrzina;
    }


    /**
     * Vraca tip vozila, u ovom slucaju "Trotinet".
     *
     * @return Tip vozila.
     */
    public String tip() {
        return "Trotinet";
    }

    /**
     * Vraca maksimalnu brzinu trotineta.
     *
     * @return Maksimalna brzina trotineta.
     */
    public double getMaksimalnaBrzina() {
        return maksimalnaBrzina;
    }

    /**
     * Postavlja maksimalnu brzinu trotineta.
     *
     * @param maksimalnaBrzina Maksimalna brzina koju trotinet moze da postigne.
     */
    public void setMaksimalnaBrzina(double maksimalnaBrzina) {
        this.maksimalnaBrzina = maksimalnaBrzina;
    }

    /**
     * Vraca string reprezentaciju trotineta,ukljucujuci informacije o maksimalnoj brzini i detalje iz superklase Vozilo.
     *
     * @return String koji predstavlja trotinet.
     */
    @Override
    public String toString() {
        return super.toString() + "/\n" +"Trotinet{" +
                "maksimalnaBrzina=" + maksimalnaBrzina +
                '}';
    }
}
