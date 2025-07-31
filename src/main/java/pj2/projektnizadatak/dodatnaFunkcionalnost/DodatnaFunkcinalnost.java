package pj2.projektnizadatak.dodatnaFunkcionalnost;

import pj2.projektnizadatak.Simulacija;
import pj2.projektnizadatak.iznajmljivanje.Iznajmljivanje;
import pj2.projektnizadatak.kvar.Kvar;
import pj2.projektnizadatak.vozila.Vozilo;

import java.io.*;
import java.util.ArrayList;
/**
 * Utility klasa DodatnaFunkcinalnost sadrzi funkcije vezane za kvarove na vozilima,
 * ukljucujuci generisanje liste vozila sa kvarovima, sumiranje troskova kvarova pojedinacnog svakog vozila i
 * serijalizaciju/deserijalizaciju liste vozila.
 */
public class DodatnaFunkcinalnost {

    /**
     * Generise listu vozila koja su imala kvar tokom iznajmljivanja.
     *
     * @param iznajmljivanja lista svih iznajmljivanja
     * @return lista vozila koja su imala kvar
     */
    public static ArrayList<Vozilo> listaKvarova(ArrayList<Iznajmljivanje> iznajmljivanja) {
        ArrayList<Vozilo> vozilaSaKvarom = new ArrayList<>();

        for (Iznajmljivanje iznajmljivanje : iznajmljivanja) {
            if (iznajmljivanje.isPostojiKvar()) {
                Vozilo vozilo = iznajmljivanje.getVozilo();
                if (!vozilaSaKvarom.contains(vozilo)) {
                    vozilaSaKvarom.add(vozilo);
                }
            }
        }

        return vozilaSaKvarom;

    }

    /**
     * Suma svih troskova kvarova za svako vozilo.
     * Prolazi kroz sva iznajmljivanja i sabira troskove kvarova za svako vozilo.
     */
    public static void sumaKvarova() {
        for (Iznajmljivanje i : Simulacija.iznajmljivanja) {
            Vozilo vozilo = i.getVozilo();

            if (vozilo != null) {
                double sumaKvarova = 0.0;

                for (Kvar kvar : vozilo.getKvarovi()) {
                    sumaKvarova += kvar.getCijena();
                }
                vozilo.setUkupnoKvara(sumaKvarova);
            }
        }
    }

    /**
     * Serijalizuje listu vozila u datoteku na zadatoj putanji.
     *
     * @param lista lista vozila koja treba da se serijalizuje
     * @param fajlPutanja putanja do fajla u koji će lista biti serijalizovana
     */
    public static void serijalizujListu(ArrayList<Vozilo> lista, String fajlPutanja) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fajlPutanja))) {
            oos.writeObject(lista);
            System.out.println("Lista je serijalizovana u fajl: " + fajlPutanja);
        } catch (IOException e) {
            System.out.println("Greška pri serijalizaciji: " + e.getMessage());
        }
    }

    /**
     * Deserijalizuje listu vozila iz datoteke na zadatoj putanji.
     *
     * @param fajlPutanja putanja do fajla iz kojeg se deserijalizuje lista
     * @return deserijalizovana lista vozila
     */

    public static ArrayList<Vozilo> deserijalizujListu(String fajlPutanja) {
        ArrayList<Vozilo> lista = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fajlPutanja))) {
            lista = (ArrayList<Vozilo>) ois.readObject();
            System.out.println("Lista je deserijalizovana iz fajla: " + fajlPutanja);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Greška pri deserijalizaciji: " + e.getMessage());
        }
        return lista;
    }
}
