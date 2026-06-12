package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TourDeFleche extends TourProjectileLance{

    public TourDeFleche(double x, double y, Environnement map) {
        super(x, y, 30, map, 32, 100, 10, 100, false);
    }

    @Override
    public Projectile projectileALancer(double x,double y, double dx,double dy) {
        return new Fleche(getX(),getY(),super.getEnv(),super.getDégât(),dx,dy,super.getPortee());
    }

    @Override
    public void agir() {
        Personnage cible = ennemiACible();
        double x,y;

        if (cible!=null){
            x = cible.getX()+ (double) cible.getTaille() /2;
            y = cible.getY()+ (double) cible.getTaille() /2;
            creerProjectile(x,y);
        }
    }

    @Override
    public void ameliorer() {
        if (getNiveau() < 4){
            switch (getNiveau()){
                case 0: augmenterPortee(20); augmenterDegat(5);getEnv().enleverArgent(100);break;
                case 1: setPeutciblerCamoufle(true);getEnv().enleverArgent(200); break;
                case 2: getEnv().enleverArgent(300);break;
                case 3: augmenterVitesse(10);getEnv().enleverArgent(400);break;
                default: break;
            }
            gainNiveau();
        }
    }
}
