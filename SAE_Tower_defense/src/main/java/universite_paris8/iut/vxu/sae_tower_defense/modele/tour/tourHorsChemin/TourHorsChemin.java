package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;

public abstract class TourHorsChemin extends Tour {

    private int portée;

    //Tour(double x, double y, Environnement map, int prix, int vitesse, int dégât)

    //Tour(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât)

    //TourHorsChemin(double x, double y, Environnement map, int prix, int vitesse, int dégât, int portee)

    public TourHorsChemin(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât, int portee) {
        super(x, y, vitesse, map, taille, prix, dégât);
        this.portée = portee;
    }

    public int getPortee() {
        return portée;
    }

}

