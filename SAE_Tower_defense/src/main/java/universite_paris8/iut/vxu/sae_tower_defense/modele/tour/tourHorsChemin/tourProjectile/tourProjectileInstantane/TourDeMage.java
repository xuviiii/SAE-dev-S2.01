package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Effet.EffetAntiCuirasse;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Effet.EffetProjectile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Effet.EffetTeleportation;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.Explosion;

public class TourDeMage extends TourProjectileInstantane {

    private EffetProjectile effetProjectile;

    public TourDeMage(double x, double y) {
        super(x, y, 10, 32, 500, 10, 150, false);
        effetProjectile = null;
    }

    @Override
    public void creerProjectile(double x, double y) {
        Environnement.getEnvironnement().getProjectiles().add(new Explosion(x, y, vraiDegat(), effetProjectile));
    }

    @Override
    public void ameliorer() {
        if (getNiveau() == 0) {
            augmenterPortee(20);
            Environnement.getEnvironnement().enleverArgent(prixAmelioration());
        }
        else
            Environnement.getEnvironnement().enleverArgent(prixAmelioration());
        switch (getNiveau()) {
            case 0 -> augmenterPortee(20);
            case 1 -> effetProjectile = new EffetAntiCuirasse();
            case 2 -> effetProjectile = new EffetTeleportation(5);
        };
        Environnement.getEnvironnement().enleverArgent(prixAmelioration());
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
