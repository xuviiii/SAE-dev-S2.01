package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

public class Explosion extends ProjectileInstantane {

    public Explosion(double x, double y, Environnement env, int degat, boolean enleveCuirase, boolean teleport) {
        super(x, y, 10, env, degat, 40, enleveCuirase, teleport);
    }
}
