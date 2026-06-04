package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class Rocher extends Projectile{

    private ArrayList<Personnage> ennemisTouches;

    public Rocher(double x, double y, int vitesse, Environnement env, double dx, double dy, int portee, int degat) {
        super(x, y, vitesse, env, 20, dx, dy, portee, degat);
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
