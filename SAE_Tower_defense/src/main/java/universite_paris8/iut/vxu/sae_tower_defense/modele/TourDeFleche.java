package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeFleche extends TourProjectileLance{

    public TourDeFleche(double x, double y, Environnement map) {
        super(x, y, 30, map, 32, 100, 10, 100);
    }

    @Override
    public Projectile projectileALancer(double x,double y, double dx,double dy) {
        return new Fleche(getX(),getY(),super.getEnv(),super.getDégât(),dx,dy,super.getPortee());
    }

    @Override
    public void agir() {
        Personnage cible = ennemiACible();
        double x,y;

        System.out.println(cible);

        if (ennemiACible()!=null){
            x = cible.getX()+ (double) cible.getTaille() /2;
            y = cible.getY()+ (double) cible.getTaille() /2;
            creerProjectile(x,y);
        }
    }

    @Override
    public void ameliorer() {

    }
}
