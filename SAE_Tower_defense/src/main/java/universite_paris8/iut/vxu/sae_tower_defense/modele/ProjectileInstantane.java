package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class ProjectileInstantane extends Projectile {

    private int tailleMax;

    public ProjectileInstantane(double x, double y, Environnement env, int degat, int tailleMax) {
        super(x, y, 0, env, 20, degat);
        this.tailleMax = tailleMax;
    }

    @Override
    public void action() {
        if (getTaille()<40){
            setTaille(getTaille()+1);
        }
        else {
            ArrayList<Personnage> personnageTouches = projectileTouche();
            for (Personnage personnage : personnageTouches)
                personnage.subirDegat(super.getDegat());
            super.getEnv().getProjectiles().remove(this);
        }
    }
}
