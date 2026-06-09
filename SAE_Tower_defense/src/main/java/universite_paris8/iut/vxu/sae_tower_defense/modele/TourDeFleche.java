package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeFleche extends TourProjectileLance{

    public TourDeFleche(double x, double y, Environnement map) {
        super(x, y, 1, map, 32, 100, 10, 100);
    }

    @Override
    public Projectile projectileALancer(double x,double y, double dx,double dy) {
        return new Fleche(getX(),getY(),10,super.getEnv(),dx,dy,super.getPortee(),super.getDégât());
    }

    @Override
    public void action() {
        if (getCompteurAction()%2==0){
            Personnage cible = ennemiACible();
            double x,y;

            if (ennemiACible()!=null){
                x = cible.getX()+ (double) cible.getTaille() /2;
                y = cible.getY()+ (double) cible.getTaille() /2;
                creerProjectile(x,y);
            }
        }
        compteurActionPlus();
    }

    @Override
    public void ameliorer() {

    }
}
