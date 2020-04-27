package gfn.marc;

import gfn.marc.gui.Fenster;

import java.io.IOException;


public class TicTacToe {

    static Fenster f;
    static boolean DEBUG = true;

    public static void main(String[] args) throws IOException {

        // Laden der Spielstände
        Speicher.spielstaendeEinlesen();

        // Spiel starten
        try {
            boolean nochEinSpiel = true;
            while (nochEinSpiel) {

                f = null;
                f = new Fenster();
                nochEinSpiel = new Spiel(f).spielen();

                // aufräumen
                f.setVisible(false);
                Gewinner.setSpieler(null);
                Zug.setZugNummer(0);

            }
        } catch (InterruptedException ie) {
            System.err.println("Da ist was schief gelaufen!");
        }
        Speicher.spielstaendeSpeichern();
        System.exit(0); // sofortiges Programmende

    }

    public static Fenster getF() {
        return f;
    }

}