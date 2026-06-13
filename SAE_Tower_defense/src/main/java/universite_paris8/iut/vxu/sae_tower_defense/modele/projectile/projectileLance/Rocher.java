package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;

import java.util.ArrayList;

public class Rocher extends ProjectileTranspercant{

    private boolean enfaleme;

    public Rocher(double x, double y, Environnement env, int degat, double dx, double dy, int porteeTour, boolean enfaleme) {
        super(x, y, 7, env, 20, degat, dx, dy, porteeTour);
        this.enfaleme = enfaleme;
    }

    @Override
    public void attaquer(Personnage personnage) {
        if (!getEnnemisTouches().contains(personnage)){
            personnage.subirDegat(super.getDegat());
            if (enfaleme) {
                personnage.ajoutTempEnflamer(5);
            }
            getEnnemisTouches().add(personnage);
        }
    }


}
