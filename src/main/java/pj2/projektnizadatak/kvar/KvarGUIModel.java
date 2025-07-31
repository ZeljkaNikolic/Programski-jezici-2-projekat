package pj2.projektnizadatak.kvar;

import java.time.LocalDateTime;

/**
 * Model za prikaz informacija o kvaru u korisnickom interfejsu.
 * Ova klasa sadrzi podatke o kvaru kao sto su ID vozila, vrsta vozila, vrijeme kada se kvar desio,
 * opis i cijena popravke.
 */
public class KvarGUIModel {
    private String id;
    private String vrsta;
    private LocalDateTime vrijeme;
    private String opis;
    private double cijena;

    /**
     * Kreira novi objekat KvarGUIModel sa zadatim atributima.
     *
     * @param id ID kvara.
     * @param vrsta Vrsta kvara.
     * @param vrijeme Datum i vrijeme kada se kvar desio.
     * @param opis Opis kvara.
     * @param cijena Cijena popravke kvara.
     */
    public KvarGUIModel(String id, String vrsta, LocalDateTime vrijeme, String opis, double cijena) {
        this.id = id;
        this.vrsta = vrsta;
        this.vrijeme = vrijeme;
        this.opis = opis;
        this.cijena = cijena;
    }

    /**
     * Postavlja ID kvara.
     *
     * @param id Novi ID kvara.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Postavlja vrstu vozila.
     *
     * @param vrsta Vrsta vozila.
     */
    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    /**
     * Postavlja datum i vrijeme kada se kvar desio.
     *
     * @param vrijeme Novi datum i vrijeme kvara.
     */
    public void setVrijeme(LocalDateTime vrijeme) {
        this.vrijeme = vrijeme;
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
     * Postavlja cijenu popravke kvara.
     *
     * @param cijena Nova cijena popravke kvara.
     */
    public void setCijena(double cijena) {
        this.cijena = cijena;
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
     * Vraca ID vozila.
     *
     * @return ID vozila.
     */
    public String getId() {
        return id;
    }

    /**
     * Vraća vrstu vozila.
     *
     * @return Vrsta vozila.
     */
    public String getVrsta() {
        return vrsta;
    }

    /**
     * Vraca datum i vrijeme kada se kvar desio.
     *
     * @return Datum i vrijeme kvara.
     */
    public LocalDateTime getVrijeme() {
        return vrijeme;
    }

    /**
     * Vraca opis kvara.
     *
     * @return Opis kvara.
     */
    public String getOpis() {
        return opis;
    }
}

