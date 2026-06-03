package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Entite {

    private String id;
    private DoubleProperty x;
    private DoubleProperty y;
    private int vitesse;

    public Entite(String id, double x, double y, int vitesse) {
        this.id = id;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.vitesse = vitesse;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty getXProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty getYProperty() {
        return y;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setX(double x) {
        this.x.setValue(x);
    }

    public void setY(double y) {
        this.y.setValue(y);
    }

    public abstract void action();
}
