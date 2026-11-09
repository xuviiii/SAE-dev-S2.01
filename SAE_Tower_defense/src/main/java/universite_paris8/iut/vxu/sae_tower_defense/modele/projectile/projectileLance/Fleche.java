package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.ArrayList;

public class Fleche extends ProjectileTranspercant{

    private int nbEnnemieMax;

    public Fleche(double x, double y, int degat, double dx, double dy, int porteeTour, int nbEnnemieMax) {
        super(x, y, 5, 10, degat, dx, dy, porteeTour);
        this.nbEnnemieMax = nbEnnemieMax;
    }

    @Override
    public void enPlus(Personnage personnage) {
        getEnnemisTouches().add(personnage);
        if (getEnnemisTouches().size() == nbEnnemieMax)
            Environnement.getEnvironnement().getProjectiles().remove(this);

    }

    @Override
    public ArrayList<Personnage> projectileTouche() {
        ArrayList<Personnage>personnageTouches = super.projectileTouche();
        while (personnageTouches.size()>1)
            personnageTouches.remove(personnageTouches.get(personnageTouches.size()-1));
        return personnageTouches;
    }
}
