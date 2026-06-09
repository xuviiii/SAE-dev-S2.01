package universite_paris8.iut.vxu.sae_tower_defense.modele;

public abstract class TourProjectileLance extends TourProjectile {

    public TourProjectileLance(double x, double y, Environnement map, int prix, int vitesse, int dégât, int portee) {
        super(x, y, map, prix, vitesse, dégât, portee);
    }

    public void creerProjectile(double x,double y) {
        double dx,dy,h;
        h = Math.hypot(x-super.getX(),y-super.getY());
        dx = (x-super.getX())/h;
        dy = (y-super.getY())/h;
        super.getEnv().getProjectiles().add(projectileALancer(x,y,dx,dy));
    }

    public abstract Projectile projectileALancer(double x,double y, double dx,double dy);
}
