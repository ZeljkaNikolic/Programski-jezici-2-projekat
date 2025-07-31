package pj2.projektnizadatak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pj2.projektnizadatak.izvjestaji.DnevniIzvjestaj;
import pj2.projektnizadatak.izvjestaji.SumarniIzvjestaj;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Kontroler klasa koja upravlja prikazom dnevnih i sumarnim izvjestaja
 * u tabelama u GUI aplikaciji. Implementira Initializable interfejs kako bi
 * omogucila inicijalizaciju tabela sa podacima nakon ucitavanja GUI komponente.
 */
public class IzvjestajiController implements Initializable {


    @FXML
    private TableView<SumarniIzvjestaj> tabelaSumarni;

    @FXML
    private TableColumn<SumarniIzvjestaj, Double> ukupanPrihodColumn;

    @FXML
    private TableColumn<SumarniIzvjestaj,Double> ukupanPopustColumn;

    @FXML
    private TableColumn<SumarniIzvjestaj,Double> uziDioColumn;
    @FXML
    private TableColumn<SumarniIzvjestaj,Double> siriDioColumn;
    @FXML
    private TableColumn<SumarniIzvjestaj,Double> odrzavanjeColumn;
    @FXML
    private TableColumn<SumarniIzvjestaj, Double> trosakColumn;

    @FXML
    private TableColumn<SumarniIzvjestaj,Double> promocijaColumn;
    @FXML
    private TableColumn<SumarniIzvjestaj,Double> porezColumn;

    @FXML
    private TableColumn<SumarniIzvjestaj,Double> popravkaColumn;




    @FXML
    private TableView<DnevniIzvjestaj> tabelaDnevni;
    @FXML
    private TableColumn<DnevniIzvjestaj,Double> datumColumn;
    @FXML
    private TableColumn<SumarniIzvjestaj, Double> prihodColumn;

    @FXML
    private TableColumn<DnevniIzvjestaj,Double> uziColumn;
    @FXML
    private TableColumn<DnevniIzvjestaj,Double> siriColumn;
    @FXML
    private TableColumn<DnevniIzvjestaj,Double> odrzavanjeIColumn;
    @FXML
    private TableColumn<DnevniIzvjestaj,Double> popustColumn;
    @FXML
    private TableColumn<DnevniIzvjestaj,Double> promocijaIColumn;
    @FXML
    private TableColumn<DnevniIzvjestaj,Double> popravkaIColumn;




    /**
     * Inicijalizacija kontrolera. Popunjava tabele podacima o dnevnim i sumarnim izvjestajima.
     *
     * @param url nije koristen, ali je potreban za implementaciju Initializable interfejsa
     * @param resourceBundle nije koristen, ali je potreban za implementaciju Initializable interfejsa
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ArrayList<DnevniIzvjestaj> izvjestaji=Simulacija.izvjestaji;
        ObservableList<DnevniIzvjestaj> observableIzvjestaji = FXCollections.observableArrayList(izvjestaji);

        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        prihodColumn.setCellValueFactory(new PropertyValueFactory<>("ukupanPrihod"));
        popustColumn.setCellValueFactory(new PropertyValueFactory<>("ukupanPopust"));
        uziColumn.setCellValueFactory(new PropertyValueFactory<>("iznosUzegDijela"));
        siriColumn.setCellValueFactory(new PropertyValueFactory<>("iznosSiregDijela"));
        odrzavanjeIColumn.setCellValueFactory(new PropertyValueFactory<>("iznosOdrzavanja"));
        promocijaIColumn.setCellValueFactory(new PropertyValueFactory<>("ukupnoPromocije"));
        popravkaIColumn.setCellValueFactory(new PropertyValueFactory<>("iznosPopravkeKvarova"));
        tabelaDnevni.setItems(observableIzvjestaji);

        SumarniIzvjestaj sumarniIzvjestaj = new SumarniIzvjestaj(izvjestaji);
        ObservableList<SumarniIzvjestaj> observableSumarniIzvjestaji = FXCollections.observableArrayList(sumarniIzvjestaj);
        ukupanPrihodColumn.setCellValueFactory(new PropertyValueFactory<>("ukupanPrihod"));
        ukupanPopustColumn.setCellValueFactory(new PropertyValueFactory<>("ukupanPopust"));
        uziDioColumn.setCellValueFactory(new PropertyValueFactory<>("iznosUzegDijela"));
        siriDioColumn.setCellValueFactory(new PropertyValueFactory<>("iznosSiregDijela"));
        odrzavanjeColumn.setCellValueFactory(new PropertyValueFactory<>("iznosOdrzavanja"));
        trosakColumn.setCellValueFactory(new PropertyValueFactory<>("ukupanTrosak"));
        promocijaColumn.setCellValueFactory(new PropertyValueFactory<>("ukupnoPromocije"));
        porezColumn.setCellValueFactory(new PropertyValueFactory<>("ukupanPorez"));
        popravkaColumn.setCellValueFactory(new PropertyValueFactory<>("iznosPopravkeKvarova"));

        tabelaSumarni.setItems(observableSumarniIzvjestaji);











    }
}
