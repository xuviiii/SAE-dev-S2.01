package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Entite;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.ArrayList;

public abstract class Projectile extends Entite {

    private static int compteur = 0;
    private int degat;

    public Projectile(double x, double y, int vitesse, Environnement env, int taille, int degat) {
        super("pr"+compteur,x,y,vitesse,env,taille);
        compteur++;

        this.degat = degat;
    }

    public int getDegat() {
        return degat;
    }

    public ArrayList<Personnage> projectileTouche() {
        ArrayList<Personnage> personnagesTouches = new ArrayList<>();
        for (Personnage personnage : super.getEnv().getPersonnages())
            if (personnage.estTouché(this))
                personnagesTouches.add(personnage);
        return personnagesTouches;
    }

    public abstract double getAngle();
}
