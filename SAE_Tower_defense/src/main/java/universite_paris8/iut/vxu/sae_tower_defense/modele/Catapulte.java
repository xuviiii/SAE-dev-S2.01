package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Catapulte extends TourProjectile {

    private double xCible;
    private double yCible;

    public Catapulte(double x, double y, Environnement map, int prix, int vitesse, int dégât, double xCible, double yCible) {
        super(x, y, map, prix, vitesse, dégât, 2000);
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
        return new Rocher(getX(),getY(),10,super.getEnv(),dx,dy,super.getPortee(),super.getDégât());
    }

    @Override
    public void action() {
        if (getCompteurAction()%5==0)
            creerProjectile(xCible,yCible);
        compteurActionPlus();
    }

    @Override
    public void ameliorer() {

    }

}
