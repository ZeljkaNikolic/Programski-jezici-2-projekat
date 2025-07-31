package pj2.projektnizadatak.izvjestaji;

import java.util.ArrayList;
/**
 * Predstavlja finalni izvjestaj koji sumira podatke iz dnevnih izvjestaja za odredjeni period.
 * Ovaj izvjestaj ukljucuje ukupne prihode, popuste, promocije, iznose za uzi i siri dio grada,
 * troskove odrzavanja, troskove popravke kvarova, ukupne troskove i poreze.
 */
public class SumarniIzvjestaj {
    private double ukupanPrihod = 0.0;
    private double ukupanPopust = 0.0;
    private double ukupnoPromocije = 0.0;
    private double iznosUzegDijela = 0.0;
    private double iznosSiregDijela = 0.0;
    private double iznosOdrzavanja = 0.0;
    private double iznosPopravkeKvarova = 0.0;
    private double ukupanTrosak ;
    private double ukupanPorez ;

    /**
     * Kreira suamrni izvjestaj na osnovu liste dnevnih izvjestaja.
     * Izracunava ukupne prihode, popuste, promocije, iznose za uzi i siri dio grada,
     * troskove odrzavanja, troskove popravke kvarova, ukupne troskove i poreze.
     *
     * @param dnevniIzvjestaji Lista dnevnih izvjestaja iz kojih se izvode podaci za sumarni izvjestaj.
     */
    public SumarniIzvjestaj(ArrayList<DnevniIzvjestaj> dnevniIzvjestaji) {

        for (DnevniIzvjestaj d : dnevniIzvjestaji) {
            this.ukupanPrihod += Math.round(d.getUkupanPrihod() * 100.0) / 100.0;
            this.ukupanPopust += Math.round(d.getUkupanPopust() * 100.0) / 100.0;
            this.ukupnoPromocije += Math.round(d.getUkupnoPromocije() * 100.0) / 100.0;

            this.iznosSiregDijela += Math.round(d.getIznosSiregDijela() * 100.0) / 100.0;
            this.iznosUzegDijela += Math.round(d.getIznosUzegDijela() * 100.0) / 100.0;

            this.iznosPopravkeKvarova += Math.round(d.getIznosPopravkeKvarova() * 100.0) / 100.0;
            this.iznosOdrzavanja += Math.round(d.getIznosOdrzavanja() * 100.0) / 100.0;



        }
        this.ukupanTrosak = Math.round(ukupanPrihod *0.2* 100.0) / 100.0;
        this.ukupanPorez = (Math.round((ukupanPrihod-iznosOdrzavanja-iznosPopravkeKvarova-ukupanTrosak) *0.01* 100.0) / 100.0);


    }

    /**
     * Vraca ukupni prihod za sve dnevne izvjestaje.
     *
     * @return Ukupan prihod.
     */
    public double getUkupanPrihod() {
        return ukupanPrihod;
    }

    /**
     * Vraca ukupan popust za sve dnevne izvjestaje.
     *
     * @return Ukupan popust.
     */
    public double getUkupanPopust() {
        return ukupanPopust;
    }

    /**
     * Vraca ukupnu vrijednost promocija za sve dnevne izvjestaje.
     *
     * @return Ukupna vrijednost promocija.
     */
    public double getUkupnoPromocije() {
        return ukupnoPromocije;
    }

    /**
     * Vraca iznos za uzi dio grada.
     *
     * @return Iznos za uzi dio grada.
     */
    public double getIznosUzegDijela() {
        return iznosUzegDijela;
    }

    /**
     * Vraca iznos za siri dio grada.
     *
     * @return Iznos za siri dio grada.
     */
    public double getIznosSiregDijela() {
        return iznosSiregDijela;
    }

    /**
     * Vraca iznos troskova odrzavanja za sve dnevne izvjestaje.
     *
     * @return Iznos troskova odrzavanja.
     */
    public double getIznosOdrzavanja() {
        return iznosOdrzavanja;
    }
    /**
     * Vraća iznos troskova popravke kvarova za sve dnevne izvjestaje.
     *
     * @return Iznos troskova popravke kvarova.
     */
    public double getIznosPopravkeKvarova() {
        return iznosPopravkeKvarova;
    }

