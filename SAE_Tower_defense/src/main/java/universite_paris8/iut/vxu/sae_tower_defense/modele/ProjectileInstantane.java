package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class ProjectileInstantane extends Projectile {

    private int tailleMax;
    private boolean enleveCuirasse;
    private boolean teleporte;

    public ProjectileInstantane(double x, double y, Environnement env, int degat, int tailleMax, boolean enleveCuirasse, boolean teleporte) {
        super(x, y, 0, env, 20, degat);
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
                if (enleveCuirasse && alea < 10) {
                    personnage.enleverCuirasses();
                }
                if (enleveCuirasse && alea < 5) {
                    personnage.reculer(5);
                }
            }
            super.getEnv().getProjectiles().remove(this);
        }
    }
}
