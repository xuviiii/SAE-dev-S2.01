package universite_paris8.iut.vxu.sae_tower_defense.modele;

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
        if (getNiveau() < 2){
            switch (getNiveau()){
                case 0:  augmenterPortee(20);break;
                default: break;
            }
            gainNiveau();
        }
    }
}