    /**
     * Vraca ukupan trosak za sva iznajmljivanja.
     *
     * @return Ukupan trosak.
     */
    public double getUkupanTrosak() {
        return ukupanTrosak;
    }

    /**
     * Vraca ukupan porez za sva iznajmljivanja.
     *
     * @return Ukupan porez.
     */
    public double getUkupanPorez() {
        return ukupanPorez;
    }

    /**
     * Postavlja ukupni prihod za sumarni izvjestaj.
     *
     * @param ukupanPrihod Ukupan prihod.
     */
    public void setUkupanPrihod(double ukupanPrihod) {
        this.ukupanPrihod = ukupanPrihod;
    }

    /**
     * Postavlja ukupan popust za sumarni izvjestaj.
     *
     * @param ukupanPopust Ukupan popust.
     */
    public void setUkupanPopust(double ukupanPopust) {
        this.ukupanPopust = ukupanPopust;
    }

    /**
     * Postavlja ukupnu vrijednost promocija za sumarni izvjestaj.
     *
     * @param ukupnoPromocije Ukupna vrijednost promocija.
     */
    public void setUkupnoPromocije(double ukupnoPromocije) {
        this.ukupnoPromocije = ukupnoPromocije;
    }

    /**
     * Postavlja iznos za uzi dio grada.
     *
     * @param iznosUzegDijela Iznos za uzi dio grada.
     */
    public void setIznosUzegDijela(double iznosUzegDijela) {
        this.iznosUzegDijela = iznosUzegDijela;
    }

    /**
     * Postavlja iznos za siri dio grada.
     *
     * @param iznosSiregDijela Iznos za siri dio grada.
     */
    public void setIznosSiregDijela(double iznosSiregDijela) {
        this.iznosSiregDijela = iznosSiregDijela;
    }

    /**
     * Postavlja iznos troskova odrzavanja za suamrni izvještaj.
     *
     * @param iznosOdrzavanja Iznos troskova odrzavanja.
     */
    public void setIznosOdrzavanja(double iznosOdrzavanja) {
        this.iznosOdrzavanja = iznosOdrzavanja;
    }

    /**
     * Postavlja iznos troskova popravke kvarova za sumarni izvjestaj.
     *
     * @param iznosPopravkeKvarova Iznos troskova popravke kvarova.
     */
    public void setIznosPopravkeKvarova(double iznosPopravkeKvarova) {
        this.iznosPopravkeKvarova = iznosPopravkeKvarova;
    }

    /**
     * Postavlja ukupan trosak za sumarni izvjestaj.
     *
     * @param ukupanTrosak Ukupan trosak.
     */
    public void setUkupanTrosak(double ukupanTrosak) {
        this.ukupanTrosak = ukupanTrosak;
    }

    /**
     * Postavlja ukupan porez za sumarni izvjestaj.
     *
     * @param ukupanPorez Ukupan porez.
     */
    public void setUkupanPorez(double ukupanPorez) {
        this.ukupanPorez = ukupanPorez;
    }
/**
 * Vraca string reprezentaciju objekta `SumarniIzvjestaj` koja ukljucuje sve atribute,
 * kao što su ukupni prihod, popust, promocije, iznos za uzi i siri dio grada, troskove odrzavanja,
 * troskove popravke kvarova, ukupne troskove i porez.
 *
 * @return String reprezentacija objekta `SumarniIzvjestaj'.
 */
    @Override
    public String toString() {
        return "SumarniIzvjestaj{" +

                " ukupanPrihod=" + ukupanPrihod +
                ", ukupanPopust=" + ukupanPopust +
                ", ukupnoPromocije=" + ukupnoPromocije +
                ", iznosUzegDijela=" + iznosUzegDijela +
                ", iznosSiregDijela=" + iznosSiregDijela +
                ", iznosOdrzavanja=" + iznosOdrzavanja +
                ", iznosPopravkeKvarova=" + iznosPopravkeKvarova +
                ", ukupanTrosak=" + ukupanTrosak +
                ", ukupanPorez=" + ukupanPorez +
                '}';
    }
}
