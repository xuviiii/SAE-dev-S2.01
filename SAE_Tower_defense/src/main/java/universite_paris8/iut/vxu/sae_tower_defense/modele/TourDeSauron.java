package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeSauron extends TourProjectileInstantane {

    public TourDeSauron(double x, double y, Environnement map) {
        super(x, y, map, 10000, 10, 10, 10000);
    }

    @Override
    public void creerProjectile(double x, double y) {
        getEnv().getProjectiles().add(new ProjectileInstantane(x,y,getEnv(),100,50));
    }

    @Override
    public void ameliorer() {

    }

}
