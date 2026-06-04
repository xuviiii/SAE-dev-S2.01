package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeFleche extends TourProjectile{

    public TourDeFleche(double x, double y, int taille, Environnement map, int prix, int vitesse, int dégât, int portee) {
        super(x, y, taille, map, prix, vitesse, dégât, portee);
    }

    @Override
    public void creerProjectile(Personnage ennemi) {
        double dx,dy,h;
        h = Math.hypot(ennemi.getX()-super.getX(),ennemi.getY()-super.getY());
        dx = (ennemi.getX()+ (double) ennemi.getTaille() /2-super.getX())/h;
        dy = (ennemi.getY()+ (double) ennemi.getTaille() /2-super.getY())/h;
        super.getEnv().getProjectiles().add(new Projectile(super.getDégât(),getX(),getY(),dx,dy,super.getPortee(),super.getEnv(),10));
    }

    @Override
    public void action() {
        if (ennemiACible()!=null) creerProjectile(ennemiACible());
    }

    @Override
    public void ameliorer() {

    }
}
