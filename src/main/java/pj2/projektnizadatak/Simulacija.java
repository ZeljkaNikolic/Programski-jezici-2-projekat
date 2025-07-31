package pj2.projektnizadatak;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pj2.projektnizadatak.dodatnaFunkcionalnost.DodatnaFunkcinalnost;
import pj2.projektnizadatak.iznajmljivanje.Iznajmljivanje;
import pj2.projektnizadatak.izvjestaji.DnevniIzvjestaj;
import pj2.projektnizadatak.izvjestaji.Racun;
import pj2.projektnizadatak.izvjestaji.SumarniIzvjestaj;
import pj2.projektnizadatak.radSaFajlovima.IznajmljivanjeParser;
import pj2.projektnizadatak.radSaFajlovima.VozilaParser;
import pj2.projektnizadatak.vozila.Vozilo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Klasa Simulacija nasledjuje Application i sluzi za pokretanje JavaFX aplikacije.
 * Ova klasa implementira simulaciju iznajmljivanja vozila, pracenje kvarova, kao i  generisanje racuna i izvjestaja.
 */
public class Simulacija extends Application {

    // Staticke varijable
    public static double DISTANCE_NARROW;
    public static double DISTANCE_WIDE;
    public static double CAR_UNIT_PRICE;
    public static double BIKE_UNIT_PRICE;
    public static double SCOOTER_UNIT_PRICE;
    public static double DISCOUNT;
    public static double DISCOUNT_PROM;
    public static String FAJL_IZNAJMLJIVANJA;
    public static String FAJL_VOZILA;
    public static double KOEF_KVARA_AUTOMOBIL;
    public static double KOEF_KVARA_BICIKL;
    public static double KOEF_KVARA_TROTINET;
    public static int DIM;
    public static String FAJL_SERIJALIZACIJA;
    public static String FOLDER_RACUNI;
    public static HashMap<String, Vozilo> vozila = new HashMap<>();
    public static ArrayList<Vozilo> listaVozila;
    public static ArrayList<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
    public static ArrayList<Vozilo> kvarovi = new ArrayList<>();
    public static ArrayList<Racun> racuni = new ArrayList<>();
    public static ArrayList<DnevniIzvjestaj> izvjestaji = new ArrayList<>();
    public static final String NAZIV_PROPERTIES="/informacije.properties";


