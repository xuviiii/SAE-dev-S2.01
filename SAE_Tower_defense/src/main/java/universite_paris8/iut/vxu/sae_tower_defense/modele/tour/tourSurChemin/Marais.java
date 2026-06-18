package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class Marais extends TourSurChemin {

    public Marais(double x, double y, Environnement map) {
        super(x, y, 1000, map, 59, 700, 0, 10);
    }

    @Override
    public void agir() {
        for (Personnage ennemi : super.getEnv().getPersonnages())
            if (ennemi.estTouché(this))
                if (getNiveau()==0)
                    ennemi.setMalusVitesse(10);
                else if (getNiveau()==1)
                    ennemi.setMalusVitesse(20);
    }


    @Override
    public int prixAmelioration() {
        if (getNiveau() == 0)
            return 100;
        return 0;
    }

    @Override
    public boolean estAuNiveauMax() {
        return getNiveau() > 0;
    }

    @Override
    public void ameliorer() {
        getEnv().enleverArgent(prixAmelioration());
        gainNiveau();
    }

    @Override
    public String toString() {
        return "Marais [ " + super.toString() + " ]" ;
    }
}
