package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;

public class Mur extends Tour {

    private int pv;

    public Mur(double x, double y, Environnement map) {
        super(x, y, 1000, map, 59, 400, 0);
        pv = 100;
    }

    @Override
    public void agir() {

    }

    @Override
    public int prixAmelioration() {
        switch (getNiveau()){
            case 0: return 200;
        }
        return 0;
    }

    @Override
    public boolean estAuNiveauMax() {
        if (getNiveau()>0)
            return true;
        return false;
    }

    @Override
    public void ameliorer() {
        pv = 250;
        gainNiveau();
    }
}
