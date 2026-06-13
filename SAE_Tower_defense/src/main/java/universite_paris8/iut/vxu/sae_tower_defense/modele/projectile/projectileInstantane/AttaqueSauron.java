package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

public class AttaqueSauron extends ProjectileInstantane {

    public AttaqueSauron(double x, double y, Environnement env, int degat ) {
        super(x, y, 5, env, degat, 60,false, false );
    }
}
