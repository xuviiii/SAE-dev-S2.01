package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;

public class Mur extends Tour {

    private int pv;

    public Mur(double x, double y, Environnement map) {
        super(x, y, 1000, map, 60, 400, 0);
        pv = 100;
    }

    @Override
    public void agir() {

    }

    @Override
    public void ameliorer() {

    }
}
