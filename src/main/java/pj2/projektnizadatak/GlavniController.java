package pj2.projektnizadatak;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GlavniController je kontroler klasa koja upravlja glavnim prozorom aplikacije.
 * Implementira interfejs Initializable za inicijalizaciju elemenata nakon ucitavanja FXML-a.
 * Ova klasa sadrzi metode za prikaz razlicitih prozora i postavljanje mape za simulaciju.
 */
public class GlavniController implements Initializable {

    @FXML
    private GridPane mapa;


    private boolean simulacijaZavrsena = false;

    /**
     * Postavlja status simulacije.
     * @param zavrsena true ako je simulacija zavrsena, false inace.
     */
    public void setSimulacijaZavrsena(boolean zavrsena) {
        this.simulacijaZavrsena = zavrsena;
    }

    /**
     * Inicijalizuje glavni prozor aplikacije i postavlja mapu sa 20x20 polja (labela).
     * Sredisnji deo mape je oznacen plavom bojom.
     * @param url URL lokacija (ne koristi se direktno)
     * @param resourceBundle ResourceBundle resursi (ne koristi se direktno)
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int startRed = 5;
        int krajRed = 14;
        int startKolona = 5;
        int krajKolona = 14;
        for (int red = 0; red < 20; red++) {
            for (int kol = 0; kol < 20; kol++) {
                Label labela= new Label();
                labela.setMinSize(60, 30);
                labela.setStyle("-fx-border-color: black;-fx-background-color: white; -fx-alignment: center;");

                // Obojimo centralni 10x10 dio plavom bojom
                if (red >= startRed && red <= krajRed && kol >= startKolona && kol <= krajKolona) {
                    labela.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-alignment: center;");
                }

                mapa.add(labela, kol, red);
            }
        }



    }

    /**
     * Prikazuje prozor sa informacijama o vozilima koristeci odgovarajuci FXML fajl.
     */
    @FXML
    public void prikazVozila()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        otvoriProzorVozila("prikazVozila.fxml", "Prikaz Vozila");
        VozilaController vozila = fxmlLoader.getController();

    }

    /**
     * Prikazuje prozor sa informacijama o kvarovima koristeci odgovarajuci FXML fajl.
     */
    @FXML
    public void prikazKvarova()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        otvoriProzor("prikazKvarova.fxml", "Prikaz Kvarova");
        KvaroviController kvarovi = fxmlLoader.getController();


    }

    /**
     * Prikazuje prozor sa izvestajima koristeci odgovarajuci FXML fajl.
     */
    @FXML
    public void prikazIzvjestaja()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        otvoriProzor("prikazIzvjestaja.fxml", "Prikaz Izvjestaja");
        IzvjestajiController izvjestaji = fxmlLoader.getController();

    }

    /**
     * Prikazuje prozor sa dodatnom funkcionalnoscu koristeci odgovarajuci FXML fajl.
     */
    @FXML
    public void prikazDeserijalizacije()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        otvoriProzor("dodatno.fxml", "Dodatna Funkcionalnost");
        DodatnaFController dodatno = fxmlLoader.getController();
    }

    /**
     * Otvara prozor sa informacijama o vozilima.
     * @param fxmlFile putanja do FXML fajla
     * @param ime naslov prozora
     */
    private void otvoriProzorVozila(String fxmlFile, String ime) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(ime);
            stage.setScene(new Scene(root, 800, 400));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Otvara novi prozor samo ako je simulacija zavrsena,
     * a u slucaju pokusaja otvaranja prozora dok simulacija traje ispisuje se odgovarajuca poruka na konzolu.
     * @param fxmlFile putanja do FXML fajla
     * @param ime naslov prozora
     */
    private void otvoriProzor(String fxmlFile, String ime) {

        if (!simulacijaZavrsena) {
            System.out.println("Simulacija nije završena. Ne možete otvoriti ovaj prozor.");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(ime);
            stage.setScene(new Scene(root, 800, 400));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vraca mrezu (GridPane) koja predstavlja mapu simulacije.
     * @return mapa kao GridPane
     */
    public  GridPane getMapa() {
        return mapa;
    }





}