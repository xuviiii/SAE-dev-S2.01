package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeMage extends TourProjectileInstantane {

    public TourDeMage(double x, double y, Environnement map) {
        super(x, y, 10, map, 32, 500, 10, 150, false);
    }

    @Override
    public void creerProjectile(double x, double y) {
        boolean enleverCirasse;
        boolean teleporte;
        enleverCirasse = getNiveau() == 2;
        teleporte = getNiveau() == 3;

        getEnv().getProjectiles().add(new ProjectileInstantane(x, y, getEnv(), 10, 15,enleverCirasse,teleporte));
    }

    @Override
    public void ameliorer() {
        if (getNiveau() < 3){
            switch (getNiveau()){
                case 0:  augmenterPortee(20);getEnv().enleverArgent(100);break;
                case 1: getEnv().enleverArgent(300);break;
                case 2: getEnv().enleverArgent(300);break;
                default: break;
            }
            gainNiveau();
        }
    }
}
