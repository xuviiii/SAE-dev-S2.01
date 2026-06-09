package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Catapulte extends TourProjectileLance {

    private double xCible;
    private double yCible;

    public Catapulte(double x, double y, Environnement map, double xCible, double yCible) {
        super(x, y, 5, map, 32, 300, 10, 10000);
        this.xCible = xCible;
        this.yCible = yCible;
    }

    public void setxCible(double xCible) {
        this.xCible = xCible;
    }

    public void setyCible(double yCible) {
        this.yCible = yCible;
    }

    @Override
    public Projectile projectileALancer(double x,double y, double dx,double dy) {
        return new Rocher(getX(),getY(),super.getEnv(),super.getDégât(),dx,dy,super.getPortee());
    }

    @Override
    public void action() {
        if (getCompteurAction()%15==0)
            creerProjectile(xCible,yCible);
        compteurActionPlus();
    }

    @Override
    public void ameliorer() {

    }

}
