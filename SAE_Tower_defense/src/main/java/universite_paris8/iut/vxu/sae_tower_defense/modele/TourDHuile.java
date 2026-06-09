package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDHuile extends TourProjectile {

    public TourDHuile(double x, double y, Environnement map, int prix, int vitesse, int dégât, int portee) {
        super(x, y, map, prix, vitesse, dégât, portee);
    }

    @Override
    public Projectile projectileALancer(double x, double y, double dx, double dy) {
        Personnage cible = ennemiACible();
        return new TonneauDHuile(getX(),getY(),10,super.getEnv(),10,dx,dy,super.getPortee(),super.getDégât(),cible.getX(),cible.getY());
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
