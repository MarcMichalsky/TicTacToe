package gfn.marc;

import java.awt.*;

public class Rechteck extends Form {
    private boolean gewinnerFeld = false;
    public Rechteck(int para1, int para2, int para3, int para4) {
        super(para1, para2, para3, para4);
    }

    @Override
    public void paintMe(Graphics2D g) {
        if (this.isSichtbar()) {
            g.setColor(Color.black);
            g.setStroke(new BasicStroke(5));
            g.drawRect(this.getPara1(), this.getPara2(), this.getPara3(), this.getPara4());
        }
    }

    public void faerbeGruen(Graphics2D g) {
        if (this.isGewinnerFeld()) {
            g.setColor(Color.green);
            g.fillRect(this.getPara1(), this.getPara2(), this.getPara3(), this.getPara4());
            g.setColor(Color.black);
            g.setStroke(new BasicStroke(5));
            g.drawRect(this.getPara1(), this.getPara2(), this.getPara3(), this.getPara4());
        }
    }

    public void paintId(Graphics g, int i) {
        i += 47;
        char[] c = {(char) i};
        g.drawChars(c, 0, 1, ((int) (this.getPara1() + (0.5 * this.getPara3()))),
                ((int) (this.getPara2() + (0.5 * this.getPara4()))));
    }

    public boolean isGewinnerFeld() {
        return gewinnerFeld;
    }

    public void setGewinnerFeld(boolean gewinnerFeld) {
        this.gewinnerFeld = gewinnerFeld;
    }
}
