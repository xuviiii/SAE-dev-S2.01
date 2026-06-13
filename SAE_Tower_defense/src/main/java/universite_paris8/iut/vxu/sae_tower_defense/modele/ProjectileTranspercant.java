package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public abstract class ProjectileTranspercant extends ProjectileLance {
    private ArrayList<Personnage> ennemisTouches;

    public ProjectileTranspercant(double x, double y, int vitesse, Environnement env, int taille, int degat, double dx, double dy, int porteeTour) {
        super(x, y, vitesse, env, taille, degat, dx, dy, porteeTour);
        ennemisTouches = new ArrayList<>();
    }

    public ArrayList<Personnage> getEnnemisTouches() {
        return ennemisTouches;
    }


}
