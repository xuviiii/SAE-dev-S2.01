package universite_paris8.iut.vxu.sae_tower_defense.modele;

import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.TourProjectile;

import java.util.ArrayList;

public class Cibleur {

    private int portee;
    private Entite entite;

    public Cibleur(int portee, Entite entite) {
        this.portee = portee;
        this.entite = entite;
    }

    public ArrayList<Personnage> ciblerEnnemie(){

        double h;
        ArrayList<Personnage> ennemisCiblables = new ArrayList<>();

        for (Personnage personnage : entite.getEnv().getPersonnages()) {
            h = Math.hypot(entite.getX()-personnage.getX(),entite.getY()-personnage.getY());
            if (h<portee)
                ennemisCiblables.add(personnage);
        }
        return ennemisCiblables;
    }

    public ArrayList<TourProjectile> ciblerAllie(){

        double h;

        ArrayList<TourProjectile> tours = new ArrayList<>();
        for (Tour tour : entite.getEnv().getTours()) {
            if (tour instanceof TourProjectile) {
                h = Math.hypot(entite.getX() - tour.getX(), entite.getY() - tour.getY());
                if (h < portee)
                    tours.add((TourProjectile) tour);
            }
        }
        return tours;
    }
}
