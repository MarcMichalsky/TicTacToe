package gfn.marc.gui;

import gfn.marc.Zug;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Fenstergroesse extends JFrame {

    private JFrame frame;
    private final Fenster hauptfenster;
    private JSlider schiebereglerFenstergroesse;
    private JLabel labelErgebnis;
    private JButton buttonOkay;
    private JButton buttonZuruecksetzen;
    private ChangeListener schiebereglerListener;
    private MouseListener schiebereglerMouseListener;

    public Fenstergroesse(Fenster hauptfenster) {
        super("Fenstergröße");
        this.frame = this;
        this.hauptfenster = hauptfenster;

        this.setSize(250, 150);
        this.setLocationRelativeTo(hauptfenster);
        this.setResizable(false);
        this.setVisible(true);
        this.initialisiereKomponenten();
        this.ordneKomponentenAn();
        this.registriereListener();
    }

    private void initialisiereKomponenten() {
        this.labelErgebnis = new JLabel("600 × 600");
        this.schiebereglerFenstergroesse = this.erstelleSchieberegler(300, 900);
        this.buttonOkay = new JButton("Okay");
        this.buttonZuruecksetzen = new JButton("zurücksetzen");
        this.schiebereglerListener = new SchiebereglerListener();
        this.schiebereglerMouseListener = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                hauptfenster.setzeFenstergroesse(schiebereglerFenstergroesse.getValue());
            }
        };
    }

    private JSlider erstelleSchieberegler(int minimum, int maximum) {
        JSlider schieberegler = new JSlider(minimum, maximum);
        schieberegler.setPaintLabels(true);
        schieberegler.setPaintTicks(true);
        schieberegler.setMinorTickSpacing(50);
        schieberegler.setMajorTickSpacing(100);
        schieberegler.setSnapToTicks(true);
        return schieberegler;
    }

    private void ordneKomponentenAn() {
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4, 4, 4, 4);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        this.getContentPane().add(this.labelErgebnis, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        this.getContentPane().add(this.schiebereglerFenstergroesse, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        this.getContentPane().add(this.buttonOkay, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        this.getContentPane().add(this.buttonZuruecksetzen, c);
    }

    private class SchiebereglerListener extends MouseAdapter implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            aktualisiereText();
        }
    }

    private void aktualisiereText() {
        StringBuilder text = new StringBuilder();
        text.append(String.valueOf(schiebereglerFenstergroesse.getValue()));
        text.append(" × ");
        text.append(String.valueOf(schiebereglerFenstergroesse.getValue()));
        this.labelErgebnis.setText(text.toString());
    }

    private void registriereListener() {
        this.schiebereglerFenstergroesse.addChangeListener(this.schiebereglerListener);
        this.schiebereglerFenstergroesse.addMouseListener(this.schiebereglerMouseListener);
        this.buttonOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        this.buttonZuruecksetzen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hauptfenster.setzeFenstergroesse(600);
                schiebereglerFenstergroesse.setValue(600);
            }
        });
    }
}

