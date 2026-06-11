package universite_paris8.iut.vxu.sae_tower_defense.modele;

import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeMage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeSauron;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.Catapulte;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDHuile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDeFleche;

public class Achat {
    private Environnement map;

    public Achat(Environnement map) {
        this.map = map;
    }


    public Tour selctionerTour(String id, double x, double y) {
        Tour nvTour;


        if (id.equals("flêche"))
            nvTour = new TourDeFleche(x+16, y+16, map);
        else if (id.equals("huile"))
            nvTour = new TourDHuile(x+16, y+16, map);
        else if (id.equals("catapulte"))
            nvTour = new Catapulte(x+16, y+16, map,0,0);
        else if (id.equals("mage"))
            nvTour = new TourDeMage(x+16, y+16, map);
        else if (id.equals("sauron"))
            nvTour = new TourDeSauron(x+16, y+16, map);
        else if (id.equals("camp"))
            nvTour = new TourDHuile(x+16, y+16, map);
        else if (id.equals("marais"))
            nvTour = new TourDHuile(x+16, y+16, map);
        else if (id.equals("mur"))
            nvTour = new TourDHuile(x+16, y+16, map);
        else
            return null;
        return nvTour;
    }

    public boolean peutEtrePoser(double x,double y, int taille){
        return ((!surAutreTour(x,y)) && horsChemin(x,y,taille));
    }


    public boolean surAutreTour(double x,double y){
        for (Tour t:map.getTours()){
            if(x> (t.getX()-t.getTaille()-1) &&  x<(t.getX()+t.getTaille()+1) && y> (t.getY()-t.getTaille()-1) && y<(t.getY()+t.getTaille()+1)){
                return true;
            }
        }
        return false;
    }

    public boolean horsChemin(double x,double y, int taille){
        if(map.getTerrain().getMap().get((int)(((x-(x%map.getTerrain().getTailleTile()))/ map.getTerrain().getTailleTile())+((y-y%map.getTerrain().getTailleTile())/ map.getTerrain().getTailleTile())*map.getTerrain().getLongueurMap())) == 1
                || map.getTerrain().getMap().get((int)((((x+taille)-((x+taille)%map.getTerrain().getTailleTile()))/ map.getTerrain().getTailleTile())+(((y+taille)-(y+taille)%map.getTerrain().getTailleTile())/ map.getTerrain().getTailleTile())*map.getTerrain().getLongueurMap())) == 1){
            return false;
        }
        else {
            return true;
        }
    }

}
