package gfn.marc;

import gfn.marc.gui.Fenster;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Zug {

    private static Fenster f = TicTacToe.getF();
    private static Spielfeld spielfeld = f.getSpielfeld();
    private static int zugNummer = 0;
    private static Spieler spieler;
    private static boolean zugLaeuft;
    private static CustomMouseListener[] cmls = new CustomMouseListener[9];

    public Zug() {
        createMouseListeners();
    }

    public static void setZugNummer(int zugNummer) {
        Zug.zugNummer = zugNummer;
    }

    // Zuganzahl hochzählen und Titelleiste setzen
    public static void macheZug(Spieler spieler) {
        Zug.spieler = spieler;
        if (zugNummer == 9) {
            Zug.zugNummer = 0;
        }
        Zug.zugNummer++;
        Zug.f.setzeTitel(spieler.getName() + " ist dran!");
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

    public static void restartZug() {
        macheZug(Zug.spieler);
        Zug.zugNummer--;
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

}

class CustomMouseListener extends MouseAdapter {

    private Fenster f;
    private Feld feld;
    private Spieler spieler;

    public CustomMouseListener(Fenster f, Feld feld) {
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
        Zug.setZugLaeuft(false);
    }
}


