package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
    private static int compteur;
    private String id;
    private int pv;
    private DoubleProperty x;
    private DoubleProperty y;
    private int dx;
    private int dy;
    private int degat;
    private double taille;

    public Personnage(int pv, int x, int y, int dx, int dy, int degat, double taille){
        compteur++;
        id= String.valueOf(compteur);
        this.pv = pv;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.dx = dx;
        this.dy = dy;
        this.degat = degat;
        this.taille = taille;
    }

    public String getId() {
        return id;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public DoubleProperty getXProperty() {
        return x;
    }

    public double getX(){
        return this.x.getValue();
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public DoubleProperty getYProperty() {
        return y;
    }

    public double getY(){
        return this.y.getValue();
    }

    public void setY(int y) {
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

    public boolean estMort(){return pv<=0;}

    public void action(){

    }

    @Override
    public String toString() {
        return "Personnage{" +
                "pv=" + pv +
                ", x=" + x +
                ", y=" + y +
                ", dx=" + dx +
                ", dy=" + dy +
                ", degat=" + degat +
                '}';
    }
}
