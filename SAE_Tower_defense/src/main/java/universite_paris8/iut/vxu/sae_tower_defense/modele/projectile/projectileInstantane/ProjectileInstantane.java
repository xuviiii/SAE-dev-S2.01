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
        int alea = (int)(Math.random()*100)+1;
        if (getTaille()<tailleMax){
            setTaille(getTaille()+1);
        }
        else {
            ArrayList<Personnage> personnageTouches = projectileTouche();
            for (Personnage personnage : personnageTouches) {
                personnage.subirDegat(super.getDegat());
            }
            super.getEnv().getProjectiles().remove(this);
        }
    }

    public int getTailleMax() {
        return tailleMax;
    }
}
