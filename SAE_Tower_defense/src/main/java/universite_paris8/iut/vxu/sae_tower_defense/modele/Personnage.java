package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Personnage {
    private static int compteur;
    private String id;
    private int pv;
    private DoubleProperty x;
    private DoubleProperty y;
    private int vitesse;
    private int degat;
    private double taille;
    private int indiceTerrain;

    public Personnage(int pv, int x, int y, int vitesse,  int degat,
                      double taille, int indiceTerrain){
        compteur++;
        id= String.valueOf(compteur);
        this.pv = pv;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.vitesse = vitesse;
        this.degat = degat;
        this.taille = taille;
        this.indiceTerrain = indiceTerrain;
    }

    public double getTaille() {
        return taille;
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

    public int getVitesse() {
        return vitesse;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public boolean estMort(){return pv<=0;}

    public void action(Environnement env){
        seDeplace(env);
    }

    private void seDeplace(Environnement env){

        int suivant = env.tileSuivante(indiceTerrain);

        int suivant_X = (suivant % env.getLongueurMap()) * env.getTailleTile();
        int suivant_Y = (suivant / env.getLongueurMap()) * env.getTailleTile();

        double dist_x = Math.abs(x.getValue() - suivant_X);
        double dist_y = Math.abs(y.getValue() - suivant_Y);

        if(x.getValue() > suivant_X){
            x.setValue(x.getValue() - (Math.min(vitesse, dist_x)));
        }

        if(x.getValue() < suivant_X){
            x.setValue(x.getValue() + (Math.min(vitesse, dist_x)));
        }

        if(y.getValue() > suivant_Y){
            y.setValue(y.getValue() - (Math.min(vitesse, dist_y)));
        }

        if(y.getValue() < suivant_Y){
            y.setValue(y.getValue() + (Math.min(vitesse, dist_y)));
        }

        if(x.getValue() == suivant_X && y.getValue() == suivant_Y){
            indiceTerrain = suivant;
        }

    }

    public int getIndiceTerrain(){
        return indiceTerrain;
    }

    public void setIndiceTerrain(int indiceTerrain) {
        this.indiceTerrain = indiceTerrain;
    }

    @Override
    public String toString() {
        return "Personnage{" +
                "pv=" + pv +
                ", x=" + x +
                ", y=" + y +
                ", degat=" + degat +
                '}';
    }
}
