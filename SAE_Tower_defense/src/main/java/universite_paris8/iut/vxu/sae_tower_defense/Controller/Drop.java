package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Placement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Marais;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Mur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.TourSurChemin;

public class Drop implements EventHandler<DragEvent> {
    private Placement achat;
    private Drag drag;

    public Drop(Placement achat, Drag drag) {
        this.achat = achat;
        this.drag =drag;
    }

    @Override
    public void handle(DragEvent event) {
        Tour tour;
        boolean success;
        tour = drag.getTour();
        tour.setX(event.getX());
        tour.setY(event.getY());

        //vérifie si on peut placer la tour
        success = ((((achat.peutEtrePoserTourHorsChemin(tour)  && !(tour instanceof TourSurChemin)) || (achat.peutEtrePoserTourSurChemin(tour)  && (tour instanceof TourSurChemin)))) && Environnement.getEnvironnement().getArgent() >= tour.getPrix());

        //pose la tour si possible
        if (success){
            if (tour instanceof Marais || tour instanceof Mur){
                tour.setX(event.getX()-(event.getX()% Terrain.getTerrain().getTailleTile()));
                tour.setY(event.getY()-(event.getY()%Terrain.getTerrain().getTailleTile()));
                ((TourSurChemin)tour).changerTerrain();
            }
            Environnement.getEnvironnement().ajouterTour(tour);
            Environnement.getEnvironnement().enleverArgent(tour.getPrix());
        }

        event.setDropCompleted(success);
        event.consume();
        }

}
