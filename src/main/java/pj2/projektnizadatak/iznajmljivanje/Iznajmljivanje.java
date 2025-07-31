package pj2.projektnizadatak.iznajmljivanje;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import pj2.projektnizadatak.ControllerPristup;
import pj2.projektnizadatak.Simulacija;
import pj2.projektnizadatak.enumi.StatusRegije;
import pj2.projektnizadatak.enumi.StatusVozila;
import pj2.projektnizadatak.izvjestaji.Racun;
import pj2.projektnizadatak.vozila.Vozilo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa Iznajmljivanje predstavlja proces iznajmljivanja vozila i upravlja njegovim kretanjem
 * na mapi. Nasljedjuje Thread klasu za implementaciju konkurentnog izvrsavanja simulacije.
 */
public class Iznajmljivanje extends Thread {
    private LocalDateTime datumVrijeme;
    private Vozilo vozilo;
    private String korisnik;
    private int XpocetnaLokacija;
    private int YpocetnaLokacija;
    private int XfinalnaLokacija;
    private int YfinalnaLokacija;
    private int trajanjeVoznje;
    private boolean postojiKvar;
    private boolean postojiPromocija;
    private int brojIznajmljivanja;


    /**
     * Konstruktor klase Iznajmljivanje.
     *
     * @param datumVrijeme        Datum i vrijeme iznajmljivanja
     * @param vozilo              Iznajmljeno vozilo
     * @param korisnik            Korisnik koji iznajmljuje vozilo
     * @param XtrenutnaLokacija    Pocetna X koordinata
     * @param YtrenutnaLokacija    Pocetna Y koordinata
     * @param XfinalnaLokacija     Finalna X koordinata
     * @param YfinalnaLokacija     Finalna Y koordinata
     * @param trajanjeVoznje       Trajanje voznje
     * @param kvar                Da li postoji kvar na vozilu
     * @param postojiPromocija     Da li postoji promocija
     * @param brojIznajmljivanja   Broj iznajmljivanja korisnika do odgovarajuceg datuma i vremena
     */
    public Iznajmljivanje(LocalDateTime datumVrijeme, Vozilo vozilo, String korisnik, int XtrenutnaLokacija, int YtrenutnaLokacija, int XfinalnaLokacija, int YfinalnaLokacija, int trajanjeVoznje, boolean kvar, boolean postojiPromocija, int brojIznajmljivanja) {
        this.datumVrijeme = datumVrijeme;
        this.vozilo = vozilo;
        this.korisnik = korisnik;
        this.XpocetnaLokacija = XtrenutnaLokacija;
        this.YpocetnaLokacija = YtrenutnaLokacija;
        this.XfinalnaLokacija = XfinalnaLokacija;
        this.YfinalnaLokacija = YfinalnaLokacija;
        this.trajanjeVoznje = trajanjeVoznje;
        this.postojiKvar = kvar;
        this.postojiPromocija = postojiPromocija;
        this.brojIznajmljivanja=brojIznajmljivanja;
    }


    /**
     * Vraca broj iznajmljivanja vozila.
     *
     * @return Broj iznajmljivanja
     */
    public int getBrojIznajmljivanja() {
        return brojIznajmljivanja;
    }

    /**
     * Postavlja broj iznajmljivanja vozila.
     *
     * @param brojIznajmljivanja Novi broj iznajmljivanja
     */
    public void setBrojIznajmljivanja(int brojIznajmljivanja) {
        this.brojIznajmljivanja = brojIznajmljivanja;
    }

    /**
     * Vraca status promocije za iznajmljivanje.
     *
     * @return true ako postoji promocija, false u suprotnom.
     */
    public boolean isPostojiPromocija() {
        return postojiPromocija;
    }

    /**
     * Postavlja status promocije za iznajmljivanje.
     *
     * @param postojiPromocija true ako postoji promocija, false u suprotnom.
     */
    public void setPostojiPromocija(boolean postojiPromocija) {
        this.postojiPromocija = postojiPromocija;
    }

    /**
     * Vraca datum i vrijeme iznajmljivanja.
     *
     * @return datum i vrijeme iznajmljivanja kao LocalDateTime objekat.
     */
    public LocalDateTime getDatumVrijeme() {
        return datumVrijeme;
    }

    /**
     * Postavlja datum i vrijeme iznajmljivanja.
     *
     * @param datumVrijeme datum i vrijeme koje se postavlja kao LocalDateTime objekat.
     */
    public void setDatumVrijeme(LocalDateTime datumVrijeme) {
        this.datumVrijeme = datumVrijeme;
    }

