package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeSauron extends TourProjectileInstantane {

    public TourDeSauron(double x, double y, Environnement map) {
        super(x, y, 5, map, 64, 50000, 100, 10000, true);
    }

    @Override
    public void creerProjectile(double x, double y) {
        getEnv().getProjectiles().add(new ProjectileInstantane(x,y,getEnv(),100,50, false, false));
    }

    @Override
    public void ameliorer() {

    }

}