    // Staticki blok za inicijalizaciju
    static {
        Properties properties = new Properties();

        try  {
            properties.load(Simulacija.class.getResourceAsStream(NAZIV_PROPERTIES)) ;

            DISTANCE_NARROW = Double.parseDouble(properties.getProperty("DISTANCE_NARROW"));
            DISTANCE_WIDE = Double.parseDouble(properties.getProperty("DISTANCE_WIDE"));
            CAR_UNIT_PRICE = Double.parseDouble(properties.getProperty("CAR_UNIT_PRICE"));
            BIKE_UNIT_PRICE = Double.parseDouble(properties.getProperty("BIKE_UNIT_PRICE"));
            SCOOTER_UNIT_PRICE = Double.parseDouble(properties.getProperty("SCOOTER_UNIT_PRICE"));
            DISCOUNT = Double.parseDouble(properties.getProperty("DISCOUNT")) / 100;
            DISCOUNT_PROM = Double.parseDouble(properties.getProperty("DISCOUNT_PROM")) / 100;
            FAJL_IZNAJMLJIVANJA = properties.getProperty("FAJL_IZNAJMLJIVANJA");
            FAJL_VOZILA = properties.getProperty("FAJL_VOZILA");
            DIM = Integer.parseInt(properties.getProperty("DIM"));
            FAJL_SERIJALIZACIJA = properties.getProperty("FAJL_SERIJALIZACIJA");
            FOLDER_RACUNI = properties.getProperty("FOLDER_RACUNI");

            KOEF_KVARA_AUTOMOBIL = Double.parseDouble(properties.getProperty("KOEF_KVARA_AUTOMOBIL"));
            KOEF_KVARA_BICIKL = Double.parseDouble(properties.getProperty("KOEF_KVARA_BICIKL"));
            KOEF_KVARA_TROTINET = Double.parseDouble(properties.getProperty("KOEF_KVARA_TROTINET"));


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Genericka metoda za ispisivanje liste.
     * @param lista Lista koju treba ispisati.
     * @param <T> Tip elemenata u listi.
     */
    public static <T> void ispisLista(ArrayList<T> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Lista je prazna ili ne postoji.");
            return;
        }

        for (T element : lista) {
            System.out.println(element);
        }
    }

    /**
     * Genericka metoda za ispisivanje mape.
     * @param mapa Mapa koju treba ispisati.
     * @param <K> Tip kljuca u mapi.
     * @param <V> Tip vrijednosti u mapi.
     */
    public static <K, V> void ispisMapa(HashMap<K, V> mapa) {
        for (HashMap.Entry<K, V> entry : mapa.entrySet()) {
            K kljuc = entry.getKey();
            V vrijednost = entry.getValue();
            System.out.println("Kljuc: " + kljuc + ", Vrijednost: " + vrijednost);
        }
    }

    /**
     * Metoda za pokretanje JavaFX aplikacije. Inicijalizuje podatke o vozilima, iznajmljivanjima, kvarovima, racunima
     * i izvestajima. Takodje pokreće simulaciju u posebnoj niti.
     * @param stage Glavna scena JavaFX aplikacije.
     * @throws IOException U slucaju greske prilikom ucitavanja FXML fajla.
     */
    @Override
    public void start(Stage stage) throws IOException {


        //VOZILA
        //System.out.println("Vozila (kljuc=id, vrijednost=vozilo):");

        Simulacija.vozila = VozilaParser.mapaVozila();
        //Simulacija.ispisMapa(vozila);
        //System.out.println();

        //System.out.println("Lista vozila: ");
        Simulacija.listaVozila=new ArrayList<>(vozila.values());
        //Simulacija.ispisLista(listaVozila);



        //IZNAJMLJIVANJA
        //System.out.println("Iznajmljivanja:");
        ArrayList<Iznajmljivanje> pomocnaIznajmljivanja=IznajmljivanjeParser.listaIznajmljivanja();
        Simulacija.iznajmljivanja = IznajmljivanjeParser.azurirajBrojIznajmljivanja(pomocnaIznajmljivanja);
        //Simulacija.ispisLista(iznajmljivanja);
        //System.out.println();
        DodatnaFunkcinalnost.sumaKvarova();




       //KVAROVI
        //System.out.println("Vozila sa kvarom:");
        Simulacija.kvarovi = DodatnaFunkcinalnost.listaKvarova(iznajmljivanja);
        //Simulacija.ispisLista(kvarovi);
        //Simulacija.ispisLista(kvarovi2);
        //System.out.println();


        //RACUNI
        Simulacija.racuni =Racun.listaRacuna(iznajmljivanja);


        //DNEVNI IZVJESTAJI

        Simulacija.izvjestaji = DnevniIzvjestaj.listaIzvjestaja();
        //Simulacija.ispisLista(izvjestaji);

        //SUMARNI IZVJESTAJ
        SumarniIzvjestaj sumarniIzvjestaj = new SumarniIzvjestaj(izvjestaji);
        //System.out.println(sumarniIzvjestaj);

        ArrayList<ArrayList<Iznajmljivanje>> grupisanaIznajmljivanja = IznajmljivanjeParser.grupisiIznajmljivanjaPoVremenu();



        for (int i = 0; i < grupisanaIznajmljivanja.size(); i++) {
            System.out.println("Grupa " + (i + 1) + ":");
            ArrayList<Iznajmljivanje> lista = grupisanaIznajmljivanja.get(i);
            for (Iznajmljivanje iznajmljivanje : lista) {
                System.out.println(iznajmljivanje);
            }
            System.out.println();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Simulacija.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        GlavniController controller = fxmlLoader.getController();
        ControllerPristup.setController(controller);
        Scene scene = new Scene(root, 1300, 700);
        stage.setTitle("Projekat");
        stage.setScene(scene);
        stage.show();




        Task<Void> simulacijaTask = new Task<>() {
            @Override
            protected Void call() throws Exception {



                for (int i = 0; i < grupisanaIznajmljivanja.size(); i++) {
                    ArrayList<Iznajmljivanje> lista = grupisanaIznajmljivanja.get(i);
                    List<Thread> niti = new ArrayList<>();

                    // Kreiranje i pokretanje niti za svako iznajmljivanje
                    for (Iznajmljivanje iznajmljivanje : lista) {

                        Thread nit = new Thread(iznajmljivanje);
                        niti.add(nit);
                        nit.start();
                    }

                    // Čekanje da sve niti iz ove grupe završe
                    for (Thread nit : niti) {
                        nit.join();
                    }

                    // Pauza od 5 sekundi između vremenskih perioda
                    if (i < grupisanaIznajmljivanja.size() - 1) {
                        TimeUnit.SECONDS.sleep(5);
                    }
                }

                // Završetak simulacije
                Platform.runLater(() -> {
                    System.out.println("Sva iznajmljivanja su završena.");
                    GlavniController glavniController = ControllerPristup.getController();
                    if (glavniController != null) {
                        glavniController.setSimulacijaZavrsena(true);
                    }
                });

                return null;
            }
        };


        Thread simulacijaThread = new Thread(simulacijaTask);
        simulacijaThread.setDaemon(true);
        simulacijaThread.start();

    }

    /**
     * Glavna metoda koja pokrece aplikaciju.
     * @param args Argumenti komandne linije.
     */
    public static void main(String[] args) {
        launch();
    }
}






