package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Entite {

    private String id;
    private DoubleProperty x;
    private DoubleProperty y;
    private double vitesse;
    private Environnement env;
    private int taille;

    public Entite(String id, double x, double y, double vitesse, Environnement env, int taille) {
        this.id = id;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.vitesse = vitesse;
        this.env = env;
        this.taille = taille;
    }

    public void augmenterVitesse(int ajout){
        vitesse += ajout;
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

    public double getVitesse() {
        return vitesse;
    }

    public Environnement getEnv() {
        return env;
    }

    public int getTaille() {
        return taille;
    }

    public void setX(double x) {
        this.x.setValue(x);
    }

    public void setY(double y) {
        this.y.setValue(y);
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public abstract void action();
}
