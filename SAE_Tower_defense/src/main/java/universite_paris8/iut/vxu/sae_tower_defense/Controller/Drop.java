package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Placement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Marais;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Mur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.TourSurChemin;

public class Drop implements EventHandler<DragEvent> {
    private Placement achat;
    private Drag drag;
    private Environnement env;

    public Drop(Placement achat, Drag drag, Environnement env) {
        this.achat = achat;
        this.drag =drag;
        this.env = env;
    }

    @Override
    public void handle(DragEvent event) {
        Tour tour;
        boolean success;
        tour = drag.getTour();
        tour.setX(event.getX());
        tour.setY(event.getY());

        //vérifie si on peut placer la tour
        success = ((((achat.peutEtrePoserTourHorsChemin(tour.getX(),tour.getY(),tour.getTaille())  && !(tour instanceof TourSurChemin)) || (achat.peutEtrePoserTourSurChemin(tour.getX(),tour.getY(),tour.getTaille())  && (tour instanceof TourSurChemin)))) && env.getArgent() >= tour.getPrix());

        //pose la tour si possible
        if (success){
            if (tour instanceof Marais || tour instanceof Mur){
                tour.setX(event.getX()-(event.getX()%env.getTerrain().getTailleTile()));
                tour.setY(event.getY()-(event.getY()%env.getTerrain().getTailleTile()));
                ((TourSurChemin)tour).changerTerrain();
            }
            env.ajouterTour(tour);
            env.enleverArgent(tour.getPrix());
        }

        event.setDropCompleted(success);
        event.consume();
        }

}
