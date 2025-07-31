package pj2.projektnizadatak.kvar;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Predstavlja kvar na vozilu.
 * Ova klasa sadrzi informacije o kvaru, ukljcujuci opis, datum i vrijeme kada se kvar desio, i cijenu popravke.
 * Takodjee, automatski generise nasumican opis kvara prilikom kreiranja objekta.
 */
public class Kvar implements Serializable {

    private String opis;
    private LocalDateTime datumVrijeme;
    private double cijena;


    private static final String[] OPISI_KVAROVA = {
            "Problem sa baterijom",
            "Problem sa motorom",
            "Pucanje gume",
            "Elektricni kvar",
            "Problem sa kocnicama"
    };

    /**
     * Kreira novi objekat klase Kvar sa zadatim datumom i vremenom, i cijenom popravke.
     * Nasumicno dodjeljuje opis kvaru iz unaprijed definisanog niza opisa kvarova.
     *
     * @param datumVrijeme Datum i vrijeme kada se kvar desio.
     * @param cijena Cijena popravke kvara.
     */
    public Kvar(LocalDateTime datumVrijeme, double cijena) {
        this.opis = getRandomOpis();
        this.datumVrijeme = datumVrijeme;
        this.cijena = cijena;
    }

    /**
     * Generise nasumican opis kvara iz niza mogucih opisa.
     *
     * @return Nasumican opis kvara.
     */
    private String getRandomOpis() {
        Random random = new Random();
        int index = random.nextInt(OPISI_KVAROVA.length);
        return OPISI_KVAROVA[index];
    }

    /**
     * Vraca cijenu popravke kvara.
     *
     * @return Cijena popravke kvara.
     */
    public double getCijena() {
        return cijena;
    }

    /**
     * Postavlja cijenu popravke kvara.
     *
     * @param cijena Nova cijena popravke kvara.
     */
    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    /**
     * Vraca opis kvara.
     *
     * @return Opis kvara.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis kvara.
     *
     * @param opis Novi opis kvara.
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraca datum i vrijeme kada se kvar desio.
     *
     * @return Datum i vrijeme kvara.
     */
    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    /**
     * Postavlja datum i vrijeme kada se kvar desio.
     *
     * @param datumVrijeme Novi datum i vrijeme kvara.
     */
    public void setDatumVrijeme(LocalDateTime datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    /**
     * Vraca string reprezentaciju objekta Kvar.
     *
     * @return String koji opisuje kvar, ukljucujuci opis, datum i vrijeme i cijenu popravke.
     */
    @Override
    public String toString() {
        return "Kvar: " + opis + " se desio: " + datumVrijeme + ". Za popravku je potrebno: " + cijena;
    }
}
