package gfn.marc.gui;

import gfn.marc.Feld;
import gfn.marc.Speicher;
import gfn.marc.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Fenster extends JFrame implements ActionListener {

    private final MyPanel myPanel;
    private Spielfeld spielfeld;
    private int fenstergroesse;

    // Menüleiste
    private JMenuBar menue;

    // Menüleiste Elemente
    private JMenu optionen;

    // Datei
    private final JMenuItem optionFenstergroesse;
    private final JMenuItem loescheSpielstaende;

    public Fenster() {
        this.setzeTitel("Tic Tac Toe");
        this.fenstergroesse = 600;
        this.myPanel = new MyPanel();
        this.getContentPane().add(this.myPanel);

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

        this.add(menue, BorderLayout.NORTH);

        this.setSize(fenstergroesse, fenstergroesse + 50);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    Speicher.spielstaendeSpeichern();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0); // sofortiges Programmende
            }
        });
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.spielfeld = new Spielfeld(this);
        this.setVisible(true);
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

    public void setzeTitel(String titel) {
        this.setTitle(titel);
    }

    public void setzeFenstergroesse(int fenstergroesse) {
        this.fenstergroesse = fenstergroesse;
        this.setSize(fenstergroesse, fenstergroesse + 50);
        this.spielfeld.resizeFelder(fenstergroesse);
        this.myPanel.repaint();
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public int getFenstergroesse() {
        return this.fenstergroesse;
    }

    class MyPanel extends JPanel {

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