    /**
     * Vraca vozilo povezano sa iznajmljivanjem.
     *
     * @return vozilo koje se iznajmljuje.
     */
    public Vozilo getVozilo() {
        return vozilo;
    }

    /**
     * Postavlja vozilo koje je povezano sa iznajmljivanjem.
     *
     * @param vozilo vozilo koje će biti iznajmljeno.
     */
    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    /**
     * Vraca ime korisnika koji iznajmljuje vozilo.
     *
     * @return korisnicko ime korisnika.
     */
    public String getKorisnik() {
        return korisnik;
    }

    /**
     * Postavlja ime korisnika koji iznajmljuje vozilo.
     *
     * @param korisnik korisnicko ime korisnika.
     */
    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * Vraca pocetnu X-koordinatu lokacije iznajmljivanja.
     *
     * @return pocetna X-koordinata.
     */
    public int getXPocetnaLokacija() {
        return XpocetnaLokacija;
    }

    /**
     * Postavlja pocetnu X-koordinatu lokacije iznajmljivanja.
     *
     * @param XpocetnaLokacija pocetna X-koordinata koja se postavlja.
     */
    public void setXPocetnaLokacija(int XpocetnaLokacija) {
        this.XpocetnaLokacija = XpocetnaLokacija;
    }

    /**
     * Vraca pocetnu Y-koordinatu lokacije iznajmljivanja.
     *
     * @return pocetna Y-koordinata.
     */
    public int getYPocetnaLokacija() {
        return YpocetnaLokacija;
    }

    /**
     * Postavlja pocetnu Y-koordinatu lokacije iznajmljivanja.
     *
     * @param YpocetnaLokacija pocetna Y-koordinata koja se postavlja.
     */
    public void setYPocetnaLokacija(int YpocetnaLokacija) {
        this.YpocetnaLokacija = YpocetnaLokacija;
    }

    /**
     * Vraca krajnju X-koordinatu destinacije iznajmljivanja.
     *
     * @return krajnja X-koordinata.
     */
    public int getXFinalnaLokacija() {
        return XfinalnaLokacija;
    }

    /**
     * Postavlja krajnju X-koordinatu destinacije iznajmljivanja.
     *
     * @param XfinalnaLokacija krajnja X-koordinata koja se postavlja.
     */
    public void setXFinalnaLokacija(int XfinalnaLokacija) {
        this.XfinalnaLokacija = XfinalnaLokacija;
    }
    /**
     * Vraca krajnju Y-koordinatu destinacije iznajmljivanja.
     *
     * @return krajnja Y-koordinata.
     */
    public int getYFinalnaLokacija() {
        return YfinalnaLokacija;
    }

    /**
     * Postavlja krajnju Y-koordinatu destinacije iznajmljivanja.
     *
     * @param YfinalnaLokacija krajnja Y-koordinata koja se postavlja.
     */
    public void setYFinalnaLokacija(int YfinalnaLokacija) {
        this.YfinalnaLokacija = YfinalnaLokacija;
    }

