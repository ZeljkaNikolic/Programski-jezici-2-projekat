package pj2.projektnizadatak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pj2.projektnizadatak.dodatnaFunkcionalnost.DodatnaFunkcinalnost;
import pj2.projektnizadatak.vozila.Vozilo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Kontroler klasa zaduzena za rukovanje dodatnom funkcionalnoscu u aplikaciji.
 * Konkretno, prikazuje tabelu vozila koja imaju prijavljene kvarove.
 * Klasa inicijalizuje tabelu sa podacima o vozilima iz serijalizovane datoteke.
 */
public class DodatnaFController implements Initializable {


    @FXML
    private TableView<Vozilo> kvaroviTabela;

    @FXML
    private TableColumn<Vozilo, String> idColumn;

    @FXML
    private TableColumn<Vozilo, String> proizvodjacColumn;

    @FXML
    private TableColumn<Vozilo, String> modelColumn;

    @FXML
    private TableColumn<Vozilo, Double> cijenaColumn;

    /**
     * Inicijalizuje tabelu sa podacima o vozilima i njihovim kvarovima.
     * Podaci se ucitavaju iz serijalizovane liste vozila sa kvarovima.
     * @param url URL resursa koji se inicijalizuje (ne koristi se direktno).
     * @param resourceBundle Resursi potrebni za lokalizaciju (ne koristi se direktno).
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        DodatnaFunkcinalnost.serijalizujListu(Simulacija.kvarovi, Simulacija.FAJL_SERIJALIZACIJA);
        ArrayList<Vozilo> kvarovi = DodatnaFunkcinalnost.deserijalizujListu(Simulacija.FAJL_SERIJALIZACIJA);

        ObservableList<Vozilo> observableVozila = FXCollections.observableArrayList(kvarovi);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("identifikator"));
        proizvodjacColumn.setCellValueFactory(new PropertyValueFactory<>("proizvodjac"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        cijenaColumn.setCellValueFactory(new PropertyValueFactory<>("ukupnoKvara"));
        kvaroviTabela.setItems(observableVozila);

    }
}
