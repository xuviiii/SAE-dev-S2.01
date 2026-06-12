package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;

import java.util.ArrayList;

public class Rocher extends ProjectileLance {

    private ArrayList<Personnage> ennemisTouches;

    public Rocher(double x, double y, Environnement env, int degat, double dx, double dy, int porteeTour) {
        super(x, y, 7, env, 40, degat, dx, dy, porteeTour);
        ennemisTouches = new ArrayList<>();
    }

    @Override
    public void attaquer(Personnage personnage) {
        if (!ennemisTouches.contains(personnage)){
            personnage.subirDegat(super.getDegat());
            ennemisTouches.add(personnage);
        }
    }


}
