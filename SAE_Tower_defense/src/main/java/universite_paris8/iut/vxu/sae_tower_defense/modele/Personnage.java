package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class Personnage extends Entite {

    private static int compteur = 0;
    private IntegerProperty pv;
    private int pvMax;
    private int degat;
    private int indiceTerrain;
    private int taille;
    private Deplacement deplacement;
    private List<Integer> historiqueDeplacement;

    public Personnage(int pv, double x, double y, double vitesse,  int degat,
                      int taille, int indiceTerrain, Environnement env, Deplacement deplacement){
        super("p"+compteur,x,y,vitesse,env);
        compteur++;

        this.pv = new SimpleIntegerProperty(pv);
        this.pvMax = pv;
        this.degat = degat;
        this.taille = taille;
        this.indiceTerrain = indiceTerrain;
        this.deplacement = deplacement;
        historiqueDeplacement = new ArrayList<>(List.of(indiceTerrain));
    }

    public IntegerProperty getPvProperty(){
        return pv;
    }

    public int getPv() {
        return pv.getValue();
    }

    public void setPv(int pv) {
        // System.out.print("SOIN ! (" + pv + ") : [" + this.pv + ",");
        this.pv.setValue(Math.min(pv, this.pvMax));
        // System.out.println(this.pv + "]");
    }

    public int getDegat() {
        return degat;
    }

    public int getTaille() {
        return taille;
    }

    public void subirDegat(int degat){
        pv.setValue(pv.getValue() - degat);
    }

    public boolean estMort(){return pv.getValue() <= 0;}

    public void meurt(){
        setPv(0);
    }

    public boolean estTouché(double x,double y){
        return super.getX()-1<=x && super.getX()+taille+1>=x && super.getY()-1<=y && super.getY()+taille+1>=y;
    }

    public void seDeplace(int cible){

        int suivant = deplacement.tileSuivante(indiceTerrain, cible);

        double suivant_X = getEnv().getTerrain().toX(suivant);
        double suivant_Y = getEnv().getTerrain().toY(suivant);

        double dist_x = Math.abs(super.getX() - suivant_X);
        double dist_y = Math.abs(super.getY() - suivant_Y);

        if(super.getX() > suivant_X){
            super.setX(super.getX() - (Math.min(super.getVitesse(), dist_x)));
        }

        if(super.getX() < suivant_X){
            super.setX(super.getX() + (Math.min(super.getVitesse(), dist_x)));
        }

        if(super.getY() > suivant_Y){
            super.setY(super.getY() - (Math.min(super.getVitesse(), dist_y)));
        }

        if(super.getY() < suivant_Y){
            super.setY(super.getY() + (Math.min(super.getVitesse(), dist_y)));
        }

        if(super.getX() == suivant_X && super.getY() == suivant_Y){
            indiceTerrain = suivant;
            historiqueDeplacement.add(suivant);
        }
        

    }

    public int getIndiceTerrain(){
        return indiceTerrain;
    }

    public void setIndiceTerrain(int indiceTerrain) {
        this.indiceTerrain = indiceTerrain;
    }

    public Deplacement getDeplacement(){
        return this.deplacement;
    }

    public List<Integer> getHistoriqueDeplacement() {
        return historiqueDeplacement;
    }

    @Override
    public void action(int temps) {
        seDeplace(getEnv().getTerrain().getIndiceCible());
    }

    @Override
    public String toString() {
        return "Personnage{" +
                "pv=" + pv +
                ", x=" + super.getX() +
                ", y=" + super.getY() +
                ", degat=" + degat +
                '}';
    }
}
