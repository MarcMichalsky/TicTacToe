package gfn.marc;

import java.util.ArrayList;

public class Spieler {

    private static ArrayList<Spieler> spieler = new ArrayList<>();

    private String name;
    private Form zeichen;
    private int punktestand;

    // Konstruktor zum erzeugen der Spieler im aktuellen Spiel
    public Spieler() {
        Spieler.spieler.add(this);
    }

    // Konstruktor zum Laden der Spieler*innen aus dem Speicher
    public Spieler(String name, int punktestand) {
        this.name = name;
        this.punktestand = punktestand;
        Spieler.spieler.add(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Form getForm() {
        return zeichen;
    }

    public void setForm(Form form) {
        this.zeichen = form;
    }

    public int getPunktestand() {
        return punktestand;
    }

    public void setPunktestand() {
        this.punktestand++;
    }

    public static ArrayList<Spieler> getSpieler() {
        return spieler;
    }
}
