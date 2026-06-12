package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

import java.util.ArrayList;

public class ProjectileInstantane extends Projectile {

    private int compteur;

    public ProjectileInstantane(double x, double y, int vitesse, Environnement env, int degat, int taille) {
        super(x, y, vitesse, env, taille, degat);
        compteur = 0;
    }

    public double getAngle(){return 0;}

    @Override
    public void action() {
        if (compteur<50){
            compteur+=getVitesse();
        }
        else {
            ArrayList<Personnage> personnageTouches = projectileTouche();
            for (Personnage personnage : personnageTouches)
                personnage.subirDegat(super.getDegat());
            super.getEnv().getProjectiles().remove(this);
        }
    }
}
