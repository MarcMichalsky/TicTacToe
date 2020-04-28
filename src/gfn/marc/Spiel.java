package gfn.marc;

import gfn.marc.gui.Fenster;

import javax.swing.*;

public class Spiel {

    private static Spieler spieler1;
    private static Spieler spieler2;
    private final Fenster f;
    private static Zug zug;


    Spiel(Fenster f) throws InterruptedException {

        this.f = f;

        // Spielernamen festlegen vor erstem Spiel
        if (spieler1 == null || spieler2 == null) {
            boolean neuerSpieler1 = false;

            // Eingebdialog: Name Spieler1
            String eingabe = "";
            while (true) {
                if (Spieler.getSpieler().size() > 1) {
                    eingabe = JOptionPane.showInputDialog(f, "Wie soll Spieler1 heißen?",
                            Spieler.getSpieler().get(Spieler.getSpieler().size() - 2).getName());
                } else {
                    eingabe = JOptionPane.showInputDialog(f, "Wie soll Spieler1 heißen?",
                            "Spieler1");
                }
                if (eingabe == null || !eingabe.contains(";")) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Der Spielername darf kein Semikolon enthalten");
                }
            }
            // Wenn der eingegebene Spielername vorhanden ist
            if ( eingabe != null && !eingabe.equals("")) {
                for (Spieler spieler : Spieler.getSpieler()) {
                    if (spieler != null) {
                        if (eingabe.equals(spieler.getName())) {
                            spieler1 = spieler;
                            spieler1.setForm(new Kreuz());
                            break;
                        }
                    }
                }
                // Wenn kein Spielername eingegeben wurde
            } else {
                spieler1 = new Spieler();
                spieler1.setForm(new Kreuz());
                spieler1.setName("Spieler1");
            }
            // Wenn ein neuer Spielername eingegeben wurde
            if (spieler1 == null) {
                spieler1 = new Spieler();
                spieler1.setForm(new Kreuz());
                spieler1.setName(eingabe);
                neuerSpieler1 = true;
            }

            // Eingebdialog: Name Spieler2
            while (true) {
                if (neuerSpieler1 && Spieler.getSpieler().size() > 1) {
                    eingabe = JOptionPane.showInputDialog(f, "Wie soll Spieler2 heißen?",
                            "Spieler2");
                } else if (Spieler.getSpieler().size() > 1) {
                    eingabe = JOptionPane.showInputDialog(f, "Wie soll Spieler2 heißen?",
                            Spieler.getSpieler().get(Spieler.getSpieler().size() - 1).getName());
                } else {
                    eingabe = JOptionPane.showInputDialog(f, "Wie soll Spieler2 heißen?",
                            "Spieler2");
                }
                if (eingabe == null || !eingabe.contains(";")) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Der Spielername darf kein Semikolon enthalten");
                }
            }

        // Wenn der eingegebene Spielername vorhanden ist
        if (eingabe != null && !eingabe.equals("")) {
            for (Spieler spieler : Spieler.getSpieler()) {
                if (spieler != null) {
                    if (eingabe.equals(spieler.getName())) {
                        spieler2 = spieler;
                        spieler2.setForm(new Kreis());
                        break;
                    }
                }
            }
            // Wenn kein Spielername eingegeben wurde
        } else {
            spieler2 = new Spieler();
            spieler2.setForm(new Kreis());
            spieler2.setName("Spieler2");
        }
        // Wenn ein neuer Spielername eingegeben wurde
        if (spieler2 == null) {
            spieler2 = new Spieler();
            spieler2.setForm(new Kreis());
            spieler2.setName(eingabe);
        }
    }

}

    public boolean spielen() {

        // Züge durchführen
        try {
            // Zufälliger Spieler startet
            double startSpieler = Math.random();
            while (Gewinner.ermittleGewinner(this) == null && Zug.getZugNummer() < 9) {
                if (zug == null || !Zug.isZugLaeuft()) {
                    zug = new Zug();
                } 
                if (Zug.getZugNummer() % 2 == 0) {
                    if (startSpieler <= 0.5d) {
                        zug.macheZug(spieler1);
                    } else {
                        zug.macheZug(spieler2);
                    }
                } else {
                    if (startSpieler > 0.5d) {
                        zug.macheZug(spieler1);
                    } else {
                        zug.macheZug(spieler2);
                    }
                }
            }
            f.repaint();
            Thread.sleep(500);
            f.setzeTitel(Spiel.spieler1.getName() + ": " + Spiel.spieler1.getPunktestand() + "  |  " +
                    Spiel.spieler2.getName() + ": " + Spiel.spieler2.getPunktestand());
            if (Gewinner.getSpieler() == null) {
                // Noch ein Spiel?
                int auswahl = JOptionPane.showOptionDialog(f, "Unentschieden!\nNoch ein Spiel?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                return auswahl != JOptionPane.NO_OPTION;
            } else {
                // Noch ein Spiel?
                int auswahl = JOptionPane.showOptionDialog(f, Gewinner.getSpieler().getName() + " hat gewonnen!\nNoch ein Spiel?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                return auswahl != JOptionPane.NO_OPTION;
            }
        } catch (InterruptedException ie) {
            System.err.println("Da ist was schief gelaufen!");
        }
        return false;
    }

    public Spieler getSpieler1() {
        return spieler1;
    }

    public Spieler getSpieler2() {
        return spieler2;
    }

    public Fenster getF() {
        return f;
    }

    public static Zug getZug() {
        return Spiel.zug;
    }
}
