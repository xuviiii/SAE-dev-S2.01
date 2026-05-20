package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Achat;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class Drop implements EventHandler<DragEvent> {
    private Achat achat;
    private Pane terrain;

    public Drop(Achat achat, Pane terrain) {
        this.achat = achat;
        this.terrain = terrain;
    }

    @Override
    public void handle(DragEvent event) {
        Shape t;
        Dragboard db = event.getDragboard();
        boolean success;
        String id = ((Node)event.getGestureSource()).getId();
        success = achat.placerTour(id, event.getX(), event.getY());
        if (!success){
            System.out.println("raté");
        }
        event.setDropCompleted(success);
        event.consume();
        }

}
