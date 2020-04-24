package gfn.marc;

import java.awt.*;

public class Kreuz extends Form {

    public Kreuz() {
    }

    public Kreuz(int para1, int para2, int para3, int para4) {
        super(para1, para2, para3, para4);
    }

    @Override
    public void paintMe(Graphics2D g) {
        if (this.isSichtbar()) {
            g.setColor(Color.blue);
            g.setStroke(new BasicStroke(10));
            g.drawLine(this.getPara1(), this.getPara2(), this.getPara3(), this.getPara4());
            g.drawLine(this.getPara3(), this.getPara2(), this.getPara1(), this.getPara4());
        }
    }

}
