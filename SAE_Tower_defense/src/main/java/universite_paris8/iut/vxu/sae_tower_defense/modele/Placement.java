package universite_paris8.iut.vxu.sae_tower_defense.modele;

import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.Camp;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.TourHorsChemin;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeMage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeSauron;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.Catapulte;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDHuile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDeFleche;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Marais;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Mur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.TourSurChemin;

public class Placement {
    private Environnement map;

    public Placement(Environnement map) {
        this.map = map;
    }

    //créer et renvoie une tour selon un et une position donner
    public Tour selctionerTour(String id, double x, double y) {
        Tour nvTour;

        switch (id){
            case "flêche": nvTour = new TourDeFleche(x+16, y+16, map);break;
            case "huile": nvTour = new TourDHuile(x+16, y+16, map);break;
            case "catapulte": nvTour = new Catapulte(x+16, y+16, map,0,0);break;
            case "mage": nvTour = new TourDeMage(x+16, y+16, map);break;
            case "sauron": nvTour = new TourDeSauron(x+16, y+16, map);break;
            case "camp": nvTour = new Camp(x+16, y+16, map);break;
            case "marais": nvTour = new Marais(x+16, y+16, map);break;
            case "mur": nvTour = new Mur(x+16, y+16, map);break;
            default: nvTour = null;break;
        }
        return nvTour;
    }

    //verifie si on peut posser une tour hors du chemin
    public boolean peutEtrePoserTourHorsChemin(double x, double y, int taille){
        return ((!surAutreTourHorsChemin(x,y)) && horsChemin(x,y,taille));
    }

    //verifie si on peut posser une tour sur le chemin
    public boolean peutEtrePoserTourSurChemin(double x, double y, int taille){
        return ((!surAutreTourSurChemin(x,y)) && !horsChemin(x,y,taille));
    }

    //vérifie si il y une tour au possition donner
    public boolean surAutreTourHorsChemin(double x,double y){
        for (Tour t:map.getTours()){
            if(t instanceof TourHorsChemin && (x> (t.getX()-t.getTaille()-1) &&  x<(t.getX()+t.getTaille()+1) && y> (t.getY()-t.getTaille()-1) && y<(t.getY()+t.getTaille()+1))){
                return true;
            }
            if(t instanceof TourSurChemin && (x> (t.getX()- (double) t.getTaille() /2) &&  x<(t.getX()+t.getTaille()+1) && y> (t.getY()- (double) t.getTaille() /2) && y<(t.getY()+t.getTaille()+1))){
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
        return !estSurUnChemin(x, y);
    }

    private boolean estSurUnChemin(double x, double y) {
        Terrain terrain = map.getTerrain();
        return terrain.getMap().get(terrain.indiceTerrain(x, y)) == 1;
    }
}
