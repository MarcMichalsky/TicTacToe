package gfn.marc;

import gfn.marc.gui.Fenster;

import java.io.IOException;


public class TicTacToe {

    static Fenster f;
    static boolean DEBUG = true;
    static Thread spiel = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                //f = null;
                f = new Fenster();
                boolean nochEinSpiel = true;
                while (nochEinSpiel) {

                    nochEinSpiel = new Spiel(f).spielen();

                    // aufräumen
                    //f.setVisible(false);
                    Gewinner.setSpieler(null);
                    Zug.setZugNummer(0);
                    f.getSpielfeld().reset();

                }
            } catch (InterruptedException ie) {
                System.err.println("Da ist was schief gelaufen!");
            }
        }
    });

    public static void main(String[] args) throws IOException, InterruptedException {

        // Laden der Spielstände
        Speicher.spielstaendeEinlesen();

        // Spiel starten
        spiel.start();
        spiel.join();
        Speicher.spielstaendeSpeichern();
        System.exit(0); // sofortiges Programmende

    }

    public static Fenster getF() {
        return f;
    }

}