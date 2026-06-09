package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeMage extends TourProjectileInstantane {

    public TourDeMage(double x, double y, Environnement map) {
        super(x, y, map, 100, 10, 10, 150);
    }

    @Override
    public void creerProjectile(double x, double y) {
        getEnv().getProjectiles().add(new ProjectileInstantane(x,y,getEnv(),10,15));
    }

    @Override
    public void ameliorer() {

    }
}
