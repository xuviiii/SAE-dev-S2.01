package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Achat;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Marais;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Mur;

public class Drop implements EventHandler<DragEvent> {
    private Achat achat;
    private Pane terrain;
    private Drag drag;
    private Environnement env;

    public Drop(Achat achat, Pane terrain, Drag drag, Environnement env) {
        this.achat = achat;
        this.terrain = terrain;
        this.drag =drag;
        this.env = env;
    }

    @Override
    public void handle(DragEvent event) {
        Tour tour;
        Dragboard db = event.getDragboard();
        boolean success;
        tour = drag.getTour();
        tour.setX(event.getX());
        tour.setY(event.getY());
        if (tour instanceof Marais || tour instanceof Mur){
            tour.setX(event.getX()-(event.getX()%env.getTerrain().getTailleTile()));
            tour.setY(event.getY()-(event.getY()%env.getTerrain().getTailleTile()));
        }

        //vérifie si on peut placer la tour
        success = (((achat.peutEtrePoserTourHorsChemin(tour.getX(),tour.getY(),tour.getTaille())  && !(tour instanceof Marais || tour instanceof Mur)) || (achat.peutEtrePoserTourSurChemin(tour.getX(),tour.getY(),tour.getTaille())  && (tour instanceof Marais || tour instanceof Mur))) && env.getArgent() >= tour.getPrix());

        //pose la tour si possible
        if (success){
            env.ajouterTour(tour);
            env.enleverArgent(tour.getPrix());
        }
        event.setDropCompleted(success);
        event.consume();
        }

}
