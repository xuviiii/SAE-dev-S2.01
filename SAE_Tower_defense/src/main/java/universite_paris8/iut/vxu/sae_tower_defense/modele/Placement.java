package universite_paris8.iut.vxu.sae_tower_defense.modele;

import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.Camp;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeMage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeSauron;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.Catapulte;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDHuile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDeFleche;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Marais;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Mur;

public class Placement {
    private Environnement map;

    public Placement(Environnement map) {
        this.map = map;
    }

    //créer et renvoie une tour selon un et une position donner
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
            nvTour = new Camp(x+16, y+16, map);
        else if (id.equals("marais"))
            nvTour = new Marais(x+16, y+16, map);
        else if (id.equals("mur"))
            nvTour = new Mur(x+16, y+16, map);
        else
            return null;
        return nvTour;
    }

    //verifie si on peut posser une tour hors du chemin
    public boolean peutEtrePoserTourHorsChemin(double x, double y, int taille){
        System.out.println("\nSur autre tour : "+surAutreTourHorsChemin(x,y)+", hors chemin : "+horsChemin(x,y,taille));
        return ((!surAutreTourHorsChemin(x,y)) && horsChemin(x,y,taille));
    }

    //verifie si on peut posser une tour sur le chemin
    public boolean peutEtrePoserTourSurChemin(double x, double y, int taille){
        return ((!surAutreTourSurChemin(x,y)) && !horsChemin(x,y,taille));
    }

    //vérifie si il y une tour au possition donner
    public boolean surAutreTourHorsChemin(double x,double y){
        for (Tour t:map.getTours()){
            if(x> (t.getX()-t.getTaille()-1) &&  x<(t.getX()+t.getTaille()+1) && y> (t.getY()-t.getTaille()-1) && y<(t.getY()+t.getTaille()+1)){
                return true;
            }
        }
        return false;
    }

    //vérifie si il y une tour au possition donner
    public boolean surAutreTourSurChemin(double x,double y){
        for (Tour t:map.getTours()){
            if(x>= (t.getX()) &&  x<(t.getX()+t.getTaille()) && y> (t.getY()) && y<=(t.getY()+t.getTaille())){
                return true;
            }
        }
        return false;
    }

    // verifie si il n'y a pas de chemin au cordonner donner
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
