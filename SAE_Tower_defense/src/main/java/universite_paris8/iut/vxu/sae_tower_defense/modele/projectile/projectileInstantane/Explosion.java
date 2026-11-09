package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Effet.EffetProjectile;

import java.util.ArrayList;

public class Explosion extends ProjectileInstantane {

    private EffetProjectile effet;

    public Explosion(double x, double y, int degat, EffetProjectile effet) {
        super(x, y, 10, degat, 40);
        this.effet = effet;
    }


    @Override
    public void enPlus(Personnage personnage) {
        int alea = (int)(Math.random()*100)+1;
        if (effet != null && alea <= 10) {
            effet.effet(personnage);
        }

    }
}
