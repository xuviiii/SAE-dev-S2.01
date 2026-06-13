package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class Rocher extends ProjectileLance{

    private ArrayList<Personnage> ennemisTouches;
    private boolean enfaleme;

    public Rocher(double x, double y, Environnement env, int degat, double dx, double dy, int porteeTour, boolean enfaleme) {
        super(x, y, 7, env, 20, degat, dx, dy, porteeTour);
        ennemisTouches = new ArrayList<>();
        this.enfaleme = enfaleme;
    }

    @Override
    public void attaquer(Personnage personnage) {
        if (!ennemisTouches.contains(personnage)){
            personnage.subirDegat(super.getDegat());
            personnage.ajoutTempEnflamer(5);
            ennemisTouches.add(personnage);
        }
    }


}
