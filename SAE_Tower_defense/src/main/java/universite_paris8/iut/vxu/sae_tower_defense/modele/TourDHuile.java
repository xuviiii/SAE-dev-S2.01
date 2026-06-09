package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDHuile extends TourProjectileLance {

    public TourDHuile(double x, double y, Environnement map) {
        super(x, y, 10, map, 32, 200, 10, 150);
    }

    @Override
    public Projectile projectileALancer(double x, double y, double dx, double dy) {
        Personnage cible = ennemiACible();
        return new TonneauDHuile(getX(),getY(),super.getEnv(),super.getDégât(),dx,dy,super.getPortee(),cible.getX(),cible.getY());
    }

    @Override
    public void ameliorer() {

    }

    @Override
    public void action() {
        if (getCompteurAction()%15==0){
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
}
