package gfn.marc;

public class Gewinner {
    private static Spieler spieler;

    public static Spieler ermittleGewinner(Spiel spiel) {

        // Prüfe Horizontale
        for (int i = 0; i <= 8; i += 3) {

            if (spiel.getF().getSpielfeld().getFelder()[i].getKreuz().isSichtbar() &&
                    spiel.getF().getSpielfeld().getFelder()[i + 1].getKreuz().isSichtbar() &&
                    spiel.getF().getSpielfeld().getFelder()[i + 2].getKreuz().isSichtbar()) {
                Gewinner.spieler = spiel.getSpieler1();
                spiel.getSpieler1().setPunktestand();
                spiel.getF().getSpielfeld().getFelder()[i].getRechteck().setGewinnerFeld(true);
                spiel.getF().getSpielfeld().getFelder()[i + 1].getRechteck().setGewinnerFeld(true);
                spiel.getF().getSpielfeld().getFelder()[i + 2].getRechteck().setGewinnerFeld(true);
                break;
            }

            if (spiel.getF().getSpielfeld().getFelder()[i].getKreis().isSichtbar() &&
                    spiel.getF().getSpielfeld().getFelder()[i + 1].getKreis().isSichtbar() &&
                    spiel.getF().getSpielfeld().getFelder()[i + 2].getKreis().isSichtbar()) {
                Gewinner.spieler = spiel.getSpieler2();
                spiel.getSpieler2().setPunktestand();
                spiel.getF().getSpielfeld().getFelder()[i].getRechteck().setGewinnerFeld(true);
                spiel.getF().getSpielfeld().getFelder()[i + 1].getRechteck().setGewinnerFeld(true);
                spiel.getF().getSpielfeld().getFelder()[i + 2].getRechteck().setGewinnerFeld(true);
            }
        }

        // Prüfe Vertikale
        for (int i = 0; i < 3; i++) {
            if (spiel.getF().getSpielfeld().getFelder()[i].getKreuz().isSichtbar() &&
                    spiel.getF().getSpielfeld().getFelder()[i + 3].getKreuz().isSichtbar() &&
                    spiel.getF().getSpielfeld().getFelder()[i + 6].getKreuz().isSichtbar()) {
                Gewinner.spieler = spiel.getSpieler1();
                spiel.getSpieler1().setPunktestand();
                spiel.getF().getSpielfeld().getFelder()[i].getRechteck().setGewinnerFeld(true);
                spiel.getF().getSpielfeld().getFelder()[i + 3].getRechteck().setGewinnerFeld(true);
                spiel.getF().getSpielfeld().getFelder()[i + 6].getRechteck().setGewinnerFeld(true);
                break;
            }


            if (spiel.getF().getSpielfeld().getFelder()[i].getKreis().isSichtbar() &&
                    spiel.getF().getSpielfeld().getFelder()[i + 3].getKreis().isSichtbar() &&
                    spiel.getF().getSpielfeld().getFelder()[i + 6].getKreis().isSichtbar()) {
                Gewinner.spieler = spiel.getSpieler2();
                spiel.getSpieler2().setPunktestand();
                spiel.getF().getSpielfeld().getFelder()[i].getRechteck().setGewinnerFeld(true);
                spiel.getF().getSpielfeld().getFelder()[i + 3].getRechteck().setGewinnerFeld(true);
                spiel.getF().getSpielfeld().getFelder()[i + 6].getRechteck().setGewinnerFeld(true);
            }

        }

        // Prüfe Diagonale
        if (spiel.getF().getSpielfeld().getFelder()[0].getKreuz().isSichtbar() &&
                spiel.getF().getSpielfeld().getFelder()[4].getKreuz().isSichtbar() &&
                spiel.getF().getSpielfeld().getFelder()[8].getKreuz().isSichtbar()) {
            Gewinner.spieler = spiel.getSpieler1();
            spiel.getSpieler1().setPunktestand();
            spiel.getF().getSpielfeld().getFelder()[0].getRechteck().setGewinnerFeld(true);
            spiel.getF().getSpielfeld().getFelder()[4].getRechteck().setGewinnerFeld(true);
            spiel.getF().getSpielfeld().getFelder()[8].getRechteck().setGewinnerFeld(true);
        }

        if (spiel.getF().getSpielfeld().getFelder()[0].getKreis().isSichtbar() &&
                spiel.getF().getSpielfeld().getFelder()[4].getKreis().isSichtbar() &&
                spiel.getF().getSpielfeld().getFelder()[8].getKreis().isSichtbar()) {
            Gewinner.spieler = spiel.getSpieler2();
            spiel.getSpieler2().setPunktestand();
            spiel.getF().getSpielfeld().getFelder()[0].getRechteck().setGewinnerFeld(true);
            spiel.getF().getSpielfeld().getFelder()[4].getRechteck().setGewinnerFeld(true);
            spiel.getF().getSpielfeld().getFelder()[8].getRechteck().setGewinnerFeld(true);
        }

        if (spiel.getF().getSpielfeld().getFelder()[2].getKreuz().isSichtbar() &&
                spiel.getF().getSpielfeld().getFelder()[4].getKreuz().isSichtbar() &&
                spiel.getF().getSpielfeld().getFelder()[6].getKreuz().isSichtbar()) {
            Gewinner.spieler = spiel.getSpieler1();
            spiel.getSpieler1().setPunktestand();
            spiel.getF().getSpielfeld().getFelder()[2].getRechteck().setGewinnerFeld(true);
            spiel.getF().getSpielfeld().getFelder()[4].getRechteck().setGewinnerFeld(true);
            spiel.getF().getSpielfeld().getFelder()[6].getRechteck().setGewinnerFeld(true);
        }

        if (spiel.getF().getSpielfeld().getFelder()[2].getKreis().isSichtbar() &&
                spiel.getF().getSpielfeld().getFelder()[4].getKreis().isSichtbar() &&
                spiel.getF().getSpielfeld().getFelder()[6].getKreis().isSichtbar()) {
            Gewinner.spieler = spiel.getSpieler2();
            spiel.getSpieler2().setPunktestand();
            spiel.getF().getSpielfeld().getFelder()[2].getRechteck().setGewinnerFeld(true);
            spiel.getF().getSpielfeld().getFelder()[4].getRechteck().setGewinnerFeld(true);
            spiel.getF().getSpielfeld().getFelder()[6].getRechteck().setGewinnerFeld(true);
        }

        return Gewinner.spieler;

    }

    public static Spieler getSpieler() {
        return spieler;
    }

    public static void setSpieler(Spieler spieler) {
        Gewinner.spieler = spieler;
    }
}
