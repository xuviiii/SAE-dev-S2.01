package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

public class ZoneDeFlamme extends Projectile {

    private int compteur;
    private double multiplicateurDurer;

    public ZoneDeFlamme(double x, double y, int degat, double multiplicateurDurer) {
        super(x, y, 10, 64, degat);
        compteur = 0;
        this.multiplicateurDurer = multiplicateurDurer;
    }

    @Override
    public void enPlus(Personnage personnage) {

    }

    @Override
    public void action() {
       super.action();
        compteur++;
        if (compteur == 200*multiplicateurDurer)
            Environnement.getEnvironnement().getProjectiles().remove(this);
    }


    @Override
    public double getAngle() {
        return 0;
    }
}
