package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage extends Entite {

    private static int compteur = 0;
    private IntegerProperty pv;
    private int pvMax;
    private int degat;
    private int indiceTerrain;
    private int indiceDepart;
    private int malusVitesse;
    private int taille;
    private Deplacement deplacement;
    private DeplacementAleatoire deplacementAlea;
    private int compteurAction;


    public Personnage(int pv, double vitesse,  int degat,
                      int taille, Environnement env, Deplacement deplacement){
        super("p"+compteur,0,0,vitesse,env,taille);
        compteur++;

        this.pv = new SimpleIntegerProperty(pv);
        this.pvMax = pv;
        this.degat = degat;
        malusVitesse = 1;
        this.indiceTerrain = 0;
        this.taille = taille;
        this.indiceDepart = indiceTerrain;
        this.deplacement = deplacement;
        this.deplacementAlea = new DeplacementAleatoire(env);
        compteurAction = 0;
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

    public void subirDegat(int degat){
        pv.setValue(pv.getValue() - degat);
    }

    public boolean estMort(){return pv.getValue() <= 0;}

    public void meurt(){
        setPv(0);
    }

    public boolean estTouché(Projectile projectile){
        double yProjectileH,yProjectileB,xProjectileG,xProjectileD;
        xProjectileG = projectile.getX();
        xProjectileD = projectile.getX()+projectile.getTaille();
        yProjectileH = projectile.getY();
        yProjectileB = projectile.getY()+projectile.getTaille();
        return super.getX()<xProjectileD && super.getX()+getTaille()>xProjectileG && super.getY()<yProjectileB && super.getY()+getTaille()>yProjectileH;
    }

    public void seDeplace(int cible){

        int suivant;

        if(deplacement.cheminVersCibleExiste(indiceTerrain, cible)){
            suivant = deplacement.tileSuivante(indiceTerrain, cible);
        } else {
            suivant = deplacementAlea.tileSuivante(indiceTerrain, cible);
        }


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

    public int getCompteurAction() {
        return compteurAction;
    }

    public int getIndiceDepart() {
        return this.indiceDepart;
    }

    public void setIndiceDepart(int indiceDepart) {
        this.indiceDepart = indiceDepart;
    }

    @Override
    public void action(/*int temps*/) {
        seDeplace(getEnv().getTerrain().getIndiceCible());
        compteurAction++;
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
