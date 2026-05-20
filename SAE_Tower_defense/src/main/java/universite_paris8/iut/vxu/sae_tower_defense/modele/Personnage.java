package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
    private static int compteur;
    private String id;
    private int pv;
    private IntegerProperty x;
    private IntegerProperty y;
    private int dx;
    private int dy;
    private int degat;
    private int indiceTerrain; // indice de la tuile / position du perso

    public Personnage(int pv, int x, int y, int dx, int dy, int degat){
        compteur++;
        id= String.valueOf(compteur);
        this.pv = pv;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dx = dx;
        this.dy = dy;
        this.degat = degat;
        indiceTerrain = (Math.random() < 0.5) ? 90 : 0;
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

    public IntegerProperty getXProperty() {
        return x;
    }

    public int getX(){
        return this.x.getValue();
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public IntegerProperty getYProperty() {
        return y;
    }

    public int getY(){
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

    public int getIndiceTerrain(){
        return indiceTerrain;
    }

    public void setIndiceTerrain(int indiceTerrain) {
        this.indiceTerrain = indiceTerrain;
    }

    public void seDeplace(Environnement env){

        System.out.println("POSITION : " + indiceTerrain + " --> CHEMIN : " + env.cheminVersCible(indiceTerrain));
        indiceTerrain = (env.cheminVersCible(indiceTerrain).size() > 1)
                ? env.cheminVersCible(indiceTerrain).get(1)
                : env.cheminVersCible(indiceTerrain).get(0);
    }

    public boolean estMort(){return pv<=0;}

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
