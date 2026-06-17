package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance.TonneauDHuile;

public class TourDHuile extends TourProjectileLance {

    public TourDHuile(double x, double y, Environnement map) {
        super(x, y, 10, map, 32, 200, 10, 150, false);
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
        switch (getNiveau()){
            case 0: augmenterDegat(5);getEnv().enleverArgent(100);break;
            case 1: getEnv().enleverArgent(200);break;
            default: break;
        }
        gainNiveau();
    }

    @Override
    public int prixAmelioration() {
        switch (getNiveau()){
            case 0: return 100;
            case 1: return 200;
        }
        return 0;
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

    @Override
    public boolean estAuNiveauMax() {
        return getNiveau() > 1;
    }
}
