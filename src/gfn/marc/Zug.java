package gfn.marc;

import java.awt.*;

public class Zug {

    private Frame f;
    private static int zugNummer = 0;
    private boolean zugLaeuft;
    private Spieler spieler;

    public Zug() {
        this.f = TicTacToe.getF();
    }

    // Zuganzahl hochzählen und Titelleiste setzen
    public void macheZug(Spieler spieler) {
        this.spieler = spieler;
        if (Zug.zugNummer == 9) {
            Zug.zugNummer = 0;
        }
        Zug.zugNummer++;
        f.setTitle(spieler.getName() + " ist dran!");
        this.setZugLaeuft(true);

        // Auf setzen eines Feldes warten
        while (this.isZugLaeuft()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                break;
            }
        }
        f.repaint();

    }

    public static int getZugNummer() {
        return Zug.zugNummer;
    }

    public boolean isZugLaeuft() {
        return this.zugLaeuft;
    }

    public void setZugLaeuft(boolean zugLaeuft) {
        this.zugLaeuft = zugLaeuft;
    }

    public static void setZugNummer(int zugNummer) {
        Zug.zugNummer = zugNummer;
    }

    public Spieler getSpieler() {
        return this.spieler;
    }
}






