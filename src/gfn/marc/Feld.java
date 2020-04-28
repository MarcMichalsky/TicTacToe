package gfn.marc;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Feld {
    private static int feldAnzahl = 0;
    private int feldId;
    private Rechteck rechteck;
    private JLabel label = new JLabel();
    private boolean gesetzt;
    private Kreuz kreuz;
    private Kreis kreis;

    static {

    }

    // Konstruktor
    public Feld(Rechteck rechteck) {
        if (feldAnzahl == 9) {
            Feld.feldAnzahl = 0;
        }
        this.rechteck = rechteck;
        Feld.feldAnzahl++;
        this.feldId = Feld.feldAnzahl;

        // Label Position und Größe des Rechtecks zuweisen
        this.label.setBounds((this.rechteck.getPara1() - Spielfeld.KORREKTUR_X), (this.rechteck.getPara2() -
                        Spielfeld.KORREKTUR_Y), this.rechteck.getPara3(), this.rechteck.getPara4());
        this.label.setVisible(true);

        kreuz = new Kreuz((int) (rechteck.getPara1() + (rechteck.getPara3() * 0.1)),
                ((int) (rechteck.getPara2() + (rechteck.getPara4() * 0.1))),
                ((int) (rechteck.getPara1() + (rechteck.getPara3() * 0.9))),
                ((int) (rechteck.getPara2() + (rechteck.getPara4() * 0.9))));

        kreis = new Kreis((int) (rechteck.getPara1() + (0.1 * rechteck.getPara3())),
                (int) (rechteck.getPara2() + (0.1 * rechteck.getPara4())),
                ((int) (rechteck.getPara3() * 0.8)),
                ((int) (rechteck.getPara4() * 0.8)));

        this.label.addMouseListener(new CustomMouseListener());

    }

    // Lokale Klasse für CustomMouseListener
    class CustomMouseListener extends MouseAdapter {

        // Auf Setzen des Feldes prüfen und ggf. Form zeichnen lassen
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (!gesetzt && Zug.isZugLaeuft()) {
                setZeichen(Spiel.getZug().getSpieler().getForm());
                Spiel.getZug().setZugLaeuft(false);
            }
        }
    }

    public void setZeichen(Form form) {
        if (form.getClass() == kreuz.getClass()) {
            kreuz.setSichtbar(true);
            kreis.setSichtbar(false);
        } else {
            kreis.setSichtbar(true);
            kreuz.setSichtbar(false);
        }
        gesetzt = true;

    }

    public JLabel getLabel() {
        return label;
    }

    public Rechteck getRechteck() {
        return rechteck;
    }

    public Kreuz getKreuz() {
        return kreuz;
    }

    public Kreis getKreis() {
        return kreis;
    }

    public int getFeldId() {
        return feldId;
    }

    public boolean isGesetzt() {
        return gesetzt;
    }

    public void setGesetzt(boolean gesetzt) {
        this.gesetzt = gesetzt;
    }

    public void setFeldId(int feldId) {
        this.feldId = feldId;
    }
}
