package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Entite;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.ArrayList;

public abstract class Projectile extends Entite {

    private static int compteur = 0;
    private int degat;

    public Projectile(double x, double y, int vitesse, int taille, int degat) {
        super("pr"+compteur,x,y,vitesse,taille);
        compteur++;

        this.degat = degat;

    }

    @Override
    public void action() {
        ArrayList<Personnage> personnageTouches = projectileTouche();
        for (Personnage personnage : personnageTouches) {
            personnage.subirDegat(getDegat());
            enPlus(personnage);
        }
    }

    public abstract void enPlus(Personnage personnage);


    public int getDegat() {
        return degat;
    }

    public ArrayList<Personnage> projectileTouche() {
        ArrayList<Personnage> personnagesTouches = new ArrayList<>();
        for (Personnage personnage : Environnement.getEnvironnement().getPersonnages())
            if (personnage.estTouché(this))
                personnagesTouches.add(personnage);
        return personnagesTouches;
    }

    public abstract double getAngle();
}
