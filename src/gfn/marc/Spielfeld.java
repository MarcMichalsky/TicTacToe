package gfn.marc;

import gfn.marc.gui.Fenster;

import java.awt.*;

import static gfn.marc.TicTacToe.DEBUG;

public class Spielfeld {

    final static int KORREKTUR_X = 0; // Korrektur der Spielfeldposition auf X Achse
    final static int KORREKTUR_Y = 0; // Korrektur der Spielfeldposition auf Y Achse

    private final Fenster f;
    private Feld[] felder = new Feld[9];

    // Unterteilung des Spielfelds in rechteckige Felder
    public Spielfeld(Fenster f) {
        this.f = f;
        int quadrant = (f.getFenstergroesse() / 3);
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                felder[k] = new Feld(new Rechteck(((quadrant * j) + KORREKTUR_X), (KORREKTUR_Y + (quadrant * i)),
                        quadrant, quadrant));
                k++;
            }
        }
    }

    // zeichne Spielfeld
    public void zeichneSpielfeld(Graphics2D g) {

        for (Feld feld : this.felder) {
            feld.getRechteck().setSichtbar(true);
            feld.getRechteck().paintMe(g);
            // Zahlen auf Felder einblenden, wenn Debug-Mode aktiviert ist
            this.f.hinzufuegen(feld.getLabel());
            if (DEBUG) {
                feld.getRechteck().paintId(g, feld.getFeldId());
            }
        }
    }

    public Feld[] getFelder() {
        return felder;
    }

    public void resizeFelder(int spielfeldgroesse) {
        Feld[] felderNeu = new Feld[9];
        int quadrant = (spielfeldgroesse / 3);
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                felderNeu[k] = new Feld(new Rechteck(((quadrant * j) + KORREKTUR_X), (KORREKTUR_Y + (quadrant * i)),
                        quadrant, quadrant));
                k++;
            }
        }
        for (int i = 0; i < this.felder.length; i++) {
            felderNeu[i].getRechteck().setGewinnerFeld(felder[i].getRechteck().isGewinnerFeld());
            felderNeu[i].getKreis().setSichtbar(felder[i].getKreis().isSichtbar());
            felderNeu[i].getKreuz().setSichtbar(felder[i].getKreuz().isSichtbar());
            felderNeu[i].setGesetzt(felder[i].isGesetzt());
            felderNeu[i].setFeldId(felder[i].getFeldId());
        }
        this.felder = felderNeu;
    }
}

