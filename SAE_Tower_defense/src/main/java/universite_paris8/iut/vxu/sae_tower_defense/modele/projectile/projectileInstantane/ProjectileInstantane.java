package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

import java.util.ArrayList;

public class ProjectileInstantane extends Projectile {

    private int tailleMax;

    public ProjectileInstantane(double x, double y, int vitesse, Environnement env, int degat, int tailleMax) {
        super(x, y, vitesse, env, 20, degat);
        this.tailleMax = tailleMax;
    }

    public double getAngle(){return 0;}

    @Override
    public void action() {
        if (getTaille()<tailleMax){
            setTaille(getTaille()+1);
        }
        else {
            ArrayList<Personnage> personnageTouches = projectileTouche();
            for (Personnage personnage : personnageTouches) {
                personnage.subirDegat(super.getDegat());
                faireEffet(personnage);
            }
            super.getEnv().getProjectiles().remove(this);
        }
    }

    public void faireEffet(Personnage personnage) {
        //faire effet est nul car il ni a aucun effet pour les projectiles instantané mais seulement pour sa classe fille Explosion
    }

    public int getTailleMax() {
        return tailleMax;
    }
}
