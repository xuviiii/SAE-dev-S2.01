package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.ProjectileInstantane;

public class TourDeMage extends TourProjectileInstantane {

    public TourDeMage(double x, double y, Environnement map) {
        super(x, y, 10, map, 32, 500, 10, 150);
    }

    @Override
    public void creerProjectile(double x, double y) {
        getEnv().getProjectiles().add(new ProjectileInstantane(x,y,getEnv(),10,15));
    }

    @Override
    public void ameliorer() {

    }
}
