package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Entite;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Deplacement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Gestionnaire.GestionnaireDeplacementPersonnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Gestionnaire.GestionnaireEffetPerso;

public abstract class Personnage extends Entite {

    private static int compteur = 0;

    private int pv;
    private int pvMax;
    private int degat;
    private int attaqueCooldown;
    private int compteurAttaque;
    private IntegerProperty tempEnflamer;

    private GestionnaireDeplacementPersonnage gestionnaireDeplacementPersonnage;

    private int gains; //valeur pour les gold

    private GestionnaireEffetPerso gestionnaireEffetPerso;

    public Personnage(int pv, double vitesse, int degat, int taille, Deplacement deplacement, int gains){
        super("p"+compteur,0,0,vitesse,taille);
        compteur++;

        this.pv = pv;
        this.pvMax = pv;
        this.degat = degat;
        this.gestionnaireEffetPerso = new GestionnaireEffetPerso(this);
        this.gestionnaireDeplacementPersonnage = new GestionnaireDeplacementPersonnage(0,0,1,deplacement,this );
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

    public GestionnaireDeplacementPersonnage getGestionnaireDeplacementPersonnage() {
        return gestionnaireDeplacementPersonnage;
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
    public void action() {
        gestionnaireDeplacementPersonnage.seDeplace(Terrain.getTerrain().getIndiceCible());
        degatEnflamer();
        compteurAttaque++;
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