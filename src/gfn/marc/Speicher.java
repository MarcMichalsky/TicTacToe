package gfn.marc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Speicher {

    private static final CSVDatei csv = new CSVDatei(benutzerVerzeichnisErmitteln() + ".TicTacToe" +
            File.separator, "TicTacToe_spielstand.csv");

    public static void spielstaendeEinlesen() throws FileNotFoundException {
        ArrayList<String> eintraege = csv.lese();

        if (eintraege != null && !eintraege.isEmpty()) {
            for (String zeile : eintraege) {
                String[] eintrag = zeile.split(";");
                if (eintrag.length != 0) {
                    String spielername = eintrag[0];
                    String punktestand = eintrag[1];
                    new Spieler(spielername, Integer.parseInt(punktestand));
                }
            }
        }
    }

    public static void spielstaendeSpeichern() throws IOException {
        csv.schreibe(Spieler.getSpieler());
    }

    public static String benutzerVerzeichnisErmitteln() {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            //Windows
            return "C:\\users\\"+ System.getProperty("user.name") +"\\";
        }
        else if (os.contains("osx")){
            //OSX
            return "/home/" + System.getProperty("user.name" + "/");
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            //Linux/Unix
            return "/home/" + System.getProperty("user.name") + "/";
        } else {
            System.err.println("Benutzerverzeichnis konnte nicht gefunden werden.");
            return "";
        }
    }


}
