package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.Explosion;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.ProjectileInstantane;

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

        getEnv().getProjectiles().add(new Explosion(x, y, getEnv(), 10,enleverCirasse,teleporte));
    }

    @Override
    public void ameliorer() {
        switch (getNiveau()){
            case 0:  augmenterPortee(20);getEnv().enleverArgent(100);break;
            case 1: getEnv().enleverArgent(300);break;
            case 2: getEnv().enleverArgent(300);break;
            default: break;
        }
        gainNiveau();
    }

    @Override
    public int prixAmelioration() {
        switch (getNiveau()){
            case 0: return 100;
            case 1, 2: return 300;
        }
        return 0;
    }

    @Override
    public boolean estAuNiveauMax() {
        return getNiveau() > 1;
    }

    @Override
    public String toString() {
        return "TourDeMage [ " + super.toString() + " ]" ;
    }
}
