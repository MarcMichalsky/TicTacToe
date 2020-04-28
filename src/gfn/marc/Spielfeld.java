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

    public void reset() {
        for (int i = 0; i < felder.length; i++) {
            felder[i].setGesetzt(false);
            felder[i].getRechteck().setGewinnerFeld(false);
            felder[i].getKreuz().setSichtbar(false);
            felder[i].getKreis().setSichtbar(false);
            f.repaint();
        }
    }
    public void resizeFelder(int spielfeldgroesse) {
        int quadrant = (spielfeldgroesse / 3);
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                felder[k].setRechteck(new Rechteck(((quadrant * j) + KORREKTUR_X), (KORREKTUR_Y + (quadrant * i)),
                        quadrant, quadrant));
                k++;
            }
        }

        for (int i = 0; i < felder.length; i++) {

            felder[i].getLabel().setBounds((felder[i].getRechteck().getPara1() - Spielfeld.KORREKTUR_X),
                    (felder[i].getRechteck().getPara2() - Spielfeld.KORREKTUR_Y),
                    felder[i].getRechteck().getPara3(), felder[i].getRechteck().getPara4());
            felder[i].getKreis().setPara1((int) (felder[i].getRechteck().getPara1() +
                    (0.1 * felder[i].getRechteck().getPara3())));
            felder[i].getKreis().setPara2((int) (felder[i].getRechteck().getPara2() +
                    (0.1 * felder[i].getRechteck().getPara4())));
            felder[i].getKreis().setPara3((int) (felder[i].getRechteck().getPara3() * 0.8));
            felder[i].getKreis().setPara4((int) (felder[i].getRechteck().getPara4() * 0.8));

            felder[i].getKreuz().setPara1((int)(felder[i].getRechteck().getPara1() +
                    (felder[i].getRechteck().getPara3() * 0.1)));
            felder[i].getKreuz().setPara2((int) (felder[i].getRechteck().getPara2() +
                    (felder[i].getRechteck().getPara4() * 0.1)));
            felder[i].getKreuz().setPara3((int) (felder[i].getRechteck().getPara1() +
                    (felder[i].getRechteck().getPara3() * 0.9)));
            felder[i].getKreuz().setPara4((int) (felder[i].getRechteck().getPara2() +
                    (felder[i].getRechteck().getPara4() * 0.9)));
        }
    }
}

