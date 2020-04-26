package gfn.marc.gui;

import gfn.marc.Feld;
import gfn.marc.Speicher;
import gfn.marc.Spielfeld;
import gfn.marc.Zug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Fenster extends JPanel implements ActionListener {

    private final JFrame fenster;
    private MyPanel myPanel;
    private Spielfeld spielfeld;
    private int fenstergroesse;

    // Menüleiste
    private JMenuBar menue;

    // Menüleiste Elemente
    private JMenu optionen;

    // Datei
    private JMenuItem optionFenstergroesse;
    private JMenuItem loescheSpielstaende;

    public Fenster() {
        this.fenster = new JFrame("Tic Tac Toe");
        this.fenstergroesse = 600;
        this.myPanel = new MyPanel();

        // Menüleiste erzeugen
        this.menue = new JMenuBar();

        // Menüelemente erzeugen
        this.optionen = new JMenu("Optionen");

        // Untermenüelemente erzeugen
        this.optionFenstergroesse = new JMenuItem("Fenstergröße");
        this.optionFenstergroesse.addActionListener(this);
        this.loescheSpielstaende = new JMenuItem("Spielstände löschen");
        this.loescheSpielstaende.addActionListener(this);

        // Menüelemente hinzufügen
        this.menue.add(optionen);

        // Untermenüelemente hinzufügen
        this.optionen.add(optionFenstergroesse);
        this.optionen.add(loescheSpielstaende);

        this.fenster.add(menue, BorderLayout.NORTH);

        this.fenster.setSize(fenstergroesse, fenstergroesse + 50);
        this.fenster.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    Speicher.spielstaendeSpeichern();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0); // sofortiges Programmende
            }
        });
        this.fenster.setLocationRelativeTo(null);
        this.fenster.setResizable(false);
    }

    public void hinzufuegen(Component c) {
        myPanel.add(c);
    }

    public void actionPerformed(ActionEvent object) {
        if (object.getSource() == optionFenstergroesse) {
            new Fenstergroesse(this);
        }
        if (object.getSource() == loescheSpielstaende) {
            System.out.println("Lösche Spielstände wurde angeklickt");
        }
    }

    public void newSpielfeld() {
        this.myPanel = null;
        this.spielfeld = null;
        this.myPanel = new MyPanel();
        this.fenster.getContentPane().add(this.myPanel);
        this.spielfeld = new Spielfeld(this);
    }

    public void macheSichtbar(boolean sichtbar) {
        this.fenster.setVisible(sichtbar);
    }

    public void setzeTitel(String titel) {
        this.fenster.setTitle(titel);
    }

    public void setzeFenstergroesse(int fenstergroesse) {
        this.fenstergroesse = fenstergroesse;
        this.fenster.setSize(fenstergroesse, fenstergroesse + 50);
        this.spielfeld.resizeFelder(fenstergroesse);
        this.myPanel.repaint();
    }

    public MyPanel getMyPanel() {
        return myPanel;
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public int getFenstergroesse() {
        return this.fenstergroesse;
    }

    class MyPanel extends JPanel {

        public MyPanel() {
            super();
        }

        public void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g;

            if (spielfeld != null) {
                spielfeld.zeichneSpielfeld(g2);
                this.repaint();
            }

            if (spielfeld != null) {
                for (Feld feld : spielfeld.getFelder()) {
                    feld.getKreuz().paintMe(g2);
                    feld.getKreis().paintMe(g2);
                    this.repaint();
                }
            }

            if (spielfeld != null) {
                for (Feld feld : spielfeld.getFelder()) {
                    feld.getRechteck().faerbeGruen(g2);
                    feld.getKreuz().paintMe(g2);
                    feld.getKreis().paintMe(g2);
                    this.repaint();
                }
            }
        }
    }
}
