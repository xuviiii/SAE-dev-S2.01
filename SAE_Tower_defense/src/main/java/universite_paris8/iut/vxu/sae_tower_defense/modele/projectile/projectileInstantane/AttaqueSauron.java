package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class AttaqueSauron extends ProjectileInstantane {

    public AttaqueSauron(double x, double y, Environnement env, int degat ) {
        super(x, y, 5, env, degat, 60 );
    }

    @Override
    public void enPlus(Personnage personnage) {}
}
