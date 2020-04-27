package gfn.marc;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Zug {

    private static Frame f;
    private static Spielfeld spielfeld;
    private static int zugNummer = 0;
    private static boolean zugLaeuft;
    private static Spieler spieler;

    public Zug() {
        Zug.spielfeld = TicTacToe.getSpielfeld();
        Zug.f = TicTacToe.getF();
    }

    // Zuganzahl hochzählen und Titelleiste setzen
    public static void macheZug(Spieler spieler) {
        Zug.spieler = spieler;
        if (zugNummer == 9) {
            Zug.zugNummer = 0;
        }
        Zug.zugNummer++;
        f.setTitle(spieler.getName() + " ist dran!");
        Zug.setZugLaeuft(true);


        // Mouselistener auf Felder setzen, die noch nicht gesetzt wurden
        for (int i = 0; i < spielfeld.getFelder().length; i++) {
            if (!spielfeld.getFelder()[i].isGesetzt()) {
                spielfeld.getFelder()[i].getLabel().addMouseListener(new CustomMouseListener(i));
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
            spielfeld.getFelder()[i].getLabel().removeMouseListener(new CustomMouseListener(i));
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

    public static void setZugNummer(int zugNummer) {
        Zug.zugNummer = zugNummer;
    }

    // Lokale Klasse für CustomMouseListener
    static class CustomMouseListener extends MouseAdapter {

        int i;

        public CustomMouseListener(int i) {
            this.i = i;
        }

        // Auf Setzen des Feldes prüfen und ggf. Form zeichnen lassen
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            spielfeld.getFelder()[i].setZeichen(spieler.getForm());
            f.repaint();
            Zug.setZugLaeuft(false);
        }
    }

}






