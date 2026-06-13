package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class Fleche extends ProjectileTranspercant{

    private int nbEnnemieMax;

    public Fleche(double x, double y, Environnement env, int degat, double dx, double dy, int porteeTour, int nbEnnemieMax) {
        super(x, y, 3, env, 5, degat, dx, dy, porteeTour);
        this.nbEnnemieMax = nbEnnemieMax;
    }

    @Override
    public void attaquer(Personnage personnage) {
        if (!getEnnemisTouches().contains(personnage)) {
            if (!personnage.isCuirasses()) {
                personnage.subirDegat(super.getDegat());
            }
            getEnnemisTouches().add(personnage);
            if (getEnnemisTouches().size() == nbEnnemieMax)
            super.getEnv().getProjectiles().remove(this);
        }
    }

    @Override
    public ArrayList<Personnage> projectileTouche() {
        ArrayList<Personnage>personnageTouches = super.projectileTouche();
        while (personnageTouches.size()>1)
            personnageTouches.remove(personnageTouches.get(personnageTouches.size()-1));
        return personnageTouches;
    }
}
