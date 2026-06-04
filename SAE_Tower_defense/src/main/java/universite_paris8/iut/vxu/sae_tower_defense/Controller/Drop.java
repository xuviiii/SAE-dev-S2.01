package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Achat;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

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
        drag.viderTour();
        terrain.getChildren().remove(terrain.lookup("#"+db.getString()));
        tour.setX(event.getX());
        tour.setY(event.getY());
        success = (achat.peutEtrePoser(tour.getX(),tour.getY(),tour.getTaille())  && !(tour ==  null));
        if (success){
            env.ajouterTour(tour);
        }
        event.setDropCompleted(success);
        event.consume();
        }

}
