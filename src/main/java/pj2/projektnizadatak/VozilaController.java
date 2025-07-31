package pj2.projektnizadatak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pj2.projektnizadatak.vozila.Automobil;
import pj2.projektnizadatak.vozila.Bicikl;
import pj2.projektnizadatak.vozila.Trotinet;
import pj2.projektnizadatak.vozila.Vozilo;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Kontroler za prikaz vozila svakog tipa u razlicitim tabelama u GUI-u.
 * Ova klasa upravlja tabelama za prikaz automobila, bicikala i trotineta u JavaFX aplikaciji.
 */
public class VozilaController implements Initializable {

    // Tabela za Automobil
    @FXML
    private TableView<Automobil> automobilTabela;
    @FXML
    private TableColumn<Automobil, String> idColumn;
    @FXML
    private TableColumn<Automobil, String> proizvodjacColumn;
    @FXML
    private TableColumn<Automobil, String> modelColumn;
    @FXML
    private TableColumn<Automobil, Double> cijenaColumn;
    @FXML
    private TableColumn<Automobil, LocalDate> datumColumn;
    @FXML
    private TableColumn<Automobil, String> opisColumn;
    @FXML
    private TableColumn<Automobil, Integer> brojLjudiColumn;

    // Tabela za Bicikl
    @FXML
    private TableView<Bicikl> biciklTabela;
    @FXML
    private TableColumn<Bicikl, String> idBColumn;
    @FXML
    private TableColumn<Bicikl, String> proizvodjacBColumn;
    @FXML
    private TableColumn<Bicikl, String> modelBColumn;
    @FXML
    private TableColumn<Bicikl, Double> cijenaBColumn;
    @FXML
    private TableColumn<Bicikl, Integer> dometColumn;


    // Tabela za Trotinet
    @FXML
    private TableView<Trotinet> trotinetTabela;
    @FXML
    private TableColumn<Trotinet, String> idTColumn;
    @FXML
    private TableColumn<Trotinet, String> proizvodjacTColumn;
    @FXML
    private TableColumn<Trotinet, String> modelTColumn;
    @FXML
    private TableColumn<Trotinet, Double> cijenaTColumn;
    @FXML
    private TableColumn<Trotinet, Double> maksimalnaBrzinaColumn;


    /**
     * Ova metoda se automatski poziva kada se GUI inicijalizuje.
     * Metoda ucitava listu vozila, razvrstava ih prema tipu (automobil, bicikl, trotinet),
     * i postavlja ih u odgovarajuće tabele.
     *
     * @param url            nije koristen, ali je potreban za implementaciju Initializable interfejsa
     * @param resourceBundle nije koristen, ali je potreban za implementaciju Initializable interfejsa
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Automobil>automobili= FXCollections.observableArrayList();
        ObservableList<Bicikl>bicikli= FXCollections.observableArrayList();
        ObservableList<Trotinet>trotineti= FXCollections.observableArrayList();

        for(Vozilo v:Simulacija.listaVozila)
        {
            if (v instanceof Automobil)
            {
                automobili.add((Automobil) v);
            }
            else if(v instanceof Bicikl)
            {
                bicikli.add((Bicikl) v);
            }
            else
                trotineti.add((Trotinet) v);

        }

        idColumn.setCellValueFactory(new PropertyValueFactory<Automobil,String>("identifikator"));
        proizvodjacColumn.setCellValueFactory(new PropertyValueFactory<Automobil, String>("proizvodjac"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Automobil,String>("model"));
        cijenaColumn.setCellValueFactory(new PropertyValueFactory<Automobil, Double>("cijenaNabavke"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<Automobil, LocalDate>("datumNabavke"));
        opisColumn.setCellValueFactory(new PropertyValueFactory<Automobil, String>("opis"));
        brojLjudiColumn.setCellValueFactory(new PropertyValueFactory<>("prevozenjeViseLjudi"));

        idBColumn.setCellValueFactory(new PropertyValueFactory<Bicikl,String>("identifikator"));
        proizvodjacBColumn.setCellValueFactory(new PropertyValueFactory<Bicikl, String>("proizvodjac"));
        modelBColumn.setCellValueFactory(new PropertyValueFactory<Bicikl,String>("model"));
        cijenaBColumn.setCellValueFactory(new PropertyValueFactory<Bicikl, Double>("cijenaNabavke"));
        dometColumn.setCellValueFactory(new PropertyValueFactory<Bicikl, Integer>("dometSaJednimPunjenjem"));


        idTColumn.setCellValueFactory(new PropertyValueFactory<Trotinet,String>("identifikator"));
        proizvodjacTColumn.setCellValueFactory(new PropertyValueFactory<Trotinet, String>("proizvodjac"));
        modelTColumn.setCellValueFactory(new PropertyValueFactory<Trotinet,String>("model"));
        cijenaTColumn.setCellValueFactory(new PropertyValueFactory<Trotinet, Double>("cijenaNabavke"));
        maksimalnaBrzinaColumn.setCellValueFactory(new PropertyValueFactory<Trotinet, Double>("maksimalnaBrzina"));

        automobilTabela.setItems(automobili);
        biciklTabela.setItems(bicikli);
        trotinetTabela.setItems(trotineti);

    }




}
