package gfn.marc;

import gfn.marc.gui.Fenster;

public class Zug {

    private Fenster f = TicTacToe.getF();
    private static int zugNummer = 0;
    private Spieler spieler;
    private static boolean zugLaeuft;

    public Zug() {
        if (zugNummer == 9) {
            Zug.zugNummer = 0;
        }
        Zug.zugNummer++;
    }

    public static void setZugNummer(int zugNummer) {
        Zug.zugNummer = zugNummer;
    }

    // Zuganzahl hochzählen und Titelleiste setzen
    public void macheZug(Spieler spieler) {
        this.spieler = spieler;
        this.f.setzeTitel(spieler.getName() + " ist dran!");
        this.setZugLaeuft(true);

        // Auf setzen eines Feldes warten
        while (this.isZugLaeuft()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                break;
            }
        }
    }

    public static int getZugNummer() {
        return zugNummer;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public boolean isZugLaeuft() {
        return Zug.zugLaeuft;
    }

    public void setZugLaeuft(boolean zugLaeuft) {
        Zug.zugLaeuft = zugLaeuft;
    }

}


