package gfn.marc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class CSVDatei{

    // Attribute
    private final String pfad;
    private final String dateiname;


    // Konstruktor
    public CSVDatei(String pfad, String dateiname) {
        this.pfad = pfad;
        this.dateiname = dateiname;
    }

    // Methode: schreibe()
    public void schreibe(ArrayList<Spieler> spieler) throws IOException {
        String pfadname = this.pfad + this.dateiname;
        File csvDatei = new File(pfadname);
        File verzeichnis = csvDatei.getParentFile();
        if (verzeichnis != null) {
            if (!verzeichnis.exists()) {
                verzeichnis.mkdirs();
            }
        }

        FileWriter csvSchreiben = new FileWriter(csvDatei);

        // schreibe Zeile
        for (Spieler s : spieler) {
            csvSchreiben.append(s.getName()).append(";").append(String.valueOf(s.getPunktestand())).append("\n");
        }
        csvSchreiben.flush();
        csvSchreiben.close();
    }

    // Methode: lese()
    public ArrayList<String> lese() throws FileNotFoundException {
        String pfadname = this.pfad + this.dateiname;
        File csvDatei;

        // neues FileObjekt erstellen
        csvDatei = new File(pfadname);

        if (csvDatei.exists()) {
            Scanner eingabe;

            // neues Scanner-Objekt erstellen
            eingabe = new Scanner(csvDatei);


            // Auslesen der Daten in einer Schleife und Ablegen in einer ArrayList
            ArrayList<String> ausgabe = new ArrayList<>();
            int i = 0;
            while(eingabe.hasNextLine()){
                String zeile = eingabe.nextLine();
                ausgabe.add(zeile);
                i++;
            }

            // Verbindung schliessen und ArrayList zurückgeben
            eingabe.close();
            return ausgabe;
        }
        return null;
    }

}
