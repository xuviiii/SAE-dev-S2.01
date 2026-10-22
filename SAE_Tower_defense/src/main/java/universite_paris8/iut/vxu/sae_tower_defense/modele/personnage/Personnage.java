package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Entite;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Deplacement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Gestionnaire.GestionnaireDeplacementPersonnage;

import java.util.List;

public abstract class Personnage extends Entite {

    private static int compteur = 0;

    private int pv;
    private int pvMax;
    private int degat;
    private int attaqueCooldown;
    private int compteurAttaque;
    private IntegerProperty tempEnflamer;


    private boolean cuirasses;

    private GestionnaireDeplacementPersonnage gestionnaireDeplacementPersonnage;

    private int gains; //valeur pour les gold

    private boolean camoufles;

    private int compteurAction; //a changé de classe

    public GestionnaireDeplacementPersonnage getGestionnaireDeplacementPersonnage() {
        return gestionnaireDeplacementPersonnage;
    }


    private GestionnaireEffetPerso gestionnaireEffetPerso;



    public Personnage(int pv, double vitesse, int degat,
                      int taille, Environnement env, Deplacement deplacement, int gains){
        super("p"+compteur,0,0,vitesse,env,taille);
        compteur++;

        this.pv = pv;
        this.pvMax = pv;
        this.degat = degat;
        this.gestionnaireDeplacementPersonnage = new GestionnaireDeplacementPersonnage(0,0,1,deplacement,this );
//        malusVitesse = 1;
//        this.indiceTerrain = 0;
//        this.indiceDepart = 0;
//        this.deplacement = deplacement;
        compteurAction = 0;
        tempEnflamer = new SimpleIntegerProperty(0);
        this.gains = gains;
        gestionnaireEffetPerso.rendreCamoufles();
        gestionnaireEffetPerso.rendreCuirases();
        attaqueCooldown = 100;
        compteurAttaque = 0;
    }

    public int getGains() {
        return gains;
    }

    public void ajoutTempEnflamer(int tempEnflamer) {
        this.tempEnflamer.set(this.tempEnflamer.get() + tempEnflamer);
    }

    public IntegerProperty tempEnflamerProperty() {
        return tempEnflamer;
    }

    public GestionnaireEffetPerso getGestionnaireEffetPerso() {
        return gestionnaireEffetPerso;
    }


    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = (Math.min(pv, this.pvMax));
    }

    public int getDegat() {
        return degat;
    }

    public void subirDegat(int degat){
        pv = pv - degat;
    }

    public boolean estMort(){return pv <= 0;}

    public void meurt(){
        setPv(0);
    }



    public int nbCassereculMax(int casse, int longueurChemin){
        for (int i=0; i < casse ; i++)
            if ( casse-i < longueurChemin)
                return casse-i;
        return 0;
    }


    private void degatEnflamer(){
        if (tempEnflamer.get() > 0){
            subirDegat(1);
            tempEnflamer.set(tempEnflamer.get() - 1);
        }
    }

    public int attaqueMur(){
        if (compteurAttaque>attaqueCooldown){
            compteurAttaque = 0;
            return degat;
        }
        return 0;
    }

    @Override
    public void action(/*int temps*/) {
        gestionnaireDeplacementPersonnage.seDeplace(getEnv().getTerrain().getIndiceCible());
        degatEnflamer();
        compteurAttaque++;
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