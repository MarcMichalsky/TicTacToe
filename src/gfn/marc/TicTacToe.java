package gfn.marc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import static gfn.marc.Spielfeld.KORREKTUR_X;
import static gfn.marc.Spielfeld.KORREKTUR_Y;


public class TicTacToe extends JFrame {

    static Frame f;
    static int fensterGroesse;
    static Spielfeld spielfeld;
    static Spiel spiel;
    static boolean DEBUG = false;

    public static void main(String[] args) throws IOException {

        // Laden der Spielstände
        Speicher.spielstaendeEinlesen();

        // Eingabedialog: Fenstergröße
        String eingabe = "";
        while (true) {
            eingabe = JOptionPane.showInputDialog(f, "Wie groß soll das Spielfeld sein? (in Pixel)",
                    "600");
            try {
                fensterGroesse = Integer.parseInt(eingabe);
                break;
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Die Eingabe muss eine Zahl sein!");
            }
        }


        // Spiel starten
        try {
            boolean nochEinSpiel = true;
            while (nochEinSpiel) {

                f = new TicTacToe();
                f.setSize((fensterGroesse + KORREKTUR_X), (fensterGroesse + KORREKTUR_Y)); // Fenster Breite und Höhe
                f.setLocationRelativeTo(null);
                f.setLayout(null);
                f.setResizable(false);
                f.setTitle("Tic Tac Toe"); // Fenster Titeltext
                f.setVisible(true); // Fenster anzeigen
                f.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        try {
                            Speicher.spielstaendeSpeichern();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        System.exit(0); // sofortiges Programmende
                    }
                });

                spielfeld = new Spielfeld(f);
                spiel = new Spiel(f, spielfeld);
                nochEinSpiel = spiel.spielen();

                // aufräumen
                spielfeld.aufraeumen();
                Gewinner.setSpieler(null);
                spiel = null;
                spielfeld = null;
                Zug.setZugNummer(0);
                f.setVisible(false);
                f = null;
            }
        } catch (InterruptedException ie) {
            System.err.println("Da ist was schief gelaufen!");
        }
        Speicher.spielstaendeSpeichern();
        System.exit(0); // sofortiges Programmende

    }

    public static Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public static Frame getF() {
        return f;
    }

    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        if (spielfeld != null) {
            spielfeld.zeichneSpielfeld(g2);
            spielfeld.setSpielfeldExistiert(true);
        }

        if (spielfeld != null) {
            for (Feld feld : spielfeld.getFelder()) {
                feld.getKreuz().paintMe(g2);
                feld.getKreis().paintMe(g2);
            }
        }

        if (spielfeld != null) {
            for (Feld feld : spielfeld.getFelder()) {
                feld.getRechteck().faerbeGruen(g2);
                feld.getKreuz().paintMe(g2);
                feld.getKreis().paintMe(g2);
            }
        }
    }

}