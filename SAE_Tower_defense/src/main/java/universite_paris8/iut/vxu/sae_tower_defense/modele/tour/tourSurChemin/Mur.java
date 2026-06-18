package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin;

import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Deplacement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.List;

public class Mur extends TourSurChemin {
  
    private int pv;

    public Mur(double x, double y, Environnement map) {
        super(x, y, 1000, map, 59, 400, 0, -1);
        pv = 100;
    }

    public void subirDegat(int degat){
        pv -=degat;
        if (pv<=0)
            getEnv().getTours().remove(this);
    }

    @Override
    public void agir() {
        int indice;
        List<Integer> adjacents;

        indice = (int)(((getX()-(getX()%getEnv().getTerrain().getTailleTile()))/getEnv().getTerrain().getTailleTile())+((getY()-(getY()%getEnv().getTerrain().getTailleTile()))/getEnv().getTerrain().getTailleTile()*getEnv().getTerrain().getLongueurMap()));
        adjacents = Deplacement.adjacents(getEnv(),indice);

        for (Personnage personnage : getEnv().getPersonnages()){
            if (adjacents.contains(personnage.getIndiceTerrain()))
                pv -= personnage.attaqueMur();
        }
        if (pv<=0)
            getEnv().getTours().remove(this);
    }



    @Override
    public int prixAmelioration() {
        if (getNiveau() == 0)
            return 200;
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

    @Override
    public String toString() {
        return "Mur [ " + super.toString() + " ]" ;
    }
}
