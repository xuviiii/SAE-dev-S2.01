package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;

public abstract class TourSurChemin extends Tour {
    private int nvValeurCasse;

    public TourSurChemin(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât, int nvValeurCasse) {
        super(x, y, vitesse, map, taille, prix, dégât);
        this.nvValeurCasse = nvValeurCasse;
    }

    public void changerTerrain(){
        getEnv().getTerrain().getMap().set(getEnv().getTerrain().indiceTerrain(getX(),getY()),nvValeurCasse);
    }
}
