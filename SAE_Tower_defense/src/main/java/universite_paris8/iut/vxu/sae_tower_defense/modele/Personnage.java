package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
    private static int compteur;
    private String id;
    private IntegerProperty pv;
    private DoubleProperty x;
    private DoubleProperty y;
    private int vitesse;
    private int degat;
    private int indiceTerrain;
    private int taille;

    public Personnage(int pv, int x, int y, int vitesse,  int degat,
                      int taille, int indiceTerrain){
        compteur++;
        id= "P"+compteur;
        this.pv = new SimpleIntegerProperty(pv);
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.vitesse = vitesse;
        this.degat = degat;
        this.taille = taille;
        this.indiceTerrain = indiceTerrain;
    }

    public String getId() {
        return id;
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty getPvProperty() {
        return pv;
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

    public int getTaille() {
        return taille;
    }

    public void subirDegat(int degat){
        pv.setValue(pv.getValue()-degat);
    }

    public boolean estMort(){return pv.getValue()<=0;}

    public boolean estTouché(double x,double y){
        return this.x.getValue()-1<=x && this.x.getValue()+taille+1>=x && this.y.getValue()-1<=y && this.y.getValue()+taille+1>=y;
    }

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
