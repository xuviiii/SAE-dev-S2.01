package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.ProjectileInstantane;

public class TourDeSauron extends TourProjectileInstantane {

    public TourDeSauron(double x, double y, Environnement map) {
        super(x, y, 5, map, 64, 50000, 100, 10000);
    }

    @Override
    public void creerProjectile(double x, double y) {
        getEnv().getProjectiles().add(new ProjectileInstantane(x,y,getEnv(),100,50));
    }

    @Override
    public void ameliorer() {

    }

}
