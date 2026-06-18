package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Cibleur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.TourProjectile;

import java.util.ArrayList;

public class Camp extends TourHorsChemin {

    private Cibleur cibleur;
    ArrayList<TourProjectile> toursBoostes;

    public Camp(double x, double y, Environnement map) {
        super(x, y, 1000, map, 32, 1000, 0, 150);
        this.cibleur = new Cibleur(getPortee(), this);
        toursBoostes = new ArrayList<>();
    }

    @Override
    public void agir() {
        ArrayList<TourProjectile> toursABooster = cibleur.ciblerAllie();
        for (TourProjectile tourProjectile : toursABooster)
            if (!toursBoostes.contains(tourProjectile)){
                if (getNiveau()>=0)
                    tourProjectile.setBoostAttaque(tourProjectile.getBoostAttaque()*1.5);
                if (getNiveau()>=1)
                    tourProjectile.setBoostVitesse(tourProjectile.getBoostVitesse()*1.5);
                if (getNiveau()>=2)
                    tourProjectile.setPeutciblerCamoufle(true);
            }
    }

    public void enleverBoost() {
        for (TourProjectile tourProjectile : toursBoostes){
            if (getNiveau()>=0)
                tourProjectile.setBoostAttaque(tourProjectile.getBoostAttaque()/1.5);
            if (getNiveau()>=1)
                tourProjectile.setBoostVitesse(tourProjectile.getBoostVitesse()/1.5);
            if (getNiveau()>=2)
                tourProjectile.setPeutciblerCamoufle(false);
        }
    }

    @Override
    public int prixAmelioration() {
        return switch (getNiveau()) {
            case 0 -> 100;
            case 1 -> 200;
            case 2 -> 300;
            default -> 0;
        };
    }

    @Override
    public boolean estAuNiveauMax() {
        return getNiveau() > 2;
    }

    @Override
    public void ameliorer() {
        getEnv().enleverArgent(prixAmelioration());
        gainNiveau();
    }

    @Override
    public String toString() {
        return "Camp [ " + super.toString() + " ]" ;
    }
}