    /**
     * Vraca  vrijednost trajanje voznje.
     *
     * @return trajanje voznje.
     */
    public int getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    /**
     * Postavlja trajanje voznje.
     *
     * @param trajanjeVoznje trajanje voznje koje se postavlja.
     */
    public void setTrajanjeVoznje(int trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    /**
     * Provjerava da li postoji kvar na vozilu.
     *
     * @return true ako postoji kvar, false u suprotnom.
     */
    public boolean isPostojiKvar() {
        return postojiKvar;
    }

    /**
     * Postavlja status kvara na vozilu.
     *
     * @param postojiKvar true ako postoji kvar, false u suprotnom.
     */
    public void setPostojiKvar(boolean postojiKvar) {
        this.postojiKvar = postojiKvar;
    }

    /**
     * Metoda koja vraca tekstualnu reprezentaciju objekta klase Iznajmljivanje.
     * Ova metoda koristi sve atribute klase kako bi kreirala string sa svim
     * relevantnim informacijama o iznajmljivanju.
     *
     * @return String sa informacijama o iznajmljivanju (datum, vozilo, korisnik itd.).
     */

    @Override
    public String toString() {
        return "Iznajmljivanje{" +
                "datumVrijeme=" + datumVrijeme +
                ", vozilo=" + vozilo +
                ", korisnik=" + korisnik +
                ", brojIznajmljivanja=" + brojIznajmljivanja +
                ", XpocetnaLokacija=" + XpocetnaLokacija +
                ", YpocetnaLokacija=" + YpocetnaLokacija +
                ", XfinalnaLokacija=" + XfinalnaLokacija +
                ", YfinalnaLokacija=" + YfinalnaLokacija +
                ", trajanjeVoznje=" + trajanjeVoznje +
                ", postojiKvar=" + postojiKvar +
                ", postojiPromocija=" + postojiPromocija +
                '}';
    }

    /**
     * Metoda koja provjerava da li se pocetna i finalna lokacija nalaze u uzoj ili siroj regiji grada.
     *
     * @return StatusRegije.UZI ako su obje lokacije unutar uze regije,
     * StatusRegije.SIRI u suprotnom.
     */
    public StatusRegije provjeraRegije() {
        int Xkoordinata = Simulacija.DIM;
        int Ykoordinata = Simulacija.DIM;
        if (XpocetnaLokacija > 4 && XpocetnaLokacija < 15 && YpocetnaLokacija > 4 && YpocetnaLokacija < 15
                && XfinalnaLokacija > 4 && XfinalnaLokacija < 15 && YfinalnaLokacija > 4 && YfinalnaLokacija < 15) {
            return StatusRegije.UZI;
        } else
            return StatusRegije.SIRI;

    }

    /**
     * Metoda koja provjerava da li se proslijeđene koordinate nalaze u uzem ili sirem dijelu grada.
     *
     * @param x X koordinata
     * @param y Y koordinata
     * @return String "UZI" ako su koordinate unutar uzeg dijela, inace "SIRI"
     */
    public StatusRegije provjeraKoordinataRegija(int x, int y)
    {
        if(x > 4 && x < 15 && y > 4 && y < 15)
        {
            return StatusRegije.UZI;
        }
        else return StatusRegije.SIRI;
    }


    /**
     * Metoda koja kontrolise ponasanje voznje, kroz konkurentno programiranje.
     * Simulira kretanje vozila na mapi, azurira GUI i azurira stanje vozila na mapi u slucaju kvara ili zavrsetka voznje.
     */
    @Override
    public void run() {
        int brojPolja=Math.abs(XfinalnaLokacija - XpocetnaLokacija) + Math.abs(YfinalnaLokacija - YpocetnaLokacija);
        double kolicnik=((double)trajanjeVoznje/brojPolja)*1000;
        int spavanje=(int) kolicnik;

        int XtrenutnaLokacija=this.XpocetnaLokacija;
        int YtrenutnaLokacija=this.YpocetnaLokacija;
        // Prikaz pocetne pozicije vozila
        Platform.runLater(() -> dodajVoziloNaMapu(this.vozilo.getIdentifikator(),this.vozilo.getTrenutniNivoBaterije(), this.XpocetnaLokacija, this.YpocetnaLokacija));

        while (true) {
            // Kretanje vozila
            int predfinalX=XtrenutnaLokacija;
            int predfinalY=YtrenutnaLokacija;

            if (YtrenutnaLokacija < YfinalnaLokacija) YtrenutnaLokacija++;
            else if (YtrenutnaLokacija > YfinalnaLokacija) YtrenutnaLokacija--;
            else {
                if (XtrenutnaLokacija < XfinalnaLokacija) XtrenutnaLokacija++;
                else if (XtrenutnaLokacija > XfinalnaLokacija) XtrenutnaLokacija--;
            }



            this.vozilo.praznjenjeBaterije();

            // cekanje na polju
            try {
                Thread.sleep(spavanje);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Ažuriranje GUI-ja
            int finalX = XtrenutnaLokacija;
            int finalY= YtrenutnaLokacija;
            Platform.runLater(() -> pomjeriVozilo(this.vozilo.getIdentifikator(), predfinalX,predfinalY,finalX, finalY, this.vozilo.getTrenutniNivoBaterije()));

            if (this.isPostojiKvar()) {
                Racun.kreirajFajlZaRacun(this);
                this.vozilo.praznjenjeBaterije();
                // Uklanjanje vozila sa mape
                Platform.runLater(() -> azuriranjeLabele(finalX, finalY, StatusVozila.KVAR));
                Platform.runLater(() -> ukloniVozilo(this.vozilo.getIdentifikator(), finalX, finalY));



                this.getVozilo().punjenjeBaterije();
                break;
            }

                if (XtrenutnaLokacija == XfinalnaLokacija && YtrenutnaLokacija == YfinalnaLokacija) {
                    Racun.kreirajFajlZaRacun(this);
                    this.vozilo.praznjenjeBaterije();
                    Platform.runLater(() -> pomjeriVoziloNaKraj(this.vozilo.getIdentifikator(), predfinalX,predfinalY,finalX, finalY, this.vozilo.getTrenutniNivoBaterije()));
                    Platform.runLater(() -> azuriranjeLabele(finalX, finalY, StatusVozila.KRAJ));
                    // cekanje na polju
                    try {
                        Thread.sleep(spavanje);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> ukloniVozilo(this.vozilo.getIdentifikator(), finalX, finalY));

                    this.getVozilo().punjenjeBaterije();

                    break;
                }



        }
    }

    /**
     * Boji labelu na osnovu stanja vozila i njegove pozicije.
     * Ako je stanje "kvar", boja labele će biti crvena, a u suprotnom zelena.
     * Pored toga, zavisno od regiona, labela se resetuje na odgovarajucu boju
     * nakon pauze od 1.5 sekundi.
     *
     * @param label Labela koja se boji.
     * @param stanje Stanje vozila ("kvar" ili dolazak na cilj).
     * @param x X koordinata vozila.
     * @param y Y koordinata vozila.
     */
    private void bojiLabelu(Label label, StatusVozila stanje, int x, int y) {
        switch(stanje)
        {
            case KVAR:
                label.setStyle("-fx-background-color: red; -fx-border-color: black; -fx-alignment: center;");
                break;
            case KRAJ:
                label.setStyle("-fx-background-color: green; -fx-border-color: black; -fx-alignment: center;");
                break;
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));

        switch(provjeraKoordinataRegija(x,y))
        {
            case UZI:
                pause.setOnFinished(event -> resetLabele(label, 0));
                pause.play();
                break;
            case SIRI:
                pause.setOnFinished(event -> resetLabele(label,1));
                pause.play();
                break;
        }


    }

    /**
     * Azurira labelu na mapi na datoj poziciji na osnovu stanja vozila.
     * Koristi platform.runLater() kako bi azuriranje bilo izvrseno na GUI thread-u.
     *
     * @param x X koordinata vozila.
     * @param y Y koordinata vozila.
     * @param stanje Stanje vozila ("kvar" ili dolazak na cilj).
     */
    private void azuriranjeLabele(int x, int y, StatusVozila stanje) {
        GridPane mapa = ControllerPristup.getController().getMapa();
        Label labela = dohvatiLabelu(mapa, x, y);

        if (labela != null) {
            Platform.runLater(() -> bojiLabelu(labela, stanje, x, y));
        }
    }

    /**
     * Resetuje labelu na osnovni stil u zavisnosti od regiona.
     * Plava boja za uzi dio grada, bijela boja za siri dio.
     *
     * @param labela Labela koja se resetuje.
     * @param i Indikator regiona (0 za uzi dio, 1 za siri dio).
     */
    private void resetLabele(Label labela, int i) {
        if(i==0)
        {
            Platform.runLater(() -> labela.setStyle("-fx-border-color: black; -fx-background-color: lightblue; -fx-alignment: center;"));
        }
        else
        {
            Platform.runLater(() -> labela.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-alignment: center;"));
        }

    }

    /**
     * Dodaje vozilo na mapu na zadatu poziciju i azurira labelu sa informacijama o vozilu.
     * Ako labela vec postoji na poziciji, azurira se mapa vozila, u suprotnom se kreira nova labela.
     *
     * @param voziloId ID vozila.
     * @param baterija Nivo baterije vozila.
     * @param x X koordinata na mapi.
     * @param y Y koordinata na mapi.
     */
    private void dodajVoziloNaMapu(String voziloId, double baterija, int x, int y) {
        GridPane mapa = ControllerPristup.getController().getMapa();

        Label labela = dohvatiLabelu(mapa,x, y);


        if (labela != null) {

            @SuppressWarnings("unchecked")
            Map<String, Double> mapaVozila = (Map<String, Double>) labela.getUserData();

            if (mapaVozila == null) {
                mapaVozila = new HashMap<>();
                labela.setUserData(mapaVozila);
            }

            mapaVozila.put(voziloId, baterija);
            azuriranjeTekstaLabele(labela, mapaVozila);

        } else {
            Label labelaVozilo = new Label();
            Map<String, Double> mapaVozila = new HashMap<>();
            mapaVozila.put(voziloId, baterija);

            labelaVozilo.setUserData(mapaVozila); // Postavi mapu kao userData

            labelaVozilo.setMinSize(60, 30);
            labelaVozilo.setStyle("-fx-border-color: black; -fx-background-color: yellow; -fx-alignment: center;");

            labelaVozilo.setText(generisanjeTekstaLabele(mapaVozila));

            mapa.add(labelaVozilo,y,x);
        }
    }



    /**
     * Azurira tekst prikazan na labeli na osnovu mape vozila i njihovih nivoa baterije.
     *
     * @param labela Labela koja se azurira.
     * @param mapaVozila Mapa vozila i njihovih nivoa baterije.
     */
    private void azuriranjeTekstaLabele(Label labela, Map<String, Double> mapaVozila) {
        // Ažuriraj tekst labele sa svim ID-jevima i nivoima baterije
        labela.setText(generisanjeTekstaLabele(mapaVozila));
    }

    /**
     * Generise tekst za prikaz na labeli na osnovu mape vozila.
     * Svaki unos u mapi sadrzi ID vozila i nivo baterije.
     *
     * @param mapaVozila Mapa koja sadrži vozila i njihove nivoe baterije.
     * @return Generisani tekst za prikaz.
     */
    private String generisanjeTekstaLabele(Map<String, Double> mapaVozila) {
        // Generiši tekst na osnovu mape
        StringBuilder tekst = new StringBuilder();
        for (Map.Entry<String, Double> rijec : mapaVozila.entrySet()) {
            tekst.append("ID: ").append(rijec.getKey()).append("\n").append(rijec.getValue()).append(" %\n");
        }
        return tekst.toString();
    }

    /**
     * Pomjera vozilo sa stare pozicije na novu poziciju na mapi.
     * Prvo uklanja vozilo sa stare pozicije, zatim ga dodaje na novu poziciju.
     *
     * @param voziloId ID vozila koje se pomjera.
     * @param staroX X koordinata stare pozicije.
     * @param staroY Y koordinata stare pozicije.
     * @param novoX X koordinata nove pozicije.
     * @param novoY Y koordinata nove pozicije.
     * @param baterija Nivo baterije vozila.
     */
    private void pomjeriVozilo(String voziloId, int staroX, int staroY, int novoX, int novoY, double baterija) {

        // Prvo uklanjamo vozilo sa stare pozicije
        ukloniVozilo(voziloId, staroX, staroY);

        // Dodajemo vozilo na novu poziciju
        dodajVoziloNaMapu(voziloId, baterija, novoX, novoY);
    }

    /**
     * Pomjera vozilo na finalnu poziciju u mapi.
     * Prvo uklanja vozilo sa stare pozicije, zatim ga dodaje na novu (finalnu) poziciju.
     *
     * @param voziloId ID vozila koje se pomjera.
     * @param staroX X koordinata stare pozicije.
     * @param staroY Y koordinata stare pozicije.
     * @param novoX X koordinata nove pozicije.
     * @param novoY Y koordinata nove pozicije.
     * @param baterija Nivo baterije vozila.
     */
    private void pomjeriVoziloNaKraj(String voziloId, int staroX, int staroY, int novoX, int novoY, double baterija) {
        ukloniVozilo(voziloId, staroX, staroY);

        dodajVoziloNaMapu(voziloId, baterija, novoX, novoY);

    }

    /**
     * Uklanja vozilo sa odredjene pozicije na mapi na osnovu ID-a vozila.
     * Azurira labelu na toj poziciji uklanjanjem vozila iz mape.
     *
     * @param voziloId ID vozila koje se uklanja.
     * @param x X koordinata pozicije.
     * @param y Y koordinata pozicije.
     */
    private void ukloniVozilo(String voziloId, int x, int y) {
        GridPane mapa = ControllerPristup.getController().getMapa();

        // Provjeri da li postoji labela na datoj poziciji
        Label existingLabel = dohvatiLabelu(mapa, x,y);


        if (existingLabel != null) {
            @SuppressWarnings("unchecked")
            Map<String, Double> vehicleMap = (Map<String, Double>) existingLabel.getUserData();

            if (vehicleMap != null) {
                vehicleMap.remove(voziloId);

                azuriranjeTekstaLabele(existingLabel, vehicleMap);


            }
        }
    }

    /**
     * Dohvata labelu na zadatoj poziciji (x, y) iz GridPane-a.
     *
     * @param mapa GridPane koji predstavlja mapu.
     * @param x X koordinata pozicije.
     * @param y Y koordinata pozicije.
     * @return Labela na zadatoj poziciji ili null ako ne postoji.
     */
    private Label dohvatiLabelu(GridPane mapa, int x, int y) {
        for (var node : mapa.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                return (Label) node;
            }
        }
        return null;
    }



}







