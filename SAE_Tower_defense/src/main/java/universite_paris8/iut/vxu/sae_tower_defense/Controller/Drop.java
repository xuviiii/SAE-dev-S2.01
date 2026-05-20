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
        Tour tour;
        Dragboard db = event.getDragboard();
        boolean success = false;
        String id = ((Node)event.getGestureSource()).getId();
        tour = achat.placerTour(id, event.getX(), event.getY());
        if (tour != null){
            System.out.println("ok");
            if (id.equals("flêche")) {
                t = new Rectangle(10,10,Color.BLUE);
            }
            else {
                t = new Rectangle(10,10,Color.BLACK);
            }
            terrain.getChildren().add(t);
            t.translateXProperty().bind(tour.xProperty());
            t.translateYProperty().bind(tour.yProperty());
            success = true;
        }
        if (!success){
            System.out.println("raté");
        }
        event.setDropCompleted(success);
        event.consume();
        }

}
