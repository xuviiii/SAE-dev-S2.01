package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {

    private int pv;
    private IntegerProperty x;
    private IntegerProperty y;
    private int dx;
    private int dy;
    private int degat;

    public Personnage(int pv, int x, int y, int dx, int dy, int degat){
        this.pv = pv;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dx = dx;
        this.dy = dy;
        this.degat = degat;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    public void setXProperty(int x) {
        this.x.setValue(x);
    }

    public IntegerProperty getYProperty() {
        return y;
    }

    public void setYProperty(int y) {
        this.y.setValue(y);
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }
}
