module pj2.projektnizadatak {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.sctp;


    opens pj2.projektnizadatak to javafx.fxml;
    opens pj2.projektnizadatak.vozila to javafx.base;
    opens pj2.projektnizadatak.kvar to javafx.base;
    opens pj2.projektnizadatak.izvjestaji to javafx.base;

    exports pj2.projektnizadatak;
}