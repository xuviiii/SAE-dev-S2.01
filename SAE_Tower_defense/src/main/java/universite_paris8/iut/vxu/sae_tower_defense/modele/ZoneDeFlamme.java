package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class ZoneDeFlamme extends Projectile {

    private int compteur;

    public ZoneDeFlamme(double x, double y, Environnement env) {
        super(x, y, 10, env, 32, 0, 0, 0, 10);
        compteur = 0;
    }

    @Override
    public void attaquer(Personnage personnage) {
    }

    @Override
    public void action() {
        for (Personnage ennemi : super.getEnv().getPersonnages())
            if (ennemi.estTouché(this))
                ennemi.subirDegat(super.getDegat());
        compteur++;
        if (compteur == 200)
            getEnv().getProjectiles().remove(this);
    }


}
