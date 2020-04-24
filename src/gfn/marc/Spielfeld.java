package gfn.marc;

import java.awt.*;

import static gfn.marc.TicTacToe.fensterGroesse;
import static gfn.marc.TicTacToe.DEBUG;

public class Spielfeld {

    final static int KORREKTUR_X = 0; // Korrektur der Spielfeldposition auf X Achse
    final static int KORREKTUR_Y = 25; // Korrektur der Spielfeldposition auf Y Achse

    private int quadrant = fensterGroesse / 3;
    private Frame f;
    private Feld[] felder = new Feld[9];
    private boolean spielfeldExistiert;

    // Unterteilung des Spielfelds in rechteckige Felder
    public Spielfeld(Frame f) {
        this.f = f;
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                felder[k] = new Feld(new Rechteck(((this.quadrant * j) + KORREKTUR_X), (KORREKTUR_Y + (this.quadrant * i)),
                        this.quadrant, this.quadrant));
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
            this.f.add(feld.getLabel());
            if (DEBUG) {
                feld.getRechteck().paintId(g, feld.getFeldId());
            }
        }
    }

    // Spielfeld äufräumen
    public void aufraeumen() {

        felder = null;
        this.f = null;

    }

    public Feld[] getFelder() {
        return felder;
    }

    public Frame getF() {
        return f;
    }

    public boolean isSpielfeldExistiert() {
        return spielfeldExistiert;
    }

    public void setSpielfeldExistiert(boolean spielfeldExistiert) {
        this.spielfeldExistiert = spielfeldExistiert;
    }
}

