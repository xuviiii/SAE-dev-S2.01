package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.Explosion;

public class TourDeMage extends TourProjectileInstantane {

    public TourDeMage(double x, double y, Environnement map) {
        super(x, y, 10, map, 32, 500, 10, 150, false);
    }

    @Override
    public void creerProjectile(double x, double y) {
        boolean enleverCuirasse;
        boolean teleporte;
        enleverCuirasse = getNiveau() == 2;
        teleporte = getNiveau() == 3;

        getEnv().getProjectiles().add(new Explosion(x, y, getEnv(), 10, enleverCuirasse,teleporte));
    }

    @Override
    public void ameliorer() {
        if (getNiveau() == 0) {
            augmenterPortee(20);
            getEnv().enleverArgent(100);
        }
        getEnv().enleverArgent(prixAmelioration());
        gainNiveau();
    }

    @Override
    public int prixAmelioration() {
        return switch (getNiveau()) {
            case 0 -> 100;
            case 1, 2 -> 300;
            default -> 0;
        };
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
