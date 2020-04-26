package gfn.marc;

import java.awt.*;

public abstract class Form {
    private int para1;
    private int para2;
    private int para3;
    private int para4;
    private boolean sichtbar;

    public Form() {
    }

    public Form(int para1, int para2, int para3, int para4) {
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.para4 = para4;
    }

    public abstract void paintMe(Graphics2D g);

    public int getPara1() {
        return para1;
    }

    public int getPara2() {
        return para2;
    }

    public int getPara3() {
        return para3;
    }

    public int getPara4() {
        return para4;
    }

    public void increasePara1(int x) {
        this.para1 += x;
    }

    public void increasePara2(int x) {
        this.para2 += x;
    }

    public void increasePara3(int x) {
        this.para3 += x;
    }

    public void increasePara4(int x) {
        this.para4 += x;
    }

    public boolean isSichtbar() {
        return sichtbar;
    }

    public void setPara1(int para1) {
        this.para1 = para1;
    }

    public void setPara2(int para2) {
        this.para2 = para2;
    }

    public void setPara3(int para3) {
        this.para3 = para3;
    }

    public void setPara4(int para4) {
        this.para4 = para4;
    }

    public void setSichtbar(boolean sichtbar) {
        this.sichtbar = sichtbar;
    }

}
