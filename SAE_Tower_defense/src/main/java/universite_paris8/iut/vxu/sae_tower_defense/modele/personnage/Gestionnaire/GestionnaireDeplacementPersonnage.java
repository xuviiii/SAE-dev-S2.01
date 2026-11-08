package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Gestionnaire;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Deplacement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.List;

public class GestionnaireDeplacementPersonnage {

    Personnage personnage;

    private int indiceTerrain;
    private int indiceDepart;
    private int malusVitesse;
    private Deplacement deplacement;

    public GestionnaireDeplacementPersonnage(int indiceTerrain, int indiceDepart, int malusVitesse, Deplacement deplacement, Personnage personnage) {
        this.indiceTerrain = indiceTerrain;
        this.indiceDepart = indiceDepart;
        this.malusVitesse = malusVitesse;
        this.deplacement = deplacement;
        this.personnage = personnage;
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



    public int getIndiceDepart() {
        return this.indiceDepart;
    }

    public void setIndiceDepart(int indiceDepart) {
        this.indiceDepart = indiceDepart;
    }

    public void seDeplace(int cible){

        int suivant = deplacement.tileSuivante(indiceTerrain, cible);

        double suivant_X = Terrain.getTerrain().positionXdepuisIndice(suivant);    //lorsqu'on aura un environnement avec un singleton on pourra modifié cette ligne
        double suivant_Y = Terrain.getTerrain().positionYdepuisIndice(suivant);

        double dist_x = Math.abs(personnage.getX() - suivant_X);
        double dist_y = Math.abs(personnage.getY() - suivant_Y);

        if(personnage.getX() > suivant_X){
            personnage.setX(personnage.getX() - (Math.min(personnage.getVitesse()/malusVitesse, dist_x)));
        }

        if(personnage.getX() < suivant_X){
            personnage.setX(personnage.getX() + (Math.min(personnage.getVitesse()/malusVitesse, dist_x)));
        }

        if(personnage.getY() > suivant_Y){
            personnage.setY(personnage.getY() - (Math.min(personnage.getVitesse()/malusVitesse, dist_y)));
        }

        if(personnage.getY() < suivant_Y){
            personnage.setY(personnage.getY() + (Math.min(personnage.getVitesse()/malusVitesse, dist_y)));
        }

        if(personnage.getX() == suivant_X && personnage.getY() == suivant_Y){
            indiceTerrain = suivant;
        }

        malusVitesse = 1;

    }

    public void reculer(int casse){
        List<Integer> chemin = deplacement.parcours(indiceTerrain, indiceDepart);
        int indice = nbCassereculMax(casse, chemin.size());
        indiceTerrain = chemin.get(indice);
        personnage.setX(Terrain.getTerrain().positionXdepuisIndice(indiceTerrain));
        personnage.setY(Terrain.getTerrain().positionYdepuisIndice(indiceTerrain));
    }
    public int nbCassereculMax(int casse, int longueurChemin){
        for (int i=0; i < casse ; i++)
            if ( casse-i < longueurChemin)
                return casse-i;
        return 0;
    }

    public void setMalusVitesse(int malusVitesse) {
        this.malusVitesse = malusVitesse;
    }
}
