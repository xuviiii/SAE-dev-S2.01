package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeFleche extends TourProjectile{

    public TourDeFleche(double x, double y, Environnement map, int prix, int vitesse, int dégât, int portee) {
        super(x, y, map, prix, vitesse, dégât, portee);
    }

    @Override
    public Projectile projectileALancer(double x,double y, double dx,double dy) {
        return new Fleche(getX(),getY(),10,super.getEnv(),dx,dy,super.getPortee(),super.getDégât());
    }

    @Override
    public void action() {
        Personnage cible = ennemiACible();
        double x,y;

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
