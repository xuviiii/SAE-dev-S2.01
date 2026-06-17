package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance.Rocher;

public class Catapulte extends TourProjectileLance {

    private double xCible;
    private double yCible;

    public Catapulte(double x, double y, Environnement map, double xCible, double yCible) {
        super(x, y, 15, map, 32, 300, 10, 10000, false);
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
        boolean enflamer = getNiveau()==1;
        return new Rocher(getX(),getY(),super.getEnv(),super.getDégât(),dx,dy,super.getPortee(), enflamer);
    }

    @Override
    public void agir() {
        creerProjectile(xCible,yCible);
    }

    @Override
    public void ameliorer() {
        switch (getNiveau()) {
            case 0: getEnv().enleverArgent(100);break;
            default:break;
        }
        gainNiveau();
    }

    @Override
    public int prixAmelioration() {
        switch (getNiveau()){
            case 0: return 100;
        }
        return 0;
    }

    @Override
    public boolean estAuNiveauMax() {
        return getNiveau() > 0;
    }

    @Override
    public String toString() {
        return "Catapulte [ " + super.toString() + " ]" ;
    }
}
