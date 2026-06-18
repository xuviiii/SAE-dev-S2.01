package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

import java.util.ArrayList;

public class ProjectileInstantane extends Projectile {

    private int tailleMax;
    private boolean enleveCuirasse;
    private boolean teleporte;

    public ProjectileInstantane(double x, double y, int vitesse, Environnement env, int degat, int tailleMax, boolean enleveCuirasse, boolean teleporte) {
        super(x, y, vitesse, env, 20, degat);
        this.tailleMax = tailleMax;
        this.enleveCuirasse =enleveCuirasse;
        this.teleporte = teleporte;
    }

    public double getAngle(){return 0;}

    @Override
    public void action() {
        int alea = (int)(Math.random()*100)+1;
        if (getTaille()<40){
            setTaille(getTaille()+1);
        }
        else {
            ArrayList<Personnage> personnageTouches = projectileTouche();
            for (Personnage personnage : personnageTouches) {
                personnage.subirDegat(super.getDegat());
                if (enleveCuirasse && alea <= 10) {
                    personnage.enleverCuirasses();
                }
                if (teleporte && alea <= 10) {
                    personnage.reculer(5);
                }
            }
            super.getEnv().getProjectiles().remove(this);
        }
    }
}
