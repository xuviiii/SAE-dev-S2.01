package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

public abstract class ProjectileInstantane extends Projectile {

    private int tailleMax;

    public ProjectileInstantane(double x, double y, int vitesse, int degat, int tailleMax) {
        super(x, y, vitesse, 20, degat);
        this.tailleMax = tailleMax;
    }

    public double getAngle(){return 0;}

    @Override
    public void action() {
        super.action();
        if (getTaille()<tailleMax){
            setTaille(getTaille()+1);
        }
        else{
            Environnement.getEnvironnement().getProjectiles().remove(this);
        }
    }



    public int getTailleMax() {
        return tailleMax;
    }
}
