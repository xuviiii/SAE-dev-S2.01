package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance.Fleche;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

public class TourDeFleche extends TourProjectileLance{

    public TourDeFleche(double x, double y, Environnement map) {
        super(x, y, 30, map, 32, 100, 10, 100, false);
    }

    @Override
    public Projectile projectileALancer(double x,double y, double dx,double dy) {
        int nbEnnemieAToucher;
        if (getNiveau() < 3){
            nbEnnemieAToucher = 1;
        }
        else {
            nbEnnemieAToucher = 2;
        }
        return new Fleche(getX(),getY(),super.getEnv(),super.getDégât(),dx,dy,super.getPortee(),nbEnnemieAToucher);
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
        switch (getNiveau()){
            case 0: augmenterPortee(20); augmenterDegat(5);break;
            case 1: setPeutciblerCamoufle(true); break;
            case 3: augmenterVitesse(10);break;
            default: break;
        }
        getEnv().enleverArgent(prixAmelioration());
        gainNiveau();
    }

    @Override
    public int prixAmelioration() {
        switch (getNiveau()){
            case 0: return 100;
            case 1: return 200;
            case 2: return 300;
            case 3: return 400;
        }
        return 0;
    }

    @Override
    public boolean estAuNiveauMax() {
        return getNiveau() > 3;
    }

    @Override
    public String toString() {
        return "Tour de flèche [ " + super.toString() + " ]" ;
    }
}
