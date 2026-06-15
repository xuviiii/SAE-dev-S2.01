package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

public class ZoneDeFlamme extends Projectile {

    private int compteur;
    private double multiplicateurDurer;

    public ZoneDeFlamme(double x, double y, Environnement env, int degat, double multiplicateurDurer) {
        super(x, y, 10, env, 32, degat);
        compteur = 0;
        this.multiplicateurDurer = multiplicateurDurer;
    }

    @Override
    public void action() {
        for (Personnage ennemi : super.getEnv().getPersonnages())
            if (ennemi.estTouché(this) && !ennemi.isCuirasses())
                ennemi.subirDegat(super.getDegat());
        compteur++;
        if (compteur == 200)
            getEnv().getProjectiles().remove(this);
    }


    @Override
    public double getAngle() {
        return 0;
    }
}
