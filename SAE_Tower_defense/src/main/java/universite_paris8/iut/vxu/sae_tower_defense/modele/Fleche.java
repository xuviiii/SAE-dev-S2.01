package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class Fleche extends ProjectileLance{

    public Fleche(double x, double y, Environnement env, int degat, double dx, double dy, int porteeTour) {
        super(x, y, 3, env, 5, degat, dx, dy, porteeTour);
    }

    @Override
    public void attaquer(Personnage personnage) {
        personnage.subirDegat(super.getDegat());
        super.getEnv().getProjectiles().remove(this);
    }

    @Override
    public ArrayList<Personnage> projectileTouche() {
        ArrayList<Personnage>personnageTouches = super.projectileTouche();
        while (personnageTouches.size()>1)
            personnageTouches.remove(personnageTouches.get(personnageTouches.size()-1));
        return personnageTouches;
    }
}
