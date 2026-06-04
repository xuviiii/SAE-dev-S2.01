package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Fleche extends Projectile{

    public Fleche(double x, double y, int vitesse, Environnement env, double dx, double dy, int portee, int degat) {
        super(x, y, vitesse, env, 5, dx, dy, portee, degat);
    }

    @Override
    public void attaquer(Personnage personnage) {
        personnage.subirDegat(super.getDegat());
        super.getEnv().getProjectiles().remove(this);
    }
}
