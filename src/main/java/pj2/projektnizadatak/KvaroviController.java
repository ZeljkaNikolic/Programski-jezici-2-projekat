package pj2.projektnizadatak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pj2.projektnizadatak.kvar.Kvar;
import pj2.projektnizadatak.kvar.KvarGUIModel;
import pj2.projektnizadatak.vozila.Automobil;
import pj2.projektnizadatak.vozila.Bicikl;
import pj2.projektnizadatak.vozila.Trotinet;
import pj2.projektnizadatak.vozila.Vozilo;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

/**
 * KvaroviController je klasa koja je odgovorna za upravljanje prikazom i podacima u tabeli
 * koja prikazuje kvarove vozila. Implementira interfejs Initializable kako bi se podaci
 * popunili kada se prikaz ucita.
 */
public class KvaroviController implements Initializable {
    @FXML
    private TableView<KvarGUIModel> kvaroviTabela;
    @FXML
    private TableColumn<KvarGUIModel, String> idColumn;
    @FXML
    private TableColumn<KvarGUIModel, String> vrstaColumn;
    @FXML
    private TableColumn<KvarGUIModel, LocalDateTime> vrijemeColumn;
    @FXML
    private TableColumn<KvarGUIModel, String> opisColumn;

    @FXML
    private TableColumn<KvarGUIModel, Double> cijenaColumn;

    /**
     * Inicijalizuje kontroler i popunjava tabelu podacima o kvarovima iz
     * liste vozila koja se nalaze u simulaciji. Ova metoda se automatski poziva
     * kada se prikaz ucita.
     *
     * @param url nije koristen, ali je potreban za implementaciju Initializable interfejsa
     * @param resourceBundle nije koristen, ali je potreban za implementaciju Initializable interfejsa
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Vozilo> vozila = Simulacija.kvarovi;

        ObservableList<KvarGUIModel> kvarovi = FXCollections.observableArrayList();

        for (Vozilo vozilo : vozila) {
            if (vozilo != null && vozilo.getKvarovi() != null) {
                for (Kvar kvar : vozilo.getKvarovi()) {
                    kvarovi.add(new KvarGUIModel(
                            vozilo.getIdentifikator(),
                            getVrsta(vozilo),
                            kvar.getDatumVrijeme(),
                            kvar.getOpis(),
                            kvar.getCijena()
                    ));
                }
            }
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        vrstaColumn.setCellValueFactory(new PropertyValueFactory<>("vrsta"));
        vrijemeColumn.setCellValueFactory(new PropertyValueFactory<>("vrijeme"));
        opisColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        cijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));

        kvaroviTabela.setItems(kvarovi);
    }

    /**
     * Vraca vrstu vozila (Automobil, Bicikl, Trotinet) kao string.
     * Koristi se za prikaz vrste vozila u tabeli.
     *
     * @param vozilo vozilo cija vrsta treba da se odredi
     * @return string koji predstavlja vrstu vozila ("Automobil", "Bicikl", "Trotinet", ili "Nepoznato")
     */
    private String getVrsta(Vozilo vozilo) {
        if (vozilo instanceof Automobil) {
            return "Automobil";
        } else if (vozilo instanceof Bicikl) {
            return "Bicikl";
        } else if (vozilo instanceof Trotinet) {
            return "Trotinet";
        }
        return "Nepoznato";
    }
}
