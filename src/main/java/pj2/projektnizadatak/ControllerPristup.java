package pj2.projektnizadatak;


/**
 * Singleton klasa za upravljanje instancom glavnog kontrolera aplikacije.
 * Ova klasa omogucava skladistenje i pristup jedinstvenoj instanci
 * klase {@link GlavniController}, što omogućava dijeljenje iste instance
 * izmedju razlicitih dijelova aplikacije.
 */
public class ControllerPristup {
    private static GlavniController instanca;

    /**
     * Postavlja instancu glavnog kontrolera.
     * Ova metoda se koristi za cuvanje instance glavnog kontrolera u ovoj klasi.
     *
     * @param controller instanca klase {@link GlavniController} koja se postavlja.
     */
    public static void setController(GlavniController controller) {
        instanca = controller;
    }

    /**
     * Vraca trenutnu instancu glavnog kontrolera.
     * Ova metoda omogucava pristup sacuvanoj instanci kontrolera iz drugih dijelova aplikacije.
     *
     * @return instanca klase {@link GlavniController}.
     */
    public static GlavniController getController() {
        return instanca;
    }
}
