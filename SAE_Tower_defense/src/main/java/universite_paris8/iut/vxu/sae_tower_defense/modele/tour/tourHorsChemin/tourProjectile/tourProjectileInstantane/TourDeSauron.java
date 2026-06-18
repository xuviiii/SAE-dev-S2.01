package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.AttaqueSauron;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.ProjectileInstantane;

public class TourDeSauron extends TourProjectileInstantane {

    public TourDeSauron(double x, double y, Environnement map) {
        super(x, y, 5, map, 64, 50000, 100, 10000, true);
    }

    @Override
    public void creerProjectile(double x, double y) {
        getEnv().getProjectiles().add(new AttaqueSauron(x,y,getEnv(),100));
    }

    @Override
    public void ameliorer() {
    }

    @Override
    public int prixAmelioration() {
        return 0;
    }

    @Override
    public boolean estAuNiveauMax() {
        return true;
    }

    @Override
    public String toString() {
        return "TourDeSauron [ " + super.toString() + " ]";
    }
}
