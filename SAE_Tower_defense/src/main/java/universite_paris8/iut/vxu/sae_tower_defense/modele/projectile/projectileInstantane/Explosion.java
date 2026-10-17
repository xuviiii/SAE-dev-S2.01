package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.ArrayList;

public class Explosion extends ProjectileInstantane {

    private Boolean enleveCuirasse;
    private Boolean teleporte;

    public Explosion(double x, double y, Environnement env, int degat, boolean enleveCuirase, boolean teleport) {
        super(x, y, 10, env, degat, 40);
        this.enleveCuirasse = enleveCuirase;
        this.teleporte = teleport;
    }

    @Override
    public void action() {
        int alea = (int)(Math.random()*100)+1;
        if (getTaille()<getTailleMax()){
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
