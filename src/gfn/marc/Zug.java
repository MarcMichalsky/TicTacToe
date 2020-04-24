package gfn.marc;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Zug {

    private static Frame f;
    private static Spielfeld spielfeld;
    private static int zugNummer = 0;
    private static boolean zugLaeuft;
    private static CustomMouseListener[] cmls = new CustomMouseListener[9];

    public Zug() {
        Zug.spielfeld = TicTacToe.getSpielfeld();
        Zug.f = TicTacToe.getF();
        createMouseListeners();
    }

    // Zuganzahl hochzählen und Titelleiste setzen
    public static void macheZug(Spieler spieler) {
        if (zugNummer == 9) {
            Zug.zugNummer = 0;
        }
        Zug.zugNummer++;
        f.setTitle(spieler.getName() + " ist dran!");
        Zug.setZugLaeuft(true);


        // Mouselistener auf Felder setzen, die noch nicht gesetzt wurden
        for (int i = 0; i < spielfeld.getFelder().length; i++) {
            if (!spielfeld.getFelder()[i].isGesetzt()) {
                cmls[i].setSpieler(spieler);
                spielfeld.getFelder()[i].getLabel().addMouseListener(cmls[i]);
            }
        }

        // Auf setzen eines Feldes warten
        while (Zug.isZugLaeuft()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                break;
            }
        }

        // MouseListener von allen Feldern entfernen
        for (int i = 0; i < spielfeld.getFelder().length; i++) {
            spielfeld.getFelder()[i].getLabel().removeMouseListener(cmls[i]);
        }

    }

    public static void createMouseListeners() {
        for (int i = 0; i < spielfeld.getFelder().length; i++) {
            cmls[i] = new CustomMouseListener(f, spielfeld.getFelder()[i]);
        }
    }

    public static int getZugNummer() {
        return zugNummer;
    }

    public static boolean isZugLaeuft() {
        return zugLaeuft;
    }

    public static void setZugLaeuft(boolean zugLaeuft) {
        Zug.zugLaeuft = zugLaeuft;
    }

    public static void setF(Frame f) {
        Zug.f = f;
    }

    public static void setSpielfeld(Spielfeld spielfeld) {
        Zug.spielfeld = spielfeld;
    }

    public static void setZugNummer(int zugNummer) {
        Zug.zugNummer = zugNummer;
    }

    public static void setCmls(CustomMouseListener[] cmls) {
        Zug.cmls = cmls;
    }
}


class CustomMouseListener implements MouseListener {

    private Frame f;
    private Feld feld;
    private Spieler spieler;

    public CustomMouseListener(Frame f, Feld feld) {
        this.f = f;
        this.feld = feld;
    }

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

    // Auf Setzen des Feldes prüfen und ggf. Form zeichnen lassen
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.feld.setZeichen(spieler.getForm());
        f.repaint();
        Zug.setZugLaeuft(false);
    }

    // notwendige Implementierungen ohne weitere Funktion
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}


