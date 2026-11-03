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
    private Environnement environnement;

    public Placement(Environnement environnement) {
        this.environnement = environnement;
    }

    //créer et renvoie une tour selon un et une position donner
    public Tour selctionerTour(String id, double x, double y) {
        Tour nvTour;

        switch (id){
            case "flêche": nvTour = new TourDeFleche(x+16, y+16, environnement);break;
            case "huile": nvTour = new TourDHuile(x+16, y+16, environnement);break;
            case "catapulte": nvTour = new Catapulte(x+16, y+16, environnement,0,0);break;
            case "mage": nvTour = new TourDeMage(x+16, y+16, environnement);break;
            case "sauron": nvTour = new TourDeSauron(x+16, y+16, environnement);break;
            case "camp": nvTour = new Camp(x+16, y+16, environnement);break;
            case "marais": nvTour = new Marais(x+16, y+16, environnement);break;
            case "mur": nvTour = new Mur(x+16, y+16, environnement);break;
            default: nvTour = null;break;
        }
        return nvTour;
    }

    //verifie si on peut posser une tour hors du chemin
    public boolean peutEtrePoserTourHorsChemin(Tour tour){
        return !surAutreTour(tour) && horsChemin(tour);
    }

    //verifie si on peut posser une tour sur le chemin
    public boolean peutEtrePoserTourSurChemin(Tour tour){
        return !tourSurChemin(tour) && estSurUnChemin(tour);
    }

    //vérifie si il y une tour au possition donner
    public boolean surAutreTour(Tour tour){
        for (Tour t: environnement.getTours()){
            if(toursSuperposées(tour,t) || toursSuperposées(t,tour))
                return true;
        }
        return false;
    }

    public boolean toursSuperposées(Tour tour1,Tour tour2){
        return (tour1.getX()>= (tour2.getX()) &&  tour1.getX()<=(tour2.getX()+tour2.getTaille())||tour1.getX()+ tour1.getTaille()>= (tour2.getX()) &&  tour1.getX()+tour1.getTaille()<=(tour2.getX()+tour2.getTaille()))
                && (tour1.getY()>= (tour2.getY()) && tour1.getY()<=(tour2.getY()+tour2.getTaille())||tour1.getY()+tour1.getTaille()>= (tour2.getY()) && tour1.getY()+tour1.getTaille()<=(tour2.getY()+tour2.getTaille()));
    }

    // verifie si il n'y a pas de chemin au cordonner donner
    public boolean horsChemin(Tour tour){
        return !posSurChemin(tour.getX(), tour.getY())
                && !posSurChemin(tour.getX()+ tour.getTaille(), tour.getY() + tour.getTaille());
    }

    private boolean estSurUnChemin(Tour tour){
        return posSurChemin(tour.getX(), tour.getY());
    }

    private boolean posSurChemin(double x, double y) {
        Terrain terrain = environnement.getTerrain();
        return terrain.getMap().get(terrain.indiceTerrain(x, y)) == 1;
    }

    public boolean tourSurChemin(Tour tour){
        Terrain terrain = tour.getEnv().getTerrain();
        int indice = terrain.indiceTerrain(tour.getX(), tour.getY());
        for (Tour t: environnement.getTours()){
            if(t.getX()==terrain.positionXdepuisIndice(indice) && t.getY()==terrain.positionYdepuisIndice(indice))
                return true;
        }
        return false;
    }
}
