package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDHuile extends TourProjectileLance {

    public TourDHuile(double x, double y, Environnement map) {
        super(x, y, 10, map, 32, 200, 10, 150);
    }

    @Override
    public Projectile projectileALancer(double x, double y, double dx, double dy) {
        Personnage cible = ennemiACible();
        double multiplicateurDurer;
        if (getNiveau() < 2)
            multiplicateurDurer = 1.5;
        else
            multiplicateurDurer = 1;
        return new TonneauDHuile(getX(),getY(),super.getEnv(),super.getDégât(),dx,dy,super.getPortee(),cible.getX(),cible.getY(), multiplicateurDurer);
    }

    @Override
    public void ameliorer() {
        if (getNiveau() < 2){
            switch (getNiveau()){
                case 0:  augmenterDegat(5);break;
                default: break;
            }
            gainNiveau();
        }
    }

    @Override
    public void agir() {
        Personnage cible = ennemiACible();
        double x,y;

        if (ennemiACible()!=null){
            x = cible.getX()+ (double) cible.getTaille() /2;
            y = cible.getY()+ (double) cible.getTaille() /2;
            creerProjectile(x,y);
        }
    }
}
